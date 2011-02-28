package ArduinoUDP;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.explodingpixels.macwidgets.HudWidgetFactory;

public class UdpSettings extends JPanel implements ActionListener{
	SendUDP udpSender = new SendUDP();
	private Preferences prefs = Preferences.userRoot().node("UDP");
	JPanel upperPanel = new JPanel();
	JPanel lowerPanel = new JPanel();
	byte[] defIp = {(byte) 127,(byte) 0,(byte) 0,(byte) 1};
	byte[] ip = prefs.getByteArray("ip", null);
	JTextField ipField1 = HudWidgetFactory.createHudTextField(ip[0]+"");
	JTextField ipField2 = HudWidgetFactory.createHudTextField(ip[1]+"");
	JTextField ipField3 = HudWidgetFactory.createHudTextField(ip[2]+"");
	JTextField ipField4 = HudWidgetFactory.createHudTextField(ip[3]+"");
	JTextField portField = HudWidgetFactory.createHudTextField(prefs.getInt("port", 8888)+"");
	JLabel pointLbl1 = HudWidgetFactory.createHudLabel(".");
	JLabel pointLbl2 = HudWidgetFactory.createHudLabel(".");
	JLabel pointLbl3 = HudWidgetFactory.createHudLabel(".");
	JLabel portLbl = HudWidgetFactory.createHudLabel(":");
	JButton saveBtn = HudWidgetFactory.createHudButton("Save");
	JButton cancelBtn = HudWidgetFactory.createHudButton("Cancel");
	
	private PropertyChangeSupport pcs;
	public final static String VIEW_SETTINGS = "true";
	
	public UdpSettings(){
		prefs = Preferences.userRoot().node("UDP");
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		saveBtn.setText("Save");
		cancelBtn.setText("Cancel");
		
		pcs = new PropertyChangeSupport(this);
		
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
				pcs.firePropertyChange(VIEW_SETTINGS, "true", "false");
				
			} else if(e.getSource() == cancelBtn){
				pcs.firePropertyChange(VIEW_SETTINGS, "true", "false");
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have not filled all the fields", "Something bad happend!", JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean fieldsFilled(){
		return (ipField1.getText() != "" || ipField2.getText() != "" || ipField3.getText() != "" || ipField4.getText() != "");
	}
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

}