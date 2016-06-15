package initialpkg;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import processing.core.*;


public class CenterPanel extends PApplet {
	
	float red, green, blue;
	double max;
	double intensity;
	int ellipseCordY = 100;
	int direction = 1;
	int speed = 2;
	
	static String input;
	ArrayList<String> timezones = StormView.returnNonRepeats(StormController.getSortedTimezone(), "timezone");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int paneWidth = (int) screenSize.getWidth();
	int paneHeight = (int) screenSize.getHeight();

	public void setup(){
		
		size((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.8));
		background(255);
		
		System.out.println((screenSize.getWidth() * 0.9) + " , " + (int) (screenSize.getHeight() * 0.8));
		
	}
	
	
	
	public void draw(){
		background(255);
		fill(200);
		ellipseMode(CENTER);
		
		
		
		if (ellipseCordY >= paneHeight - 100 || ellipseCordY <= 100){
			direction = -1;
		}
		
		if (input == "timezonesketch"){
			ellipseCordY += speed * direction;
			double interval = (screenSize.getWidth() * 0.9)/timezones.size();
			fill(100);
			int xcord = (int) (interval/2);
			for (int i = 0; i < timezones.size(); i++){
				text(timezones.get(i), xcord, 20);
				xcord += interval;
			}
			
			ellipse(100, ellipseCordY, 50, 50);
			
		} else {
			
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

	
}
