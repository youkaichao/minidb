package org.minidb.bptree;

import org.minidb.exception.MiniDBException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainDataConfiguration extends Configuration {
    public int nValidPointerInFreePage;
    public MainDataConfiguration(ArrayList<Type> types, ArrayList<Integer> sizes, ArrayList<Integer> colIDs) throws MiniDBException {
        super(0, types, sizes, colIDs);
        // pad keysize to multiples of 8 (size of long)
        int tmpSize = keySize;
        if(tmpSize % 8 != 0)
        {
            tmpSize += 8 - (tmpSize % 8);
        }
        tmpSize += 8; // space for `RowID`
        pageSize = Math.max(24, tmpSize);
        nValidPointerInFreePage = pageSize / 8 - 1;
    }
}
