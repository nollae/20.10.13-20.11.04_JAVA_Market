package market;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.SystemColor;

public class MyTableCellRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{	
	MarketSearch market_s;
	MarketMgmUI main;
	ProductVO vo;
	MemberVO mvo = new MemberVO();

	public MyTableCellRenderer(MarketSearch market_s,MarketMgmUI main) {
		this.market_s = market_s ;
		this.main = main;
		this.mvo=main.vo;
	}
	
	public Component getTableCellRendererComponent(JTable table,Object value,	boolean isSelected,boolean hasFocus,int row,	int column) {
		JButton comp =null;
		if(column ==10) {
			 comp = new JButton("����");
			 comp.setForeground(Color.DARK_GRAY);
			 comp.setBackground(SystemColor.controlHighlight);
		}
		return comp;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,int row, int column) {
		JButton btn_buy = new JButton("����");
		btn_buy.setForeground(Color.DARK_GRAY);
		btn_buy.setBackground(SystemColor.controlHighlight);
		
		btn_buy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?");
				 if (result == 0) {
					 int review_result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
					 if(review_result == 0) {
						  String comm=JOptionPane.showInputDialog(null, "���並 �Է����ּ���");
						  
						  ProductVO vo = market_s.plist.get(row);
						  ReviewVO rvo = new ReviewVO();
						  rvo.setMid(vo.getMid());
						  rvo.setPid(vo.getPid());
						  rvo.setEvaluation(comm);				  
						  
						  market_s.dao.product_row(vo);
						  market_s.dao.review_insert(comm,rvo);
						  market_s.dao.delete_review(rvo.getPid());	
						  						  
						  new MarketSearch(main, market_s.dao).search("show_all");	
					 }else {
						 JOptionPane.showMessageDialog(null,"���� ����� ��ҵǾ����ϴ�.");
					 }
				}else {
					JOptionPane.showMessageDialog(null,"��ǰ ���Ű� ��ҵǾ����ϴ�.");
				}
			}			
		});
			return btn_buy;	
	}
	
	public Object getCellEditorValue() {
		return null;
	}
	
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}
	
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

}
