package org.minidb.client;

import org.minidb.grammar.ResultTable;
import org.minidb.server.ServerConnection;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    /**
     * usage: java Client.java --host 127.0.0.1 --port 2134
     * */
    public static void main(String[] arg) throws IOException {
        String host = "127.0.0.1";
        int port = 2134;
        for (int i = 0; i < arg.length; i++) {
            String currentArg = arg[i];

            if (currentArg.equals("--port")) {
                i++;
                port = Integer.valueOf(arg[i]);
            }

            if (currentArg.equals("--host")) {
                i++;
                host = arg[i];
            }
        }
        Socket socket = new Socket(host, port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);
        LinkedList<String> commands = new LinkedList<>();
        boolean closed = false;
        while (socket != null) {
            try {
                String command;
                if(commands.isEmpty())
                {
                    command = scanner.nextLine();
                    if(command.startsWith("import"))
                    {
                        String filename = command.substring(6).replaceAll("\\s+", "");
                        Scanner fscanner = new Scanner(new FileInputStream(new File(filename)));
                        while (fscanner.hasNextLine())
                        {
                            commands.push(fscanner.nextLine());
                        }
                    }else {
                        commands.push(command);
                    }
                    if(commands.isEmpty())
                    {
                        System.out.println("Empty file!");
                        continue;
                    }
                }
                command = commands.pop();
                if(command.toLowerCase().equals("exit"))
                {
                    closed = true;
                }
                oos.writeObject(command);
                Object obj = ois.readObject();
                if(obj instanceof Exception)
                {
                    ((Exception) obj).printStackTrace();
                }
                if(obj instanceof ResultTable)
                {
                    ResultTable result  = (ResultTable) obj;
                    for(String name : result.meta.colnames)
                    {
                        System.out.print(name);
                        System.out.print('\t');
                    }
                    System.out.println();
                    for(ArrayList<Object> row : result.data)
                    {
                        for(Object o : row)
                        {
                            if(o == null)
                            {
                                System.out.print("[null]");
                            }else {
                                System.out.print(o.toString());
                            }
                            System.out.print("\t");
                        }
                        System.out.println();
                    }
                }
                if(closed)
                {
                    socket.close();
                    socket = null;
                }
                System.out.println();
            } catch (Exception e) {
                socket.close();
                socket = null;
                e.printStackTrace();
            }
        }
    }
}
