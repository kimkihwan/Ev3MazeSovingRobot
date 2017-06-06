package robot2;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class Colorsensor extends AbstractFilter{
	float[] sample;
	public Colorsensor (SampleProvider source){
		super(source);
		sample = new float[sampleSize];
	}
	public String get_color(){
		super.fetchSample(sample,0);
		String colorName = "";
		switch((int)sample[0]){
			case -1: colorName = "NONE"; break;//-1
			case 7: colorName = "BLACK"; break;//7
			case 2: colorName = "BLUE"; break;//2
			case 1: colorName = "GREEN"; break;//1
			case 3: colorName = "YELLOW"; break;//3
			case 0: colorName = "RED"; break;//0
			case 6: colorName = "WHITE"; break;//6
			case 13: colorName = "BROWN"; break;//13
		}
		return colorName;
	}
}
