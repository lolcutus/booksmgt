package ro.cuzma.books.ui;

import ro.cuzma.books.BookRow;
import ro.cuzma.books.BookData;
import ro.cuzma.books.BooksDatabase;
import ro.cuzma.books.ui.book.BookCellRenderJLabel;
import ro.cuzma.books.ui.book.BookPanelAdd;
import ro.cuzma.books.ui.book.BookPanelTable;
import ro.cuzma.books.ui.book.BookTable;
import ro.cuzma.event.CustomEvent;
import ro.cuzma.event.CustomEventDispatcher;
import ro.cuzma.event.CustomEventListener;
import ro.cuzma.ui.TableWithColumnModel;
//import com.cuzma.util.Log4JUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class BooksManagement extends javax.swing.JFrame implements CustomEventListener {
	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			try {
				javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	private JMenuItem			helpMenuItem;
	private JMenu				jMenu5;
	private JPanel				jPanelNothing				= new JPanel();
	private JMenu				jMenu4;
	private JMenuItem			exitMenuItem;
	private JSeparator			jSeparator2;
	private JPanel				jPanelCentral;
	private BookPanelTable		jPanelBooks;
	private JMenuItem			jMenuItemBooksDel;
	private JMenuItem			jMenuItemBooksEdit;
	private JMenuItem			jMenuItemBooksNew;
	private JMenu				jMenuEditBooks;
	private JMenuItem			saveAsMenuItem;
	private JMenuItem			saveMenuItem;
	private JMenuItem			openFileMenuItem;
	private JMenuItem			newFileMenuItem;
	private JMenu				jMenu3;
	private JMenuBar			jMenuBar1;
	private BooksDatabase		db;
	private BookPanelAdd		jBookAdd;
	private final int			SHOW_NOTHING				= 0;
	private final int			SHOW_BOOKS					= 1;
	private final int			SHOW_BOOK_ADD				= 2;
	private final int			SHOW_BOOK_EDIT				= 3;
	private int					whatToShow					= SHOW_NOTHING;
	public static final String	CE_BOOKM_TYPE				= "BOOKM";
	public static final String	CE_BOOKM_VALUE_SHOW_BOOKS	= "SBOOKS";
	public static final String	CE_BOOKM_VALUE_SHOW_BOOK_EDIT	= "SBOOKEDIT";

	/**
	 * This method initializes this
	 */
	private void initialize() {
		CustomEventDispatcher.getCustomEventDispatcher().addNewListener(this);
		this.setSize(new Dimension(203, 59));
		jPanelCentral = jPanelNothing;
		getContentPane().add(jPanelCentral, BorderLayout.CENTER);
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		//Log4JUtil li = new Log4JUtil();
		//li.init("e:\\carti\\log.ini");
		BooksManagement inst = new BooksManagement();
		inst.setVisible(true);
		if (args[0] != null) {
			inst.openDB(new File(args[0]));
		}
	}

	public BooksManagement() {
		super();
		initialize();
		initGUI();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		{
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
		}
		this.setFocusCycleRoot(false);
		this.setPreferredSize(new java.awt.Dimension(864, 498));
		this.setTitle("Book Management");
		try {
			this.setSize(864, 498);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("New");
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
						openFileMenuItem.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								openFileMenuItemMouseReleased(evt);
							}
						});
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Save");
						saveMenuItem.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								saveMenuItemMouseReleased(evt);
							}
						});
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenu3.add(saveAsMenuItem);
						saveAsMenuItem.setText("Save As ...");
						saveAsMenuItem.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								saveAsMenuItemMouseReleased(evt);
							}
						});
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Edit");
					{
						jMenuEditBooks = new JMenu();
						jMenu4.add(jMenuEditBooks);
						jMenuEditBooks.setText("Books");
						{
							jMenuItemBooksNew = new JMenuItem();
							jMenuEditBooks.add(jMenuItemBooksNew);
							jMenuItemBooksNew.setText("Add");
							jMenuItemBooksNew.addMouseListener(new MouseAdapter() {
								public void mouseReleased(MouseEvent evt) {
									jMenuItemBooksNewMouseReleased(evt);
								}
							});
						}
						{
							jMenuItemBooksEdit = new JMenuItem();
							jMenuEditBooks.add(jMenuItemBooksEdit);
							jMenuItemBooksEdit.setText("Edit");
							jMenuItemBooksEdit.addMouseListener(new MouseAdapter() {
								public void mouseReleased(MouseEvent evt) {
									jMenuItemBooksEditMouseReleased(evt);
								}
							});
						}
						{
							jMenuItemBooksDel = new JMenuItem();
							jMenuEditBooks.add(jMenuItemBooksDel);
							jMenuItemBooksDel.setText("Delete");
							jMenuItemBooksDel.addMouseListener(new MouseAdapter() {
								public void mouseReleased(MouseEvent evt) {
									jMenuItemBooksDelMouseReleased(evt);
								}
							});
						}
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openFileMenuItemMouseReleased(MouseEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// if(lastDir != null) {
		// chooser.setCurrentDirectory(new File(this.picDB.getRoot()));
		// }
		File thFile;
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			openDB(chooser.getSelectedFile());
			// showTable();
		}
	}

	private void openDB(File dbFile) {
		db = new BooksDatabase();
		db.load(dbFile);
		//if (jPanelBooks == null) {
		//} else {
		//jPanelBooks.setRows(db.getBooks());
		//}
		this.setTitle("Book Management: " + dbFile.getAbsoluteFile());
		showCentralPannel(SHOW_BOOKS);
	}

	/*
	 * public void showTable() {
	 * this.jTableBooks.setNewData(this.jTableBooks.reloadData());
	 * this.jTableBooks.revalidate(); this.jTableBooks.repaint(); //
	 * this.jScrollPaneTable.revalidate(); // this.jScrollPaneTable.repaint(); }
	 */
	private void saveMenuItemMouseReleased(MouseEvent evt) {
		try {
			db.save();
			JOptionPane.showMessageDialog(null, "Save succesfull");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void jMenuItemBooksDelMouseReleased(MouseEvent evt) {
		if (jPanelBooks != null) {
			this.jPanelBooks.getTableBooks().deleteSelectedBook();
		} else {
			JOptionPane.showMessageDialog(null, "Not right view ;)!!!");
		}
	}

	private void jMenuItemBooksNewMouseReleased(MouseEvent evt) {
		showCentralPannel(SHOW_BOOK_ADD);
	}

	private void showCentralPannel(int whatToShow) {
		if (this.whatToShow != whatToShow) {
			switch (whatToShow) {
			case SHOW_NOTHING:
				this.whatToShow = whatToShow;
				this.remove(jPanelCentral);
				jPanelCentral = jPanelNothing;
				getContentPane().add(jPanelCentral, BorderLayout.CENTER);
				jPanelBooks = null;
				jBookAdd = null;
				jPanelCentral.repaint();
				jPanelCentral.revalidate();
				this.repaint();
				break;
			case SHOW_BOOKS:
				this.whatToShow = whatToShow;
				this.remove(jPanelCentral);
				jPanelBooks = new BookPanelTable(db.getBooks(),db.getLangs(),db.getTypes());
				jPanelCentral = jPanelBooks;
				getContentPane().add(jPanelCentral, BorderLayout.CENTER);
				jBookAdd = null;
				jPanelCentral.repaint();
				jPanelCentral.revalidate();
				this.repaint();
				break;
			case SHOW_BOOK_ADD:
				this.whatToShow = whatToShow;
				this.remove(jPanelCentral);
				//if (jBookAdd == null) {
				//	System.out.println("Add null");
				jBookAdd = new BookPanelAdd(db);
				//}
				//System.out.println("after Add null");
				jPanelCentral = jBookAdd;
				getContentPane().add(jPanelCentral, BorderLayout.CENTER);
				jPanelBooks = null;
				jPanelCentral.repaint();
				jPanelCentral.revalidate();
				this.repaint();
				break;
			case SHOW_BOOK_EDIT:
				int selRow = this.jPanelBooks.getTableBooks().getSelectedRow();
				if (selRow >= 0) {
					this.whatToShow = whatToShow;
					BookRow book = (BookRow) this.jPanelBooks.getTableBooks().getValueAt(selRow, 0);
					this.remove(jPanelCentral);
					jBookAdd = new BookPanelAdd(db, book);
					//System.out.println("after Add null");
					jPanelCentral = jBookAdd;
					getContentPane().add(jPanelCentral, BorderLayout.CENTER);
					jPanelBooks = null;
					jPanelCentral.repaint();
					jPanelCentral.revalidate();
					this.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "No row selected!!!");
				}
				break;
			}
		}
	}

	private void jMenuItemBooksEditMouseReleased(MouseEvent evt) {
		showCentralPannel(SHOW_BOOK_EDIT);
	}

	public void handleCustomEvent(CustomEvent ce) {
		if (ce.getEventType().equals(BooksManagement.CE_BOOKM_TYPE)) {
			if (ce.getEventValue().equals(BooksManagement.CE_BOOKM_VALUE_SHOW_BOOKS)) {
				showCentralPannel(SHOW_BOOKS);
			}else if (ce.getEventValue().equals(BooksManagement.CE_BOOKM_VALUE_SHOW_BOOK_EDIT)) {
				showCentralPannel(SHOW_BOOK_EDIT);
				}
		}
	}
	
	private void saveAsMenuItemMouseReleased(MouseEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// if(lastDir != null) {
		// chooser.setCurrentDirectory(new File(this.picDB.getRoot()));
		// }
		File thFile;
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				db.save(chooser.getSelectedFile());
				this.setTitle("Book Management: " + chooser.getSelectedFile().getAbsoluteFile());
				JOptionPane.showMessageDialog(null, "Save succesfull");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
			// showTable();
		}
	}
} // @jve:decl-index=0:visual-constraint="10,10"
