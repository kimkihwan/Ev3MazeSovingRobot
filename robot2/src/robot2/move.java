package robot2;

import java.net.MalformedURLException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.Bluetooth;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.PublishFilter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class move {
	RemoteEV3 ev3;
	static RMIRegulatedMotor motorA;
	static RMIRegulatedMotor motorB;
	static int[] path=new int[4650007];
	static int p=0;
	
	static NXTConnection btc = Bluetooth.getNXTCommConnector().waitForConnection(0, NXTConnection.RAW);
	static DataInputStream input = btc.openDataInputStream();
	static DataOutputStream output = btc.openDataOutputStream();
	static int command=0;
	public static void main(String[] args) throws RemoteException, MalformedURLException, IOException, NotBoundException {
		mov();	
	}
	
	static void mov() throws IOException, RemoteException, MalformedURLException, NotBoundException {
		//Creates the remote ev3
		RemoteEV3 ev3=new RemoteEV3("10.0.1.1");
		Sound.beep();
		LCD.drawString("Waiting!\n", 4, 4);
		//gets ports
		ev3.getPort("A");
		ev3.getPort("B");
		
		//creates motors
		if(motorA==null){motorA = ev3.createRegulatedMotor("A", 'L');}
		if(motorB==null){motorB = ev3.createRegulatedMotor("B", 'L');}
		motorA.setSpeed(180);
		motorB.setSpeed(180);
		//Light1
		NXTLightSensor lightSensor = new NXTLightSensor(SensorPort.S3);
		SensorMode light;
		float bright=0;
		float bright2=0;

		//Sonar
		float frequency = 1;
		EV3UltrasonicSensor sonarSensor = new EV3UltrasonicSensor(SensorPort.S2);
		SampleProvider sonar = new PublishFilter(sonarSensor.getDistanceMode(), "Ultrasonic readings", frequency);
		float[] sample4 = new float[sonar.sampleSize()];
		float distance=0;
		
		//Color
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
		SensorMode color;
		int colorID;
		String colorName = "";
		int i;
		int cnt = 0;
		int k=1;
		int rot = 2;
		int cnt_p=0;
		int speed_flag=1;//original speed = 1, lower speed = 2
		
		try{
		    command = input.readByte();
			}catch(IOException e){
			   System.exit(1);
		}
		if(command==1)
		{
			while(!Button.ESCAPE.isDown()) {
				cnt++;
	            cnt_p++;
	            if(cnt==50)
				{
	            	command = input.available();
	                if(command==2)
	                {
	                	motorA.stop(true);
	                	motorB.stop(true);
	                }
	                else
	                {
	                	//150정도면 한칸움직임
						LCD.scroll();
						light = lightSensor.getRedMode();
						float[] sample1 = new float[light.sampleSize()];
						light.fetchSample(sample1, 0);
						bright = (float)sample1[0];
						
						sonar.fetchSample(sample4, 0);
						distance = sample4[0];
						//LCD.drawString(distance + " \n", 5, 5);
						if(distance<0.03||distance>10000)
						{
							motorA.stop(true);
							motorB.stop(true);
							color = colorSensor.getColorIDMode();
							float[] sample3 = new float[color.sampleSize()];
							color.fetchSample(sample3, 0);
							colorID = (int)sample3[0];
							colorName = "";
							switch(colorID){
							case -1: colorName = "NONE"; break;
							case 0: colorName = "Red";
												path[p]=2;
												speed_flag=1;
												motorA.setSpeed(180);
												motorB.setSpeed(180);
												motorB.stop(true);motorA.stop(true);
												motorA.rotate(510,true);motorB.rotate(-510);
												for(i=0;i<400;i++){motorA.forward();motorB.forward();}
												motorB.stop(true);motorA.stop(true);
												break;//left rotate
							case 1: colorName = "Green"; break;
							case 2: colorName = "Blue";
												path[p]=3;
												motorB.stop(true);motorA.stop(true);
												motorA.rotate(-510,true);motorB.rotate(510);
												speed_flag=1;
												motorA.setSpeed(180);
												motorB.setSpeed(180);
												for(i=0;i<400;i++){motorA.forward();motorB.forward();}
												motorB.stop(true);motorA.stop(true);
												break;//right rotate
							case 3: colorName = "Yellow";
												path[p]=4;
												motorB.stop(true);motorA.stop(true);motorA.rotate(510,true);motorB.rotate(-510);//left rotate
												speed_flag=1;
												motorA.setSpeed(180);
												motorB.setSpeed(180);
												for(i=0;i<400;i++){motorA.forward();motorB.forward();}
												motorB.stop(true);motorA.stop(true);motorA.rotate(-510,true);motorB.rotate(510);//right rotate
												for(i=0;i<400;i++){motorA.forward();motorB.forward();}
												motorB.stop(true);motorA.stop(true);
												break;
							case 6: colorName = "White"; break;
							case 7: colorName = "Black"; LCD.clearDisplay();LCD.drawString("FINISH I'm Solved!", 1, 1); break;
							case 13:colorName = "Brown"; break;
							}
							LCD.drawString(colorID + " - " + colorName + " \n", 5, 5);
							if(colorID==7)
							{
								motorB.close();
								motorA.close();
								colorSensor.close();
								lightSensor.close();
								sonarSensor.close();
								Delay.msDelay(2000);
								break;
							}
						}
						else if(distance<0.3)
						{
							speed_flag=2;
							motorA.setSpeed(80);
							motorB.setSpeed(80);
							motorA.forward();
							motorB.forward();
						}
						else
						{
							motorA.forward();
							motorB.forward();
						}
						
						
						if(bright>=0.35){
							for(i=0;i<rot;i++){
								motorA.rotate(-20*k,true);
								motorB.rotate(20*k);
								light = lightSensor.getRedMode();
								light.fetchSample(sample1, 0);
								bright2 = (float)sample1[0];
								if(bright2<0.35)
								{
									motorB.forward();
									motorA.forward();
									rot=2;
									break;
								}
							}
							rot*=2;
							k*=-1;
							
						}
						else{
							path[p] = 1;
							motorB.forward();
							motorA.forward();
							k=1;
						}
						
						cnt = 0;		
						//LCD.drawString(bright + " \n" + bright2 + " \n", 1, 1);
	                }//else command==1,3
				}//cnt==50
				if(speed_flag==1){
					cnt_p++;
				}
				if(cnt_p==5650){//cnt_p = beep five times in original speed, 11 times in lower speed.
					Sound.beep();
					LCD.drawString(path[p] + " send\n", 4, 4);
					output.writeByte(path[p]);
					output.flush();
					p++;
					cnt_p=0;
				}
			}//while
		}//command==1
		input.close();
		output.close();
		motorB.close();
		motorA.close();
		colorSensor.close();
		lightSensor.close();
		sonarSensor.close();
	}

}