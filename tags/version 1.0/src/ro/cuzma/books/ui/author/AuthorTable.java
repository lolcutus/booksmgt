package ro.cuzma.books.ui.author;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import ro.cuzma.books.Author;
import ro.cuzma.books.AuthorData;
import ro.cuzma.books.BookRow;
import ro.cuzma.books.BookData;
import ro.cuzma.ui.Column;
import ro.cuzma.ui.TableWithColumn;
import ro.cuzma.ui.TableWithColumnModel;


//	public class BookTable extends TableWithColumn {
public class AuthorTable extends TableWithColumn {
	int	readColumn	= -1;

	public AuthorTable(AuthorData tableRows, JTextArea messageText) {
		super(tableRows, messageText);
		// this.addMouseListener(new BookTableMouseListener(this));
	}

	

	protected void createAllColumns() {
		Column tmp = new Column("Pos", Integer.class, 0, Author.COLUMN_ID, 30);
		addColumn(tmp);
		tmp = new Column("First Name", String.class, 1, Author.COLUMN_FIRST_NAME, 100);
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Middle Nane", String.class, 2, Author.COLUMN_MIDDLE_NAME, 100);
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Name", String.class, 3, Author.COLUMN_NAME, 100);
		addColumn(tmp);
			}

	public void deleteSelectedBook() {
		if (((TableWithColumnModel) this.getModel()).getEdit()) {
			int selRow = this.getSelectedRow();
			if (selRow >= 0) {
				BookRow book = (BookRow) this.getValueAt(selRow, 0);
				if (JOptionPane.showConfirmDialog(null, "Do you want to delete book: " +  book.getDisplayValue(BookRow.COLUMN_TITLE), "Delete book", JOptionPane.YES_NO_OPTION) == 0) {
					((BookData) this.getTableData()).deleteRow(book);
					this.setNewData(this.reloadData());
					this.revalidate();
					this.repaint();
				}
			} else {
				JOptionPane.showMessageDialog(null, "No row selected!!!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Not in edit mode!!!");
		}
	}
}
