package org.minidb.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;


@SuppressWarnings("unused")
class TreeOverflow extends TreeNode {


    public final LinkedList<Long> valueList;
    private long nextPagePointer;
    private long prevPagePointer;

    /**
     * Constructor which takes into the node type as well as the
     * page index
     *
     * @param nextPagePointer the next overflow pointer
     * @param prevPagePointer the previous leaf or overflow pointer
     * @param pageIndex the page index in the file
     */
    TreeOverflow(long nextPagePointer, long prevPagePointer,
                 long pageIndex) {
        super(TreeNodeType.TREE_LEAF_OVERFLOW, pageIndex);
        valueList = new LinkedList<>();
        this.nextPagePointer = nextPagePointer;
        this.prevPagePointer = prevPagePointer;
    }

    void pushToValueList(long value)
        {valueList.push(value);}

    void addToValueList(int index, long value)
        {valueList.add(index, value);}

    long getValueAt(int index)
        {return valueList.get(index);}

    long getNextPagePointer()
        {return(nextPagePointer);}

    void setNextPagePointer(long next)
        {nextPagePointer = next;}

    private long getPrevPagePointer()
        {return prevPagePointer;}

    void setPrevPagePointer(long prevPagePointer)
        {this.prevPagePointer = prevPagePointer;}


    /**
     * @param r pointer to *opened* B+ tree file
     * @throws IOException is thrown when an I/O operation fails
     */
    @Override
    public void writeNode(RandomAccessFile r, BPlusConfiguration conf)
            throws IOException {
        // account for the header page as well.
        r.seek(getPageIndex());

        byte[] buffer = new byte[conf.pageSize];
        ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
        // now write the node type
        bbuffer.putShort(getPageType());

        // write the prev pointer
        bbuffer.putLong(prevPagePointer);

        // write the next pointer
        bbuffer.putLong(nextPagePointer);

        // then write the current capacity
        bbuffer.putInt(getCurrentCapacity());

        conf.writeKey(bbuffer, getKeyAt(0));

        // now write the values
        for(int i = 0; i < getCurrentCapacity(); i++)
            {bbuffer.putLong(valueList.get(i));}
        r.write(buffer);
    }
}
