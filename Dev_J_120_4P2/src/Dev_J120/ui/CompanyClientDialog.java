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
import java.awt.Font;

import Dev_J120.models.CompanyInfo;

public class CompanyClientDialog extends JDialog {
    private final JTextField areaCodeField;
    private final JTextField phoneNumField;
    private final JTextField companyNameField;
    private final JTextField companyAddrField;
    private final JTextField companyDirectorField;
    private final JTextField companyContactPersonField; 

    private boolean okPressed;

    public CompanyClientDialog(JFrame owner) {
	super(owner, true);
		
	areaCodeField = new JTextField(3);
	phoneNumField = new JTextField(5);
	companyNameField = new JTextField(34);
	companyAddrField = new JTextField(33);
        companyDirectorField = new JTextField(33);
        companyContactPersonField = new JTextField(29);
		
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
	lbl = new JLabel("Company name");
        lbl.setFont(f1);
        companyNameField.setFont(f0); 
	lbl.setLabelFor(companyNameField);
	p.add(lbl);
	p.add(companyNameField);
	controlsPane.add(p);
		
	p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	lbl = new JLabel("Company address");
        lbl.setFont(f1);
        companyAddrField.setFont(f0); 
	lbl.setLabelFor(companyAddrField);
	p.add(lbl);
	p.add(companyAddrField);
	controlsPane.add(p);
                
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	lbl = new JLabel("Company director");
        lbl.setFont(f1);
        companyDirectorField.setFont(f0); 
	lbl.setLabelFor(companyDirectorField);
	p.add(lbl);
	p.add(companyDirectorField);
	controlsPane.add(p);
                
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	lbl = new JLabel("Company contact person");
        lbl.setFont(f1);
        companyContactPersonField.setFont(f0); 
	lbl.setLabelFor(companyContactPersonField);
	p.add(lbl);
	p.add(companyContactPersonField);
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
	   companyNameField.requestFocusInWindow();
	okPressed = false;
	setVisible(true);
	return okPressed;
    }

    public void prepareForAdd() {
	setTitle("New company client registration");
		
	areaCodeField.setText("");
	phoneNumField.setText("");
	companyNameField.setText("");
	companyAddrField.setText("");
        companyDirectorField.setText("");
        companyContactPersonField.setText("");
		
	areaCodeField.setEnabled(true);
	phoneNumField.setEnabled(true);
    }

    public void prepareForChange(CompanyInfo ci) {
	setTitle("Company client properties change");
		
	areaCodeField.setText(ci.getPhoneNumber().getAreaCode());
	phoneNumField.setText(ci.getPhoneNumber().getLocalNum());
	companyNameField.setText(ci.getCompanyName());
	companyAddrField.setText(ci.getCompanyAddress());
        companyDirectorField.setText(ci.getCompanyDirectorName());
        companyContactPersonField.setText(ci.getContactPersonName());
		
	areaCodeField.setEnabled(false);
	phoneNumField.setEnabled(false);
    }

    public String getAreaCode() {
	return areaCodeField.getText();
    }

    public String getPhoneNum() {
	return phoneNumField.getText();
    }

    public String getCompanyClientName() {
	return companyNameField.getText();
    }

    public String getCompanyClientAddr() {
	return companyAddrField.getText();
    }

    public String getCompanyDirectorName() {
	 return companyDirectorField.getText();
    }
        
    public String getCompanyContactPersonName() {
	return companyContactPersonField.getText();
    }
}
