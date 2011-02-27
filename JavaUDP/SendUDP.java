package ArduinoUDP;

import java.io.*;
import java.net.*;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

public class SendUDP {
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;
	private byte[] sendData = new byte[1024];
	private int port;
	private Preferences prefs;
	
	public void sendUDPString(String sentence){
		sendData = sentence.getBytes();
		prefs = Preferences.userRoot().node("UDP");
		if(prefs.getBoolean("prefsSet", false)){
			byte[] defIp = {(byte) 127,(byte) 0,(byte) 0,(byte) 1};
			try {
				IPAddress = InetAddress.getByAddress(prefs.getByteArray("ip", defIp ));
			} catch (UnknownHostException e1) {
				// TODO catch it
			}
			port = prefs.getInt("port", 8888);
			//JOptionPane.showMessageDialog(null, IPAddress.toString() + ":"+ port,"", JOptionPane.ERROR_MESSAGE);
			
		try {
			
			clientSocket = new DatagramSocket();
		
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			clientSocket.send(sendPacket);
			clientSocket.close();
		} catch (SocketException e) { 
			//TODO catch it
		} catch (IOException e) { 
			//TODO catch it
		}
		}
	}
	public void setIP(byte[] IP){
		try {
			IPAddress = InetAddress.getByAddress(IP.toString(),IP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setPort(int newPort){
		port = newPort;
	}
}
