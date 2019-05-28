package org.minidb.relation;


import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.minidb.bptree.*;
import org.minidb.exception.MiniDBException;

import org.minidb.utils.Misc;

import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Relation {
    public RelationMeta meta;
    public String directory; // the directory to store the relation data
    public MainDataFile data; // main tree for the data
    public ArrayList<BPlusTree> superKeyTrees, indexTrees, nullTrees;

    // create the relation, save the meta data and create data trees
    public void create() throws IOException, MiniDBException {
        meta.nextRowID = 0L;
        meta.validate();
        meta.write(Paths.get(directory, "meta").toString());
        createOrResumeData(true);
    }

    public void drop() throws IOException {
        Misc.rmDir(directory);
    }

    public void deleteAllData() throws IOException, MiniDBException {
        drop();
        new File(directory).mkdir();
        create();
    }

    // close the relation, save meta data and commit trees.
    public void close() throws IOException, MiniDBException {
        meta.write(Paths.get(directory, "meta").toString());

        data.close();

        for(BPlusTree each : superKeyTrees)
        {
            each.commitTree();
        }

        for(BPlusTree each : indexTrees)
        {
            each.commitTree();
        }

        for(BPlusTree each : nullTrees)
        {
            each.commitTree();
        }
    }

    // resume a relation from disk
    public void resume() throws FileNotFoundException, IOException, ClassNotFoundException, MiniDBException {
        meta = RelationMeta.read(Paths.get(directory, "meta").toString());
        createOrResumeData(false);
    }

    private void createOrResumeData(boolean create) throws FileNotFoundException, IOException, MiniDBException
    {
        String mode = create ? "rw+" : "rw";

        // main data
        ArrayList<Integer> colIDs = new ArrayList<>();
        for (int i = 0; i < meta.ncols; ++i)
        {
            colIDs.add(i);
        }
        data = new MainDataFile(
                new MainDataConfiguration(meta.coltypes, meta.colsizes, colIDs),
                mode,
                Paths.get(directory, "data").toString(),
                new BPlusTree(
                        new BPlusConfiguration(
                                1024,
                                8,
                                new ArrayList<>(Arrays.asList(Long.class)),
                                new ArrayList<>(Arrays.asList(8)),
                                new ArrayList<>(Arrays.asList(0)),
                                true,
                                1000),
                        mode,
                        Paths.get(directory, "rowID2position").toString()
                )
        );

        superKeyTrees = new ArrayList<>(Arrays.asList(new BPlusTree[meta.superKeys.size()]));
        indexTrees = new ArrayList<>(Arrays.asList(new BPlusTree[meta.indices.size()]));
        nullTrees = new ArrayList<>(Arrays.asList(new BPlusTree[meta.nullableColIds.size()]));

        // resume null tree
        for(int i = 0; i < nullTrees.size(); ++i)
        {
            BPlusTree tmp = new BPlusTree(
                    new BPlusConfiguration(
                            1024,
                            8,
                            new ArrayList<>(Arrays.asList(Long.class)),
                            new ArrayList<>(Arrays.asList(8)),
                            new ArrayList<>(Arrays.asList(0)),
                            false,
                            1000),
                    mode,
                    Paths.get(directory, String.format("null.%d.data", meta.nullableColIds.get(i))).toString()
            );
            nullTrees.set(i, tmp);
        }

        // resume indices trees
        for(int i = 0; i < indexTrees.size(); ++i)
        {
            ArrayList<Integer> colId = meta.indices.get(i);
            BPlusTree tmp = new BPlusTree(
                    new BPlusConfiguration(
                            1024,
                            8,
                            colId.stream().map((x -> meta.coltypes.get(x))).collect(Collectors.toCollection(ArrayList::new)),
                            colId.stream().map((x -> meta.colsizes.get(x))).collect(Collectors.toCollection(ArrayList::new)),
                            colId,
                            false,
                            1000),
                    mode,
                    Paths.get(directory, String.format("index.%d.data", i)).toString()
            );
            indexTrees.set(i, tmp);
        }

        // resume super key trees
        for(int i = 0; i < superKeyTrees.size(); ++i)
        {
            ArrayList<Integer> colId = meta.superKeys.get(i);
            BPlusTree tmp = new BPlusTree(
                    new BPlusConfiguration(
                            1024,
                            8,
                            colId.stream().map((x -> meta.coltypes.get(x))).collect(Collectors.toCollection(ArrayList::new)),
                            colId.stream().map((x -> meta.colsizes.get(x))).collect(Collectors.toCollection(ArrayList::new)),
                            colId,
                            true,
                            1000),
                    mode,
                    Paths.get(directory, String.format("key.%d.data", i)).toString()
            );
            superKeyTrees.set(i, tmp);
        }
    }

    // insert one row, cols are in the same order as the definition (the order in meta.colnames).
    public void insert(ArrayList<Object> row) throws MiniDBException, IOException {
        long rowID = meta.nextRowID++;
        // check length
        if(row.size() != meta.ncols)
            throw new MiniDBException(String.format("%d values required but %d values are given.", meta.ncols, row.size()));

        ArrayList<Integer> indeedNullCols = new ArrayList<>();
        for(int i=0; i < meta.ncols; ++i)
        {
            if(!meta.nullableColIds.contains(i) && row.get(i) == null)
            {// check nullable
                throw new MiniDBException(String.format("column (%s) cannot be null!", meta.colnames.get(i)));
            }
            if(row.get(i) != null)
            {
                // check type
                if(meta.coltypes.get(i) != row.get(i).getClass())
                {
                    throw new MiniDBException(
                            String.format("Non-compatible type. Given: %s, Required: %s!",
                                    row.get(i).getClass().getTypeName(),
                                    meta.coltypes.get(i).getTypeName()));
                }
                // check string length
                if(meta.coltypes.get(i) == String.class)
                {
                    int length = ((String) row.get(i)).getBytes(StandardCharsets.UTF_8).length;
                    if(length > meta.colsizes.get(i))
                    {
                        throw new MiniDBException(
                                String.format(
                                        "column (%s) length exceeds! The value is (%s) with a length of %d (in bytes), but the limit is %d.",
                                        meta.colnames.get(i),
                                        (String) row.get(i), length, meta.colsizes.get(i)));
                    }
                }
            }else
            {// null columns
                indeedNullCols.add(i);
                if(meta.coltypes.get(i) == String.class)
                {
                    row.set(i, "");
                }else if(meta.coltypes.get(i) == Integer.class)
                {
                    row.set(i, new Integer(0));
                }else if(meta.coltypes.get(i) == Long.class)
                {
                    row.set(i, new Long(0));
                }else if(meta.coltypes.get(i) == Double.class)
                {
                    row.set(i, new Double(0));
                }else if(meta.coltypes.get(i) == Float.class)
                {
                    row.set(i, new Float(0));
                }

            }
        }

        // check unique constrains
        for(BPlusTree tree : superKeyTrees)
        {
            ArrayList<Object> thisRow = tree.conf.colIDs.stream().map(x -> row.get(x)).collect(Collectors.toCollection(ArrayList::new));
            if(tree.search(thisRow).size() > 0)
            {// duplicate keys
                throw new MiniDBException(String.format("Value (%s) already exists!", tree.conf.keyToString(thisRow)));

            }
        }

        // now it is time to insert!
        data.insertRow(row, rowID);
        for(BPlusTree tree : superKeyTrees)
        {
            ArrayList<Object> thisRow = tree.conf.colIDs.stream().map(x -> row.get(x)).collect(Collectors.toCollection(ArrayList::new));
            tree.insertPair(thisRow, rowID);
        }
        for(BPlusTree tree : indexTrees)
        {
            ArrayList<Object> thisRow = tree.conf.colIDs.stream().map(x -> row.get(x)).collect(Collectors.toCollection(ArrayList::new));
            tree.insertPair(thisRow, rowID);
        }

        // record null columns
        for(int i=0; i < nullTrees.size(); ++i)
        {
            if(indeedNullCols.contains(i))
            {
                nullTrees.get(i).insertPair(new ArrayList<Object>(Arrays.asList(rowID)), -1L);
            }
        }
    }

    public void delete(long rowID) throws IOException, MiniDBException {
        ArrayList<Object> row = data.readRow(rowID);
        data.deleteRow(rowID);

        for(BPlusTree tree : superKeyTrees)
        {
            ArrayList<Object> thisRow = tree.conf.colIDs.stream().map(x -> row.get(x)).collect(Collectors.toCollection(ArrayList::new));
            tree.deletePair(thisRow, rowID);
        }
        for(BPlusTree tree : indexTrees)
        {
            ArrayList<Object> thisRow = tree.conf.colIDs.stream().map(x -> row.get(x)).collect(Collectors.toCollection(ArrayList::new));
            tree.deletePair(thisRow, rowID);
        }
        for(BPlusTree tree : nullTrees)
        {
            tree.deletePair(new ArrayList<Object>(Arrays.asList(rowID)), -1L);
        }
    }

    public void delete(Collection<Long> rowIDs) throws IOException, MiniDBException {
        for(Long rowID : rowIDs)
        {
            delete(rowID);
        }
    }

    public LinkedList<MainDataFile.SearchResult> readRows(Collection<Long> rowIDs)  throws IOException, MiniDBException
    {
        LinkedList<MainDataFile.SearchResult> ans = new LinkedList<MainDataFile.SearchResult>();
        for(Long rowID : rowIDs)
        {
            MainDataFile.SearchResult result = new MainDataFile.SearchResult(data.readRow(rowID), rowID);
            for(BPlusTree nullTree : nullTrees)
            {
                if(nullTree.search(new ArrayList<Object>(Arrays.asList(result.rowID))).size() != 0)
                {
                    result.key.set(nullTree.conf.colIDs.get(0), null);
                }
            }
            ans.add(result);
        }
        return ans;
    }

    // linear scan
    // process each row to restore the null value
    public LinkedList<MainDataFile.SearchResult> searchRows(Function<MainDataFile.SearchResult, Boolean> pred){
        Function<MainDataFile.SearchResult, Boolean> predpred = result -> {
            try{
                for(BPlusTree nullTree : nullTrees)
                {
                    if(nullTree.search(new ArrayList<Object>(Arrays.asList(result.rowID))).size() != 0)
                    {
                        result.key.set(nullTree.conf.colIDs.get(0), null);
                    }
                }
                return pred.apply(result);
            }catch (Exception e){
                throw new ParseCancellationException("Error!");
            }
        };
        try {
            return data.searchRows(predpred);
        }catch (Exception e){
            throw new ParseCancellationException("Error!");
        }
    }
}
