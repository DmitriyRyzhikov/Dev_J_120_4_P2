package Dev_J120.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import Dev_J120.models.PersonInfo;

public class PersonClientDialog extends JDialog {
    private final JTextField areaCodeField;
    private final JTextField phoneNumField;
    private final JTextField clientNameField;
    private final JTextField clientAddrField;
    private JFormattedTextField clientBirthDayField = null; 

    private boolean okPressed;

    public PersonClientDialog(JFrame owner) {
	super(owner, true);
		
	areaCodeField = new JTextField(3);
	phoneNumField = new JTextField(5);
	clientNameField = new JTextField(30);
	clientAddrField = new JTextField(30);
        MaskFormatter mask;
            try {
                mask = new MaskFormatter("##.##.####");
                mask.setPlaceholderCharacter('0');
                clientBirthDayField = new JFormattedTextField(mask); 
            } catch (ParseException ex) {
                Logger.getLogger(PersonClientDialog.class.getName()).log(Level.SEVERE, null, ex);
            }                              		
	initLayout();		
	setResizable(false);
    }

    private void initLayout() {
	initControls();
	initOkCancelButtons();
    }

    private void initControls() {
	JPanel controlsPane = new JPanel(null);
	controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));
        Font f0 = new Font("Arial", 0, 16);
        Font f1 = new Font("Arial", 1, 16);
		
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel lbl = new JLabel("Phone number (");
        lbl.setFont(f1);
        areaCodeField.setFont(f0);
	lbl.setLabelFor(areaCodeField);
	p.add(lbl);
	p.add(areaCodeField);
	lbl = new JLabel(")");
        lbl.setFont(f1);
        phoneNumField.setFont(f0);
	lbl.setLabelFor(phoneNumField);
	p.add(lbl);
	p.add(phoneNumField);
	controlsPane.add(p);
		
	p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	lbl = new JLabel("Person name");
        lbl.setFont(f1);
        clientNameField.setFont(f0);
	lbl.setLabelFor(clientNameField);
	p.add(lbl);
	p.add(clientNameField);
	controlsPane.add(p);
		
	p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	lbl = new JLabel("Person address");
        lbl.setFont(f1);
        clientAddrField.setFont(f0);
	lbl.setLabelFor(clientAddrField);
	p.add(lbl);
	p.add(clientAddrField);
	controlsPane.add(p);
                
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	lbl = new JLabel("Person date of birth");
        lbl.setFont(f1);
        clientBirthDayField.setPreferredSize(new Dimension(90, 25)); 
        clientBirthDayField.setFont(f0); 
	lbl.setLabelFor(clientBirthDayField);
	p.add(lbl);
	p.add(clientBirthDayField);
	controlsPane.add(p);
		
	add(controlsPane, BorderLayout.CENTER);
    }

    private void initOkCancelButtons() {
	JPanel btnsPane = new JPanel();
	
	JButton okBtn = new JButton("OK");
	okBtn.addActionListener(e -> {
	    okPressed = true;
	    setVisible(false);
	    });
	okBtn.setDefaultCapable(true);
	btnsPane.add(okBtn);
		
	Action cancelDialogAction = new AbstractAction("Cancel") {
	        @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
	    }
	}; 
		
	JButton cancelBtn = new JButton(cancelDialogAction);
	btnsPane.add(cancelBtn);
		
	add(btnsPane, BorderLayout.SOUTH);
		
	final String esc = "escape";
	getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
		     .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), esc);
	getRootPane().getActionMap().put(esc, cancelDialogAction);
    }

    public boolean showModal() {
	pack();
	setLocationRelativeTo(getOwner());
	if(areaCodeField.isEnabled())
	    areaCodeField.requestFocusInWindow();
	else
	    clientNameField.requestFocusInWindow();
	okPressed = false;
	setVisible(true);
	return okPressed;
    }

    public void prepareForAdd() {
	setTitle("New person client registration");
		
	areaCodeField.setText("");
	phoneNumField.setText("");
	clientNameField.setText("");
	clientAddrField.setText("");
        clientBirthDayField.setText("");
		
	areaCodeField.setEnabled(true);
	phoneNumField.setEnabled(true);
    }

    public void prepareForChange(PersonInfo ci) {
	setTitle("Person client properties change");
		
	areaCodeField.setText(ci.getPhoneNumber().getAreaCode());
	phoneNumField.setText(ci.getPhoneNumber().getLocalNum());
	clientNameField.setText(ci.getName());
	clientAddrField.setText(ci.getAddress());
        clientBirthDayField.setText(ci.getDateOfBirth());  
		
	areaCodeField.setEnabled(false);
	phoneNumField.setEnabled(false);
    }

    public String getAreaCode() {
	return areaCodeField.getText();
    }

    public String getPhoneNum() {
	return phoneNumField.getText();
    }

    public String getPersonClientName() {
	return clientNameField.getText();
    }

    public String getPersonClientAddr() {
        return clientAddrField.getText();
    }

    public String getPersonClientDateOfBirth() {
	return clientBirthDayField.getText();
    }
}
