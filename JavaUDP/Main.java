package ArduinoUDP;

import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.explodingpixels.macwidgets.HudWindow;

public class Main extends JPanel implements PropertyChangeListener,ActionListener{
	public InetAddress IPAddress;
	public int port;
	private HudWindow settingsFrame;
	private UdpSettings settings;
	KeyListener listener;
	static JFrame frame;
	JMenuBar menuBar;
	JMenuItem menuBarEditItem;
	JMenu editMenu;
	
	public Main(){
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
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
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
		menuBarEditItem = new JMenuItem("Settings");
		menuBarEditItem.addActionListener(this);
		editMenu.add(menuBarEditItem);
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
		// TODO Auto-generated method stub
		if(e.getSource() == menuBarEditItem) settingsFrame.getJDialog().setVisible(true);
	}

	

}