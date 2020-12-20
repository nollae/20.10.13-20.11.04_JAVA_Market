package market;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.SystemColor;

public class MarketMgmJoin {

	MarketMgmUI main;

	JPanel joinBackPane;
	private JTextField jf_id;
	private JPasswordField jf_pass;
	private JTextField jf_name;
	private JTextField jf_addr;
	private JTextField jf_phone;
	private JTextField jf_email;
	JLabel jl_login, jl_id, jl_pass, jl_name, jl_addr, jl_phone, jl_email, jl_emailCheck;
	JButton doJoin, resetJoin, idcheck;

	public MarketMgmJoin() {
	}

	public MarketMgmJoin(MarketMgmUI main) {
		this.main = main;
		this.joinBackPane = main.joinBackPane;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void join() {
		joinOff();
        main.showPane.setVisible(false);
//        joinBackPane = new ImageJoinPanel(new ImageIcon("C:/java_workspace/market/images/join_back.png").getImage());
        joinBackPane = new ImageJoinPanel(new ImageIcon("C:\\dev\\se_workspace\\market\\images\\join_back.png").getImage()); //영화

		main.setSize(joinBackPane.getWidth(), joinBackPane.getHeight() + 38);
		main.getContentPane().add(joinBackPane);

//		ImageIcon join_img = new ImageIcon("C:/dev/se_workspace/sist_project_1/images/테스트.jpg");
		
		jl_login = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		jl_login.setFont(new Font("제주고딕", Font.PLAIN, 17));
		jl_login.setBounds(174, 10, 106, 30);
		joinBackPane.add(jl_login);

		jl_id = new JLabel("\uC544\uC774\uB514");
		jl_id.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_id.setBounds(30, 75, 60, 20);
		joinBackPane.add(jl_id);

		jl_pass = new JLabel("\uBE44\uBC00\uBC88\uD638");
		jl_pass.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_pass.setBounds(30, 120, 60, 20);
		joinBackPane.add(jl_pass);

		jl_name = new JLabel("\uC774\uB984");
		jl_name.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_name.setBounds(30, 165, 60, 20);
		joinBackPane.add(jl_name);

		jl_phone = new JLabel("\uD578\uB4DC\uD3F0");
		jl_phone.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_phone.setBounds(30, 255, 60, 20);
		joinBackPane.add(jl_phone);

		jl_addr = new JLabel("\uC8FC\uC18C");
		jl_addr.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_addr.setBounds(30, 210, 60, 20);
		joinBackPane.add(jl_addr);

		jl_email = new JLabel("\uC774\uBA54\uC77C");
		jl_email.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_email.setBounds(30, 300, 60, 20);
		joinBackPane.add(jl_email);

		jf_id = new JTextField();
		jf_id.setBounds(115, 75, 140, 20);
		joinBackPane.add(jf_id);
		jf_id.setColumns(10);

		jf_pass = new JPasswordField();
		jf_pass.setColumns(10);
		jf_pass.setBounds(115, 120, 140, 20);
		joinBackPane.add(jf_pass);

		jf_name = new JTextField();
		jf_name.setColumns(10);
		jf_name.setBounds(115, 165, 140, 20);
		joinBackPane.add(jf_name);

		jf_addr = new JTextField();
		jf_addr.setColumns(10);
		jf_addr.setBounds(115, 210, 200, 20);
		joinBackPane.add(jf_addr);

		jf_phone = new JTextField();
		jf_phone.setColumns(10);
		jf_phone.setBounds(115, 255, 140, 20);
		joinBackPane.add(jf_phone);

		jf_email = new JTextField();
		jf_email.setColumns(10);
		jf_email.setBounds(115, 300, 200, 20);
		joinBackPane.add(jf_email);

		idcheck = new JButton("\uC911\uBCF5\uD655\uC778");
		idcheck.setForeground(new Color(153, 204, 255));
		idcheck.setBackground(Color.DARK_GRAY);
		idcheck.setFont(new Font("제주고딕", Font.PLAIN, 13));
		idcheck.setBounds(270, 73, 95, 25);
		joinBackPane.add(idcheck);

		JLabel label = new JLabel("-\uC5C6\uC774 \uC785\uB825");
		label.setFont(new Font("제주고딕", Font.PLAIN, 13));
		label.setBounds(270, 255, 95, 20);
		joinBackPane.add(label);

		jl_emailCheck = new JLabel("");
		jl_emailCheck.setFont(new Font("제주고딕", Font.PLAIN, 13));
		jl_emailCheck.setBounds(115, 336, 200, 20);
		joinBackPane.add(jl_emailCheck);

		resetJoin = new JButton("\uAC00\uC785\uCDE8\uC18C");
		resetJoin.setForeground(new Color(153, 204, 255));
		resetJoin.setFont(new Font("제주고딕", Font.PLAIN, 13));
		resetJoin.setBackground(Color.DARK_GRAY);
		resetJoin.setBounds(303, 397, 95, 25);
		joinBackPane.add(resetJoin);

		doJoin = new JButton("회원가입");
		doJoin.setForeground(new Color(153, 204, 255));
		doJoin.setFont(new Font("제주고딕", Font.PLAIN, 13));
		doJoin.setBackground(Color.DARK_GRAY);
		doJoin.setBounds(199, 397, 95, 25);
		joinBackPane.add(doJoin);

		main.setVisible(true);

		joinOn();

		MemberMgmJoinEvent eventObj = new MemberMgmJoinEvent();
		doJoin.addActionListener(eventObj);
		resetJoin.addActionListener(eventObj);
		idcheck.addActionListener(eventObj);
		jf_id.addActionListener(eventObj);
		jf_email.addActionListener(eventObj);
	}

	public void joinOff() {
		joinBackPane.removeAll();
	}

	public void joinOn() {
		joinBackPane.setVisible(true);
	}

	/* 회원가입 취소 */
	public void joinCancel() {
		joinBackPane.setVisible(false);
	}

	public boolean joinFomeCheck() {
		boolean result = false;

		if (jf_id.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("아이디를 입력해주세요"));
			jf_id.requestFocus();
		} else if (jf_pass.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("패스워드를 입력해주세요"));
			jf_pass.requestFocus();
		} else if (jf_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("이름을 입력해주세요"));
			jf_name.requestFocus();
		} else if (jf_addr.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("이름을 입력해주세요"));
			jf_addr.requestFocus();
		} else if (jf_phone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("핸드폰 번호를 입력해주세요"));
			jf_phone.requestFocus();
		} else if (jf_email.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("이메일을 입력해주세요"));
			jf_email.requestFocus();
		} else {
			result = true;
		}

		return result;
	}

	public void joinProc() {
		MemberVO vo = new MemberVO();
		vo.setId(jf_id.getText().trim());
		vo.setPass(jf_pass.getText().trim());
		vo.setName(jf_name.getText().trim());
		vo.setAddr(jf_addr.getText().trim());
		vo.setPhone(jf_phone.getText().trim());
		vo.setEmail(jf_email.getText().trim());

		boolean result = main.system.join(vo);

		if (result) {
			JOptionPane.showMessageDialog(null, main.getMsg("회원가입 성공"));
		} else {

			JOptionPane.showMessageDialog(null, main.getMsg("회원가입 실패"));
		}
	}

	public void existIdCheck() {
		MemberVO vo = new MemberVO();
		vo.setId(jf_id.getText().trim());
		boolean result = main.system.idCheck(vo.getId());

		if (result) {
			JOptionPane.showMessageDialog(null, main.getMsg("이미 사용중인 아이디입니다."));
		} else {
			JOptionPane.showMessageDialog(null, main.getMsg("사용가능한 아이디입니다."));
		}
	}

	public void existEmailCheck() {
		MemberVO vo = new MemberVO();
		vo.setEmail(jf_email.getText().trim());
		boolean result = main.system.emailCheck(vo.getEmail());

		if (result) {
			jl_emailCheck.setText("이미 등록된 이메일입니다.");
		} else {
			jl_emailCheck.setText("사용 가능한 이메일입니다.");
		}
	}

	class MemberMgmJoinEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (doJoin == obj) {
				if (joinFomeCheck()) {
					joinProc();
					joinCancel();
					main.showMain();
				}
			} else if (resetJoin == obj) {
				joinCancel();
				main.showMain();
			} else if (idcheck == obj) {
				existIdCheck();
			} else if (jf_email == obj) {
				existEmailCheck();
			}
		}
	}
}

class ImageJoinPanel extends JPanel {
	private Image img;

	public ImageJoinPanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}