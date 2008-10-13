package ro.cuzma.books;

import java.util.Vector;
import javax.swing.JOptionPane;

import ro.cuzma.db.TableData;

public class AuthorData extends TableData {
	private String	filterFirstName		= null;
	private String	filterName			= null;
	private String	filterMiddleName	= null;

	public AuthorData(Vector rows) {
		super(rows,"authors");
	}

	public Vector getSortedFilteredData() {
		// TODO Auto-generated method stub
		if (rows == null)
			return null;
		Vector tmp = new Vector();
		Author au;
		boolean addAuthor = true;
		for (int i = 0; i < rows.size(); i++) {
			au = (Author) rows.get(i);
			addAuthor = true;
			if (addAuthor == true) {
				if (this.filterFirstName == null || ((String)au.getValue(Author.COLUMN_FIRST_NAME)).toUpperCase().indexOf(this.filterFirstName.toUpperCase()) != -1) {
					addAuthor = true;
				} else {
					addAuthor = false;
				}
			}
			if (addAuthor == true) {
				if (this.filterName == null || ((String)au.getValue(Author.COLUMN_NAME)).toUpperCase().indexOf(this.filterName.toUpperCase()) != -1) {
					addAuthor = true;
				} else {
					addAuthor = false;
				}
			}
			//System.out.println(this.filterMiddleName);
			
			if (addAuthor == true) {
				if (this.filterMiddleName == null || ((String)au.getDisplayValue(Author.COLUMN_MIDDLE_NAME)).toUpperCase().indexOf(this.filterMiddleName.toUpperCase()) != -1) {
					addAuthor = true;
				} else {
					addAuthor = false;
				}
			}
			if (addAuthor == true) {
				tmp.add(au);
			}
		}
		return tmp;
	}

	public void setFilter(String firstName,  String middleName,String name) {
		if (firstName == null || firstName.equals("")) {
			this.filterFirstName = null;
		} else {
			this.filterFirstName = firstName;
		}
		if (name == null || name.equals("")) {
			this.filterName = null;
		} else {
			this.filterName = name;
		}
		if (middleName == null || middleName.equals("")) {
			this.filterMiddleName = null;
		} else {
			this.filterMiddleName = middleName;
		}
			}
}
