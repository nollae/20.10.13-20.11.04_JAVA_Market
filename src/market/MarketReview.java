package market;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class MarketReview {
// Field
	MarketMgmUI main;
	JPanel reviewPane;
	JPanel jp_search, search_pack;
	JLabel jl_reviewName, block1, block2, block3, block4;
	JButton btn_search;
	JTextField jt_search;
	Object[] columns = { "판매자ID", "게시물번호", "리뷰", "리뷰등록일" };
	Object[] row = new Object[4];
	DefaultTableModel model = new DefaultTableModel(columns, 0);
	JTable table = new JTable(model);
	MarketDAO dao;

// Constructor
	public MarketReview(MarketMgmUI main, MarketDAO dao) {
		this.main = main;
		this.reviewPane = main.reviewPane;
		this.dao = dao;
	}

//Method
	/**
	 * @wbp.parser.entryPoint
	 */
	public void review() {
		main.switchPane(MarketMgmUI.REVIEW);
		reviewPane.setLayout(null);

		jp_search = new JPanel();
		search_pack = new JPanel();

		block1 = new JLabel();
		jp_search.add(block1);

		jl_reviewName = new JLabel("[ 검색 ID ]  ");
		jl_reviewName.setFont(new Font("제주고딕", Font.PLAIN, 16));
		jl_reviewName.setBounds(205, 28, 58, 15);
		jp_search.add(jl_reviewName);

		block2 = new JLabel("  ");
		jp_search.add(block2);

		jt_search = new JTextField();
		jt_search.setBounds(284, 25, 156, 21);
		jp_search.add(jt_search);
		jt_search.setColumns(20);

		block3 = new JLabel("   ");
		jp_search.add(block3);

		btn_search = new JButton("검 색");
		btn_search.setForeground(new Color(160, 204, 255));
		btn_search.setBackground(Color.DARK_GRAY);
		btn_search.setFont(new Font("제주고딕", Font.PLAIN, 16));
		btn_search.setBounds(452, 24, 64, 23);
		jp_search.add(btn_search);

		block4 = new JLabel();
		jp_search.add(block4);

		search_pack.add(new JLabel("  "));
		search_pack.add(jp_search);
		search_pack.add(new JLabel("  "));
		search_pack.setLayout(new GridLayout(3, 1));

		reviewPane.add(search_pack);

		model.setColumnIdentifiers(columns);
		table.setModel(model);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.white);
		jp_search.setBackground(Color.white);

		search_pack.setBackground(Color.white);

		table.getColumn("판매자ID").setCellRenderer(dtcr);
		table.getColumn("게시물번호").setCellRenderer(dtcr);
		table.getColumn("리뷰").setCellRenderer(dtcr);
		table.getColumn("리뷰등록일").setCellRenderer(dtcr);

		JScrollPane pane = new JScrollPane(table);

		table.getColumn(table.getColumnName(0)).setPreferredWidth(30);
		table.getColumn(table.getColumnName(1)).setPreferredWidth(20);
		table.getColumn(table.getColumnName(2)).setPreferredWidth(300);
		table.getColumn(table.getColumnName(3)).setPreferredWidth(50);

		table.setPreferredScrollableViewportSize(new Dimension(1200, 535));
		table.setRowHeight(table.getRowHeight() + 70);
		table.setFillsViewportHeight(true);

		reviewPane.setLayout(new BorderLayout());

		reviewPane.add(BorderLayout.SOUTH, pane);
		reviewPane.add(BorderLayout.NORTH, search_pack);
		main.add(reviewPane, BorderLayout.CENTER);

		main.setVisible(true);

		jt_search.addActionListener(new MemberSearchEvent());
		btn_search.addActionListener(new MemberSearchEvent());
	}// review method

	// 특정값 JTableDate
	public void crateJTableData(String mid) {
		ArrayList<ReviewVO> rlist = dao.review_list(mid);
		model.setNumRows(0);
		for (ReviewVO rvo : rlist) {
			if (rvo != null) {
				row[0] = rvo.getMid();
				row[1] = rvo.getPid();
				row[2] = rvo.getEvaluation();
				row[3] = rvo.getRdate();
				model.addRow(row);
			}
			table.repaint();
			model.fireTableDataChanged();
		}
	}

	// 이벤트 처리 클래스
	class MemberSearchEvent implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (!jt_search.getText().equals("") || obj == btn_search) {
				String mid = jt_search.getText().trim();
				if (dao.reviewDataCheck(mid)) {
					crateJTableData(mid);
					jt_search.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "해당 ID가 존재하지 않습니다.");
					jt_search.setText("");
					jt_search.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "판매자 ID 입력해주세요.");
				jt_search.requestFocus();
			}
		}
	}

}