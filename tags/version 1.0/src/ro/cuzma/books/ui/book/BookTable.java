package ro.cuzma.books.ui.book;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import ro.cuzma.books.BookRow;
import ro.cuzma.books.BookData;
import ro.cuzma.ui.Column;
import ro.cuzma.ui.TableWithColumn;
import ro.cuzma.ui.TableWithColumnModel;

//public class BookTable extends TableWithColumn {
public class BookTable extends TableWithColumn {
	/**
	 * @uml.property name="readColumn"
	 */
	int	readColumn	= -1;
	

	public BookTable(BookData tableRows, JTextArea messageText) {
		super(tableRows, messageText);
		this.addMouseListener(new BookTableMouseListener(this));
	}

	

	protected void createAllColumns() {
		int i = 0;
		BookCellRenderJLabel bcr = new BookCellRenderJLabel();
		Column tmp = new Column("Pos", Integer.class, i, BookRow.COLUMN_ID, 30);
		tmp.setCellRender(new BookCellRenderJLabel());
		i++;
		addColumn(tmp);
		tmp = new Column("Read?", Boolean.class, i, BookRow.COLUMN_TOREAD, 30);
		tmp.setEditable(true);
		tmp.setCellRender(new BookCellRenderBool());
		i++;
		addColumn(tmp);
		tmp = new Column("Title", String.class, i, BookRow.COLUMN_TITLE, 200);
		tmp.setCellRender(bcr);
		tmp.setEditable(true);
		i++;
		addColumn(tmp);
		tmp = new Column("Original Title", String.class, i, BookRow.COLUMN_ORIG_TITLE, 200, true);
		tmp.setCellRender(bcr);
		tmp.setEditable(true);
		i++;
		addColumn(tmp);
		tmp = new Column("ISBN", Long.class, i, BookRow.COLUMN_ISBN, 100);
		tmp.setEditable(true);
		tmp.setCellRender(bcr);
		i++;
		addColumn(tmp);
		
		tmp = new Column("Author", String.class, i, BookRow.COLUMN_AUTHORS, 150);
		tmp.setCellRender(bcr);
		i++;
		addColumn(tmp);
		tmp = new Column("Serie", String.class, i, BookRow.COLUMN_SERIE, 100);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Serie Pos", Integer.class, i, BookRow.COLUMN_SERIE_POS, 50);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Read", Integer.class, i, BookRow.COLUMN_READ, 50);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Book Type", String.class, i, BookRow.COLUMN_BOOK_TYPE, 60);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Language", String.class, i, BookRow.COLUMN_LANGUAGE, 60);
		tmp.setCellRender(bcr);
		i++;
		//tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Format", String.class, i, BookRow.COLUMN_FORMAT, 60);
		tmp.setCellRender(bcr);
		i++;
		//tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Status", String.class, i, BookRow.COLUMN_STATUS, 50);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp); 
		tmp = new Column("Publisher", String.class, i, BookRow.COLUMN_PUBLISHER, 80);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Colection", String.class, i, BookRow.COLUMN_COLLECTION, 80);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Collection Pos", Integer.class, i, BookRow.COLUMN_COLLECTION_POS, 60);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Bought Date", String.class, i, BookRow.COLUMN_BOUGHT_DATE, 70);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Aparition Date", String.class, i, BookRow.COLUMN_APPARITION_DATE, 70);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Price", Float.class, i, BookRow.COLUMN_PRICE, 50);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Currency", String.class, i, BookRow.COLUMN_CURRENCY, 50);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Loan Person", String.class, i, BookRow.COLUMN_LOAN_PERS, 80);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("Loan Date", String.class, i, BookRow.COLUMN_LOAN_DATE, 70);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
		tmp = new Column("File", String.class, i, BookRow.COLUMN_FILE, 300);
		tmp.setCellRender(bcr);
		i++;
		tmp.setEditable(true);
		addColumn(tmp);
	}

	public void deleteSelectedBook() {
		if (((TableWithColumnModel) this.getModel()).getEdit()) {
			int selRow = this.getSelectedRow();
			if (selRow >= 0) {
				BookRow book = (BookRow) this.getValueAt(selRow, 0);
				if (JOptionPane.showConfirmDialog(null, "Do you want to delete book: " + book.getDisplayValue(BookRow.COLUMN_TITLE), "Delete book", JOptionPane.YES_NO_OPTION) == 0) {
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
