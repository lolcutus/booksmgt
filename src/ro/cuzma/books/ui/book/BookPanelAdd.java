package ro.cuzma.books.ui.book;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ro.cuzma.books.Author;
import ro.cuzma.books.BookRow;
import ro.cuzma.books.BooksDatabase;
import ro.cuzma.books.ui.BooksManagement;
import ro.cuzma.books.ui.author.AuthorPanelTable;
import ro.cuzma.db.ValueDisplay;
import ro.cuzma.event.CustomEvent;
import ro.cuzma.ui.TableComboBox;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
public class BookPanelAdd extends JPanel {
	private String CLASS_NAME = this.getClass().getName();
	private JButton				jButtonCancel;
	private JButton				jButtonAdd;
	private JPanel				jPanelButtons;
	private JPanel				jPanelData;
	// Title
	private JTextField			jTextTitle;
	private JLabel				jLabelTitle;
	private JLabel jLabelFormat;
	private TableComboBox			jFormat;
	private JScrollPane			jScrollPaneAuthors;
	// Title Orig
	private JTextField			jTextTitleO;
	private JLabel				jLabelTitleO;
	// Title Serie
	private JTextField			jTextSerie;
	private JLabel				jLabelSerie;
	// Title Orig
	private JTextField			jTextSerieN;
	private JLabel				jLabelSerieN;
	// Read
	private JTextField			jTextRead;
	private JLabel				jLabelRead;
	// BookType
	private JTextField			jTextBookType;
	private JLabel				jLabelBookType;
	// Language
	private TableComboBox			jLang;
	private JLabel				jLabelLanguage;
	// Publisher
	private JTextField			jTextPublisher;
	private JLabel				jLabelPublisher;
	// Status
	private JTextField			jTextStatus;
	private JLabel				jLabelStatus;
	// Collection
	private JTextField			jTextCollection;
	private JLabel				jLabelCollection;
	// CollectionNR
	private JTextField			jTextCollectionNR;
	private JLabel				jLabelCollectionNR;
	// BoughtDate
	private JTextField			jTextBoughtDate;
	private JLabel				jLabelBoughtDate;
	// AparitionDate
	private JTextField			jTextAparitionDate;
	private JLabel				jLabelAparitionDate;
	// Price
	private JTextField			jTextPrice;
	private JLabel				jLabelPrice;
	// Currency
	private JTextField			jTextCurrency;
	private JLabel				jLabelCurrency;
	// Buttons Authors
	private JButton				jButtonAddAu	= new JButton("Add Author");
	private JButton				jButtonReAu		= new JButton("Remove Author");
	// Authors
	private JTextArea			jTextAuthors;
	private JLabel				jLabelAuthors;
	// ISBN
	private JTextField			jTextISBN;
	private JLabel				jLabelISBN;
	// ISBN
	private JCheckBox			jcbToRead;
	private JLabel				jLabelToRead;
	private AuthorPanelTable	jAuthors;
	private JSplitPane			jSplitPane;
	//Vector						Authors			= new Vector();
	private Vector<Author>		authors			= new Vector<Author>();
	private String				fileType;
	private String				fileName;
	BookRow						bkID			= null;
	private BooksDatabase bDB;

	public BookPanelAdd(BooksDatabase bDB) {
		super();
		this.bDB = bDB;
		initGUI();
	}

	public BookPanelAdd(BooksDatabase bDB, BookRow bkID) {
		super();
		this.bDB = bDB;
		initGUI();
		this.bkID = bkID;
		jButtonAdd.setText("Save");
		
		//Logger.getLogger(CLASS_NAME).debug(bkID.getValue(Book.COLUMN_AUTHORS).getClass().getName());
		authors = (Vector)bkID.getValue(BookRow.COLUMN_AUTHORS);
		jTextTitle.setText((String)bkID.getDisplayValue(BookRow.COLUMN_TITLE));
		jTextTitleO.setText((String)bkID.getDisplayValue(BookRow.COLUMN_ORIG_TITLE));
		jTextRead.setText((String)bkID.getDisplayValue(BookRow.COLUMN_READ));
		jTextSerie.setText((String)bkID.getDisplayValue(BookRow.COLUMN_SERIE));
		jTextSerieN.setText((String)bkID.getDisplayValue(BookRow.COLUMN_SERIE_POS));
		jTextBookType.setText((String)bkID.getDisplayValue(BookRow.COLUMN_BOOK_TYPE));
		jLang.setSelectedItem(bkID.getValue(BookRow.COLUMN_LANGUAGE));
		jFormat.setSelectedItem(bkID.getValue(BookRow.COLUMN_FORMAT));
		jTextPublisher.setText((String)bkID.getDisplayValue(BookRow.COLUMN_PUBLISHER));
		jTextStatus.setText((String)bkID.getDisplayValue(BookRow.COLUMN_STATUS));
		jTextCollection.setText((String)bkID.getDisplayValue(BookRow.COLUMN_COLLECTION));
		jTextCollectionNR.setText((String)bkID.getDisplayValue(BookRow.COLUMN_COLLECTION_POS));
		jTextBoughtDate.setText((String)bkID.getDisplayValue(BookRow.COLUMN_BOUGHT_DATE));
		jTextAparitionDate.setText((String)bkID.getDisplayValue(BookRow.COLUMN_APPARITION_DATE));
		jTextPrice.setText((String)bkID.getDisplayValue(BookRow.COLUMN_PRICE));
		jTextCurrency.setText((String)bkID.getDisplayValue(BookRow.COLUMN_CURRENCY));
		Author tmp;
		String rez = "";
		for (int i = 0; i < authors.size(); i++) {
			tmp = (Author) authors.get(i);
			if (rez.equals("")) {
				rez = tmp.getStringRow();
			} else {
				rez = rez + "\n" + tmp.getStringRow();
			}
		}
		this.jTextAuthors.setText(rez);
		jTextISBN.setText((String)bkID.getDisplayValue(BookRow.COLUMN_ISBN));
		jcbToRead.setSelected(((Boolean)bkID.getValue(BookRow.COLUMN_TOREAD)).booleanValue());
		//private AuthorPanelTable	jAuthors;
		//private Vector<Author>		authors			= new Vector<Author>();
		//fileType.setText(bkID.getTitleOrig());
		//fileName.setText(bkID.getTitleOrig());

		
	}

	private void initGUI() {
		try {
			{
				BorderLayout thisLayout = new BorderLayout();
				this.setLayout(thisLayout);
				this.setPreferredSize(new java.awt.Dimension(761, 563));
				{
					jPanelButtons = new JPanel();
					GridBagLayout jPanelButtonsLayout = new GridBagLayout();
					jPanelButtonsLayout.rowWeights = new double[] { 0.1 };
					jPanelButtonsLayout.rowHeights = new int[] { 7 };
					jPanelButtonsLayout.columnWeights = new double[] { 0.0, 0.0, 0.1, 0.1 };
					jPanelButtonsLayout.columnWidths = new int[] { 82, 84, 7, 7 };
					jPanelButtons.setLayout(jPanelButtonsLayout);
					this.add(jPanelButtons, BorderLayout.NORTH);
					{
						jButtonAdd = new JButton();
						jPanelButtons.add(jButtonAdd, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jButtonAdd.setText("Add");
						jButtonAdd.setSize(80, 23);
						jButtonAdd.setPreferredSize(new java.awt.Dimension(80, 23));
						jButtonAdd.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								jButtonAddMouseReleased(evt);
							}
						});
					}
					{
						jButtonCancel = new JButton();
						jPanelButtons.add(jButtonCancel, new GridBagConstraints(2, -1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jButtonCancel.setText("Back to Books");
						jButtonCancel.addMouseListener(new MouseAdapter() {
							public void mouseReleased(MouseEvent evt) {
								jButtonCancelMouseReleased(evt);
							}
						});
					}
				}
				{
					jSplitPane = new JSplitPane();
					this.add(jSplitPane, BorderLayout.CENTER);
					jSplitPane.setPreferredSize(new java.awt.Dimension(500, 25));
					{
						jPanelData = new JPanel();
						jSplitPane.add(jPanelData, JSplitPane.LEFT);
						GridBagLayout jPanelDataLayout = new GridBagLayout();
						jPanelDataLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
						// jPanelDataLayout.rowHeights = new int[] {40, 20, 20,
						// 20, 20, 20, 20, 20, 20, 20, 20, 7, 7, 7, 7, 7, 7, 7,
						// 7, 7, 7, 7};
						jPanelDataLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
						jPanelDataLayout.columnWidths = new int[] { 17, 112, 256, 21 };
						jPanelData.setLayout(jPanelDataLayout);
						jPanelData.setPreferredSize(new java.awt.Dimension(549, 371));
						jPanelDataLayout.rowHeights = new int[] {-38, 20, 20, 20, 20, 20, 20, 20, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 55, 18, 16, 3, 18};
						// Title
						{
							jTextTitle = new JTextField();
							jPanelData.add(jTextTitle, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextTitle.setText("");
							jTextTitle.setSize(300, 20);
							jTextTitle.setPreferredSize(new java.awt.Dimension(300, 20));
							jTextTitle.setAutoscrolls(false);
							jLabelTitle = new JLabel();
							jPanelData.add(jLabelTitle, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelTitle.setText("Title");
						}
						// Title Orig
						{
							jTextTitleO = new JTextField();
							jPanelData.add(jTextTitleO, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextTitleO.setText("");
							jTextTitleO.setSize(300, 20);
							jTextTitleO.setPreferredSize(new java.awt.Dimension(300, 20));
							jLabelTitleO = new JLabel();
							jPanelData.add(jLabelTitleO, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelTitleO.setText("Original Title");
						}
						// Serie
						{
							jTextSerie = new JTextField();
							jPanelData.add(jTextSerie, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextSerie.setText("");
							jTextSerie.setSize(150, 20);
							jTextSerie.setPreferredSize(new java.awt.Dimension(150, 20));
							jLabelSerie = new JLabel();
							jPanelData.add(jLabelSerie, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelSerie.setText("Serie");
						}
						// Serie NR
						{
							jTextSerieN = new JTextField();
							jPanelData.add(jTextSerieN, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextSerieN.setSize(30, 20);
							jTextSerieN.setPreferredSize(new java.awt.Dimension(30, 20));
							jLabelSerieN = new JLabel();
							jPanelData.add(jLabelSerieN, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelSerieN.setText("Serie Position");
						}
						// Read
						{
							jTextRead = new JTextField();
							jPanelData.add(jTextRead, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextRead.setSize(30, 20);
							jTextRead.setPreferredSize(new java.awt.Dimension(30, 20));
							jLabelRead = new JLabel();
							jPanelData.add(jLabelRead, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelRead.setText("Read");
						}
						// BookType
						{
							jTextBookType = new JTextField();
							jPanelData.add(jTextBookType, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextBookType.setSize(100, 20);
							jTextBookType.setPreferredSize(new java.awt.Dimension(100, 20));
							jLabelBookType = new JLabel();
							jPanelData.add(jLabelBookType, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelBookType.setText("Book Type");
						}
						// Language
						{
							jLang  = new TableComboBox(this.bDB.getLangs(),false);;
							jPanelData.add(jLang, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLang.setSize(100, 20);
							jLang.setPreferredSize(new java.awt.Dimension(100, 20));
							jLabelLanguage = new JLabel();
							jPanelData.add(jLabelLanguage, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelLanguage.setText("Language");
						}
						//format
						{
							jFormat  = new TableComboBox(this.bDB.getTypes(),false);;
							jPanelData.add(jFormat, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jFormat.setSize(100, 20);
							jFormat.setPreferredSize(new java.awt.Dimension(100, 20));
							
							jLabelFormat = new JLabel();
							jPanelData.add(jLabelFormat, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelFormat.setText("Format");
						}
						// Publisher
						{
							jTextPublisher = new JTextField();
							jPanelData.add(jTextPublisher, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextPublisher.setSize(200, 20);
							jTextPublisher.setPreferredSize(new java.awt.Dimension(200, 20));
							jLabelPublisher = new JLabel();
							jPanelData.add(jLabelPublisher, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelPublisher.setText("Publisher");
						}
						// Status
						{
							jTextStatus = new JTextField();
							jPanelData.add(jTextStatus, new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextStatus.setSize(70, 20);
							jTextStatus.setPreferredSize(new java.awt.Dimension(70, 20));
							jLabelStatus = new JLabel();
							jPanelData.add(jLabelStatus, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelStatus.setText("Status");
						}
						// Collection
						{
							jTextCollection = new JTextField();
							jPanelData.add(jTextCollection, new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextCollection.setSize(150, 20);
							jTextCollection.setPreferredSize(new java.awt.Dimension(150, 20));
							jLabelCollection = new JLabel();
							jPanelData.add(jLabelCollection, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelCollection.setText("Collection");
						}
						// CollectionNR
						{
							jTextCollectionNR = new JTextField();
							jPanelData.add(jTextCollectionNR, new GridBagConstraints(2, 12, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextCollectionNR.setSize(50, 20);
							jTextCollectionNR.setPreferredSize(new java.awt.Dimension(50, 20));
							jLabelCollectionNR = new JLabel();
							jPanelData.add(jLabelCollectionNR, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelCollectionNR.setText("Collection Pos");
						}
						// BoughtDate
						{
							jTextBoughtDate = new JTextField();
							jPanelData.add(jTextBoughtDate, new GridBagConstraints(2, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextBoughtDate.setSize(80, 20);
							jTextBoughtDate.setPreferredSize(new java.awt.Dimension(80, 20));
							jLabelBoughtDate = new JLabel();
							jPanelData.add(jLabelBoughtDate, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelBoughtDate.setText("Bought Date");
						}
						// AparitionDate
						{
							jTextAparitionDate = new JTextField();
							jPanelData.add(jTextAparitionDate, new GridBagConstraints(2, 14, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextAparitionDate.setSize(80, 20);
							jTextAparitionDate.setPreferredSize(new java.awt.Dimension(80, 20));
							jLabelAparitionDate = new JLabel();
							jPanelData.add(jLabelAparitionDate, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelAparitionDate.setText("Aparition Date");
						}
						// Price
						{
							jTextPrice = new JTextField();
							jPanelData.add(jTextPrice, new GridBagConstraints(2, 15, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextPrice.setSize(50, 20);
							jTextPrice.setPreferredSize(new java.awt.Dimension(50, 20));
							jLabelPrice = new JLabel();
							jPanelData.add(jLabelPrice, new GridBagConstraints(1, 15, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelPrice.setText("Price");
						}
						// Currency
						{
							jTextCurrency = new JTextField();
							jPanelData.add(jTextCurrency, new GridBagConstraints(2, 16, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextCurrency.setSize(50, 20);
							jTextCurrency.setPreferredSize(new java.awt.Dimension(50, 20));
							jLabelCurrency = new JLabel();
							jPanelData.add(jLabelCurrency, new GridBagConstraints(1, 16, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelCurrency.setText("Currency");
						}
						// Authors
						{
							jPanelData.add(jButtonReAu, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jButtonReAu.setSize(90, 20);
							jButtonReAu.setPreferredSize(new java.awt.Dimension(90, 20));
							jButtonReAu.addMouseListener(new MouseAdapter() {
								public void mouseReleased(MouseEvent evt) {
									jButtonReAuMouseReleased(evt);
								}
							});
							jPanelData.add(jButtonAddAu, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jButtonAddAu.setSize(90, 20);
							jButtonAddAu.setPreferredSize(new java.awt.Dimension(90, 20));
							jButtonAddAu.addMouseListener(new MouseAdapter() {
								public void mouseReleased(MouseEvent evt) {
									jButtonAddAuMouseReleased(evt);
								}
							});
						}
						// Authors
						// ISBN
						{
							jTextISBN = new JTextField();
							jPanelData.add(jTextISBN, new GridBagConstraints(2, 19, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextISBN.setSize(100, 20);
							jTextISBN.setPreferredSize(new java.awt.Dimension(100, 20));
							jLabelISBN = new JLabel();
							jPanelData.add(jLabelISBN, new GridBagConstraints(1, 19, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelISBN.setText("ISBN");
						}
						// TOREAD
						{
							jcbToRead = new JCheckBox();
							jPanelData.add(jcbToRead, new GridBagConstraints(2, 20, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelToRead = new JLabel();
							jPanelData.add(jLabelToRead, new GridBagConstraints(1, 20, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelToRead.setText("Read?");
						}
						{
							jScrollPaneAuthors = new JScrollPane();
							jScrollPaneAuthors.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							jPanelData.add(jScrollPaneAuthors, new GridBagConstraints(2, 18, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextAuthors = new JTextArea();
							jScrollPaneAuthors.setSize(300, 60);
							jScrollPaneAuthors.setPreferredSize(new java.awt.Dimension(300, 60));
							jScrollPaneAuthors.setViewportView(jTextAuthors);
							jLabelAuthors = new JLabel();
							jPanelData.add(jLabelAuthors, new GridBagConstraints(1, 18, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelAuthors.setText("Authors");
						}
						
					}
					{
						jAuthors = new AuthorPanelTable(bDB.getAuthors());
						jSplitPane.add(jAuthors, JSplitPane.RIGHT);
						jAuthors.setPreferredSize(new java.awt.Dimension(106, 352));
						// jAuthors.setModel(jTable1Model);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addAuthor(Author au) {
		Author tmp;
		String rez = "";
		for (int i = 0; i < authors.size(); i++) {
			tmp = (Author) authors.get(i);
			if (tmp.isEqual(au)) {
				return;
			}
			if (rez.equals("")) {
				rez = tmp.getStringRow();
			} else {
				rez = rez + "\n" + tmp.getStringRow();
			}
		}
		if (rez.equals("")) {
			rez = au.getStringRow();
		} else {
			rez = rez + "\n" + au.getStringRow();
		}
		authors.add(au);
		this.jTextAuthors.setText(rez);
	}

	private void removeAuthor(Author au) {
		authors.remove(au);
		Author tmp;
		String rez = "";
		for (int i = 0; i < authors.size(); i++) {
			tmp = (Author) authors.get(i);
			if (rez.equals("")) {
				rez = tmp.getStringRow();
			} else {
				rez = rez + "\n" + tmp.getStringRow();
			}
		}
		this.jTextAuthors.setText(rez);
	}

	private void jButtonAddAuMouseReleased(MouseEvent evt) {
		int selRow = jAuthors.getJTableAuthors().getSelectedRow();
		if (selRow >= 0) {
			Author au = (Author) jAuthors.getJTableAuthors().getValueAt(selRow, 0);
			addAuthor(au);
		}
	}

	private void jButtonReAuMouseReleased(MouseEvent evt) {
		int selRow = jAuthors.getJTableAuthors().getSelectedRow();
		if (selRow >= 0) {
			Author au = (Author) jAuthors.getJTableAuthors().getValueAt(selRow, 0);
			removeAuthor(au);
		}
	}

	private void addBook() throws Exception {
		String rootPath = "";
		String relativePath = null;
		Vector bAuthors = authors;
		Vector tLoans = new Vector();
		String title = jTextTitle.getText();
		String titleOrig = jTextTitleO.getText();
		int readNr = 0;
		if (jTextRead.getText().equals("")){
			readNr = 0;
		}else{
			readNr = (new Integer(jTextRead.getText())).intValue();
		}
		String fileName = null;
		ValueDisplay language = (ValueDisplay)jLang.getSelectedItem();
		ValueDisplay fileType = (ValueDisplay)jFormat.getSelectedItem();
		Integer bookID = new Integer(bDB.getBooks().getNewBookID());
		String status = jTextStatus.getText();
		String publisher = jTextPublisher.getText();
		String collection = jTextCollection.getText();
		String collectionNR = jTextCollectionNR.getText();
		String boughtDate = jTextBoughtDate.getText();
		String aparitionDate = jTextAparitionDate.getText();
		Float price = new Float(0);
		if (!jTextPrice.getText().equals("")){
			price = new Float(jTextPrice.getText());
		}
		String currency = jTextCurrency.getText();
		String bookType = jTextBookType.getText();
		String serie = jTextSerie.getText();
		Integer seriePos = null; 
			if (!jTextSerieN.getText().equals("")){
				seriePos = new Integer(jTextSerieN.getText());
			}
		Long isbn = null;
		if (!jTextISBN.getText().equals("")){
			isbn = new Long(jTextISBN.getText());
		}
		boolean toRead = jcbToRead.isSelected();
		String loanPers = null;
		String loanDate = null;
		BookRow bk = new BookRow(bookID, rootPath, relativePath, bAuthors, title, titleOrig, readNr, fileType, fileName, language, status, publisher, collection, collectionNR, boughtDate, aparitionDate,
						price, currency, bookType, serie, seriePos, tLoans, isbn, toRead,loanPers,loanDate);
		bDB.getBooks().add(bk);
		JOptionPane.showMessageDialog(null, "Add succesfull");
	}
	private void saveBook() throws Exception {
		bkID.setValue(BookRow.COLUMN_TITLE,jTextTitle.getText());
		bkID.setValue(BookRow.COLUMN_ORIG_TITLE,jTextTitleO.getText());
		bkID.setValue(BookRow.COLUMN_AUTHORS,authors);
		bkID.setValue(BookRow.COLUMN_SERIE,jTextSerie.getText());
		bkID.setStringValue(BookRow.COLUMN_SERIE_POS,jTextSerieN.getText());
		bkID.setStringValue(BookRow.COLUMN_READ,jTextRead.getText());
		bkID.setValue(BookRow.COLUMN_BOOK_TYPE,jTextBookType.getText());
		bkID.setValue(BookRow.COLUMN_LANGUAGE,jLang.getSelectedItem());
		bkID.setValue(BookRow.COLUMN_FORMAT,jFormat.getSelectedItem());
		bkID.setValue(BookRow.COLUMN_STATUS,jTextStatus.getText());
		bkID.setValue(BookRow.COLUMN_PUBLISHER,jTextPublisher.getText());
		bkID.setValue(BookRow.COLUMN_COLLECTION,jTextCollection.getText());
		bkID.setStringValue(BookRow.COLUMN_COLLECTION_POS,jTextCollectionNR.getText());
		bkID.setStringValue(BookRow.COLUMN_BOUGHT_DATE,jTextBoughtDate.getText());
		bkID.setStringValue(BookRow.COLUMN_APPARITION_DATE,jTextAparitionDate.getText());
		bkID.setStringValue(BookRow.COLUMN_PRICE,jTextPrice.getText());
		bkID.setValue(BookRow.COLUMN_CURRENCY,jTextCurrency.getText());
		//bkID.setValue(Book.COLUMN_LOAN_PERS,
		//bkID.setValue(Book.COLUMN_LOAN_DATE,
		//bkID.setValue(Book.COLUMN_FILE,
		bkID.setStringValue(BookRow.COLUMN_ISBN,jTextISBN.getText());
		bkID.setValue(BookRow.COLUMN_TOREAD,jcbToRead.isSelected());
		//bkID.setValue(Book.COLUMN_REL_PATH,
		
		JOptionPane.showMessageDialog(null, "Save succesfull");
	}
	private void jButtonAddMouseReleased(MouseEvent evt) {
		try {
			if(bkID == null){
			addBook();
			}else{
				saveBook();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void jButtonCancelMouseReleased(MouseEvent evt) {
		CustomEvent.dispatchEvent(this, BooksManagement.CE_BOOKM_TYPE, BooksManagement.CE_BOOKM_VALUE_SHOW_BOOKS);
	}
}
