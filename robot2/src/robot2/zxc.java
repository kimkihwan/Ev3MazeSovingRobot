package robot2;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.NXTConnection;

import java.io.*;

public class zxc {
	public static void main(String args[]) throws IOException{
			byte command = 0;
			int i=1;
			//int[] path = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
			//int[] path2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
			LCD.drawString("Waiting!\n", 4, 4);
			LCD.scroll();
			NXTConnection btc = Bluetooth.getNXTCommConnector().waitForConnection(0, NXTConnection.RAW);
			Sound.beep();
			LCD.drawString("Connect success!\n", 4, 4);
			LCD.scroll();
			
			DataInputStream input = btc.openDataInputStream();
			DataOutputStream output = btc.openDataOutputStream();
			while(true){
			    try{
			       command = input.readByte();
				}catch(IOException e){
				   System.exit(1);
				}
			    
				LCD.drawString(command + " \n", 4, 4);
				if(Button.waitForAnyPress()==1){
					LCD.scroll();
					//for(i=0;i<16;i++)
					output.writeByte(i);
					//output.writeByte(i);
					//path2[i]+=10;
					//output.writeInt(path2[i]);
					//LCD.drawString(path[i]+ " and "+ path2[i] + " send\n", 4, 4);
					LCD.drawString(Integer.toString(i)+" send\n", 4, 4);
					LCD.scroll();
					i++;
					output.flush();
					if(Button.waitForAnyPress()==2)
						break;
				}
			}//while
			input.close();
			output.close();
			btc.close();
	}//main
}//class	

