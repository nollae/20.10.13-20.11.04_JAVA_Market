package market;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;

public class MarketUpdate {
	// Field
	MarketMgmUI main;
	MarketDAO dao;
	JPanel img_update;
	JPanel updatePane, mainPane;
	int idx = -1;

	JPanel update_top, search_panel, update_bottom;
	JTextField tf_update_last;
	JButton update_search;
	ArrayList<String> tf_update_list;

	MarketUpdateEvent eventObj = new MarketUpdateEvent();
	JTextField tf_update;
	JLabel label_pname, label_explain;
	JButton btnUpdate, btnUpdateReset;
	JLabel label_price, label_phone, label_state, label_method;
	JTextField tf_pname, tf_price, tf_phone, tf_explain;
	JRadioButton btn_use, btn_unuse;
	JComboBox combo_area;
	ButtonGroup group;
	MarketRegister rg;
	String pid;
	
	private JComboBox combo_method;

	// Constructor
	public MarketUpdate() {
	}

	public MarketUpdate(MarketMgmUI main, MarketDAO dao) {
		this.main = main;
		this.updatePane = main.updatePane;
		this.dao = dao;
	}

	// Method
	/**
	 * @wbp.parser.entryPoint
	 */
	public void update() {
//		JPanel updatePane=new  JPanel();
		tf_update_list = new ArrayList<String>();

		main.switchPane(MarketMgmUI.UPDATE);
		updatePane.setLayout(null);

		JLabel title_label = new JLabel("※ 수정할 게시물의 번호를 입력해주세요 ※");
		title_label.setFont(new Font("제주고딕", Font.PLAIN, 13));
		title_label.setBounds(361, 86, 245, 32);
		updatePane.add(title_label);

		JLabel id_label = new JLabel("[ 게시물 번호 ]");
		id_label.setFont(new Font("제주고딕", Font.PLAIN, 20));
		id_label.setBounds(140, 47, 150, 40);
		updatePane.add(id_label);

		tf_update = new JTextField();
		tf_update.setBounds(290, 57, 375, 25);
		tf_update.setColumns(10);
		updatePane.add(tf_update);

		update_search = new JButton("\uAC80  \uC0C9");
		update_search.setBackground(Color.DARK_GRAY);
		update_search.setForeground(new Color(153, 204, 255));
		update_search.setFont(new Font("제주고딕", Font.PLAIN, 16));
		update_search.setBounds(699, 52, 79, 32);
		updatePane.add(update_search);
		

		main.getContentPane().add(updatePane);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);

		tf_update.addActionListener(eventObj);
		update_search.addActionListener(eventObj);
	}

	/** 수정 검색 처리 */
	public void updateSearchProc() {
		pid = tf_update.getText().trim();
		if (pid.equals("")) {
			JOptionPane.showMessageDialog(null, "물품번호를 입력해주세요");
			tf_update.requestFocus();
		} else {
			idx = main.system.SearchPid(pid);
			if (idx != 0) {
				ProductVO pvo = main.system.selectProduct(pid);
				updateOkForm(pvo);

			} else {
				updateFailForm();
			}
		}
	}

	/** 수정 데이터 등록 폼 : 물품명, 주소, 설명, 가격 **/
	public void updateOkForm(ProductVO pvo) {
		update();

		label_pname = new JLabel("-   \uC0C1\uD488 \uC774\uB984");
		label_pname.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_pname.setBounds(179, 112, 101, 40);
		updatePane.add(label_pname);

		label_price = new JLabel("-   \uC0C1\uD488 \uAC00\uACA9");
		label_price.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_price.setBounds(179, 157, 101, 40);
		updatePane.add(label_price);

		label_phone = new JLabel("-  \uAC70\uB798\uC2DC\uC5F0\uB77D\uCC98");
		label_phone.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_phone.setBounds(179, 202, 127, 40);
		updatePane.add(label_phone);

		label_state = new JLabel("-   \uC0C1\uD488 \uC0C1\uD0DC");
		label_state.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_state.setBounds(179, 247, 99, 40);
		updatePane.add(label_state);

		label_method = new JLabel("-   \uAC70\uB798 \uBC29\uBC95");
		label_method.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_method.setBounds(179, 292, 101, 40);
		updatePane.add(label_method);

		tf_pname = new JTextField();
		tf_pname.setFont(new Font("제주고딕", Font.PLAIN, 14));
		tf_pname.setColumns(10);
		tf_pname.setBounds(371, 123, 284, 25);
		updatePane.add(tf_pname);

		tf_price = new JTextField();
		tf_price.setFont(new Font("제주고딕", Font.PLAIN, 14));
		tf_price.setColumns(10);
		tf_price.setBounds(371, 167, 284, 25);
		updatePane.add(tf_price);

		tf_phone = new JTextField();
		tf_phone.setFont(new Font("제주고딕", Font.PLAIN, 14));
		tf_phone.setColumns(10);
		tf_phone.setBounds(371, 211, 284, 25);
		updatePane.add(tf_phone);

		tf_explain = new JTextField();
		tf_explain.setFont(new Font("제주고딕", Font.PLAIN, 14));
		tf_explain.setColumns(10);
		tf_explain.setBounds(371, 396, 394, 60);
		updatePane.add(tf_explain);

		btnUpdate = new JButton("\uC218\uC815 \uC644\uB8CC");
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setForeground(new Color(153, 204, 255));
		btnUpdate.setFont(new Font("제주고딕", Font.PLAIN, 16));
		btnUpdate.setBounds(595, 474, 101, 28);
		updatePane.add(btnUpdate);

		btnUpdateReset = new JButton("\uB2E4\uC2DC \uC791\uC131");
		btnUpdateReset.setForeground(new Color(153, 204, 255));
		btnUpdateReset.setFont(new Font("제주고딕", Font.PLAIN, 16));
		btnUpdateReset.setBackground(Color.DARK_GRAY);
		btnUpdateReset.setBounds(719, 474, 101, 28);
		updatePane.add(btnUpdateReset);

		label_explain = new JLabel("-   \uC0C1\uD488 \uC815\uBCF4");
		label_explain.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_explain.setBounds(179, 382, 101, 40);
		updatePane.add(label_explain);

		btn_use = new JRadioButton(" \uC0AC\uC6A9\uAC10 \uC788\uC74C");
		btn_use.setBackground(Color.WHITE);
		btn_use.setFont(new Font("제주고딕", Font.PLAIN, 14));
		btn_use.setBounds(371, 257, 107, 23);
		btn_use.setActionCommand("사용감 있음");
		updatePane.add(btn_use);

		btn_unuse = new JRadioButton(" \uC0AC\uC6A9\uAC10 \uC5C6\uC74C");
		btn_unuse.setBackground(Color.WHITE);
		btn_unuse.setFont(new Font("제주고딕", Font.PLAIN, 14));
		btn_unuse.setBounds(499, 257, 107, 23);
		btn_unuse.setActionCommand("사용감 없음");
		btn_unuse.setSelected(true);
		updatePane.add(btn_unuse);

		group = new ButtonGroup();
		group.add(btn_use);
		group.add(btn_unuse);

		JLabel label_area = new JLabel("-   \uAC70\uB798 \uC9C0\uC5ED");
		label_area.setFont(new Font("제주고딕", Font.PLAIN, 18));
		label_area.setBounds(179, 337, 101, 40);
		updatePane.add(label_area);

		combo_method = new JComboBox();
		combo_method.setBackground(Color.WHITE);
		combo_method.setModel(new DefaultComboBoxModel(
				new String[] { "\uC9C1\uAC70\uB798", "\uBC30\uC1A1", "\uC9C1\uAC70\uB798 \uB610\uB294 \uBC30\uC1A1" }));
		combo_method.setFont(new Font("제주고딕", Font.PLAIN, 14));
		combo_method.setBounds(371, 302, 127, 23);
		updatePane.add(combo_method);

		combo_area = new JComboBox();
		combo_area.setBackground(Color.WHITE);
		combo_area.setModel(new DefaultComboBoxModel(new String[] { "\uAC15\uC6D0\uB3C4", "\uACBD\uAE30\uB3C4",
				"\uACBD\uC0C1\uB0A8\uB3C4", "\uACBD\uC0C1\uBD81\uB3C4", "\uAD11\uC8FC\uAD11\uC5ED\uC2DC",
				"\uB300\uAD6C\uAD11\uC5ED\uC2DC", "\uB300\uC804\uAD11\uC5ED\uC2DC", "\uBD80\uC0B0\uAD11\uC5ED\uC2DC",
				"\uC11C\uC6B8\uD2B9\uBCC4\uC2DC", "\uC138\uC885\uD2B9\uBCC4\uC790\uCE58\uC2DC",
				"\uC6B8\uC0B0\uAD11\uC5ED\uC2DC", "\uC778\uCC9C\uAD11\uC5ED\uC2DC", "\uC804\uB77C\uB0A8\uB3C4",
				"\uC804\uB77C\uBD81\uB3C4", "\uC81C\uC8FC\uD2B9\uBCC4\uC790\uCE58\uB3C4", "\uCDA9\uCCAD\uB0A8\uB3C4",
				"\uCDA9\uCCAD\uB0A8\uB3C4" }));
		combo_area.setFont(new Font("제주고딕", Font.PLAIN, 14));
		combo_area.setBounds(371, 347, 127, 23);
		updatePane.add(combo_area);


		String[] data_list = new String[20];
		data_list[0] = pvo.getPname();
		data_list[1] = String.valueOf(pvo.getPrice());
		data_list[2] = pvo.getPphone();
		data_list[3] = pvo.getState();
		data_list[4] = pvo.getMethod();
		data_list[5] = pvo.getArea();
		data_list[6] = pvo.getExplain();

		tf_pname.setText(data_list[0]);
		tf_price.setText(data_list[1]);
		tf_phone.setText(data_list[2]);

		if (pvo.getState().equals("사용감 있음")) {
			btn_use.setSelected(true);
			btn_use.setText(data_list[3]);
		} else {
			btn_unuse.setSelected(true);
			btn_unuse.setText(data_list[3]);
		}
		for (int i = 0; i < combo_method.getItemCount(); i++) {
			String item = (String) combo_method.getItemAt(i);
			if (item.equals(data_list[4])) {
				combo_method.setSelectedItem(data_list[4]);
			}
		}

		for (int i = 0; i < combo_area.getItemCount(); i++) {
			String item = (String) combo_area.getItemAt(i);
			if (item.equals(data_list[5])) {
				combo_area.setSelectedItem(data_list[5]);
			}
		}

		tf_explain.setText(data_list[6]);

		tf_update_list.add(tf_pname.getText());
		tf_update_list.add(tf_price.getText());
		tf_update_list.add(tf_phone.getText());
		if (btn_use.isSelected()) {
			tf_update_list.add(btn_use.getText());
		} else {
			tf_update_list.add(btn_use.getText());
		}

		tf_update_list.add(combo_method.getSelectedItem().toString());
		tf_update_list.add(combo_area.getSelectedItem().toString());
		tf_update_list.add(tf_explain.getText());

		main.getContentPane().add(updatePane);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);

		btn_use.addActionListener(eventObj);
		btn_unuse.addActionListener(eventObj);
		btnUpdate.addActionListener(eventObj);
		btnUpdateReset.addActionListener(eventObj);
	}

	/** 업데이트 실패 */
	public void updateFailForm() {
		update_bottom.removeAll();
		update_bottom.add(BorderLayout.NORTH, new JLabel());
		update_bottom.add(BorderLayout.CENTER, new JLabel("!! 물품의 정보가 존재하지 않습니다 !!"));

		updatePane.add(BorderLayout.CENTER, update_bottom);
		main.getContentPane().add(updatePane);
		main.setVisible(true);

	}

	/** 수정처리 메소드 */
	public void updateProc(String name) {
		ArrayList<String> dataList = new ArrayList<String>();

		dataList.add(tf_pname.getText().trim());
		dataList.add(tf_price.getText().trim());
		dataList.add(tf_phone.getText().trim());
		if (btn_use.getText().trim().equals("사용감 있음")) {
			dataList.add(btn_use.getText().trim());
		} else {
			dataList.add(btn_unuse.getText().trim());
		}
		dataList.add(combo_method.getSelectedItem().toString().trim());
		dataList.add(combo_area.getSelectedItem().toString().trim());
		dataList.add(tf_explain.getText().trim());

		ProductVO pvo = new ProductVO();
		pvo.setPid(pid);
		pvo.setPname(dataList.get(0));
		pvo.setPrice(Integer.parseInt(dataList.get(1)));
		pvo.setPphone(dataList.get(2));
		pvo.setState(dataList.get(3));
		pvo.setMethod(dataList.get(4));

		pvo.setArea(dataList.get(5));
		pvo.setExplain(dataList.get(6));

		if (main.system.update_pr(pvo, main.vo)) {
			JOptionPane.showMessageDialog(null, "수정이 완료됐습니다");
			new MarketSearch(main, main.system.dao).search("show_all");
		  }else {
			JOptionPane.showMessageDialog(null, "수정을 실패했습니다");
		}

	}

	/** 이벤트 처리 클래스 */
	class MarketUpdateEvent implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (obj == tf_update || obj == update_search) {
				updateSearchProc(); // 검색명 확인 후 수정 폼 데이터 입력

			} else if (obj == btnUpdate) {
				updateProc(main.vo.getId());
			} else if (obj == btnUpdateReset) {
				tf_pname.setText("");
				tf_price.setText("");
				tf_phone.setText("");
				btn_use.setSelected(true);
				btn_unuse.setSelected(false);
				combo_method.getSelectedItem().toString().equals("직거래");
				combo_area.getSelectedItem().toString().equals("강원도");
				tf_explain.setText("");
			}
		}
	}
}
