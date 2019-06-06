package org.minidb.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import org.minidb.grammar.ResultTable;
import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ClientSwing {
    private JComboBox<String> comboBox1;
    private JPanel PanelMain;
    private JScrollPane Scroll;
    private JButton ExeCute;
    private JTable Table;
    private JTextArea textCommand;
    private JTextArea recommanded;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientSwing(String host,int port)throws IOException{
        socket = new Socket(host,port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        Scroll = new JScrollPane();
        Scroll.setPreferredSize(new Dimension(450,180));
        PanelMain = new JPanel();
        PanelMain.setPreferredSize(new Dimension(600,400));
        comboBox1 = new JComboBox();
        comboBox1.setPreferredSize(new Dimension(120,50));
        comboBox1.addItem("Create Database");
        comboBox1.addItem("Drop Database");
        comboBox1.addItem("Use Database");
        comboBox1.addItem("Create Table");
        comboBox1.addItem("Drop Table");
        comboBox1.addItem("Insert");
        comboBox1.addItem("Delete");
        comboBox1.addItem("Show Table");
        comboBox1.addItem("Select");
        comboBox1.addItem("Exit");
        textCommand = new JTextArea();
        textCommand.setPreferredSize(new Dimension(450,180));
        recommanded = new JTextArea();
        recommanded.setPreferredSize(new Dimension(450,180));
        ExeCute = new JButton();
        ExeCute.setPreferredSize(new Dimension(100,50));
        ExeCute.setText("Execute");
        Table = new JTable();
        Table.setPreferredSize(new Dimension(400,150));
        Table.setVisible(false);
        PanelMain.add(comboBox1);
        PanelMain.add(textCommand);
        PanelMain.add(recommanded);
        PanelMain.add(ExeCute);
        PanelMain.add(Scroll);
        Scroll.setViewportView(Table);
        Scroll.setVisible(true);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == comboBox1){
                    int index = comboBox1.getSelectedIndex();

                    switch (index){
                        case 0://CreateDatabase Command
                            textCommand.setText("create database ");
                            recommanded.setText("Recommanded:  create database DBNAME");
                            break;
                        case 1://DropDatabase Command
                            textCommand.setText("drop database ");
                            recommanded.setText("Recommanded: drop database DBNAME");
                            break;
                        case 2://UseDatabase Command
                            textCommand.setText("use database ");
                            recommanded.setText("Recommanded: use database DBNAME");
                            break;
                        case 3://CreateTable Command
                            textCommand.setText("create table ( )");
                            recommanded.setText("Recommanded: create table TABLENAME(NAME1 TYPE1,NAME2 TYPE2,...)");
                            break;
                        case 4://DropTable Command
                            textCommand.setText("drop table ");
                            recommanded.setText("Recommanded: drop table TABLENAME");
                            break;
                        case 5://Insert Command
                            textCommand.setText("insert into  values( )");
                            recommanded.setText("Recommanded: insert into TABLENAME values(VALUE1,VALUE2,...)");
                            break;
                        case 6://Delete Command
                            textCommand.setText("delete from  where = ");
                            recommanded.setText("Recommanded: delete from TABLENAME where COLNAME = NAME");
                            break;
                        case 7://ShowTable Command
                            textCommand.setText("show table ");
                            recommanded.setText("Recommanded: show table TABLENAME");
                            break;
                        case 8://Select Command
                            textCommand.setText("select  from ");
                            recommanded.setText("Recommanded: select COLNAME from TABLENAME" );
                            break;
                        case 9://ShutDown Command
                            textCommand.setText("exit");
                            recommanded.setText("This command will shut down the server");
                            break;
                    }
                }
            }
        });

    }

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 2134;
        for (int i = 0; i < args.length; i++) {
            String currentArg = args[i];

            if (currentArg.equals("--port")) {
                i++;
                port = Integer.valueOf(args[i]);
            }

            if (currentArg.equals("--host")) {
                i++;
                host = args[i];
            }
        }
        JFrame frame = new JFrame("miniDB Client");
        ClientSwing UI = new ClientSwing(host,port);
        frame.setContentPane(UI.PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.pack();
        frame.setVisible(true);
        UI.ExeCute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == UI.ExeCute) {
                        LinkedList<String> commands = new LinkedList<>();
                        String command;
                        DefaultTableModel modeltable = (DefaultTableModel) UI.Table.getModel();
                        modeltable.setColumnCount(0);
                        modeltable.setRowCount(0);
                        Scanner scann = new Scanner(UI.textCommand.getText().toString());
                        boolean closed = false;
                        try {
                            if (commands.isEmpty()) {
                                command = scann.nextLine().trim();
                                while (!command.endsWith(";"))
                                {
                                    command += scann.nextLine().trim();
                                }
                                command = command.substring(0, command.length() - 1);
                                if (command.startsWith("import")) {
                                    String filename = command.substring(6).replaceAll("\\s+", "");
                                    Scanner fscanner = null;
                                    try {
                                        fscanner = new Scanner(new FileInputStream(new File(filename)));
                                    } catch (FileNotFoundException ex) {
                                        ex.printStackTrace();
                                    }
                                    while (fscanner.hasNextLine()) {
                                        String tmpCommand = fscanner.nextLine().trim();
                                        while (!tmpCommand.endsWith(";"))
                                        {
                                            tmpCommand += fscanner.nextLine().trim();
                                        }
                                        tmpCommand = tmpCommand.substring(0, tmpCommand.length() - 1);
                                        commands.push(tmpCommand);
                                    }
                                } else {
                                    commands.push(command);
                                }
                                if (command.isEmpty()) {
                                    //System.out.println("Empty file!");
                                    UI.recommanded.setText("Empty file!");
                                }
                            }
                            command = commands.pop();
                            if (command.toLowerCase().equals("exit")) {
                                closed = true;
                            }
                            try {
                                UI.oos.writeObject(command);
                                StringBuilder strBuild = new StringBuilder();
                                Object obj = UI.ois.readObject();
                                if (obj instanceof ResultTable) {
                                    ResultTable result = (ResultTable) obj;
                                    Object[] colomn = new Object[result.meta.ncols];
                                    int pos = 0;
                                    DefaultTableModel tableModel = (DefaultTableModel) UI.Table.getModel();
                                    for (String name : result.meta.colnames) {
                                        strBuild.append(name + '\t');
                                        colomn[pos] = name;
                                        tableModel.addColumn(name);
                                        pos++;
                                        System.out.print(name);
                                        System.out.print('\t');
                                    }
                                    System.out.println();
                                    Object[][] dataShow = new Object[result.data.size()][result.meta.ncols];
                                    int out_pos = 0;
                                    for (ArrayList<Object> row : result.data) {
                                        Object[] rowShow = new Object[result.meta.ncols];
                                        pos = 0;
                                        for (Object o : row) {
                                            if (o == null) {
                                                System.out.print("[null]");
                                                dataShow[out_pos][pos] = "[null]";
                                                strBuild.append("[null]");
                                                rowShow[pos] = "[null]";
                                            } else {
                                                System.out.print(o.toString());
                                                dataShow[out_pos][pos] = o.toString();
                                                strBuild.append(o.toString());
                                                rowShow[pos] = o.toString();
                                            }
                                            pos++;
                                            System.out.print("\t");
                                            strBuild.append('\t');
                                        }
                                        tableModel.addRow(rowShow);
                                        out_pos++;
                                        System.out.println();
                                    }
                                    UI.Table.setVisible(true);
                                }
                                int in = strBuild.indexOf("message");
                                if(in >= 0){
                                    strBuild.delete(in,in+7);
                                }
                                UI.recommanded.setText(strBuild.toString());
                            } catch (Exception exc) {
                                exc.printStackTrace();
                            }

                            if(closed)
                            {
                                UI.socket.close();
                                UI.socket = null;
                            }
                            System.out.println();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            });
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    UI.oos.writeObject("exit;");
                    Object obj = UI.ois.readObject();
                    System.out.println("Bye Bye");
                }catch (Exception e)
                {}
                finally {
                    frame.dispose();
                    System.exit(0);
                }
            }
        });
        }


}
