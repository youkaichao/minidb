package org.minidb.bplus.bptree;

/**
 * Wrapper to conveniently return the (Key, Value) pair
 * without having to resort to "weird" solutions.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class KeyValueWrapper {

    private final Object[] key;           // key
    private final long value;       // value

    /**
     * This is the only constructor... as we only
     * need to set them
     * @param key the key of (K, V) pair
     * @param value the value of the (K, V) pair
     */
    public KeyValueWrapper(Object[] key, long value) {
        this.key = key;
        this.value = value;
    }

    public Object[] getKey() {
        return key;
    }

    public long getValue() {
        return value;
    }


}
