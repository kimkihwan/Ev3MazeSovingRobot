package robot2;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
//import lejos.hardware.Sound;

//import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTLightSensor;
//import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.Port;
//import lejos.hardware.port.SensorPort;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

public class hello {
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	
	RemoteEV3 ev3;
	static RMIRegulatedMotor motorA;
	static RMIRegulatedMotor motorB;

	static Lightsensor light;
	static Colorsensor color;
	//static Sonarsensor sonar;
	static Brick b;
	
	static Port s2;
	static Port s3;
	static Port s4;
	
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		//Creates the remote ev3
		RemoteEV3 ev3=new RemoteEV3("10.0.1.1");
		
		ev3.isLocal();
		
		init(ev3);
		
		light();
		
	}

	public static void init(RemoteEV3 ev3) {	
		//gets ports
		ev3.getPort("A");
		ev3.getPort("B");
		
		//creates motors
		if(motorA==null){motorA = ev3.createRegulatedMotor("A", 'L');}
		if(motorB==null){motorB = ev3.createRegulatedMotor("B", 'L');}
		
		b = BrickFinder.getDefault();
		
		s2 = b.getPort("S2");
		s3 = b.getPort("S3");
		s4 = b.getPort("S4");
		
		//EV3UltrasonicSensor EV3sonar = new EV3UltrasonicSensor(s2); 
		NXTLightSensor NXTlight = new NXTLightSensor(s3);
		EV3ColorSensor EV3color = new EV3ColorSensor(s4);
		
	//	sonar = new Sonarsensor(EV3sonar);
		light = new Lightsensor(NXTlight);
		color = new Colorsensor(EV3color);
	}
	
	public static void light() throws RemoteException {
		if(light.get_light()==BLACK) {
			motorB.forward();
			motorA.forward();
		}
		else if(light.get_light()==WHITE) {
			motorB.stop(true);
			motorA.stop(true);
		}
	}
	
}

