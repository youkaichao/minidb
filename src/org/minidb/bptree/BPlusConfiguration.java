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
public class BPlusConfiguration {

    public int pageSize;           // page size (in bytes)
    public int keySize;            // key size (in bytes)
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
    public final ArrayList<Type> types; // Keys may contain multiple columns. `types` tracks the type for each column
    // use Integer/Float etc for primitive types
    public final ArrayList<Integer> sizes; // size of each key type (in bytes)
    public final ArrayList<Integer> colIDs; // ID of each column
    public final ArrayList<Integer> strColLocalId; // ID of string columns (in local Id, not the id from the whole table)

    /**
     * @param pageSize page size (default is 1024 bytes)
     * @param valueSize satellite data (default is 8 bytes)
     * @param trimFileThreshold threshold to perform file conditioning (default is 1000)
     */
    public BPlusConfiguration(int pageSize, int valueSize, ArrayList<Type> types, ArrayList<Integer> sizes, ArrayList<Integer> colIDs,
                              boolean unique, int trimFileThreshold)
            throws MiniDBException {
        this.unique = unique;
        this.colIDs = colIDs;
        this.types = types;
        for(Type each : types)
        {
            if(each != Integer.class && each != Long.class && each != Float.class && each != Double.class && each != String.class)
            {
                throw new MiniDBException(String.format(MiniDBException.UnknownColumnType, each.getTypeName()));
            }
        }
        strColLocalId = new ArrayList<Integer>();
        for(int i = 0; i < types.size(); ++i)
        {
            if(types.get(i) == String.class)
            {
                strColLocalId.add(i);
            }
        }
        this.sizes = sizes;
        keySize = 0;
        for(int each : sizes)
        {
            keySize += each;
        }
        this.pageSize = pageSize;   // page size (in bytes)
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

    /*
    compare function with short-cut evaluation.
    * */
    private boolean compare(ArrayList<Object> key1, ArrayList<Object> key2, BiFunction<Integer, Integer, Boolean> func, boolean finalValue)
    {
        for(int j = 0; j < types.size(); ++j)
        {
            if(types.get(j) == Integer.class)
            {
                int ans = Integer.compare((Integer) key1.get(j), (Integer) key2.get(j));
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == Long.class)
            {
                int ans = Long.compare((Long) key1.get(j), (Long) key2.get(j));
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == Float.class)
            {
                int ans = Float.compare((Float) key1.get(j), (Float) key2.get(j));
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == Double.class)
            {
                int ans = Double.compare((Double) key1.get(j), (Double) key2.get(j));
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == String.class)
            {
                int ans = ((String) key1.get(j)).compareTo((String) key2.get(j));
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }
        }
        // every objects are equal
        return finalValue;
    }

    // > op
    public boolean gt(ArrayList<Object> key1, ArrayList<Object> key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x > y, false);
    }

    // >= op
    public boolean ge(ArrayList<Object> key1, ArrayList<Object> key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x > y, true);
    }

    // < op
    public boolean lt(ArrayList<Object> key1, ArrayList<Object> key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x < y, false);
    }
    // <= op
    public boolean le(ArrayList<Object> key1, ArrayList<Object> key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x < y, true);
    }

    // != op
    public boolean neq(ArrayList<Object> key1, ArrayList<Object> key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> !x.equals(y), false);
    }

    // == op
    public boolean eq(ArrayList<Object> key1, ArrayList<Object> key2)
    {
        return !neq(key1, key2);
    }


    public void writeKey(RandomAccessFile r, ArrayList<Object> key) throws IOException
    {
        for(int j = 0; j < types.size(); ++j)
        {
            if(types.get(j) == Integer.class)
            {
                r.writeInt((Integer) key.get(j));
            }else if(types.get(j) == Long.class)
            {
                r.writeLong((Long) key.get(j));
            }else if(types.get(j) == Float.class)
            {
                r.writeFloat((Float) key.get(j));
            }else if(types.get(j) == Double.class)
            {
                r.writeDouble((Double) key.get(j));
            }else if(types.get(j) == String.class)
            {
                r.write(((String) key.get(j)).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    public ArrayList<Object> readKey(RandomAccessFile r) throws IOException
    {
        ArrayList<Object> key = new ArrayList<>(Arrays.asList(new Object[types.size()]));
        for(int j = 0; j < types.size(); ++j)
        {
            if(types.get(j) == Integer.class)
            {
                key.set(j, r.readInt());
            }else if(types.get(j) == Long.class)
            {
                key.set(j, r.readLong());
            }else if(types.get(j) == Float.class)
            {
                key.set(j, r.readFloat());
            }else if(types.get(j) == Double.class)
            {
                key.set(j, r.readDouble());
            }else if(types.get(j) == String.class)
            {
                //TODO possible not efficient. buffer is copied into the string?
                byte[] buffer = new byte[sizes.get(j)];
                r.read(buffer, 0, sizes.get(j));
                key.set(j, new String(buffer, StandardCharsets.UTF_8));
            }
        }
        return key;
    }

    public void printKey(ArrayList<Object> key)
    {
        System.out.println(keyToString(key));
    }

    public String keyToString(ArrayList<Object> key)
    {
        StringBuilder ans = new StringBuilder();
        ans.append("[");
        for(int i = 0; i < types.size(); ++i)
        {
            if(types.get(i) == Integer.class)
            {
                ans.append((Integer) key.get(i));
                ans.append(' ');
            }else if(types.get(i) == Long.class)
            {
                ans.append((Long) key.get(i));
                ans.append(' ');
            }else if(types.get(i) == Float.class)
            {
                ans.append((Float) key.get(i));
                ans.append(' ');
            }else if(types.get(i) == Double.class)
            {
                ans.append((Double) key.get(i));
                ans.append(' ');
            }else if(types.get(i) == String.class)
            {
                ans.append((String) key.get(i));
                ans.append(' ');
            }
        }
        ans.append("]");
        return ans.toString();
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

    public ArrayList<Object> padKey(ArrayList<Object> key) throws MiniDBException
    {
        for (Integer i : strColLocalId)
        {
            key.set(i, padString((String) key.get(i), sizes.get(i)));
        }
        return key;
    }
}
