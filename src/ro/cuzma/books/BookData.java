package ro.cuzma.books;

import java.util.Vector;
import javax.swing.JOptionPane;

import ro.cuzma.db.TableData;
import ro.cuzma.db.ValueDisplay;

public class BookData extends TableData {
	private String	filterTitle		= null;
	private String	filterTitleOrig	= null;
	private String	filterAuthor	= null;
	private Integer	filterRead		= null;
	private String	filterSerie		= null;
	private String	filterPublisher	= null;
	private String	filterStatus	= null;
	private Integer	filterFormat	= null;
	private Integer	filterLang		= null;
	private String	filterBookType	= null;
	private Long	filterISBN		= null;
	private Boolean	filterToRead		= null;

	public BookData(Vector rows) {
		super(rows,"books");
	}

	public Vector getSortedFilteredData() {
		// TODO Auto-generated method stub
		if (rows == null)
			return null;
		Vector tmp = new Vector();
		BookRow bk;
		boolean addBook = true;
		for (int i = 0; i < rows.size(); i++) {
			bk = (BookRow) rows.get(i);
			addBook = true;
			if (addBook == true) {
				if (this.filterTitle == null || ((String)bk.getDisplayValue(BookRow.COLUMN_TITLE)).toUpperCase().indexOf(this.filterTitle.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterTitleOrig == null || ((String)bk.getDisplayValue(BookRow.COLUMN_ORIG_TITLE)).toUpperCase().indexOf(this.filterTitleOrig.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterAuthor == null || ((String)bk.getDisplayValue(BookRow.COLUMN_AUTHORS)).toUpperCase().indexOf(this.filterAuthor.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (filterRead == null || filterRead.intValue() == ((Integer)bk.getValue(BookRow.COLUMN_READ)).intValue()) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterSerie == null || ((String)bk.getDisplayValue(BookRow.COLUMN_SERIE)).toUpperCase().indexOf(this.filterSerie.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterPublisher == null || ((String)bk.getDisplayValue(BookRow.COLUMN_PUBLISHER)).toUpperCase().indexOf(this.filterPublisher.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterStatus == null || ((String)bk.getDisplayValue(BookRow.COLUMN_STATUS)).toUpperCase().indexOf(this.filterStatus.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterFormat == null || ((ValueDisplay)bk.getValue(BookRow.COLUMN_FORMAT)).getRowID() == filterFormat.intValue()) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterLang == null || ((ValueDisplay)bk.getValue(BookRow.COLUMN_LANGUAGE)).getRowID() == filterLang.intValue()) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (this.filterBookType == null || ((String)bk.getDisplayValue(BookRow.COLUMN_BOOK_TYPE)).toUpperCase().indexOf(this.filterBookType.toUpperCase()) != -1) {
					addBook = true;
				} else {
					addBook = false;
				}
			}
			if (addBook == true) {
				if (filterISBN == null) {
					addBook = true;
				} else {
					if (bk.getValue(BookRow.COLUMN_ISBN) != null && filterISBN.longValue() == ((Long)bk.getValue(BookRow.COLUMN_ISBN)).longValue()) {
						addBook = true;
					} else {
						addBook = false;
					}
				}
			}
			if (addBook == true) {
				if (filterToRead == null) {
					addBook = true;
				} else {
					if (((Boolean)bk.getValue(BookRow.COLUMN_TOREAD)).booleanValue()== filterToRead.booleanValue()) {
						addBook = true;
					} else {
						addBook = false;
					}
				}
			}
			if (addBook == true) {
				tmp.add(bk);
			}
		}
		return tmp;
	}

	public void setFilter(String title, String titleOrig, String author, String read, String filterSerie, String filterPublisher, String filterStatus, Integer filterFormat, Integer filterLang,
					String filterBookType, String isbn,Boolean toRead) {
		if (read == null || read.equals("")) {
			this.filterRead = null;
		} else {
			this.filterRead = new Integer(read);
		}
		if (title == null || title.equals("")) {
			this.filterTitle = null;
		} else {
			this.filterTitle = title;
		}
		if (author == null || author.equals("")) {
			this.filterAuthor = null;
		} else {
			this.filterAuthor = author;
		}
		if (titleOrig == null || titleOrig.equals("")) {
			this.filterTitleOrig = null;
		} else {
			this.filterTitleOrig = titleOrig;
		}
		if (filterSerie == null || filterSerie.equals("")) {
			this.filterSerie = null;
		} else {
			this.filterSerie = filterSerie;
		}
		if (filterPublisher == null || filterPublisher.equals("")) {
			this.filterPublisher = null;
		} else {
			this.filterPublisher = filterPublisher;
		}
		if (filterStatus == null || filterStatus.equals("")) {
			this.filterStatus = null;
		} else {
			this.filterStatus = filterStatus;
		}
		if (filterFormat == null || filterFormat.equals("")) {
			this.filterFormat = null;
		} else {
			this.filterFormat = filterFormat;
		}
		if (filterLang == null || filterLang.equals("")) {
			this.filterLang = null;
		} else {
			this.filterLang = filterLang;
		}
		if (filterBookType == null || filterBookType.equals("")) {
			this.filterBookType = null;
		} else {
			this.filterBookType = filterBookType;
		}
		if (isbn == null || isbn.equals("")) {
			this.filterISBN = null;
		} else {
			this.filterISBN = new Long(isbn);
		}
		this.filterToRead = toRead;
	}

	public int getNewBookID() {
		int rez = 0;
		int tmpId;
		// System.out.println(books.size());
		for (int i = 0; i < rows.size(); i++) {
			// System.out.println("i: "+i);
			tmpId = ((BookRow) rows.get(i)).getRowID();
			if (rez < tmpId) {
				rez = tmpId;
			}
			;
		}
		return rez + 1;
	}
}
