package ArduinoUDP;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class blindsController extends JPanel implements ActionListener{
	SendUDP udpSender = new SendUDP();
	
	JButton minBtn = new JButton();
	JButton maxBtn = new JButton();
	JSlider blindsSlider = new JSlider(JSlider.HORIZONTAL, 0,9,1);
	ChangeListener changeListener = new	ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			String msg = "2" + blindsSlider.getValue();
			udpSender.sendUDPString(msg);
		}
	};
	
	public blindsController(){
		minBtn.addActionListener(this); maxBtn.addActionListener(this);
		blindsSlider.addChangeListener(changeListener);
		setLayout(new GridBagLayout());
		GridBagConstraints bcg = new GridBagConstraints();
		
		bcg.gridx = 0;
		bcg.gridy = 0;
		add(minBtn, bcg);
		bcg.gridx = 1;
		add(blindsSlider, bcg);	
		bcg.gridx = 2;
		add(maxBtn, bcg);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == minBtn){
			blindsSlider.setValue(0);
		} else if(e.getSource() == maxBtn){
			blindsSlider.setValue(10);
		}
		
	}
}