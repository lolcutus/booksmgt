package ro.cuzma.books;

import ro.cuzma.db.DBException;
import ro.cuzma.tools.MyStringTokenizer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Loan {
	/**
	 * @uml.property  name="pers"
	 */
	private String	pers		= "";
	/**
	 * @uml.property  name="loanDate"
	 */
	private String	loanDate	= "";
	/**
	 * @uml.property  name="returnDate"
	 */
	private String	returnDate	= null;

	public Loan(String pers, String loanDate, String returnDate) {
		this.pers = pers;
		this.loanDate = loanDate;
		if (returnDate == null || returnDate.equals("")) {
			this.returnDate = null;
		} else {
			this.returnDate = returnDate;
		}
	}

	public static void main(String[] args) throws DBException {
		Author author = new Author(1, "Lewis");
		//author.show();
	}

	public String toXml(String indent) {
		String rez = "";
		rez = rez + indent + "<loan>\r\n";
		rez = rez + indent + "\t<pers>" + this.pers + "</pers>\r\n";
		rez = rez + indent + "\t<loanDate>" + this.loanDate + "</loanDate>\r\n";
		if (this.returnDate != null) {
			rez = rez + indent + "\t<returnDate>" + this.returnDate + "</returnDate>\r\n";
		}
		rez = rez + indent + "</loan>\r\n";
		return rez;
	}

	public static Loan processNode(Node loan) {
		NodeList children = loan.getChildNodes();
		String pers = null;
		String loanDate = null;
		String returnDate = null;
		Loan result = null;
		int id = -1;
		if (children != null) {
			int len = children.getLength();
			// System.out.println("childs len: " + len);
			Node tmp;
			for (int i = 0; i < len; i++) {
				tmp = children.item(i);
				if (tmp.getNodeType() == Node.ELEMENT_NODE) {
					if (tmp.getLocalName().equalsIgnoreCase("pers")) {
						pers = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("loanDate")) {
						loanDate = tmp.getChildNodes().item(0).getNodeValue();
					} else if (tmp.getLocalName().equalsIgnoreCase("returnDate")) {
						if (tmp.getChildNodes().item(0) != null && !tmp.getChildNodes().item(0).equals("")) {
							returnDate = tmp.getChildNodes().item(0).getNodeValue();
						}
					}
				}
			}
			result = new Loan(pers, loanDate, returnDate);
		}
		return result;
	}

	/**
	 * @return  the loanDate
	 * @uml.property  name="loanDate"
	 */
	public String getLoanDate() {
		return loanDate;
	}

	/**
	 * @param loanDate  the loanDate to set
	 * @uml.property  name="loanDate"
	 */
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	/**
	 * @return  the pers
	 * @uml.property  name="pers"
	 */
	public String getPers() {
		return pers;
	}

	/**
	 * @param pers  the pers to set
	 * @uml.property  name="pers"
	 */
	public void setPers(String pers) {
		this.pers = pers;
	}

	/**
	 * @return  the returnDate
	 * @uml.property  name="returnDate"
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate  the returnDate to set
	 * @uml.property  name="returnDate"
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
