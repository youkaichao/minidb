package org.minidb.bplus.bptree;

import javafx.util.Pair;
import org.minidb.exception.MiniDBException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * On-disk BPlus Tree implementation.
 * values are unique. keys are unique. But one key can have multiple corresponding values,
 * depending on the `unique` flag in the `BPlusConfiguration` parameter.
 * The tree is used to represent a mapping from index to rowID.
 * */
@SuppressWarnings("WeakerAccess")
public class BPlusTree {

    private TreeNode root;
    private TreeNode aChild;
    private RandomAccessFile treeFile;
    private BPlusConfiguration conf;
    private LinkedList<Long> freeSlotPool;
    private LinkedList<Long> lookupPagesPool;
    private long firstPoolNextPointer;
    private long totalTreePages;
    private long maxPageNumber;
    private int deleteIterations;

    /**
     * @param conf B+ Tree configuration instance
     * @param mode I/O mode
     * @param treeFilePath file path for the file
     * @throws IOException is thrown when we fail to open/create the binary tree file
     */
    @SuppressWarnings("unused")
    public BPlusTree(BPlusConfiguration conf, String mode,
                     String treeFilePath)
            throws IOException, MiniDBException {
        this.conf = conf;
        initializeCommon();
        openFile(treeFilePath, mode, conf);
    }

    // Is this a unique index?
    public boolean isUnique()
    {
        return conf.unique;
    }

    /**
     * Insert the (key, value) pair into the tree
     * @param key key to add
     * @param value value of the key
     * @throws IOException is thrown when any of the read/write ops fail.
     * @throws IllegalStateException is thrown we have a null tree
     * @throws MiniDBException
     * */
    @SuppressWarnings("unused")
    public void insertPair(Object[] key, long value)
            throws IOException, MiniDBException,
            IllegalStateException {

        if(root == null)
            {throw new IllegalStateException("Can't insert to null tree");}

        padKey(key);

        // check if our root is full
        if(root.isFull(conf)) {
            // allocate a new *internal* node, to be placed as the
            // *left* child of the new root
            aChild = this.root;
            TreeInternalNode node_buf = new TreeInternalNode(TreeNodeType.TREE_ROOT_INTERNAL,
                    generateFirstAvailablePageIndex(conf));
            node_buf.addPointerAt(0, aChild.getPageIndex());
            this.root = node_buf;

            // split root.
            splitTreeNode(node_buf, 0);
            writeFileHeader(conf);
            insertNonFull(node_buf, key, value);
        }
        else
            {insertNonFull(root, key, value);}
    }

    /**
     * This function is inspired from the one given in CLRS for inserting a key to
     * a B-Tree but as splitTreeNode has been (heavily) modified in order to be used
     * in our B+ Tree. It supports handling duplicate keys (if enabled) as well.
     *
     * It is able to insert the (Key, Value) pairs using only one pass through the tree.
     *
     * @param n current node
     * @param key key to add
     * @param value value paired with the key
     * @throws IOException is thrown when an I/O operation fails
     */
    private void insertNonFull(TreeNode n, Object[] key, long value)
            throws IOException, MiniDBException {
        boolean useChild = true;
        int i = binSearchBlock(n, key, Rank.PlusOne);
        // check if we have a leaf
        if(n.isLeaf()) {
            TreeLeaf l = (TreeLeaf)n;

            // before we add it, let's check if the key already exists
            // and if it does pull up (or create) the overflow page and
            // add the value there.
            //
            // Not that we do *not* add the key if we have a true unique flag


            // this is to adjust for a corner case due to indexing
            int iadj = (n.getCurrentCapacity() > 0 &&
                    i == 0 && conf.gt(n.getFirstKey(), key)) ? i : i-1;
            if(n.getCurrentCapacity() > 0 && conf.eq(n.getKeyAt(iadj), key))
            {// duplicate value for the same key
                if(conf.unique) {
                    throw new MiniDBException(String.format(MiniDBException.DuplicateValue, Long.toString(value),
                            TreeNode.keyToString(key, conf)));
                }

                // overflow page does not exist, yet; time to create it!
                if(l.getOverflowPointerAt(iadj) < 0) {
                    createOverflowPage(l, iadj, value);
                }
                // page already exists, so pull it and check if it has
                // available space, if it does all is good; otherwise we
                // pull the next overflow page or we create another one.
                else {

                    TreeOverflow ovf =
                            (TreeOverflow) readNode(l.getOverflowPointerAt(iadj));

                    while(ovf.isFull(conf)) {
                        // check if we have more, if not create
                        if(ovf.getNextPagePointer() < 0)
                        // create page and return
                        {createOverflowPage(ovf, -1, value); return;}
                        // load the next page
                        else
                        {ovf = (TreeOverflow)readNode(ovf.getNextPagePointer());}
                    }

                    // if the loaded page is not full then add it.
                    ovf.pushToValueList(value);
                    ovf.incrementCapacity(conf);
                    ovf.writeNode(treeFile, conf);
                }
            }
            else {
                // we have a new key insert
                l.addToValueList(i, value);
                l.addToKeyArrayAt(i, key);
                // also create a NULL overflow pointer
                l.addToOverflowList(i, -1L);
                l.incrementCapacity(conf);
                // commit the changes
                l.writeNode(treeFile, conf);
            }

        } else {

            // This requires a bit of explanation; the above while loop
            // starts initially from the *end* key parsing the *right*
            // child, so initially it's like this:
            //
            //  Step 0:
            //
            //  Key Array:          | - | - | - | x |
            //  Pointer Array:      | - | - | - | - | x |
            //
            // Now if the while loop stops there, we have a *right* child
            // pointer, but should it continues we get the following:
            //
            // Step 1:
            //
            //  Key Array:          | - | - | x | - |
            //  Pointer Array:      | - | - | - | x | - |
            //
            //  and finally we reach the special case where we have the
            //  following:
            //
            // Final step:
            //
            //  Key Array:          | x | - | - | - |
            //  Pointer Array:      | x | - | - | - | - |
            //
            //
            // In this case we have a *left* pointer, which can be
            // quite confusing initially... hence the changed naming.
            //
            //

            TreeInternalNode inode = (TreeInternalNode)n;
            aChild = readNode(inode.getPointerAt(i));
            if (aChild.isOverflow() || aChild.isLookupPageOverflowNode()) {
                // "aChild can't be overflow node"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }
            TreeNode nextAfterAChild = null;
            if(aChild.isFull(conf)) {
                splitTreeNode(inode, i);
                if (conf.ge(key, n.getKeyAt(i))) {
                    useChild = false;
                    nextAfterAChild = readNode(inode.getPointerAt(i+1));
                }
            }

            insertNonFull(useChild ? aChild : nextAfterAChild, key, value);
        }
    }

    /**
     *
     * This function is based on the similar function prototype that
     * is given by CLRS for B-Tree but is altered (quite a bit) to
     * be able to be used for B+ Trees.
     *
     * The main difference is that when the split happens *all* keys
     * are preserved and the first key of the right node is moved up.
     *
     * For example say we have the following (order is assumed to be 3):
     *
     *          [ k1 k2 k3 k4 ]
     *
     * This split would result in the following:
     *
     *              [ k3 ]
     *              /   \
     *            /      \
     *          /         \
     *     [ k1 k2 ]   [ k3 k4 ]
     *
     * This function requires at least *3* page writes plus the commit of
     * the updated page count to the file header. In the case that after
     * splitting we have a new root we must commit the new root index
     * to the file header as well; this happens transparently inside
     * writeNode method and is not explicitly done here.
     *
     * @param n internal node "parenting" the split
     * @param index index in the node n that we need to add the median
     */
    private void splitTreeNode(TreeInternalNode n, int index)
            throws IOException, MiniDBException {

        int setIndex;
        TreeNode znode;
        Object[] keyToAdd;
        TreeNode ynode = aChild; // x.c_{i}
        if(ynode.isInternalNode()) {
            TreeInternalNode zInternal,
                             yInternal = (TreeInternalNode) ynode;

            zInternal = new TreeInternalNode(TreeNodeType.TREE_INTERNAL_NODE,
                    generateFirstAvailablePageIndex(conf));

            setIndex =  conf.treeDegree-1;

            int i;
            for(i = 0; i < setIndex; i++) {
                zInternal.addToKeyArrayAt(i, yInternal.popKey());
                zInternal.addPointerAt(i, yInternal.popPointer());
            }
            zInternal.addPointerAt(i, yInternal.popPointer());
            //keyToAdd = ynode.getFirstKey();
            keyToAdd = ynode.popKey();

            zInternal.setCurrentCapacity(setIndex);
            yInternal.setCurrentCapacity(setIndex);

            // it it was the root, invalidate it and make it a regular internal node
            if(yInternal.isRoot()) {
                yInternal.setNodeType(TreeNodeType.TREE_INTERNAL_NODE);
            }

            // update pointer at n_{index+1}
            n.addPointerAt(index, zInternal.getPageIndex());
            // update key value at n[index]
            n.addToKeyArrayAt(index, keyToAdd);
            // adjust capacity
            n.incrementCapacity(conf);
            // update shared child pointer.
            aChild = zInternal;
            // update reference
            znode = zInternal;
        }
        // we have a leaf
        else {
            TreeLeaf zLeaf,
                     yLeaf = (TreeLeaf) ynode,
                     afterLeaf;

            zLeaf = new TreeLeaf(yLeaf.getNextPagePointer(),
                    yLeaf.getPageIndex(), TreeNodeType.TREE_LEAF,
                    generateFirstAvailablePageIndex(conf));

            // update the previous pointer from the node after ynode
            if(yLeaf.getNextPagePointer() != -1) {
                afterLeaf = (TreeLeaf) readNode(yLeaf.getNextPagePointer());
                afterLeaf.setPrevPagePointer(zLeaf.getPageIndex());
                afterLeaf.writeNode(treeFile, conf);
            }

            // update pointers in ynode, only have to update next pointer
            yLeaf.setNextPagePointer(zLeaf.getPageIndex());

            setIndex = conf.leafNodeDegree-1;

            for(int i = 0; i < setIndex; i++) {
                //long fk = ynode.getLastKey();
                //long ovf1 = ((TreeLeaf)ynode).getLastOverflowPointer();
                zLeaf.pushToKeyArray(yLeaf.removeLastKey());
                zLeaf.pushToValueList(yLeaf.removeLastValue());
                zLeaf.pushToOverflowList(yLeaf.removeLastOverflowPointer());
                zLeaf.incrementCapacity(conf);
                yLeaf.decrementCapacity(conf);
            }

            // it it was the root, invalidate it and make it a regular leaf
            if(yLeaf.isRoot()) {
                yLeaf.setNodeType(TreeNodeType.TREE_LEAF);
            }

            // update pointer at n_{index+1}
            n.addPointerAt(index + 1, zLeaf.getPageIndex());
            // update key value at n[index]
            n.addToKeyArrayAt(index, zLeaf.getKeyAt(0));
            // adjust capacity
            n.incrementCapacity(conf);
            // update reference
            znode = zLeaf;
        }

        znode.setBeingDeleted(false);
        // commit the changes
        znode.writeNode(treeFile, conf);
        ynode.writeNode(treeFile, conf);
        n.writeNode(treeFile, conf);
        // commit page counts
        updatePageIndexCounts(conf);
    }

    /**
     * This function is responsible for handling the creation of overflow pages. We have
     * generally two distinct cases which are the following:
     *
     *  * Create an overflow page directly from a B+ TreeLeaf.
     *  * Add an overflow page directly after an existing one.
     *
     *  In both cases for convenience we update all the required metrics as well as
     *  push to the newly created page the required value.
     *
     * @param n node to add the page
     * @param index this is only used in the case of a leaf
     * @param value value to push in the new page
     * @throws IOException is thrown when an I/O operation fails
     */
    private void createOverflowPage(TreeNode n, int index, long value)
            throws IOException, MiniDBException {
        TreeOverflow novf;
        if(n.isOverflow()) {
            TreeOverflow ovf = (TreeOverflow)n;
            novf = new TreeOverflow(-1L, ovf.getPageIndex(),
                    generateFirstAvailablePageIndex(conf));
            // push the first value
            novf.pushToValueList(value);
            novf.incrementCapacity(conf);
            // update overflow pointer to parent node
            ovf.setNextPagePointer(novf.getPageIndex());
            // set being deleted to false
            novf.setBeingDeleted(false);
            // commit changes to new overflow page
            novf.writeNode(treeFile, conf);
            // commit changes to old overflow page
            ovf.writeNode(treeFile, conf);
        } else if(n.isLeaf()) {
            TreeLeaf l = (TreeLeaf)n;
            novf = new TreeOverflow(-1L, l.getPageIndex(),
                    generateFirstAvailablePageIndex(conf));
//            System.out.println("Creating overflow page with index: " + novf.getPageIndex() +
//                    " for key: " + l.getKeyAt(index));
            // push the first value
            novf.pushToValueList(value);
            novf.incrementCapacity(conf);
            // update overflow pointer to parent node
            l.setOverflowPointerAt(index, novf.getPageIndex());
            // set being deleted to false
            novf.setBeingDeleted(false);
            // commit changes to overflow page
            novf.writeNode(treeFile, conf);
            // commit changes to leaf page
            l.writeNode(treeFile, conf);
            // commit page counts
        } else {
            // "Expected Leaf or Overflow, got instead: " + n.getNodeType().toString()
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // commit page counts
        updatePageIndexCounts(conf);
    }

    /**
     * Binary search implementation for tree blocks; if not found returns the lower/upper bound
     * position instead based on `rank`.
     * @param n node to search
     * @param key key to search
     * @param rank rank of the search (for lower/upper bound)
     * @return the index of the bound or found key.
     */
    private int binSearchBlock(TreeNode n, Object[] key, Rank rank) {
        return binSearchRec(n, 0, n.getCurrentCapacity() - 1, key, rank);
    }

    /**
     * Binary search implementation for tree blocks.
     *
     * @param n    node to search
     * @param l    left (lower-part) array index
     * @param r    right (upper-part) array index
     * @param key  key to search
     * @param rank rank of the search (for lower/upper bound)
     * @return the index of the bound or found key.
     */
    private int binSearchRec(TreeNode n, int l, int r, Object[] key, Rank rank) {
        int m;
        Object[] mkey;

        if (l > r) {
            switch (rank) {
                case Pred:
                    return l == 0 ? l : l - 1;
                case Succ:
                    return l > 0 && l == n.getCurrentCapacity() ? l - 1 : l;
                //case Exact: return l;
                default:
                    return l;
            }
        } else {
            m = (l + r) / 2;
            mkey = n.getKeyAt(m);
        }

        if (conf.lt(mkey, key)) {
            return binSearchRec(n, m + 1, r, key, rank);
        } else if (conf.gt(mkey, key)) {
            return binSearchRec(n, l, m - 1, key, rank);
        } else { // this is equal
            return Rank.PlusOne == rank ? m + 1 : m;
        }
    }


    /**
     * read the values for the specified key. overflow values are read as well.
     *
     * @param l leaf which contains the key with the overflow page
     * @param i index of the key
     * @throws IOException is thrown when an I/O operation fails
     */
    private LinkedList<Long> getValues(TreeLeaf l, int i)
            throws IOException {
        LinkedList<Long> ans = new LinkedList<>();
        ans.addLast(l.valueList.get(i));
        if(conf.unique)
        {
            return ans;
        }
        // non-unique index, find values in the overflow pages
        if(l.getOverflowPointerAt(i) != -1) {
            TreeOverflow povf = (TreeOverflow)readNode(l.getOverflowPointerAt(i));
            while(true)
            {
                ans.addAll(povf.valueList);
                if(povf.getNextPagePointer() != -1L)
                {
                    povf = (TreeOverflow)readNode(povf.getNextPagePointer());
                }else {
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * Function to parse the overflow pages specifically for the range queries
     *
     * @param l leaf which contains the key with the overflow page
     * @param index index of the key
     * @param res where to store the results
     * @throws IOException is thrown when an I/O operation fails
     */
    private void parseOverflowPages(TreeLeaf l, int index, RangeResult res)
            throws IOException {
        TreeOverflow ovfPage = (TreeOverflow)readNode(l.getOverflowPointerAt(index));
        int icap = 0;
        while(icap < ovfPage.getCurrentCapacity()) {
            res.getQueryResult().add(new KeyValueWrapper(l.getKeyAt(index),
                    ovfPage.getValueAt(icap)));
            icap++;
            // check if we have more pages
            if(icap == ovfPage.getCurrentCapacity() &&
                    ovfPage.getNextPagePointer() != -1L) {
                ovfPage = (TreeOverflow)readNode(ovfPage.getNextPagePointer());
                icap = 0;
            }
        }
    }

    /**
     * Handle range search queries with a bit of twist on how we handle duplicate keys.
     *
     *  We have two basic cases depending duplicate keys, which is basically whether
     *  we actually return them or not.
     *
     * @param minKey min key of the range
     * @param maxKey max key of the range
     * @return the results packed in a neat class for handling
     * @throws IOException is thrown when an I/O operation fails
     */
    public LinkedList<Long> rangeSearch(Object[] minKey, Object[] maxKey)
            throws IOException, MiniDBException {
        padKey(minKey);
        SearchResult minAns = findKey(minKey);
        padKey(maxKey);
        LinkedList<Long> ans = new LinkedList<>();
        RangeResult rangeQueryResult = new RangeResult();
        if(minAns.found) {
            // read up until we find a key that's greater than maxKey
            // or the last entry.
            TreeLeaf leaf = minAns.leafLoc;
            Integer i = minAns.index;
            while(conf.le(leaf.getKeyAt(i), maxKey))
            {
                ans.addAll(getValues(leaf, i));
                i++;
                if(i == leaf.getCurrentCapacity())
                {// check if we need to read the next block
                    if(leaf.getNextPagePointer() < 0)// check if we have a next node to load.
                    {
                        break;
                    }
                    leaf = (TreeLeaf)readNode(leaf.getNextPagePointer());
                    i = 0;
                }
            }
            return ans;
        }
        SearchResult maxAns = findKey(maxKey);
        if(maxAns.found) {
            // read down until we find a key that's less than minKey
            // or the first entry.
            TreeLeaf leaf = maxAns.leafLoc;
            Integer i = maxAns.index;
            while(conf.ge(leaf.getKeyAt(i), minKey))
            {
                ans.addAll(getValues(leaf, i));
                i--;
                if(i < 0)
                {// check if we need to read the next block
                    if(leaf.getPrevPagePointer() < 0)// check if we have a previous node to load.
                    {
                        break;
                    }
                    leaf = (TreeLeaf)readNode(leaf.getPrevPagePointer());
                    i = leaf.getCurrentCapacity() - 1;
                }
            }
            return ans;
        }
        // this is the case where both searches might fail to find something, but
        // we *might* have something between in the given range. To account for
        // that even if we have *not* found something we will return those results
        // instead. For example say we have a range of [2, 5] and we only have keys
        // from [3, 4], thus both searches for min and max would fail to find a
        // matching key in both cases. Thing is to account for that *both* results
        // will be stopped at the first key that is less than min and max values
        // given even if we did not find anything.
        // finally return the result list (empty or not)
//        SearchResult sMax;
//        sMax = searchKey(maxKey, conf.unique);
//        int i = sMax.getIndex();
//        while(i >= 0 && conf.ge(sMax.getLeaf().getKeyAt(i), minKey)) {
//            rangeQueryResult.getQueryResult().
//                    add(new KeyValueWrapper(sMax.getLeaf().getKeyAt(i),
//                            sMax.getLeaf().getValueAt(i)));
//
//            // check if we have an overflow page
//            if(!conf.unique && sMax.getLeaf().getOverflowPointerAt(i) != -1)
//            {parseOverflowPages(sMax.getLeaf(), i, rangeQueryResult);}
//
//            i--;
//            // check if we need to read the next block
//            if(i < 0) {
//                // check if we do have another node to load
//                if(sMax.getLeaf().getPrevPagePointer() < 0)
//                // if not just break the loop
//                {break;}
//                sMax.setLeaf((TreeLeaf)readNode(sMax.getLeaf().getPrevPagePointer()));
//                // set it to max length
//                i = sMax.getLeaf().getCurrentCapacity()-1;
//            }
//        }
        return ans;
    }


    /**
    * @param key the key to find
    * @return if `key` is not found, `null` is returned.
     *        else, return Pair(leaf node that contains the given key, the index of the key)
    *
    */
    private SearchResult findKey(Object[] key) throws IOException, MiniDBException
    {
        return _findKey(root, null, -1, -1, key);
    }

    private SearchResult _findKey(TreeNode current, TreeInternalNode parent,
                               int parentPointerIndex, int parentKeyIndex,
                               Object[] key)
            throws IOException, MiniDBException{
        // check if we need to consolidate
        if(current.isTimeToMerge(conf)) {
            //System.out.println("Parent needs merging (internal node)");
            TreeNode mres = mergeOrRedistributeTreeNodes(current, parent,
                    parentPointerIndex, parentKeyIndex);
            if(mres != null) {
                current = mres;
            }
        }

        // search for the key
        int i = binSearchBlock(current, key, Rank.Succ);
        // check if it's an internal node
        if(current.isInternalNode()) {
            // if it is, descend to a leaf
            TreeInternalNode inode = (TreeInternalNode)current;
            int idx = i;
            // check if we are at the end
            if(conf.ge(key, current.getKeyAt(i))) {
                idx++;
            }
            // read the next node
            TreeNode next = readNode(inode.getPointerAt(idx));
            // finally return the resulting set
            return _findKey(next, inode, idx, i/*keyLocation*/, key);
        }
        // the current node, is a leaf.
        else if(current.isLeaf()) {
            TreeLeaf l = (TreeLeaf)current;
            // check if we actually found the key
            if(i == l.getCurrentCapacity() || conf.neq(key, l.getKeyAt(i))) {
                //key not found
                return new SearchResult(l, i, false);
            }else {
                // key found!
                return new SearchResult(l, i, true);
            }
        }
        // bad node type
        throw new MiniDBException(MiniDBException.BadNodeType);
    }

    /**
     * delete or update an existing pair.
     * It makes update faster than deleting and inserting, as well as sharing much code with deletion.
     * @param delete whether to delete or update.
     * @param value useful if `not conf.unique`. (if `conf.unique`, the `key` is enough to identify the pair)
     * @param newValue useful only when `delete == false` (which means update).
     * @return whether the deletion or update is successful
     * */
    private boolean deleteOrUpdatePair(Object[] key, long value, long newValue, boolean delete)
            throws IOException, MiniDBException
    {
        padKey(key);
        SearchResult ans = findKey(key);
        if(!ans.found)
        {
            return false;
        }
        TreeLeaf l = ans.leafLoc;
        Integer i = ans.index;
        if(conf.unique)
        {// the key is enough to identify the pair. `value` is useless
            if(delete)
            {
                l.removeEntryAt(i, conf);
            }else{
                l.valueList.set(i, newValue);
            }
            l.writeNode(treeFile, conf);
            return true;
        }

        long savedValue = l.getValueAt(i);
        if(savedValue == value)
        {// the value is found in this node
            if(delete)
            {
                long ovfpointer = l.getOverflowPointerAt(i);
                if(ovfpointer == -1)
                {//there is only one value for the key. delete the (key, value) pair.
                    l.removeEntryAt(i, conf);
                }else {// the index is not unique and there are multiple values.
                    // delete one value and read a value from the overflow page.
                    TreeOverflow povf = (TreeOverflow)readNode(ovfpointer);
                    l.valueList.set(i, povf.valueList.remove(0));
                    // persist
                    povf.writeNode(treeFile, conf);
                }
            }else {
                l.valueList.set(i, newValue);
            }
            l.writeNode(treeFile, conf);
            return true;
        }

        // check if we have an overflow page
        if(l.getOverflowPointerAt(i) != -1) {
            TreeOverflow ovf = null; // parent of `povf`
            TreeOverflow povf =
                    (TreeOverflow)readNode(l.getOverflowPointerAt(i));

            boolean found = false;
            while (!found)
            {
                // find the value in one overflow page
                ListIterator<Long> it = povf.valueList.listIterator();
                while (it.hasNext() && !found)
                {
                    if(it.next() == value)
                    {
                        found = true;
                    }
                }
                if(found)
                {
                    if(delete)
                    {
                        it.remove();
                        povf.decrementCapacity(conf); // decrease the page capacity
                    }else{
                        it.set(newValue);
                    }

                    if(povf.isEmpty()) // delete a value, may cause empty overflow page
                    {
                        // it's time to remove the page
                        if (ovf == null) {
                            l.setOverflowPointerAt(i, -1L);
                            l.writeNode(treeFile, conf);
                        }
                        else {
                            ovf.setNextPagePointer(-1L);
                            ovf.writeNode(treeFile, conf);
                        }
                        deletePage(povf.getPageIndex(), false);
                    }else{
                        // delete a value but the page is not empty, or update a value. just update the page.
                        if(!delete && value == newValue)
                        {
                            // small optimization. if the update is the same, no need to write the file.
                        }else{
                            povf.writeNode(treeFile, conf);
                        }
                    }
                    return true;
                }else {
                    // find the value in the next page
                    if(povf.getNextPagePointer() != -1L)
                    {
                        ovf = povf;
                        povf = (TreeOverflow)readNode(povf.getNextPagePointer());
                    }else {
                        // value not found
                        return false;
                    }
                }
            }
        }
        // pair not found
        return false;
    }

    public boolean deletePair(Object[] key, long value) throws IOException, MiniDBException  {
        return deleteOrUpdatePair(key, value, -1L, true);
    }

    public boolean updatePair(Object[] key, long value, long newValue) throws IOException, MiniDBException
    {
        return deleteOrUpdatePair(key, value, newValue, false);
    }

    /**
     * query all the values with key.
     * @return not null. LinkedList with length >= 0.
     * */
    public LinkedList<Long> search(Object[] key) throws IOException, MiniDBException
    {
        LinkedList<Long> returnValue = new LinkedList<>();
        padKey(key);
        SearchResult ans = findKey(key);
        if(!ans.found)
        {
            return returnValue;
        }
        TreeLeaf l = ans.leafLoc;
        Integer i = ans.index;
        returnValue.addAll(getValues(l, i));
        return returnValue;
    }

    /**
     * Check if the node has the specified parent
     *
     * @param node node can be internal or leaf
     * @param parent parent is always internal node
     * @param pindex index to check
     * @return true if it is, false if it's not
     */
    private boolean isParent(TreeNode node, TreeInternalNode parent, int pindex) {
        return(parent.getCurrentCapacity() >= pindex && pindex >= 0 &&
                (node.getPageIndex() == parent.getPointerAt(pindex)));
    }

    /**
     * Simple helper function to check if we can re-distribute the node
     * values.
     *
     * @param with node to check the capacity
     * @return the number of positions to check
     */
    private int canRedistribute(TreeNode with)
            throws MiniDBException {
        if(with != null) {
            if(with.isInternalNode()) {
                if(isValidAfterRemoval(((TreeInternalNode)with), 1)) {return(1);}
            } else if(with.isLeaf()) {
                if(isValidAfterRemoval(((TreeLeaf)with), 1)) {return(1);}
            } else {
                //"Not leaf or internal node found"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }
        }
        return(-1);
    }

    /**
     * Check if the internal node fulfills the B+ Tree invariant after removing
     * <code>remove</code> number of elements
     *
     * @param node node to check
     * @param remove elements to be removed
     * @return true if does, false if it fails the condition
     */
    private boolean isValidAfterRemoval(TreeInternalNode node, int remove)
        {return((node.getCurrentCapacity()-remove) >= conf.getMinInternalNodeCapacity());}

    /**
     * Check if the leaf node fulfills the B+ Tree invariant after removing
     * <code>remove</code> number of elements
     *
     * @param node node to check
     * @param remove elements to be removed
     * @return true if does, false if it fails the condition
     */
    private boolean isValidAfterRemoval(TreeLeaf node, int remove)
        {return((node.getCurrentCapacity()-remove) >= conf.getMinLeafNodeCapacity());}

    /**
     * Function that is responsible to redistribute values among two leaf nodes
     * while updating the referring key of the parent node (always an internal node).
     *
     *
     * We have two distinct cases which are the following:
     *
     * This case is when we use the prev pointer:
     *
     * |--------|  <-----  |--------|
     * |  with  |          |   to   |
     * |--------|  ----->  |--------|
     *
     * In this case we  *remove* the *last* n elements from with
     * and *push* them (in the order removed) into the destination node
     *
     * The parent key-pointer is updated with the first value of the
     * receiving node.
     *
     * The other case is when we use the next pointer:
     *
     * |--------|  <-----  |--------|
     * |   to   |          |  with  |
     * |--------|  ----->  |--------|
     *
     * In this case we *remove* the *first* n elements from with and
     * add them *last* (in the order removed) into the destination node.
     *
     * The parent key-pointer is updated with the first value of the
     * node we retrieved the values.
     *
     *
     *
     * @param to node to receive (Key, Value) pairs
     * @param with node that we take the (Key, Value) pairs
     * @param left if left is true, then we use prev leaf else next
     * @param parent the internal node parenting both
     * @param parentKeyIndex index of the parent that refers to this pair
     * @throws IOException is thrown when an I/O operation fails
     */
    private void redistributeNodes(TreeLeaf to, TreeLeaf with,
                                   boolean left, TreeInternalNode parent,
                                   int parentKeyIndex)
            throws IOException, MiniDBException {
        Object[] key;
        // handle the case when redistributing using prev
        if(left) {
            to.pushToOverflowList(with.removeLastOverflowPointer());
            to.pushToValueList(with.removeLastValue());
            to.pushToKeyArray(with.removeLastKey());
            to.incrementCapacity(conf);
            with.decrementCapacity(conf);
            // get the key from the left node
            key = to.getKeyAt(0);

        }
        // handle the case when redistributing using next
        else {
            to.addLastToOverflowList(with.popOverflowPointer());
            to.addLastToValueList(with.popValue());
            to.addLastToKeyArray(with.popKey());
            to.incrementCapacity(conf);
            with.decrementCapacity(conf);
            // get the key from the right node
            key = with.getKeyAt(0);
        }

        // in either case update parent pointer
        parent.setKeyArrayAt(parentKeyIndex, key);
        // finally write the changes
        to.writeNode(treeFile, conf);
        with.writeNode(treeFile, conf);
        parent.writeNode(treeFile, conf);
    }

    /**
     * Function that is responsible to redistribute values among two internal nodes
     * while updating the referring key of the parent node (always an internal node).
     *
     *
     * We have two distinct cases which are the following:
     *
     * This case is when we use the prev pointer:
     *
     * |--------|  <-----  |--------|
     * |  with  |          |   to   |
     * |--------|  ----->  |--------|
     *
     * In this case we  *remove* the *last* n elements from with
     * and *push* them (in the order removed) into the destination node
     *
     * The parent key-pointer is updated with the first value of the
     * receiving node.
     *
     * The other case is when we use the next pointer:
     *
     * |--------|  <-----  |--------|
     * |   to   |          |  with  |
     * |--------|  ----->  |--------|
     *
     * In this case we *remove* the *first* n elements from with and
     * add them *last* (in the order removed) into the destination node.
     *
     * The parent key-pointer is updated with the first value of the
     * node we retrieved the values.
     *
     *
     *
     * @param to node to receive (Key, Value) pairs
     * @param with node that we take the (Key, Value) pairs
     * @param left if left is true, then we use prev leaf else next
     * @param parent the internal node parenting both
     * @param parentKeyIndex index of the parent that refers to this pair
     * @throws IOException is thrown when an I/O operation fails
     */
    private void redistributeNodes(TreeInternalNode to, TreeInternalNode with,
                                   boolean left, TreeInternalNode parent,
                                   int parentKeyIndex)
            throws IOException, MiniDBException {
        Object[] key;
        Object[] pkey = parent.getKeyAt(parentKeyIndex);
        if(left) {
            to.pushToKeyArray(pkey);
            key = with.removeLastKey();
            to.pushToPointerArray(with.removeLastPointer());
            to.incrementCapacity(conf);
            with.decrementCapacity(conf);
        }
        // handle the case when redistributing using next
        else {
            to.addLastToKeyArray(pkey);
            key = with.popKey();
            to.addPointerLast(with.popPointer());
            to.incrementCapacity(conf);
            with.decrementCapacity(conf);
        }
        // in either case update the parent key
        parent.setKeyArrayAt(parentKeyIndex, key);
        // finally write the chances
        to.writeNode(treeFile, conf);
        with.writeNode(treeFile, conf);
        parent.writeNode(treeFile, conf);
    }

    /**
     * Function that merges two leaves together; in this case we *must*
     * have two leaves, left and right that are merged and their parent
     * that *must* be an internal node (or root).
     *
     * We also have the index of the parent that the pointers indicate
     * these two leaves
     *
     * it should be like this:
     *
     *            parent
     *      ... |  key  | ...
     *           /     \
     *      | left | right |
     *
     *
     * The merge happens from right -> left thus the final result would
     * be like this:
     *
     *            parent
     *      ... |  key  | ...
     *           /     \
     *      | result |  x
     *
     *
     * So basically we dump the values of right to the left while
     * updating the pointers.
     *
     *
     *
     * @param left left-most leaf to merge
     * @param right right-most leaf to merge
     * @param other the other cached node
     * @param parent parent of both leaves (internal node)
     * @param parentPointerIndex index of parent that has these two pointers
     * @throws IOException is thrown when an I/O operation fails
     */
    private TreeNode mergeNodes(TreeLeaf left, TreeLeaf right, TreeLeaf other,
                            TreeInternalNode parent, int parentPointerIndex,
                            int parentKeyIndex, boolean isLeftOfNext,
                                boolean useNextPointer)
            throws IOException, MiniDBException {

        if((left.getCurrentCapacity() + right.getCurrentCapacity()) >
                conf.getMaxLeafNodeCapacity()) {
            // "Leaf node capacity exceeded in merge"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // flag the node for deletion
        right.setBeingDeleted(true);
        // join the two leaves together.
        int cap = right.getCurrentCapacity();
        joinLeaves(left, right, cap);

        // update the next pointers
        left.setNextPagePointer(right.getNextPagePointer());
        // now fix the top pointer
        fixTheTopPointer(other, parent, parentPointerIndex,
                parentKeyIndex, isLeftOfNext, useNextPointer);

        // update capacity as in both cases we remove a value
        parent.decrementCapacity(conf);
        // write parent node
        parent.writeNode(treeFile, conf);

        // update the prev pointer of right next node (if any)
        if(right.getNextPagePointer() != -1) {
            TreeLeaf rnext = (TreeLeaf)readNode(right.getNextPagePointer());
            rnext.setPrevPagePointer(left.getPageIndex());
            rnext.writeNode(treeFile, conf);
        }

        // write the left node to disk
        left.writeNode(treeFile, conf);
        // remove the page
        deletePage(right.getPageIndex(), false);
        // finally return the node reference
        return (left);
    }

    private void fixTheTopPointer(TreeNode other, TreeInternalNode parent,
                                  int parentPointerIndex, int parentKeyIndex,
                                  boolean isLeftOfNext, boolean useNextPointer) {
        if (useNextPointer) {
            if (isLeftOfNext) {
                parent.removeKeyAt(parentKeyIndex + 1);
                parent.removePointerAt(parentPointerIndex + 1);
            } else {
                parent.removeKeyAt(parentKeyIndex);
                parent.removePointerAt(parentPointerIndex + 1);
            }
        } else {
            if (isLeftOfNext) {
                parent.removeKeyAt(parentKeyIndex);
                parent.removePointerAt(parentPointerIndex);
            } else {
                parent.removeKeyAt(parentKeyIndex);
                parent.removePointerAt(parentPointerIndex);
                parent.setKeyArrayAt(parentKeyIndex - 1, other.getFirstKey());
            }
        }
    }

    /**
     * @param left  left leaf
     * @param right right leaf
     * @param cap   max capacity of the leaf
     */
    private void joinLeaves(TreeLeaf left, TreeLeaf right, int cap)
            throws MiniDBException {
        for (int i = 0; i < cap; i++) {
            left.addLastToOverflowList(right.popOverflowPointer());
            left.addLastToValueList(right.popValue());
            left.addLastToKeyArray(right.popKey());
            left.incrementCapacity(conf);
            right.decrementCapacity(conf);
        }
    }

    /**
     * Naive merge of two leaf nodes from right to left.
     *
     * @param left node that merge ends
     * @param right node that is deleted during merge
     * @throws IOException is thrown when an I/O operation fails
     */
    private void mergeNodes(TreeLeaf left, TreeLeaf right)
            throws IOException, MiniDBException {
        right.setBeingDeleted(true);
        // join the two leaves together.
        int cap = right.getCurrentCapacity();
        joinLeaves(left, right, cap);

        // remove the page
        deletePage(right.getPageIndex(), false);
    }

    /**
     * Function that merges two internal nodes together; in this case
     * we *must* have two internal nodes, left and right that are merged
     * and their parent that *must* be an internal node (or root).
     *
     * We also have the index of the parent that the pointers
     * indicate these two internal nodes.
     *
     * it should be like this:
     *
     *            parent
     *      ... |  key  | ...
     *           /     \
     *      | left | right |
     *
     * @param left left-most internal node to merge
     * @param right right-most internal node to merge
     * @param parent parent of both internal nodes.
     * @param parentPointerIndex index of the parent that has these two pointers
     * @throws IOException is thrown when an I/O operation fails
     */
    private TreeNode mergeNodes(TreeInternalNode left, TreeInternalNode right,
                            TreeInternalNode other, TreeInternalNode parent,
                                int parentPointerIndex,
                                int parentKeyIndex,
                                boolean isLeftOfNext,
                                boolean useNextPointer)
            throws IOException, MiniDBException {

        // check if we can actually merge
        if((left.getCurrentCapacity() + right.getCurrentCapacity()) >
                conf.getMaxInternalNodeCapacity()) {
            // "Internal node capacity exceeded in merge"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // check if we are using a trickery
        if(isLeftOfNext && useNextPointer)
            {left.addLastToKeyArray(parent.getKeyAt(parentKeyIndex+1));}
        else
            {left.addLastToKeyArray(parent.getKeyAt(parentKeyIndex));}

        // flag the node for deletion
        right.setBeingDeleted(true);

        int cap = right.getCurrentCapacity();
        joinInternalNodes(left, right, cap);
        // pump the last pointer as well
        left.addPointerLast(right.popPointer());
        // now increment the capacity as well
        left.incrementCapacity(conf);
        // now fix the top pointer.


        // now fix the top pointer
        fixTheTopPointer(other, parent, parentPointerIndex,
                parentKeyIndex, isLeftOfNext, useNextPointer);

        // update capacity as in both cases we remove a value
        parent.decrementCapacity(conf);
        // write parent node
        parent.writeNode(treeFile, conf);

        // write the node
        left.writeNode(treeFile, conf);
        // remove the page
        deletePage(right.getPageIndex(), false);
        // finally remove the node reference
        return(left);
    }

    /**
     * Naive merge of two internal nodes from right to left.
     *
     * @param left node that merge ends
     * @param right node that is deleted during merge
     * @throws IOException is thrown when an I/O operation fails
     */
    private void mergeNodes(TreeInternalNode left, TreeInternalNode right, Object[] midKey)
            throws IOException, MiniDBException {
        right.setBeingDeleted(true);
        left.addLastToKeyArray(midKey);
        int cap = right.getCurrentCapacity();
        joinInternalNodes(left, right, cap);

        // pump the last pointer as well
        left.addPointerLast(right.popPointer());
        left.incrementCapacity(conf);

        // finally remove the page
        deletePage(right.getPageIndex(), false);
    }

    /**
     * @param left  left node
     * @param right right node
     * @param cap   max capacity of the node
     */
    private void joinInternalNodes(TreeInternalNode left, TreeInternalNode right, int cap)
            throws MiniDBException {
        for (int i = 0; i < cap; i++) {
            left.addLastToKeyArray(right.popKey());
            left.addPointerLast(right.popPointer());
            left.incrementCapacity(conf);
            right.decrementCapacity(conf);
        }
    }

    /**
     *
     * This function handles the merging or redistribution of the nodes depending
     * on their capacity; usually we just redistribute, if not we merge.
     *
     * @param mnode current node (either leaf or internal node)
     * @param parent parent node *always* an internal node
     * @param parentPointerIndex pointer index to mnode in parent
     * @param parentKeyIndex key index that has mnode as a child in parent
     * @return the updated mnode
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    public TreeNode mergeOrRedistributeTreeNodes(TreeNode mnode, TreeInternalNode parent,
                                             int parentPointerIndex, int parentKeyIndex)
            throws IOException, MiniDBException {

        // that index should not be present
        if(parent != null && parentPointerIndex < 0)
            {throw new IllegalStateException("index < 0");}

        // this is the only case that the tree shrinks, from the root.
        if(parent == null) {
            TreeNode lChild = handleRootRedistributionOrMerging(mnode);
            if (lChild != null) return lChild;
        }
        // merging a leaf requires the most amount of work, since
        // all leaves by definition are linked in a doubly-linked
        // linked-list; hence when we merge/remove a node we have
        // to make sure those links are consistent
        else if(mnode.isLeaf()) {
            return handleLeafNodeRedistributionOrMerging(mnode, parent,
                    parentPointerIndex, parentKeyIndex);
        }

        // we have to merge internal nodes, this is the somewhat easy
        // case, since we do not have to update any more links than the
        // currently pulled nodes.
        else if(mnode.isInternalNode()) {
            return handleInternalNodeRedistributionOrMerging(mnode, parent,
                    parentPointerIndex, parentKeyIndex);
        } else
            {throw new IllegalStateException("Read unknown or overflow page while merging");}

        // probably we didn't change something
        return null;
    }

    /**
     * Handle the root node cases (leaf and internal node)
     *
     * @param mnode root node
     * @return the root node
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private TreeNode handleRootRedistributionOrMerging(TreeNode mnode)
            throws IOException, MiniDBException {
        if(mnode.isInternalNode()) {
            //System.out.println("\n -- Check if Consolidating Root required");

            if (mnode.getCurrentCapacity() > 1) {
                //System.out.println("\n -- Root size > 2, no consolidation");
                return root;
            }

            TreeInternalNode splitNode = (TreeInternalNode)mnode;
            // read up their pointers
            TreeNode lChild, rChild;

            // load the pointers
            lChild = readNode(splitNode.getPointerAt(0));
            rChild = readNode(splitNode.getPointerAt(1));

            if(lChild == null || rChild == null)
            {
                // "Null root child found."
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }

            int lcap = lChild.getCurrentCapacity();
            int rcap = rChild.getCurrentCapacity();

            // check their type
            if(lChild.isLeaf()) {

                // check if it's time to merge
                if((lcap > conf.getMinLeafNodeCapacity()) &&
                        (rcap > conf.getMinLeafNodeCapacity())) {
                    //System.out.println(" -- No need to consolidate root yet (to -> leaf)");
                    return mnode;
                }

                TreeInternalNode rNode = (TreeInternalNode)mnode;
                TreeLeaf pLeaf = (TreeLeaf)lChild,
                         nLeaf = (TreeLeaf)rChild;

                // now merge them, and delete root page, while
                // updating it's page number etc

                int nnum = canRedistribute(nLeaf);
                int pnum = canRedistribute(pLeaf);

                if(nnum > 0) {
                    redistributeNodes(pLeaf, nLeaf, false, rNode, 0);
                }
                // now check if we can redistribute with prev
                else if(pnum > 0) {
                    redistributeNodes(nLeaf, pLeaf, true, rNode, 0);
                }
                // merge the two nodes and promote them to root
                else {
                    mergeNodes(pLeaf, nLeaf);
                    // update root page
                    pLeaf.setNodeType(TreeNodeType.TREE_ROOT_LEAF);
                    // update the leaf pointers
                    pLeaf.setNextPagePointer(-1L);
                    pLeaf.setPrevPagePointer(-1L);
                    // delete previous root page
                    deletePage(root.getPageIndex(), false);
                    // set root page
                    root = lChild;
                    // write root header
                    writeFileHeader(conf);
                    // write left leaf
                    lChild.writeNode(treeFile, conf);
                    // since we have a new root
                    return(lChild);
                }

            } else if(lChild.isInternalNode()) {
                // check if it's time to merge
                if ((lcap + rcap) >= conf.getMaxInternalNodeCapacity()) {
                    //System.out.println(" -- No need to consolidate root yet (to -> internal)");
                    return mnode;
                }
                /*
                else {
                    System.out.println("-- Consolidating Root (internal -> internal)");
                }
                */

                TreeInternalNode rNode = (TreeInternalNode)mnode,
                                 lIntNode = (TreeInternalNode)lChild,
                                 rIntNode = (TreeInternalNode)rChild;

                int nnum = canRedistribute(rIntNode);
                int pnum = canRedistribute(lIntNode);


                if(nnum > 0) {
                    redistributeNodes(lIntNode, rIntNode, true, rNode, 0);
                }
                // now check if we can redistribute with prev
                else if(pnum > 0) {
                    //System.out.println("\t -- Redistributing right with elements from left");
                    redistributeNodes(rIntNode, lIntNode, false, rNode, 0);
                }
                // merge the two nodes and promote them to root
                else {
                    //System.out.println("\t -- Merging leaf nodes");
                    mergeNodes(lIntNode, rIntNode, splitNode.getFirstKey());
                    // update root page
                    lIntNode.setNodeType(TreeNodeType.TREE_ROOT_INTERNAL);
                    // delete previous root page
                    deletePage(root.getPageIndex(), false);
                    // set root page
                    root = lChild;
                    // write root header
                    writeFileHeader(conf);
                    // write left leaf
                    lChild.writeNode(treeFile, conf);
                    // since we have a new root
                    return(lChild);
                }
            } else{
                // "Unknown children type"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }

            return(root);

        } else if(!mnode.isLeaf()){
            // "Invalid tree Root type."
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }
        return null;
    }

    /**
     * Handle the leaf section of the redistribution/merging
     *
     * @param mnode node to process
     * @param parent the parent node
     * @param parentPointerIndex parent pointer index
     * @param parentKeyIndex parent key index that mnode is child
     * @return the updated node (if any updates are made)
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private TreeNode handleLeafNodeRedistributionOrMerging(TreeNode mnode,
                                                           TreeInternalNode parent,
                                                           int parentPointerIndex,
                                                           int parentKeyIndex)
            throws IOException, MiniDBException {
        TreeLeaf splitNode = (TreeLeaf)mnode;
        TreeLeaf nptr, pptr;

        // load the pointers
        nptr = (TreeLeaf)readNode(splitNode.getNextPagePointer());
        pptr = (TreeLeaf)readNode(splitNode.getPrevPagePointer());

        if(nptr == null && pptr == null) {
            // "Both children (leaves) can't null"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        } /*
        else {
            System.out.println("Leaf node merging/redistribution needs to happen");
        }*/

        // validate neighbouring nodes
        validateNeighbours(pptr, splitNode, nptr);

        int nnum = canRedistribute(nptr);
        int pnum = canRedistribute(pptr);
        int snum = canRedistribute(splitNode);
        boolean isLeftOfNext = (parentPointerIndex > parentKeyIndex);
        boolean splitNodeIsLeftChild = parentKeyIndex == parentPointerIndex;

        boolean npar = isParent(nptr, parent, parentPointerIndex+1);
        boolean ppar = isParent(pptr, parent, parentPointerIndex-1);

        // check if we can redistribute with next
        if(nnum > 0 && npar) {
            //System.out.println("\t -- Redistributing split node with elements from next");
            if(splitNodeIsLeftChild) {
                redistributeNodes(splitNode, nptr, false, parent, parentKeyIndex);
            } else {
                redistributeNodes(splitNode, nptr, false, parent, parentKeyIndex+1);
            }
        }
        // now check if we can redistribute with prev
        else if(pnum > 0 && ppar) {
            //System.out.println("\t -- Redistributing split node with elements from prev");
            if(splitNodeIsLeftChild) {
                redistributeNodes(splitNode, pptr, true, parent, parentKeyIndex-1);
            } else {
                redistributeNodes(splitNode, pptr, true, parent, parentKeyIndex);
            }
        } else if(snum > 0) {
            if(nptr != null) {
                //System.out.println("\t -- Redistributing next node with elements from split");
                if(splitNodeIsLeftChild) {
                    redistributeNodes(nptr, splitNode, true, parent, parentKeyIndex);
                } else {
                    redistributeNodes(nptr, splitNode, true, parent, parentKeyIndex+1);
                }
            } else {
                //System.out.println("\t -- Redistributing prev with elements from split");
                if(splitNodeIsLeftChild) {
                    redistributeNodes(pptr, splitNode, false, parent, parentKeyIndex-1);
                } else {
                    redistributeNodes(pptr, splitNode, false, parent, parentKeyIndex);
                }
            }
        }
        // we can't redistribute, try merging with next
        else if(npar) {
            //System.out.println("Merging leaf next");
            // it's the case where split node is the left node from parent
            mnode = mergeNodes(splitNode, nptr, pptr, parent,
                    parentPointerIndex, parentKeyIndex,
                    isLeftOfNext, /*useNextPointer = */ true);
        }
        // last chance, try merging with prev
        else if(ppar) {
            //System.out.println("Merging leaf prev");
            // it's the case where split node is in the left from parent
            mnode = mergeNodes(pptr, splitNode, nptr, parent,
                    parentPointerIndex, parentKeyIndex,
                    isLeftOfNext, /*useNextPointer = */ false);
        } else
            {throw new IllegalStateException("Can't have both leaf " +
                    "pointers null and not be root or no " +
                    "common parent");}

        return(mnode);
    }

    /**
     * Extra validation on leaf pointers
     *
     * @param prev previous leaf
     * @param split current leaf (split node)
     * @param next next leaf
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private void validateNeighbours(TreeLeaf prev, TreeLeaf split, TreeLeaf next)
            throws MiniDBException {
        if(prev != null) {
            if(split.getPrevPagePointer() != prev.getPageIndex()) {
                // "Split prev pointer not matching prev page index"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            } else if(prev.getNextPagePointer() != split.getPageIndex()) {
                // "Split page index not matching prev pointer"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }
        }

        if(next != null) {
            if(split.getNextPagePointer() != next.getPageIndex()) {
                // "Split next pointer not matching next page index"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            } else if(next.getPrevPagePointer() != split.getPageIndex()) {
                // "Split page index not matching next pointer"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }
        }
    }

    /**
     * Handle the internal node redistribution/merging
     *
     * @param mnode node to process
     * @param parent the parent node
     * @param parentPointerIndex parent pointer index
     * @param parentKeyIndex parent key index that mnode is child
     * @return the updated node (if any updates are made)
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private TreeNode handleInternalNodeRedistributionOrMerging(TreeNode mnode,
                                                               TreeInternalNode parent,
                                                               int parentPointerIndex,
                                                               int parentKeyIndex)
            throws IOException, MiniDBException {
        //System.out.println("Internal node merging/redistribution needs to happen");

        TreeInternalNode splitNode = (TreeInternalNode)mnode;
        TreeInternalNode nptr, pptr;

        // load the adjacent nodes
        nptr = (TreeInternalNode)readNode(parent.getPointerAt(parentPointerIndex+1));
        pptr = (TreeInternalNode)readNode(parent.getPointerAt(parentPointerIndex-1));

        if(nptr == null && pptr == null) {
            // "Can't have both children null"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        int nnum = canRedistribute(nptr);
        int pnum = canRedistribute(pptr);
        int snum = canRedistribute(splitNode);
        boolean isLeftOfNext = (parentPointerIndex > parentKeyIndex);
        boolean splitNodeIsLeftChild = parentKeyIndex == parentPointerIndex;

        // check if we can redistribute with the next node
        if(nnum > 0) {
            //System.out.println(" -- Internal Redistribution to split with next");
            if(splitNodeIsLeftChild) {
                redistributeNodes(splitNode, nptr, false, parent, parentKeyIndex);
            } else {
                redistributeNodes(splitNode, nptr, false, parent, parentKeyIndex+1);
            }
        }
        // check if we can redistribute with the previous node
        else if(pnum > 0) {
            //System.out.println(" -- Internal Redistribution to split with prev");
            if(splitNodeIsLeftChild) {
                redistributeNodes(splitNode, pptr, true, parent, parentKeyIndex-1);
            } else {
                redistributeNodes(splitNode, pptr, true, parent, parentKeyIndex);
            }
        }
        else if(snum > 0) {
            // check try to send items from next
            if(nptr != null) {
                //System.out.println(" -- Internal Redistribution to next with split");
                if(splitNodeIsLeftChild) {
                    redistributeNodes(nptr, splitNode, true, parent, parentKeyIndex);
                } else {
                    redistributeNodes(nptr, splitNode, true, parent, parentKeyIndex+1);
                }
            }
            // if not, try to send items from prev
            else {
                //System.out.println(" -- Internal Redistribution to prev with split");
                if(splitNodeIsLeftChild) {
                    redistributeNodes(pptr, splitNode, false, parent, parentKeyIndex-1);
                } else {
                    redistributeNodes(pptr, splitNode, false, parent, parentKeyIndex);
                }
            }
        }
        // can't redistribute; merging needs to happen, check which has t-1 elements
        else {
            //System.out.println(" -- Internal merging actually happens");
            // check if we can merge with the right node
            if(nptr != null && nptr.isTimeToMerge(conf)) {
                mnode = mergeNodes(splitNode, nptr, pptr, parent,
                        parentPointerIndex, parentKeyIndex,
                        isLeftOfNext, /*useNextPointer = */ true);
            }
            // now, check if we can merge with the left node
            else if(pptr != null && pptr.isTimeToMerge(conf)) {
                mnode = mergeNodes(pptr, splitNode, nptr, parent,
                        parentPointerIndex, parentKeyIndex,
                        isLeftOfNext, /*useNextPointer = */ false);
            } else {
                // "Can't merge or redistribute, corrupted file?"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }
        }

        return(mnode);
    }

    /**
     * Function that initially creates the tree. Here we always
     * create a Leaf that acts as our Root, until we split it.
     * @return the initial (leaf) tree root.
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private TreeNode createTree() throws IOException, MiniDBException {
        if(root == null) {
            root = new TreeLeaf(-1, -1,
                    TreeNodeType.TREE_ROOT_LEAF,
                    generateFirstAvailablePageIndex(conf));
            // write the file
            root.writeNode(treeFile, conf);
        }
        return(root);
    }

    /**
     * Map the short value to an actual node type enumeration value.
     * This paradoxically is the opposite of that we do in the similarly named
     * function in each node.
     *
     * @param pval a value read from the file indicating which type of node this is
     * @return nodeType equivalent
     * @throws InvalidPropertiesFormatException is thrown when the node is of an unknown type
     */
    private TreeNodeType getPageType(short pval)
            throws InvalidPropertiesFormatException {
        switch(pval) {

            case 1:         // LEAF
                {return(TreeNodeType.TREE_LEAF);}

            case 2:         // INTERNAL NODE
                {return(TreeNodeType.TREE_INTERNAL_NODE);}

            case 3:         // INTERNAL NODE /w ROOT
                {return(TreeNodeType.TREE_ROOT_INTERNAL);}

            case 4:         // LEAF NODE /w ROOT
                {return(TreeNodeType.TREE_ROOT_LEAF);}

            case 5:         // LEAF OVERFLOW NODE
                {return(TreeNodeType.TREE_LEAF_OVERFLOW);}

            case 6:         // TREE_LOOKUP_OVERFLOW
            {
                return (TreeNodeType.TREE_LOOKUP_OVERFLOW);
            }
            default: {
                throw new InvalidPropertiesFormatException("Unknown " +
                        "node value read; file possibly corrupt?");
            }
        }
    }

    /**
     * Calculate the page offset taking in account the
     * for the lookup page at the start of the file.
     *
     * @param index index of the page
     * @return the calculated file offset to be fed in seek.
     */
    private long calculatePageOffset(long index)
        {return(conf.pageSize*(index+1));}

    /**
     * Function that commits the allocation pool to the file;
     * this can be done after each deletion or more unsafely
     * before committing the file changes at the end.
     *
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private void commitLookupPage() throws IOException, MiniDBException {

        int i, cap = lookupPagesPool.size();
        // push all the existing lookup pages to the free pool
        for (i = 0; i < cap; i++) {
            freeSlotPool.add(lookupPagesPool.removeFirst());
        }
        // adjust the page layout first
        squeezeFileLength();

        // check if we need more than one lookup page
        if (freeSlotPool.size() <= conf.getFirstLookupPageElements()) {
            // reset the pointer and commit it
            // seek to the position we have to start to write
            treeFile.seek(conf.headerSize - 8 /* 1 less position */);
            this.firstPoolNextPointer = -1L;
            treeFile.writeLong(this.firstPoolNextPointer);
            int flpSz = freeSlotPool.size();
            // now write
            if (flpSz > 0) {
                for (Long fpIndex : freeSlotPool) {
                    treeFile.writeLong(fpIndex);
                }

            }
            // if we have less elements than max write -1L
            // at the end to indicate termination of page
            // but only for the first "page" (after the
            // file header).
            if (flpSz < conf.getFirstLookupPageElements()) {
                treeFile.writeLong(-1L);
            }
        } else {
            int written = 0;
            int rest = (freeSlotPool.size() - conf.getFirstLookupPageElements()),
                    // go past the header chunk
                    poolIndex = conf.getFirstLookupPageElements();

            TreeLookupOverflowNode lpOvf;
            cap = conf.getMaxLookupPageOverflowCapacity();
            // calculate the number of pages needed
            int pages = (int) Math.ceil(rest / ((double) cap));

            for (i = 0; i < pages; i++) {
                lookupPagesPool.add(freeSlotPool.removeFirst());
            }

            // the ending pointer
            lookupPagesPool.add(-1L);
            // get the smallest page available
            firstPoolNextPointer = lookupPagesPool.getFirst();
            // write all the pages
            for (i = 0; i < pages; i++) {
                // create the page
                lpOvf = createOverflowLookupPage(lookupPagesPool.get(i),
                        lookupPagesPool.get(i + 1));

                // write it.
                for (int j = 0;
                     j < cap && poolIndex < freeSlotPool.size();
                     j++, poolIndex++) {
                    lpOvf.addToKeyArrayAt(j, new Object[]{freeSlotPool.get(poolIndex)});
                    lpOvf.incrementCapacity(conf);
                    written++;
                }
                lpOvf.writeNode(treeFile, conf);
            }
            // remove the last entry
            lookupPagesPool.removeLast();

            // seek to the position we have to start to write
            treeFile.seek(conf.headerSize - 8 /* 1 less position */);
            treeFile.writeLong(this.firstPoolNextPointer);
            // write the first page chunk (after the
            // file header) [no need to check for capacity, as this
            // is done above]
            for (i = 0; i < conf.getFirstLookupPageElements(); i++) {
                treeFile.writeLong(freeSlotPool.get(i));
                written++;
            }


            if (written != freeSlotPool.size()) {
                // "Amount of lookup values written does not comply with the size of the pool"
                throw new MiniDBException(MiniDBException.InvalidBPTreeState);
            }

//            System.out.println(" -- Multiple pages needed for the " +
//                    "overflow values" + "\n\tPages needed: " + pages +
//                    "\n\tInitial page capacity: " +
//                    conf.getFirstLookupPageElements() +
//                    "\n\tPer Page capacity: " +
//                    conf.getMaxLookupPageOverflowCapacity());
        }
    }

    /**
     * This function adjust the file length by purging the high index pages that
     * are used. Low index pages are purged last by design.
     * Unused pages are pruned out.
     * 0 1 2 3 4 5  -- pages
     * 0 1 0 1 0 0  -- used
     * then 4 & 5 are pruned out.
     * @throws IOException is thrown when an I/O operation fails
     */
    private void squeezeFileLength() throws IOException {
        Collections.sort(freeSlotPool);
        long lastPos = freeSlotPool.size() > 0 ? freeSlotPool.getLast() : -1L;
        while (lastPos != -1L && lastPos == calculatePageOffset(this.maxPageNumber)) {
            this.maxPageNumber--;
            freeSlotPool.removeLast();
            lastPos = freeSlotPool.size() > 0 ? freeSlotPool.getLast() : -1L;
        }
        // set the length to be max page plus one
        treeFile.setLength(calculatePageOffset(this.maxPageNumber + 1));
    }

    private TreeLookupOverflowNode createOverflowLookupPage(long index, long nextPointer) {
        return (new TreeLookupOverflowNode(index, nextPointer));
    }

    /**
     * Read each tree node and return it as a generic type
     *
     * @param index index of the page in the file
     * @return a TreeNode object referencing to the loaded page
     * @throws IOException is thrown when an I/O operation fails
     */
    private TreeNode readNode(long index) throws IOException {

        // caution.
        if(index < 0)
            {return(null);}
        treeFile.seek(index);
        // get the page type
        TreeNodeType nt = getPageType(treeFile.readShort());

        // handle internal node reading
        if(isInternalNode(nt)) {
            TreeInternalNode tnode = new TreeInternalNode(nt, index);
            int curCap = treeFile.readInt();
            for(int i = 0; i < curCap; i++) {
                tnode.addToKeyArrayAt(i, TreeNode.readKey(treeFile, conf));
                tnode.addPointerAt(i, treeFile.readLong());
            }
            // add the final pointer
            tnode.addPointerAt(curCap, treeFile.readLong());
            // update the capacity
            tnode.setCurrentCapacity(curCap);
            // set deleted flag
            tnode.setBeingDeleted(false);
            return(tnode);
        }
        // check if we have an overflow page
        else if(isOverflowPage(nt)) {
            long nextptr = treeFile.readLong();
            long prevptr = treeFile.readLong();
            int curCap = treeFile.readInt();
            TreeOverflow tnode = new TreeOverflow(nextptr, prevptr, index);

            // read entries
            for(int i = 0; i < curCap; i++) {
                tnode.addToValueList(i, treeFile.readLong());
            }
            // update capacity
            tnode.setCurrentCapacity(curCap);
            return(tnode);
        }
        // well, it must be a leaf node
        else if (isLeaf(nt)) {
            long nextptr = treeFile.readLong();
            long prevptr = treeFile.readLong();
            int curCap = treeFile.readInt();
            TreeLeaf tnode = new TreeLeaf(nextptr, prevptr, nt, index);

            // read entries
            for(int i = 0; i < curCap; i++) {
                tnode.addToKeyArrayAt(i, TreeNode.readKey(treeFile, conf));
                tnode.addToOverflowList(i, treeFile.readLong());
                tnode.addToValueList(i, treeFile.readLong());
            }
            // update capacity
            tnode.setCurrentCapacity(curCap);

            // set being deleted to false
            tnode.setBeingDeleted(false);

            return(tnode);
        } else {
            long nextptr = treeFile.readLong();
            int curCap = treeFile.readInt();
            TreeLookupOverflowNode lpOvf = new TreeLookupOverflowNode(index, nextptr);

            // now loop through the
            for (int i = 0; i < curCap; i++) {
                lpOvf.addToKeyArrayAt(i, new Object[]{treeFile.readLong()});
            }

            // update capacity
            lpOvf.setCurrentCapacity(curCap);

            return (lpOvf);
        }
    }

    /**
     * Check if the node is an internal node
     *
     * @param nt nodeType of the node we want to check
     * @return return true if it's an Internal Node, false if it's not.
     */
    private boolean isInternalNode(TreeNodeType nt) {
        return(nt == TreeNodeType.TREE_INTERNAL_NODE ||
                nt == TreeNodeType.TREE_ROOT_INTERNAL);
    }

    /**
     * Check if the node is an overflow page
     *
     * @param nt nodeType of the node we want to check
     * @return return true if it's an overflow page, false if it's not.
     */
    public boolean isOverflowPage(TreeNodeType nt)
        {return(nt == TreeNodeType.TREE_LEAF_OVERFLOW);}

    /**
     * Check if the node is a leaf page
     *
     * @param nt nodeType of the node we want to check
     * @return return true it's a leaf page, false if it's not
     */
    public boolean isLeaf(TreeNodeType nt) {
        return(nt == TreeNodeType.TREE_LEAF ||
                nt == TreeNodeType.TREE_LEAF_OVERFLOW ||
                nt == TreeNodeType.TREE_ROOT_LEAF);
    }

    /**
     * Reads an existing file and generates a B+ configuration based on the stored values
     *
     * @param r file to read from
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private void readFileHeader(RandomAccessFile r)
            throws IOException, MiniDBException {
        r.seek(0L);

        // read the header number
        int headerNumber = r.readInt();

        if(headerNumber < 0)
        {
            // "Negative header number found..."
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // read the page size
        int pageSize = r.readInt();

        if(pageSize < 0)
        {
            // "Cannot create a tree with negative page size"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // read the entry size
        int entrySize = r.readInt();

        if(entrySize <= 0)
        {
            // "Entry size must be > 0"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // key size
        int keySize = r.readInt();

        if(keySize > 8 || keySize < 4)
        {
            // "Key size but be either 4 or 8 bytes"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // read the number of pages (excluding the lookup)
        totalTreePages = r.readLong();

        if(totalTreePages < 0)
        {
            // "Tree page number cannot be < 0"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }


        // read the max page offset
        maxPageNumber = r.readLong();

        if(maxPageNumber < 0 || (totalTreePages > 0 && maxPageNumber == 0))
        {
            // "Invalid max page offset"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // read the root index
        long rootIndex = r.readLong();

        if(rootIndex < 0)
        {
            // "Root can't have index < 0"
            throw new MiniDBException(MiniDBException.InvalidBPTreeState);
        }

        // read the next lookup page pointer
        this.firstPoolNextPointer = r.readLong();

        // read the root.
        root = readNode(rootIndex);
    }

    /**
     * Writes the file header containing all the juicy details
     *
     * @param conf valid configuration
     * @throws IOException is thrown when an I/O operation fails
     */
    private void writeFileHeader(BPlusConfiguration conf)
            throws IOException {
        treeFile.seek(0L);
        treeFile.writeInt(conf.headerSize);
        treeFile.writeInt(conf.pageSize);
        treeFile.writeInt(conf.entrySize);
        treeFile.writeInt(conf.keySize);
        treeFile.writeLong(totalTreePages);
        treeFile.writeLong(maxPageNumber);
        treeFile.writeLong(root.getPageIndex());
        treeFile.writeLong(this.firstPoolNextPointer);
    }

    /**
     * Opens a file descriptor to our B+ Tree storage file; it can
     * handle already existing files as well without recreating them
     * unless explicitly stated.
     *
     * @param path file path
     * @param mode mode of opening (basically to truncate it or not)
     * @param opt configuration reference
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    private void openFile(String path, String mode, BPlusConfiguration opt)
            throws IOException, MiniDBException {
        File f = new File(path);
        String stmode = mode.substring(0, 2);
        treeFile = new RandomAccessFile(path, stmode);
        conf = opt;
        // check if the file already exists
        if(f.exists() && !mode.contains("+")) {
            System.out.println("File already exists (size: " + treeFile.length() +
                    " bytes), trying to read it...");
            readFileHeader(treeFile);
            // read the lookup page
            initializeLookupPage(f.exists());
            System.out.println("File seems to be valid. Loaded OK!");
        }
        // if we have to start anew, do so.
        else {
            treeFile.setLength(0);
            initializeLookupPage(false);
            createTree();
            writeFileHeader(conf);
        }
    }

    /**
     * Just commit the tree by actually closing the FD
     * thus flushing the buffers.
     *
     * @throws IOException is thrown when an I/O operation fails
     * @throws MiniDBException is thrown when there are inconsistencies in the blocks.
     */
    public void commitTree() throws IOException, MiniDBException {
        commitLookupPage();
        writeFileHeader(conf);
        this.treeFile.close();
    }

    /**
     * This function initializes the look-up page; in the simple
     * case that it does not already exist it just creates an
     * empty page by witting -1L all over it. If it exists then
     * we load it into memory for further use.
     *
     * @param exists flag to indicate if the file already exists
     * @throws IOException is thrown when an I/O operation fails
     */
    private void initializeLookupPage(boolean exists) throws IOException {
        // get to the beginning of the file after the header
        this.treeFile.seek(conf.headerSize);

        // check if already have a page, if not create it
        if(!exists) {
            for (int i = 0; i < conf.getFirstLookupPageElements(); i++)
                {this.treeFile.writeLong(-1);}
        }
        // if we do, read it.
        else {

            // add this as well.
            //this.freeSlotPool.add(firstPoolNextPointer);
            long val;
            int parsed = 0;
            for (int i = 0; i < conf.getFirstLookupPageElements(); i++) {
                if ((val = this.treeFile.readLong()) == -1L) {
                    break;
                }
                this.freeSlotPool.add(val);
            }

            // now check if we have more pages
            long pindex = firstPoolNextPointer;
            TreeLookupOverflowNode lpOvf;
            while (pindex != -1L) {
                parsed++;
                freeSlotPool.add(pindex);
                lpOvf = (TreeLookupOverflowNode) readNode(pindex);
                for(Object[] each : lpOvf.keyArray)
                {
                    freeSlotPool.addLast((Long)each[0]);
                }
                pindex = lpOvf.getNextPointer();
            }

            System.out.println("-- Parsed " + parsed +
                    " lookup overflow pages and the initial one, totaling: " +
                    freeSlotPool.size() + " entries");
        }
    }

    /**
     * Generate the first available index for a page.
     *
     * @param conf B+ configuration reference
     * @return page index
     */
    private long generateFirstAvailablePageIndex(BPlusConfiguration conf) {
        long index;
        // check if we have used pages
        if(freeSlotPool.size() > 0)
            {index = freeSlotPool.pop(); totalTreePages++; return(index);}
        // if not pad to the end of the file.
        else {
            if (maxPageNumber <= totalTreePages) {
                maxPageNumber++;
            }
            totalTreePages++;
            index = conf.pageSize * (maxPageNumber + 1);
            return(index);
        }
    }

    /**
     * Commit the page count and the max offset in the file
     *
     * @param conf B+ configuration reference
     * @throws IOException is thrown when an I/O operation fails
     */
    private void updatePageIndexCounts(BPlusConfiguration conf) throws IOException {
        treeFile.seek(conf.getPageCountOffset());
        treeFile.writeLong(totalTreePages);
        treeFile.writeLong(maxPageNumber);
    }

    /**
     * Return the current configuration
     *
     * @return the configuration reference
     */
    @SuppressWarnings("unused")
    public BPlusConfiguration getTreeConfiguration()
        {return(conf);}

    /**
     * Prints the current configuration to stdout.
     */
    public void printCurrentConfiguration()
        {conf.printConfiguration();}

    /**
     * Returns the total number of pages currently in use
     * @return return the total number of pages
     */
    @SuppressWarnings("unused")
    public long getTotalTreePages()
        {return totalTreePages;}

    /**
     * Max index used (indicates the filesize)
     * @return max number of pages that the file has
     */
    @SuppressWarnings("unused")
    public long getMaxPageNumber() {
        return maxPageNumber;
    }

    /**
     * Handy method to initialize common variables
     */
    private void initializeCommon() {
        this.totalTreePages = 0L;
        this.maxPageNumber = 0L;
        this.deleteIterations = 0;
        this.firstPoolNextPointer = -1L;
        this.freeSlotPool = new LinkedList<>();
        this.lookupPagesPool = new LinkedList<>();
    }

    /**
     * Delete the page
     *
     * @param pageIndex page index to remove
     * @param sort sort free sort pool?
     */
    private void deletePage(long pageIndex, boolean sort)
            throws IOException, MiniDBException {
        this.freeSlotPool.add(pageIndex);
        this.totalTreePages--;
        this.deleteIterations++;

        if(sort || isTimeForConditioning()) {
            this.deleteIterations = 0;
            commitLookupPage();
        }
    }

    /**
     * Check if we have to condition the file
     *
     * @return if it's time for conditioning
     */
    private boolean isTimeForConditioning()
        {return(this.deleteIterations == conf.conditionThreshold);}

    /**
     * Helper to print the node
     *
     * @param index index of the node to read and print.
     * @throws IOException is thrown when an I/O operation fails
     */
    @SuppressWarnings("unused")
    public void printNodeAt(long index) throws IOException {
        TreeNode t = readNode(index);
        t.printNode(conf);

        if(t.isInternalNode()) {
            TreeInternalNode t2 = (TreeInternalNode)t;
            long ptr;
            for(int i = 0; i < t2.getCurrentCapacity()+1; i++) {
                ptr = t2.getPointerAt(i);
                if(ptr < 0) {break;}
                printNodeAt(ptr);
            }
        }
    }

    /**
     * Condition the given string to match the entry size.
     *
     *  -- in case of length being greater than entry size, it is trimmed
     *  -- in case of length being less than entry size, it is appended with
     *      whitespaces.
     *
     * @param s string to condition
     * @return the conditioned string
     */
    private String conditionString(String s) {
        if(s == null) {
            s = " ";
            //System.out.println("Cannot have a null string");
        }

        if(s.length() > conf.entrySize) {
            System.out.println("Satellite length can't exceed " +
                    conf.entrySize + " trimming...");
            s = s.substring(0, conf.entrySize);
        } else if(s.length() < conf.entrySize) {
            //System.out.println("Satellite length can't be less than" +
            //        conf.getEntrySize() + ", adding whitespaces to make up");
            int add = conf.entrySize - s.length();
            for(int i = 0; i < add; i++) {s = s + " ";}
        }
        return(s);
    }

    private static String padString(String arg, int nBytes) throws MiniDBException
    {
        int size = arg.getBytes(StandardCharsets.UTF_8).length;
        if(size > nBytes)
        {
            throw new MiniDBException(String.format(MiniDBException.StringLengthOverflow, nBytes, arg, size));
        }
        if(size == nBytes)
        {
            return arg;
        }
        return arg + new String(new char[nBytes - size]).replace('\0', ' ');
    }

    private Object[] padKey(Object[] key) throws MiniDBException
    {
        for (Integer i : conf.strColIndexes)
        {
            key[i] = padString((String)key[i], conf.sizes[i]);
        }
        return key;
    }

    private enum Rank {Pred, Succ, PlusOne, Exact}

    public class SearchResult {

        public TreeLeaf leafLoc;               // the leaf which our (K, V) might reside
        public final int index;                // index where first key is <= our requested key
        public final boolean found;            // we found the requested key?

        /**
         * Constructor for unique queries, hence feed it all the above information
         *
         * @param leaf the leaf which our (K, V) might reside
         * @param index index where first key is <= our requested key
         * @param found we found the requested key?
         */
        public SearchResult(TreeLeaf leaf, int index, boolean found) {
            this.leafLoc = leaf;
            this.index = index;
            this.found = found;
        }
    }
}
