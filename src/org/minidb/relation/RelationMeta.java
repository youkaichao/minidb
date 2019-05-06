package org.minidb.relation;

import org.minidb.exception.MiniDBException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class RelationMeta implements Serializable {
    long nextRowID; // the next available row ID
    int ncols; // number of columns
    ArrayList<String> colnames; // column names
    ArrayList<Type> coltypes; // column types
    ArrayList<Integer> colsizes; // column sizes. (esp. for variable length string)
    ArrayList<Integer> nullableColIds; // nullable columns. other columns are non-nullable
    // super key is just the abstraction of uniqueness (on one or multiple columns).
    // colIDs for super keys (primary key is just a normal super key)
    ArrayList<ArrayList<Integer>> superKeys;
    // index is just the abstraction of non-uniqueness.
    // colIDs for indices
    ArrayList<ArrayList<Integer>> indices;

    // validate the meta configuration
    public boolean validate() throws MiniDBException
    {
        if(colnames == null) return false;
        if(coltypes == null) return false;
        if(colsizes == null) return false;
        if(nullableColIds == null) return false;
        if(superKeys == null) return false;
        if(indices == null) return false;
        if(ncols <= 0) return false;
        if(ncols != colnames.size()) return false;
        if(ncols != coltypes.size()) return false;
        if(ncols != colsizes.size()) return false;
        // check unsupported types
        for(Type each : coltypes)
        {
            if(each != Integer.class && each != Long.class && each != Float.class && each != Double.class && each != String.class)
            {
                throw new MiniDBException(String.format(MiniDBException.UnknownColumnType, each.getTypeName()));
            }
        }
        // check malformed sizes
        for(int i = 0; i < ncols; ++i)
        {
            if(coltypes.get(i) == Integer.class)
            {
                if(colsizes.get(i) != 4) return false;
            }else if(coltypes.get(i) == Long.class)
            {
                if(colsizes.get(i) != 8) return false;
            }else if(coltypes.get(i) == Float.class)
            {
                if(colsizes.get(i) != 4) return false;
            }else if(coltypes.get(i) == Double.class)
            {
                if(colsizes.get(i) != 8) return false;
            }else if(coltypes.get(i) == String.class)
            {
                if(colsizes.get(i) <= 0) return false;
            }
        }
        for(int each: nullableColIds)
        {
            if(!(each >= 0 && each < ncols)) return false;
        }
        for(ArrayList<Integer> each : superKeys)
        {
            for(Integer each_each : each)
            {
                if(!(each_each >= 0 && each_each < ncols)) return false;
            }
        }
        for(ArrayList<Integer> each : indices)
        {
            for(Integer each_each : each)
            {
                if(!(each_each >= 0 && each_each < ncols)) return false;
            }
        }

        // columns concerned with candiate keys and indices are not nullable
        HashSet<Integer> nonNullableCols = new HashSet<>();
        for(ArrayList<Integer> each : superKeys)
        {
            nonNullableCols.addAll(each);
        }
        for(ArrayList<Integer> each : indices)
        {
            nonNullableCols.addAll(each);
        }
        HashSet<Integer> nullableCols = new HashSet<Integer>(nullableColIds);
        nonNullableCols.retainAll(nullableCols);
        if(nonNullableCols.size() != 0)
        {
            StringBuilder msg = new StringBuilder();
            msg.append("[");
            for(Integer each : nonNullableCols)
            {
                msg.append(colnames.get(each));
                msg.append(' ');
            }
            msg.append("]");
            throw new MiniDBException(String.format("Conflict nullability requirement for %s", msg.toString()));
        }
        return true;
    }

    public static RelationMeta read(String filepath) throws IOException, ClassNotFoundException {
        return (RelationMeta) new ObjectInputStream(new FileInputStream(filepath)).readObject();
    }

    public void write(String filepath) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filepath)).writeObject(this);
    }
}
