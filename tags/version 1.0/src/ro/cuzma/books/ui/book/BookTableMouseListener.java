package ro.cuzma.books.ui.book;


import ro.cuzma.books.BookRow;
import ro.cuzma.books.ui.BooksManagement;
import ro.cuzma.db.BookType;
import ro.cuzma.event.CustomEvent;
import ro.cuzma.ui.TableWithColumnModel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.Logger;



public class BookTableMouseListener extends MouseAdapter {
	BookTable table;
	public BookTableMouseListener(BookTable table){
		this.table = table;
	}
	public void mouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON3) {
			TableColumnModel colModel = table.getColumnModel();
			int mColIndex = table.columnAtPoint(evt.getPoint());
			int mRowIndex = table.rowAtPoint(evt.getPoint());
			table.setColumnSelectionInterval(mColIndex, mColIndex);
			table.setRowSelectionInterval( mRowIndex,mRowIndex);
			JPopupMenu menu = new JPopupMenu();
			int selRow = table.getSelectedRow();
			BookRow book = null;
			if (selRow >= 0){
				book = (BookRow)table.getValueAt(selRow, 0);
			};
			if (((TableWithColumnModel)table.getModel()).getEdit()){
				menu.add(new EditAction(table,"Unedit TableData"));
			}else{
				menu.add(new EditAction(table,"Edit TableData"));
			}
			if (book != null){
				File tmpF = book.getFile();
				if (tmpF != null){
					menu.add(new OpenAction(book,"Open file: " + book.getDisplayValue(BookRow.COLUMN_FILE)));
				}
			}
			if (book != null ){
				menu.add(new JSeparator());
				menu.add(new EditBookAction(table,"Edit: " +  book.getDisplayValue(BookRow.COLUMN_TITLE)));
			}
			if (book != null && ((TableWithColumnModel)table.getModel()).getEdit()){
				menu.add(new JSeparator());
				menu.add(new DeleteAction(table,"Delete: " +  book.getDisplayValue(BookRow.COLUMN_TITLE)));
			}
			Point pt = SwingUtilities.convertPoint(evt.getComponent(), evt.getPoint(), table);
			menu.show(table, pt.x, pt.y);
		}
	}
	class EditAction extends AbstractAction {
		JTable	comp;

		public EditAction(JTable comp,String text) {
			super(text);
			this.comp = comp;
		}

		public void actionPerformed(ActionEvent e) {
			((TableWithColumnModel)table.getModel()).setUnsetEdit();
			CustomEvent.dispatchEvent(this,BookPanelTable.CE_BOOK_TYPE,BookPanelTable.CE_BOOK_VALUE_EDIT);
			table.setNewData(table.reloadData());
			table.revalidate();
			table.repaint();
		}

		public boolean isEnabled() {
			return true;
		}
	}
	class EditBookAction extends AbstractAction {
		JTable	comp;

		public EditBookAction(JTable comp,String text) {
			super(text);
			this.comp = comp;
		}

		public void actionPerformed(ActionEvent e) {
			CustomEvent.dispatchEvent(this,BooksManagement.CE_BOOKM_TYPE,BooksManagement.CE_BOOKM_VALUE_SHOW_BOOK_EDIT);
			
		}

		public boolean isEnabled() {
			return true;
		}
	}
	class DeleteAction extends AbstractAction {
		JTable	comp;
	

		public DeleteAction(JTable comp,String text) {
			super(text);
			this.comp = comp;
		}

		public void actionPerformed(ActionEvent e) {
			table.deleteSelectedBook();
		}

		public boolean isEnabled() {
			return true;
		}
	}
	class OpenAction extends AbstractAction {
		BookRow toOpen;

		public OpenAction(BookRow toOpen,String text) {
			super(text);
			this.toOpen = toOpen;
		}

		public void actionPerformed(ActionEvent e) {
			//System.out.println("cmd \"" + toOpen.getAbsoluteFile() + "\"");
			Runtime rt = Runtime.getRuntime();
			try {
				Object tst = toOpen.getValue(BookRow.COLUMN_FORMAT);
				String openWidth = ((BookType)tst).getOpenWidth().getStringValue();
				openWidth = "rundll32 url.dll,FileProtocolHandler";
				if (openWidth != null){
					Logger.getLogger("com.cuzma.books.BookType").debug("\"" +openWidth + "\" \""+ toOpen.getFile().getAbsoluteFile() + "\"");
					rt.exec("" +openWidth + " \""+ toOpen.getFile().getAbsoluteFile() + "\"");
				}else{
				rt.exec("explorer \"" + toOpen.getFile().getAbsoluteFile() + "\"");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		public boolean isEnabled() {
			return true;
		}
	}

}
