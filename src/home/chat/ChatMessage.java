package home.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatMessage extends Remote {
	public void setMessage(String msg) throws RemoteException;
}
