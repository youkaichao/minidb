package org.minidb.bplus.test;

import org.minidb.bplus.bptree.BPlusConfiguration;
import org.minidb.bplus.bptree.BPlusTree;
import org.minidb.bplus.bptree.SearchResult;
import org.minidb.bplus.util.DuplicateValuesException;
import org.minidb.bplus.util.InvalidBTreeStateException;
import org.minidb.bplus.util.UnknownColumnTypeException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
        HashSet<Long> expectedValues = new HashSet<Long>();
        expectedValues.addAll(Arrays.asList(expected));
        if(!expectedValues.equals(findValues))
        {
            throw new Exception("wrong!");
        }
    }

    @Test
    public void testInsert()
            throws Exception, IOException, InvalidBTreeStateException, DuplicateValuesException, UnknownColumnTypeException {
        boolean recreateTree = true;
        Type[] types = new Type[]{Integer.class, Double.class, String.class};
        int[] sizes = new int[]{4, 8, 10};
        String[] colNames = new String[]{"a", "b", "c"};
        BPlusConfiguration btconf = new BPlusConfiguration(256, 8, types, sizes, colNames, 1000);
        BPlusTree bt = new BPlusTree(btconf, recreateTree ? "rw+" : "rw", "file.data");
        bt.insertKey(new Object[]{100, 200.0, "1234567890"}, 12L);
        bt.insertKey(new Object[]{100, 200.0, "1234567890"}, 0L);
        bt.insertKey(new Object[]{100, 200.0, "1234567890"}, 254L);
        bt.insertKey(new Object[]{100, 200.0, "1234567892"}, 13L);
        bt.insertKey(new Object[]{100, 200.0, "0234567892"}, 15L);
        bt.insertKey(new Object[]{100, 200.0, "0244567892"}, 255L);
        assertEqual(bt, new Object[]{100, 200.0, "0244567892"}, new Long[]{255L});
        assertEqual(bt, new Object[]{100, 200.0, "0234567892"}, new Long[]{15L});
        assertEqual(bt, new Object[]{100, 200.0, "1234567890"}, new Long[]{12L, 0L, 254L});
        assertEqual(bt, new Object[]{100, 200.0, "1234567800"}, new Long[]{});
//        bt.commitTree();
    }
}
