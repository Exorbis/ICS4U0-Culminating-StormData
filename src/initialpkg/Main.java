package initialpkg;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;




/**
 * 
 * @author Marc Pelve
 * @author Andrew Seidel
 * @since May 5, 2016
 * @since JDK 8
 * @version 1.0
 *
 */
//Took Seidel's progress bar

public class Main {
	
	public static void main(String[] args) {
		
		
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 100);
		frame.setVisible(true);
		frame.setTitle("Reading file...");
		final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
		final JProgressBar progressBar = new JProgressBar(model);
		progressBar.setStringPainted(true);
		frame.add(progressBar);
		progressBar.setValue(0);
		int progressValue = 0;
		
		
		int lines = countLines("Stormdata_1996.csv");
		System.out.println(lines);

		
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Stormdata_1996.csv")))){
			
			String thisLine = null;
			
			
			while ((thisLine = br.readLine()) != null){
				try {
									
					
					progressValue++;
					final int setValue = (int)((1.0 * progressValue)/(lines * 1.0)*100.0);	//updates progress bar and shows
					SwingUtilities.invokeLater(new Runnable() {								//the text.  This runs in another 
						public void run() {													//Thread.
							progressBar.setValue(setValue);
							progressBar.setString("Reading in file... " + setValue + "%");
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		

	}
	
	
	public static int countLines(String fileName){
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(fileName));
	    	int lines = 0;
	    	while (br.readLine() != null) lines++;
	    	br.close();
	    	return lines;
	    } catch (IOException e){
	    	e.printStackTrace();
	    }
	    return 0;
	}
	
}
