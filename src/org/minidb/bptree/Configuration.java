package org.minidb.bptree;

import org.minidb.exception.MiniDBException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Configuration {
    public int pageSize;           // page size (in bytes)
    public int keySize;            // key size (in bytes)
    public ArrayList<Type> types; // Keys may contain multiple columns. `types` tracks the type for each column
    // use Integer/Float etc for primitive types
    public ArrayList<Integer> sizes; // size of each key type (in bytes)
    public ArrayList<Integer> colIDs; // ID of each column
    public ArrayList<Integer> strColLocalId; // ID of string columns (in local Id, not the id from the whole table)

    public Configuration(int pageSize, ArrayList<Type> types, ArrayList<Integer> sizes, ArrayList<Integer> colIDs) throws MiniDBException {
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
    }

    /*compare function with short-cut evaluation.**/
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


    public void writeKey(ByteBuffer r, ArrayList<Object> key) throws IOException
    {
        padKey(key);
        for(int j = 0; j < types.size(); ++j)
        {
            if(types.get(j) == Integer.class)
            {
                r.putInt((Integer) key.get(j));
            }else if(types.get(j) == Long.class)
            {
                r.putLong((Long) key.get(j));
            }else if(types.get(j) == Float.class)
            {
                r.putFloat((Float) key.get(j));
            }else if(types.get(j) == Double.class)
            {
                r.putDouble((Double) key.get(j));
            }else if(types.get(j) == String.class)
            {
                r.put(((String) key.get(j)).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    public ArrayList<Object> readKey(ByteBuffer r) throws IOException
    {
        ArrayList<Object> key = new ArrayList<>(Arrays.asList(new Object[types.size()]));
        for(int j = 0; j < types.size(); ++j)
        {
            if(types.get(j) == Integer.class)
            {
                key.set(j, r.getInt());
            }else if(types.get(j) == Long.class)
            {
                key.set(j, r.getLong());
            }else if(types.get(j) == Float.class)
            {
                key.set(j, r.getFloat());
            }else if(types.get(j) == Double.class)
            {
                key.set(j, r.getDouble());
            }else if(types.get(j) == String.class)
            {
                //TODO possible not efficient. buffer is copied into the string?
                byte[] buffer = new byte[sizes.get(j)];
                r.get(buffer, 0, sizes.get(j));
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

    public static String padString(String arg, int nBytes) throws MiniDBException
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
