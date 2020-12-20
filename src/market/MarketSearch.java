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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class MarketSearch implements TableModelListener {
//Field
	MarketMgmUI main;
	JPanel searchPane;
	JPanel jp_search, search_pack;
	JLabel jl_searchName, block1, block2, block3, block4;
	JButton btn_search;
	JTextField jt_search;
	ArrayList<ProductVO> plist;
	MyTableModel model;
	MarketDAO dao;

//Constructor
	public MarketSearch(MarketMgmUI main, MarketDAO dao) {
		this.main = main;
		this.searchPane = main.searchPane;
		this.dao = dao;
	}

//Method
	/**
	 * @wbp.parser.entryPoint
	 */
	public void search(String pname) {
		main.switchPane(MarketMgmUI.SEARCH);
		searchPane.setLayout(null);

		jp_search = new JPanel();
		search_pack = new JPanel();

		block1 = new JLabel();
		jp_search.add(block1);

		jl_searchName = new JLabel("[ 상품이름 ]  ");
		jl_searchName.setFont(new Font("제주고딕", Font.PLAIN, 16));
		jl_searchName.setBounds(205, 28, 58, 15);
		jp_search.add(jl_searchName);

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

		searchPane.add(search_pack);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		if (pname.equals("show_all")) {
			plist = dao.search_list();
		} else {
			plist = dao.search_list(pname);
		}
		model = new MyTableModel(plist);
		JTable table = new JTable(model);

		table.getColumn("구매").setCellRenderer(dtcr);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.white);
		jp_search.setBackground(Color.white);

		search_pack.setBackground(Color.white);

		table.getColumn(table.getColumnName(0)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(1)).setPreferredWidth(70);
		table.getColumn(table.getColumnName(2)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(3)).setPreferredWidth(40);
		table.getColumn(table.getColumnName(4)).setPreferredWidth(60);
		table.getColumn(table.getColumnName(5)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(6)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(8)).setPreferredWidth(230);
		table.getColumn(table.getColumnName(9)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(10)).setPreferredWidth(40);
			
		table.setPreferredScrollableViewportSize(new Dimension(1200,535));

		MyTableCellRenderer tcr = new MyTableCellRenderer(this, main);
		table.getColumnModel().getColumn(10).setCellEditor(tcr);
		table.getColumnModel().getColumn(10).setCellRenderer(tcr);

		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
		}
		JScrollPane pane = new JScrollPane(table);

		table.setRowHeight(table.getRowHeight() + 70);
		table.setFillsViewportHeight(true);

		searchPane.setLayout(new BorderLayout());
		searchPane.add(BorderLayout.SOUTH, pane);
		searchPane.add(BorderLayout.NORTH, search_pack);
		main.add(searchPane, BorderLayout.CENTER);
		main.setVisible(true);

		table.getModel().addTableModelListener(this);
		btn_search.addActionListener(new MemberSearchEvent());
		jt_search.addActionListener(new MemberSearchEvent());
	}// search method

	// 이벤트 처리 클래스
	class MemberSearchEvent implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if (!jt_search.getText().equals("") || obj == btn_search) {
				String pname = jt_search.getText().trim();
				if (dao.searchDataCheck(pname)) {
					search(pname);
					jt_search.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "해당 물품이 존재하지 않습니다.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "물품명을 입력해주세요.");
				jt_search.requestFocus();
			}
		}
	}

	public void tableChanged(TableModelEvent e) {
		System.out.println(e.getSource());
		int row = e.getFirstRow();
		int column = e.getColumn();

		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
	}

}