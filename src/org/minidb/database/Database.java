package org.minidb.database;

import org.minidb.exception.MiniDBException;
import org.minidb.relation.Relation;
import org.minidb.utils.Misc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Database {
    public String getDirectory() {
        return directory;
    }

    String directory; // the directory to store the database
    ArrayList<String> relations; // the relations in the database
    HashMap<String, Relation> tableNameToTable;

    public Database()
    {
        directory = "";
        relations = new ArrayList<>();
        tableNameToTable = new HashMap<String, Relation>();
    }

    public Database(String directory) {
        this.directory = directory;
        relations = new ArrayList<>();
        tableNameToTable = new HashMap<String, Relation>();
    }

    public void create() throws MiniDBException {
        File file = new File(directory);
        if(file.exists()) throw new MiniDBException(String.format("Database %s already exists!", directory));
        if(!file.mkdir()) throw new MiniDBException(String.format("Failed to create the database %s. Cannot create directory.", directory));
    }

    public void resume() throws MiniDBException, IOException, ClassNotFoundException {
        File file = new File(directory);
        if(!file.exists()) throw new MiniDBException(String.format("Database %s disappears!", directory));
        File[] directories = file.listFiles(File::isDirectory);
        if(directories == null)
            return;
        for(File each : directories)
        {
            Relation relation = new Relation();
            relation.directory = each.getAbsolutePath();
            relation.resume();
            tableNameToTable.put(each.getName(), relation);
        }
    }

    public void close() throws IOException, MiniDBException {
        for(Relation relation : tableNameToTable.values())
        {
            relation.close();
        }
    }

    public void addRelation(String name, Relation relation) throws MiniDBException, IOException {
        if(tableNameToTable.containsKey(name))
            throw new MiniDBException(String.format("Database %s already contains a table named %s!", directory, name));
        String path = Paths.get(directory, name).toString();
        if(! new File(path).mkdir())
            throw new MiniDBException(String.format("Failed to create the table %s. Cannot create directory.", name));
        relation.directory = path;
        try{
            relation.create();
        } catch (Exception e) {
            Misc.rmDir(path);
            throw e;
        }
        tableNameToTable.put(name, relation);
    }

    public void dropRelation(String name) throws MiniDBException, IOException {
        if(!tableNameToTable.containsKey(name))
            throw new MiniDBException(String.format("Database %s dose not contain a table named %s!", directory, name));
        Relation relation = tableNameToTable.get(name);
        relation.drop();
        tableNameToTable.remove(name);
    }

    public ArrayList<String> getRelationNames()
    {
        return new ArrayList<>(tableNameToTable.keySet());
    }

    public Relation getRelation(String name)
    {
        return tableNameToTable.get(name);
    }
}
