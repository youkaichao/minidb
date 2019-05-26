package org.minidb.bptree;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

@SuppressWarnings("unused")
class TreeFreePoolNode extends TreeNode {

    private long next; // next pointer

    /**
     * Constructor which takes into the node type as well as the
     * page index
     *
     * @param pageIndex the page index in the file
     */
    TreeFreePoolNode(long pageIndex, long nextPointer) {
        super(TreeNodeType.TREE_FREE_POOL, pageIndex);
        this.next = nextPointer;
    }

    /**
     * @param r     an *already* open pointer which points to our B+ Tree file
     * @param conf  B+ Tree configuration
     * @throws IOException is thrown when an I/O operation fails
     */
    @Override
    public void writeNode(RandomAccessFile r,
                          BPlusConfiguration conf)
            throws IOException {

        // account for the header page as well
        r.seek(getPageIndex());

        byte[] buffer = new byte[conf.pageSize];
        ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);

        // write the node type
        bbuffer.putShort(getPageType());

        // write the next pointer
        bbuffer.putLong(next);

        // write current capacity
        bbuffer.putInt(getCurrentCapacity());

        // now write the index values
        for (int i = 0; i < getCurrentCapacity(); i++) {
            bbuffer.putLong((Long) getKeyAt(i).get(0));
        }
        r.write(buffer);

    }


    /**
     * Get the next pointer of the node
     *
     * @return the next pointer value
     */
    long getNextPointer() {
        return next;
    }

    /**
     * Set the next pointer of the node
     *
     * @param nextPointer the new next pointer
     */
    public void setNextPointer(long nextPointer) {
        this.next = nextPointer;
    }
}
