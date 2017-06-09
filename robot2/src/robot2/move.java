package robot2;


import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.port.SensorPort;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.PublishFilter;

import java.io.IOException;
public class move {
	RemoteEV3 ev3;
	static RMIRegulatedMotor motorA;
	static RMIRegulatedMotor motorB;
	
	public static void main(String[] args) throws IOException, RemoteException, MalformedURLException, NotBoundException {
		//Creates the remote ev3
		RemoteEV3 ev3=new RemoteEV3("10.0.1.1");

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
		
		//Light2
		NXTLightSensor lightSensor2 = new NXTLightSensor(SensorPort.S1);
		SensorMode light2;
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
		int mov = 0;
		while(!Button.ESCAPE.isDown()) {
			cnt++;
			motorA.forward();
			motorB.forward();
			if(cnt==50)
			{
				/*
				//150정도면 한칸움직임
				motorA.stop(false);
				motorB.stop(false);
				*/
				LCD.scroll();
				light = lightSensor.getRedMode();
				float[] sample1 = new float[light.sampleSize()];
				light.fetchSample(sample1, 0);
				bright = (float)sample1[0];
				
				light2 = lightSensor2.getRedMode();
				float[] sample2 = new float[light.sampleSize()];
				light2.fetchSample(sample2, 0);
				bright2 = (float)sample2[0];
				
				sonar.fetchSample(sample4, 0);
				distance = sample4[0];
				LCD.drawString(distance + " \n", 5, 5);
				if(distance<0.04||distance>10000)
				{
					motorA.stop(false);
					motorB.stop(false);
					color = colorSensor.getColorIDMode();
					float[] sample3 = new float[color.sampleSize()];
					color.fetchSample(sample3, 0);
					colorID = (int)sample3[0];
					colorName = "";
					switch(colorID){
					case -1: colorName = "NONE"; break;
					case 0: colorName = "Red";
										motorB.stop(false);motorA.stop(false);motorA.rotate(510);motorB.rotate(-510);break;
					case 1: colorName = "Green"; break;
					case 2: colorName = "Blue";
										motorB.stop(false);motorA.stop(false);motorA.rotate(-510);motorB.rotate(510);break;
					case 3: colorName = "Yellow";
										motorB.stop(false);motorA.stop(false);motorA.rotate(510);motorB.rotate(-510);
										for(mov=0;mov<150;mov++){motorA.forward();motorB.forward();}
										motorB.stop(false);motorA.stop(false);motorA.rotate(-510);motorB.rotate(510);
										for(mov=0;mov<150;mov++){motorA.forward();motorB.forward();}
										motorB.stop(false);motorA.stop(false);
										break;
					case 6: colorName = "White"; break;
					case 7: colorName = "Black"; LCD.drawString("FINISH I'm Solved!", 5, 5); break;
					case 13:colorName = "Brown"; break;
					}
					LCD.drawString(colorID + " - " + colorName + " \n", 5, 5);
				}
				else if(distance<0.3)
				{
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
				
				/*
				if(bright>=0.35){
					motorA.rotate(-10);
					motorB.rotate(10);
				}
				else{
					motorB.forward();
					motorA.forward();
				}
				*/
				cnt = 0;		
				//LCD.drawString(bright + " \n" + bright2 + " \n", 1, 1);
			}
		}
		
		/*		
		//lets move motors
		motorB.forward();
		motorA.forward();
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		motorB.stop(true);
		motorA.stop(true);
		
		//beep because its nice to know we are done
		Sound.twoBeeps();
		*/
		//close ports. Must be completed every time.
		motorB.close();
		motorA.close();
		colorSensor.close();
		lightSensor.close();
		sonarSensor.close();
	}

}