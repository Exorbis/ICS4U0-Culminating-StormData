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
import com.opencsv.CSVReader;

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
		frame.setTitle("Reading file");
		final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
		final JProgressBar progressBar = new JProgressBar(model);
		progressBar.setStringPainted(true);
		frame.add(progressBar);
		progressBar.setValue(0);
		int progressValue = 0;
		
		
		int lines = (int) countLines("Stormdata_1996.csv");
		System.out.println(lines);

		
		
		
		try (CSVReader csvr = new CSVReader(new InputStreamReader(new FileInputStream("Stormdata_1996.csv")))){
			
			String [] thisLine = null;
			int j = 0;
			
			while ((thisLine = csvr.readNext()) != null){
				try {
					j++;
					if (j == 6) System.out.println(Arrays.deepToString(thisLine));
					progressValue++;
					final int setValue = (int)((1.0 * progressValue)/(lines * 1.0)*100.0);	//updates progress bar and shows
					SwingUtilities.invokeLater(new Runnable() {								//the text.  This runs in another 
						public void run() {													//Thread.
							progressBar.setValue(setValue);
							progressBar.setString("Reading file - [" + setValue + "%]");
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
	
	
	public static long countLines(String fileName){
	    try {
	    	CSVReader csvr = new CSVReader(new FileReader(fileName));
	    	while(csvr.readNext() != null){}
	    	return csvr.getRecordsRead();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	    return 0;
	}
	
}
