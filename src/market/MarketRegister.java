package market;
//backup

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MarketRegister {
	// Field
	JPanel regPane;
	JPanel jp_name, jp_price, jp_button, jp_explain;
	JLabel jl_name, jl_price, jl_explain;
	JTextField jt_name, jt_price, jt_explain, jt_pphone;
	MarketMgmUI main;
	JButton btnReg, btnReset;
	JRadioButton jr_yes, jr_no;
	JComboBox jc_area, jc_method;
	MemberVO mvo = new MemberVO();
	ButtonGroup group;
	JScrollPane jScrollPane = new JScrollPane();
	private JLabel lblNewLabel;

	// Constructor
	public MarketRegister() {
	}

	public MarketRegister(MarketMgmUI main) {
		this.main = main;
		this.regPane = main.regPane;
		this.mvo = main.vo;
	}

	// Method
	/**
	 * @wbp.parser.entryPoint
	 */
	public void register() {
		main.switchPane(MarketMgmUI.REGISTER);
		regPane.setLayout(null);

		JLabel jl_name = new JLabel("-  \uC0C1\uD488 \uC774\uB984 ");
		jl_name.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_name.setHorizontalAlignment(SwingConstants.CENTER);
		jl_name.setBounds(60, 64, 410, 34);
		regPane.add(jl_name);

		JLabel jl_price = new JLabel("- \uC0C1\uD488 \uAC00\uACA9");
		jl_price.setHorizontalAlignment(SwingConstants.CENTER);
		jl_price.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_price.setBounds(60, 108, 410, 34);
		regPane.add(jl_price);

		JLabel jl_pphone = new JLabel("- \uAC70\uB798 \uC2DC \uC5F0\uB77D\uCC98");
		jl_pphone.setHorizontalAlignment(SwingConstants.CENTER);
		jl_pphone.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_pphone.setBounds(60, 152, 436, 34);
		regPane.add(jl_pphone);

		JLabel jl_explain = new JLabel("- \uC0C1\uD488 \uC815\uBCF4");
		jl_explain.setHorizontalAlignment(SwingConstants.CENTER);
		jl_explain.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_explain.setBounds(60, 353, 410, 34);
		regPane.add(jl_explain);

		JLabel jl_state = new JLabel("- \uC0C1\uD488 \uC0C1\uD0DC ");
		jl_state.setHorizontalAlignment(SwingConstants.CENTER);
		jl_state.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_state.setBounds(60, 196, 410, 34);
		regPane.add(jl_state);

		JLabel jl_mehod = new JLabel("- \uAC70\uB798 \uBC29\uBC95 ");
		jl_mehod.setHorizontalAlignment(SwingConstants.CENTER);
		jl_mehod.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_mehod.setBounds(60, 246, 410, 34);
		regPane.add(jl_mehod);

		JLabel jl_area = new JLabel("- \uAC70\uB798 \uC9C0\uC5ED");
		jl_area.setHorizontalAlignment(SwingConstants.CENTER);
		jl_area.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jl_area.setBounds(60, 298, 410, 34);
		regPane.add(jl_area);

		jt_name = new JTextField();
		jt_name.setBounds(401, 70, 130, 27);
		regPane.add(jt_name);
		jt_name.setColumns(10);

		jt_price = new JTextField();
		jt_price.setColumns(10);
		jt_price.setBounds(401, 114, 130, 27);
		regPane.add(jt_price);

		jt_pphone = new JTextField();
		jt_pphone.setColumns(10);
		jt_pphone.setBounds(401, 158, 182, 27);
		regPane.add(jt_pphone);

		jt_explain = new JTextField();
		jt_explain.setColumns(10);
		jt_explain.setBounds(401, 353, 343, 90);
		regPane.add(jt_explain);

		jr_no = new JRadioButton("사용감 없음");
		jr_no.setFont(new Font("제주고딕", Font.PLAIN, 17));
		jr_no.setBackground(Color.WHITE);
		jr_no.setBounds(557, 197, 119, 34);
		regPane.add(jr_no);
		jr_no.setActionCommand("사용감 없음");

		jr_no.setSelected(true);

		jr_yes = new JRadioButton("사용감 있음");
		jr_yes.setFont(new Font("제주고딕", Font.PLAIN, 17));
		jr_yes.setBackground(Color.WHITE);
		jr_yes.setBounds(401, 197, 119, 34);

		regPane.add(jr_yes);
		jr_yes.setActionCommand("사용감 있음");

		group = new ButtonGroup();
		group.add(jr_yes);
		group.add(jr_no);

		jc_method = new JComboBox();
		jc_method.setModel(new DefaultComboBoxModel(new String[] { "직거래", "배송", "직거래 또는 배송" }));
		jc_method.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jc_method.setBackground(Color.WHITE);
		jc_method.setBounds(401, 252, 152, 23);
		regPane.add(jc_method);

		jc_area = new JComboBox();
		jc_area.setModel(new DefaultComboBoxModel(new String[] { "강원도", "경기도", "경상남도", "경상북도", "광주광역시", "대구광역시",
				"대전광역시", "부산광역시", "서울특별시", "세종특별자치시", "울산광역시", "인천광역시", "전라남도", "전라북도", "제주도특별자치도", "충청남도", "충청북도" }));
		jc_area.setFont(new Font("제주고딕", Font.PLAIN, 19));
		jc_area.setBackground(Color.WHITE);
		jc_area.setBounds(401, 304, 152, 23);
		regPane.add(jc_area);

		btnReg = new JButton("\uC0C1\uD488 \uB4F1\uB85D");
		btnReg.setFont(new Font("제주고딕", Font.PLAIN, 19));
		btnReg.setForeground(new Color(153, 204, 255));
		btnReg.setBackground(Color.DARK_GRAY);
		btnReg.setBounds(600, 485, 119, 34);
		regPane.add(btnReg);

		btnReset = new JButton("\uC0C1\uD488 \uCDE8\uC18C");
		btnReset.setFont(new Font("제주고딕", Font.PLAIN, 19));
		btnReset.setForeground(new Color(153, 204, 255));
		btnReset.setBackground(Color.DARK_GRAY);
		btnReset.setBounds(748, 485, 119, 34);
		regPane.add(btnReset);

		main.getContentPane().add(regPane, BorderLayout.CENTER);

		lblNewLabel = new JLabel(
				"\u203B\uCC44\uD305 \uAC00\uB2A5\uC2DC\uAC04\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694.");
		lblNewLabel.setBounds(401, 453, 343, 15);
		regPane.add(lblNewLabel);
		main.setVisible(true);

		MemberRegisterEvent regEvent = new MemberRegisterEvent();
		jr_yes.addActionListener(regEvent);
		jr_no.addActionListener(regEvent);
		btnReg.addActionListener(regEvent);
		btnReset.addActionListener(regEvent);

	}// register method

	// regFormCheck
	public boolean regFormCheck() {
		boolean result = false;

		if (jt_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("이름을 입력해주세요"));
			jt_name.requestFocus();
		} else if (jt_price.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("가격를 입력해주세요"));
			jt_price.requestFocus();
		} else if (jt_pphone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("거래 시 연락처를 입력해주세요"));
			jt_pphone.requestFocus();
		} else if (jt_explain.getText().equals("")) {
			JOptionPane.showMessageDialog(null, main.getMsg("설명을 입력해주세요"));
			jt_explain.requestFocus();
		} else if (Integer.parseInt(jt_price.getText().trim()) < 0) {
			JOptionPane.showMessageDialog(null, main.getMsg("가격를 다시 입력해주세요"));
			jt_price.setText("");
			jt_price.requestFocus();
		} else {
			result = true;
		}

		return result;
	}// regFormCheck method

	// registerProc
	public void registerProc() {
		// MemberVO 객체를 생성하여 등록
		ProductVO vo = new ProductVO();
		vo.setMid(mvo.getId());
		vo.setPrice(Integer.parseInt(jt_price.getText().trim()));
		vo.setPname(jt_name.getText().trim());
		vo.setState(group.getSelection().getActionCommand());
		vo.setExplain(jt_explain.getText().trim());
		vo.setPphone(jt_pphone.getText().trim());
		vo.setMethod(jc_method.getSelectedItem().toString());
		vo.setArea(jc_area.getSelectedItem().toString());

		// member 테이블에 등록
		boolean result = main.system.register(vo);
		if (result) {
			JOptionPane.showMessageDialog(null, "등록 성공!!");
			jt_name.setText("");
			jt_price.setText("");
			jt_explain.setText("");
			jt_pphone.setText("");
			jt_name.requestFocus();
		} else {
			JOptionPane.showMessageDialog(null, "등록 실패!!");
		}


	}

	// 이벤트 처리 클래스
	class MemberRegisterEvent implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (btnReg == obj) {
				if (regFormCheck())
					registerProc();
			} else if (btnReset == obj) {
				jt_name.setText("");
				jt_price.setText("");
				jt_explain.setText("");
				jt_pphone.setText("");
				jt_name.requestFocus();
			}
		}

	}// event class

}// class
