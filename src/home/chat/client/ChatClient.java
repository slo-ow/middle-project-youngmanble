package home.chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import home.chat.Chat;
import home.chat.ChatMessage;


public class ChatClient extends UnicastRemoteObject implements ChatMessage{
	
	public ChatClient() throws RemoteException{}
	
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println(msg);
	}                            
	
	public static void main(String[] args) throws Exception {
		String name ="승환";
		ChatClient obj = new ChatClient();
		Chat cs = (Chat)Naming.lookup("rmi://192.168.207.11/chat");
		cs.setClient(obj,name);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String msg = in.readLine();
			cs.setMessage(msg,name);
			
		}
		
	}

}
