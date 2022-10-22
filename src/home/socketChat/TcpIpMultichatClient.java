package home.socketChat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import home.controller.HomePageController;
import vo.MemberVO;

public class TcpIpMultichatClient {
	public static void main(String args[]) {
//		if(args.length!=1){
//			System.out.println("USAGE : java TcpIpMultichatClient 대화명");
//			System.exit(0);
//		}
		
		//MemberVO logVO = HomePageController.session;
		String id = "아무개";//logVO.getMem_id();
		try{
			//String serverIp = "192.168.207.11";
			String serverIp = "118.42.46.19";
			//소켓을 생성하여 연결을 요청한다.
			Socket socket = new Socket(serverIp,7777);
			System.out.println("서버에 연결되었습니다.");
			Thread sender = new Thread(new ClientSender(socket,id));
			Thread receiver = new Thread(new ClientReceiver(socket));

			sender.start();
			receiver.start();			
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e){

		}
	}//main

	//ClientSender
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;

		ClientSender(Socket socket, String name){
			this.socket = socket;
			try{
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			}catch(Exception e) { } //Exception

		}//CilentSender

		public void run(){ //Thread
			Scanner scanner = new Scanner(System.in);

			try{
				if(out!=null){
					out.writeUTF(name);
				}
				while(out!=null){
					out.writeUTF("["+name+"]"+scanner.nextLine() +"서버쪽 화면에 보내는내용");
				}
			}catch(IOException e){
				//
			}
		}//run()
	}//ClientSender

	//ClientReceiver
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;

		ClientReceiver(Socket socket){
			this.socket = socket;
			try{
				in = new DataInputStream(socket.getInputStream());
			}catch(IOException e) { } //Exception

		}//CilentSender

		public void run(){ //Thread

			while(in!=null){
				try{
					System.out.println(in.readUTF()+"내가 받아들이는 내용");
				}catch(IOException e){
					//
				}
			}

		}//run()
	}//ClientReceiver


}
