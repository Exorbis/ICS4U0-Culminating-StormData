package initialpkg;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;


/**
 * This panel is the bottom of the StormView client which displays the circles based off the total damages in the month (color) and the 
 * severity of the magnitude of the storm (size) 
 * LowerPanel extends CenterPanel as it inherits the properties from super including the PApplet attribute
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 15, 2016
 * @version 1.1
 *
 */
//This uses integrated processing features from parent 
public class LowerPanel extends CenterPanel {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	float red, green, blue;
	double maxDmg, maxMag;
	double intensity, magIntensity;


	/**
	 * This is the default constructor that is initialized when running the StormView client
	 * It displays the lower panel containing the circles based off color and size dependent on the data
	 */
	public void setup(){
		
		size((int) (screenSize.getWidth() * 0.9), (int) ((screenSize.getHeight() * 0.8)*0.2));
		background(255);

		maxDmg = Collections.max(StormController.getTotalDmgMonths());
		maxMag = Collections.max(StormController.getTotalMagnitudeMonths());

		fill(200);
		ellipseMode(CENTER);
		textAlign(CENTER);
		textSize(15);
		float spacing = (float) ((screenSize.getWidth() * 0.9)/12);
		float xcord = spacing/2;
		
		for (int i = 0; i < 12; i++){
			intensity = (StormController.getTotalDmgMonths().get(i)) / maxDmg;
			magIntensity = (StormController.getTotalMagnitudeMonths().get(i)) / maxMag;
			if (magIntensity == 0.0){
				magIntensity = 0.1;
			}
			setColor(intensity);
			fill(this.red, this.green, this.blue);
			ellipse(xcord, height/2, (float) magIntensity * 100, (float) magIntensity * 100);
			fill(0);
			text(months[i], xcord, height - 20);
			xcord += spacing;
			
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
