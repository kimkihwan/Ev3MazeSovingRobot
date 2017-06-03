package robot2;


import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.hardware.*;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

public class hello {
	RemoteEV3 ev3;
	static RMIRegulatedMotor motorA;
	static RMIRegulatedMotor motorB;
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		//Creates the remote ev3
		RemoteEV3 ev3=new RemoteEV3("10.0.1.1");
		ev3.isLocal();
		
		//gets ports
		ev3.getPort("A");
		ev3.getPort("B");
		
		//creates motors
		if(motorA==null){motorA = ev3.createRegulatedMotor("A", 'L');}
		if(motorB==null){motorB = ev3.createRegulatedMotor("B", 'L');}
		
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
		
		//close ports. Must be completed every time.
		motorB.close();
		motorA.close();
		
	}

}

