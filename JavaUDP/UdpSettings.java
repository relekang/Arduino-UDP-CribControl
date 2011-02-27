package ArduinoUDP;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UdpSettings extends JPanel implements ActionListener{
	SendUDP udpSender = new SendUDP();
	private Preferences prefs;
	JPanel upperPanel = new JPanel();
	JPanel lowerPanel = new JPanel();
	JTextField ipField1 = new JTextField();
	JTextField ipField2 = new JTextField();
	JTextField ipField3 = new JTextField();
	JTextField ipField4 = new JTextField();
	JTextField portField = new JTextField();
	JLabel pointLbl1 = new JLabel(".");
	JLabel pointLbl2 = new JLabel(".");
	JLabel pointLbl3 = new JLabel(".");
	JLabel portLbl = new JLabel(":");
	JButton saveBtn = new JButton();
	JButton cancelBtn = new JButton();
	
	public UdpSettings(){
		prefs = Preferences.userRoot().node("UDP");
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		saveBtn.setText("Save");
		cancelBtn.setText("Cancel");
		
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		ipField1.setColumns(2);
		ipField2.setColumns(2);
		ipField3.setColumns(2);
		ipField4.setColumns(2);
		portField.setColumns(4);
		
		upperPanel.add(ipField1);	upperPanel.add(pointLbl1);
		upperPanel.add(ipField2);	upperPanel.add(pointLbl2);
		upperPanel.add(ipField3);	upperPanel.add(pointLbl3);
		upperPanel.add(ipField4);	upperPanel.add(portLbl);
		upperPanel.add(portField);
		lowerPanel.add(saveBtn); 
		lowerPanel.add(cancelBtn);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(upperPanel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(lowerPanel, c);
	}
	
	public void actionPerformed(ActionEvent e){
		if(fieldsFilled()){
			if(e.getSource() == saveBtn){
				//TODO
				byte ip[] = {(byte) Integer.parseInt(ipField1.getText()), (byte) Integer.parseInt(ipField2.getText()), (byte) Integer.parseInt(ipField3.getText()), (byte) Integer.parseInt(ipField4.getText())};
				int port = Integer.parseInt(portField.getText());
				prefs.putByteArray("ip", ip);
				prefs.putInt("port", port);
				prefs.putBoolean("prefsSet", true);
			} else if(e.getSource() == cancelBtn){
				//TODO
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have not filled all the fields", "Something bad happend!", JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean fieldsFilled(){
		return (ipField1.getText() != "" || ipField2.getText() != "" || ipField3.getText() != "" || ipField4.getText() != "");
	}
	

}