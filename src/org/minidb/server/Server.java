package org.minidb.server;

import org.minidb.database.Database;
import org.minidb.exception.MiniDBException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
    /**
     * usage: java Server.java --port 2134 --data ./data  --db default
     * */
    public static void main(String[] arg) throws IOException, MiniDBException {
        int port = 2134;
        Path dataDir = Paths.get("./data");
        String dbname = "default";
        for (int i = 0; i < arg.length; i++) {
            String currentArg = arg[i];

            if (currentArg.equals("--port")) {
                i++;
                port = Integer.valueOf(arg[i]);
            }

            if (currentArg.equals("--data")) {
                i++;
                dataDir = Paths.get(arg[i]);
            }

            if (currentArg.equals("--db")) {
                i++;
                dbname = arg[i];
            }
        }
        assert Files.isDirectory(dataDir) : String.format("Data directory %s does not exist!", dataDir.toString());
        Path defaultDBPath = Paths.get(dataDir.toString(), dbname);
        if(Files.notExists(defaultDBPath))
        {
            new Database(defaultDBPath.toString()).create();
        }else{
            assert Files.isDirectory(defaultDBPath) : String.format("%s is not a directory!", defaultDBPath.toString());
        }
        ServerSocket socket = new ServerSocket(port);
        while (socket != null) {
            try {
                Socket s = socket.accept();
                new Thread(new ServerConnection(s, dataDir, defaultDBPath)).start();
            } catch (Exception e) {
                e.printStackTrace();
                socket.close();
                socket = null;
            }
        }
    }
}
