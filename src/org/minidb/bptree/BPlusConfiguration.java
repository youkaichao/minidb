package org.minidb.bptree;

import org.minidb.exception.MiniDBException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

/**
 *
 * Class that stores all of the configuration parameters for our B+ Tree.
 *
 * You can view a description on all of the parameters below...
 *
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class BPlusConfiguration extends Configuration {

    public int valueSize;          // entry size (in bytes)
    public int headerSize;               // header size (in bytes)

    public int leafHeaderSize;           // leaf node header size (in bytes)
    public int internalNodeHeaderSize;   // internal node header size (in bytes)
    public int overflowNodeHeaderSize;   // overflow node header size
    public int freePoolNodeHeaderSize; // free pool page header size

    public int leafNodeDegree;           // leaf node degree
    public int treeDegree;               // tree degree (internal node degree)
    public int overflowPageDegree;       // overflow page degree
    public int freePoolNodeDegree; // lookup overflow page degree

    public int trimFileThreshold;       // iterations to trim the file
    public boolean unique;               // whether one key can have multiple values. This corresponds to unique index.

    public BPlusConfiguration(int pageSize, int valueSize, ArrayList<Type> types, ArrayList<Integer> sizes, ArrayList<Integer> colIDs,
                              boolean unique, int trimFileThreshold)
            throws MiniDBException {
        super(pageSize, types, sizes, colIDs);
        this.unique = unique;
        this.valueSize = valueSize; // entry size (in bytes)
        this.trimFileThreshold = trimFileThreshold;       // iterations for conditioning

        this.headerSize = (Integer.SIZE * 3 + 4 * Long.SIZE) / 8;          // header size in bytes

        this.leafHeaderSize = (Short.SIZE + 2 * Long.SIZE + Integer.SIZE) / 8; // 22 bytes
        this.internalNodeHeaderSize = (Short.SIZE + Integer.SIZE) / 8; // 6 bytes
        this.overflowNodeHeaderSize = (Short.SIZE + 2 * Long.SIZE + Integer.SIZE) / 8 + keySize; // 22 + keySize bytes
        this.freePoolNodeHeaderSize = (Short.SIZE + Long.SIZE + Integer.SIZE) / 8; // 14 bytes

        // now calculate the degree

        // data: key and a value and an overflow pointer
        this.leafNodeDegree = calculateDegree(keySize + valueSize + Long.SIZE / 8, leafHeaderSize);
        // data: key and a pointer
        this.treeDegree = (pageSize - internalNodeHeaderSize - Long.SIZE / 8) / (keySize + Long.SIZE / 8);
        this.overflowPageDegree = calculateDegree(valueSize, overflowNodeHeaderSize);
        this.freePoolNodeDegree = calculateDegree(Long.SIZE / 8, freePoolNodeHeaderSize);
        checkDegreeValidity();
    }

    private int calculateDegree(int elementSize, int elementHeaderSize)
        {return (pageSize-elementHeaderSize)/elementSize;}

    /**
     *
     * Little function that checks if we have any degree < 2 (which is not allowed)
     *
     */
    private void checkDegreeValidity() {
        if (treeDegree < 2 || leafNodeDegree < 2 ||
                overflowPageDegree < 2 || freePoolNodeDegree < 2)
            {throw new IllegalArgumentException("Can't have a degree < 2");}
    }

    public int getMaxInternalNodeCapacity()
        {return treeDegree;}

    public int getMinInternalNodeCapacity()
        {return (treeDegree-1) / 2;}

    public int getMaxLeafNodeCapacity()
        {return leafNodeDegree;}

    public int getMinLeafNodeCapacity()
        {return (leafNodeDegree-1) / 2;}

    public int getMaxOverflowNodeCapacity() {
        return overflowPageDegree;
    }

    public int getFreePoolNodeDegree()
        {return freePoolNodeDegree;}

    public long getFreePoolNodeOffset()
        {return freePoolNodeHeaderSize;}

    public int getPageCountOffset() {
        return 12;
    }

}
