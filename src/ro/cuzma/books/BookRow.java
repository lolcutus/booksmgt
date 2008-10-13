package ro.cuzma.books;

//import com.cuzma.ui.Column;
import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
//import larry.poze.categories.Category;
import ro.cuzma.db.BookType;
import ro.cuzma.db.DBException;
import ro.cuzma.db.TableCell;
import ro.cuzma.db.TableCellBoolean;
import ro.cuzma.db.TableCellCalendar;
import ro.cuzma.db.TableCellFloat;
import ro.cuzma.db.TableCellInteger;
import ro.cuzma.db.TableCellLong;
import ro.cuzma.db.TableCellString;
import ro.cuzma.db.TableCellValueDisplay;
import ro.cuzma.db.TableCellVector;
import ro.cuzma.db.TableColumn;
import ro.cuzma.db.TableRow;
import ro.cuzma.db.ValueDisplay;
import ro.cuzma.tools.MyStringTokenizer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BookRow extends TableRow {
	// Columns
	public final static int			COLUMN_TITLE			= 1;
	public final static int			COLUMN_ORIG_TITLE		= 2;
	public final static int			COLUMN_AUTHORS			= 3;
	public final static int			COLUMN_SERIE			= 4;
	public final static int			COLUMN_SERIE_POS		= 5;
	public final static int			COLUMN_READ				= 6;
	public final static int			COLUMN_BOOK_TYPE		= 7;
	public final static int			COLUMN_LANGUAGE			= 8;
	public final static int			COLUMN_FORMAT			= 9;
	public final static int			COLUMN_STATUS			= 10;
	public final static int			COLUMN_PUBLISHER		= 11;
	public final static int			COLUMN_COLLECTION		= 12;
	public final static int			COLUMN_COLLECTION_POS	= 13;
	public final static int			COLUMN_BOUGHT_DATE		= 14;
	public final static int			COLUMN_APPARITION_DATE	= 15;
	public final static int			COLUMN_PRICE			= 16;
	public final static int			COLUMN_CURRENCY			= 17;
	public final static int			COLUMN_LOAN_PERS		= 18;
	public final static int			COLUMN_LOAN_DATE		= 19;
	public final static int			COLUMN_FILE				= 20;
	public final static int			COLUMN_ISBN				= 21;
	public final static int			COLUMN_TOREAD			= 22;
	public final static int			COLUMN_REL_PATH			= 23;
	// Order
	// Data
	private String					rootPath;
	//TableCell
	private TableCell				title;
	private TableCell				titleOrig;
	private TableCell				readNr;
	private TableCellValueDisplay	language;
	private TableCell				status;
	private TableCell				publisher;
	private TableCell				collection;
	private TableCell				collectionNR;
	private TableCell				apparitionDate;
	private TableCell				boughtDate;
	private TableCell				toRead;
	private TableCell				currency;
	private TableCell				bookType;
	private TableCell				serie;
	private TableCell				seriePos;
	private TableCell				relativePath;
	private TableCellValueDisplay	fileType;
	private TableCell				fileName;
	private TableCell				price;
	private TableCell				isbn;
	private TableCell				loanPerson;
	private TableCell				loanDate;
	private TableCellVector			authors;
	private Vector<Loan>			oldLoans				= new Vector<Loan>();
	private boolean					isBook					= false;
	private String					separator				= " - ";

	protected void init() throws DBException {
		//Title
		this.title = new TableCellString(new TableColumn(BookRow.COLUMN_TITLE, String.class, false, "title"));
		this.cells.put(BookRow.COLUMN_TITLE, this.title);
		//Original Title
		this.titleOrig = new TableCellString(new TableColumn(BookRow.COLUMN_ORIG_TITLE, String.class, true, "titleOrig"));
		this.cells.put(BookRow.COLUMN_ORIG_TITLE, this.titleOrig);
		//Read
		this.readNr = new TableCellInteger(new TableColumn(BookRow.COLUMN_READ, Integer.class, false, "readNr"));
		this.cells.put(BookRow.COLUMN_READ, this.readNr);
		//Language
		this.language = new TableCellValueDisplay(new TableColumn(BookRow.COLUMN_LANGUAGE, String.class, false, "lang"));
		this.cells.put(BookRow.COLUMN_LANGUAGE, this.language);
		//Status
		this.status = new TableCellString(new TableColumn(BookRow.COLUMN_STATUS, String.class, false, "status"));
		this.cells.put(BookRow.COLUMN_STATUS, this.status);
		//Publisher
		this.publisher = new TableCellString(new TableColumn(BookRow.COLUMN_PUBLISHER, String.class, true, "publisher"));
		this.cells.put(BookRow.COLUMN_PUBLISHER, this.publisher);
		//Collection
		this.collection = new TableCellString(new TableColumn(BookRow.COLUMN_COLLECTION, String.class, true, "collection"));
		this.cells.put(BookRow.COLUMN_COLLECTION, this.collection);
		//collectionNR
		this.collectionNR = new TableCellInteger(new TableColumn(BookRow.COLUMN_COLLECTION_POS, Integer.class, true, "collectionNR"));
		this.cells.put(BookRow.COLUMN_COLLECTION_POS, this.collectionNR);
		//apparitionDate
		this.apparitionDate = new TableCellCalendar(new TableColumn(BookRow.COLUMN_APPARITION_DATE, String.class, true, "apparitionDate"));
		this.cells.put(BookRow.COLUMN_APPARITION_DATE, this.apparitionDate);
		//boughtDate
		this.boughtDate = new TableCellCalendar(new TableColumn(BookRow.COLUMN_BOUGHT_DATE, String.class, true, "boughtDate"));
		this.cells.put(BookRow.COLUMN_BOUGHT_DATE, this.boughtDate);
		//		toRead
		this.toRead = new TableCellBoolean(new TableColumn(BookRow.COLUMN_TOREAD, Boolean.class, true, "toRead"));
		this.cells.put(BookRow.COLUMN_TOREAD, this.toRead);
		//currency
		this.currency = new TableCellString(new TableColumn(BookRow.COLUMN_CURRENCY, String.class, true, "currency"));
		this.cells.put(BookRow.COLUMN_CURRENCY, this.currency);
		//bookType
		this.bookType = new TableCellString(new TableColumn(BookRow.COLUMN_BOOK_TYPE, String.class, true, "bookType"));
		this.cells.put(BookRow.COLUMN_BOOK_TYPE, this.bookType);
		//		serie
		this.serie = new TableCellString(new TableColumn(BookRow.COLUMN_SERIE, String.class, true, "serie"));
		this.cells.put(BookRow.COLUMN_SERIE, this.serie);
		//		seriePos
		this.seriePos = new TableCellInteger(new TableColumn(BookRow.COLUMN_SERIE_POS, Integer.class, true, "seriePos"));
		this.cells.put(BookRow.COLUMN_SERIE_POS, this.seriePos);
		//		relativePath
		this.relativePath = new TableCellString(new TableColumn(BookRow.COLUMN_REL_PATH, String.class, true, "relativePath"));
		this.cells.put(BookRow.COLUMN_REL_PATH, this.relativePath);
		//		fileType
		this.fileType = new TableCellValueDisplay(new TableColumn(BookRow.COLUMN_FORMAT, String.class, false, "type"));
		this.cells.put(BookRow.COLUMN_FORMAT, this.fileType);
		//		relativePath
		this.fileName = new TableCellString(new TableColumn(BookRow.COLUMN_FILE, String.class, true, "fileName"));
		this.cells.put(BookRow.COLUMN_FILE, this.fileName);
		//		price
		this.price = new TableCellFloat(new TableColumn(BookRow.COLUMN_PRICE, String.class, true, "price"));
		this.cells.put(BookRow.COLUMN_PRICE, this.price);
		//		ISBN
		this.isbn = new TableCellLong(new TableColumn(BookRow.COLUMN_ISBN, String.class, true, "isbn"));
		this.cells.put(BookRow.COLUMN_ISBN, this.isbn);
		//		Authors
		this.authors = new TableCellVector(new TableColumn(BookRow.COLUMN_AUTHORS, String.class, true, "aid"), "authors");
		this.cells.put(BookRow.COLUMN_AUTHORS, this.authors);
		//loanPerson
		this.loanPerson = new TableCellString(new TableColumn(BookRow.COLUMN_LOAN_PERS, String.class, true, "loanPerson"));
		this.cells.put(BookRow.COLUMN_LOAN_PERS, this.loanPerson);
		//boughtDate
		this.loanDate = new TableCellCalendar(new TableColumn(BookRow.COLUMN_LOAN_DATE, String.class, true, "loanDate"));
		this.cells.put(BookRow.COLUMN_LOAN_DATE, this.loanDate);
	}

	public static void main(String[] args) {
		/*
		 * if (args.length != 2) { Book book = new Book( "c:\\Carti", new File(
		 * "C:\\Carti\\All\\Beletristica\\RO\\Pablo Coellho\\A. S. Puskin - Dama
		 * de pica.rtf")); book.show(); } else { Book book = new Book(args[0],
		 * new File(args[1])); System.out.println(book.toXml("")); }
		 */
	}

	/**
	 * @param fromFile
	 * @throws  
	 * @throws BooksException
	 */
	public BookRow(String rootPath, File fromFile, BooksDatabase db) throws DBException {
		super("book");
		this.rootPath = rootPath;
		processFile(fromFile, db);
	}

	public BookRow(Integer bookid, String rootPath, String relativePath, Vector<Author> bAuthors, String title, String titleOrig, int readNr, ValueDisplay fileType, String fileName, ValueDisplay language,
					String status, String publisher, String collection, String collectionNR, String boughtDate, String apparitionDate, Float price, String currency, String bookType, String serie,
					Integer seriePos, Vector loans, Long isbn, boolean toRead, String loanPers, String loanDate) throws DBException {
		super("book");
		if (bookid == null)
			throw new DBException("ID can not be null!!!");
		setRowID(bookid);
		setRootPath(rootPath);
		// if (relativePath == null)
		// throw new BooksException("Relative path can not be null!!!");
		this.relativePath.setValue(relativePath);
		//System.out.println("Autri to book: " + bAuthors.size());
		this.authors.setValue(bAuthors);
		this.title.setValue(title);
		this.titleOrig.setValue(titleOrig);
		this.readNr.setValue(readNr);
		this.fileType.setValue(fileType);
		this.fileName.setValue(fileName);
		//Logger.getLogger("ro.cuzma.books.Book").debug("Lang: " + language.getRowID());
		this.language.setValue(language);
		//Logger.getLogger("ro.cuzma.books.Book").debug("Lang: " + this.language.getStringValue());
		//BooksDatabase.addLang(language);
		this.status.setValue(status);
		this.publisher.setValue(publisher);
		this.collection.setValue(collection);
		this.collectionNR.setStringValue(collectionNR);
		this.boughtDate.setStringValue(boughtDate);
		this.apparitionDate.setStringValue(apparitionDate);
		this.price.setValue(price);
		this.currency.setStringValue(currency);
		this.bookType.setStringValue(bookType);
		this.serie.setStringValue(serie);
		this.seriePos.setValue(seriePos);
		this.loanPerson.setValue(loanPers);
		this.loanDate.setStringValue(loanDate);
		setLoans(loans);
		this.isbn.setValue(isbn);
		this.toRead.setValue(toRead);
	}

	public String toXml(String indent) {
		String rez = "";
		rez = rez + indent + "<book>\r\n";
		rez = rez + indent + "\t<id>" + this.rowID + "</id>\r\n";
		Enumeration en = cells.keys();
		while (en.hasMoreElements()) {
			rez = rez + ((TableCell) cells.get(en.nextElement())).toXml(indent + "\t");
		}
		/*Vector tmp = this.getAuthors();
		 Author aut = null;
		 rez = rez + indent + "\t<authors>\r\n";
		 for (int i = 0; i < tmp.size(); i++) {
		 aut = (Author) tmp.get(i);
		 rez = rez + indent + "\t\t<aid>" + aut.getAuthorID() + "</aid>\r\n";
		 }*/
		//rez = rez + indent + "\t</authors>\r\n";
		String oldLoans = "";
		String curLoan = "";
		Loan tmpL = null;
		for (int i = 0; i < this.oldLoans.size(); i++) {
			tmpL = this.oldLoans.get(i);
			if (tmpL.getReturnDate() == null) {
				curLoan = tmpL.toXml(indent + "\t");
			} else {
				oldLoans = oldLoans + tmpL.toXml(indent + "\t\t");
			}
		}
		if (!oldLoans.equals("")) {
			rez = rez + indent + "\t<oldLoans>\r\n";
			rez = rez + oldLoans;
			rez = rez + indent + "\t</oldLoans>\r\n";
		}
		/*if (!curLoan.equals("")) {
		 rez = rez + curLoan;
		 }*/
		rez = rez + indent + "</book>\r\n";
		return rez;
	}



	protected Object getVal(int columnID) {
		Object rez = null;
		switch (columnID) {
		case BookRow.COLUMN_TITLE:
		case BookRow.COLUMN_ORIG_TITLE:
		case BookRow.COLUMN_READ:
		case BookRow.COLUMN_LANGUAGE:
		case BookRow.COLUMN_STATUS:
		case BookRow.COLUMN_PUBLISHER:
		case BookRow.COLUMN_COLLECTION:
		case BookRow.COLUMN_COLLECTION_POS:
		case BookRow.COLUMN_BOUGHT_DATE:
		case BookRow.COLUMN_APPARITION_DATE:
		case BookRow.COLUMN_TOREAD:
		case BookRow.COLUMN_CURRENCY:
		case BookRow.COLUMN_SERIE:
		case BookRow.COLUMN_SERIE_POS:
		case BookRow.COLUMN_BOOK_TYPE:
		case BookRow.COLUMN_FILE:
		case BookRow.COLUMN_FORMAT:
		case BookRow.COLUMN_ISBN:
		case BookRow.COLUMN_PRICE:
		case BookRow.COLUMN_AUTHORS:
			rez = getCell(columnID).getValue();
			break;
		case BookRow.COLUMN_LOAN_PERS:
			rez = this.getLoanPerson();
			break;
		case BookRow.COLUMN_LOAN_DATE:
			rez = this.getLoanDate();
			break;
		}
		return rez;
	}

	private void processFile(File fromFile, BooksDatabase db) throws DBException {
		String folderPath = fromFile.getParent();
		// System.out.println(folderPath);
		if (folderPath.equals(this.rootPath)) {
			this.relativePath.setValue("");
		} else {
			this.relativePath.setValue(folderPath.substring(rootPath.length() + 1));
		}
		fileName.setValue(fromFile.getName());
		fileType.setValue(fileName.getStringValue().substring(fileName.getStringValue().lastIndexOf(".") + 1));
		String fileTmp = fileName.getStringValue().substring(0, fileName.getStringValue().lastIndexOf("."));
		// System.out.println(relativePath);
		// System.out.println(fileName);
		MyStringTokenizer st = new MyStringTokenizer(fileTmp, this.separator);
		String[] data = new String[5];
		int i = 0;
		while (st.hasMoreTokens()) {
			data[i] = st.nextToken();
			// System.out.println(data[i]);
			i++;
		}
		if (data[3] != null) {
			this.setRowID(db.getNewBookID());
			this.setValue(BookRow.COLUMN_TITLE, data[3]);
			System.out.println(data[1]);
			this.setValue(BookRow.COLUMN_SERIE_POS, data[2]);
			this.setValue(BookRow.COLUMN_SERIE, data[1]);
			this.addAuthor(data[0], db);
			this.setIsBook(true);
		} else if (data[2] != null) {
			this.setRowID(db.getNewBookID());
			this.setValue(BookRow.COLUMN_TITLE, data[2]);
			// this.setColNumber("");
			this.setValue(BookRow.COLUMN_SERIE, data[1]);
			this.addAuthor(data[0], db);
			this.setIsBook(true);
		} else if (data[1] != null) {
			this.setRowID(db.getNewBookID());
			this.setValue(BookRow.COLUMN_TITLE, data[1]);
			// this.setColNumber("");
			// this.setColectie("");
			this.addAuthor(data[0], db);
			this.setIsBook(true);
		} else {
			this.setIsBook(false);
		}
		//this.language = "en";
	}

	public static BookRow processNode(Node xmlPic, String root, Vector authors, Vector languagesV,Vector typesV) throws DBException {
		BookRow bk = null;
		NodeList children = xmlPic.getChildNodes();
		String rootPath = root;
		String relativePath = null;
		Vector bAuthors = new Vector();
		ValueDisplay bLang = null;
		Vector tLoans = new Vector();
		String title = null;
		String titleOrig = null;
		int readNr = 0;
		boolean isBook = false;
		ValueDisplay fileType = null;
		String fileName = null;
		Integer bookID = -1;
		String status = null;
		String publisher = null;
		String collection = null;
		String collectionNR = null;
		String boughtDate = null;
		String aparitionDate = null;
		Float price = null;
		String currency = null;
		String bookType = null;
		String serie = null;
		String loanPers = null;
		String loanDate = null;
		Integer seriePos = null;
		Long isbn = null;
		boolean toRead = false;
		if (children != null) {
			int len = children.getLength();
			// System.out.println("childs len: " + len);
			Node tmp;
			for (int i = 0; i < len; i++) {
				tmp = children.item(i);
				if (tmp.getNodeType() == Node.ELEMENT_NODE) {
					// System.out.print(tmp.getLocalName() + "-");
					if (tmp.getLocalName().equalsIgnoreCase("title")) {
						title = tmp.getChildNodes().item(0).getNodeValue();
						// System.out.println(title);
					} else if (tmp.getLocalName().equalsIgnoreCase("titleOrig")) {
						titleOrig = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("status")) {
						if (tmp.getChildNodes().item(0) != null) {
							status = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("type")) {
						//fileType = tmp.getChildNodes().item(0).getNodeValue();
						String typeS = tmp.getChildNodes().item(0).getNodeValue();
						Integer itmp = new Integer(typeS);
						// boolean foundById = false;
						//Logger.getLogger("ro.cuzma.books.Book").debug("Type: " + typesV.size());
						for (int k = 0; k < typesV.size(); k++) {
							if (((BookType) typesV.get(k)).getRowID() == itmp.intValue()) {
								fileType = (BookType) typesV.get(k);
								//Logger.getLogger("ro.cuzma.books.Book").debug("Type: " + fileType.getRowID());
								break;
							}
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("relativePath")) {
						relativePath = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("fileName")) {
						fileName = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("lang")) {
						String langS = tmp.getChildNodes().item(0).getNodeValue();
						Integer itmp = new Integer(langS);
						// boolean foundById = false;
						for (int k = 0; k < languagesV.size(); k++) {
							if (((ValueDisplay) languagesV.get(k)).getRowID() == itmp.intValue()) {
								bLang = (ValueDisplay) languagesV.get(k);
								//Logger.getLogger("ro.cuzma.books.Book").debug("Lang: " + bLang.getRowID());
								break;
							}
						}
						//Logger.getLogger("ro.cuzma.books.Book").debug("Lang: " + bLang.size());
					} else if (tmp.getLocalName().equalsIgnoreCase("id")) {
						bookID = new Integer(tmp.getChildNodes().item(0).getNodeValue());
					} else if (tmp.getLocalName().equalsIgnoreCase("readNr")) {
						readNr = (new Integer(tmp.getChildNodes().item(0).getNodeValue())).intValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("isbn")) {
						isbn = new Long(tmp.getChildNodes().item(0).getNodeValue());
					} else if (tmp.getLocalName().equalsIgnoreCase("authors")) {
						NodeList catList = tmp.getChildNodes();
						// System.out.print(tmp.getLocalName() + "-");
						int len1 = catList.getLength();
						Node tmp1;
						for (int j = 0; j < len1; j++) {
							tmp1 = catList.item(j);
							if (tmp1.getNodeType() == Node.ELEMENT_NODE) {
								// System.out.print(tmp.getLocalName() + "-");
								if (tmp1.getLocalName().equalsIgnoreCase("aid")) {
									Integer itmp = new Integer(tmp1.getChildNodes().item(0).getNodeValue());
									// boolean foundById = false;
									for (int k = 0; k < authors.size(); k++) {
										if (((Author) authors.get(k)).getRowID() == itmp.intValue()) {
											bAuthors.add(authors.get(k));
											// foundById = true;
											break;
										}
									}
								}
							}
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("oldLoans")) {
						NodeList catList = tmp.getChildNodes();
						// System.out.print(tmp.getLocalName() + "-");
						int len1 = catList.getLength();
						Node tmp1;
						for (int j = 0; j < len1; j++) {
							tmp1 = catList.item(j);
							if (tmp1.getNodeType() == Node.ELEMENT_NODE) {
								// System.out.print(tmp.getLocalName() + "-");
								if (tmp1.getLocalName().equalsIgnoreCase("loan")) {
									tLoans.add(Loan.processNode(tmp1));
								}
							}
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("loan")) {
						tLoans.add(Loan.processNode(tmp));
					} else if (tmp.getLocalName().equalsIgnoreCase("publisher")) {
						if (tmp.getChildNodes().item(0) != null) {
							publisher = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("collection")) {
						if (tmp.getChildNodes().item(0) != null) {
							collection = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("collectionNR")) {
						if (tmp.getChildNodes().item(0) != null) {
							collectionNR = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("boughtDate")) {
						if (tmp.getChildNodes().item(0) != null) {
							boughtDate = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("apparitionDate")) {
						if (tmp.getChildNodes().item(0) != null) {
							aparitionDate = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("price")) {
						if (tmp.getChildNodes().item(0) != null) {
							price = new Float(tmp.getChildNodes().item(0).getNodeValue());
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("currency")) {
						if (tmp.getChildNodes().item(0) != null) {
							currency = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("bookType")) {
						if (tmp.getChildNodes().item(0) != null) {
							bookType = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("serie")) {
						if (tmp.getChildNodes().item(0) != null) {
							serie = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("seriePos")) {
						if (tmp.getChildNodes().item(0) != null) {
							seriePos = new Integer(tmp.getChildNodes().item(0).getNodeValue());
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("toRead")) {
						toRead = true;
					} else if (tmp.getLocalName().equalsIgnoreCase("loanPers")) {
						if (tmp.getChildNodes().item(0) != null) {
							loanPers = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("loanDate")) {
						if (tmp.getChildNodes().item(0) != null) {
							loanDate = tmp.getChildNodes().item(0).getNodeValue();
						}
					}
				}
			}
			//Logger.getLogger("ro.cuzma.books.Book").debug("loanPers: "+ loanPers);
			bk = new BookRow(bookID, rootPath, relativePath, bAuthors, title, titleOrig, readNr, fileType, fileName, bLang, status, publisher, collection, collectionNR, boughtDate, aparitionDate, price,
							currency, bookType, serie, seriePos, tLoans, isbn, toRead, loanPers, loanDate);
		}
		return bk;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath.trim();
	}

	public String getRootPath() {
		if (rootPath == null) {
			return "";
		}
		return rootPath;
	}

	public void setIsBook(boolean isBook) {
		this.isBook = isBook;
	}

	public boolean isBook() {
		return isBook;
	}

	private void addAuthor(String text, BooksDatabase db) {
		StringTokenizer st = new StringTokenizer(text, ",");
		String tmpStr;
		Author tmp = null;
		while (st.hasMoreElements()) {
			tmpStr = st.nextToken().trim();
			try {
				tmp = new Author(db.getNewAuthorID(), tmpStr);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.authors.add(db.addAuthor(tmp));
		}
	}

	public boolean isEqual(BookRow cmp) {
		if (this.rowID == cmp.rowID) {
			return true;
		}
		// if (this.title.equalsIgnoreCase(cmp.title))
		// return true;
		return false;
	}

	public Loan getLoan() {
		Loan rez = null;
		Loan tmp = null;
		for (int i = 0; i < this.oldLoans.size(); i++) {
			tmp = this.oldLoans.get(i);
			if (tmp.getReturnDate() == null) {
				rez = tmp;
				break;
			}
		}
		return rez;
	}

	public String getLoanDate() {
		Loan tmp = getLoan();
		if (tmp == null) {
			return "";
		} else {
			return tmp.getLoanDate();
		}
	}

	public String getLoanPerson() {
		Loan tmp = getLoan();
		if (tmp == null) {
			return "";
		} else {
			return tmp.getPers();
		}
	}

	public void setBook(boolean isBook) {
		this.isBook = isBook;
	}

	public int compareTo(Object arg0) {
		BookRow tmp = (BookRow) arg0;
		int rez = 0;
		int curI = 0;
		int tmpI = 0;
		switch (BookRow.getFieldOrder()) {
		case BookRow.COLUMN_ID:
			rez = this.getRowID() - tmp.getRowID();
			break;
		case BookRow.COLUMN_TITLE:
		case BookRow.COLUMN_ORIG_TITLE:
		case BookRow.COLUMN_READ:
		case BookRow.COLUMN_LANGUAGE:
		case BookRow.COLUMN_STATUS:
		case BookRow.COLUMN_PUBLISHER:
		case BookRow.COLUMN_COLLECTION:
		case BookRow.COLUMN_COLLECTION_POS:
		case BookRow.COLUMN_BOUGHT_DATE:
		case BookRow.COLUMN_APPARITION_DATE:
		case BookRow.COLUMN_TOREAD:
		case BookRow.COLUMN_SERIE_POS:
		case BookRow.COLUMN_BOOK_TYPE:
		case BookRow.COLUMN_CURRENCY:
		case BookRow.COLUMN_FORMAT:
		case BookRow.COLUMN_FILE:
		case BookRow.COLUMN_ISBN:
		case BookRow.COLUMN_PRICE:
		case BookRow.COLUMN_AUTHORS:
			rez = this.getCell(BookRow.getFieldOrder()).compareTo(tmp.getCell(BookRow.getFieldOrder()));
			if (rez == 0) {
				rez = this.getRowID() - tmp.getRowID();
			}
			break;
		case BookRow.COLUMN_SERIE:
			rez = this.getCell(BookRow.getFieldOrder()).compareTo(tmp.getCell(BookRow.getFieldOrder()));
			if (rez == 0) {
				rez = this.getCell(BookRow.COLUMN_SERIE_POS).compareTo(tmp.getCell(BookRow.COLUMN_SERIE_POS));
				if (rez == 0) {
					rez = this.getRowID() - tmp.getRowID();
				}
			}
			break;
		case BookRow.COLUMN_LOAN_PERS:
			rez = this.getLoanPerson().compareTo(tmp.getLoanPerson());
			if (rez == 0) {
				rez = this.getRowID() - tmp.getRowID();
			}
			break;
		case BookRow.COLUMN_LOAN_DATE:
			break;
		}
		if (rez == 0) {
			rez = title.getStringValue().compareTo(tmp.title.getStringValue());
			if (rez == 0) {
				rez = this.getRowID() - tmp.getRowID();
			}
		}
		if (rez == 0) {
			rez = 1;
		}
		return rez * getAscDesc();
	}

	private void addLoan(Loan toAdd) {
		this.oldLoans.add(toAdd);
	}

	public Vector<Loan> getLoans() {
		return this.oldLoans;
	}

	public void setLoans(Vector<Loan> loans) throws DBException {
		this.oldLoans = loans;
		/*Loan l = getLoan();
		 if (l != null){
		 loanPerson.setStringValue(l.getPers());
		 loanDate.setStringValue(l.getLoanDate());
		 }**/
	}

	public File getFile() {
		if (this.rootPath != null && this.relativePath.getValue() != null && this.fileName.getValue() != null) {
			return new File(rootPath + "\\" + relativePath.getStringValue() + "\\" + fileName.getStringValue());
		}
		return null;
	}

	@Override
	public String getStringRow() {
		// TODO Auto-generated method stub
		return rowID.toString();
	}
}
