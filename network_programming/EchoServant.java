package network_programming;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class EchoServant extends UnicastRemoteObject implements Echo {
    @Serial
    private static final long serialVersionUID = 337L;

    public EchoServant() throws RemoteException {
        super();
    }

    public String echo(String input) throws RemoteException {
        System.out.println("from client: [" + input + "]");
        TreeMap<String, Integer> db = PhoneBookRMIServer.db;
        String[] dane = input.split(" ");
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
            return ("[" + name + "] [" + db.get(name) + "]");
        } else if (dane[0].equals("get")) {
            name = dane[1];
            if (db.containsKey(name)) {
               return ("[" + name + "] [" + db.get(name) + "]");
            } else if (!db.isEmpty()) {
                String downed = name.toLowerCase();
                List<String> found = new ArrayList<>();
                for (String s : db.keySet()) {
                    if (s.toLowerCase().startsWith(downed)) {
                        found.add("[" + s + "] [" + db.get(s) + "]");
                    }
                }
                return String.valueOf(found);
            } else {
               return ("--- no name: [" + name + "] ---");
            }
        } else if (dane[0].equals("getall")) {
            if (db.isEmpty()) {
                return ("baza danych pusta");
            } else {
                List<String> all = new ArrayList<>();
                for (String s : db.keySet()) {
                    String val = db.get(s).toString();
                    all.add("[" + s + "] [" + val + "]");
                }
                return String.valueOf(all);
            }
        }else {
            return "network_programming.EchoServant nie zrozumiał polecenia: " + input;
        }
    }
}
