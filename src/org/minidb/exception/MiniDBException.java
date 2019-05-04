package org.minidb.exception;

public class MiniDBException extends Exception {
    public MiniDBException(String m)
    {super(m);}
    public static String StringLengthOverflow = "String Length Exceeds the limits! The limit is %d (bytes) " +
            "and the string (%s) has a length of %d.";
    public static String DuplicateValue = "Duplicate value (%s) for the same key (%s)!";
    public static String UnknownColumnType = "Unknown column type (%s). " +
            "Only Integer, Long, Float, Double and String are supported!";
    public static String InvalidBPTreeState = "Internal error! Invalid B+ tree state.";
}
