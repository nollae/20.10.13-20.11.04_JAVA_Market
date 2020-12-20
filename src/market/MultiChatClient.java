package market;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChatClient extends WindowAdapter implements ActionListener {
	//Field
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	//채팅 UI
	JTextArea content;
	JTextField input;
	JButton send;
	
	//
	String name;
	public static final int CONNECT = 0; // 처음접속 MulltiChatClient.CONNECT
	public static final int TALKING =1; // 대화중
	public static final int EXIT = -1;//종료
	
	//Constructor
	public MultiChatClient() {
		try {
			socket = new Socket("localhost",5777);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			chatUI();
			
			//서버로 부터 전송되는 메시지를 계속 수신하는 쓰레드 객체 생성
			ClientThread ct = new ClientThread(ois,content,input);
			ct.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public MultiChatClient(String port, String name) {
		try {
			this.name=name;
			socket = new Socket("localhost",Integer.parseInt(port));
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			chatUI();
			
			//처음접속 메시지 전송
			MessageVO msgVO = new MessageVO();
			msgVO.setName(name);
			msgVO.setStatus(MultiChatClient.CONNECT);
			
			oos.writeObject(msgVO);
			
			//서버로 부터 전송되는 메시지를 계속 수신하는 쓰레드 객체 생성
			ClientThread ct = new ClientThread(ois,content,input);
			ct.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**채팅화면구성**/
	public void chatUI() {
		JFrame jf= new JFrame("채팅창");
		JPanel jp= new JPanel();
		JLabel label = new JLabel("입력");
		Font font=new Font("맑은 고딕",Font.BOLD,12);
		content = new JTextArea(50,50);
		input = new JTextField(15);
		send = new JButton("SEND");
		
		content.setEditable(false);
		content.setFont(font);	input.setFont(font);
		send.setFont(font);	label.setFont(font);
		
		jp.add(label);	jp.add(input);	jp.add(send);
		jf.add(content,BorderLayout.CENTER);
		jf.add(jp,BorderLayout.SOUTH);
		
		jf.setSize(300,200);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		input.requestFocus();
		
		jf.addWindowListener(this);
		input.addActionListener(this);
		send.addActionListener(this);
	}
	/** 이벤트 처리**/
	public void windowClosing(WindowEvent we) {
		try {
			//접속 종료시
			MessageVO msgVO=new MessageVO();
			msgVO.setName(name);
			msgVO.setStatus(MultiChatClient.EXIT);
			oos.writeObject(msgVO);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj =ae.getSource();
		if(obj==input || obj==send) {
			try {
				String msg = input.getText().trim();
				if(msg.equals("")) {
					JOptionPane.showMessageDialog(null,"메시지를 입력해주세요");
					input.requestFocus();
				}else {
					//대화중~
					MessageVO msgVO= new MessageVO();
					msgVO.setName(name);
					msgVO.setMsg(msg);
					msgVO.setStatus(MultiChatClient.TALKING);
					oos.writeObject(msgVO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
