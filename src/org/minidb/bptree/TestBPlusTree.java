package org.minidb.bptree;

import org.junit.Assert;
import org.minidb.exception.MiniDBException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class TestBPlusTree {

    private static BPlusTree commonTree(boolean unique, boolean create) throws Exception, IOException, MiniDBException
    {
        boolean recreateTree = create;
        ArrayList<Type> types = new ArrayList<>(Arrays.asList(Integer.class, Double.class, String.class));
        ArrayList<Integer> sizes = new ArrayList<>(Arrays.asList(4, 8, 10));
        ArrayList<Integer> colIDs = new ArrayList<>(Arrays.asList(0, 1, 2));
        BPlusConfiguration btconf = new BPlusConfiguration(256, 8, types, sizes, colIDs, unique,1000);
        BPlusTree bt = new BPlusTree(btconf, recreateTree ? "rw+" : "rw", "file.data");
        return bt;
    }

    private static BPlusTree createCommonTree(boolean unique) throws Exception, IOException, MiniDBException
    {
        return commonTree(unique, true);
    }

    private static BPlusTree resumeCommonTree(boolean unique) throws Exception, IOException, MiniDBException
    {
        return commonTree(unique, false);
    }

    private static void assertSearchEqual(BPlusTree bt, ArrayList<Object> key, Long[] expected) throws Exception
    {
        LinkedList<Long> values = bt.search(key);
        HashSet<Long> findValues = new HashSet<Long>(values);
        HashSet<Long> expectedValues = new HashSet<>(Arrays.asList(expected));
        if(!expectedValues.equals(findValues))
        {
            throw new Exception("wrong!");
        }
    }

    private static void assertSetEqual(LinkedList<Long> values, Long[] expected)
    {
        HashSet<Long> findValues = new HashSet<Long>(values);
        HashSet<Long> expectedValues = new HashSet<>(Arrays.asList(expected));
        Assert.assertTrue(findValues.equals(expectedValues));
    }

    @Test
    public void testDuplicateInsert()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 13L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 15L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 255L);
        try{
            bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, new String(new char[120]))), 255L);
            throw new Exception("wrong!");
        }catch (MiniDBException e)
        {
        }
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "9")), new Long[]{255L, 254L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "120")), new Long[]{0L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "12")), new Long[]{12L, 13L, 15L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "xs")), new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testUniqueInsert()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(true);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        try{
            bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 13L);
            throw new Exception("wrong!");
        }catch (MiniDBException e)
        {
        }
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "9")), new Long[]{254L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "120")), new Long[]{0L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "12")), new Long[]{12L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "xs")), new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testDuplicateDelete()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 13L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 15L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 255L);
        // delete an existing pair
        Assert.assertTrue(bt.deletePair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 255L));
        Assert.assertFalse(bt.deletePair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 255L));
        // delete a pair that does not exist
        Assert.assertFalse(bt.deletePair(new ArrayList<>(Arrays.asList(100, 200.0, "xs")), 255L));
        // check these searches are correct
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "9")), new Long[]{254L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "120")), new Long[]{0L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "12")), new Long[]{12L, 13L, 15L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "xs")), new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testUniqueDelete()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(true);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        // delete an existing pair
        Assert.assertTrue(bt.deletePair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), -1L));
        Assert.assertFalse(bt.deletePair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), -1L));
        // delete a pair that does not exist
        Assert.assertFalse(bt.deletePair(new ArrayList<>(Arrays.asList(100, 200.0, "xs")), -1L));
        // check these searches are correct
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "9")), new Long[]{});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "120")), new Long[]{0L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "12")), new Long[]{12L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "xs")), new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testDuplicateUpdate()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 13L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 15L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 255L);
        // update an existing pair to a different value
        Assert.assertTrue(bt.updatePair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L, 99L));
        // update an existing pair to the same value
        Assert.assertTrue(bt.updatePair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 99L, 99L));
        // update a pair that does not exist
        Assert.assertFalse(bt.updatePair(new ArrayList<>(Arrays.asList(100, 200.0, "xs")), 255L, -1L));
        // check these searches are correct
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "9")), new Long[]{254L, 255L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "120")), new Long[]{0L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "12")), new Long[]{99L, 13L, 15L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "xs")), new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testUniqueUpdate()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(true);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        // update an existing pair to a different value
        Assert.assertTrue(bt.updatePair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), -1L, 99L));
        // update an existing pair to the same value
        Assert.assertTrue(bt.updatePair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), -1L, 99L));
        // update a pair that does not exist
        Assert.assertFalse(bt.updatePair(new ArrayList<>(Arrays.asList(100, 200.0, "xs")), 255L, -1L));

        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "9")), new Long[]{254L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "120")), new Long[]{0L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "12")), new Long[]{99L});
        assertSearchEqual(bt, new ArrayList<>(Arrays.asList(100, 200.0, "xs")), new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testDuplicateRangeSearch()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 13L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12 ")), 15L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 255L);
        // equivalent class for range search. [|( a, b )|], where a = b, a < b, a > b
        // test case for a = b
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), true, true),
                new Long[]{12L, 13L, 15L});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), false, true),
                new Long[]{});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), true, false),
                new Long[]{});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), false, false),
                new Long[]{});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "13")), new ArrayList<>(Arrays.asList(100, 200.0, "13")), true, true),
                new Long[]{});
        // test case for a > b
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "13")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), true, true),
                new Long[]{});
        // test case for a < b
            // find all
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "0")), new ArrayList<>(Arrays.asList(100, 200.0, "999")), true, true),
                new Long[]{0L, 12L, 13L, 15L, 254L, 255L});
            // find one
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "8")), new ArrayList<>(Arrays.asList(100, 200.0, "92")), true, true),
                new Long[]{254L, 255L});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "8")), new ArrayList<>(Arrays.asList(100, 200.0, "9")), false, true),
                new Long[]{254L, 255L});
            // find none
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "55")), new ArrayList<>(Arrays.asList(100, 200.0, "66")), true, true),
                new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testUniqueRangeSearch()
            throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(true);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "12")), 12L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "120")), 0L);
        bt.insertPair(new ArrayList<>(Arrays.asList(100, 200.0, "9")), 254L);
        // equivalent class for range search. [|( a, b )|], where a = b, a < b, a > b
        // test case for a = b
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), true, true),
                new Long[]{12L});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), false, true),
                new Long[]{});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), true, false),
                new Long[]{});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "12")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), false, false),
                new Long[]{});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "13")), new ArrayList<>(Arrays.asList(100, 200.0, "13")), true, true),
                new Long[]{});
        // test case for a > b
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "13")), new ArrayList<>(Arrays.asList(100, 200.0, "12")), true, true),
                new Long[]{});
        // test case for a < b
        // find all
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "0")), new ArrayList<>(Arrays.asList(100, 200.0, "999")), true, true),
                new Long[]{0L, 12L, 254L});
        // find one
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "8")), new ArrayList<>(Arrays.asList(100, 200.0, "92")), true, true),
                new Long[]{254L});
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "8")), new ArrayList<>(Arrays.asList(100, 200.0, "9")), false, true),
                new Long[]{254L});
        // find none
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(100, 200.0, "55")), new ArrayList<>(Arrays.asList(100, 200.0, "66")), true, true),
                new Long[]{});
//        bt.commitTree();
    }

    @Test
    public void testMassiveUniqueInsert()throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(true);
        for(int i = 0; i < 1000; ++ i)
        {
            bt.insertPair(new ArrayList<>(Arrays.asList(i, 200.0, "12")), i);
        }
        int start = 20, end = 50;
        Long[] ans = new Long[end - start];
        for (int i = start; i < end; ++i)
        {
            ans[i - start] = new Long(i);
        }
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(start, 200.0, "12")), new ArrayList<>(Arrays.asList(end, 200.0, "12")), true, false),
                ans);
    }

    @Test
    public void testMassiveDuplicateInsert()throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        for(int i = 0; i < 1000; ++ i)
        {
            bt.insertPair(new ArrayList<>(Arrays.asList(i, 200.0, "12")), i);
            bt.insertPair(new ArrayList<>(Arrays.asList(i, 200.0, "12")), i+1);
        }
        int start = 20, end = 50;
        Long[] ans = new Long[(end - start) * 2];
        for (int i = start; i < end; ++i)
        {
            ans[(i - start) * 2] = new Long(i);
            ans[(i - start) * 2 + 1] = new Long(i + 1);
        }
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(start, 200.0, "12")), new ArrayList<>(Arrays.asList(end, 200.0, "12")), true, false),
                ans);
    }

    @Test
    public void testExtremeDuplicateInsert()throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        for(int i = 0; i < 100; ++ i)
        {
            bt.insertPair(new ArrayList<>(Arrays.asList(0, 200.0, "12")), i);
        }
        int start = 0, end = 100;
        Long[] ans = new Long[(end - start)];
        for (int i = start; i < end; ++i)
        {
            ans[i] = new Long(i);
        }
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(start, 200.0, "12")), new ArrayList<>(Arrays.asList(end, 200.0, "12")), true, true),
                ans);
    }

    @Test
    public void testMassiveDuplicateResume()throws Exception, IOException, MiniDBException{
        BPlusTree bt = createCommonTree(false);
        for(int i = 0; i < 1000; ++ i)
        {
            bt.insertPair(new ArrayList<>(Arrays.asList(i, 200.0, "12")), i);
            bt.insertPair(new ArrayList<>(Arrays.asList(i, 200.0, "12")), i+1);
        }
        bt.commitTree();
        bt = resumeCommonTree(false);
        int start = 20, end = 50;
        Long[] ans = new Long[(end - start) * 2];
        for (int i = start; i < end; ++i)
        {
            ans[(i - start) * 2] = new Long(i);
            ans[(i - start) * 2 + 1] = new Long(i + 1);
        }
        assertSetEqual(
                bt.rangeSearch(new ArrayList<>(Arrays.asList(start, 200.0, "12")), new ArrayList<>(Arrays.asList(end, 200.0, "12")), true, false),
                ans);
    }
}
