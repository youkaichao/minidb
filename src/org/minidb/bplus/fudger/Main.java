package org.minidb.bplus.fudger;

import org.minidb.bplus.bptree.BPlusConfiguration;
import org.minidb.bplus.bptree.BPlusTree;
import org.minidb.bplus.bptree.SearchResult;
import org.minidb.bplus.util.DuplicateValuesException;
import org.minidb.bplus.util.InvalidBTreeStateException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args)
            throws IOException, InvalidBTreeStateException, DuplicateValuesException {
        boolean recreateTree = true;
        BPlusConfiguration btconf = new BPlusConfiguration();
        BPlusTree bt = new BPlusTree(btconf, recreateTree ? "rw+" : "rw", "file.data", new Type[]{int.class});
        bt.insertKey(1, 12L);
        SearchResult ans = bt.searchKey(1, false);
        LinkedList<Long> values = ans.getValues();
        Iterator<Long> it = values.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
        // finally close it.
        bt.commitTree();
    }

}
