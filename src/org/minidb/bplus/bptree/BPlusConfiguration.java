package org.minidb.bplus.bptree;

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
    public boolean unique;               // whether one key can have multiple values
    /**
     *
     *  Default constructor which initializes all
     *  settings to the predefined defaults.
     *
     */
    public BPlusConfiguration() {
        basicParams(1024, 8, 8);
        initializeCommon(pageSize, keySize, entrySize, 1000);
    }

    /**
     * Overloaded constructor allows page size adjustments
     *
     * @param pageSize page size (in bytes)
     */
    public BPlusConfiguration(int pageSize) {
        basicParams(pageSize, 8, 8);
        initializeCommon(pageSize, keySize, entrySize, 1000);
    }

    /**
     * Overloaded constructor
     *
     * @param pageSize page size (default is 1024 bytes)
     * @param keySize key size (default is long [8 bytes])
     * @param entrySize satellite data (default is 8 bytes)
     */
    public BPlusConfiguration(int pageSize, int keySize,
                              int entrySize) {
        basicParams(pageSize, keySize, entrySize);
        initializeCommon(pageSize, keySize, entrySize, 1000);
    }

    /**
     * Overloaded constructor
     *
     * @param pageSize           page size (default is 1024 bytes)
     * @param keySize            key size (default is long [8 bytes])
     * @param entrySize          satellite data (default is 20 bytes)
     * @param conditionThreshold threshold to perform file conditioning
     */
    @SuppressWarnings("unused")
    public BPlusConfiguration(int pageSize, int keySize,
                              int entrySize, int conditionThreshold) {
        basicParams(pageSize, keySize, entrySize);
        initializeCommon(pageSize, keySize, entrySize, conditionThreshold);
    }

    /**
     * Set up the basic parameters of the tree
     *
     * @param pageSize  page size (default is 1024 bytes)
     * @param keySize   key size (default is long [8 bytes])
     * @param entrySize satellite data (default is 20 bytes)
     */
    private void basicParams(int pageSize, int keySize, int entrySize) {
        this.pageSize = pageSize;   // page size (in bytes)
        this.entrySize = entrySize; // entry size (in bytes)
        this.keySize = keySize;     // key size (in bytes)
    }

    /**
     * Common method to initialize constructor parameters
     *
     * @param pageSize page size (default is 1024 bytes)
     * @param keySize key size (default is long [8 bytes])
     * @param entrySize satellite data (default is 20 bytes)
     * @param conditionThreshold the number of iterations before file conditioning
     */
    private void initializeCommon(int pageSize, int keySize,
                                  int entrySize, int conditionThreshold) {
        this.headerSize =                                   // header size in bytes
                (Integer.SIZE * 4 + 4 * Long.SIZE) / 8;
        this.internalNodeHeaderSize = (Short.SIZE + Integer.SIZE) / 8; // 6 bytes
        this.leafHeaderSize = (Short.SIZE + 2 * Long.SIZE + Integer.SIZE) / 8; // 22 bytes
        this.lookupOverflowHeaderSize = 14;
        this.lookupPageSize = pageSize - headerSize;        // lookup page size
        this.conditionThreshold = conditionThreshold;       // iterations for conditioning
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
}
