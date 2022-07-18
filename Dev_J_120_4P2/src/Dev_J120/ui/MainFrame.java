
package Dev_J120.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Dev_J120.Utils;
import Dev_J120.lists.ClientListOfCompany;
import Dev_J120.lists.ClientListOfPerson;
import Dev_J120.lists.FileService;
import Dev_J120.models.PersonInfo;
import Dev_J120.models.CompanyInfo;
import Dev_J120.models.PhoneNumber;

public class MainFrame extends JFrame{
    
    private final PersonClientListTableModel personTableModel = new PersonClientListTableModel();
    private final JTable personsTable = new JTable();	
    private final PersonClientDialog personDialog = new PersonClientDialog(this);
    
    private final CompanyClientListTableModel companyTableModel = new CompanyClientListTableModel();
    private final JTable companyTable = new JTable();
    private final CompanyClientDialog companyDialog = new CompanyClientDialog(this);
    
    private final FileService fs = new FileService();
    
    private final Font font1 = new Font("Dialog", Font.BOLD, 13);
    
    
    public MainFrame() {
        super("Two-type list of clients");        
        initTabbedPanel();
        startApp();
        closeApp();		
        setBounds(50, 100, 880, 500);	   
	}
    
    private void tuningTables(){
        Font font = new Font("Times new roman", 0, 16);
        personsTable.setModel(personTableModel);
        companyTable.setModel(companyTableModel); 
	personsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        companyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        personsTable.setRowHeight(27);
        companyTable.setRowHeight(27); 
        personsTable.setFont(font);
        companyTable.setFont(font); 
        Utils.alignCenter(personsTable, 0);
        Utils.alignCenter(personsTable, 3);
        Utils.alignCenter(personsTable, 4);
        Utils.alignCenter(companyTable, 0);
        Utils.alignCenter(companyTable, 5);
        Utils.setJTableColumnsWidth(personsTable, 900, 25, 40, 75, 25, 20);
        Utils.setJTableColumnsWidth(companyTable, 900, 19, 25, 30, 30, 30, 21);
        personsTable.getTableHeader().setFont(font1);
        companyTable.getTableHeader().setFont(font1);
    }
    private void initTabbedPanel(){
        tuningTables();
   
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel[] panels = new JPanel[2];
        for(int i =0; i<panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new BorderLayout());
            tabbedPane.addTab((i%2!=1)? "Persons": "Сompanies", panels[i]);
            tabbedPane.setMnemonicAt(i, (i%2!=1)? KeyEvent.VK_P : KeyEvent.VK_C);
            tabbedPane.setFont(font1);
            add(tabbedPane);
            }
        initPersonMenu(panels[0]);
        initCompanyMenu(panels[1]); 
        panels[0].add(new JScrollPane(personsTable), BorderLayout.CENTER);
        panels[1].add(new JScrollPane(companyTable), BorderLayout.CENTER);
    }

    private void initPersonMenu(Container container) {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu operations = new JMenu("Operations for persons");
        operations.setFont(font1);
        operations.setBorder(BorderFactory.createEtchedBorder());
        operations.setMnemonic('O');
        menuBar.add(operations);
        addMenuItemTo(operations, "<html><font size=\"4\">Add</font>", 'A',
                KeyStroke.getKeyStroke('A', InputEvent.ALT_DOWN_MASK),
                e -> addPersonClient());

        addMenuItemTo(operations, "<html><font size=\"4\">Change</font>", 'C',
                KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK),
                e -> changePersonClient());

        addMenuItemTo(operations, "<html><font size=\"4\">Delete</font>", 'D',
                KeyStroke.getKeyStroke('D', InputEvent.ALT_DOWN_MASK),
                e -> delPersonClient());

        container.add(menuBar, BorderLayout.NORTH);
	}
    
    private void initCompanyMenu(Container container) {
        
        JMenuBar menuBar = new JMenuBar();
        JMenu operations = new JMenu("Operations for companies");
        operations.setFont(font1);
        operations.setBorder(BorderFactory.createEtchedBorder());
        operations.setMnemonic('O');
        menuBar.add(operations);

        addMenuItemTo(operations, "<html><font size=\"4\">Add</font>", 'A',
                KeyStroke.getKeyStroke('A', InputEvent.ALT_DOWN_MASK),
                e -> addCompanyClient());

        addMenuItemTo(operations, "<html><font size=\"4\">Change</font>", 'C',
                KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK),
                e -> changeCompanyClient());

        addMenuItemTo(operations, "<html><font size=\"4\">Delete</font>", 'D',
                KeyStroke.getKeyStroke('D', InputEvent.ALT_DOWN_MASK),
                e -> delCompanyClient());

        container.add(menuBar, BorderLayout.NORTH);
	}
    
    private void addMenuItemTo(JMenu parent, String text, char mnemonic,
                               KeyStroke accelerator, ActionListener al) {
        
        JMenuItem mi = new JMenuItem(text, mnemonic);
        mi.setAccelerator(accelerator);
        mi.addActionListener(al);
        parent.add(mi);
    }
    
    private void startApp(){
        
        addWindowListener(new WindowAdapter() { 
            @Override
        public void windowOpened(WindowEvent e) {
            
            Map<String, List<String>> sourceMap;
            List<String> sourcePersonList;
            List<String> sourceCompanyList;
            try{
                sourceMap = fs.extractClientsFromFile();
                         
                sourcePersonList = sourceMap.get(FileService.getURL_PERSON());
                sourceCompanyList = sourceMap.get(FileService.getURL_COMPANY());
            
                sourcePersonList.forEach(x -> {
                String[] temp = x.split("\u0009");
                personTableModel.addPersonClient(new PhoneNumber(temp[0], temp[1]), 
                                                 temp[2], temp[3], temp[4], temp[5]);
                });
                sourceCompanyList.forEach(x -> {
                String[] temp = x.split("\u0009");
                companyTableModel.addCompanyClient(new PhoneNumber(temp[0], temp[1]), temp[2], 
                                                   temp[3], temp[4], temp[5], temp[6]); 
                }); 
                }            
            catch (IOException ex) {
                   JOptionPane.showMessageDialog(e.getWindow(), 
                   "The data source file is missing or reading from it is impossible. The data will not be uploaded",
                   "Error. An error occurred while reading the file.", JOptionPane.ERROR_MESSAGE);
                }
            catch(NullPointerException npe){}                
        }
      });
    }
    
    private void closeApp(){
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane
                        .showConfirmDialog(e.getWindow(), "Closing the app? Are you sure?",
                                "Confirmation of closing", JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                if (n == 0) {
                    List<PersonInfo> personList = ClientListOfPerson.getPersonInstance().getPersonClientsList();
                    List<CompanyInfo> companyList = ClientListOfCompany.getCompanyInstance().getCompanyClientsList();
                    try { 
                        fs.saveClientsToFile(personList, companyList);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(e.getWindow(), 
                                "An error occurred while writing the file. Table Data may be lost.", "Error. The application will be stopped.",
			JOptionPane.ERROR_MESSAGE);
                    }
                    e.getWindow().setVisible(false);
                    System.exit(0);
                }
                else
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
    }
    
    private void addPersonClient() {
	personDialog.prepareForAdd();
	while(personDialog.showModal()) {
	try {
	    PhoneNumber pn = new PhoneNumber(personDialog.getAreaCode(), personDialog.getPhoneNum());
	    personTableModel.addPersonClient(pn, personDialog.getPersonClientName(), personDialog.getPersonClientAddr(), 
                                             personDialog.getPersonClientDateOfBirth());
		return;
	    } catch(Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "Person registration error",
						  JOptionPane.ERROR_MESSAGE);
			}
		}
	}
    private void addCompanyClient() {
	companyDialog.prepareForAdd();
	while(companyDialog.showModal()) {
	try {
	    PhoneNumber pn = new PhoneNumber(companyDialog.getAreaCode(), companyDialog.getPhoneNum());
	    companyTableModel.addCompanyClient(pn, companyDialog.getCompanyClientName(), companyDialog.getCompanyClientAddr(), 
                                          companyDialog.getCompanyDirectorName(), companyDialog.getCompanyContactPersonName());
		return;
	    } catch(Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "Company registration error",
						  JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
    private void changePersonClient() {
	int seldRow = personsTable.getSelectedRow();
	if(seldRow == -1)
	    return;
		
	PersonInfo ci = personTableModel.getPersonClient(seldRow);
	personDialog.prepareForChange(ci);
        try {
	if(personDialog.showModal()) {
	    ci.setName(personDialog.getPersonClientName());
	    ci.setAddress(personDialog.getPersonClientAddr());
            ci.setDateOfBirth(personDialog.getPersonClientDateOfBirth()); 
            ci.setPersonAge(ci.ageCalculator(ci.getDateOfBirth()));  
	    personTableModel.personClientChanged(seldRow);   
             }      
	   }
        catch (Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error changing person data.",
						  JOptionPane.ERROR_MESSAGE);
			}
	}
    private void changeCompanyClient() {
	int seldRow = companyTable.getSelectedRow();
	if(seldRow == -1)
	    return;
		
	CompanyInfo ci = companyTableModel.getCompanyClient(seldRow);
	companyDialog.prepareForChange(ci);
        try {
	if(companyDialog.showModal()) {
	    ci.setCompanyName(companyDialog.getCompanyClientName());
	    ci.setCompanyAddress(companyDialog.getCompanyClientAddr());
            ci.setCompanyDirectorName(companyDialog.getCompanyDirectorName()); 
            ci.setContactPersonName(companyDialog.getCompanyContactPersonName()); 
	    companyTableModel.companyClientChanged(seldRow); 
	                               }
           }
        catch (Exception ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error changing company data.",
						  JOptionPane.ERROR_MESSAGE);
			      }
    }
	
    private void delPersonClient() {
	int seldRow = personsTable.getSelectedRow();
	if(seldRow == -1)
	    return;
		
	PersonInfo ci = personTableModel.getPersonClient(seldRow);
	if(JOptionPane.showConfirmDialog(this, 
		"Are you sure you want to delete Person\n"
		+ "with phone number " + ci.getPhoneNumber() + "?", 
		"Delete confirmation", JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		personTableModel.dropPersonClient(seldRow);
		}
	}
    private void delCompanyClient() {
	int seldRow = companyTable.getSelectedRow();
	if(seldRow == -1)
	    return;
		
	       CompanyInfo ci = companyTableModel.getCompanyClient(seldRow);
	if(JOptionPane.showConfirmDialog(this, 
		"Are you sure you want to delete Company\n"
		+ "with phone number " + ci.getPhoneNumber() + "?", 
		"Delete confirmation", JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                companyTableModel.dropCompanyClient(seldRow); 
		}
	}
}
