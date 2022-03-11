package network_programming;

import java.io.*;
import java.net.Socket;

public class HTTPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            pw.println("GET "+args[2]+" HTTP/1.1");
            pw.println("Host: www."+args[0]);
            pw.println("");

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String outStr;

            //Prints each line of the response
            while((outStr = br.readLine()) != null){
                System.out.println(outStr);
            }
            br.close();
            pw.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }
    }
}
