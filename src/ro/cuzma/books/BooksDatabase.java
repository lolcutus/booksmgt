package ro.cuzma.books;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeSet;
import java.util.Vector;
//import larry.poze.DateCreated;
//import larry.poze.Picture;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ro.cuzma.books.exceptions.BooksException;
import ro.cuzma.db.BookType;
import ro.cuzma.db.DBException;
import ro.cuzma.db.ValueDisplay;
import ro.cuzma.db.ValueDisplayData;

public class BooksDatabase {
	private static final String	DEFAULT_PARSER_NAME	= "org.apache.xerces.parsers.DOMParser";
	private BookData			books				= new BookData(new Vector());
	private AuthorData			authors				= new AuthorData(new Vector());
	private  ValueDisplayData       langs = new ValueDisplayData(new Vector(),"languages");
	private  ValueDisplayData       types = new ValueDisplayData(new Vector(),"types");
	private String				root				= "r:\\Carti";
	File						databaseFile;

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BooksDatabase bdb = new BooksDatabase();
		bdb.load(new File("e:\\Carti\\all.xml"));
		// bdb.parseDir("r:\\Carti", "All\\Beletristica\\EN");
		int newID = 1;
		Author tmp;
		int oldID;
		/*for (int i = 0; i < bdb.getAuthors().size(); i++) {
			tmp = (Author) bdb.getAuthors().get(i);
			oldID = tmp.getRowID();
			tmp.setValue((newID++);
		}
		try {
			bdb.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// bdb.load(new File("r:\\Carti\\books.xml"));
		/*
		 * for(int i = 0;i < bdb.getAuthors().size();i++){
		 * System.out.println(((Author)bdb.getAuthors().get(i)).getConcatenateName()); }
		 */
	}

	public BookRow addBook(BookRow bk) {
		BookRow tmp;
		for (int i = 0; i < books.size(); i++) {
			tmp = (BookRow) books.get(i);
			if (tmp.isEqual(bk)) {
				return tmp;
			}
		}
		books.add(bk);
		return bk;
	}
	
	public  ValueDisplay addLang(String lang,String description) throws DBException {
		
		ValueDisplay tmp;
		for (int i = 0; i < this.langs.size(); i++) {
			tmp = (ValueDisplay) this.langs.get(i);
			if (tmp.getDisplayValue(ValueDisplay.COLUMN_VALUE).equals(lang)) {
				return tmp;
			}
		}
		ValueDisplay tmpL = new ValueDisplay("lang",this.getNewLanguagesID(),lang,description);
		this.langs.add(tmpL);
		return tmpL;
	}

	public Author addAuthor(Author au) {
		/*
		 * Author tmp; for (int i = 0; i < authors.size(); i++) { tmp = (Author)
		 * authors.get(i); if (tmp.isEqual(au)) { return tmp; } }
		 */
		// System.out.println("not found: " + au.getConcatenateName() );
		authors.add(au);
		return au;
	}

	public void parseDir(String root, String path) {
		File curDir = new File(root + File.separator + path);
		String fileName = "";
		if (curDir.isDirectory()) {
			File[] allFielsInDir = curDir.listFiles();
			BookRow tmp = null;
			for (int i = 0; i < allFielsInDir.length; i++) {
				if (!allFielsInDir[i].getAbsoluteFile().isDirectory()) {
					fileName = allFielsInDir[i].getAbsolutePath();
					if (allFielsInDir[i].getName().charAt(0) != '#') {
						// System.out.println(allFielsInDir[i].getName());
						try {
							tmp = new BookRow(root, allFielsInDir[i], this);
						} catch (DBException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (tmp.isBook()) {
							tmp = this.addBook(tmp);
						}
						// System.out.println(tmp.toXml(""));
					}
				} else {
					String pathI = allFielsInDir[i].getAbsolutePath();
					parseDir(root, pathI.substring(root.length()));
				}
			}
		}
	}
	public void save(File newFile) throws IOException{
		this.databaseFile = newFile;
		save();
	}

	public void save() throws IOException {
		if (databaseFile == null) {
			databaseFile = new File(root + "\\books-en.xml");
		}
		String filePath = databaseFile.getAbsolutePath();
		File tmp = new File(filePath + ".old");
		tmp.delete();
		databaseFile.renameTo(tmp);
		databaseFile.createNewFile();
		RandomAccessFile ra = new RandomAccessFile(databaseFile, "rw");
		ra.writeBytes("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		//ra.writeBytes("<?xml-stylesheet type=\"text/xsl\" href=\"books.xsl\"?>\r\n");
		ra.writeBytes("<database>\r\n");
		ra.writeBytes("\t<root>" + this.getRoot() + "</root>\r\n");
		
		ra.writeBytes(this.langs.toXml("\t"));
		ra.writeBytes(this.types.toXml("\t"));
		ra.writeBytes(this.authors.toXml("\t"));
		// ra.writeBytes("\t<CategoryList>\r\n");
		// ra.writeBytes(categories.save("\t\t", null));
		// ra.writeBytes("\t</CategoryList>\r\n");
		/*ra.writeBytes("\t<authors>\r\n");
		for (int i = 0; i < this.authors.size(); i++) {
			ra.writeBytes(((Author) this.authors.get(i)).toXml("\t\t"));
		}
		// }
		ra.writeBytes("\t</authors>\r\n");*/
		ra.writeBytes("\t<books>\r\n");
		for (int i = 0; i < this.books.size(); i++) {
			ra.writeBytes(((BookRow) this.books.get(i)).toXml("\t\t"));
		}
		ra.writeBytes("\t</books>\r\n");
		ra.writeBytes("</database>");
		ra.close();
	}

	/**
	 * @return  the authors
	 * @uml.property  name="authors"
	 */
	public AuthorData getAuthors() {
		return authors;
	}

	/**
	 * @param authors  the authors to set
	 * @uml.property  name="authors"
	 */
	public void setAuthors(AuthorData authors) {
		this.authors = authors;
	}

	/**
	 * @return  the books
	 * @uml.property  name="books"
	 */
	public BookData getBooks() {
		return books;
	}

	/**
	 * @param books  the books to set
	 * @uml.property  name="books"
	 */
	public void setBooks(BookData books) {
		this.books = books;
	}

	/**
	 * @return  the databaseFile
	 * @uml.property  name="databaseFile"
	 */
	public File getDatabaseFile() {
		return databaseFile;
	}

	/**
	 * @param databaseFile  the databaseFile to set
	 * @uml.property  name="databaseFile"
	 */
	public void setDatabaseFile(File databaseFile) {
		this.databaseFile = databaseFile;
	}

	/**
	 * @return  the root
	 * @uml.property  name="root"
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * @param root  the root to set
	 * @uml.property  name="root"
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	public int getNewAuthorID() {
		int rez = 70;
		int tmpId;
		for (int i = 0; i < authors.size(); i++) {
			tmpId = ((Author) authors.get(i)).getRowID();
			if (rez < tmpId) {
				rez = tmpId;
			}
			;
		}
		return rez + 1;
	}

	public int getNewBookID() {
		return books.getNewBookID();
	}
	public  int getNewLanguagesID() {
		return this.langs.getNewID();
	}

	public void load(File fileFrom) {
		this.databaseFile = fileFrom;
		try {
			DOMParser parser = (DOMParser) Class.forName(DEFAULT_PARSER_NAME).newInstance();
			parser.parse(fileFrom.getAbsolutePath());
			Document doc = parser.getDocument();
			this.traverse(doc);
			/*
			 * for (int i = 0; i < this.categories.size(); i++) {
			 * ((PictureCategories) this.categories.get(i)).show(""); }
			 */
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void traverse(Node node) {
		// is there anything to do?
		if (node == null) {
			return;
		}
		int type = node.getNodeType();
		switch (type) {
		// print document
		case Node.DOCUMENT_NODE: {
			traverse(((Document) node).getDocumentElement());
			break;
		}
			// print element with attributes
		case Node.ELEMENT_NODE: {
			// System.out.println("node: " + node.getLocalName());
			if (node.getLocalName().equals("authors") && node.getParentNode().getLocalName().equals("database")) {
				Node tmp1;
				int len1 = node.getChildNodes().getLength();
				// System.out.println("## " + len1);
				for (int j = 0; j < len1; j++) {
					tmp1 = node.getChildNodes().item(j);
					// System.out.println(tmp1.getLocalName());
					if (tmp1.getLocalName() != null && tmp1.getLocalName().equalsIgnoreCase("author")) {
						// categories.processNode(tmp1, null);
						this.addAuthor(Author.processNode(tmp1));
					}
					// pcTmp.addChildren(pcTmp);
				}
			} else if (node.getLocalName().equals("books")) {
				Node tmp1;
				int len1 = node.getChildNodes().getLength();
				// System.out.println("## " + len1);
				for (int j = 0; j < len1; j++) {
					tmp1 = node.getChildNodes().item(j);
					if (tmp1.getLocalName() != null && tmp1.getLocalName().equalsIgnoreCase("book")) {
						try {
							this.addBook(BookRow.processNode(tmp1, this.root, this.getAuthors().getRows(),this.langs.getRows(),this.types.getRows()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// pcTmp.addChildren(pcTmp);
				}
			} else if (node.getLocalName().equals("languages")) {
				Node tmp1;
				int len1 = node.getChildNodes().getLength();
				// System.out.println("## " + len1);
				for (int j = 0; j < len1; j++) {
					tmp1 = node.getChildNodes().item(j);
					if (tmp1.getLocalName() != null && tmp1.getLocalName().equalsIgnoreCase("lang")) {
						try {
							this.langs.addValue(ValueDisplay.processNode(tmp1,"lang"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// pcTmp.addChildren(pcTmp);
				}
			}else if (node.getLocalName().equals("types")) {
				Node tmp1;
				int len1 = node.getChildNodes().getLength();
				// System.out.println("## " + len1);
				for (int j = 0; j < len1; j++) {
					tmp1 = node.getChildNodes().item(j);
					if (tmp1.getLocalName() != null && tmp1.getLocalName().equalsIgnoreCase("type")) {
						try {
							this.types.addValue(BookType.processNode(tmp1,"type"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// pcTmp.addChildren(pcTmp);
				}
			}else if (node.getLocalName().equals("root")) {
				this.root = node.getChildNodes().item(0).getNodeValue();
			} else {
				NodeList children = node.getChildNodes();
				if (children != null) {
					int len = children.getLength();
					// System.out.println("childs len: " + len);
					for (int i = 0; i < len; i++) {
						traverse(children.item(i));
					}
				}
			}
			break;
		}
		}
	}

	
		public void deleteBook(BookRow book){
			books.remove(book);
	}

		public ValueDisplayData getLangs() {
			return langs;
		}

		public ValueDisplayData getTypes() {
			return types;
		}
}
