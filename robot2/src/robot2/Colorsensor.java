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
	public string get_color(){
		super.fetchSample(sample,0);
		string colorName = "";
		switch((int)sample[0]){
			case Color.NONE: colorName = "NONE"; break;//-1
			case Color.BLACK: colorName = "BLACK"; break;//7
			case Color.BLUE: colorName = "BLUE"; break;//2
			case Color.GREEN: colorName = "GREEN"; break;//1
			case Color.YELLOW: colorName = "YELLOW"; break;//3
			case Color.RED: colorName = "RED"; break;//0
			case Color.WHITE: colorName = "WHITE"; break;//6
			case Color.BROWN: colorName = "BROWN"; break;//13
		}
		return colorName;
	}
	
	
}
