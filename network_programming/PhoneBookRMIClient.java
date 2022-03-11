package network_programming;

import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

//Aby skorzystać z klienta, wywołaj go z terminala, tak jak to było w przypadku zadania nr 2
public class PhoneBookRMIClient {
    private static final String echoServerName = "rmi://localhost/ECHO-FACTORY-SERVER";

    PhoneBookRMIClient() throws NamingException, MalformedURLException, NotBoundException, RemoteException {

    }

    public static void main(String[] args) throws NamingException, MalformedURLException, NotBoundException, RemoteException {
        new PhoneBookRMIClient();
        Remote remote = Naming.lookup(echoServerName);
        if (remote instanceof EchoFactory echoFactory) {
            Echo echo = echoFactory.getEcho();
            String result = null;
            String name;
            Integer number;
            String choice = args[0];
            if (choice.equals("get")) {
                name = args[1];
                result = echo.echo("get " + name);
            } else if (choice.equals("put")) {
                name = args[1];
                number = Integer.valueOf(args[2]);
                result = echo.echo("put " + name + " " + number);
            } else if (choice.equals("getall")) { //testowa metoda wyświetlająca obecny stan bazy danych
                result = echo.echo("getall");
            }
            System.out.println(result);
        }

    }
}
