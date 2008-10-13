package ro.cuzma.books.ui.book;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.apache.log4j.Logger;

import ro.cuzma.books.BookRow;
import ro.cuzma.ui.TableWithColumnModel;

public class BookCellRenderJLabel extends DefaultTableCellRenderer {
	public static final Color	COL_PAPER		= new Color(255, 255, 215);
	public static final Color	COL_EDIT		= new Color(225, 225, 255);
	public static final Color	COL_NOT_READ	= new Color(255, 198, 198);

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		BookCellRenderJLabel.setColors(this, table, value, selected, focused, row, column);
		if (((TableWithColumnModel) table.getModel()).getColumnClass(column) == Integer.class || ((TableWithColumnModel) table.getModel()).getColumnClass(column) == Double.class
						|| ((TableWithColumnModel) table.getModel()).getColumnClass(column) == Float.class || ((TableWithColumnModel) table.getModel()).getColumnClass(column) == Long.class) {
			this.setHorizontalAlignment(JLabel.RIGHT);
		} else {
			this.setHorizontalAlignment(JLabel.LEFT);
		}
		super.getTableCellRendererComponent(table, value, selected, focused,
		row, column);
		return this;
	}

	public static void setColors(Component comp, JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		comp.setEnabled(table == null || table.isEnabled()); // see question
		// above
		BookRow tmp = (BookRow) table.getValueAt(row, 0);
		Color foreground = null;
		Color background = null;
		if (selected) {
			comp.setForeground((foreground != null) ? foreground : table.getSelectionForeground());
			comp.setBackground(table.getSelectionBackground());
		} else {
			 comp.setForeground(table.getForeground());

			if (((TableWithColumnModel) table.getModel()).getEdit()) {
				if (((TableWithColumnModel) table.getModel()).isCellEditable(row, column)) {
					comp.setBackground(BookCellRenderJLabel.COL_EDIT);
				} else {
					
						if ((tmp.getDisplayValue(BookRow.COLUMN_FORMAT).toString().equals("Paper"))) {
							comp.setBackground(BookCellRenderJLabel.COL_PAPER);
						} else {
							comp.setBackground(null);
						}
					
				}
			} else {
				if (tmp.getDisplayValue(BookRow.COLUMN_FORMAT) == null) {
					comp.setBackground(null);
				} else {
					if (((Integer)tmp.getValue(BookRow.COLUMN_READ)).intValue() == 0 && (column == 0 || column == 1)) {
						comp.setBackground(BookCellRenderJLabel.COL_NOT_READ);
					} else {
						//Logger.getLogger("ro.cuzma.books.ui.book.BookCellRenderJLabel").debug(tmp.getValue(Book.COLUMN_FORMAT));
						if ((tmp.getValue(BookRow.COLUMN_FORMAT).toString().equals("Paper"))) {
							comp.setBackground(BookCellRenderJLabel.COL_PAPER);
						} else {
							comp.setBackground(null);
						}
					}
				}
			}
		}
	}
}
