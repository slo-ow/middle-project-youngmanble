package admin.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import vo.MemberVO;

/**
 * @author 문성철
 * @version 1.0
 * 
 * 관리자페이지 - 회원 1:1 채팅기능 
 * */
public class AdminInquiryController {

	@FXML
	private Button btnEnter;

	@FXML
	private Button btnChatOn;

	@FXML
	private TextField tfChatSpace;

	@FXML
	private Hyperlink rinkMail;

	@FXML
	private TextArea taChat;

	@FXML
	private Button btnChatOff;



	@FXML
	void chaton(ActionEvent event) {
		System.out.println("채팅활성 버튼 클릭");
		System.out.println("서버실행");
		startServer();
	}

	@FXML
	void gomail(ActionEvent event) {
		Stage stage = new Stage();
		stage.setTitle("이메일보내기!!");

		rinkMail.setText("https://mail.naver.com");
		String location = rinkMail.getText();
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		engine.load(location);

		Scene sc = new Scene(browser);
		stage.setScene(sc);
		stage.setWidth(800);
		stage.setHeight(600);
		stage.show();

	}

	@FXML
	void chatoff(ActionEvent event) {
		System.out.println("채팅 비활성화 호출");
		taChat.setText("[서버연결종료]");
		closeServer();
	}

	@FXML
	void msgForm(ActionEvent event){
		System.out.println("입력창 호출");
		String msg = "[관리자] :"+tfChatSpace.getText();
		sendToAll(msg);
		//tfChatSpace.clear(); //채팅입력이되었다면 필드를 지워준다.

	}

	///////////////////////////////////////////////////////////////
	HashMap clients;
	Socket socket;
	MemberVO logVO = HomePageController.session;
	DataOutputStream out;
	ServerSocket serverSocket;
	//관리자가 채팅활성화를 누르면 서버가 시작된다.
	void startServer(){ 
		taChat.setText("채팅이 시작 되었습니다.");
		tfChatSpace.setDisable(false);
		Thread thread = new Thread(){
			@Override
			public void run() {
				clients = new HashMap();
				Collections.synchronizedMap(clients);

				serverSocket = null;
				socket = null;

				try{
					serverSocket = new ServerSocket(7777);
					System.out.println("채팅이 시작되었습니다.");
					/////클라이언트와 관리자의 1:1 채팅공간을 만들어준다.
					while(true){
						System.out.println("[서버연결 기다리는중]");
						socket = serverSocket.accept();
						System.out.println("[서버연결 성공]");
						System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속하였습니다."+socket.getInputStream());
						taChat.setText("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속하였습니다.");
						ServerReceiver thread = new ServerReceiver(socket);
						thread.start();
					}
				} catch(Exception e) {
					e.printStackTrace();
				} 
			}
		};
		thread.start();
	}

	//메세지 전송
	void sendToAll(String msg){
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(msg);
			taChat.setText(taChat.getText() + "\n" + msg);//textArea 로 메세지전송
		} catch (IOException e) {
			e.printStackTrace();

		}
	}//sendToAll

	// 서버동작
	class ServerReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		ServerReceiver(Socket socket){
			this.socket = socket;
			try{
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			}catch(IOException e){

			}
		}

		public void run(){
			String name = "";
			try{
				name = in.readUTF();
				sendToAll("■알림■ 관리자님이 입장하셨습니다.");
				taChat.setText(name+"님이 입장하셨습니다.");

				clients.put(name, out);
				System.out.println("현재 서버접속자 수는"+clients.size()+"입니다.");
				while(in!=null){
					sendToAll(in.readUTF());
				}				
			}catch(IOException e){
				e.getMessage();
			} finally {
				sendToAll("#"+name+"님이 나가셨습니다.");
				taChat.setText(name+"님이 퇴장하셨습니다.");
				clients.remove(name);
				System.out.println("["+socket.getInetAddress()
				+":"+socket.getPort()+"]"+"에서 접속을 종료하였습니다.");
				System.out.println("현재 서버 접속자 수는 "+clients.size()+"입니다.");

			}//try
		}//run
	}//ReceiverThread

	//서버종료
	void closeServer(){    	
		String exit = "exit";  ///종료메세지
		System.out.println(exit + " || 서버에게 보낸 종료메세지 확인");
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(exit);
			tfChatSpace.setDisable(true);
			if(socket.isConnected() && !socket.isClosed()){
				System.out.println("[서버연결종료]");
				socket.close();
				serverSocket.close();
			}    			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
