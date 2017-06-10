package robot2;

import lejos.hardware.Bluetooth;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.NXTConnection;
import java.io.*;

public class zxc {
	public static void main(String args[]){
			char command = 'a';
			LCD.drawString("Waiting!\n", 4, 4);
			LCD.scroll();
			NXTConnection btc = Bluetooth.getNXTCommConnector().waitForConnection(0, NXTConnection.RAW);
			Sound.beep();
			LCD.drawString("Connect success!\n", 4, 4);
			LCD.scroll();
			DataInputStream dis = btc.openDataInputStream();
			while(true){
			    try{
			        command = dis.readChar();
					}catch(IOException e){
					    System.exit(1);
					}
			    LCD.drawString(command + " \n", 5, 5);
				LCD.scroll();
				}//while
		}//main
}//class	

