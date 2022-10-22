package games.service;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main_Client extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Client frame = new Main_Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("전체 접속자");
		lblNewLabel.setBounds(12, 10, 64, 15);
		contentPane.add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(12, 35, 100, 108);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("쪽지보내기");
		btnNewButton.setBounds(12, 153, 100, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("방 목록");
		lblNewLabel_1.setBounds(12, 186, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JList list_1 = new JList();
		list_1.setBounds(12, 205, 100, 143);
		contentPane.add(list_1);
		
		JButton btnNewButton_1 = new JButton("방 만들기");
		btnNewButton_1.setBounds(12, 365, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("방 참여하기");
		btnNewButton_2.setBounds(12, 398, 97, 23);
		contentPane.add(btnNewButton_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(124, 35, 298, 354);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(134, 399, 211, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("전송");
		btnNewButton_3.setBounds(357, 398, 64, 23);
		contentPane.add(btnNewButton_3);
	}
}
