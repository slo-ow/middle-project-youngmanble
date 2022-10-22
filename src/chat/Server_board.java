package chat;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import java.sql.Connection;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;







public class Server_board extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField portTxt;
	private JTextArea textArea = new JTextArea();
	private JButton startBtn = new JButton("서버 실행");
	private JButton stopBtn = new JButton("서버 중지");

	// Network 자원
	private ServerSocket sercerSocket;
	private Socket socket;
	private int port; // 포트번호 받기
	private Vector userVc = new Vector();

	private StringTokenizer st;

	// 방
	private Vector roomVc = new Vector();

	// 생성자
	Server_board() {
		// 화면 생성 메서드
		init();
		// 서버(startButtom 액션) 매서드(start/stop) - 리스너 설정 메서드
		serverMain();
	}

	private void serverMain() {
		// 자체 클래스에서 상속 받음
		startBtn.addActionListener(this);
		stopBtn.addActionListener(this);
	}

	// 화면 생성 메서드
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel MainLabel = new JLabel("Board Server");
		MainLabel.setFont(new Font("굴림", Font.BOLD, 36));
		MainLabel.setBounds(12, 0, 360, 80);
		contentPane.add(MainLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 60, 337, 241);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(textArea);
		// textArea 상에 문자 자체 기입 금지
		textArea.setEditable(false);

		JLabel lblNewLabel = new JLabel("포트번호");
		lblNewLabel.setBounds(12, 320, 57, 15);
		contentPane.add(lblNewLabel);

		portTxt = new JTextField();
		portTxt.setBounds(81, 320, 268, 21);
		contentPane.add(portTxt);
		portTxt.setColumns(10);

		startBtn.setBounds(12, 370, 162, 23);
		contentPane.add(startBtn);

		stopBtn.setBounds(175, 370, 174, 23);
		contentPane.add(stopBtn);
		stopBtn.setEnabled(false);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Server_board.class.getResource("/imgs/배경.jpg")));
		label.setBounds(0, 0, 361, 419);
		contentPane.add(label);

		// true는 화면에 보이도록 설정
		// false는 화면에 보이지 않도록 설정
		this.setVisible(true);
	}

	// 서버 시작 메서드
	private void serverStart() {
		// 해당 Port가 열려 있으면 접속이 안될 수 있을 경우를 대비한 예외처리
		try {
			sercerSocket = new ServerSocket(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미 사용중입니다.", "알림", JOptionPane.ERROR_MESSAGE);
		}

		// 정상적으로 포트가 열렸을 경우
		if (sercerSocket != null) {
			Connection();
		}
	}

	// 정상 접속 메서드
	private void Connection() {
		// 익명 Thread
		Thread th = new Thread(new Runnable() {

			// Thread에서 처리할 일을 기재한다.
			@Override
			public void run() {

				while (true) {
					try {
						// 1가지의 Thread에서는 1가지의 일만 처리 할 수 있음
						textArea.append("사용자 접속 대기중\n");
						socket = sercerSocket.accept(); // 사용자 접속 대기
														// (접속이 이루어지기 전까지 무한 대기)
						textArea.append("사용자 접속\n");

						UserInfo user = new UserInfo(socket);
						// 객체의 Thread 실행(개별 Thread)
						user.start();

					} catch (IOException e) {

						break;
					}

				} // while문 끝
			}
		});

		th.start();
	}

	public static void main(String[] args) {

		new Server_board();
	}

	// 이벤트 입력(직접 상속)
	@Override
	public void actionPerformed(ActionEvent e) {

		// 버튼 명으로 구분
		if (e.getSource() == startBtn) {
			System.out.println("서버 스타트 버튼 클릭");
			// 포트번호 등록
			port = Integer.parseInt(portTxt.getText().trim());
			serverStart(); // 소캣 생성 및 사용자 접속 대기

			// 반복 서버 실행 차단
			startBtn.setEnabled(false);
			portTxt.setEditable(false);
			stopBtn.setEnabled(true);
			// 서버 초기화
		} else if (e.getSource() == stopBtn) {

			// 서버 실행 차단 해제
			startBtn.setEnabled(true);
			portTxt.setEditable(true);
			stopBtn.setEnabled(false);

			try {
				sercerSocket.close();
				userVc.removeAllElements();
				roomVc.removeAllElements();
			} catch (IOException e1) {
			}
			System.out.println("서버 스탑 버튼 클릭");

		}
	}// 액션 이벤트 끝

	class UserInfo extends Thread {
		// 클라이언트와 메시지를 전달 받고 전달하는 기능
		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;

		private Socket userSocket;
		private String nickName;

		private boolean RoomCheck = true;

		// 생성자 메서드
		UserInfo(Socket soc) {
			this.userSocket = soc;

			UserNetwork();
		}

		// 네트워크 자원 설정
		private void UserNetwork() {
			// userSocket 을 사용하지 않으면, 서버 자체의 소켓으로 넘어갈 수 있기 때문에
			// userSocket으로 설정
			try {
				is = userSocket.getInputStream();
				dis = new DataInputStream(is);

				os = userSocket.getOutputStream();
				dos = new DataOutputStream(os);

				// 사용자의 닉네임을 받는다.
				nickName = dis.readUTF();
				textArea.append(nickName + ": 사용자 접속");

				// BroadCast
				// 기존 사용자들에게 새로운 사용자 알림
				BroadCast("NewUser/" + nickName);// Protocol : "NewUser/" + 사용자
													// ID

				// 자신에게 기존 사용자를 알림
				for (int i = 0; i < userVc.size(); i++) {
					UserInfo u = (UserInfo) userVc.elementAt(i);

					sendMessage("OldUser/" + u.nickName);
				}

				// 자긴에게 기존 방 목록을 알림
				for (int i = 0; i < roomVc.size(); i++) {
					RoomInfo r = (RoomInfo) roomVc.elementAt(i);

					sendMessage("OldRoom/" + r.roomName);
				}

				// 사용자에게 알린 후 Vector에 자신을 추가
				// Vector : 동적으로 늘기도 하고 줄기도 하는 배열
				userVc.add(this);

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Stream설정 에러", "알림", JOptionPane.ERROR_MESSAGE);
			}

		}

		// Thread에서 처리할 내용
		public void run() {
			while (true) {
				// 메시지를 수신
				try {
					String msg = dis.readUTF();
					textArea.append(nickName + " : 사용자로부터 들어온 메시지 : " + msg + "\n");
					inMessage(msg);

				} catch (IOException e) {
					textArea.append(nickName + "사용자 로그아웃\n");
					try {
						dos.close();
						dis.close();
						userSocket.close();
						userVc.remove(this);
						BroadCast("UserOut/" + nickName);

					} catch (IOException e1) {
					}
					break;
				}
			}
		}// run 메서드 끝

		// 클라이언트로부터 들어오는 메시지 처리
		private void inMessage(String str) {
			st = new StringTokenizer(str, "/");

			String protocol = st.nextToken();
			String message = st.nextToken();
			System.out.println("포로토콜 : " + protocol);
			System.out.println("메시지 : " + message);

			if (protocol.equals("Paper")) {
				String user = st.nextToken();

				System.out.println("받는 사람 : " + user);


			
				// 벡터에서 해당 사용자를 찾아서 메시지 전송
				for (int i = 0; i < userVc.size(); i++) {
					UserInfo u = (UserInfo) userVc.elementAt(i);
					if (u.nickName.equals(user)) {
						u.sendMessage("Paper/" + message + "/" + user);

					}
				}
				// 방 생성
			} else if (protocol.equals("CreateRoom")) {
				// 1. 현재 같은 방이 존재하는지 확인
				for (int i = 0; i < roomVc.size(); i++) {
					RoomInfo r = (RoomInfo) roomVc.elementAt(i);

					// 이미 존재 시,
					if (r.roomName.equals(message)) {
						sendMessage("CreateRoomFail/ok");
						RoomCheck = false;
						break;
					}
				} // for문 끝

				// 방을 만들 수 있을 때,
				if (RoomCheck) {
					RoomInfo newRoom = new RoomInfo(message, this);
					// 전체 방 벡터에 방을 추가
					roomVc.add(newRoom);

					sendMessage("CreateRoom/" + message);

					BroadCast("NewRoom/" + message);
				}

				RoomCheck = true;
			} else if (protocol.equals("Chatting")) {
				String msg = st.nextToken();
				System.out.println("송신 내용 : " + msg);
				for (int i = 0; i < roomVc.size(); i++) {
					RoomInfo r = (RoomInfo) roomVc.elementAt(i);

					// 해당 방을 찾았을 때,
					if (r.roomName.equals(message)) {
						r.BroadCastRoom("Chatting/" + nickName + "/" + msg);
					}
				}

			} else if (protocol.equals("JoinRoom")) {
				for (int i = 0; i < roomVc.size(); i++) {
					RoomInfo r = (RoomInfo) roomVc.elementAt(i);
					if (r.roomName.equals(message)) {
						// 새로운 사용자를 알린다.
						r.BroadCastRoom("Chatting/알림/*****" + nickName + "님이 입장하셨습니다.*****");

						// 사용자 추가
						r.AddUser(this);
						sendMessage("JoinRoom/" + message);
					}
				}
			}

		}
		// 전체 사용자에게 메시지 보내는 부분
		private void BroadCast(String str) {
			for (int i = 0; i < userVc.size(); i++) {
				UserInfo u = (UserInfo) userVc.elementAt(i);

				u.sendMessage(str);
			}
		}

		// 클라이언트에게 메시지 전송
		private void sendMessage(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // UserInfo class 끝

	class RoomInfo {
		private String roomName;
		private Vector roomUserVc = new Vector();

		RoomInfo(String str, UserInfo u) {
			this.roomName = str;
			this.roomUserVc.add(u);
		}

		// 현재 방은 모든 사람에게 알리는 메서드
		public void BroadCastRoom(String str) {
			for (int i = 0; i < roomUserVc.size(); i++) {
				UserInfo u = (UserInfo) roomUserVc.elementAt(i);

				u.sendMessage(str);
			}
		}

		// 방에 참여하는 메서드
		private void AddUser(UserInfo u) {
			this.roomUserVc.add(u);
		}
	}

}
