package robot2;
/*
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.remote.ev3.RemoteEV3;
import lejos.hardware.sensor.EV3ColorSensor;


import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.RegulatedMotor;



import lejos.utility.Stopwatch;

import java.util.Random;
*/
import lejos.hardware.BrickFinder; 
import lejos.hardware.Keys; 
import lejos.hardware.ev3.EV3; 
import lejos.hardware.lcd.LCD; 
import lejos.hardware.lcd.TextLCD; 
import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.motor.Motor; 
import lejos.hardware.port.MotorPort; 
import lejos.hardware.port.SensorPort; 
import lejos.hardware.sensor.EV3ColorSensor; 
import lejos.hardware.sensor.EV3TouchSensor; 
import lejos.hardware.sensor.SensorMode;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.Color; 
import lejos.robotics.RegulatedMotor; 
import lejos.robotics.Touch; 
import lejos.utility.Delay; 
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class temp2 {
	RemoteEV3 ev3;
	static int cnt = 0;
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		RemoteEV3 ev3=new RemoteEV3("10.0.1.1");
		//EV3 ev3 = (EV3) BrickFinder.getLocal();
		//TextLCD lcd = ev3.getTextLCD();
//		Keys keys = ev3.getKeys();

		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
		SensorMode color;
		int colorId;
		String colorName;
		while(true){
			cnt++;
			if(cnt==20)
			{
				color = colorSensor.getColorIDMode();
				float[] sample = new float[color.sampleSize()];
				color.fetchSample(sample, 0);
				colorId = (int)sample[0];
				colorName = "";
				//System.out.print("\n\n\n\n");
				//System.out.println(colorId);
				//if(colorId==7)
					
				
				switch(colorId){
					case Color.NONE: colorName = "NONE"; break;//-1
					case Color.BLACK: colorName = "BLACK"; break;//7
					case Color.BLUE: colorName = "BLUE"; break;//2
					case Color.GREEN: colorName = "GREEN"; break;//1
					case Color.YELLOW: colorName = "YELLOW"; break;//3
					case Color.RED: colorName = "RED"; break;//0
					case Color.WHITE: colorName = "WHITE"; break;//6
					case Color.BROWN: colorName = "BROWN"; break;//13
				}
				LCD.drawString(colorId + " - " + colorName + " \n ", 0, 0);
				cnt = 0;
			}
//			if(keys.waitForAnyPress()==1)
//				break;
		}
		//keys.waitForAnyPress();
		//colorSensor.close();

	}

}
