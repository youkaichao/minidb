package org.minidb.relation;


import org.minidb.bptree.BPlusConfiguration;
import org.minidb.bptree.BPlusTree;
import org.minidb.exception.MiniDBException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Relation {
    RelationMeta meta;
    String directory; // the directory to store the relation data
    BPlusTree data; // main tree for the data
    ArrayList<BPlusTree> candidateKeyTrees, indexTrees, nullTrees;

    // create the relation, save the meta data and create data trees
    public void create() throws IOException, MiniDBException {
        meta.nextRowID = 0L;
        meta.validate();
        meta.write(Paths.get(directory, "meta").toString());
        createOrResumeData(true);
    }

    // close the relation, save meta data and commit trees.
    public void close() throws IOException, MiniDBException {
        meta.write(Paths.get(directory, "meta").toString());

        data.commitTree();

        for(BPlusTree each : candidateKeyTrees)
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
        data = new BPlusTree(
                new BPlusConfiguration(1024, 8, meta.coltypes, meta.colsizes, colIDs, false,1000),
                mode,
                Paths.get(directory, "data").toString()
        );

        candidateKeyTrees = new ArrayList<>(Arrays.asList(new BPlusTree[meta.candidateKeys.size()]));
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

        // resume candidate key trees
        for(int i = 0; i < candidateKeyTrees.size(); ++i)
        {
            ArrayList<Integer> colId = meta.candidateKeys.get(i);
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
            candidateKeyTrees.set(i, tmp);
        }
    }

}
