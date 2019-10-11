package project.GUI;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class TableClass extends JFrame{
	
	public JTable table;

	public TableClass(DotH d) {
		
		TableModel model = new TableModel(d);
		table = new JTable(model);

	}
	
	class TableModel extends AbstractTableModel{
		
		ArrayList<Element> data;
		
		public TableModel(DotH d) {
			this.data = new ArrayList<Element>(d.methods.member);
			data.addAll(d.datas.member);
		}
		
		String[] colunmName = {"Name", "Type", "Access"};
		
		@Override
		public int getRowCount() {
			return data.size();
		}
		@Override
		public int getColumnCount() {
			return colunmName.length;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Element element = data.get(rowIndex);
			Object value = null;
			switch(columnIndex) {
			case 0: 
				if(element.bool) {
					if(element.parameter == null) {
						value = element.name+"()";
					}
					else
						value = element.name +"("+element.parameter+")";
				}
				else
					value = element.name;
				break;
			case 1:
				if(element.type == null) {
					value = "void";
				}
				else
					value = element.type;
				break;
			case 2:
				value = element.access;
				break;
			}
			return value;
		}
		
		public String getColumnName(int col) {
			return colunmName[col];
		}
		
		public Class getColumnClass(int c) {
			Class type = String.class;
			return type;
		}
		
	}

	
}
