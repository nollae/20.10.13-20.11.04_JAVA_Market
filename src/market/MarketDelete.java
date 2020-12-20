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

public class MarketDelete {
	MarketMgmUI main;
	JPanel deletePane;
	JPanel jp_delete_search, jp_deleteResult, search_pack;
	JTextField jt_deleteSearch;
	JButton deleteSearch;
	JLabel jl_deleteSearchName, block1, block2, block3, block4;
	MemberVO mvo = new MemberVO();
	Object[] columns = { "�Խù���ȣ", "��ǰ�̸�", "����", "����ó", "����", "�ŷ����", "�ŷ�����", "��ǰ����", "�����" };
	Object[] row = new Object[9];
	DefaultTableModel model = new DefaultTableModel(columns, 0);
	JTable table = new JTable(model);

	public MarketDelete() {
	}

	public MarketDelete(MarketMgmUI main) {
		this.main = main;
		this.deletePane = main.deletePane;
		this.mvo = main.vo;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void delete() {
		main.switchPane(MarketMgmUI.DELETE);
		deletePane.setLayout(null);

		jp_delete_search = new JPanel();
		jp_deleteResult = new JPanel();
		search_pack = new JPanel();

		block1 = new JLabel();
		jp_delete_search.add(block1);

		jl_deleteSearchName = new JLabel("[ �Խù� ��ȣ ]  ");
		jl_deleteSearchName.setFont(new Font("���ְ��", Font.PLAIN, 16));
		jl_deleteSearchName.setBounds(205, 28, 58, 15);
		jp_delete_search.add(jl_deleteSearchName);

		block2 = new JLabel("  ");
		jp_delete_search.add(block2);

		jt_deleteSearch = new JTextField();
		jt_deleteSearch.setBounds(284, 25, 156, 21);
		jp_delete_search.add(jt_deleteSearch);
		jt_deleteSearch.setColumns(20);

		block3 = new JLabel("   ");
		jp_delete_search.add(block3);

		deleteSearch = new JButton("�� ��");
		deleteSearch.setForeground(new Color(160, 204, 255));
		deleteSearch.setBackground(Color.DARK_GRAY);
		deleteSearch.setFont(new Font("���ְ��", Font.PLAIN, 16));
		deleteSearch.setBounds(452, 24, 64, 23);
		jp_delete_search.add(deleteSearch);

		block4 = new JLabel();
		jp_delete_search.add(block4);

		search_pack.add(new JLabel("  "));
		search_pack.add(jp_delete_search);
		search_pack.add(new JLabel("  "));
		search_pack.setLayout(new GridLayout(3, 1));

		deletePane.add(jp_deleteResult);
		deletePane.add(search_pack); // ��ȭ
		crateJTableData();
		model.setColumnIdentifiers(columns);
		table.setModel(model);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.white);
		jp_delete_search.setBackground(Color.white);
		jp_deleteResult.setBackground(Color.white);
		search_pack.setBackground(Color.white);

		table.getColumn("�Խù���ȣ").setCellRenderer(dtcr);
		table.getColumn("��ǰ�̸�").setCellRenderer(dtcr);
		table.getColumn("����").setCellRenderer(dtcr);
		table.getColumn("����ó").setCellRenderer(dtcr);
		table.getColumn("����").setCellRenderer(dtcr);
		table.getColumn("�ŷ����").setCellRenderer(dtcr);
		table.getColumn("�ŷ�����").setCellRenderer(dtcr);
		table.getColumn("��ǰ����").setCellRenderer(dtcr);
		table.getColumn("�����").setCellRenderer(dtcr);

		JScrollPane pane = new JScrollPane(table);

		table.getColumn(table.getColumnName(0)).setPreferredWidth(40);
		table.getColumn(table.getColumnName(1)).setPreferredWidth(70);
		table.getColumn(table.getColumnName(2)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(5)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(6)).setPreferredWidth(50);
		table.getColumn(table.getColumnName(7)).setPreferredWidth(230);
		table.getColumn(table.getColumnName(8)).setPreferredWidth(50);

		table.setPreferredScrollableViewportSize(new Dimension(1200, 535));
		table.setRowHeight(table.getRowHeight() + 70);
		table.setFillsViewportHeight(true);

		jp_deleteResult.setLayout(new BorderLayout());
		deletePane.setLayout(new BorderLayout());

		deletePane.add(BorderLayout.CENTER, jp_deleteResult);
		deletePane.add(BorderLayout.SOUTH, pane);
		deletePane.add(BorderLayout.NORTH, search_pack);
		main.add(deletePane, BorderLayout.CENTER);

		main.setVisible(true);

		jt_deleteSearch.addActionListener(new MemberDeleteEvent());
		deleteSearch.addActionListener(new MemberDeleteEvent());
	}// delete method

	// ��ü����Ʈ JTableDate
	public void crateJTableData() {
		ArrayList<ProductVO> plist = main.system.delete_list(mvo);
		model.setNumRows(0);

		for (ProductVO vo : plist) {
			if (vo != null) {
				row[0] = vo.getPid();
				row[1] = vo.getPname();
				row[2] = vo.getPrice();
				row[3] = vo.getPphone();
				row[4] = vo.getState();
				row[5] = vo.getMethod();
				row[6] = vo.getArea();
				row[7] = vo.getExplain();
				row[8] = vo.getPdate();

				model.addRow(row);
			}
			table.repaint();
		}
		model.fireTableDataChanged();
	}

	// deleteDataCheck

	public boolean deleteDataCheck(String name, MemberVO mvo) {
		return main.system.delselect(name, mvo);
	}

	// deleteProc
	public void deleteProc(String name) {
		boolean result = main.system.delete(name);
		if (result) {
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�");
		} else {
			JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�");
		}
	}// deleteProc

	// �̺�Ʈ ó�� Ŭ����
	class MemberDeleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();

			if (!jt_deleteSearch.getText().equals("") || obj == deleteSearch) {
				String name = jt_deleteSearch.getText().trim();

				if (deleteDataCheck(name, mvo)) {
					int result = JOptionPane.showConfirmDialog(null, main.getMsg("������ �����Ͻðڽ��ϱ�?"));
					if (result == 0)
						deleteProc(name);
					jt_deleteSearch.setText("");
					deletePane.setVisible(false);
					delete();
				} else {
					// ������ ������ ����
					JOptionPane.showMessageDialog(null, main.getMsg("������ �����Ͱ� �������� �ʽ��ϴ�."));
				}
			} else {
				JOptionPane.showMessageDialog(null, main.getMsg("�������� �Է����ּ���"));
				jt_deleteSearch.requestFocus();
			}
		}
	}
}