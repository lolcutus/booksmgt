package ro.cuzma.books;

import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class GetDataFromName {
	/**
	 * @uml.property  name="startFolder"
	 */
	private String startFolder;
	/**
	 * @uml.property  name="books"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="ro.cuzma.books.Book"
	 */
	private Vector books = new Vector();
	public GetDataFromName(String startFolder) {
		this.startFolder = startFolder;
	}

	public static void main(String[] args) {
		if (args.length == 0){
			System.out.println("usage: java larry.books.GetDataFromName startFolder");
		}else{
			GetDataFromName getDataFromName = new GetDataFromName(args[0]);
		    getDataFromName.process();
		    getDataFromName.toCSV();
		}
	}
	public void process(){
		File stDir = new File(this.startFolder);
	    processDir(stDir);
		
	}
	private void processDir(File curDir){
		BookRow tmp = null;
	    if(curDir.isDirectory()){
	        File[] allFielsInDir = curDir.listFiles();
			File tmpFile = null;
	        for(int i = 0; i < allFielsInDir.length; i++){
				tmpFile = allFielsInDir[i].getAbsoluteFile();
	            if(!tmpFile.isDirectory()){
					//System.out.println(tmpFile.getAbsolutePath());
					//tmp = new Book(this.startFolder,tmpFile);
					if (tmp.isBook()){
						this.books.add(tmp);
					}else{
					    System.out.println(tmpFile.getAbsolutePath());
					}
	            } else{
	                processDir(allFielsInDir[i]);
	            }
	        }
		}
	}
	public void show(){
	    Vector tmp = this.books;
	    BookRow aut = null;
	    for(int i = 0;i < tmp.size();i++){
	        aut = (BookRow)tmp.get(i);
	        //aut.show();
			System.out.println("==================================");
	    }
	}
	public void toCSV(){
		Vector tmp = this.getBooks();
		BookRow book = null;
		for(int i = 0;i < tmp.size();i++){
			book = (BookRow)tmp.get(i);
			//System.out.println(book.toCSV());
		}
	}

	/**
	 * @param startFolder  the startFolder to set
	 * @uml.property  name="startFolder"
	 */
	public void setStartFolder(String startFolder) {
		this.startFolder = startFolder;
	}

	/**
	 * @return  the startFolder
	 * @uml.property  name="startFolder"
	 */
	public String getStartFolder() {
		return startFolder;
	}

	/**
	 * @param books  the books to set
	 * @uml.property  name="books"
	 */
	public void setBooks(Vector books) {
		this.books = books;
	}

	/**
	 * @return  the books
	 * @uml.property  name="books"
	 */
	public Vector getBooks() {
		return books;
	}
}
