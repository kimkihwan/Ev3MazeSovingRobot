package robot2;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.RegulatedMotor;



import lejos.utility.Stopwatch;

import java.util.Random;

public class temp3 {
	
}
/*	RemoteEV3 ev3;
	static Port s2;
	static Brick b;
    public static void main(String [] args) throws InterruptedException {
    	b = BrickFinder.getDefault();
        //gets ports
      	ev3.getPort("A");
      	ev3.getPort("B");
    	s2 = b.getPort("S2");
    	Ultrasonicsensor ultra = new Ultrasonicsensor(s2);
      	//creates motors
      	if(motorA==null){motorA = ev3.createRegulatedMotor("A", 'L');}
      	if(motorB==null){motorB = ev3.createRegulatedMotor("B", 'L');}
        
      	
      	
      	for (int i = 0; i < 5; i++) {

            // No initial motor movement (because it did nothing anyway)

            // We change this to approach from either direction.
            while (ultra.get_Distance() != 30) {
                // Check whether it's behind or ahead of it.
                // Assuming that B- and C- increase distance, and B+ and C+ decrease it (depends on robot configuration).
                // This is called a feedback loop, by the way.
                if (ultra.get_Distance() < 30) { // Move forward (distance+)
                    Motor.B.backward();
                    Motor.C.backward();
                } else { // Move backward (distance-)
                    Motor.B.forward();
                    Motor.C.forward();
                }
            }

            // We only get here when the distance is right, so stop the motors.
            motorA.stop();
            motorB.stop();               

            LCD.clear();
            LCD.drawString("Distance : "+ultra.getDistance(), 0, 0);
        }
        Button.waitForAnyPress();
    }
}*/