package initialpkg;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import processing.core.*;


public class CenterPanel extends PApplet {
	
	float red, green, blue;
	double intensity;

	
	static String input, secondaryInput;
	ArrayList<String> timezones = StormView.returnNonRepeats(StormController.getSortedTimezone(), "timezone");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int paneWidth = (int) screenSize.getWidth();
	int paneHeight = (int) screenSize.getHeight();
	
	double amountOfStorms;
	int stateMax;
	ArrayList<String> uniqueState = StormView.returnNonRepeats(StormController.getSortedState(), "state");

	public void setup(){
		
		size((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.8));
		background(255);
		
		
		
	}
	
	
	
	public void draw(){
	
		
		background(255);
		fill(200);
		ellipseMode(CENTER);
		textAlign(CENTER);
		ArrayList<String> uniqueState = StormView.returnNonRepeats(StormController.getSortedState(), "state");
		double interval = (screenSize.getWidth() * 0.9)/timezones.size();
		double intervalEllipse = (screenSize.getWidth() * 0.9)/(uniqueState.size()/4);
		int xcordEllipse = (int) (intervalEllipse) - 50;
		int ycordEllipse = 100;
		
		for (int i = 0; i <uniqueState.size(); i++){
			fill(200);
			ellipse(xcordEllipse, ycordEllipse, 50, 50);
			text((uniqueState.get(i).substring(0, 4)), xcordEllipse, ycordEllipse - 30);
			fill(255);
			text(StormController.getTotalStormsPerState().get(i), xcordEllipse, ycordEllipse+2);
			xcordEllipse += intervalEllipse;
			if (xcordEllipse > (intervalEllipse * (uniqueState.size() / 4))){
				xcordEllipse = (int) (intervalEllipse) - 50;
				ycordEllipse += intervalEllipse;
			}
		}
		
		
		fill(100);
		int xcord = (int) (interval/2);
		for (int i = 0; i < timezones.size(); i++){
			text(timezones.get(i), xcord, 20);
			xcord += interval;
		}

			
			
		
	}
	
	void setColor(double intensityValue){
		blue = (float) 0;
		if (0 <= intensityValue && intensityValue < 0.5){
			green = (float) 255;
			red = (float) (2 * intensityValue * 255);
		}
		if (0.5 <= intensityValue && intensityValue <= 1){
			red = (float) 255;
			green = (float) (1 - 2 * (intensityValue - 0.5)) * 255;
		}
		
	}
	
	static void setInput(String inputChange){
		input = inputChange.toLowerCase();
	}
	
	static void setInput2(String inputChange){
		secondaryInput = inputChange.toLowerCase();
	}

	
}
