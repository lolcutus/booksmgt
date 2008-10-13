package ro.cuzma.books;

//import larry.poze.categories.Category;
//import larry.poze.categories.PictureCategories;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import com.cuzma.db.TableCellInteger;
import ro.cuzma.db.DBException;
import ro.cuzma.db.TableCell;
import ro.cuzma.db.TableCellString;
import ro.cuzma.db.TableColumn;
import ro.cuzma.db.TableRow;
import ro.cuzma.tools.*;

public class Author extends TableRow {
	public final static int	COLUMN_FIRST_NAME	= 1;
	public final static int	COLUMN_NAME			= 2;
	public final static int	COLUMN_MIDDLE_NAME	= 3;
	private TableCell		firstName;
	private TableCell		middleName;
	private TableCell		name;

	// private static int id = 1;
	public Author(int authorID) throws DBException {
		super("author");
		this.rowID = authorID;
	}

	/**
	 * @param firstName
	 * @param name
	 * @throws DBException 
	 */
	public Author(int authorID, String allName) throws DBException {
		super("author");
		this.rowID = authorID;
		this.setAll(allName, " ");
	}

	public Author(int authorID, String firstName, String middleName, String name) throws DBException {
		super("author");
		this.rowID = authorID;
		this.name.setValue(name);
		this.firstName.setValue(firstName);
		this.middleName.setValue(middleName);
	}

	private void setAll(String text, String separator) throws DBException {
		text = text.trim();
		///this.firstName.setValue(null);
		//this.middleName.(null);
		//this.setName(null);
		MyStringTokenizer ms = new MyStringTokenizer(text, separator);
		if (ms.hasMoreTokens()) {
			this.firstName.setValue(ms.nextToken());
		}
		if (ms.hasMoreTokens()) {
			this.middleName.setValue(ms.nextToken());
		}
		if (ms.hasMoreTokens()) {
			this.name.setValue(ms.nextToken());
		}
		if (ms.hasMoreTokens()) {
			this.middleName.setValue(this.middleName.getStringValue() + " " + this.name.getStringValue());
			this.name.setValue(ms.nextToken());
		}
		if (ms.hasMoreTokens()) {
			this.middleName.setValue(this.middleName.getStringValue() + " " + this.name.getStringValue());
			this.name.setValue(ms.nextToken());
		}
		if (this.name.getStringValue() == null || this.name.getStringValue().equals("")) {
			if (this.middleName.getStringValue() == null || this.middleName.getStringValue().equals("")) {
				if (this.firstName.getStringValue() != null || this.firstName.getStringValue().equals("")) {
					this.name.setValue(this.firstName.getStringValue());
					this.middleName.setValue("");
				}
			} else {
				this.name.setValue(this.middleName.getStringValue());
				this.middleName.setValue("");
			}
		}
	}

	/*public void show() {
	 System.out.println("First Name: -" + getFirstName() + "-");
	 System.out.println("Middle Name: -" + getMiddleName() + "-");
	 System.out.println("Name: -" + getName() + "-");
	 }*/
	public static void main(String[] args) {
		Author author;
		try {
			author = new Author(1, "Lewis");
			//author.show();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public String getConcatenateName() {
	 String rez = "";
	 if (this.firstName.getStringValue() != null) {
	 rez = rez + firstName.getStringValue();
	 }
	 if (this.middleName.getStringValue() != null) {
	 rez = rez + this.middleName.getStringValue();
	 }
	 if (this.name.getStringValue() != null) {
	 rez = rez + this.name.getStringValue();
	 }
	 return rez;
	 }*/
	public boolean isEqual(Author cmp) {
		if (this.getStringRow().equalsIgnoreCase(cmp.getStringRow()))
			return true;
		return false;
	}

	public static Author processNode(Node author) {
		NodeList children = author.getChildNodes();
		String firstName = null;
		String name = null;
		String middleName = null;
		Author result = null;
		int id = -1;
		if (children != null) {
			int len = children.getLength();
			// System.out.println("childs len: " + len);
			Node tmp;
			for (int i = 0; i < len; i++) {
				tmp = children.item(i);
				if (tmp.getNodeType() == Node.ELEMENT_NODE) {
					if (tmp.getLocalName().equalsIgnoreCase("name")) {
						name = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("firstName")) {
						firstName = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("middleName")) {
						if (tmp.getChildNodes().item(0) != null && !tmp.getChildNodes().item(0).equals("")) {
							middleName = tmp.getChildNodes().item(0).getNodeValue();
						}
					} else if (tmp.getLocalName().equalsIgnoreCase("id")) {
						id = new Integer(tmp.getChildNodes().item(0).getNodeValue()).intValue();
					}
				}
			}
			try {
				result = new Author(id, firstName, middleName, name);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	protected void init() throws DBException {
		//		firstName
		this.firstName = new TableCellString(new TableColumn(Author.COLUMN_FIRST_NAME, String.class, false, "firstName"));
		this.cells.put(Author.COLUMN_FIRST_NAME, this.firstName);
		//middleName
		this.middleName = new TableCellString(new TableColumn(Author.COLUMN_MIDDLE_NAME, String.class, true, "middleName"));
		this.cells.put(Author.COLUMN_MIDDLE_NAME, this.middleName);
		//Name
		this.name = new TableCellString(new TableColumn(Author.COLUMN_NAME, String.class, false, "name"));
		this.cells.put(Author.COLUMN_NAME, this.name);
	}

	@Override
	public String getStringRow() {
		String rez = "";
		if (this.firstName.getStringValue() != null) {
			rez = rez + firstName.getStringValue() + " ";
		}
		if (this.middleName.getStringValue() != null) {
			rez = rez + this.middleName.getStringValue() + " ";
		}
		if (this.name.getStringValue() != null) {
			rez = rez + this.name.getStringValue();
		}
		return rez;
	}
}
