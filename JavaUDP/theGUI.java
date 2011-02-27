package ArduinoUDP;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class theGUI extends JPanel {
	public InetAddress IPAddress;
	public int port;
	
	public theGUI(){
		UdpSettings settings = new UdpSettings();
		lightsRemote lightsRM = new lightsRemote();
		blindsController blindsRM= new blindsController();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		add(settings, gbc);
		gbc.gridy = 1;
		add(lightsRM, gbc);
		gbc.gridy = 2;
		add(blindsRM, gbc);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Arduino Controller");
		frame.add(new theGUI());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);		
	}

}