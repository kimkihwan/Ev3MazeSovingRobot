package robot2;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class Colorsensor extends AbstractFilter{
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	
	float[] sample;
	public Colorsensor (SampleProvider source){
		super(source);
		sample = new float[sampleSize];
		
	}
	public boolean get_color(){
		super.fetchSample(sample,0);
		if(sample[0]>=0.35){
			return WHITE;
		}
		else{
			return BLACK;
		}
		
	}
	
	
}
