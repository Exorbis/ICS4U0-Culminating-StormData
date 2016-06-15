package initialpkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class LowerPanel extends CenterPanel {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	float hue, saturation, brightness;
	
	public void setup(){
		
		size((int) (screenSize.getWidth() * 0.9), (int) ((screenSize.getHeight() * 0.8)*0.2));
		background(255);
		
	}



	public void draw(){
		
		background(255);
		fill(200);
		ellipseMode(CENTER);
		textAlign(CENTER);
		textSize(15);
		float spacing = (float) ((screenSize.getWidth() * 0.9)/12);
		float x = spacing/2;
		
		for (int i = 0; i < 12; i++){
			
			fill(hue, saturation, brightness);
			ellipse(x, height/2, 20, 20);
			fill(0);
			text(months[i], x, height - 20);
			x += spacing;
			
		}
		
	}
}
