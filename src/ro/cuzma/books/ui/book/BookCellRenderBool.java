package ro.cuzma.books.ui.book;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import ro.cuzma.books.BookRow;
import ro.cuzma.ui.TableWithColumnModel;

public class BookCellRenderBool extends JCheckBox implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		BookCellRenderJLabel.setColors(this, table, value, selected, focused, row, column);
		this.setHorizontalAlignment(JLabel.CENTER);
		//System.out.println("BookCellRenderBool: " + value);
		setSelected((value != null && ((Boolean) value).booleanValue()));
		return this;
	}
}
