package market;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MarketMyPage {

	MarketMgmUI main;
	JPanel myPagePane;
//	JPanel info_updatePane, inputIdPane;
	private JTextField jt_idCheck;
	private JPasswordField jt_upass;
	private JTextField jt_uname;
	private JTextField jt_uaddr;
	private JTextField jt_uphone;
	private JTextField jt_uemail;
	JButton btn_idCheck, btn_updateInfo, btn_updateCancel;
	MarketUpdateInfoEvent eventobj = new MarketUpdateInfoEvent();
	String mid;
	int idx = 0;
	MemberVO vo = new MemberVO();

	public MarketMyPage() {
	}

	public MarketMyPage(MarketMgmUI main) {
		this.main = main;
		this.myPagePane = main.myPagePane;
		this.vo = main.vo;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void myPage() {
		main.switchPane(MarketMgmUI.MYHOME);

//		myPagePane = new JPanel();
		myPagePane.setLayout(null);

		JLabel inform = new JLabel(
				"\u203B \uBCF8\uC778 \uD655\uC778\uC744 \uC704\uD574 \uC544\uC774\uB514\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694. \u203B");
		inform.setHorizontalAlignment(SwingConstants.CENTER);
		inform.setFont(new Font("제주고딕", Font.PLAIN, 13));
		inform.setBounds(307, 44, 333, 32);
		myPagePane.add(inform);

		JLabel jl_pass = new JLabel("[ \uC544\uC774\uB514 ]");
		jl_pass.setHorizontalAlignment(SwingConstants.CENTER);
		jl_pass.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_pass.setBounds(297, 21, 92, 20);
		myPagePane.add(jl_pass);

		jt_idCheck = new JTextField();
		jt_idCheck.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jt_idCheck.setBounds(411, 20, 138, 21);
		myPagePane.add(jt_idCheck);
		jt_idCheck.setColumns(10);

		btn_idCheck = new JButton("\uC778\uC99D");
		btn_idCheck.setBackground(Color.DARK_GRAY);
		btn_idCheck.setForeground(new Color(153, 204, 255));
		btn_idCheck.setFont(new Font("제주고딕", Font.PLAIN, 19));
		btn_idCheck.setBounds(581, 20, 74, 22);
		myPagePane.add(btn_idCheck);
		main.getContentPane().add(myPagePane);
		main.setVisible(true);

		jt_idCheck.addActionListener(eventobj);
		btn_idCheck.addActionListener(eventobj);
	}

	public void myPageUdate(MemberVO mvo) {
		myPage();

		JLabel uinform = new JLabel(vo.getId() + "\uB2D8\uC758 \uAC1C\uC778\uC815\uBCF4");
		uinform.setBounds(346, 99, 237, 30);
		uinform.setFont(new Font("제주고딕", Font.PLAIN, 19));
		uinform.setHorizontalAlignment(SwingConstants.RIGHT);
		myPagePane.add(uinform);

		JLabel jl_upass = new JLabel("- \uBE44\uBC00\uBC88\uD638");
		jl_upass.setBounds(297, 190, 95, 25);
		jl_upass.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(jl_upass);

		JLabel jl_uname = new JLabel("- \uC774\uB984");
		jl_uname.setBounds(297, 245, 68, 25);
		jl_uname.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(jl_uname);

		JLabel jl_uaddr = new JLabel("- \uC8FC\uC18C");
		jl_uaddr.setBounds(297, 300, 68, 25);
		jl_uaddr.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(jl_uaddr);

		JLabel jl_uphone = new JLabel("- \uC804\uD654\uBC88\uD638");
		jl_uphone.setBounds(297, 355, 95, 25);
		jl_uphone.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(jl_uphone);

		JLabel jl_uemail = new JLabel("- \uC774\uBA54\uC77C");
		jl_uemail.setBounds(297, 410, 68, 25);
		jl_uemail.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(jl_uemail);

		jt_upass = new JPasswordField();
		jt_upass.setBounds(446, 190, 230, 25);
		myPagePane.add(jt_upass);
		jt_upass.setColumns(10);

		jt_uname = new JTextField();
		jt_uname.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jt_uname.setBounds(446, 245, 230, 25);
		jt_uname.setColumns(10);
		myPagePane.add(jt_uname);

		jt_uaddr = new JTextField();
		jt_uaddr.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jt_uaddr.setBounds(446, 300, 230, 25);
		jt_uaddr.setColumns(10);
		myPagePane.add(jt_uaddr);

		jt_uphone = new JTextField();
		jt_uphone.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jt_uphone.setBounds(446, 355, 230, 25);
		jt_uphone.setColumns(10);
		myPagePane.add(jt_uphone);

		jt_uemail = new JTextField();
		jt_uemail.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jt_uemail.setBounds(446, 410, 230, 25);
		jt_uemail.setColumns(10);
		myPagePane.add(jt_uemail);

		btn_updateInfo = new JButton("\uC218\uC815\uD558\uAE30");
		btn_updateInfo.setBackground(Color.DARK_GRAY);
		btn_updateInfo.setForeground(new Color(153, 204, 255));
		btn_updateInfo.setBounds(741, 483, 107, 30);
		btn_updateInfo.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(btn_updateInfo);

		btn_updateCancel = new JButton("\uCDE8\uC18C\uD558\uAE30");
		btn_updateCancel.setBackground(Color.DARK_GRAY);
		btn_updateCancel.setForeground(new Color(153, 204, 255));
		btn_updateCancel.setBounds(860, 483, 107, 30);
		btn_updateCancel.setFont(new Font("제주고딕", Font.PLAIN, 19));
		myPagePane.add(btn_updateCancel);

		String[] list = new String[5];
		list[0] = mvo.getPass();
		list[1] = mvo.getName();
		list[2] = mvo.getAddr();
		list[3] = mvo.getPhone();
		list[4] = mvo.getEmail();

		jt_upass.setText(list[0]);
		jt_uname.setText(list[1]);
		jt_uaddr.setText(list[2]);
		jt_uphone.setText(list[3]);
		jt_uemail.setText(list[4]);

		main.getContentPane().add(myPagePane);
		main.setVisible(true);

		btn_updateInfo.addActionListener(eventobj);
		btn_updateCancel.addActionListener(eventobj);
	}

	public void idSearchProc() {
		mid = jt_idCheck.getText().trim();
		if (mid.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			jt_idCheck.requestFocus();
		} else if (mid.equals(vo.getId())){
			idx = main.system.searchMember(mid);
			if (idx != 0) {
				MemberVO mvo = main.system.selectMember(mid);
				myPageUdate(mvo);
			}
		} else {
			JOptionPane.showMessageDialog(null, "아이디를 다시 입력해주세요");
		}
	}

	public void updateProc() {
		ArrayList<String> list = new ArrayList<>();
		list.add(jt_upass.getText().trim());
		list.add(jt_uname.getText().trim());
		list.add(jt_uaddr.getText().trim());
		list.add(jt_uphone.getText().trim());
		list.add(jt_uemail.getText().trim());

		MemberVO mvo = new MemberVO(); 
		mvo.setId(mid);
		mvo.setPass(list.get(0));
		mvo.setName(list.get(1));
		mvo.setAddr(list.get(2));
		mvo.setPhone(list.get(3));
		mvo.setEmail(list.get(4));

		if (main.system.update_info(mvo)) {
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
			myPagePane.setVisible(false);
			myPage();
		} else {
			JOptionPane.showMessageDialog(null, "수정 실패했습니다.");
			System.out.println(main.system.update_info(mvo));
		}
	}

	class MarketUpdateInfoEvent implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (jt_idCheck == obj || btn_idCheck == obj) {
				idSearchProc();
			} else if (btn_updateInfo == obj) {
				updateProc();
			} else if (btn_updateCancel == obj) {
				int result = JOptionPane.showConfirmDialog(null, main.getMsg("수정을 취소하시겠습니까?"));
				if (result == 0) {
					myPagePane.setVisible(false);
					myPage();
				}

			}
		}
	}
}
