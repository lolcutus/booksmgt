package ro.cuzma.books.exceptions;

public class BooksException extends Exception {
	  public BooksException(String msg)
	    {
	        super(msg);
	    }

	    public BooksException(Throwable exception)
	    {
	        super(exception);
	    }

	    public BooksException(String msg, Throwable innerException)
	    {
	        super(msg, innerException);
	    }
}
