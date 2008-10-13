package ro.cuzma.books.ui.book;


import ro.cuzma.books.BookData;
import ro.cuzma.books.ui.book.BookTable;
import ro.cuzma.db.ValueDisplayData;
import ro.cuzma.event.CustomEvent;
import ro.cuzma.event.CustomEventDispatcher;
import ro.cuzma.event.CustomEventListener;
import ro.cuzma.ui.TableComboBox;
import ro.cuzma.ui.TableWithColumnModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

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
public class BookPanelTable extends javax.swing.JPanel implements CustomEventListener {
	
	public static final String CE_BOOK_TYPE = "BOOK";
	public static final String CE_BOOK_VALUE_EDIT = "EDIT";
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
	
	private JTextField	jTextFieldTitle;
	private JButton		jButtonApply;
	private JPanel		jPanelNorth;
	private BookTable	jTableBooks;
	private JScrollPane	jScrollPaneTable;
	private JLabel		jLabelTitle;
	private JTextField	jTextRead;
	private JComboBox jComboBoxToRead;
	private JLabel jLabelToRead;
	private JTextField jTextFieldISBN;
	private JLabel jLabelISBN;
	private JButton		jButtonEdit;
	private JTextField	jTextFieldStatus;
	private JLabel		jLabelStatus;
	private TableComboBox	jFormat;
	private JLabel		jLabelFormat;
	private TableComboBox	jLang;
	
	private JLabel		jLabelang;
	private JTextField	jTextFieldBookType;
	private JLabel		jLabelBookType;
	private JTextField	jTextFieldPub;
	private JLabel		jLabelPublisher;
	private JTextField	jTextFieldSerie;
	private JLabel		jLabelSerie;
	private JTextArea	jTextAreaDetails	= new JTextArea();	;
	private JPanel		jPanelText;
	private JLabel		jLabelRead;
	private JTextField	jTextFieldAuthor;
	private JLabel		jLabelAuthor;
	private BookData	rows;
	ValueDisplayData langs;
	ValueDisplayData types;

	private void initialize() {
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		this.setSize(new Dimension(203, 59));
	}

	public BookPanelTable(BookData rows,ValueDisplayData langs,ValueDisplayData types) {
		super();
		this.rows = rows;
		this.langs = langs;
		this.types = types;
		initialize();
		initGUI();
		jTableBooks = new BookTable(rows, jTextAreaDetails);
		//jTableBooks = new BookTable(rows, jTextAreaDetails, null);
		jScrollPaneTable.setViewportView(jTableBooks);
		// jTableBooks.setTableData(new BookData(db));
		if (((TableWithColumnModel) jTableBooks.getModel()).getEdit()) {
			jButtonEdit.setText("Unedit");
		} else {
			jButtonEdit.setText("Edit");
		}
		CustomEventDispatcher.getCustomEventDispatcher().addNewListener(this);
	}

	private void initGUI() {
		jScrollPaneTable = new JScrollPane();
		jScrollPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(jScrollPaneTable, BorderLayout.CENTER);
		// jScrollPaneTable.setPreferredSize(new java.awt.Dimension(541,
		// 308));
		jScrollPaneTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		{
			// jTableBooks = new BookTable(new
			// BookData(db),jTextAreaDetails, new BookCellRenderJLabel());
		}
		{
			jPanelText = new JPanel();
			GridLayout jPanelTextLayout = new GridLayout(1, 1);
			jPanelTextLayout.setColumns(1);
			jPanelTextLayout.setHgap(5);
			jPanelTextLayout.setVgap(5);
			jPanelText.setLayout(jPanelTextLayout);
			this.add(jPanelText, BorderLayout.SOUTH);
			jPanelText.setMinimumSize(new java.awt.Dimension(10, 50));
			{
				// jTextAreaDetails = new JTextArea();
				jPanelText.add(jTextAreaDetails);
				jTextAreaDetails.setFocusCycleRoot(true);
				jTextAreaDetails.setMinimumSize(new java.awt.Dimension(30, 16));
				jTextAreaDetails.setPreferredSize(new java.awt.Dimension(643, 59));
				jTextAreaDetails.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			}
		}
		{
			jPanelNorth = new JPanel();
			this.add(jPanelNorth, BorderLayout.NORTH);
			GridBagLayout jPanelNorthLayout = new GridBagLayout();
			jPanelNorthLayout.rowWeights = new double[] { 0.1, 0.1 };
			jPanelNorthLayout.rowHeights = new int[] { 7, 7 };
			jPanelNorthLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1};
			jPanelNorthLayout.columnWidths = new int[] {82, 41, 108, 49, 108, 43, 80, 63, 111, 48, 107, 45, 50, 7};
			jPanelNorth.setLayout(jPanelNorthLayout);
			jPanelNorth.setPreferredSize(new java.awt.Dimension(643, 47));
			{
				jButtonApply = new JButton();
				jPanelNorth.add(jButtonApply, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
				jButtonApply.setText("Apply");
				// jButtonApply.setHorizontalAlignment(SwingConstants.LEFT);
				jButtonApply.setSize(80, 23);
				jButtonApply.setPreferredSize(new java.awt.Dimension(80, 23));
				jButtonApply.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent evt) {
						jButtonApplyMouseReleased(evt);
					}
				});
			}
			{
				jTextFieldTitle = new JTextField();
				jPanelNorth.add(jTextFieldTitle, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldTitle.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldTitle.setSize(200, 20);
				jTextFieldTitle.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelTitle = new JLabel();
				jPanelNorth.add(jLabelTitle, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelTitle.setText("Title");
				jLabelTitle.setInheritsPopupMenu(false);
			}
			{
				jLabelAuthor = new JLabel();
				jPanelNorth.add(jLabelAuthor, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelAuthor.setText("Author");
			}
			{
				jTextFieldAuthor = new JTextField();
				jPanelNorth.add(jTextFieldAuthor, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldAuthor.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldAuthor.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelRead = new JLabel();
				jPanelNorth.add(jLabelRead, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelRead.setText("Read");
			}
			{
				jTextRead = new JTextField();
				jPanelNorth.add(jTextRead, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextRead.setPreferredSize(new java.awt.Dimension(50, 20));
				jTextRead.setDragEnabled(true);
				jTextRead.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelSerie = new JLabel();
				jPanelNorth.add(jLabelSerie, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelSerie.setText("Serie");
			}
			{
				jTextFieldSerie = new JTextField();
				jPanelNorth.add(jTextFieldSerie, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldSerie.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldSerie.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelPublisher = new JLabel();
				jPanelNorth.add(jLabelPublisher, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelPublisher.setText("Publisher");
			}
			{
				jTextFieldPub = new JTextField();
				jPanelNorth.add(jTextFieldPub, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldPub.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldPub.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelBookType = new JLabel();
				jPanelNorth.add(jLabelBookType, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelBookType.setText("Book Type");
			}
			{
				jTextFieldBookType = new JTextField();
				jPanelNorth.add(jTextFieldBookType, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldBookType.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldBookType.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelang = new JLabel();
				jPanelNorth.add(jLabelang, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelang.setText("Lang");
			}
			{
				jLang = new TableComboBox(this.langs,true);
				jPanelNorth.add(jLang, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLang.setPreferredSize(new java.awt.Dimension(80, 20));
				jLang.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						jLangItemStateChanged(evt);
					}
				});
			}
			{
				jLabelFormat = new JLabel();
				jPanelNorth.add(jLabelFormat, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelFormat.setText("Format");
			}
			{
				jFormat = new TableComboBox(types,true);
				jPanelNorth.add(jFormat, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jFormat.setDoubleBuffered(true);
				jFormat.setPreferredSize(new java.awt.Dimension(100, 20));
				jFormat.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						jLangItemStateChanged(evt);
					}
				});
				
			}
			{
				jLabelStatus = new JLabel();
				jPanelNorth.add(jLabelStatus, new GridBagConstraints(9, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelStatus.setText("Status");
			}
			{
				jTextFieldStatus = new JTextField();
				jPanelNorth.add(jTextFieldStatus, new GridBagConstraints(10, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldStatus.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldStatus.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jButtonEdit = new JButton();
				jPanelNorth.add(jButtonEdit, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jButtonEdit.setText("Edit");
				jButtonEdit.setFocusCycleRoot(true);
				jButtonEdit.setPreferredSize(new java.awt.Dimension(80, 23));
				jButtonEdit.setSize(80, 23);
				jButtonEdit.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent evt) {
						jButtonEditMouseReleased(evt);
					}
				});
			}
			{
				jLabelISBN = new JLabel();
				jPanelNorth.add(jLabelISBN, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelISBN.setText("ISBN");
			}
			{
				jTextFieldISBN = new JTextField();
				jPanelNorth.add(jTextFieldISBN, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jTextFieldISBN.setPreferredSize(new java.awt.Dimension(100, 20));
				jTextFieldISBN.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent evt) {
						jKeyReleased(evt);
					}
				});
			}
			{
				jLabelToRead = new JLabel();
				jPanelNorth.add(jLabelToRead, new GridBagConstraints(11, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabelToRead.setText("To Read?");
			}
			{
				ComboBoxModel jComboBoxToReadModel = new DefaultComboBoxModel(new String[] { "", "true","false" });
				jComboBoxToRead = new JComboBox();
				jPanelNorth.add(jComboBoxToRead, new GridBagConstraints(12, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jComboBoxToRead.setModel(jComboBoxToReadModel);
				jComboBoxToRead.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						jComboBoxToReadItemStateChanged(evt);
					}
				});
			}
		}
		this.setFocusCycleRoot(false);
		this.setPreferredSize(new java.awt.Dimension(967, 498));
	}

	public void showTable() {
		this.jTableBooks.setNewData(this.jTableBooks.reloadData());
		this.jTableBooks.revalidate();
		this.jTableBooks.repaint();
		// this.jScrollPaneTable.revalidate();
		// this.jScrollPaneTable.repaint();
	}

	private void filterData() {
		/*
		 * jTextFieldPublisher; jTetFieldStatus; jTextFieldFormat;
		 * jTextFieldLang; jTextFieldBookType; jTextFieldPub; jTextFieldSerie;
		 * jLabelSerie;
		 */
		// db.getBooks()
		Boolean toRead = null;
		String toReadS = jComboBoxToRead.getSelectedItem().toString();
		if (toReadS.equals("true")){
			toRead = new Boolean(true);
		}
		if (toReadS.equals("false")){
			toRead = new Boolean(false);
		}
		rows.setFilter(jTextFieldTitle.getText(), null, jTextFieldAuthor.getText(), jTextRead.getText(), jTextFieldSerie.getText(), jTextFieldPub.getText(), jTextFieldStatus.getText(),
						jFormat.getSelectedID(), jLang.getSelectedID(), jTextFieldBookType.getText(),jTextFieldISBN.getText(),toRead);
		this.jTableBooks.setNewData(rows.getSortedFilteredData());
		this.jTableBooks.revalidate();
		this.jTableBooks.repaint();
	}

	private void jButtonApplyMouseReleased(MouseEvent evt) {
		filterData();
	}

	private void jKeyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			filterData();
		}
	}
	public void setUnsetEdit() {
		if (((TableWithColumnModel) jTableBooks.getModel()).setUnsetEdit()) {
			jButtonEdit.setText("Unedit");
		} else {
			jButtonEdit.setText("Edit");
		}
		// this.jTableBooks.getModel().repaint();
		// jTableBooks.setModel(jTableBooks.getModel());
		jTableBooks.revalidate();
		jTableBooks.repaint();
		this.jScrollPaneTable.revalidate();
		this.jScrollPaneTable.repaint();
	}

	private void jButtonEditMouseReleased(MouseEvent evt) {
		setUnsetEdit();
	}

	/**
	 * @return the rows
	 * @uml.property name="rows"
	 */
	public BookData getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 * @uml.property name="rows"
	 */
	public void setRows(BookData rows) {
		this.rows = rows;
		// ((BookData)jTableBooks.getTableData()).setDb(db);
	}

	public BookTable getTableBooks() {
		return jTableBooks;
	}

	public void handleCustomEvent(CustomEvent ce) {
		if (ce.getEventType().equals(BookPanelTable.CE_BOOK_TYPE)){
			if (ce.getEventValue().equals(BookPanelTable.CE_BOOK_VALUE_EDIT)){
				if (((TableWithColumnModel) jTableBooks.getModel()).getEdit()){
					jButtonEdit.setText("Unedit");
				} else {
					jButtonEdit.setText("Edit");
				}
			}
		}
	}
	
	private void jComboBoxToReadItemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == 1){
			filterData();
		}
		//TODO add your code for jComboBoxToRead.itemStateChanged
	}
	
	private void jLangItemStateChanged(ItemEvent evt) {
		filterData();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
