package ArduinoUDP;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.explodingpixels.macwidgets.HudWindow;

public class Main extends JPanel implements PropertyChangeListener,ActionListener{
	SendUDP sendUdp;
	public InetAddress IPAddress;
	public int port;
	private HudWindow settingsFrame;
	private UdpSettings settings;
	KeyListener listener;
	static JFrame frame;
	JMenuBar menuBar;
	JMenu editMenu,lightsMenu,blinderMenu;
	JMenuItem editSettingsMenuItem, lightsOnMenuItem,lightsOffMenuItem,blinder0MenuItem,blinder5MenuItem,blinder9MenuItem;
	
	public Main(){
		sendUdp = new SendUDP();
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		menuBar = new JMenuBar();
		createMenuBar();
		settingsFrame = new HudWindow("Settings");
		settings = new UdpSettings();
		settings.addPropertyChangeListener(this);
		lightsRemote lightsRM = new lightsRemote();
		blindsController blindsRM = new blindsController();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		add(lightsRM, gbc);
		gbc.gridy = 1;
		add(blindsRM, gbc);
		listener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				JOptionPane lol = new JOptionPane(e.getKeyCode());
				lol.setVisible(true);
			}
			
			@Override public void keyReleased(KeyEvent e) { }
			@Override public void keyPressed(KeyEvent e) { }
		};
		addKeyListener(listener);
		setFocusable(true);
		
		
		settingsFrame.setContentPane(settings);
		settingsFrame.getJDialog().pack();
		settingsFrame.getJDialog().setLocation(400, 20);
	}

	private void createMenuBar() {
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		editSettingsMenuItem = new JMenuItem("Settings");
		editSettingsMenuItem.addActionListener(this);
		editMenu.add(editSettingsMenuItem);
		lightsMenu = new JMenu("Lights");
		menuBar.add(lightsMenu);
		lightsOnMenuItem = new JMenuItem("On");
		lightsOnMenuItem.addActionListener(this);
		lightsMenu.add(lightsOnMenuItem);
		lightsOffMenuItem = new JMenuItem("Off");
		lightsOffMenuItem.addActionListener(this);
		lightsMenu.add(lightsOffMenuItem);
		
		blinderMenu = new JMenu("Blinds");
		menuBar.add(blinderMenu);
		blinder0MenuItem = new JMenuItem("0%");
		blinder0MenuItem.addActionListener(this);
		blinderMenu.add(blinder0MenuItem);
		blinder5MenuItem = new JMenuItem("50%");
		blinder5MenuItem.addActionListener(this);
		blinderMenu.add(blinder5MenuItem);
		blinder9MenuItem = new JMenuItem("90%");
		blinder9MenuItem.addActionListener(this);
		blinderMenu.add(blinder9MenuItem);
		frame.setJMenuBar(menuBar);
		
	}
	

	public static void main(String[] args) {
		frame = new JFrame("Arduino Controller");
		frame.add(new Main());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == UdpSettings.VIEW_SETTINGS); settingsFrame.getJDialog().setVisible(Boolean.parseBoolean(evt.getNewValue().toString()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == editSettingsMenuItem) settingsFrame.getJDialog().setVisible(true);
		else if(e.getSource() == lightsOnMenuItem) sendUdp.sendUDPString("11");
		else if(e.getSource() == lightsOffMenuItem) sendUdp.sendUDPString("10");
		else if(e.getSource() == blinder0MenuItem) sendUdp.sendUDPString("20");
		else if(e.getSource() == blinder5MenuItem) sendUdp.sendUDPString("25");
		else if(e.getSource() == blinder9MenuItem) sendUdp.sendUDPString("29");
	}

	

}