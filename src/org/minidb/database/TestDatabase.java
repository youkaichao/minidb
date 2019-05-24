package org.minidb.database;
import org.junit.Test;
import org.minidb.exception.MiniDBException;
import org.minidb.relation.Relation;
import org.minidb.relation.RelationMeta;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TestDatabase {

    @Test
    public void testCreateDatabase() throws IOException, ClassNotFoundException, MiniDBException {
        Database database = new Database();
        database.directory = "data/first";
        new File(database.directory).mkdirs();

        RelationMeta meta = new RelationMeta();
        meta.ncols = 3;
        meta.colnames = new ArrayList<>(Arrays.asList("a", "b", "c"));
        meta.coltypes = new ArrayList<>(Arrays.asList(Integer.class, Double.class, String.class));
        meta.colsizes = new ArrayList<>(Arrays.asList(4, 8, 5));
        meta.nullableColIds = new ArrayList<>(Arrays.asList(2));
        meta.superKeys = new ArrayList<ArrayList<Integer>>();
        meta.superKeys.add(new ArrayList<Integer>(Arrays.asList(0)));
        meta.indices = new ArrayList<ArrayList<Integer>>();
        meta.indices.add(new ArrayList<Integer>(Arrays.asList(1)));

        Relation relation = new Relation();
        relation.meta = meta;

        database.addRelation("what", relation);
        database.dropRelation("what");

        database.addRelation("what", relation);
        relation.insert(new ArrayList<Object>(Arrays.asList(16, 0.0, "you")));
        database.close();

        database.resume();
    }
}
