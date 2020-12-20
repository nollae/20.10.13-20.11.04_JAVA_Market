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
	//ä�� UI
	JTextArea content;
	JTextField input;
	JButton send;
	
	//
	String name;
	public static final int CONNECT = 0; // ó������ MulltiChatClient.CONNECT
	public static final int TALKING =1; // ��ȭ��
	public static final int EXIT = -1;//����
	
	//Constructor
	public MultiChatClient() {
		try {
			socket = new Socket("localhost",5777);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			chatUI();
			
			//������ ���� ���۵Ǵ� �޽����� ��� �����ϴ� ������ ��ü ����
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
			
			//ó������ �޽��� ����
			MessageVO msgVO = new MessageVO();
			msgVO.setName(name);
			msgVO.setStatus(MultiChatClient.CONNECT);
			
			oos.writeObject(msgVO);
			
			//������ ���� ���۵Ǵ� �޽����� ��� �����ϴ� ������ ��ü ����
			ClientThread ct = new ClientThread(ois,content,input);
			ct.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**ä��ȭ�鱸��**/
	public void chatUI() {
		JFrame jf= new JFrame("ä��â");
		JPanel jp= new JPanel();
		JLabel label = new JLabel("�Է�");
		Font font=new Font("���� ���",Font.BOLD,12);
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
	/** �̺�Ʈ ó��**/
	public void windowClosing(WindowEvent we) {
		try {
			//���� �����
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
					JOptionPane.showMessageDialog(null,"�޽����� �Է����ּ���");
					input.requestFocus();
				}else {
					//��ȭ��~
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
