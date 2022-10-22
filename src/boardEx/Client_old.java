package boardEx;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;






public class Client_old extends JFrame implements ActionListener{

	// 로그인 GUI 변수
	private JFrame loginGUI = new JFrame();
	private JPanel loginPane;
	private JTextField ipTxt; // ip받는 텍스트
	private JTextField portTxt; // port받는 텍스트
	private JTextField idTxt; // id받는 텍스트
	private JButton loginBtn = new JButton("로그인");
	private JButton exitBtn = new JButton("종료");

	// Main GUI 변수
	private JPanel mainPane;
	private JTextField sendMegTxt;
	private JButton paperBtn = new JButton("쪽지보내기");
	private JButton creRoomBtn = new JButton("방 생성");
	private JButton joinRoomBtn = new JButton("방 참여");
	private JButton sendMegBtn = new JButton("전송");
	private JList connectList = new JList(); // 접속자 리스트
	private JList roomAllList = new JList(); // 방 목록 리스트
	private JTextArea mainMegTxt = new JTextArea(); // 채팅창 변수
	private JButton gameStartBtn = new JButton("게임 시작");

	// Network 자원
	private Socket socket;
	// 필요하다면 ip를 강제로 입력
	private String ip; // "127.0.0.1" 은 자기자신
	private int port; // 서버에서 입력한 port 번호
	private String id = "";
	
	// 그 외 변수들
	Vector userList = new Vector();
	Vector roomList = new Vector();
	StringTokenizer st;
	

	
	// 내가 있는 방
	private String myRoom;


	// 서버와 메시지를 전달 받고 전달하는 기능
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	BoardMain bm;
	
	// 생성자 메서드
	Client_old() {
		// 로그인창 화면 구성 메서드
		loginInit();
		// 로그인 메서드 - 리스너 설정 메서드
		loginMain();
		// 메인창 화면 구성 메서드
		MainInit();
	}

	private void loginMain() {
		// 자체 클래스에서 상속 받음
		loginBtn.addActionListener(this); // 로그인 버튼 리스너
		exitBtn.addActionListener(this); // 로그인 창 종료 버튼 리스너
		paperBtn.addActionListener(this); // 쪽지보내기 리스너
		creRoomBtn.addActionListener(this); // 방 만들기 리스너
		joinRoomBtn.addActionListener(this);// 방 참여하기 리스너
		sendMegBtn.addActionListener(this); // 채팅 메시지 보내기 리스너
		gameStartBtn.addActionListener(this);

	}

	// 로그인창 화면 구성 메서드
	private void loginInit() {
		loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 로그인 창 닫기 버튼
		loginGUI.setBounds(100, 100, 236, 417); // 로그인 창 크기
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginGUI.setContentPane(loginPane);
		loginPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setBounds(12, 140, 57, 15);
		loginPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setBounds(12, 182, 69, 15);
		loginPane.add(lblNewLabel_1);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 225, 57, 15);
		loginPane.add(lblId);

		ipTxt = new JTextField();
		ipTxt.setBounds(81, 137, 116, 21);
		loginPane.add(ipTxt);
		ipTxt.setColumns(10);

		portTxt = new JTextField();
		portTxt.setBounds(81, 179, 116, 21);
		loginPane.add(portTxt);
		portTxt.setColumns(10);

		idTxt = new JTextField();
		idTxt.setBounds(81, 222, 116, 21);
		loginPane.add(idTxt);
		idTxt.setColumns(10);

		loginBtn.setBounds(12, 310, 195, 23);
		loginPane.add(loginBtn);

		// true는 화면에 보이도록 설정
		// false는 화면에 보이지 않도록 설정
		loginGUI.setVisible(true);
	}

	// 메인 창 화면 구성 메서드
	private void MainInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 519);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("전체 접속자");
		lblNewLabel.setBounds(12, 10, 64, 15);
		mainPane.add(lblNewLabel);

		connectList.setBounds(12, 35, 100, 108);
		mainPane.add(connectList);

		paperBtn.setBounds(12, 153, 100, 23);
		mainPane.add(paperBtn);

		JLabel lblNewLabel_1 = new JLabel("방 목록");
		lblNewLabel_1.setBounds(12, 186, 57, 15);
		mainPane.add(lblNewLabel_1);

		roomAllList.setBounds(12, 205, 100, 143);
		mainPane.add(roomAllList);

		creRoomBtn.setBounds(12, 365, 97, 23);
		mainPane.add(creRoomBtn);

		joinRoomBtn.setBounds(12, 398, 97, 23);
		mainPane.add(joinRoomBtn);

		mainMegTxt.setBounds(124, 35, 298, 354);
		mainPane.add(mainMegTxt);
		mainMegTxt.setEditable(false);

		sendMegTxt = new JTextField();
		sendMegTxt.setBounds(134, 399, 211, 21);
		mainPane.add(sendMegTxt);
		sendMegTxt.setColumns(10);
		sendMegTxt.setEnabled(false);
		
		sendMegBtn.setBounds(357, 398, 64, 23);
		mainPane.add(sendMegBtn);
		sendMegBtn.setEnabled(false);
		

		gameStartBtn.setFont(new Font("굴림", Font.BOLD, 25));
		gameStartBtn.setBounds(12, 431, 410, 40);
		gameStartBtn.setEnabled(false);
		mainPane.add(gameStartBtn);
		
		// 클라이언트 실행시, 접속 이전까지는 창이 뜨지 않음
		this.setVisible(false);
	}

	// 네트워크 연결 메서드
	private void Network() {
		try {
			socket = new Socket(ip, port);

			// 정상적으로 소켓이 연결되었을 경우
			if (socket != null) {
				Connection();
			}

		} catch (UnknownHostException e) { // HOST 에러
			JOptionPane.showMessageDialog(null, "연결 실패", "알림", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) { // Stream 에러
			JOptionPane.showMessageDialog(null, "연결 실패", "알림", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 실제적인 연결 메서드
	private void Connection() {
		// Stream 오류
		try {
			is = socket.getInputStream();
			dis = new DataInputStream(is);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결 실패", "알림", JOptionPane.ERROR_MESSAGE);
		} // Stream 설정 끝
		
		// main UI 표시
		this.setVisible(true);
		// 로그인 창 삭제
		this.loginGUI.setVisible(false);
		
		// Thread 설정
		// 처음 접속 시에 ID 전송
		sendMessage(id);

		// connectList에 사용자 추가
		userList.add(id);
		connectList.setListData(userList);

		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					try {
						String msg = dis.readUTF(); // 메시지 수신
						System.out.println("서버로부터 수신된 메시지 : " + msg);

						inMessage(msg);

					} catch (IOException e) {
						try {
							os.close();
							is.close();
							dos.close();
							dis.close();
							socket.close();
							JOptionPane.showMessageDialog(null, "서버 연결 해제", "알림", JOptionPane.ERROR_MESSAGE);
						} catch (IOException e1) {
						}
						break;
					}
				}

			}
		});

		th.start();

	}

	// 서버로 부터 들어오는 모든 메시지
	// 토큰 사용
	// 계속 메시지를 받기 위해서는 현 위치에서 초기화가 이루어져야 함
	private void inMessage(String str) {
		st = new StringTokenizer(str, "/"); // str 문자열을 '/'를 기준으로 자름

		String protocol = st.nextToken();
		String message = st.nextToken();

		System.out.println("프로토콜 : " + protocol);
		System.out.println("내용 : " + message);

		// 새로운 접속자
		if (protocol.equals("NewUser")) {
			userList.add(message);
			connectList.setListData(userList);
		} else if (protocol.equals("OldUser")) {
			userList.add(message);
			connectList.setListData(userList);
		} else if (protocol.equals("Paper")) {
			st = new StringTokenizer(message, "/");
			String user = st.nextToken();
			
			System.out.println(user + " 사용자로부터 온 쪽지 " + message);


			JOptionPane.showMessageDialog(null, message, user + "님으로 부터 쪽지", JOptionPane.CLOSED_OPTION);

			// 방 만들기 성공 했을 때
		} else if (protocol.equals("CreateRoom")) {
			myRoom = message;
			sendMegTxt.setEnabled(true);
			sendMegBtn.setEnabled(true);
			creRoomBtn.setEnabled(false);
			joinRoomBtn.setEnabled(false);
			gameStartBtn.setEnabled(true);
			// 방 만들기 실패 했을 때
		} else if (protocol.equals("CreateRoomFail")) {
			JOptionPane.showMessageDialog(null, "방 만들기 실패", "알림", JOptionPane.ERROR_MESSAGE);

			// 방이 생성된 이후
		} else if (protocol.equals("NewRoom")) {
			roomList.add(message);
			roomAllList.setListData(roomList);

			// 채팅 메시지
		} else if (protocol.equals("Chatting")) {
			String msg = st.nextToken();
			System.out.println("채팅 내용 : " + msg);

			mainMegTxt.append(message + " : " + msg + "\n");

			// 기존의 방 출력
		} else if (protocol.equals("OldRoom")) {
			roomList.add(message);
			roomAllList.setListData(roomList);

			// 방 참여
		} else if (protocol.equals("JoinRoom")) {
			myRoom = message;
			sendMegTxt.setEnabled(true);
			sendMegBtn.setEnabled(true);
			creRoomBtn.setEnabled(false);
			joinRoomBtn.setEnabled(false);
			gameStartBtn.setEnabled(true);
			JOptionPane.showMessageDialog(null, "방에 입장했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			// 다른 사용자가 나갔을 때
		} else if (protocol.equals("UserOut")) {
			userList.remove(message);
			connectList.setListData(userList);
		}

	}

	// 서버에 메시지를 송신
	private void sendMessage(String str) {
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new Client_old();
	}

	// 이벤트 입력
	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼명 구분

		if (e.getSource() == loginBtn) {
			System.out.println("로그인 버튼 클릭");

			if (ipTxt.getText().length() == 0) {
				ipTxt.setText("IP를 입력해 주세요");
				ipTxt.requestFocus(); // 해당 칸으로 커서 이동
			} else if (portTxt.getText().length() == 0) {
				portTxt.setText("PORT를 입력해 주세요");
				portTxt.requestFocus();
			} else if (idTxt.getText().length() == 0) {
				idTxt.setText("ID를 입력해 주세요");
				idTxt.requestFocus();
			} else {
				// 사용자가 입력한 값을 비교
				ip = ipTxt.getText().trim(); // ip 받기
											// trim() : 빈 공간 발생시 빈칸 제외하고 송신

				port = Integer.parseInt(portTxt.getText().trim());
				// port 는 int형이기 때문에 형변환이 필요

				id = idTxt.getText().trim(); // id 받기
			}

			Network();
		} else if (e.getSource() == paperBtn) {
			System.out.println("쪽지 보내기 버튼 클릭");
			// 쪽지 보내기
			String user = (String) connectList.getSelectedValue();
			String paper = JOptionPane.showInputDialog("보낼 메시지");

			if (paper != null) {
				sendMessage("Paper/" + user + "/" + paper);
				// user에게 paper를 보냄.
			}
			System.out.println("받는 사람 : " + user + " | 보낼 내용 : " + paper);

		} else if (e.getSource() == creRoomBtn) {
			String roomName = JOptionPane.showInputDialog("방 이름");
			if (roomName != null) {
				sendMessage("CreateRoom/" + roomName);
			}
			System.out.println("방 만들기 버튼 클릭");

		} else if (e.getSource() == joinRoomBtn) {

			String joinRoom = (String) roomAllList.getSelectedValue();
			sendMessage("JoinRoom/" + joinRoom);

			System.out.println("방 참여하기 버튼 클릭");
		} else if (e.getSource() == sendMegBtn) {
			// 메시지 송부
			sendMessage("Chatting/" + myRoom + "/" + sendMegTxt.getText().trim());

			System.out.println("전송 버튼 클릭");
		} else if (e.getSource() == gameStartBtn){
			bm.main(null);
			System.out.println("게임 시작 버튼 클릭");
		}

	}

	
}
