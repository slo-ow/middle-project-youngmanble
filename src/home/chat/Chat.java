package home.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat extends Remote {
	public void setClient(ChatMessage cc,String name) throws RemoteException;

	public void setMessage(String msg,String name) throws RemoteException;

	void update(String name) throws RemoteException;
}
