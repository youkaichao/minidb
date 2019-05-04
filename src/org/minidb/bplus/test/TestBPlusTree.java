package org.minidb.bplus.test;

import org.junit.Assert;
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
        BPlusConfiguration btconf = new BPlusConfiguration(256, 8, types, sizes, colNames, false,1000);
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

    @Test
    public void testDelete()
            throws Exception, IOException, MiniDBException{
        boolean recreateTree = true;
        Type[] types = new Type[]{Integer.class, Double.class, String.class};
        int[] sizes = new int[]{4, 8, 10};
        String[] colNames = new String[]{"a", "b", "c"};
        BPlusConfiguration btconf = new BPlusConfiguration(256, 8, types, sizes, colNames, false,1000);
        BPlusTree bt = new BPlusTree(btconf, recreateTree ? "rw+" : "rw", "file.data");
        bt.insertPair(new Object[]{100, 200.0, "12"}, 12L);
        bt.insertPair(new Object[]{100, 200.0, "120"}, 0L);
        bt.insertPair(new Object[]{100, 200.0, "9"}, 254L);
        bt.insertPair(new Object[]{100, 200.0, "12 "}, 13L);
        bt.insertPair(new Object[]{100, 200.0, "12 "}, 15L);
        bt.insertPair(new Object[]{100, 200.0, "9"}, 255L);
        // delete an existing pair
        Assert.assertTrue(bt.deletePair(new Object[]{100, 200.0, "9"}, 255L));
        Assert.assertFalse(bt.deletePair(new Object[]{100, 200.0, "9"}, 255L));
        // delete a pair that does not exist
        Assert.assertFalse(bt.deletePair(new Object[]{100, 200.0, "xs"}, 255L));
        // check these searches are correct
        assertEqual(bt, new Object[]{100, 200.0, "9"}, new Long[]{254L});
        assertEqual(bt, new Object[]{100, 200.0, "120"}, new Long[]{0L});
        assertEqual(bt, new Object[]{100, 200.0, "12"}, new Long[]{12L, 13L, 15L});
        assertEqual(bt, new Object[]{100, 200.0, "xs"}, new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testUpdate()
            throws Exception, IOException, MiniDBException{
        boolean recreateTree = true;
        Type[] types = new Type[]{Integer.class, Double.class, String.class};
        int[] sizes = new int[]{4, 8, 10};
        String[] colNames = new String[]{"a", "b", "c"};
        BPlusConfiguration btconf = new BPlusConfiguration(256, 8, types, sizes, colNames, false,1000);
        BPlusTree bt = new BPlusTree(btconf, recreateTree ? "rw+" : "rw", "file.data");
        bt.insertPair(new Object[]{100, 200.0, "12"}, 12L);
        bt.insertPair(new Object[]{100, 200.0, "120"}, 0L);
        bt.insertPair(new Object[]{100, 200.0, "9"}, 254L);
        bt.insertPair(new Object[]{100, 200.0, "12 "}, 13L);
        bt.insertPair(new Object[]{100, 200.0, "12 "}, 15L);
        bt.insertPair(new Object[]{100, 200.0, "9"}, 255L);
        // update an existing pair to a different value
        Assert.assertTrue(bt.updatePair(new Object[]{100, 200.0, "12"}, 12L, 99L));
        // update an existing pair to the same value
        Assert.assertTrue(bt.updatePair(new Object[]{100, 200.0, "12"}, 99L, 99L));
        // delete a pair that does not exist
        Assert.assertFalse(bt.updatePair(new Object[]{100, 200.0, "xs"}, 255L, -1L));
        // check these searches are correct
        assertEqual(bt, new Object[]{100, 200.0, "9"}, new Long[]{254L, 255L});
        assertEqual(bt, new Object[]{100, 200.0, "120"}, new Long[]{0L});
        assertEqual(bt, new Object[]{100, 200.0, "12"}, new Long[]{99L, 13L, 15L});
        assertEqual(bt, new Object[]{100, 200.0, "xs"}, new Long[]{});
//        bt.commitTree();
    }
}
