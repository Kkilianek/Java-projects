package network_programming;

import java.io.*;
import java.net.Socket;
// Uruchamiaj z terminala komendą java network_programming.PhoneBookClient i komendami podanymi niżej: get, put i getall z odpowiednimi argumentami podanymi w instrukcji do laboratorium
public class PhoneBookClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12129);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            String name;
            Integer number;
            String choice = args[0];
            if (choice.equals("get")) {
                name = args[1];
                pw.println("get " + name);
            } else if (choice.equals("put")) {
                name = args[1];
                number = Integer.valueOf(args[2]);
                pw.println("put " + name + " " + number);
            } else if (choice.equals("getall")) { //testowa metoda wyswietlajaca obecny stan bazy danych
                pw.println("getall");
            }
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String out;
            while ((out = br.readLine())!=null){
                System.out.println(out);
            }
            br.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }
    }
}
