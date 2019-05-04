package org.minidb.bplus.test;

import org.minidb.bplus.bptree.BPlusConfiguration;
import org.minidb.bplus.bptree.BPlusTree;
import org.minidb.bplus.bptree.SearchResult;
import org.minidb.exception.MiniDBException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class TestBPlusTree {

    private static void assertEqual (BPlusTree bt, Object[] key, Long[] expected) throws Exception
    {
        SearchResult ans = bt.searchKey(key, false);
        LinkedList<Long> values = ans.getValues();
        HashSet<Long> findValues = new HashSet<Long>();
        if(values != null)
        {
            findValues.addAll(values);
        }else{
            if(expected.length != 0)
            {
                throw new Exception("wrong!");
            }
        }
        HashSet<Long> expectedValues = new HashSet<>(Arrays.asList(expected));
        if(!expectedValues.equals(findValues))
        {
            throw new Exception("wrong!");
        }
    }

    @Test
    public void testInsert()
            throws Exception, IOException, MiniDBException{
        boolean recreateTree = true;
        Type[] types = new Type[]{Integer.class, Double.class, String.class};
        int[] sizes = new int[]{4, 8, 10};
        String[] colNames = new String[]{"a", "b", "c"};
        BPlusConfiguration btconf = new BPlusConfiguration(256, 8, types, sizes, colNames, 1000);
        BPlusTree bt = new BPlusTree(btconf, recreateTree ? "rw+" : "rw", "file.data");
        bt.insertPair(new Object[]{100, 200.0, "12"}, 12L);
        bt.insertPair(new Object[]{100, 200.0, "120"}, 0L);
        bt.insertPair(new Object[]{100, 200.0, "9"}, 254L);
        bt.insertPair(new Object[]{100, 200.0, "12 "}, 13L);
        bt.insertPair(new Object[]{100, 200.0, "12 "}, 15L);
        bt.insertPair(new Object[]{100, 200.0, "9"}, 255L);
        try{
            bt.insertPair(new Object[]{100, 200.0, new String(new char[120])}, 255L);
            throw new Exception("wrong!");
        }catch (MiniDBException e)
        {
        }
        assertEqual(bt, new Object[]{100, 200.0, "9"}, new Long[]{255L, 254L});
        assertEqual(bt, new Object[]{100, 200.0, "120"}, new Long[]{0L});
        assertEqual(bt, new Object[]{100, 200.0, "12"}, new Long[]{12L, 13L, 15L});
        assertEqual(bt, new Object[]{100, 200.0, "xs"}, new Long[]{});
//        bt.commitTree();
    }
}
