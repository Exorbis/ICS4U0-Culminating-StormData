package initialpkg;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.opencsv.CSVReader;






/**
 * This is a program that retrieves data from "Storm Data" files and allows predetermined user interactivity with the organized data
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 2, 2016
 * @version 1.1
 *
 */
//Requires openCSV API 
//Used Andrew Seidel's loading bar

public class Main {
	
	 
	

	public static ArrayList<Storm> fileData = new ArrayList<Storm>();
	
	static ArrayList<ArrayList<Storm>> sortedLists = new ArrayList<ArrayList<Storm>>();
	
	static ArrayList<Storm> sorted_beginYearMonth = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_beginDay = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_beginTime = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_episodeID = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_eventID = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_state = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_stateFIPS = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_year = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_month = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_eventType = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_czType = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_czName = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_wfo = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_beginDateTime = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_timezone = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_directInj = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_indirectInj = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_directDeaths = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_indirectDeaths = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_propertyDmg = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_cropDmg = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_magnitude = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_magnitudeType = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_floodCause = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_torFScale = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_torLength = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_torWidth = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_torState = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_torName = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_beginLocation = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_beginLatitude = new ArrayList<Storm>();
	static ArrayList<Storm> sorted_beginLongitude = new ArrayList<Storm>();

	static double progressValue = 0;
	static double totalValue = 0;

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 

	sortedLists.add(sorted_beginYearMonth);
	sortedLists.add(sorted_beginDay);
	sortedLists.add(sorted_beginTime);
	sortedLists.add(sorted_episodeID);
	sortedLists.add(sorted_eventID);
	sortedLists.add(sorted_state);
	sortedLists.add(sorted_stateFIPS);
	sortedLists.add(sorted_year);
	sortedLists.add(sorted_month);
	sortedLists.add(sorted_eventType);
	sortedLists.add(sorted_czType);
	sortedLists.add(sorted_czName);
	sortedLists.add(sorted_wfo);
	sortedLists.add(sorted_beginDateTime);
	sortedLists.add(sorted_timezone);
	sortedLists.add(sorted_directInj);
	sortedLists.add(sorted_indirectInj);
	sortedLists.add(sorted_directDeaths);
	sortedLists.add(sorted_indirectDeaths);
	sortedLists.add(sorted_propertyDmg);
	sortedLists.add(sorted_cropDmg);
	sortedLists.add(sorted_magnitude);
	sortedLists.add(sorted_magnitudeType);
	sortedLists.add(sorted_floodCause);
	sortedLists.add(sorted_torFScale);
	sortedLists.add(sorted_torLength);
	sortedLists.add(sorted_torWidth);
	sortedLists.add(sorted_torState);
	sortedLists.add(sorted_torName);
	sortedLists.add(sorted_beginLocation);
	sortedLists.add(sorted_beginLatitude);
	sortedLists.add(sorted_beginLongitude);
	
		Thread openFile = new Thread(new Runnable (){

			public void run() {
				FileOpen.createAndShowGUI();

			}
		});


		Thread convertData = new Thread(new Runnable () {
			public void run () {

				fileData = fileToStorm(FileOpen.getPath());

			}
		});
		
		

		openFile.start();



		while(FileOpen.getPath() == null){
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		convertData.start();

		while(fileData.size() == 0) {
			try {
				Thread.sleep(0);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		StormView frame = new StormView();
		frame.setVisible(true);
		



	}



	/**
	 * This method sorts an array of Storm objects based off the specified data type in a recursive-top down format
	 *
	 * @param array is the input array that needs to be sorted
	 * @param type is what the method is going to sort based off of
	 * @return the sorted array
	 */
	public static ArrayList<Storm> mergeSort(ArrayList<Storm> array, String type){
		if (array.size() <= 1) return array;

		int midpoint = array.size()/2;

		ArrayList<Storm> firstHalf = new ArrayList<Storm>(array.subList(0, midpoint));
		ArrayList<Storm> secondHalf = new ArrayList<Storm>(array.subList(midpoint, array.size()));

		firstHalf = mergeSort(firstHalf, type);
		secondHalf = mergeSort(secondHalf, type);

		return merge(firstHalf, secondHalf, type);
	}

	/**
	 * This method merges the first half split with the second half split in the mergeSort process
	 *
	 * @param array1 is the first half of the split
	 * @param array2 is the second half of the split
	 * @return array3 is the finalized merged array of the first half and second half 
	 */
	public static ArrayList<Storm> merge(ArrayList<Storm> array1, ArrayList<Storm> array2, String type){
		ArrayList<Storm> array3 =  new ArrayList<Storm>();

		
		String checkType = fileData.get(0).checkType(type).toLowerCase();

		if (checkType.equals("string")){

			while (array1.size() > 0 && array2.size() > 0){

				if (array1.get(0).getDataString(type).compareTo(array2.get(0).getDataString(type)) >= 0){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}

		else if (checkType.equals("integer")){
			while (array1.size() > 0 && array2.size() > 0){

				if (array1.get(0).getDataInt(type) > array2.get(0).getDataInt(type)){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}

		else if (checkType.equals("double")){
			while (array1.size() > 0 && array2.size() > 0){

				if (array1.get(0).getDataDouble(type) > array2.get(0).getDataDouble(type)){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}

		else if (checkType.equals("character")){
			while (array1.size() > 0 && array2.size() > 0){

				if (array1.get(0).getDataChar(type) > (array2.get(0).getDataChar(type))){
					array3.add(array2.get(0));
					array2.remove(0);
				} else {
					array3.add(array1.get(0));
					array1.remove(0);
				}
			}

			while (array1.size() > 0){
				array3.add(array1.get(0));
				array1.remove(0);
			}

			while (array2.size() > 0){
				array3.add(array2.get(0));
				array2.remove(0);
			}
		}



		return array3;
	}

	/**
	 * This method takes in a file name (if path relative) or a path with then number of lines and creates a Storm object based off each line of the CSV file using openCSV API
	 * 
	 * @param fileName is the name of the file (if the path is relative) or the path itself
	 * @param lines the number of lines in the file that is inputed that will most likely be calculated by countLines method
	 * @return the array of Storm objects with all relevant parameters inputed
	 */
	public static ArrayList<Storm> fileToStorm(String fileName){
		ArrayList<Storm> array = new ArrayList<Storm>();
		int counterValue = 0;
		int lines = 0;


		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		
		final  JFrame frame = new JFrame();
		final  JPanel panel = new JPanel(new FlowLayout());
		final  DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
		final  JProgressBar progressBar = new JProgressBar(model);
		final  Dimension prefSize = progressBar.getPreferredSize();
		final JLabel counter = new JLabel();
		
		counter.setText("Number of lines: " + counterValue);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 110);
		frame.setVisible(true);
		frame.setTitle("File data to Storm object");
		prefSize.width = 830;
		prefSize.height = 63;
		progressBar.setPreferredSize(prefSize);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		panel.add(progressBar);
		panel.add(counter);
		frame.add(panel);
		
		int progressValue = 0;

		
	    try {
	    	BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    	String input;
	    	while ((input = reader.readLine()) != null){
	    		if (input.startsWith("19") || input.startsWith("20")){
	    			lines++;
	    			counter.setText("Number of lines: " + lines);
	    		}	    		
	    	}
	    	
	    	totalValue = lines;
	    	
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	    
	    
		
		try (CSVReader csvr = new CSVReader(new InputStreamReader(new FileInputStream(fileName)))){

			String [] thisLine = null;


			while ((thisLine = csvr.readNext()) != null){

				if (progressValue != 0){

					array.add(new Storm(thisLine[0], checkParseInt(thisLine[1]), checkParseInt(thisLine[2]), thisLine[3], checkParseInt(thisLine[4]), checkParseInt(thisLine[5]), 
							checkParseInt(thisLine[6]), checkParseInt(thisLine[7]), thisLine[8], checkParseInt(thisLine[9]), checkParseInt(thisLine[10]), thisLine[11], thisLine[12], 
							thisLine[13].charAt(0), checkParseInt(thisLine[14]), thisLine[15], thisLine[16], thisLine[17], thisLine[18], thisLine[19], checkParseInt(thisLine[20]), 
							checkParseInt(thisLine[21]), checkParseInt(thisLine[22]), checkParseInt(thisLine[23]), thisLine[24], thisLine[25], thisLine[26], checkParseDouble(thisLine[27]),
							thisLine[28], thisLine[29], thisLine[30], thisLine[31], checkParseDouble(thisLine[32]), checkParseDouble(thisLine[33]), thisLine[34], thisLine[35], 
							checkParseInt(thisLine[36]), thisLine[37], checkParseDouble(thisLine[38]), thisLine[39], thisLine[40], checkParseDouble(thisLine[41]), thisLine[42], thisLine[43], 
							checkParseDouble(thisLine[44]), checkParseDouble(thisLine[45]), checkParseDouble(thisLine[46]), checkParseDouble(thisLine[47]), thisLine[48], thisLine[49], thisLine[50], 
							thisLine[51], thisLine[52], thisLine[53], thisLine[54], thisLine[55], thisLine[56], thisLine[57]));
				}

				
				try {
					progressValue++;
					final int setValue = (int)((1.0 * progressValue)/(totalValue * 1.0)*100.0);	//updates progress bar and shows
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
			
			csvr.close();
			
			
			/*
			SwingUtilities.invokeLater(new Runnable() {
				public void run(){
					frame.dispose();
				}
			});*/

		} catch (Exception e){
			e.printStackTrace();
		}


		return array;
	}


	/**
	 * This method returns the integer value of the string if it is not empty, if it is empty the method returns -1
	 * 
	 * @param input the input string that needs to be changed into an integer
	 * @return the value -1 or the integer value of the inputed string (int)
	 */
	public static int checkParseInt(String input){
		if (input.equals("")){ return -1; }
		else { return Integer.parseInt(input); }
	}



	/**
	 * This method returns the double value of the string if it is not empty, if it is empty the method returns -1
	 * 
	 * @param input the input string that needs to be changed into an double
	 * @return the value -1 or the double value of the inputed string (double)
	 */
	public static double checkParseDouble(String input){
		if (input.equals("")){ return -1; }
		else { return Double.parseDouble(input); }
	}

	/**
	 * This method returns the array of Storm objects that were initiated during the opening process 
	 * 
	 * @return the array list of storm objects
	 */
	public static ArrayList<Storm> getStorms() {
		return fileData;
	}

}
