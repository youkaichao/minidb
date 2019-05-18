package org.minidb.relation;
import org.junit.Test;
import org.minidb.exception.MiniDBException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
        meta.superKeys = new ArrayList<ArrayList<Integer>>();
        meta.superKeys.add(new ArrayList<Integer>(Arrays.asList(0)));
        meta.indices = new ArrayList<ArrayList<Integer>>();
        meta.indices.add(new ArrayList<Integer>(Arrays.asList(1)));

        Relation relation = new Relation();
        relation.directory = "data/db/MyTable";
        new File(relation.directory).mkdirs();
        relation.meta = meta;
        relation.create();
        for (int i = 0; i < 100; i++) {
            relation.insert(new ArrayList<Object>(Arrays.asList(i, new Double(i), "you")));
        }
        for (int i = 0; i < 90; i++) {
            relation.delete(i);
        }
        relation.close();
        relation.resume();
        System.out.println(relation.data.getElementCount());
    }


    @Test
    public void test1()
    {
        byte[] buffer = new byte[256];
        ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.nativeOrder());
        bbuffer.putLong(1);
        bbuffer.putLong(2);
        bbuffer.position(0);
        System.out.println(bbuffer.getLong());
        System.out.println(bbuffer.getLong());
        // ;
        System.out.println(bbuffer.position());
    }
}
