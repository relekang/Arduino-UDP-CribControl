package ArduinoUDP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class lightsRemote extends JPanel implements ActionListener{
	SendUDP udpSender = new SendUDP();
	JButton onBtn = new JButton();
	JButton offBtn = new JButton();
	public lightsRemote(){
		onBtn.setText("Lights on");
		offBtn.setText("Lights off");
		
		onBtn.addActionListener(this);
		offBtn.addActionListener(this);
		
		add(onBtn);
		add(offBtn);
	}
	
	public void actionPerformed(ActionEvent e){
			if(e.getSource() == onBtn){
				//TODO 
				udpSender.sendUDPString("11");
			} else if(e.getSource() == offBtn){
				//TODO 
				udpSender.sendUDPString("10");
			}

	}
}
