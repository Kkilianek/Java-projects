package network_programming;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;
//Uruchom normalnie ze środowiska klikając RUN - serwer będzie uruchomiony, póki go nie zatrzymasz
public class PhoneBookServer {
    //wersja podstawowa z wariantem rozmytym i trwałym
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost.getHostAddress() = " + localHost.getHostAddress());
        System.out.println("localHost.getHostName() = " + localHost.getHostName());

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(12129);
        } catch (Exception e) {
            System.err.println("Create server socket: " + e);
            return;
        }
        File file = new File("data.ser");
        FileInputStream in = null;
        TreeMap<String, Integer> db = new TreeMap<>();
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream out;
        if (file.exists()) {
            if (file.length() != 0) {
                in = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(in);
                db = (TreeMap<String, Integer>) objectInputStream.readObject();
                objectInputStream.close();
                in.close();
            }
        }
        while (true) {
            try {

                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                String fromClient = br.readLine();
                String[] dane = fromClient.split(" ");
                String name;
                Integer number;
                if (dane[0].equals("put")) {
                    name = dane[1];
                    number = Integer.valueOf(dane[2]);
                    if (db.containsKey(name)) {
                        db.replace(name, number);
                    } else {
                        db.put(name, number);
                    }
                    out = new FileOutputStream(file);
                    objectOutputStream = new ObjectOutputStream(out);
                    objectOutputStream.writeObject(db);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    out.close();
                    pw.println("[" + name + "] [" + db.get(name) + "]");
                } else if (dane[0].equals("get")) {
                    name = dane[1];
                    if (db.containsKey(name)) {
                        pw.println("[" + name + "] [" + db.get(name) + "]");
                    } else if (!db.isEmpty()) {
                        String downed = name.toLowerCase();
                        for (String s : db.keySet()) {
                            if (s.toLowerCase().startsWith(downed)) {
                                pw.println("[" + s + "] [" + db.get(s) + "]");
                            }
                        }
                        pw.println();
                    } else {
                        pw.println("--- no name: [" + name + "] ---");
                    }
                } else if (dane[0].equals("getall")) {
                    if (db.isEmpty()) {
                        pw.println("baza danych pusta");
                    } else {
                        for (String s : db.keySet()) {
                            String val = db.get(s).toString();
                            pw.println("[" + s + "] [" + val + "]");
                        }
                    }
                }
                br.close();
            } catch (Exception e) {
                System.err.println("Server exception: " + e);
            }
        }
    }
}
