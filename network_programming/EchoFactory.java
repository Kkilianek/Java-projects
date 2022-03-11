package network_programming;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoFactory extends Remote {
    Echo getEcho() throws RemoteException;
}
