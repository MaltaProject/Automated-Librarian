package AutomatedLibrarian;
import java.awt.CardLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.cloudgarden.layout.AnchorLayout;
import com.google.gdata.client.books.BooksService;
import com.google.gdata.client.books.VolumeQuery;
import com.google.gdata.data.Category;
import com.google.gdata.data.books.BooksCategory;
import com.google.gdata.data.books.VolumeEntry;
import com.google.gdata.data.books.VolumeFeed;
import com.google.gdata.data.dublincore.Creator;
import com.google.gdata.data.dublincore.Title;
import com.google.gdata.util.ServiceException;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
//TODO Release:
	checkout
	checkin
	list all books that are out
	add password for adding books/google adding books

//TODO Future: (In no particular order)
	Bar scanner codes
	remove books
	remove librarian (can be done now by removing librarian user file
	list librarians
	library fines
	list all fines
	Image of book on view page (This would be separfrom the current view page, instead of 7 results, there would be 6 buttons, each with a picture of the cover image)
	Pages of search results...
**/

public class frontEnd extends javax.swing.JFrame {

	static boolean masterCheckComplete = false;				//Is true when master user has entered correct password.
	static String passphrase = "dmcslibrary";				// WILL BE REPLACED!! INSECURE!! Due to time constraints for releasing the application, I encrypt each book file with this pass phrase, this will soon be replaced by a passphrase specified by the master user and stored in the master file. It will be retrieved when a new librarian is created and then stored in their file.
	boolean searchWithGoogle = false;
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//All of these used to be public - I switched them to see if I can access them from a different class - if something breaks, change back!

	public JMenuItem helpMenuItem;
	public JMenu jMenu5;
	public JLabel advancedSearch;
	public JButton isbnSearch;
	public JButton genreSearch;
	public JButton authorSearch;
	public JLabel searchID;
	public JButton search;
	public JTextField usrInpt;
	public JPanel jPanel1;
	public JMenuItem deleteMenuItem;
	private JButton jButton1;
	private JLabel jLabel3;
	private JLabel jLabel1;
	private JPasswordField jPasswordField1;
	private JButton jButton2;
	private JPanel masterCheck;
	private JButton submit;
	private JPasswordField libPass;
	private JLabel passWord;
	private JTextField newBookCall;
	private JLabel callNum;
	private JLabel googleHeader;
	private JLabel notesHeader;
	private JPanel qrReader;
	private JButton libPrefButton;
	private JButton editBookDataButton;
	private JButton changePatronDataButton;
	private JButton removeBooksButton;
	private JButton checkinMultipleButton;
	private JButton checkoutmultipleButton;
	private JButton aquisitionListButton;
	private JButton overdueListButton;
	private JButton checkedoutListButton;
	private JButton removeLibrarianButton;
	private JScrollPane jScrollPane4;
	private JLabel checkedoutbookTitle;
	private JTable list;
	private JPanel checkedoutBooks;
	private JButton cancelAddPatron;
	private JButton addNewPatron;
	private JTextField emailField;
	private JLabel email;
	private JTextField phoneNumberField;
	private JLabel phoneNumber;
	private JScrollPane jScrollPane3;
	private JList dayField;
	private JScrollPane jScrollPane2;
	private JList monthField;
	private JButton removePatronButton;
	private JButton addPatronButton;
	private JScrollPane jScrollPane1;
	private JList yearField;
	private JLabel day;
	private JLabel month;
	private JLabel year;
	private JLabel dateofbirth;
	private JTextField cityField;
	private JLabel city;
	private JTextField postalCodeField;
	private JLabel postalCode;
	private JTextField addressField2;
	private JTextField addressField;
	private JLabel address;
	private JTextField surnameField;
	private JLabel surname;
	private JTextField givenNameField;
	private JLabel givenName;
	private JLabel addPatronTitle;
	private JPanel addPatron;
	private JTextArea notesLabel;
	private JLabel notesTitle;
	private JLabel callnumLabel;
	private JLabel callnumTitle;
	private JTextArea newBookNotes;
	private JScrollPane scrollPanel;
	private JButton searchGoogle;
	private JLabel isbnHeader;
	private JTextField isbnInput;
	private JPanel addBookWithGoogle;
	private JButton googleBook;
	private JButton BacktoLibCTRL;
	private JButton submitBook;
	private JTextField addBookGenre;
	private JLabel newBookGenreHeader;
	private JTextField newBookIsbn;
	private JLabel addBookIsbnHeader;
	private JTextField newBookAuthor;
	private JLabel addBookAuthorHeader;
	private JTextField newBookTitle;
	private JLabel titleheader;
	private JLabel addBookHeader;
	private JPanel addNewBook;
	private JButton addBook;
	private JButton backToControl;
	private JLabel libCreated;
	private JLabel jLabel4;
	private JLabel message;
	private JButton backToLib;
	private JLabel jLabel2;
	private JPasswordField newMasterPassword;
	private JButton jButton6;
	private JButton jButton5;
	private JLabel incorrect;
	private JButton jButton4;
	private JLabel passwordTitle;
	private JLabel info;
	private JLabel changeMasterTitle;
	private JPanel changeMaster;
	private JButton back;
	private JButton jButton3;
	private JLabel libCtrlHeader;
	private JPanel libControl;
	private JTextField libUser;
	private JLabel userName;
	private JLabel addLibTitle;
	private JPanel addLib;
	private JLabel bookStatusActual;
	private JLabel bookStatus;
	private JButton checkIn;
	private JButton checkStatus;
	private JButton takeOut;
	private JLabel bookGenre;
	private JLabel bookIsbn;
	private JLabel isbnLabel;
	private JLabel genreLabel;
	private JLabel bookAuthor;
	private JLabel authorLabel;
	private JLabel bookTitle;
	private JLabel titleLabel;
	private JToggleButton jToggleButton1;
	private JPanel viewBook;
	private JLabel isbnBook3;
	public JSeparator jSeparator1;
	public JMenuItem pasteMenuItem;
	public JMenuItem copyMenuItem;
	public JMenuItem cutMenuItem;
	public JMenu jMenu4;
	public JMenuItem closeFileMenuItem;
	public JMenuItem saveAsMenuItem;
	public JLabel searchResultsLabel;
	public JLabel titleBook4;
	public JLabel genreBook3;
	public JLabel authorBook3;
	public JLabel titleBook3;
	public JLabel genreBook2;
	public JLabel authorBook2;
	public JLabel isbnBook2;
	public JLabel titleBook2;
	public JLabel genreBook1;
	public JLabel authorBook1;
	public JLabel isbnBook1;
	public JLabel titleBook1;
	public JButton viewBook7;
	public JButton viewBook6;
	public JButton viewBook5;
	public JButton viewBook4;
	public JButton viewBook2;
	public JButton viewBook3;
	public JButton backToSearch;
	public JPanel SearchResults;
	public JButton titleSearch;
	private JButton viewBook1;
	public JLabel authorBook7;
	public JLabel genreBook7;
	public JLabel genreBook6;
	public JLabel authorBook6;
	public JLabel genreBook5;
	public JLabel authorBook5;
	public JLabel genreBook4;
	public JLabel authorBook4;
	public JLabel isbnBook7;
	public JLabel isbnBook6;
	public JLabel isbnBook5;
	public JLabel isbnBook4;
	public JLabel titleBook7;
	public JLabel titleBook6;
	public JLabel titleBook5;
	public JMenu jMenu3;
	public JMenuBar jMenuBar1;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frontEnd inst = new frontEnd();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public frontEnd() {
		super();
		boolean runCount = true;


		if (runCount == true) {
			runCount = false;
			initGUI();
		}

	}

	public void initGUI() {
		try {
			{
				CardLayout thisLayout = new CardLayout();
				getContentPane().setLayout(thisLayout);
				this.setResizable(false);
				this.setTitle("Automated Library System");
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, "Center");
					GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(739, 516));
					{
						usrInpt = new JTextField();
						AnchorLayout usrInptLayout = new AnchorLayout();
						usrInpt.setLayout(usrInptLayout);
						usrInpt.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent evt) {
								System.out.println("usrInpt.keyTyped, event="+evt);
							}
						});
					}		
					{
						search = new JButton();
						search.setText("Search All");
						search.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("search.mouseClicked, event="+evt);

								String searchType = "all";
								jPanel1.setVisible(false);
								SearchResults.setVisible(true);

								frontEnd frontEnd = new frontEnd();
								frontEnd.begin(usrInpt.getText(), searchType);

								if (frontEnd.noresults == true){
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(true);
									frontEnd.noresults = false;
								} else {
									//Sets result labels
									titleBook1.setText(frontEnd.title1);
									titleBook2.setText(frontEnd.title2);
									titleBook3.setText(frontEnd.title3);
									titleBook4.setText(frontEnd.title4);
									titleBook5.setText(frontEnd.title5);
									titleBook6.setText(frontEnd.title6);
									titleBook7.setText(frontEnd.title7);
									authorBook1.setText(frontEnd.author1);
									authorBook2.setText(frontEnd.author2);
									authorBook3.setText(frontEnd.author3);
									authorBook4.setText(frontEnd.author4);
									authorBook5.setText(frontEnd.author5);
									authorBook6.setText(frontEnd.author6);
									authorBook7.setText(frontEnd.author7);
									genreBook1.setText(frontEnd.genre1);
									genreBook2.setText(frontEnd.genre2);
									genreBook3.setText(frontEnd.genre3);
									genreBook4.setText(frontEnd.genre4);
									genreBook5.setText(frontEnd.genre5);
									genreBook6.setText(frontEnd.genre6);
									genreBook7.setText(frontEnd.genre7);
									isbnBook1.setText(frontEnd.isbn1);
									isbnBook2.setText(frontEnd.isbn2);
									isbnBook3.setText(frontEnd.isbn3);
									isbnBook4.setText(frontEnd.isbn4);
									isbnBook5.setText(frontEnd.isbn5);
									isbnBook6.setText(frontEnd.isbn6);
									isbnBook7.setText(frontEnd.isbn7);

									//resets hideresults
									viewBook1.setVisible(true);
									viewBook2.setVisible(true);
									viewBook3.setVisible(true);
									viewBook4.setVisible(true);
									viewBook5.setVisible(true);
									viewBook6.setVisible(true);
									viewBook7.setVisible(true);

									//Hide Blank Results
									if (hideResult1 == true){
										viewBook1.setVisible(false);
									} if (hideResult2 == true){
										viewBook2.setVisible(false);
									} if (hideResult3 == true){
										viewBook3.setVisible(false);
									} if (hideResult4 == true){
										viewBook4.setVisible(false);
									}  if (hideResult5 == true){
										viewBook5.setVisible(false);
									} if (hideResult6 == true){
										viewBook6.setVisible(false);
									} if (hideResult7 == true){
										viewBook7.setVisible(false);
									}


								}
							}
						});
					}
					{
						genreSearch = new JButton();
						genreSearch.setText("Genre");
						genreSearch.setSize(140, 30);
						genreSearch.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("genreSearch.mouseClicked, event="+evt);

								String searchType = "genre";

								jPanel1.setVisible(false);
								SearchResults.setVisible(true);

								frontEnd frontEnd = new frontEnd();
								frontEnd.begin(usrInpt.getText(), searchType);

								if (frontEnd.noresults == true){
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(true);
									frontEnd.noresults = false;
								} else {
									//Sets result labels
									titleBook1.setText(frontEnd.title1);
									titleBook2.setText(frontEnd.title2);
									titleBook3.setText(frontEnd.title3);
									titleBook4.setText(frontEnd.title4);
									titleBook5.setText(frontEnd.title5);
									titleBook6.setText(frontEnd.title6);
									titleBook7.setText(frontEnd.title7);
									authorBook1.setText(frontEnd.author1);
									authorBook2.setText(frontEnd.author2);
									authorBook3.setText(frontEnd.author3);
									authorBook4.setText(frontEnd.author4);
									authorBook5.setText(frontEnd.author5);
									authorBook6.setText(frontEnd.author6);
									authorBook7.setText(frontEnd.author7);
									genreBook1.setText(frontEnd.genre1);
									genreBook2.setText(frontEnd.genre2);
									genreBook3.setText(frontEnd.genre3);
									genreBook4.setText(frontEnd.genre4);
									genreBook5.setText(frontEnd.genre5);
									genreBook6.setText(frontEnd.genre6);
									genreBook7.setText(frontEnd.genre7);
									isbnBook1.setText(frontEnd.isbn1);
									isbnBook2.setText(frontEnd.isbn2);
									isbnBook3.setText(frontEnd.isbn3);
									isbnBook4.setText(frontEnd.isbn4);
									isbnBook5.setText(frontEnd.isbn5);
									isbnBook6.setText(frontEnd.isbn6);
									isbnBook7.setText(frontEnd.isbn7);

									//resets hideresults
									viewBook1.setVisible(true);
									viewBook2.setVisible(true);
									viewBook3.setVisible(true);
									viewBook4.setVisible(true);
									viewBook5.setVisible(true);
									viewBook6.setVisible(true);
									viewBook7.setVisible(true);

									//Hide Blank Results
									if (hideResult1 == true){
										viewBook1.setVisible(false);
									} if (hideResult2 == true){
										viewBook2.setVisible(false);
									} if (hideResult3 == true){
										viewBook3.setVisible(false);
									} if (hideResult4 == true){
										viewBook4.setVisible(false);
									} if (hideResult5 == true){
										viewBook5.setVisible(false);
									} if (hideResult6 == true){
										viewBook6.setVisible(false);
									} if (hideResult7 == true){
										viewBook7.setVisible(false);
									}
								}
							}
						});
					}
					{
						isbnSearch = new JButton();
						isbnSearch.setText("ISBN");
						isbnSearch.setSize(145, 30);
						isbnSearch.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("isbnSearch.mouseClicked, event="+evt);

								String searchType = "isbn";

								jPanel1.setVisible(false);
								SearchResults.setVisible(true);

								frontEnd frontEnd = new frontEnd();
								frontEnd.begin(usrInpt.getText(), searchType);

								if (frontEnd.noresults == true){
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(true);
									frontEnd.noresults = false;
								} else {
									//Sets result labels
									titleBook1.setText(frontEnd.title1);
									titleBook2.setText(frontEnd.title2);
									titleBook3.setText(frontEnd.title3);
									titleBook4.setText(frontEnd.title4);
									titleBook5.setText(frontEnd.title5);
									titleBook6.setText(frontEnd.title6);
									titleBook7.setText(frontEnd.title7);
									authorBook1.setText(frontEnd.author1);
									authorBook2.setText(frontEnd.author2);
									authorBook3.setText(frontEnd.author3);
									authorBook4.setText(frontEnd.author4);
									authorBook5.setText(frontEnd.author5);
									authorBook6.setText(frontEnd.author6);
									authorBook7.setText(frontEnd.author7);
									genreBook1.setText(frontEnd.genre1);
									genreBook2.setText(frontEnd.genre2);
									genreBook3.setText(frontEnd.genre3);
									genreBook4.setText(frontEnd.genre4);
									genreBook5.setText(frontEnd.genre5);
									genreBook6.setText(frontEnd.genre6);
									genreBook7.setText(frontEnd.genre7);
									isbnBook1.setText(frontEnd.isbn1);
									isbnBook2.setText(frontEnd.isbn2);
									isbnBook3.setText(frontEnd.isbn3);
									isbnBook4.setText(frontEnd.isbn4);
									isbnBook5.setText(frontEnd.isbn5);
									isbnBook6.setText(frontEnd.isbn6);
									isbnBook7.setText(frontEnd.isbn7);

									//resets hideresults
									viewBook1.setVisible(true);
									viewBook2.setVisible(true);
									viewBook3.setVisible(true);
									viewBook4.setVisible(true);
									viewBook5.setVisible(true);
									viewBook6.setVisible(true);
									viewBook7.setVisible(true);

									//Hide Blank Results
									if (hideResult1 == true){
										viewBook1.setVisible(false);
									} if (hideResult2 == true){
										viewBook2.setVisible(false);
									} if (hideResult3 == true){
										viewBook3.setVisible(false);
									} if (hideResult4 == true){
										viewBook4.setVisible(false);
									} if (hideResult5 == true){
										viewBook5.setVisible(false);
									} if (hideResult6 == true){
										viewBook6.setVisible(false);
									} if (hideResult7 == true){
										viewBook7.setVisible(false);
									}
								}
							}
						});
					}
					{
						titleSearch = new JButton();
						titleSearch.setText("Title");
						titleSearch.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("titleSearch.mouseClicked, event="+evt);

								String searchType = "title";

								jPanel1.setVisible(false);
								SearchResults.setVisible(true);

								frontEnd frontEnd = new frontEnd();
								frontEnd.begin(usrInpt.getText(), searchType);

								if (frontEnd.noresults == true){
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(true);
									frontEnd.noresults = false;
								} else {
									//Sets result labels
									titleBook1.setText(frontEnd.title1);
									titleBook2.setText(frontEnd.title2);
									titleBook3.setText(frontEnd.title3);
									titleBook4.setText(frontEnd.title4);
									titleBook5.setText(frontEnd.title5);
									titleBook6.setText(frontEnd.title6);
									titleBook7.setText(frontEnd.title7);
									authorBook1.setText(frontEnd.author1);
									authorBook2.setText(frontEnd.author2);
									authorBook3.setText(frontEnd.author3);
									authorBook4.setText(frontEnd.author4);
									authorBook5.setText(frontEnd.author5);
									authorBook6.setText(frontEnd.author6);
									authorBook7.setText(frontEnd.author7);
									genreBook1.setText(frontEnd.genre1);
									genreBook2.setText(frontEnd.genre2);
									genreBook3.setText(frontEnd.genre3);
									genreBook4.setText(frontEnd.genre4);
									genreBook5.setText(frontEnd.genre5);
									genreBook6.setText(frontEnd.genre6);
									genreBook7.setText(frontEnd.genre7);
									isbnBook1.setText(frontEnd.isbn1);
									isbnBook2.setText(frontEnd.isbn2);
									isbnBook3.setText(frontEnd.isbn3);
									isbnBook4.setText(frontEnd.isbn4);
									isbnBook5.setText(frontEnd.isbn5);
									isbnBook6.setText(frontEnd.isbn6);
									isbnBook7.setText(frontEnd.isbn7);

									//resets hideresults
									viewBook1.setVisible(true);
									viewBook2.setVisible(true);
									viewBook3.setVisible(true);
									viewBook4.setVisible(true);
									viewBook5.setVisible(true);
									viewBook6.setVisible(true);
									viewBook7.setVisible(true);

									//Hide Blank Results
									if (hideResult1 == true){
										viewBook1.setVisible(false);
									} if (hideResult2 == true){
										viewBook2.setVisible(false);
									} if (hideResult3 == true){
										viewBook3.setVisible(false);
									} if (hideResult4 == true){
										viewBook4.setVisible(false);
									} if (hideResult5 == true){
										viewBook5.setVisible(false);
									} if (hideResult6 == true){
										viewBook6.setVisible(false);
									} if (hideResult7 == true){
										viewBook7.setVisible(false);
									}
								}


							}
						});
					}
					{
						advancedSearch = new JLabel();
						advancedSearch.setText("Advanced Search: Search by...");
					}
					{
						authorSearch = new JButton();
						authorSearch.setText("Author");
						authorSearch.setSize(100, 30);
						authorSearch.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("authorSearch.mouseClicked, event="+evt);

								String searchType = "author";

								jPanel1.setVisible(false);
								SearchResults.setVisible(true);

								frontEnd frontEnd = new frontEnd();
								frontEnd.begin(usrInpt.getText(), searchType);

								if (frontEnd.noresults == true){
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(true);
									frontEnd.noresults = false;
								} else {
									//Sets result labels
									titleBook1.setText(frontEnd.title1);
									titleBook2.setText(frontEnd.title2);
									titleBook3.setText(frontEnd.title3);
									titleBook4.setText(frontEnd.title4);
									titleBook5.setText(frontEnd.title5);
									titleBook6.setText(frontEnd.title6);
									titleBook7.setText(frontEnd.title7);
									authorBook1.setText(frontEnd.author1);
									authorBook2.setText(frontEnd.author2);
									authorBook3.setText(frontEnd.author3);
									authorBook4.setText(frontEnd.author4);
									authorBook5.setText(frontEnd.author5);
									authorBook6.setText(frontEnd.author6);
									authorBook7.setText(frontEnd.author7);
									genreBook1.setText(frontEnd.genre1);
									genreBook2.setText(frontEnd.genre2);
									genreBook3.setText(frontEnd.genre3);
									genreBook4.setText(frontEnd.genre4);
									genreBook5.setText(frontEnd.genre5);
									genreBook6.setText(frontEnd.genre6);
									genreBook7.setText(frontEnd.genre7);
									isbnBook1.setText(frontEnd.isbn1);
									isbnBook2.setText(frontEnd.isbn2);
									isbnBook3.setText(frontEnd.isbn3);
									isbnBook4.setText(frontEnd.isbn4);
									isbnBook5.setText(frontEnd.isbn5);
									isbnBook6.setText(frontEnd.isbn6);
									isbnBook7.setText(frontEnd.isbn7);



									//Hide Blank Results
									if (hideResult1 == true){
										viewBook1.setVisible(false);
									} if (hideResult2 == true){
										viewBook2.setVisible(false);
									} if (hideResult3 == true){
										viewBook3.setVisible(false);
									} if (hideResult4 == true){
										viewBook4.setVisible(false);
									} if (hideResult5 == true){
										viewBook5.setVisible(false);
									}  if (hideResult6 == true){
										viewBook6.setVisible(false);
									}  if (hideResult7 == true){
										viewBook7.setVisible(false);
									}
								}
							}
						});
					}
					{
						searchID = new JLabel();
						searchID.setText("Search The DMCS Library");
					}
					jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
							.addContainerGap(139, 139)
							.addComponent(searchID, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(search, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(usrInpt, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(advancedSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(authorSearch, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(genreSearch, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(titleSearch, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(isbnSearch, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
											.addContainerGap(201, 201));
					jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
							.addGap(104, 104, 104)
							.addGroup(jPanel1Layout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
											.addGroup(jPanel1Layout.createParallelGroup()
													.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
															.addGroup(jPanel1Layout.createParallelGroup()
																	.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
																			.addComponent(authorSearch, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
																			.addComponent(genreSearch, 0, 128, Short.MAX_VALUE))
																			.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
																					.addComponent(advancedSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																					.addGap(0, 52, Short.MAX_VALUE)))
																					.addComponent(titleSearch, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
																					.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
																							.addComponent(searchID, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
																							.addGap(0, 92, Short.MAX_VALUE)))
																							.addComponent(isbnSearch, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
																							.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
																									.addComponent(usrInpt, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
																									.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																									.addGap(0, 7, Short.MAX_VALUE)))
																									.addContainerGap(112, 112));
				}
				{
					noResultsFound = new JPanel();
					GroupLayout noResultsFoundLayout = new GroupLayout((JComponent)noResultsFound);
					noResultsFound.setLayout(noResultsFoundLayout);
					getContentPane().add(noResultsFound, "jPanel2");
					{
						noResultsMessage = new JLabel();
						noResultsMessage.setText("No Results Found!");
					}
					{
						noResultsMessage2 = new JLabel();
						noResultsMessage2.setText("Why Don't you try your search again with different keywords?");
					}
					{
						searchAgain = new JButton();
						searchAgain.setText("Search");
						searchAgain.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("searchAgain.mouseClicked, event="+evt);
								jPanel1.setVisible(true);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
							}
						});
					}
					noResultsFoundLayout.setHorizontalGroup(noResultsFoundLayout.createSequentialGroup()
							.addContainerGap(141, 141)
							.addGroup(noResultsFoundLayout.createParallelGroup()
									.addGroup(noResultsFoundLayout.createSequentialGroup()
											.addComponent(noResultsMessage2, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
											.addGap(0, 0, Short.MAX_VALUE))
											.addGroup(noResultsFoundLayout.createSequentialGroup()
													.addGap(153)
													.addGroup(noResultsFoundLayout.createParallelGroup()
															.addGroup(noResultsFoundLayout.createSequentialGroup()
																	.addComponent(noResultsMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addGap(0, 0, Short.MAX_VALUE))
																	.addGroup(GroupLayout.Alignment.LEADING, noResultsFoundLayout.createSequentialGroup()
																			.addGap(27)
																			.addComponent(searchAgain, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
																			.addGap(0, 27, Short.MAX_VALUE)))
																			.addGap(167)))
																			.addContainerGap(128, 128));
					noResultsFoundLayout.setVerticalGroup(noResultsFoundLayout.createSequentialGroup()
							.addContainerGap(167, 167)
							.addComponent(noResultsMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(noResultsMessage2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(searchAgain, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(221, Short.MAX_VALUE));
				}
				{
					SearchResults = new JPanel();
					getContentPane().add(SearchResults, "jPanel2");
					GroupLayout SearchResultsLayout = new GroupLayout((JComponent)SearchResults);
					SearchResults.setLayout(SearchResultsLayout);
					SearchResults.setPreferredSize(new java.awt.Dimension(561, 273));
					{
						searchResultsLabel = new JLabel();
						searchResultsLabel.setText("Search Results:");
					}
					{
						isbnBook3 = new JLabel();
						isbnBook3.setText("Isbn");
					}
					{
						viewBook1 = new JButton();
						viewBook1.setText("View");
						viewBook1.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook1.mouseClicked, event="+evt);
								
								if (searchWithGoogle==true){
				
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook1.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook1.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(true);
									
									String titleTEMP = title1.replace("Title: ","");
									String authorTEMP = author1.replace("Author: ","");
									String isbnTEMP = isbn1.replace("ISBN: ","");
									String genreTEMP = genre1.replace("Genre: ","");
									
									security security = new security();
									try {
										security.reader(bookID1, 8);
									} catch (IOException e) {
										e.printStackTrace();
									}
									System.out.println("Patron Status:" + security.lineString);
									security.lineString = security.lineString.replace("<patron>", "");
									security.lineString = security.lineString.replace("</patron>", "");
									if (security.lineString.equals("Available")){
										bookStatusActual.setText("This book is currently Available");
									} else {
										bookStatusActual.setText("This book is currently Unavailable");
									}
									
									try {
										security.reader(bookID1, 8);
									} catch (IOException e) {
										e.printStackTrace();
									}
									security.lineString = security.lineString.replace("<callnum>","");
									security.lineString = security.lineString.replace("</callnum>", "");
									callnumLabel.setText(security.lineString);
									try {
										security.reader(bookID1, 9);
									} catch (IOException e) {
										e.printStackTrace();
									}
									security.lineString = security.lineString.replace("<notes>","");
									security.lineString = security.lineString.replace("</notes>", "");
									notesLabel.setText(security.lineString);
									
									bookTitle.setText(titleTEMP);
									bookAuthor.setText(authorTEMP);
									bookIsbn.setText(isbnTEMP);
									bookGenre.setText(genreTEMP);
									
								}
								
							}
						});
					}
					{
						backToSearch = new JButton();
						backToSearch.setText("Search Again");
						backToSearch.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("backToSearch.mouseClicked, event="+evt);
								jPanel1.setVisible(true);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(false);
							}
						});
					}
					{
						viewBook3 = new JButton();
						viewBook3.setText("View");
						viewBook3.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook3.mouseClicked, event="+evt);
								if (searchWithGoogle==true){
									
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook3.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook3.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
								jPanel1.setVisible(false);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(true);
								
								String titleTEMP = title1.replace("Title: ","");
								String authorTEMP = author1.replace("Author: ","");
								String isbnTEMP = isbn1.replace("ISBN: ","");
								String genreTEMP = genre1.replace("Genre: ","");
								
								security security = new security();
								try {
									security.reader(bookID3, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Patron Status:" + security.lineString);
								security.lineString = security.lineString.replace("<patron>", "");
								security.lineString = security.lineString.replace("</patron>", "");
								if (security.lineString.equals("Available")){
									bookStatusActual.setText("This book is currently Available");
								} else {
									bookStatusActual.setText("This book is currently Unavailable");
								}
								try {
									security.reader(bookID1, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<callnum>","");
								security.lineString = security.lineString.replace("</callnum>", "");
								callnumLabel.setText(security.lineString);
								try {
									security.reader(bookID1, 9);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<notes>","");
								security.lineString = security.lineString.replace("</notes>", "");
								notesLabel.setText(security.lineString);
								bookTitle.setText(titleTEMP);
								bookAuthor.setText(authorTEMP);
								bookIsbn.setText(isbnTEMP);
								bookGenre.setText(genreTEMP);
								}
							}
						});
					}
					{
						viewBook2 = new JButton();
						viewBook2.setText("View");
						viewBook2.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook2.mouseClicked, event="+evt);
								if (searchWithGoogle==true){
									
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook2.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook2.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
								jPanel1.setVisible(false);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(true);
								
								String titleTEMP = title2.replace("Title: ","");
								String authorTEMP = author2.replace("Author: ","");
								String isbnTEMP = isbn2.replace("ISBN: ","");
								String genreTEMP = genre2.replace("Genre: ","");
								
								security security = new security();
								try {
									security.reader(bookID2, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Patron Status:" + security.lineString);
								security.lineString = security.lineString.replace("<patron>", "");
								security.lineString = security.lineString.replace("</patron>", "");
								if (security.lineString.equals("Available")){
									bookStatusActual.setText("This book is currently Available");
								} else {
									bookStatusActual.setText("This book is currently Unavailable");
								}
								try {
									security.reader(bookID1, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<callnum>","");
								security.lineString = security.lineString.replace("</callnum>", "");
								callnumLabel.setText(security.lineString);
								try {
									security.reader(bookID1, 9);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<notes>","");
								security.lineString = security.lineString.replace("</notes>", "");
								notesLabel.setText(security.lineString);
								bookTitle.setText(titleTEMP);
								bookAuthor.setText(authorTEMP);
								bookIsbn.setText(isbnTEMP);
								bookGenre.setText(genreTEMP);
								}
							}
						});
					}
					{
						viewBook4 = new JButton();
						viewBook4.setText("View");
						viewBook4.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook4.mouseClicked, event="+evt);
								if (searchWithGoogle==true){
									
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook4.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook4.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
								jPanel1.setVisible(false);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(true);
								
								String titleTEMP = title1.replace("Title: ","");
								String authorTEMP = author1.replace("Author: ","");
								String isbnTEMP = isbn1.replace("ISBN: ","");
								String genreTEMP = genre1.replace("Genre: ","");
								
								security security = new security();
								try {
									security.reader(bookID4, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Patron Status:" + security.lineString);
								security.lineString = security.lineString.replace("<patron>", "");
								security.lineString = security.lineString.replace("</patron>", "");
								if (security.lineString.equals("Available")){
									bookStatusActual.setText("This book is currently Available");
								} else {
									bookStatusActual.setText("This book is currently Unavailable");
								}
								try {
									security.reader(bookID1, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<callnum>","");
								security.lineString = security.lineString.replace("</callnum>", "");
								callnumLabel.setText(security.lineString);
								try {
									security.reader(bookID1, 9);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<notes>","");
								security.lineString = security.lineString.replace("</notes>", "");
								notesLabel.setText(security.lineString);
								bookTitle.setText(titleTEMP);
								bookAuthor.setText(authorTEMP);
								bookIsbn.setText(isbnTEMP);
								bookGenre.setText(genreTEMP);
								}
							}
						});
					}
					{
						viewBook5 = new JButton();
						viewBook5.setText("View");
						viewBook5.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook5.mouseClicked, event="+evt);
								if (searchWithGoogle==true){
									
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook5.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook5.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
								jPanel1.setVisible(false);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(true);
								
								String titleTEMP = title1.replace("Title: ","");
								String authorTEMP = author1.replace("Author: ","");
								String isbnTEMP = isbn1.replace("ISBN: ","");
								String genreTEMP = genre1.replace("Genre: ","");
								
								security security = new security();
								try {
									security.reader(bookID5, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Patron Status:" + security.lineString);
								security.lineString = security.lineString.replace("<patron>", "");
								security.lineString = security.lineString.replace("</patron>", "");
								if (security.lineString.equals("Available")){
									bookStatusActual.setText("This book is currently Available");
								} else {
									bookStatusActual.setText("This book is currently Unavailable");
								}
								try {
									security.reader(bookID1, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<callnum>","");
								security.lineString = security.lineString.replace("</callnum>", "");
								callnumLabel.setText(security.lineString);
								try {
									security.reader(bookID1, 9);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<notes>","");
								security.lineString = security.lineString.replace("</notes>", "");
								notesLabel.setText(security.lineString);
								bookTitle.setText(titleTEMP);
								bookAuthor.setText(authorTEMP);
								bookIsbn.setText(isbnTEMP);
								bookGenre.setText(genreTEMP);
								}
							}
						});
					}
					{
						viewBook6 = new JButton();
						viewBook6.setText("View");
						viewBook6.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook6.mouseClicked, event="+evt);
								if (searchWithGoogle==true){
									
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook6.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook6.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
								jPanel1.setVisible(false);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(true);
								
								String titleTEMP = title1.replace("Title: ","");
								String authorTEMP = author1.replace("Author: ","");
								String isbnTEMP = isbn1.replace("ISBN: ","");
								String genreTEMP = genre1.replace("Genre: ","");
								
								security security = new security();
								try {
									security.reader(bookID6, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Patron Status:" + security.lineString);
								security.lineString = security.lineString.replace("<patron>", "");
								security.lineString = security.lineString.replace("</patron>", "");
								if (security.lineString.equals("Available")){
									bookStatusActual.setText("This book is currently Available");
								} else {
									bookStatusActual.setText("This book is currently Unavailable");
								}
								try {
									security.reader(bookID1, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<callnum>","");
								security.lineString = security.lineString.replace("</callnum>", "");
								callnumLabel.setText(security.lineString);
								try {
									security.reader(bookID1, 9);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<notes>","");
								security.lineString = security.lineString.replace("</notes>", "");
								notesLabel.setText(security.lineString);
								bookTitle.setText(titleTEMP);
								bookAuthor.setText(authorTEMP);
								bookIsbn.setText(isbnTEMP);
								bookGenre.setText(genreTEMP);
								}
							}
						});
					}
					{
						viewBook7 = new JButton();
						viewBook7.setText("View");
						viewBook7.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("viewBook7.mouseClicked, event="+evt);
								if (searchWithGoogle==true){
									
									newBookIsbn.setText(isbnInput.getText());
									String removeAuthor = authorBook7.getText();
									removeAuthor = removeAuthor.replaceAll("Author:", "");
									newBookAuthor.setText(removeAuthor);
									String removeTitle = titleBook7.getText();
									removeTitle = removeTitle.replaceAll("Title:", "");
									newBookTitle.setText(removeTitle);
									
									jPanel1.setVisible(false);
									SearchResults.setVisible(false);
									noResultsFound.setVisible(false);
									viewBook.setVisible(false);
									addLib.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);
									masterCheck.setVisible(false);
									addNewBook.setVisible(true);
									addBookWithGoogle.setVisible(false);
									searchWithGoogle=false;
								} else {
								jPanel1.setVisible(false);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(true);
								
								String titleTEMP = title1.replace("Title: ","");
								String authorTEMP = author1.replace("Author: ","");
								String isbnTEMP = isbn1.replace("ISBN: ","");
								String genreTEMP = genre1.replace("Genre: ","");
								
								security security = new security();
								try {
									security.reader(bookID7, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Patron Status:" + security.lineString);
								security.lineString = security.lineString.replace("<patron>", "");
								security.lineString = security.lineString.replace("</patron>", "");
								if (security.lineString.equals("Available")){
									bookStatusActual.setText("This book is currently Available");
								} else {
									bookStatusActual.setText("This book is currently Unavailable");
								}
								try {
									security.reader(bookID1, 8);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<callnum>","");
								security.lineString = security.lineString.replace("</callnum>", "");
								callnumLabel.setText(security.lineString);
								try {
									security.reader(bookID1, 9);
								} catch (IOException e) {
									e.printStackTrace();
								}
								security.lineString = security.lineString.replace("<notes>","");
								security.lineString = security.lineString.replace("</notes>", "");
								notesLabel.setText(security.lineString);
								bookTitle.setText(titleTEMP);
								bookAuthor.setText(authorTEMP);
								bookIsbn.setText(isbnTEMP);
								bookGenre.setText(genreTEMP);
								}
							}
						});
					}
					{
						titleBook2 = new JLabel();
						titleBook2.setText("Title");
						titleBook2.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						isbnBook2 = new JLabel();
						isbnBook2.setText("Isbn");
					}
					{
						authorBook2 = new JLabel();
						authorBook2.setText("Author");
					}
					{
						genreBook2 = new JLabel();
						genreBook2.setText("Genre");
					}
					{
						titleBook3 = new JLabel();
						titleBook3.setText("Title");
						titleBook3.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						authorBook3 = new JLabel();
						authorBook3.setText("Author");
					}
					{
						genreBook3 = new JLabel();
						genreBook3.setText("Genre");
					}
					{
						titleBook4 = new JLabel();
						titleBook4.setText("Title");
						titleBook4.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						titleBook5 = new JLabel();
						titleBook5.setText("Title");
						titleBook5.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						titleBook6 = new JLabel();
						titleBook6.setText("Title");
						titleBook6.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						titleBook7 = new JLabel();
						titleBook7.setText("Title");
						titleBook7.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						isbnBook4 = new JLabel();
						isbnBook4.setText("Isbn");
					}
					{
						isbnBook5 = new JLabel();
						isbnBook5.setText("Isbn");
					}
					{
						isbnBook6 = new JLabel();
						isbnBook6.setText("Isbn");
					}
					{
						isbnBook7 = new JLabel();
						isbnBook7.setText("Isbn");
					}
					{
						authorBook4 = new JLabel();
						authorBook4.setText("Author");
					}
					{
						genreBook4 = new JLabel();
						genreBook4.setText("Genre");
					}
					{
						authorBook5 = new JLabel();
						authorBook5.setText("Author");
					}
					{
						genreBook5 = new JLabel();
						genreBook5.setText("Genre");
					}
					{
						authorBook6 = new JLabel();
						authorBook6.setText("Author");
					}
					{
						genreBook6 = new JLabel();
						genreBook6.setText("Genre");
					}
					{
						genreBook7 = new JLabel();
						genreBook7.setText("Genre");
					}
					{
						authorBook7 = new JLabel();
						authorBook7.setText("Author");
					}
					{
						titleBook1 = new JLabel();
						titleBook1.setText("Title");
						titleBook1.setFont(new java.awt.Font("Ubuntu",1,15));
					}
					{
						isbnBook1 = new JLabel();
						isbnBook1.setText("Isbn");
					}
					{
						authorBook1 = new JLabel();
						authorBook1.setText("Author");
					}
					{
						genreBook1 = new JLabel();
						genreBook1.setText("Genre");
					}
					SearchResultsLayout.setHorizontalGroup(SearchResultsLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(SearchResultsLayout.createParallelGroup()
									.addGroup(SearchResultsLayout.createSequentialGroup()
											.addGroup(SearchResultsLayout.createParallelGroup()
													.addComponent(viewBook2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
													.addComponent(viewBook3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
													.addComponent(viewBook4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
													.addComponent(viewBook5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
													.addComponent(viewBook6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
													.addComponent(viewBook7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
													.addComponent(viewBook1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addGroup(SearchResultsLayout.createParallelGroup()
															.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																	.addComponent(isbnBook7, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																	.addGap(0, 6, Short.MAX_VALUE))
																	.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																			.addComponent(isbnBook6, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																			.addGap(0, 6, Short.MAX_VALUE))
																			.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																					.addComponent(isbnBook5, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																					.addGap(0, 6, Short.MAX_VALUE))
																					.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																							.addComponent(isbnBook4, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																							.addGap(0, 6, Short.MAX_VALUE))
																							.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																									.addComponent(titleBook7, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																									.addGap(0, 6, Short.MAX_VALUE))
																									.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																											.addComponent(titleBook6, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																											.addGap(0, 6, Short.MAX_VALUE))
																											.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																													.addComponent(titleBook5, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																													.addGap(0, 6, Short.MAX_VALUE))
																													.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																															.addComponent(titleBook4, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																															.addGap(0, 6, Short.MAX_VALUE))
																															.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																																	.addComponent(titleBook3, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																																	.addGap(0, 6, Short.MAX_VALUE))
																																	.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																																			.addComponent(titleBook2, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																																			.addGap(0, 6, Short.MAX_VALUE))
																																			.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																																					.addComponent(isbnBook1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																																					.addGap(0, 6, Short.MAX_VALUE))
																																					.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																																							.addComponent(titleBook1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																																							.addGap(0, 6, Short.MAX_VALUE))
																																							.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																																									.addComponent(isbnBook2, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																																									.addGap(0, 6, Short.MAX_VALUE))
																																									.addComponent(isbnBook3, GroupLayout.Alignment.LEADING, 0, 290, Short.MAX_VALUE))
																																									.addGroup(SearchResultsLayout.createParallelGroup()
																																											.addComponent(genreBook5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(genreBook4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(genreBook3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(genreBook2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(genreBook1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(genreBook7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(genreBook6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
																																											.addComponent(authorBook6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)))
																																											.addGroup(GroupLayout.Alignment.LEADING, SearchResultsLayout.createSequentialGroup()
																																													.addComponent(searchResultsLabel, 0, 512, Short.MAX_VALUE)
																																													.addGap(54)
																																													.addComponent(backToSearch, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
																																													.addGap(14))));
					SearchResultsLayout.setVerticalGroup(SearchResultsLayout.createSequentialGroup()
							.addGap(8)
							.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(backToSearch, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addComponent(searchResultsLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(SearchResultsLayout.createParallelGroup()
											.addGroup(SearchResultsLayout.createSequentialGroup()
													.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
															.addComponent(authorBook1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
															.addComponent(titleBook1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																	.addComponent(genreBook1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(isbnBook1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																	.addComponent(viewBook1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																	.addGroup(SearchResultsLayout.createParallelGroup()
																			.addGroup(SearchResultsLayout.createSequentialGroup()
																					.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																							.addComponent(authorBook2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																							.addComponent(titleBook2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																							.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																									.addComponent(genreBook2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																									.addComponent(isbnBook2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																									.addComponent(viewBook2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
																									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																									.addGroup(SearchResultsLayout.createParallelGroup()
																											.addGroup(SearchResultsLayout.createSequentialGroup()
																													.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																															.addComponent(authorBook3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																															.addComponent(titleBook3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
																															.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																	.addComponent(genreBook3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																	.addComponent(isbnBook3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																																	.addComponent(viewBook3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
																																	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																	.addGroup(SearchResultsLayout.createParallelGroup()
																																			.addGroup(SearchResultsLayout.createSequentialGroup()
																																					.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																							.addComponent(authorBook4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																							.addComponent(titleBook4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																																							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
																																							.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																									.addComponent(genreBook4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																									.addComponent(isbnBook4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																																									.addComponent(viewBook4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
																																									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																									.addGroup(SearchResultsLayout.createParallelGroup()
																																											.addGroup(SearchResultsLayout.createSequentialGroup()
																																													.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																															.addComponent(authorBook5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																															.addComponent(titleBook5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																																															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
																																															.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																																	.addComponent(genreBook5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																																	.addComponent(isbnBook5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																																																	.addComponent(viewBook5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
																																																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																																																	.addGroup(SearchResultsLayout.createParallelGroup()
																																																			.addGroup(SearchResultsLayout.createSequentialGroup()
																																																					.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																																							.addComponent(authorBook6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																																							.addComponent(titleBook6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																																																							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																																							.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																																									.addComponent(genreBook6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																																									.addComponent(isbnBook6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																																																									.addComponent(viewBook6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
																																																									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																																																									.addGroup(SearchResultsLayout.createParallelGroup()
																																																											.addGroup(SearchResultsLayout.createSequentialGroup()
																																																													.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																																															.addComponent(authorBook7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																																															.addComponent(titleBook7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																																																															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																																															.addGroup(SearchResultsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																																																	.addComponent(genreBook7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																																																																	.addComponent(isbnBook7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																																																																	.addComponent(viewBook7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
																																																																	.addContainerGap(60, 60));
				}
				{
					viewBook = new JPanel();
					getContentPane().add(viewBook, "jPanel2");
					GroupLayout viewBookLayout = new GroupLayout((JComponent)viewBook);
					viewBook.setLayout(viewBookLayout);
					viewBook.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					viewBook.setEnabled(false);
					{
						jToggleButton1 = new JToggleButton();
						jToggleButton1.setText("No Cover Image");
						jToggleButton1.setEnabled(false);
					}
					{
						genreLabel = new JLabel();
						genreLabel.setText("Genre:");
					}
					{
						isbnLabel = new JLabel();
						isbnLabel.setText("ISBN:");
					}
					{
						bookIsbn = new JLabel();
						bookIsbn.setText("ISBN");
						bookIsbn.setFont(new java.awt.Font("Ubuntu",1,16));
					}
					{
						bookGenre = new JLabel();
						bookGenre.setText("Genre");
						bookGenre.setFont(new java.awt.Font("Ubuntu",1,16));
					}
					{
						takeOut = new JButton();
						takeOut.setText("Check Out");
						takeOut.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("takeOut.mouseClicked, event="+evt);
								
								//TODO Librarian Login here
								
								String user = JOptionPane.showInputDialog(null, "Patron Username: (lastname, firstname","", 1);
								
							}
						});
					}
					{
						checkStatus = new JButton();
						checkStatus.setText("Check Status");
						checkStatus.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("checkStatus.mouseClicked, event="+evt);
								
							}
						});
					}
					{
						checkIn = new JButton();
						checkIn.setText("Check In");
						checkIn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("checkIn.mouseClicked, event="+evt);
								
							}
						});
					}
					{
						callnumTitle = new JLabel();
						callnumTitle.setText("Call Number:");
					}
					{
						callnumLabel = new JLabel();
						callnumLabel.setText("Call Number");
						callnumLabel.setFont(new java.awt.Font("Ubuntu",1,16));
					}
					{
						notesTitle = new JLabel();
						notesTitle.setText("Notes:");
					}
					{
						notesLabel = new JTextArea();
						notesLabel.setText("General Notes / Book Description");
						notesLabel.setEditable(false);
						notesLabel.setOpaque(false);
						notesLabel.setLineWrap(true);
					}
					{
						bookStatus = new JLabel();
						bookStatus.setText("Status:");
					}
					{
						bookStatusActual = new JLabel();
						bookStatusActual.setText("This book is currently: Avaliable");
					}
					{
						jButton1 = new JButton();
						jButton1.setText("Search Again");
						jButton1.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("jButton1.mouseClicked, event="+evt);
								jPanel1.setVisible(true);
								SearchResults.setVisible(false);
								noResultsFound.setVisible(false);
								viewBook.setVisible(false);
							}
						});
					}
					{
						titleLabel = new JLabel();
						titleLabel.setText("Title:");
					}
					{
						bookTitle = new JLabel();
						bookTitle.setText("Title");
						bookTitle.setFont(new java.awt.Font("Ubuntu",1,16));
					}
					{
						authorLabel = new JLabel();
						authorLabel.setText("Author:");
					}
					{
						bookAuthor = new JLabel();
						bookAuthor.setText("Author");
						bookAuthor.setFont(new java.awt.Font("Ubuntu",1,16));
					}
					viewBookLayout.setHorizontalGroup(viewBookLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(viewBookLayout.createParallelGroup()
									.addComponent(jToggleButton1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addComponent(takeOut, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addComponent(checkStatus, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addComponent(checkIn, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(viewBookLayout.createParallelGroup()
											.addComponent(bookIsbn, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE)
											.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
													.addGroup(viewBookLayout.createParallelGroup()
															.addComponent(callnumLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
															.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																	.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
																	.addGap(213))
																	.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																			.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
																			.addGap(254))
																			.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																					.addComponent(isbnLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
																					.addGap(296))
																					.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																							.addComponent(bookAuthor, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
																							.addGap(129))
																							.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																									.addComponent(genreLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
																									.addGap(296))
																									.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																											.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
																											.addGap(254))
																											.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																													.addComponent(bookStatus, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
																													.addGap(296))
																													.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																															.addComponent(bookStatusActual, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																															.addGap(62))
																															.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																																	.addComponent(bookGenre, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
																																	.addGap(62))
																																	.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																																			.addComponent(callnumTitle, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
																																			.addGap(165))
																																			.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																																					.addComponent(notesTitle, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
																																					.addGap(254)))
																																					.addGap(49)
																																					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
																																					.addGap(0, 68, Short.MAX_VALUE))
																																					.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																																							.addComponent(notesLabel, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)
																																							.addGap(0, 68, Short.MAX_VALUE))));
					viewBookLayout.setVerticalGroup(viewBookLayout.createSequentialGroup()
							.addGap(7)
							.addGroup(viewBookLayout.createParallelGroup()
									.addComponent(jToggleButton1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
									.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
											.addGroup(viewBookLayout.createParallelGroup()
													.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
															.addGap(20)
															.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
															.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																	.addComponent(jButton1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(titleLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																	.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(bookAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																	.addComponent(isbnLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(bookIsbn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																	.addComponent(genreLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(bookGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																	.addComponent(callnumTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(callnumLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
																	.addGroup(viewBookLayout.createParallelGroup()
																			.addComponent(takeOut, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																			.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																					.addGap(12)
																					.addComponent(bookStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
																					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																					.addGroup(viewBookLayout.createParallelGroup()
																							.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																									.addComponent(bookStatusActual, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																									.addGap(12))
																									.addComponent(checkIn, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																									.addGroup(viewBookLayout.createParallelGroup()
																											.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																													.addComponent(notesTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																													.addComponent(notesLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
																													.addGap(0, 0, Short.MAX_VALUE))
																													.addGroup(GroupLayout.Alignment.LEADING, viewBookLayout.createSequentialGroup()
																															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																															.addComponent(checkStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																															.addGap(0, 97, Short.MAX_VALUE)))
																															.addContainerGap(30, 30));
				}
				{
					addLib = new JPanel();
					getContentPane().add(addLib, "jPanel2");
					GroupLayout addLibLayout = new GroupLayout((JComponent)addLib);
					addLib.setLayout(addLibLayout);
					{
						addLibTitle = new JLabel();
						addLibTitle.setText("Set up a new librarian");
					}
					{
						passWord = new JLabel();
						passWord.setText("Password (8 Characters):");
					}
					{
						libCreated = new JLabel();
						libCreated.setText(" ");
					}
					{
						backToControl = new JButton();
						backToControl.setText("Back to Control");
						backToControl.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("backToControl.mouseClicked, event="+evt);
								
								libPass.setEnabled(true);
								submit.setEnabled(true);
								libUser.setEnabled(true);
								
								libPass.setText("");
								libUser.setText("");
								
								jLabel4.setText(" ");
								
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(true);
								changeMaster.setVisible(false);
								
							}
						});
					}
					{
						libPass = new JPasswordField();
					}
					{
						submit = new JButton();
						submit.setText("Add Librarian");
						submit.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("submit.mouseClicked, event="+evt);
								
								if (masterCheckComplete == true){
									String enteredUser = libUser.getText(); 
									String userNameFile = enteredUser + ".lib";
									String newLibPass = libPass.getText();
									
									security security = new security();
									security.write(userNameFile, passphrase);
									
									try {
										security.encrypt(userNameFile, newLibPass);
									} catch (InvalidKeyException e) {
										e.printStackTrace();
									} catch (NoSuchAlgorithmException e) {
										e.printStackTrace();
									} catch (InvalidKeySpecException e) {
										e.printStackTrace();
									} catch (NoSuchPaddingException e) {
										e.printStackTrace();
									} catch (InvalidAlgorithmParameterException e) {
										e.printStackTrace();
									} catch (IllegalBlockSizeException e) {
										e.printStackTrace();
									} catch (BadPaddingException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}
									
									security.deleteFile(userNameFile);
									
									String newUserNameFile = userNameFile + ".des";
									try {
										security.rename(newUserNameFile, userNameFile);
									} catch (IOException e) {
										e.printStackTrace();
									}
									
									jLabel4.setText("Librarian Created");
									
									libPass.setEnabled(false);
									submit.setEnabled(false);
									libUser.setEnabled(false);
									
									System.out.println("Librarian Created");
									
								} else {
									libCreated.setText("Get a Life");
								}
							}
						});
					}
					{
						userName = new JLabel();
						userName.setText("Username:");
					}
					{
						libUser = new JTextField();
					}
					{
						jLabel4 = new JLabel();
						jLabel4.setText(" ");
						jLabel4.setFont(new java.awt.Font("Ubuntu",1,14));
					}
					addLibLayout.setHorizontalGroup(addLibLayout.createSequentialGroup()
							.addComponent(libCreated, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(263)
							.addGroup(addLibLayout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.LEADING, addLibLayout.createSequentialGroup()
											.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE))
											.addGroup(addLibLayout.createSequentialGroup()
													.addPreferredGap(jLabel4, passWord, LayoutStyle.ComponentPlacement.INDENT)
													.addGroup(addLibLayout.createParallelGroup()
															.addGroup(addLibLayout.createSequentialGroup()
																	.addComponent(passWord, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
																	.addGap(0, 0, Short.MAX_VALUE))
																	.addGroup(addLibLayout.createSequentialGroup()
																			.addGap(7)
																			.addGroup(addLibLayout.createParallelGroup()
																					.addGroup(addLibLayout.createSequentialGroup()
																							.addComponent(submit, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
																							.addGroup(addLibLayout.createSequentialGroup()
																									.addComponent(libPass, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
																									.addGroup(addLibLayout.createSequentialGroup()
																											.addComponent(addLibTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																											.addGroup(addLibLayout.createSequentialGroup()
																													.addComponent(userName, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
																													.addGroup(addLibLayout.createSequentialGroup()
																															.addComponent(libUser, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
																															.addGroup(addLibLayout.createSequentialGroup()
																																	.addComponent(backToControl, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
																																	.addGap(0, 20, Short.MAX_VALUE)))))
																																	.addContainerGap(259, 259));
					addLibLayout.setVerticalGroup(addLibLayout.createSequentialGroup()
							.addComponent(libCreated, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(108)
							.addComponent(addLibTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(21)
							.addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(libUser, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(passWord, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(libPass, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(submit, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(backToControl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(116, Short.MAX_VALUE));
				}
				{
					masterCheck = new JPanel();
					getContentPane().add(masterCheck, "jPanel2");
					GroupLayout masterCheckLayout = new GroupLayout((JComponent)masterCheck);
					masterCheck.setLayout(masterCheckLayout);
					{
						jButton2 = new JButton();
						jButton2.setText("Verify");
						jButton2.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								
								masterCheckComplete = false;
								
								System.out.println("jButton2.mouseClicked, event="+evt);
								String MasterPasswordCheck = jPasswordField1.getText();
								jPasswordField1.setText("");
								
								security security = new security();
								String filename = "master.des";
								String changedFilename = "master.des.dcr";
								
								security.decrypt(filename, MasterPasswordCheck);
								
								if (security.passwordFalse == true){
									security.passwordFalse = false;
									jLabel2.setText("Incorrect!");
								} else {
									jLabel2.setText(" ");
									security.deleteFile(changedFilename);
									
									masterCheckComplete = true;
									
									jPanel1.setVisible(false);
									noResultsFound.setVisible(false);
									SearchResults.setVisible(false);
									addLib.setVisible(true);
									masterCheck.setVisible(false);
									libControl.setVisible(false);
									changeMaster.setVisible(false);	
								}
								
							}
						});
					}
					{
						jPasswordField1 = new JPasswordField();
					}
					{
						jLabel1 = new JLabel();
						jLabel1.setText("Master User Password:");
					}
					{
						jLabel3 = new JLabel();
						jLabel3.setText("The Master Librarian must first verify you want to do this!");
					}
					{
						jLabel2 = new JLabel();
						jLabel2.setText(" ");
						jLabel2.setFont(new java.awt.Font("Andale Mono",1,14));
					}
					{
						backToLib = new JButton();
						backToLib.setText("Back to Control");
						backToLib.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("backToLib.mouseClicked, event="+evt);
								jPanel1.setVisible(true);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(true);
								changeMaster.setVisible(false);
							}
						});
					}
					masterCheckLayout.setHorizontalGroup(masterCheckLayout.createSequentialGroup()
							.addContainerGap(151, 151)
							.addGroup(masterCheckLayout.createParallelGroup()
									.addGroup(masterCheckLayout.createSequentialGroup()
											.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
											.addGap(0, 0, Short.MAX_VALUE))
											.addGroup(masterCheckLayout.createSequentialGroup()
													.addGap(125)
													.addGroup(masterCheckLayout.createParallelGroup()
															.addGroup(masterCheckLayout.createSequentialGroup()
																	.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
																	.addGroup(masterCheckLayout.createSequentialGroup()
																			.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
																			.addGroup(masterCheckLayout.createSequentialGroup()
																					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
																					.addGroup(masterCheckLayout.createSequentialGroup()
																							.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
																							.addGroup(masterCheckLayout.createSequentialGroup()
																									.addComponent(backToLib, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
																									.addGap(0, 113, Short.MAX_VALUE)))
																									.addContainerGap(154, 154));
					masterCheckLayout.setVerticalGroup(masterCheckLayout.createSequentialGroup()
							.addContainerGap(120, 120)
							.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(backToLib, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(160, Short.MAX_VALUE));
				}
				{
					libControl = new JPanel();
					getContentPane().add(libControl, "jPanel2");
					GroupLayout libControlLayout = new GroupLayout((JComponent)libControl);
					libControl.setLayout(libControlLayout);
					{
						libCtrlHeader = new JLabel();
						libCtrlHeader.setText("Welcome to Library Control");
					}
					{
						back = new JButton();
						back.setText("Back to Homepage");
						back.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("back.mouseClicked, event="+evt);
								jPanel1.setVisible(true);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(false);
							}
						});
					}
					{
						addPatronButton = new JButton();
						addPatronButton.setText("Add Patron");
						addPatronButton.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("addPatronButton.mouseClicked, event="+evt);
								addPatron.setVisible(true);
								libControl.setVisible(false);
								
							}
						});
					}
					{
						removeLibrarianButton = new JButton();
						removeLibrarianButton.setText("Remove Librarian");
					}
					{
						checkedoutListButton = new JButton();
						checkedoutListButton.setText("Checked out List");
					}
					{
						overdueListButton = new JButton();
						overdueListButton.setText("Overdue List");
					}
					{
						aquisitionListButton = new JButton();
						aquisitionListButton.setText("Aquisition List");
					}
					{
						libPrefButton = new JButton();
						libPrefButton.setText("Library Preferences");
					}
					{
						editBookDataButton = new JButton();
						editBookDataButton.setText("Edit Book Information");
					}
					{
						removeBooksButton = new JButton();
						removeBooksButton.setText("Remove Books");
					}
					{
						changePatronDataButton = new JButton();
						changePatronDataButton.setText("Edit Patron Data");
					}
					{
						checkoutmultipleButton = new JButton();
						checkoutmultipleButton.setText("Check Out Multiple Books");
					}
					{
						checkinMultipleButton = new JButton();
						checkinMultipleButton.setText("Check In Multiple Books");
					}
					{
						removePatronButton = new JButton();
						removePatronButton.setText("Remove Patron");
					}
					{
						jButton3 = new JButton();
						jButton3.setText("Add a Librarian");
						jButton3.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("jButton3.mouseClicked, event="+evt);
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(true);
								libControl.setVisible(false);
							}
						});
					}
					{
						jButton5 = new JButton();
						jButton5.setText("Change Master Password");
						jButton5.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("jButton5.mouseClicked, event="+evt);
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(false);
								changeMaster.setVisible(true);
							}
						});
					}
					{
						googleBook = new JButton();
						googleBook.setText("Add Books with Google");
						googleBook.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("googleBook.mouseClicked, event="+evt);
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(false);
								changeMaster.setVisible(false);
								addNewBook.setVisible(false);
								addBookWithGoogle.setVisible(true);
								
							}
						});
					}
					{
						addBook = new JButton();
						addBook.setText("Add Books  by Hand");
						addBook.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("addBook.mouseClicked, event="+evt);
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(false);
								changeMaster.setVisible(false);
								addNewBook.setVisible(true);
							}
						});
					}
					libControlLayout.setHorizontalGroup(libControlLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(libControlLayout.createParallelGroup()
									.addGroup(libControlLayout.createSequentialGroup()
											.addGroup(libControlLayout.createParallelGroup()
													.addComponent(jButton5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
													.addComponent(jButton3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
													.addComponent(removeLibrarianButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
													.addComponent(checkedoutListButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
													.addComponent(overdueListButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
													.addComponent(aquisitionListButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
													.addGap(55)
													.addGroup(libControlLayout.createParallelGroup()
															.addGroup(GroupLayout.Alignment.LEADING, libControlLayout.createSequentialGroup()
																	.addComponent(libCtrlHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addGap(12))
																	.addComponent(removePatronButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
																	.addComponent(addPatronButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
																	.addComponent(checkoutmultipleButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
																	.addComponent(checkinMultipleButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
																	.addComponent(changePatronDataButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
																	.addGap(46)
																	.addGroup(libControlLayout.createParallelGroup()
																			.addComponent(addBook, GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)
																			.addComponent(googleBook, GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)
																			.addComponent(removeBooksButton, GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)
																			.addComponent(editBookDataButton, GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)
																			.addComponent(libPrefButton, GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)))
																			.addGroup(GroupLayout.Alignment.LEADING, libControlLayout.createSequentialGroup()
																					.addGap(161)
																					.addComponent(back, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
																					.addGap(0, 135, Short.MAX_VALUE)))
																					.addContainerGap());
					libControlLayout.setVerticalGroup(libControlLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(libCtrlHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addGroup(libControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(addBook, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(addPatronButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(jButton3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(libControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(changePatronDataButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(googleBook, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
											.addComponent(removeLibrarianButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(libControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
													.addComponent(removeBooksButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(removePatronButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(jButton5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
													.addGap(42)
													.addGroup(libControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
															.addComponent(editBookDataButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
															.addComponent(checkoutmultipleButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
															.addComponent(checkedoutListButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addGroup(libControlLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																	.addComponent(libPrefButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(checkinMultipleButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addComponent(overdueListButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
																	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																	.addComponent(aquisitionListButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addGap(0, 87, Short.MAX_VALUE)
																	.addComponent(back, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
																	.addContainerGap(47, 47));
				}
				{
					changeMaster = new JPanel();
					getContentPane().add(changeMaster, "jPanel2");
					GroupLayout changeMasterLayout = new GroupLayout((JComponent)changeMaster);
					changeMaster.setLayout(changeMasterLayout);
					{
						changeMasterTitle = new JLabel();
						changeMasterTitle.setText("Set the Master User Password");
					}
					{
						info = new JLabel();
						info.setText("This should only be done once, when you first set up the program!");
					}
					{
						message = new JLabel();
						message.setText(" ");
					}
					{
						newMasterPassword = new JPasswordField();
					}
					{
						jButton6 = new JButton();
						jButton6.setText("Back to Control");
						jButton6.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("jButton6.mouseClicked, event="+evt);
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(true);
								changeMaster.setVisible(false);
							}
						});
					}
					{
						incorrect = new JLabel();
						incorrect.setText(" ");
						incorrect.setFont(new java.awt.Font("Ubuntu",1,16));
					}
					{
						passwordTitle = new JLabel();
						passwordTitle.setText("Password:");
					}
					{
						jButton4 = new JButton();
						jButton4.setText("Submit");
						jButton4.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("jButton4.mouseClicked, event="+evt);
								security security = new security();
								String filename = "master.des";
								String password = "autolib";
								security.decrypt(filename, password);
								incorrect.setText(" ");
								
								if (security.passwordFalse == true){
									security.passwordFalse = false;
									incorrect.setText("The Master Password has already been set!");
								} else {
									
									String masterNewPassword = newMasterPassword.getText();
									
									String delete = "master.des";
									security.deleteFile(delete);
									String encryptFile = "master.des.dcr";
									
									try {
										security.encrypt(encryptFile, masterNewPassword);
									} catch (InvalidKeyException e1) {
										
										e1.printStackTrace();
									} catch (NoSuchAlgorithmException e1) {
										
										e1.printStackTrace();
									} catch (InvalidKeySpecException e1) {
										
										e1.printStackTrace();
									} catch (NoSuchPaddingException e1) {
										
										e1.printStackTrace();
									} catch (InvalidAlgorithmParameterException e1) {
										
										e1.printStackTrace();
									} catch (IllegalBlockSizeException e1) {
										
										e1.printStackTrace();
									} catch (IOException e1) {
										
										e1.printStackTrace();
									} catch (BadPaddingException e) {
										
										e.printStackTrace();
									}
									
									String oldName = "master.des.dcr.des";
									String newName = "master.des";
									
									
									try {
										security.rename(oldName, newName);
									} catch (IOException e) {
										e.printStackTrace();
									}
									
									incorrect.setText("The Master Password was changed Succesfully!");
									jButton4.setEnabled(false);
									newMasterPassword.setEnabled(false);
									
								}
								
							}
						});
					}
					changeMasterLayout.setHorizontalGroup(changeMasterLayout.createSequentialGroup()
							.addContainerGap(149, 149)
							.addGroup(changeMasterLayout.createParallelGroup()
									.addGroup(changeMasterLayout.createSequentialGroup()
											.addComponent(info, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
											.addGap(0, 0, Short.MAX_VALUE))
											.addGroup(changeMasterLayout.createSequentialGroup()
													.addGap(32)
													.addGroup(changeMasterLayout.createParallelGroup()
															.addComponent(incorrect, GroupLayout.Alignment.LEADING, 0, 411, Short.MAX_VALUE)
															.addGroup(changeMasterLayout.createSequentialGroup()
																	.addGap(101)
																	.addGroup(changeMasterLayout.createParallelGroup()
																			.addGroup(changeMasterLayout.createSequentialGroup()
																					.addComponent(changeMasterTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																					.addGap(0, 0, Short.MAX_VALUE))
																					.addGroup(changeMasterLayout.createSequentialGroup()
																							.addPreferredGap(changeMasterTitle, message, LayoutStyle.ComponentPlacement.INDENT)
																							.addGroup(changeMasterLayout.createParallelGroup()
																									.addGroup(GroupLayout.Alignment.LEADING, changeMasterLayout.createSequentialGroup()
																											.addComponent(message, 0, 57, Short.MAX_VALUE)
																											.addGap(0, 111, GroupLayout.PREFERRED_SIZE))
																											.addGroup(changeMasterLayout.createSequentialGroup()
																													.addGap(7)
																													.addGroup(changeMasterLayout.createParallelGroup()
																															.addGroup(changeMasterLayout.createSequentialGroup()
																																	.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
																																	.addGap(0, 0, Short.MAX_VALUE))
																																	.addGroup(GroupLayout.Alignment.LEADING, changeMasterLayout.createSequentialGroup()
																																			.addComponent(passwordTitle, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
																																			.addGap(0, 57, Short.MAX_VALUE))
																																			.addGroup(changeMasterLayout.createSequentialGroup()
																																					.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
																																					.addGap(0, 0, Short.MAX_VALUE))
																																					.addGroup(changeMasterLayout.createSequentialGroup()
																																							.addComponent(newMasterPassword, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
																																							.addGap(0, 0, Short.MAX_VALUE)))))
																																							.addGap(32)))
																																							.addGap(98)))
																																							.addGap(59)))
																																							.addContainerGap(65, 65));
					changeMasterLayout.setVerticalGroup(changeMasterLayout.createSequentialGroup()
							.addContainerGap(121, 121)
							.addComponent(changeMasterTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(info, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(25)
							.addComponent(passwordTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(newMasterPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(incorrect, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(message, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(103, Short.MAX_VALUE));
				}
				{
					addNewBook = new JPanel();
					getContentPane().add(addNewBook, "addNewBook");
					GroupLayout addNewBookLayout = new GroupLayout((JComponent)addNewBook);
					addNewBook.setLayout(addNewBookLayout);
					{
						addBookHeader = new JLabel();
						addBookHeader.setText("Add a New Book to the Library");
					}
					{
						titleheader = new JLabel();
						titleheader.setText("Book Title:");
					}
					{
						newBookTitle = new JTextField();
						newBookTitle.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent evt) {
								System.out.println("newBookTitle.focusGained, event="+evt);
								submitBook.setText("Add Book");
							}
						});
					}
					{
						addBookAuthorHeader = new JLabel();
						addBookAuthorHeader.setText("Book's Author:");
					}
					{
						newBookAuthor = new JTextField();
					}
					{
						addBookIsbnHeader = new JLabel();
						addBookIsbnHeader.setText("Book's ISBN:");
					}
					{
						newBookIsbn = new JTextField();
					}
					{
						newBookGenreHeader = new JLabel();
						newBookGenreHeader.setText("Book's Genres:");
					}
					{
						addBookGenre = new JTextField();
					}
					{
						submitBook = new JButton();
						submitBook.setText("Add New Book");
						submitBook.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("submitBook.mouseClicked, event="+evt);
								
								int bookID = 0;
								String bookTitle = newBookTitle.getText();
								String bookAuthor = newBookAuthor.getText();
								String bookIsbn = newBookIsbn.getText();
								String bookGenre = addBookGenre.getText();
								String bookCallnum = newBookCall.getText();
								String bookNotes = newBookNotes.getText();
								bookNotes = bookNotes.replace("\n", " ");
								File inputFile = new File("books.xml");
								File tempFile = new File("books.tmp");
								
								security security = new security();
								try {
									security.reader("bookID", 2);
									
									
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								System.out.println(security.lineString);
								bookID = Integer.parseInt(security.lineString);
								bookID++;
								String bookIDString = ""+bookID;
								security.deleteFile("bookID");
								security.write("bookID", bookIDString);
								
								//Create a new bookFile
								security.write(bookIDString, "<book>\n");
								security.write(bookIDString, "<title>" + bookTitle + "</title>\n");
								security.write(bookIDString, "<author>" + bookAuthor + "</author>\n");
								security.write(bookIDString, "<cover></cover>\n");
								security.write(bookIDString, "<genre>" + bookGenre + "</genre>\n");
								security.write(bookIDString, "<ISBN>" + bookIsbn + "</ISBN>\n");
								security.write(bookIDString, "<callnum>"+bookCallnum  + "</callnum>\n");
								security.write(bookIDString, "<notes>" + bookNotes + "</notes>\n");
								security.write(bookIDString, "<patron>Available</patron>\n");
								security.write(bookIDString, "<checkoutDate></checkoutDate>\n");
								security.write(bookIDString, "<dueDate></dueDate>\n");
								security.write(bookIDString, "</book>\n");
								
								BufferedReader reader = null;
								try {
									reader = new BufferedReader(new FileReader(inputFile));
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								BufferedWriter writer = null;
								try {
									writer = new BufferedWriter(new FileWriter(tempFile));
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								String lineToRemove = "</XML>";
								String currentLine = "";
								
								try {
									while((currentLine = reader.readLine()) != null) {
										
										System.out.println(currentLine + "|" + lineToRemove + "|");
										
										if(currentLine.contains(lineToRemove)) {
											System.out.println("EQUAL");
										} else {
											writer.write(currentLine);
											writer.newLine();
										}
										
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								try {
									writer.write("<book>");
									writer.newLine();
									writer.write("<title>"+bookTitle+"</title>");
									writer.newLine();
									writer.write("<author>"+bookAuthor+"</author>");
									writer.newLine();
									writer.write("<genre>"+bookGenre+"</genre>");
									writer.newLine();
									writer.write("<ISBN>"+bookIsbn+"</ISBN>");
									writer.newLine();
									writer.write("<bookID>"+bookID+"</bookID>");
									writer.newLine();
									writer.write("</book>");
									writer.newLine();
									writer.write("</XML>");
									writer.close();
									boolean successful = tempFile.renameTo(inputFile);
									
								} catch (IOException e) {
									e.printStackTrace();
								}
								
								addBookGenre.setText("");
								newBookAuthor.setText("");
								newBookTitle.setText("");
								newBookIsbn.setText("");
								newBookCall.setText("");
								newBookNotes.setText("");
								
								submitBook.setText("Book Added");
								
							}
							
						});
					}
					{
						notesHeader = new JLabel();
						notesHeader.setText("General Notes/Book Description:");
					}
					{
						BacktoLibCTRL = new JButton();
						BacktoLibCTRL.setText("Cancel");
						BacktoLibCTRL.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("BacktoLibCTRL.mouseClicked, event="+evt);
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(true);
								addNewBook.setVisible(false);
								addBookWithGoogle.setVisible(false);
							}
						});
					}
					{
						scrollPanel = new JScrollPane();
						{
							newBookNotes = new JTextArea();
							scrollPanel.setViewportView(newBookNotes);
							newBookNotes.setPreferredSize(new java.awt.Dimension(216, 314));
							newBookNotes.setLineWrap(true);
							newBookNotes.setTabSize(1);
						}
					}
					{
						callNum = new JLabel();
						callNum.setText("Call Number");
					}
					{
						newBookCall = new JTextField();
					}
					addNewBookLayout.setHorizontalGroup(addNewBookLayout.createSequentialGroup()
							.addContainerGap(51, 51)
							.addGroup(addNewBookLayout.createParallelGroup()
									.addGroup(addNewBookLayout.createSequentialGroup()
											.addGroup(addNewBookLayout.createParallelGroup()
													.addComponent(newBookCall, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
													.addComponent(callNum, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
													.addComponent(addBookGenre, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
													.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
															.addComponent(newBookGenreHeader, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
															.addGap(145))
															.addComponent(newBookIsbn, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
															.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
																	.addComponent(addBookIsbnHeader, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
																	.addGap(145))
																	.addComponent(newBookAuthor, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
																	.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
																			.addComponent(addBookAuthorHeader, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
																			.addGap(145))
																			.addComponent(newBookTitle, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
																			.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
																					.addComponent(titleheader, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
																					.addGap(145))
																					.addComponent(BacktoLibCTRL, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
																					.addComponent(submitBook, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE))
																					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																					.addGroup(addNewBookLayout.createParallelGroup()
																							.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
																									.addComponent(scrollPanel, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
																									.addGap(0, 18, Short.MAX_VALUE))
																									.addGroup(addNewBookLayout.createSequentialGroup()
																											.addComponent(getNotesHeader(), GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
																											.addGap(0, 0, Short.MAX_VALUE))))
																											.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
																													.addGap(194)
																													.addComponent(addBookHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
																													.addGap(0, 211, Short.MAX_VALUE)))
																													.addContainerGap(44, 44));
					addNewBookLayout.setVerticalGroup(addNewBookLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(addBookHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(addNewBookLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(titleheader, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(getNotesHeader(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(addNewBookLayout.createParallelGroup()
											.addGroup(GroupLayout.Alignment.LEADING, addNewBookLayout.createSequentialGroup()
													.addComponent(newBookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(addBookAuthorHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(newBookAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(addBookIsbnHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(newBookIsbn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(newBookGenreHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
													.addComponent(addBookGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
													.addComponent(callNum, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
													.addComponent(newBookCall, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
													.addComponent(submitBook, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(BacktoLibCTRL, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
													.addComponent(scrollPanel, GroupLayout.Alignment.LEADING, 0, 332, Short.MAX_VALUE))
													.addContainerGap(74, 74));
				}
				{
					addBookWithGoogle = new JPanel();
					GroupLayout addBookWithGoogleLayout = new GroupLayout((JComponent)addBookWithGoogle);
					addBookWithGoogle.setLayout(addBookWithGoogleLayout);
					getContentPane().add(addBookWithGoogle, "jPanel2");
					{
						googleHeader = new JLabel();
						googleHeader.setText("Import Books Information using Google Books");
					}
					{
						isbnInput = new JTextField();
					}
					{
						isbnHeader = new JLabel();
						isbnHeader.setText("Enter the ISBN of the book that you wish to Input");
					}
					{
						searchGoogle = new JButton();
						searchGoogle.setText("Search Google Books");
						searchGoogle.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								
								System.out.println("searchGoogle.mouseClicked, event="+evt);
								
								String searchTerm = isbnInput.getText();
								
								BooksService service = new BooksService("gdataSample-Books-1");

								BooksClient BC = new BooksClient();
								try {
									BC.searchVolumes(service, false, searchTerm);
								} catch (IOException e) {
									e.printStackTrace();
								} catch (ServiceException e) {
									e.printStackTrace();
								}
								
								
								int googleResultsSize = BC.googleResults.size();
								
								//Makes all fields invisible, they will be made visible if filled
								viewBook1.setVisible(false);
								viewBook2.setVisible(false);
								viewBook3.setVisible(false);
								viewBook4.setVisible(false);
								viewBook5.setVisible(false);
								viewBook6.setVisible(false);
								viewBook7.setVisible(false);
								
								
								titleBook1.setText(" ");
								titleBook2.setText(" ");
								titleBook3.setText(" ");
								titleBook4.setText(" ");
								titleBook5.setText(" ");
								titleBook6.setText(" ");
								titleBook7.setText(" ");
								authorBook1.setText(" ");
								authorBook2.setText(" ");
								authorBook3.setText(" ");
								authorBook4.setText(" ");
								authorBook5.setText(" ");
								authorBook6.setText(" ");
								authorBook7.setText(" ");
								isbnBook1.setText(" ");
								isbnBook2.setText(" ");
								isbnBook3.setText(" ");
								isbnBook4.setText(" ");
								isbnBook5.setText(" ");
								isbnBook6.setText(" ");
								isbnBook7.setText(" ");
								genreBook1.setText(" ");
								genreBook2.setText(" ");
								genreBook3.setText(" ");
								genreBook4.setText(" ");
								genreBook5.setText(" ");
								genreBook6.setText(" ");
								genreBook7.setText(" ");
								titleBook1.setVisible(false);
								titleBook2.setVisible(false);
								titleBook3.setVisible(false);
								titleBook4.setVisible(false);
								titleBook5.setVisible(false);
								titleBook6.setVisible(false);
								titleBook7.setVisible(false);
								authorBook1.setVisible(false);
								authorBook2.setVisible(false);
								authorBook3.setVisible(false);
								authorBook4.setVisible(false);
								authorBook5.setVisible(false);
								authorBook6.setVisible(false);
								authorBook7.setVisible(false);
								isbnBook1.setVisible(false);
								isbnBook2.setVisible(false);
								isbnBook3.setVisible(false);
								isbnBook4.setVisible(false);
								isbnBook5.setVisible(false);
								isbnBook6.setVisible(false);
								isbnBook7.setVisible(false);
								genreBook1.setVisible(false);
								genreBook2.setVisible(false);
								genreBook3.setVisible(false);
								genreBook4.setVisible(false);
								genreBook5.setVisible(false);
								genreBook6.setVisible(false);
								genreBook7.setVisible(false);
								
								//Changes from view to Pick
								viewBook1.setText("Pick");
								viewBook2.setText("Pick");
								viewBook3.setText("Pick");
								viewBook4.setText("Pick");
								viewBook5.setText("Pick");
								viewBook6.setText("Pick");
								viewBook7.setText("Pick");
								
								searchResultsLabel.setText("Search Results: (Powered by Google)");
								
								int currentLine = 0;
								int resultPrint = 1;
								String getTitle = "";
								boolean getAuthor = false;
								String resultsLine = "";
									
								while (googleResultsSize-1>currentLine){
									if(BC.googleResults.get(currentLine).equals("1")){
										while (getAuthor==false){
											
											resultsLine = BC.googleResults.get(currentLine);
											if (resultsLine.contains("Title")){
												currentLine++;
												getTitle = BC.googleResults.get(currentLine);
											} else if (resultsLine.contains("Author")){
												currentLine++;
												authorBook1.setText("Author: " + BC.googleResults.get(currentLine));
												getAuthor=true;
											} else {
												getTitle = getTitle + " " + BC.googleResults.get(currentLine);
											}
											
											if (getAuthor==false){
												currentLine++;
											}
									}
										
										titleBook1.setVisible(true);
										titleBook1.setText("Title: " + getTitle);
										authorBook1.setVisible(true);
										isbnBook1.setVisible(true);
										isbnBook1.setText(" ");
										viewBook1.setVisible(true);
										getAuthor=false;
										
									} else if(BC.googleResults.get(currentLine).equals("2")){
											while (getAuthor==false){
													
													resultsLine = BC.googleResults.get(currentLine);
													if (resultsLine.contains("Title")){
														currentLine++;
														getTitle = BC.googleResults.get(currentLine);
													} else if (resultsLine.contains("Author")){
														currentLine++;
														authorBook2.setText("Author: " + BC.googleResults.get(currentLine));
														getAuthor=true;
													} else {
														getTitle = getTitle + " " + BC.googleResults.get(currentLine);
													}
													if (getAuthor==false){
														currentLine++;
													}
											}
										
											
											titleBook2.setVisible(true);
											titleBook2.setText("Title: " + getTitle);
											authorBook2.setVisible(true);
											isbnBook2.setVisible(true);
											isbnBook2.setText(" ");
											viewBook2.setVisible(true);
											getAuthor=false;
											
									} else if(BC.googleResults.get(currentLine).equals("3")){
										
										while (getAuthor==false){
											
											resultsLine = BC.googleResults.get(currentLine);
											if (resultsLine.contains("Title")){
												currentLine++;
												getTitle = BC.googleResults.get(currentLine);
											} else if (resultsLine.contains("Author")){
												currentLine++;
												authorBook3.setText("Author: " + BC.googleResults.get(currentLine));
												getAuthor=true;
											} else {
												getTitle = getTitle + " " + BC.googleResults.get(currentLine);
											}
											if (getAuthor==false){
												currentLine++;
											}
									}
											
											titleBook3.setVisible(true);
											titleBook3.setText("Title: " + getTitle);
											authorBook3.setVisible(true);
											isbnBook3.setVisible(true);
											isbnBook3.setText(" ");
											viewBook3.setVisible(true);
											getAuthor=false;
									
									} else if(BC.googleResults.get(currentLine).equals("4")){
										
										while (getAuthor==false){
											
											resultsLine = BC.googleResults.get(currentLine);
											if (resultsLine.contains("Title")){
												currentLine++;
												getTitle = BC.googleResults.get(currentLine);
											} else if (resultsLine.contains("Author")){
												currentLine++;
												authorBook4.setText("Author: " + BC.googleResults.get(currentLine));
												getAuthor=true;
											} else {
												getTitle = getTitle + " " + BC.googleResults.get(currentLine);
											}
											if (getAuthor==false){
												currentLine++;
											}
									}
										
										titleBook4.setVisible(true);
										titleBook4.setText("Title: " + getTitle);
										authorBook4.setVisible(true);
										isbnBook4.setVisible(true);
										isbnBook4.setText(" ");
										viewBook4.setVisible(true);
										getAuthor=false;
								
									} else if(BC.googleResults.get(currentLine).equals("5")){
										
										while (getAuthor==false){
											
											resultsLine = BC.googleResults.get(currentLine);
											if (resultsLine.contains("Title")){
												currentLine++;
												getTitle = BC.googleResults.get(currentLine);
											} else if (resultsLine.contains("Author")){
												currentLine++;
												authorBook5.setText("Author: " + BC.googleResults.get(currentLine));
												getAuthor=true;
											} else {
												getTitle = getTitle + " " + BC.googleResults.get(currentLine);
											}
											if (getAuthor==false){
												currentLine++;
											}
									}
										
										titleBook5.setVisible(true);
										titleBook5.setText("Title: " + getTitle);
										authorBook5.setVisible(true);
										isbnBook5.setVisible(true);
										isbnBook5.setText(" ");
										viewBook5.setVisible(true);
										getAuthor=false;
								
									} else if(BC.googleResults.get(currentLine).equals("6")){
										
										while (getAuthor==false){
											
											resultsLine = BC.googleResults.get(currentLine);
											if (resultsLine.contains("Title")){
												currentLine++;
												getTitle = BC.googleResults.get(currentLine);
											} else if (resultsLine.contains("Author")){
												currentLine++;
												authorBook6.setText("Author: " + BC.googleResults.get(currentLine));
												getAuthor=true;
											} else {
												getTitle = getTitle + " " + BC.googleResults.get(currentLine);
											}
											if (getAuthor==false){
												currentLine++;
											}
									}
										
										titleBook6.setVisible(true);
										titleBook6.setText("Title: " + getTitle);
										authorBook6.setVisible(true);
										isbnBook6.setVisible(true);
										isbnBook6.setText(" ");
										viewBook6.setVisible(true);
										getAuthor=false;
								
									} else if(BC.googleResults.get(currentLine).equals("7")){
										
										while (getAuthor==false){
											
											resultsLine = BC.googleResults.get(currentLine);
											if (resultsLine.contains("Title")){
												currentLine++;
												getTitle = BC.googleResults.get(currentLine);
											} else if (resultsLine.contains("Author")){
												currentLine++;
												authorBook7.setText("Author: " + BC.googleResults.get(currentLine));
												getAuthor=true;
											} else {
												getTitle = getTitle + " " + BC.googleResults.get(currentLine);
											}
											if (getAuthor==false){
												currentLine++;
											}
									}
										
										titleBook7.setVisible(true);
										titleBook7.setText("Title: " + getTitle);
										authorBook7.setVisible(true);
										isbnBook7.setVisible(true);
										isbnBook7.setText(" ");
										viewBook7.setVisible(true);
										getAuthor=false;
								
									}
										currentLine++;
									
								}
								
								searchWithGoogle = true;
								
								//Display search results
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(true);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(false);
								changeMaster.setVisible(false);	
								masterCheck.setVisible(false);
								addNewBook.setVisible(false);
								addBookWithGoogle.setVisible(false);
							
						}
							
						});
					}
						addBookWithGoogleLayout.setHorizontalGroup(addBookWithGoogleLayout.createSequentialGroup()
						.addContainerGap(142, 142)
						.addGroup(addBookWithGoogleLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, addBookWithGoogleLayout.createSequentialGroup()
						        .addComponent(isbnInput, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(searchGoogle, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 0, Short.MAX_VALUE))
						    .addGroup(addBookWithGoogleLayout.createSequentialGroup()
						        .addGap(38)
						        .addGroup(addBookWithGoogleLayout.createParallelGroup()
						            .addGroup(addBookWithGoogleLayout.createSequentialGroup()
						                .addComponent(isbnHeader, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						                .addGap(0, 0, Short.MAX_VALUE))
						            .addGroup(GroupLayout.Alignment.LEADING, addBookWithGoogleLayout.createSequentialGroup()
						                .addPreferredGap(isbnHeader, googleHeader, LayoutStyle.ComponentPlacement.INDENT)
						                .addComponent(googleHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						                .addGap(0, 22, Short.MAX_VALUE)))
						        .addGap(58)))
						.addContainerGap(121, 121));
						addBookWithGoogleLayout.setVerticalGroup(addBookWithGoogleLayout.createSequentialGroup()
						.addContainerGap(121, 121)
						.addComponent(googleHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(31)
						.addComponent(isbnHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(addBookWithGoogleLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(searchGoogle, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(isbnInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(242, 242));
				}
				{
					addPatron = new JPanel();
					getContentPane().add(addPatron, "jPanel2");
					GroupLayout addPatronLayout = new GroupLayout((JComponent)addPatron);
					addPatron.setLayout(addPatronLayout);
					addPatron.setPreferredSize(new java.awt.Dimension(708, 466));
					{
						addPatronTitle = new JLabel();
						addPatronTitle.setText("Add a new Patron to the Library:");
					}
					{
						dateofbirth = new JLabel();
						dateofbirth.setText("Date of Birth (All Numeric Values):");
					}
					{
						phoneNumber = new JLabel();
						phoneNumber.setText("Phone Number:");
					}
					{
						phoneNumberField = new JTextField();
					}
					{
						email = new JLabel();
						email.setText("Email Address:");
					}
					{
						emailField = new JTextField();
					}
					{
						addNewPatron = new JButton();
						addNewPatron.setText("Add Patron");
						addNewPatron.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("addNewPatron.mouseClicked, event="+evt);
								System.out.print("Creating new Patron File");
								security security = new security();
								
								try {
									security.reader("userID", 2);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								System.out.println(security.lineString);
								int userID = Integer.parseInt(security.lineString);
								userID++;
								String bookIDString = ""+userID;
								security.deleteFile("userID");
								security.write("userID", bookIDString);
								
								security.write("users.xml", "<user>\n");
								security.write("users.xml", "<GivenName>"+givenNameField.getText()+"</GivenName>\n");
								security.write("users.xml", "<Surname>"+surnameField.getText()+"</Surname>\n");
								security.write("users.xml", "<userID>"+userID+"</userID>\n");
								security.write("users.xml", "</user>\n");
								
								security.write(userID + ".usr", "<user>\n");
								security.write(userID + ".usr", "<GivenName>"+givenNameField.getText()+"</GivenName>\n");
								security.write(userID + ".usr", "<Surname>"+surnameField.getText()+"</Surename>\n");
								security.write(userID + ".usr", "<Address>"+addressField.getText()+"</Address>\n");
								security.write(userID + ".usr", "<Address>"+addressField2.getText()+"</Address>\n");
								security.write(userID + ".usr", "<PostalCode>"+postalCodeField.getText()+"</PostalCode>\n");
								security.write(userID + ".usr", "<email>"+emailField.getText()+"</email>\n");
								security.write(userID + ".usr", "<DOB>\n");
								String[] years =  {"1900", "1901", "1902", "1903", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2048", "2049", "2050"};
								String year = years[yearField.getSelectedIndex()];
								security.write(userID + ".usr", "<year>"+year+"</year>\n");
								String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
								String month = months[monthField.getSelectedIndex()];
								security.write(userID + ".usr", "<month>"+month+"</month>\n");
								String[] days = { "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
								String day = days[dayField.getSelectedIndex()];
								security.write(userID + ".usr", "<day>"+day + "</day>\n");
								security.write(userID + ".usr", "</DOB>\n");
								security.write(userID + ".usr", "</user>\n");
								
							}
						});
					}
					{
						cancelAddPatron = new JButton();
						cancelAddPatron.setText("Cancel");
						cancelAddPatron.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("cancelAddPatron.mouseClicked, event="+evt);
								emailField.setText("");
								addressField.setText("");
								addressField2.setText("");
								phoneNumberField.setText("");
								postalCodeField.setText("");
								cityField.setText("");
								surnameField.setText("");
								givenNameField.setText("");
								
								jPanel1.setVisible(false);
								noResultsFound.setVisible(false);
								SearchResults.setVisible(false);
								addLib.setVisible(false);
								masterCheck.setVisible(false);
								libControl.setVisible(true);
								addNewBook.setVisible(false);
								addBookWithGoogle.setVisible(false);
								viewBook.setVisible(false);
								addPatron.setVisible(false);
							}
						});
					}
					{
						jScrollPane3 = new JScrollPane();
						{
							ListModel dayFieldModel = 
								new DefaultComboBoxModel(
										new String[] { "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31" });
							dayField = new JList();
							jScrollPane3.setViewportView(dayField);
							dayField.setModel(dayFieldModel);
						}
					}
					{
						day = new JLabel();
						day.setText("Day:");
					}
					{
						jScrollPane2 = new JScrollPane();
						{
							ListModel monthFieldModel = 
								new DefaultComboBoxModel(
										new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" });
							monthField = new JList();
							jScrollPane2.setViewportView(monthField);
							monthField.setModel(monthFieldModel);
						}
					}
					{
						year = new JLabel();
						year.setText("Year:");
					}
					{
						month = new JLabel();
						month.setText("Month:");
					}
					{
						givenName = new JLabel();
						givenName.setText("Given Name:");
					}
					{
						givenNameField = new JTextField();
					}
					{
						surname = new JLabel();
						surname.setText("Surname:");
					}
					{
						surnameField = new JTextField();
					}
					{
						address = new JLabel();
						address.setText("Address:");
					}
					{
						addressField = new JTextField();
					}
					{
						addressField2 = new JTextField();
					}
					{
						postalCode = new JLabel();
						postalCode.setText("Postal Code:");
					}
					{
						postalCodeField = new JTextField();
					}
					{
						city = new JLabel();
						city.setText("City:");
					}
					{
						jScrollPane1 = new JScrollPane();
						{
							ListModel yearFieldModel = 
								new DefaultComboBoxModel(
										new String[] {"1900", "1901", "1902", "1903", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2048", "2049", "2050"});
							
							yearField = new JList();
							jScrollPane1.setViewportView(yearField);
							yearField.setModel(yearFieldModel);
							yearField.setPreferredSize(new java.awt.Dimension(98, 4259));
						}
					}
					{
						cityField = new JTextField();
					}
					addPatronLayout.setHorizontalGroup(addPatronLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(addPatronLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(givenNameField, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
						        .addGap(9))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(surnameField, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
						        .addGap(9))
						    .addGroup(addPatronLayout.createSequentialGroup()
						        .addGroup(addPatronLayout.createParallelGroup()
						            .addGroup(addPatronLayout.createSequentialGroup()
						                .addGroup(addPatronLayout.createParallelGroup()
						                    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						                    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                        .addComponent(year, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						                        .addGap(14)))
						                .addGap(19)
						                .addGroup(addPatronLayout.createParallelGroup()
						                    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                        .addComponent(month, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						                        .addGap(65))
						                    .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
						            .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                .addComponent(addPatronTitle, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						                .addGap(23))
						            .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                .addComponent(givenName, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						                .addGap(23))
						            .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                .addComponent(surname, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						                .addGap(23)))
						        .addGap(20)
						        .addGroup(addPatronLayout.createParallelGroup()
						            .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
						            .addComponent(day, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
						    .addComponent(dateofbirth, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(addNewPatron, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(cancelAddPatron, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
						.addGap(24)
						.addGroup(addPatronLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(city, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 94, Short.MAX_VALUE))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(cityField, 0, 325, Short.MAX_VALUE)
						        .addGap(12))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(addressField2, 0, 325, Short.MAX_VALUE)
						        .addGap(12))
						    .addComponent(postalCode, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addGap(0, 0, Short.MAX_VALUE)
						        .addComponent(postalCodeField, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
						        .addGap(12))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(addressField, 0, 325, Short.MAX_VALUE)
						        .addGap(12))
						    .addComponent(address, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 94, Short.MAX_VALUE))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(phoneNumberField, 0, 325, Short.MAX_VALUE)
						        .addGap(12))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(email, 0, 325, Short.MAX_VALUE)
						        .addGap(12))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(emailField, 0, 325, Short.MAX_VALUE)
						        .addGap(12))));
					addPatronLayout.setVerticalGroup(addPatronLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(addPatronTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(addPatronLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(address, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(givenName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(addPatronLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(addressField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(givenNameField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(addPatronLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addGroup(addPatronLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						            .addComponent(addressField2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						            .addComponent(surname, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						        .addGap(18)
						        .addComponent(postalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(postalCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addGap(23)
						        .addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(dateofbirth, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addGroup(addPatronLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						            .addComponent(month, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						            .addComponent(year, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						            .addComponent(day, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(addPatronLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addGroup(addPatronLayout.createParallelGroup()
						            .addComponent(jScrollPane3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						            .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						            .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						                .addGap(16)
						                .addComponent(city, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						                .addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						                .addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						                .addComponent(phoneNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						                .addGap(13)))
						        .addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						        .addGap(29))
						    .addGroup(GroupLayout.Alignment.LEADING, addPatronLayout.createSequentialGroup()
						        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						        .addGroup(addPatronLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						            .addComponent(addNewPatron, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						            .addComponent(emailField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGap(18)
						.addComponent(cancelAddPatron, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(33, Short.MAX_VALUE));
				}
				{
					checkedoutBooks = new JPanel();
					GroupLayout checkedoutBooksLayout = new GroupLayout((JComponent)checkedoutBooks);
					checkedoutBooks.setLayout(checkedoutBooksLayout);
					getContentPane().add(checkedoutBooks, "jPanel2");
					{
						jScrollPane4 = new JScrollPane();
						{
							TableModel listModel = 
								new DefaultTableModel(
										new String[][] { { "One", "Two", "Three", "Four" }, { "Three", "Four", "Five", "Six" } },
										new String[] { "Book ID", "Title", "Status", "Location" });
							list = new JTable();
							jScrollPane4.setViewportView(list);
							list.setModel(listModel);
							list.setOpaque(false);
						}
					}
					{
						checkedoutbookTitle = new JLabel();
						checkedoutbookTitle.setText("A list of all books that have been checked out and their corresponding information");
					}
						checkedoutBooksLayout.setHorizontalGroup(checkedoutBooksLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(checkedoutBooksLayout.createParallelGroup()
						    .addComponent(jScrollPane4, GroupLayout.Alignment.LEADING, 0, 684, Short.MAX_VALUE)
						    .addGroup(checkedoutBooksLayout.createSequentialGroup()
						        .addComponent(checkedoutbookTitle, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap());
						checkedoutBooksLayout.setVerticalGroup(checkedoutBooksLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(checkedoutbookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane4, 0, 418, Short.MAX_VALUE)
						.addContainerGap());
				}
				{
					qrReader = new JPanel();
					getContentPane().add(qrReader, "jPanel2");
				}
			}
			this.setSize(718, 523);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Library Control");
					jMenu3.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							System.out.println("jMenu3.mouseClicked, event="+evt);
							jPanel1.setVisible(false);
							noResultsFound.setVisible(false);
							SearchResults.setVisible(false);
							addLib.setVisible(false);
							masterCheck.setVisible(false);
							libControl.setVisible(true);
							addNewBook.setVisible(false);
							addBookWithGoogle.setVisible(false);
							viewBook.setVisible(false);
							addPatron.setVisible(false);
						}
					});
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
	static String bookID1 = " ";
	static String bookID2 = " ";
	static String bookID3 = " ";
	static String bookID4 = " ";
	static String bookID5 = " ";
	static String bookID6 = " ";
	static String bookID7 = " ";
	static String title1 = " ";
	static String title2 = " ";
	static String title3 = " ";
	static String title4 = " ";
	static String title5 = " ";
	static String title6 = " ";
	static String title7 = " ";
	static String author1 = " ";
	static String author2 = " ";
	static String author3 = " ";
	static String author4 = " ";
	static String author5 = " ";
	static String author6 = " ";
	static String author7 = " ";
	static String genre1 = " ";
	static String genre2 = " ";
	static String genre3 = " ";
	static String genre4 = " ";
	static String genre5 = " ";
	static String genre6 = " ";
	static String genre7 = " ";
	static String isbn1= " ";
	static String isbn2 = " ";
	static String isbn3 = " ";
	static String isbn4 = " ";
	static String isbn5 = " ";
	static String isbn6 = " ";
	static String isbn7 = " ";
	private JButton searchAgain;
	private JLabel noResultsMessage2;
	private JLabel noResultsMessage;
	private JPanel noResultsFound;
	static boolean noresults = false;
	static boolean hideResult1 = true;
	static boolean hideResult2 = true;
	static boolean hideResult3 = true;
	static boolean hideResult4 = true;
	static boolean hideResult5 = true;
	static boolean hideResult6 = true;
	static boolean hideResult7 = true;


	public void begin(String searchInput, String searchType){
		//Resets Search Values
		title1 = " ";
		title2 = " ";
		title3 = " ";
		title4 = " ";
		title5 = " ";
		title6 = " ";
		title7 = " ";
		author1 = " ";
		author2 = " ";
		author3 = " ";
		author4 = " ";
		author5 = " ";
		author6 = " ";
		author7 = " ";
		genre1 = " ";
		genre2 = " ";
		genre3 = " ";
		genre4 = " ";
		genre5 = " ";
		genre6 = " ";
		genre7 = " ";
		isbn1= " ";
		isbn2 = " ";
		isbn3 = " ";
		isbn4 = " ";
		isbn5 = " ";
		isbn6 = " ";
		isbn7 = " ";
		noresults = false;
		hideResult1 = true;
		hideResult2 = true;
		hideResult3 = true;
		hideResult4 = true;
		hideResult5 = true;
		hideResult6 = true;
		hideResult7 = true;
		bookID1 = " ";
		bookID2 = " ";
		bookID3 = " ";
		bookID4 = " ";
		bookID5 = " ";
		bookID6 = " ";
		bookID7 = " ";
		
		int resultnum = 0;
		String strLine;
		ArrayList<String> books = new ArrayList<String>();
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> titleResults = new ArrayList<String>();
		ArrayList<String> authorResults = new ArrayList<String>();
		ArrayList<String> isbnResults = new ArrayList<String>();
		ArrayList<String> genreResults = new ArrayList<String>();
		ArrayList<String> bookIDResults = new ArrayList<String>();
		ArrayList<String> callnumResults = new ArrayList<String>();
		ArrayList<String> notesResults = new ArrayList<String>();
		
		String fileln = ""; 

		boolean searchTitle = false;
		boolean searchAuthor = false;
		boolean searchGenre = false;
		boolean searchIsbn = false;

		if (searchType == "all"){
			searchTitle = true;
			searchAuthor = true;
			searchGenre = true;
			searchIsbn = true;
		} else if (searchType == "genre"){
			searchTitle = false;
			searchAuthor = false;
			searchGenre = true;
			searchIsbn = false;
		} else if (searchType == "isbn"){
			searchTitle = false;
			searchAuthor = false;
			searchGenre = false;
			searchIsbn = true;
		} else if (searchType == "author"){
			searchTitle = false;
			searchAuthor = true;
			searchGenre = false;
			searchIsbn = false;
		} else if (searchType == "title"){
			searchTitle = true;
			searchAuthor = false;
			searchGenre = false;
			searchIsbn = false;
		}

		try { 
			FileInputStream fstream = new FileInputStream("books.xml");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			while (br.ready() == true) {
				strLine = br.readLine();
				books.add(strLine);
			}
			in.close(); // Once the array is loaded, the file is closed
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		int sizeIndex;
		int readLine = -1;
		int nextLine = 0;

		sizeIndex = books.size();
		System.out.println(sizeIndex);

		while (readLine < sizeIndex - 1){
			if (readLine < sizeIndex - 1){
				readLine++;
				fileln = books.get(readLine);
			}

			System.out.println("FILE:" + fileln);
			System.out.println("READLINE"+readLine);
			System.out.println("SIZEINDEX"+ sizeIndex);

			nextLine = readLine + 1;

			searchInput = searchInput.toLowerCase();
			fileln = fileln.toLowerCase();

			if (fileln.contains("<title>") && searchTitle == true){
				fileln = fileln.trim();
				fileln = fileln.replace("<title>", "");
				fileln = fileln.replace("</title>", "");



				if (fileln.contains(searchInput)){
					int getOtherInfo = readLine;


					titleResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<author>", "");
					fileln = fileln.replace("</author>", "");
					authorResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<genre>", "");
					fileln = fileln.replace("</genre>", "");
					genreResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<ISBN>", "");
					fileln = fileln.replace("</ISBN>", "");
					isbnResults.add(fileln);
					
					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<bookID>", "");
					fileln = fileln.replace("</bookID>", "");
					bookIDResults.add(fileln);

					readLine = readLine + 5;
				}
			}

			if (fileln.contains("<author>") && searchAuthor == true){
				fileln = fileln.trim();
				fileln = fileln.replace("<author>", "");
				fileln = fileln.replace("</author>", "");

				if (fileln.contains(searchInput)){
					int getOtherInfo = readLine;

					authorResults.add(fileln);

					getOtherInfo--;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<title>", "");
					fileln = fileln.replace("</title>", "");
					titleResults.add(fileln);

					getOtherInfo = getOtherInfo + 2;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<genre>", "");
					fileln = fileln.replace("</genre>", "");
					genreResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<ISBN>", "");
					fileln = fileln.replace("</ISBN>", "");
					isbnResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<bookID>", "");
					fileln = fileln.replace("</bookID>", "");
					bookIDResults.add(fileln);

					readLine = readLine + 4;
				}


			}

			if (fileln.contains("<genre>") && searchGenre == true){
				fileln = fileln.trim();
				fileln = fileln.replace("<genre>", "");
				fileln = fileln.replace("</genre>", "");				

				if (fileln.contains(searchInput)){
					int getOtherInfo = readLine;

					genreResults.add(fileln);

					getOtherInfo = getOtherInfo - 2;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<title>", "");
					fileln = fileln.replace("</title>", "");
					titleResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<author>", "");
					fileln = fileln.replace("</author>", "");
					authorResults.add(fileln);

					getOtherInfo = getOtherInfo + 2;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<ISBN>", "");
					fileln = fileln.replace("</ISBN>", "");
					isbnResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<bookID>", "");
					fileln = fileln.replace("</bookID>", "");
					bookIDResults.add(fileln);

					readLine = readLine + 2;
				}
			}

			if (fileln.contains("<ISBN>") && searchIsbn == true){
				fileln = fileln.trim();
				fileln = fileln.replace("<ISBN>", "");
				fileln = fileln.replace("</ISBN>", "");				
				if (fileln.contains(searchInput)){
					int getOtherInfo = readLine;

					isbnResults.add(fileln);

					getOtherInfo = getOtherInfo - 3;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<title>", "");
					fileln = fileln.replace("</title>", "");
					titleResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<author>", "");
					fileln = fileln.replace("</author>", "");
					authorResults.add(fileln);

					getOtherInfo++;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<genre>", "");
					fileln = fileln.replace("</genre>", "");
					genreResults.add(fileln);

					getOtherInfo = getOtherInfo +2;
					fileln = books.get(getOtherInfo);
					fileln = fileln.replace("<bookID>", "");
					fileln = fileln.replace("</bookID>", "");
					bookIDResults.add(fileln);
					
					readLine = readLine + 1;
				}
			}



		}



		try {
			titleResults.get(0);
		} catch (Exception e) {
			noresults = true;
		} 

		int titleResultsSize;
		titleResultsSize = titleResults.size();

		if (noresults == false){
			if (titleResultsSize >= 1){
				title1 = "Title: " + titleResults.get(0);
				author1 = "Author: " + authorResults.get(0);
				genre1 = "Genre: " + genreResults.get(0);
				isbn1 = "ISBN: " + isbnResults.get(0);
				bookID1 = bookIDResults.get(0);
				hideResult1 = false;
			}
			if (titleResultsSize >= 2){
				title2 = "Title: " + titleResults.get(1);
				author2 = "Author: " + authorResults.get(1);
				genre2 = "Genre: " + genreResults.get(1);
				isbn2 = "ISBN: " + isbnResults.get(1);
				bookID1 = bookIDResults.get(1);
				hideResult2= false;
			}
			if (titleResultsSize >= 3){
				title3 = "Title: " + titleResults.get(2);
				author3 = "Author: " + authorResults.get(2);
				genre3 = "Genre: " + genreResults.get(2);
				isbn3 = "ISBN: " + isbnResults.get(2);
				bookID1 = bookIDResults.get(2);
				hideResult3 = false;
			}
			if (titleResultsSize >= 4){
				title4 = "Title: " + titleResults.get(3);
				author4 = "Author: " + authorResults.get(3);
				genre4 = "Genre: " + genreResults.get(3);
				isbn4 = "ISBN: " + isbnResults.get(3);
				bookID1 = bookIDResults.get(3);
				hideResult4 = false;
			}

			if (titleResultsSize >= 5){
				title5 = "Title: " + titleResults.get(4);
				author5 = "Author: " + authorResults.get(4);
				genre5 = "Genre: " + genreResults.get(4);
				isbn5 = "ISBN: " + isbnResults.get(4);
				bookID5 = bookIDResults.get(4);
				hideResult5 = false;
			}
			if (titleResultsSize >= 6){
				title6 = "Title: " + titleResults.get(5);
				author6 = "Author: " + authorResults.get(5);
				genre6 = "Genre: " + genreResults.get(5);
				isbn6 = "ISBN: " + isbnResults.get(5);
				bookID6 = bookIDResults.get(5);
				hideResult6 = false;
			}
			if (titleResultsSize >= 7){
				title7 = "Title: " + titleResults.get(6);
				author7 = "Author: " + authorResults.get(6);
				genre7 = "Genre: " + genreResults.get(6);
				isbn7 = "ISBN: " + isbnResults.get(6);
				bookID7 = bookIDResults.get(6);
				hideResult7 = false;
			}
		}
	}
	
	public JLabel getNotesHeader() {
		return notesHeader;
	}

}



/**
 * This demonstrates password-based encryption (PBE) using the
 * DES algorithm.  As mentioned before, DES is not very secure,
 * but the SDK version used for this example does not support
 * other (stronger) PBE algorithms
 *
 * The text file "clear.txt" will be read, encrypted and written back
 * as "clear.txt.des".
 *
 * I've done all the "hardcoding" of the file names and password for
 * simplicity of discussion.
 *
 */



class security {
	static boolean passwordFalse = false;
	private static String filename;
	private static String password;
	private static FileInputStream inFile;
	private static FileOutputStream outFile;
	static String lineString = "";

	/**
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */




	public void encrypt(String filename, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException{
		// File to encrypt.  It does not have to be a text file!

		System.out.println("Encryption has been initiated and is taking place...");

		// Password must be at least 8 characters (bytes) long

		inFile = new FileInputStream(filename);
		outFile = new FileOutputStream(filename + ".des");

		// Use PBEKeySpec to create a key based on a password.
		// The password is passed as a character array

		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory =
			SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey passwordKey = keyFactory.generateSecret(keySpec);

		// PBE = hashing + symmetric encryption.  A 64 bit random
		// number (the salt) is added to the password and hashed
		// using a Message Digest Algorithm (MD5 in this example.).
		// The number of times the password is hashed is determined
		// by the interation count.  Adding a random number and
		// hashing multiple times enlarges the key space.

		byte[] salt = new byte[8];
		Random rnd = new Random();
		rnd.nextBytes(salt);
		int iterations = 100;

		//Create the parameter spec for this salt and interation count

		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);

		// Create the cipher and initialize it for encryption.

		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		cipher.init(Cipher.ENCRYPT_MODE, passwordKey, parameterSpec);

		// Need to write the salt to the (encrypted) file.  The
		// salt is needed when reconstructing the key for decryption.

		outFile.write(salt);

		// Read the file and encrypt its bytes.

		byte[] input = new byte[64];
		int bytesRead;
		while ((bytesRead = inFile.read(input)) != -1)
		{
			byte[] output = cipher.update(input, 0, bytesRead);
			if (output != null) outFile.write(output);
		}

		byte[] output = cipher.doFinal();
		if (output != null) outFile.write(output);

		inFile.close();
		outFile.flush();
		outFile.close();
	}

	public void decrypt(String filename, String password){
		// File to decrypt.

		System.out.println("Decrption has been initiated and is taking place...");
		
		try {
			
			inFile = new FileInputStream(filename);
			outFile = new FileOutputStream(filename + ".dcr");

			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory keyFactory =
				SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey passwordKey = keyFactory.generateSecret(keySpec);

			// Read in the previouly stored salt and set the iteration count.

			byte[] salt = new byte[8];
			inFile.read(salt);
			int iterations = 100;

			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, iterations);

			// Create the cipher and initialize it for decryption.

			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, passwordKey, parameterSpec);


			byte[] input = new byte[64];
			int bytesRead;
			while ((bytesRead = inFile.read(input)) != -1)
			{
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null)
					outFile.write(output);
			}

			byte[] output = cipher.doFinal();
			if (output != null)
				outFile.write(output);

			inFile.close();
			outFile.flush();
			outFile.close();
		} catch (InvalidKeyException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			
			e.printStackTrace();
		} catch (BadPaddingException e) {
			passwordFalse = true;
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void deleteFile(String fileName) {
	    // A File object to represent the filename
	    File f = new File(fileName);

	    // Make sure the file or directory exists and isn't protected
	    if (!f.exists()) throw new IllegalArgumentException("Delete: no such file or directory: " + fileName);

	    if (!f.canWrite()) throw new IllegalArgumentException("Delete: write protected: " + fileName);

	    // If it is a directory, make sure it is empty
	    if (f.isDirectory()) {
	      String[] files = f.list();
	      if (files.length > 0) throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
	    }

	    // Attempt to delete it
	    boolean success = f.delete();

	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
	  }
	
	public void rename(String oldName, String newName) throws IOException {

	    // Construct the file object. Does NOT create a file on disk!
	    File f = new File(oldName); // backup of this source file.

	    // Rename the backup file to "junk.dat"
	    // Renaming requires a File object for the target.
	    f.renameTo(new File(newName));
	  }
	
	public String reader(String filename, int line) throws IOException{
		   Scanner input = null;
		  
		  lineString = "";
		   
		   try {
	           input = new Scanner(new BufferedReader(new FileReader(filename)));
	           
	           int currentLine = 1;
	           
	           while (input.hasNext() && line != currentLine) {
	               currentLine++;
	        	   lineString = input.nextLine();
	           }
	      } finally {
	           if (input != null) {
	               input.close();
	           }
	      }
	   
		return lineString;
	}

	public void write(String filename, String stringtoWrite) {
	        try{
	        														// Create file 
	      FileWriter fstream = new FileWriter(filename,true);
	          BufferedWriter out = new BufferedWriter(fstream);
	      out.write(stringtoWrite);
	      															//Close the output stream
	      out.close();
	      }catch (Exception e){										//Catch exception if any
	        System.err.println("Error: " + e.getMessage());
	      }
	    }


}



class BooksClient {
	
	public static final ArrayList<String> googleResults = new ArrayList<String>();
	
	protected BooksClient() {}

	private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static final String BOOKS_GDATA_SERVER = "http://books.google.com";

	public static final String VOLUMES_FEED = BOOKS_GDATA_SERVER + "/books/feeds/volumes";

	public static final String USER_LIBRARY_FEED = BOOKS_GDATA_SERVER + "/books/feeds/users/me/collections/library/volumes";

	public static final String USER_ANNOTATION_FEED = BOOKS_GDATA_SERVER + "/books/feeds/users/me/volumes";

	public void searchVolumes(BooksService service,boolean authenticated, String searchTerms)throws IOException, ServiceException {

		VolumeQuery query = new VolumeQuery(new URL(VOLUMES_FEED));

		query.setFullTextQuery(searchTerms);

		printUnderlined("Running Search for '" + searchTerms + "'");
		VolumeFeed volumeFeed = service.query(query, VolumeFeed.class);
		printVolumeFeed(volumeFeed);

		if (authenticated) {
			
			System.out.println("You're outta luck b/c I will never authenticate you!");
			//handleSearchVolumes(service, volumeFeed);
		}
	}
	private void printVolumeFeed(VolumeFeed volumeFeed)throws IOException, ServiceException {
		String title = volumeFeed.getTitle().getPlainText();
		System.out.println(title);

		List<VolumeEntry> volumeEntries = volumeFeed.getEntries();
		if (volumeEntries.size() == 0) {
			googleResults.add("This feed contains no entries.");
			return;
		}
		System.out.println("Results " + volumeFeed.getStartIndex() + " - " + (volumeFeed.getStartIndex() + volumeEntries.size() - 1) + " of " + volumeFeed.getTotalResults());
		System.out.println();

		int count = 1;
		for (VolumeEntry entry : volumeEntries) {
			googleResults.add(String.valueOf(count));
			printVolumeEntry(entry);
			count++;
		}
		System.out.println();
	}

private static void printVolumeEntry(VolumeEntry entry) throws IOException,
ServiceException {
	googleResults.add("Title: ");
	for (Title t : entry.getTitles()) {
		googleResults.add(t.getValue() + " ");
		System.out.println(t.getValue());
	}
	googleResults.add("Author: ");
	for (Creator c : entry.getCreators()) {
		googleResults.add(c.getValue() + " ");
		System.out.println(c.getValue());
	}
	if (entry.hasRating()) {
		System.out.println("Rating: " + entry.getRating().getAverage());
	}
	if (entry.hasReview()) {
		System.out.println("Review: " + entry.getReview().getValue());
	}
	boolean firstLabel = true;
	if (entry.getCategories().size() > 0) {
		for (Category c : entry.getCategories()) {
			if (c.getScheme() == BooksCategory.Scheme.LABELS_SCHEME) {
				if (firstLabel) {
					System.out.print("Labels: ");
					firstLabel = false;
				}
				System.out.print(c.getTerm() + "\t");
			}
		}
		if (!firstLabel) {
			System.out.println();
		}
	}
	if (entry.hasViewability()) {
		System.out.println("Viewability: " + entry.getViewability().getValue());
	}
	System.out.println();
}

private static void printUnderlined(String stringToUnderline) {
    System.out.println(stringToUnderline);
    for (int i = 0; i < stringToUnderline.length(); ++i) {
      System.out.print("-");
    }
    System.out.println("\n");
  }


}

