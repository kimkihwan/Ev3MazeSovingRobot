package robot2;

import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class Ultrasonicsensor extends AbstractFilter{
	
	float[] sample;
	public Ultrasonicsensor (SampleProvider source){
		super(source);
		sample = new float[sampleSize];	
	}
	public boolean get_Distance(){
		super.fetchSample(sample,0);
		if(sample[0]<0.4){
			return true;
		}
		else{
			return false;
		}	
	}	
}
