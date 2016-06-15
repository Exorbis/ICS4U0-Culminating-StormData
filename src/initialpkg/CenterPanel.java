package initialpkg;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import processing.core.*;


/**
 * This panel is the CenterPanel (read: center panel) for the StormView client GUI. It operates as the a display that holds circles of all the states (uniquely) present in the storm data
 * Within each circle contains the amount of times there has been a storm in that state/territory/zone of any kind 
 * It also acts as the super for LowerPanel in order to pass its primary method as well as the PApplet functionality
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 15, 2016
 * @version 1.1
 *
 */
public class CenterPanel extends PApplet {
	
	float red, green, blue;
	double intensity;

	
	ArrayList<String> timezones = StormView.returnNonRepeats(StormController.getSortedTimezone(), "timezone");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int paneWidth = (int) screenSize.getWidth();
	int paneHeight = (int) screenSize.getHeight();

	ArrayList<String> uniqueState = StormView.returnNonRepeats(StormController.getSortedState(), "state");

	/**
	 * This is the initialization on first running of the sketch, which includes the size of the panel and background color
	 */
	public void setup(){
		
		size((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.8));
		background(255);
		
		
		
	}
	
	
	/**
	 * This method allows for the ongoing illustration of the circles and other provided visuals
	 * This holds the center "state" circles and the time zones that are present in the file data
	 */
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
	
	/**
	 * This method takes in a decimal percentage in order to create a color gradient based on how intense (1 being the most -> the max -> and 0 being the least)
	 * A shade of red is set on the high end, and a shade of green is set on the low end
	 * 
	 * @param intensityValue is the decimal percentage of the inputed value that is used to create a gradient based on the value
	 */
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


	
}
