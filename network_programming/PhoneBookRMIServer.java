package network_programming;

import javax.naming.NamingException;
import java.io.Serial;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.TreeMap;
//Serwer uruchom normalnie, tak jak to było w przypadku zadania nr 2 (ze środowiska)
public class PhoneBookRMIServer extends UnicastRemoteObject implements EchoFactory {
    static TreeMap<String, Integer> db = new TreeMap<>();
    @Serial
    private static final long serialVersionUID = 7L;
    private static final String echoServerName = "rmi://localhost/ECHO-FACTORY-SERVER";

    public PhoneBookRMIServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, NamingException {
        final EchoFactory echoServer = new PhoneBookRMIServer();
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind(echoServerName, echoServer);
    }

    @Override
    public Echo getEcho() throws RemoteException {
        return new EchoServant();
    }
}
