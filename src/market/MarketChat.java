package market;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MarketChat {
	// Field
	JPanel chatPane, chatMainPane;
	JLabel jl_chat_select;
	JTextField jt_chat_select;
	JButton btnChat_select, btn_chatjoin, send;
	MarketMgmUI main;
	MemberVO mvo = new MemberVO();
	JTextField input;
	JScrollPane scrollPane;
	JTextArea content;
	JList list_chatlist;
	JLabel now_room_num;

	ObjectInputStream ois;
	ObjectOutputStream oos;

//Constructor
	public MarketChat() {
	}

	public MarketChat(MarketMgmUI main) {
		this.main = main;
		this.chatPane = main.chatPane;
		this.mvo = main.vo;
		this.content = main.content;
		this.input = main.input;
		this.ois = main.ois;
		this.oos = main.oos;
	}

//Method
	/**
	 * @wbp.parser.entryPoint
	 */
	public void chat() {
		main.switchPane(MarketMgmUI.CHAT);
		chatPane.setLayout(null);
//	JPanel chatPane = new JPanel();

		JLabel title_label = new JLabel("※  채팅을 원하는 게시물 번호 입력  ※");
		title_label.setFont(new Font("제주고딕", Font.PLAIN, 13));
		title_label.setBounds(397, 52, 245, 32);
		chatPane.add(title_label);

		JLabel id_label = new JLabel("[  게시물 번호  ]");
		id_label.setFont(new Font("제주고딕", Font.PLAIN, 20));
		id_label.setBounds(151, 22, 146, 40);
		chatPane.add(id_label);

		jt_chat_select = new JTextField();
		jt_chat_select.setBounds(337, 29, 365, 25);
		jt_chat_select.setColumns(10);
		chatPane.add(jt_chat_select);

		btnChat_select = new JButton("\uAC80  \uC0C9");
		btnChat_select.setBackground(Color.DARK_GRAY);
		btnChat_select.setForeground(new Color(153, 204, 255));
		btnChat_select.setFont(new Font("제주고딕", Font.PLAIN, 16));
		btnChat_select.setBounds(737, 27, 79, 32);
		chatPane.add(btnChat_select);

		main.getContentPane().add(chatPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(120, 90, 743, 366);
		chatPane.add(panel);
		panel.setLayout(null);

		JLabel lblNull = new JLabel(mvo.id + "님의 채팅방");
		lblNull.setHorizontalAlignment(SwingConstants.CENTER);
		lblNull.setFont(new Font("제주고딕", Font.PLAIN, 15));
		lblNull.setBounds(23, 19, 112, 19);
		panel.add(lblNull);

		btn_chatjoin = new JButton("\uCC44\uD305\uBC29\uCC38\uC5EC");
		btn_chatjoin.setBackground(Color.DARK_GRAY);
		btn_chatjoin.setForeground(Color.WHITE);
		btn_chatjoin.setFont(new Font("제주고딕", Font.PLAIN, 15));
		btn_chatjoin.setBounds(23, 299, 112, 54);
		panel.add(btn_chatjoin);

		DefaultListModel model = new DefaultListModel();
		list_chatlist = new JList(model);
		list_chatlist.setFont(new Font("제주고딕", Font.PLAIN, 13));
		for (String id : main.system.chat_list(mvo.id)) {
			model.addElement("[" + id + "] " + main.system.get_pname(id));
		}
		list_chatlist.setBounds(23, 48, 112, 241);
		panel.add(list_chatlist);

//	input = new JTextField();
		input.setBounds(147, 322, 481, 30);
		panel.add(input);
		input.setColumns(10);

		send = new JButton("\uC804  \uC1A1");
		send.setBackground(Color.DARK_GRAY);
		send.setForeground(Color.WHITE);
		send.setFont(new Font("제주고딕", Font.PLAIN, 15));
		send.setBounds(633, 325, 80, 28);
		panel.add(send);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 48, 566, 263);
		panel.add(scrollPane);

		content.setFont(new Font("제주고딕", Font.PLAIN, 15));
		scrollPane.setViewportView(content);

		now_room_num = new JLabel("현재 채팅방 번호 : " + main.now_room);
		now_room_num.setHorizontalAlignment(SwingConstants.LEFT);
		now_room_num.setFont(new Font("제주고딕", Font.PLAIN, 17));
		now_room_num.setBounds(159, 14, 213, 28);
		panel.add(now_room_num);
		main.setVisible(true);

		// 리스너
		MemberChatEvent chatEvent = new MemberChatEvent();
		list_chatlist.addListSelectionListener(chatEvent);
		input.addActionListener(chatEvent);
		send.addActionListener(chatEvent);
		btnChat_select.addActionListener(chatEvent);
		jt_chat_select.addActionListener(chatEvent);

	}// chat method

//chatFormCheck
	public boolean chatFormCheck() {
		boolean result = false;
		if (input.getText().equals("")) {
			result = true;
		}
		return result;
	}

	public void chatProc() {

	}

//이벤트 처리 클래스
	class MemberChatEvent implements ActionListener, ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {// 메소드가 두번 호출 조정하기
				// 클릭된 번호 갖어오기
				content.setText(null);
				String clike_room = (String) list_chatlist.getSelectedValue();
				int idx = clike_room.indexOf("]");
				String clike_room_num = clike_room.substring(1, idx);
//				System.out.println("clike_room_num");
				main.now_room = Integer.parseInt(clike_room_num);
				now_room_num.setText("현재 채팅방 번호 : " + main.now_room);
				MessageVO msgVO = new MessageVO();
				msgVO.setName(main.vo.id);
				msgVO.setStatus(main.CONNECT);
				msgVO.setRoom_num(main.now_room);
				try {
					oos.writeObject(msgVO);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				// 채팅방 내용 textarea에 보여주기

			}
		}

		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (send == obj || input == obj) {
				try {
					String msg = input.getText().trim();
					if (msg.equals("")) {
						JOptionPane.showMessageDialog(null, "메시지를 입력해주세요");
						input.requestFocus();
					} else {
						// 대화중~
						MessageVO msgVO = new MessageVO();
						msgVO.setName(mvo.getId());
						msgVO.setMsg(msg);
						msgVO.setStatus(MarketMgmUI.TALKING);
						msgVO.setRoom_num(main.now_room);
						oos.writeObject(msgVO);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (btn_chatjoin == obj) {
				// 채팅하기를 눌럿을때

			} else if (btnChat_select == obj || jt_chat_select == obj) {
				// 검색을 눌럿을때
				if (!jt_chat_select.getText().equals("")) {
					main.now_room = Integer.parseInt(jt_chat_select.getText());
					now_room_num.setText("현재 채팅방 번호 : " + main.now_room);
					MessageVO msgVO = new MessageVO();
					msgVO.setName(main.vo.id);
					msgVO.setStatus(main.CONNECT);
					msgVO.setRoom_num(main.now_room);
					content.setText(null);

					try {
						main.oos.writeObject(msgVO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "게시물번호를 입력해주세요");
				}

			}

		}

	}// event class
}
