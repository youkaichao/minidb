package org.minidb.relation;
import org.junit.Assert;
import org.junit.Test;
import org.minidb.exception.MiniDBException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class TestRelation {

    @Test
    public void testCreateRelation() throws IOException, ClassNotFoundException, MiniDBException {
        RelationMeta meta = new RelationMeta();
        meta.ncols = 3;
        meta.colnames = new ArrayList<>(Arrays.asList("a", "b", "c"));
        meta.coltypes = new ArrayList<>(Arrays.asList(Integer.class, Double.class, String.class));
        meta.colsizes = new ArrayList<>(Arrays.asList(4, 8, 5));
        meta.nullableColIds = new ArrayList<>(Arrays.asList(2));
        meta.candidateKeys = new ArrayList<ArrayList<Integer>>();
        meta.candidateKeys.add(new ArrayList<Integer>(Arrays.asList(0)));
        meta.indices = new ArrayList<ArrayList<Integer>>();
        meta.indices.add(new ArrayList<Integer>(Arrays.asList(1)));

        Relation relation = new Relation();
        relation.directory = "data/db/MyTable";
        new File(relation.directory).mkdirs();
        relation.meta = meta;
        relation.create();
        relation.insert(new ArrayList<Object>(Arrays.asList(16, 0.0, "you")));
        relation.close();
        relation.resume();
    }
}
