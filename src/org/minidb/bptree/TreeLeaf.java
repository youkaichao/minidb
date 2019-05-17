package org.minidb.bptree;

import org.minidb.exception.MiniDBException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

/**
 * Class for our Tree leafs
 *
 */
@SuppressWarnings("unused")
class TreeLeaf extends TreeNode {
    private long nextPagePointer;           // pointer to next leaf in the list
    private long prevPagePointer;           // pointer to prev leaf in the list
    public LinkedList<Long> valueList;   // satellite data list
    private LinkedList<Long> overflowList;  // overflow pointer list

    /**
     * Constructor for our Internal node
     *
     * @param nextPagePointer the next leaf pointer
     * @param prevPagePointer the previous leaf pointer
     * @param nodeType the node type
     * @param pageIndex the index of the page
     */
    TreeLeaf(long nextPagePointer, long prevPagePointer,
             TreeNodeType nodeType, long pageIndex) {
        super(nodeType, pageIndex);
        if(nodeType == TreeNodeType.TREE_ROOT_LEAF && nextPagePointer > 0)
            {throw new IllegalArgumentException("Can't have leaf " +
                    "root with non-null next pointer");}
        this.nextPagePointer = nextPagePointer;
        this.prevPagePointer = prevPagePointer;
        this.overflowList = new LinkedList<>();
        this.valueList = new LinkedList<>();
    }

    void addToOverflowList(int index, long value)
        {overflowList.add(index, value);}

    void addLastToOverflowList(long value)
        {overflowList.addLast(value);}

    void addLastToValueList(long value)
        {valueList.addLast(value);}

    long getOverflowPointerAt(int index)
        {return overflowList.get(index);}

    void pushToOverflowList(long overflowPointer)
        {overflowList.push(overflowPointer);}

    long popOverflowPointer()
        {return(overflowList.pop());}

    void setOverflowPointerAt(int index, long value)
        {overflowList.set(index, value);}

    long removeLastOverflowPointer()
        {return(overflowList.removeLast());}

    long getLastOverflowPointer()
        {return(overflowList.getLast());}

    void addToValueList(int index, long value)
        {valueList.add(index, value);}

    long getValueAt(int index)
        {return valueList.get(index);}

    void pushToValueList(long value)
        {valueList.push(value);}

    long popValue()
        {return valueList.pop();}

    long removeLastValue()
        {return  valueList.removeLast();}

    long getNextPagePointer()
        {return(nextPagePointer);}

    void setNextPagePointer(long next)
        {nextPagePointer = next;}

    long getPrevPagePointer()
        {return prevPagePointer;}

    void setPrevPagePointer(long prevPagePointer) {
        this.prevPagePointer = prevPagePointer;
    }

    long removeEntryAt(int index, BPlusConfiguration conf)
            throws MiniDBException {
        keyArray.remove(index);
        overflowList.remove(index);
        long s = valueList.remove(index);
        decrementCapacity(conf);
        return(s);
    }

    /**
     * @param r pointer to *opened* B+ tree file
     * @param conf configuration parameter
     * @throws IOException is thrown when an I/O operation fails
     */
    @Override
    public void writeNode(RandomAccessFile r, BPlusConfiguration conf)
            throws IOException {

        // update root index in the file
        if(this.isRoot()) {
            r.seek(conf.headerSize-16L);
            r.writeLong(getPageIndex());
        }

        // account for the header page as well.
        r.seek(getPageIndex());

        // now write the node type
        r.writeShort(getPageType());

        // write the prev pointer
        r.writeLong(prevPagePointer);

        // write the next pointer
        r.writeLong(nextPagePointer);

        // then write the current capacity
        r.writeInt(getCurrentCapacity());

        // now write the Key/Value pairs
        for(int i = 0; i < getCurrentCapacity(); i++) {
            conf.writeKey(r, getKeyAt(i));
            r.writeLong(valueList.get(i));
            r.writeLong(getOverflowPointerAt(i));
        }
    }
}
