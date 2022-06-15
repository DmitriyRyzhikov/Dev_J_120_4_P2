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

import Dev_J120.models.CompanyInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Dialog window for person client attributes entering/editing.
 */
public class CompanyClientDialog extends JDialog {
	private final JTextField areaCodeField;
	private final JTextField phoneNumField;
	private final JTextField companyNameField;
	private final JTextField companyAddrField;
        private final JTextField companyDirectorField;
        private final JTextField companyContactPersonField; 
	
	/**
	 * Set to {@code true}, when a user closes the dialog with "OK" button. 
	 * This field value is returned by {@link #showModal} method.
	 */
	private boolean okPressed;
	
	/**
	 * Constructs the dialog.
	 * 
	 * @param owner dialog window parent container
	 */
	public CompanyClientDialog(JFrame owner) {
		super(owner, true);
		
		areaCodeField = new JTextField(3);
		phoneNumField = new JTextField(5);
		companyNameField = new JTextField(30);
		companyAddrField = new JTextField(30);
                companyDirectorField = new JTextField(20);
                companyContactPersonField = new JTextField(20);
		
		initLayout();
		
		setResizable(false);
	}

	/**
	 * Initializes dialog layout and puts required controls onto the dialog.
	 */
	private void initLayout() {
		initControls();
		initOkCancelButtons();
	}

	/**
	 * Creates layout for client information input fields and adds the fields
	 * to the dialog controls hierarchy.
	 */
	private void initControls() {
		JPanel controlsPane = new JPanel(null);
		controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));
		
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lbl = new JLabel("Phone number (");
		lbl.setLabelFor(areaCodeField);
		p.add(lbl);
		p.add(areaCodeField);
		lbl = new JLabel(")");
		lbl.setLabelFor(phoneNumField);
		p.add(lbl);
		p.add(phoneNumField);
		controlsPane.add(p);
		
		p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbl = new JLabel("Company name");
		lbl.setLabelFor(companyNameField);
		p.add(lbl);
		p.add(companyNameField);
		controlsPane.add(p);
		
		p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbl = new JLabel("Company address");
		lbl.setLabelFor(companyAddrField);
		p.add(lbl);
		p.add(companyAddrField);
		controlsPane.add(p);
                
                p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbl = new JLabel("Company director");
		lbl.setLabelFor(companyDirectorField);
		p.add(lbl);
		p.add(companyDirectorField);
		controlsPane.add(p);
                
                p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbl = new JLabel("Company contact person");
		lbl.setLabelFor(companyContactPersonField);
		p.add(lbl);
		p.add(companyContactPersonField);
		controlsPane.add(p);
		
		add(controlsPane, BorderLayout.CENTER);
	}

	/**
	 * Creates bottom panel with "OK" and "Cancel" buttons.
	 */
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
		getRootPane()
			.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), esc);
		getRootPane()
			.getActionMap()
			.put(esc, cancelDialogAction);
	}
	
	/**
	 * Shows dialog, and returns, when the user finishes his work with the dialog.
	 * Returns {@code true}, if the user closed the dialog with "OK" button, 
	 * {@code false} otherwise. 
	 * 
	 * @return {@code true}, if a user closed the dialog with "OK" button,
	 * 		{@code false} otherwise (with "Cancel" button, or with system close options).
	 */
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

	/**
	 * Prepares dialog for entering of data about new person client.
	 * Clears all dialog controls; enables phone number fields.
	 */
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
	
	/**
	 * Prepares dialog for editing of information about person client.
	 * Fills dialog controls with data from provided client information object;
	 * disables phone number fields.
	 * 
	 * @param ci client information used to fill dialog controls
	 */
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
	
	/**
	 * Returns value of area code entered by a user.
	 */
	public String getAreaCode() {
		return areaCodeField.getText();
	}
	
	/**
	 * Returns value of phone local number entered by a user.
	 */
	public String getPhoneNum() {
		return phoneNumField.getText();
	}
	
	/**
	 * Returns value of client name entered by a user.
	 */
	public String getCompanyClientName() {
		return companyNameField.getText();
	}
	
	/**
	 * Returns value of client address entered by a user.
	 */
	public String getCompanyClientAddr() {
		return companyAddrField.getText();
	}
        /**
	 * Returns value of client address entered by a user.
  
	 */
	public String getCompanyDirectorName() {
		return companyDirectorField.getText();
	}
        public String getCompanyContactPersonName() {
		return companyContactPersonField.getText();
	}
}
