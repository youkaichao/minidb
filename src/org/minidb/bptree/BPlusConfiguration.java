package org.minidb.bptree;

import org.minidb.exception.MiniDBException;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public int entrySize;          // entry size (in bytes)
    public int treeDegree;               // tree degree (internal node degree)
    public int headerSize;               // header size (in bytes)
    public int leafHeaderSize;           // leaf node header size (in bytes)
    public int internalNodeHeaderSize;   // internal node header size (in bytes)
    public int lookupOverflowHeaderSize; // lookup overflow page header size
    public int lookupOverflowPageDegree; // lookup overflow page degree
    public int leafNodeDegree;           // leaf node degree
    public int overflowPageDegree;       // overflow page degree
    public int lookupPageSize;           // look up page size
    public int conditionThreshold;       // iterations to perform conditioning
    public boolean unique;               // whether one key can have multiple values. This corresponds to unique index.
    public final ArrayList<Type> types; // Keys may contain multiple columns. `types` tracks the type for each column
    // use Integer/Float etc for primitive types
    public final ArrayList<Integer> sizes; // size of each key type (in bytes)
    public final ArrayList<Integer> colIDs; // ID of each column
    public final ArrayList<Integer> strColLocalId; // ID of string columns (in local Id, not the id from the whole table)

    /**
     * @param pageSize page size (default is 1024 bytes)
     * @param entrySize satellite data (default is 8 bytes)
     * @param conditionThreshold threshold to perform file conditioning (default is 1000)
     */
    public BPlusConfiguration(int pageSize, int entrySize, ArrayList<Type> types, ArrayList<Integer> sizes, ArrayList<Integer> colIDs,
                              boolean unique, int conditionThreshold)
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
        this.entrySize = entrySize; // entry size (in bytes)
        this.conditionThreshold = conditionThreshold;       // iterations for conditioning
        this.headerSize =                                   // header size in bytes
                (Integer.SIZE * 4 + 4 * Long.SIZE) / 8;
        this.internalNodeHeaderSize = (Short.SIZE + Integer.SIZE) / 8; // 6 bytes
        this.leafHeaderSize = (Short.SIZE + 2 * Long.SIZE + Integer.SIZE) / 8; // 22 bytes
        this.lookupOverflowHeaderSize = 14;
        this.lookupPageSize = pageSize - headerSize;        // lookup page size
        // now calculate the tree degree
        this.treeDegree = calculateDegree(2*keySize, internalNodeHeaderSize);
        // leaf & overflow have the same header size.
        this.leafNodeDegree = calculateDegree((2*keySize)+entrySize, leafHeaderSize);
        this.overflowPageDegree = calculateDegree(entrySize, leafHeaderSize);
        this.lookupOverflowPageDegree = calculateDegree(keySize,
                lookupOverflowHeaderSize);
        checkDegreeValidity();
    }

    /**
     * calculates the degree of a node (internal/leaf)
     *
     * @param elementSize the node element size (in bytes)
     * @param elementHeaderSize the node header size (in bytes)
     * @return the node degree
     */
    private int calculateDegree(int elementSize, int elementHeaderSize)
        {return((int) (((pageSize-elementHeaderSize)/(2.0*elementSize))/*+0.5*/));}

    /**
     *
     * Little function that checks if we have any degree < 2 (which is not allowed)
     *
     */
    private void checkDegreeValidity() {
        if (treeDegree < 2 || leafNodeDegree < 2 ||
                overflowPageDegree < 2 || lookupOverflowPageDegree < 2)
            {throw new IllegalArgumentException("Can't have a degree < 2");}
    }

    public int getFirstLookupPageElements() {
        return lookupPageSize / keySize;
    }

    public int getMaxInternalNodeCapacity()
        {return((2*treeDegree) - 1);}

    public int getMaxLeafNodeCapacity()
        {return((2*leafNodeDegree) - 1);}

    public int getMaxOverflowNodeCapacity() {
        return ((2 * overflowPageDegree) - 1);
    }

    public int getMaxLookupPageOverflowCapacity() {
        return ((2 * lookupOverflowPageDegree) - 1);
    }

    public int getMinLeafNodeCapacity()
        {return(leafNodeDegree-1);}

    public int getMinInternalNodeCapacity()
        {return(treeDegree-1);}

    public int getLookupPageDegree()
        {return(pageSize/keySize);}

    public long getLookupPageOffset()
        {return(pageSize-lookupPageSize);}

    public int getPageCountOffset() {
        return (headerSize - 16);
    }

    public void printConfiguration() {
        System.out.println("\n\nPrinting B+ Tree configuration\n");
        System.out.println("Page size: " + pageSize + " (in bytes)");
        System.out.println("Key size: " + keySize + " (in bytes)");
        System.out.println("Entry size: " + entrySize + " (in bytes)");
        System.out.println("File header size: " + headerSize + " (in bytes)");
        System.out.println("Lookup space size: " + lookupPageSize +
                " (in bytes)");
        System.out.println("\nInternal Node Degree: " +
                treeDegree +
                "\n\t Min cap: " + getMinInternalNodeCapacity() +
                "\n\t Max cap: " + getMaxInternalNodeCapacity() +
                "\n\t Total header bytes: " + internalNodeHeaderSize);

        System.out.println("\nLeaf Node Degree: " +
                leafNodeDegree +
                "\n\t Min cap: " + getMinLeafNodeCapacity() +
                "\n\t Max cap: " + getMaxLeafNodeCapacity() +
                "\n\t Total header bytes: " + leafHeaderSize);

        System.out.println("\nOverflow page Degree: " +
                overflowPageDegree +
                "\n\tExpected cap: " + getMaxOverflowNodeCapacity());

        System.out.println("\nLookup page overflow Degree" +
                overflowPageDegree +
                "\n\tExpected cap: " + getMaxInternalNodeCapacity());
    }

    /*
    compare function with short-cut evaluation.
    * */
    private boolean compare(Object[] key1, Object[] key2, BiFunction<Integer, Integer, Boolean> func, boolean finalValue)
    {
        for(int j = 0; j < types.size(); ++j)
        {
            if(types.get(j) == Integer.class)
            {
                int ans = Integer.compare((Integer)key1[j], (Integer)key2[j]);
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == Long.class)
            {
                int ans = Long.compare((Long)key1[j], (Long)key2[j]);
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == Float.class)
            {
                int ans = Float.compare((Float)key1[j], (Float)key2[j]);
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == Double.class)
            {
                int ans = Double.compare((Double)key1[j], (Double)key2[j]);
                if(ans == 0)
                {
                    continue;
                }
                return func.apply(ans, 0);
            }else if(types.get(j) == String.class)
            {
                int ans = ((String)key1[j]).compareTo((String)key2[j]);
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
    public boolean gt(Object[] key1, Object[] key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x > y, false);
    }

    // >= op
    public boolean ge(Object[] key1, Object[] key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x > y, true);
    }

    // < op
    public boolean lt(Object[] key1, Object[] key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x < y, false);
    }
    // <= op
    public boolean le(Object[] key1, Object[] key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> x < y, true);
    }

    // != op
    public boolean neq(Object[] key1, Object[] key2)
    {
        return compare(key1, key2, (Integer x, Integer y) -> !x.equals(y), false);
    }

    // == op
    public boolean eq(Object[] key1, Object[] key2)
    {
        return !neq(key1, key2);
    }
}
