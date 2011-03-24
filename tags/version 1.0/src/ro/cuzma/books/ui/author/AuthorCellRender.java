package ro.cuzma.books.ui.author;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ro.cuzma.books.BookRow;
import ro.cuzma.ui.TableWithColumnModel;

public class AuthorCellRender extends DefaultTableCellRenderer {
	private final Color	edit	= new Color(225, 225, 255);

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		setEnabled(table == null || table.isEnabled()); // see question
		// above
		//Author tmp = (Author) table.getValueAt(row, 0);
		// Color col = new Color()
		//
		if (((TableWithColumnModel) table.getModel()).getEdit()) {
			if (((TableWithColumnModel) table.getModel()).isCellEditable(row, column)) {
				setBackground(edit);
			} else {
				setBackground(null);
			}
		} else {
			setBackground(null);
		}
		if (((TableWithColumnModel) table.getModel()).getColumnClass(column) == Integer.class || ((TableWithColumnModel) table.getModel()).getColumnClass(column) == Double.class
						|| ((TableWithColumnModel) table.getModel()).getColumnClass(column) == Float.class) {
			this.setHorizontalAlignment(JLabel.RIGHT);
		} else {
			this.setHorizontalAlignment(JLabel.LEFT);
		}
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		return this;
	}
}
