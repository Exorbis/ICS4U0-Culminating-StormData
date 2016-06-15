package initialpkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LowerPanel extends CenterPanel {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	float red, green, blue;
	double maxDmg, maxMag;
	double intensity, magIntensity;


	
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



	public void draw(){

		
		
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
	
	
	
}
