package org.minidb.grammar;

import org.minidb.relation.RelationMeta;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ResultTable implements Serializable {

    public RelationMeta meta;
    public ArrayList<ArrayList<Object>> data;

    public ResultTable() {
        data = new ArrayList<ArrayList<Object>>();
    }

    public ResultTable(ArrayList<String> colnames, ArrayList<ArrayList<Object>> values)
    {
        meta = new RelationMeta();
        meta.ncols = colnames.size();
        meta.colnames = colnames;
        this.data = values;
    }

    public static ResultTable getSimpleTable(String name, Collection<Object> values)
    {
        RelationMeta meta = new RelationMeta();
        meta.ncols = 1;
        meta.colnames = new ArrayList<>(Arrays.asList(name));
        ResultTable table = new ResultTable();
        table.meta = meta;
        for(Object o : values)
        {
            table.data.add(new ArrayList<>(Arrays.asList(o)));
        }
        return table;
    }

    public static ResultTable getSimpleMessageTable(String msg)
    {
        RelationMeta meta = new RelationMeta();
        meta.ncols = 1;
        meta.colnames = new ArrayList<>(Arrays.asList("message"));
        ResultTable table = new ResultTable();
        table.meta = meta;
        table.data.add(new ArrayList<Object>(Arrays.asList(msg)));
        return table;
    }
}
