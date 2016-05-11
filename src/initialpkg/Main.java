package initialpkg;

import java.awt.GridBagLayout;
import java.awt.Window;
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

			
			while ((thisLine = csvr.readNext()) != null){
				try {
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
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run(){
					frame.dispose();
				}
			});
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Storm> fileToStorm(String fileName, int lines){
		ArrayList<Storm> array = new ArrayList<Storm>();
		
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 100);
		frame.setVisible(true);
		frame.setTitle("File data to Storm object");
		final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
		final JProgressBar progressBar = new JProgressBar(model);
		progressBar.setStringPainted(true);
		frame.add(progressBar);
		progressBar.setValue(0);
		int progressValue = 0;
		
		
		
		return array;
	}
	
	
	/**
	 * This method counts the number of lines in the file using openCSV API
	 * Also includes a loading bar and line counter to visualize the process
	 * 
	 * @param fileName is the input file name if opened relatively or path 
	 * @return the number of lines counted (long)
	 */
	public static int countLines(String fileName){
		int progressValue = 0;
		
		//Initialize JFrame, JPanel, JLabel
		final JFrame frame = new JFrame("Line Counter");
		JPanel panel = new JPanel();
		JLabel counter = new JLabel();
		
		//Label (Line count)
		counter.setText("Number of lines: " + progressValue);
		
		//Panel (Contains label)
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(counter);
		
		//Frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(250, 75);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(panel);
		
		
	    try {
	    	CSVReader csvr = new CSVReader(new FileReader(fileName));
	    	while(csvr.readNext() != null){
	    		progressValue++;
	    		counter.setText("Number of lines: " + progressValue); //update counter
	    	}
	    	csvr.close();
	    	return (int) csvr.getRecordsRead();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	    return 0;
	}
	
}
