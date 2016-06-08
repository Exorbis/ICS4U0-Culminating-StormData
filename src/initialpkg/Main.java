package initialpkg;


import java.awt.Component;
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

	static double progressValue = 0, sortBarValue = 0;
	static double totalValue = 0, sortBarTotal = 0;
	static JLabel counter = new JLabel();
	static String category;

	static boolean finishedSort = false;

	
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 

	
		Thread openFile = new Thread(new Runnable (){

			public void run() {
				FileOpen.createAndShowGUI();

			}
		});


		Thread convertData = new Thread(new Runnable () {
			public void run () {

				fileToStorm(FileOpen.getPath());

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

		while(finishedSort == false) {
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
		sortBarTotal = fileData.size();
		
		if (array.size() <= 1) return array;

		int midpoint = array.size()/2;

		ArrayList<Storm> firstHalf = new ArrayList<Storm>(array.subList(0, midpoint));
		ArrayList<Storm> secondHalf = new ArrayList<Storm>(array.subList(midpoint, array.size()));

		firstHalf = mergeSort(firstHalf, type);
		secondHalf = mergeSort(secondHalf, type);

		sortBarValue++;
		counter.setText(category + ": " + (int) sortBarValue + "/" + (int) sortBarTotal);
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
	public static void fileToStorm(String fileName){
		ArrayList<Storm> array = new ArrayList<Storm>();
		int counterValue = 0;
		int lines = 0;


		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		
		final JFrame frame = new JFrame();
		final JPanel panel = new JPanel(new FlowLayout());
		final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
		final JProgressBar progressBar = new JProgressBar(model);
		final Dimension prefSize = progressBar.getPreferredSize();
		
		
		counter.setText("Number of lines: " + counterValue);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 110);
		frame.setVisible(true);
		frame.setTitle("File data to Storm object");
		prefSize.width = 800;
		prefSize.height = 63;
		progressBar.setPreferredSize(prefSize);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		panel.add(progressBar);
		panel.add(counter);
		frame.add(panel);
		

		
	    try {
	    	BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    	String input;
	    	while ((input = reader.readLine()) != null){
	    		if (input.startsWith("19") || input.startsWith("20")){
	    			lines++;
	    			counter.setText("Number of lines: " + lines);
	    		}	    		
	    	}
	    	reader.close();
	    	progressValue = 0;
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

				
				update(progressBar);
			}
			
			csvr.close();
			

		} catch (Exception e){
			e.printStackTrace();
		}

		
		fileData = array;
		progressValue = 0;
		totalValue = 31;
		
		category = "beginyearmonth";
		sorted_beginYearMonth = mergeSort(array, "beginyearmonth");
		update(progressBar);
		
		sortBarValue = 0;
		category = "beginday";
		sorted_beginDay = mergeSort(array, "beginday");
		update(progressBar);
		
		sortBarValue = 0;
		category = "begintime";
		sorted_beginTime = mergeSort(array, "begintime");
		update(progressBar);
		
		sortBarValue = 0;
		category = "episodeid";
		sorted_episodeID = mergeSort(array, "episodeid");
		update(progressBar);
		
		sortBarValue = 0;
		category = "eventid";
		sorted_eventID = mergeSort(array, "eventid");
		update(progressBar);
		
		sortBarValue = 0;
		category = "state";
		sorted_state = mergeSort(array, "state");
		update(progressBar);
		
		sortBarValue = 0;
		category = "statefips";
		sorted_stateFIPS = mergeSort(array, "statefips");
		update(progressBar);
		
		sortBarValue = 0;
		category = "year";
		sorted_year = mergeSort(array, "year");
		update(progressBar);
		
		sortBarValue = 0;
		category = "month";
		sorted_month = mergeSort(array, "month");
		update(progressBar);

		sortBarValue = 0;
		category = "eventtype";
		sorted_eventType = mergeSort(array, "eventtype");
		update(progressBar);
		
		sortBarValue = 0;
		category = "cztype";
		sorted_czType = mergeSort(array, "cztype");
		update(progressBar);
		
		sortBarValue = 0;
		category = "czname";
		sorted_czName = mergeSort(array, "czname");
		update(progressBar);
		
		sortBarValue = 0;
		category = "wfo";
		sorted_wfo = mergeSort(array, "wfo");
		update(progressBar);
		
		sortBarValue = 0;
		category = "timezone";
		sorted_timezone = mergeSort(array, "timezone");
		update(progressBar);
		
		sortBarValue = 0;
		category = "directinj";
		sorted_directInj = mergeSort(array, "directinj");
		update(progressBar);
		
		sortBarValue = 0;
		category = "indirectinj";
		sorted_indirectInj = mergeSort(array, "indirectinj");
		update(progressBar);
		
		sortBarValue = 0;
		category = "directdeaths";
		sorted_directDeaths = mergeSort(array, "directdeaths");
		update(progressBar);
		
		sortBarValue = 0;
		category = "indirectdeaths";
		sorted_indirectDeaths = mergeSort(array, "indirectdeaths");
		update(progressBar);
		
		sortBarValue = 0;
		category = "propertydmg";
		sorted_propertyDmg = mergeSort(array, "propertydmg");
		update(progressBar);
		
		sortBarValue = 0;
		category = "cropdmg";
		sorted_cropDmg = mergeSort(array, "cropdmg");
		update(progressBar);
		
		sortBarValue = 0;
		category = "magnitude";
		sorted_magnitude = mergeSort(array, "magnitude");
		update(progressBar);
		
		sortBarValue = 0;
		category = "magnitudetype";
		sorted_magnitudeType = mergeSort(array, "magnitudetype");
		update(progressBar);
		
		sortBarValue = 0;
		category = "floodcause";
		sorted_floodCause = mergeSort(array, "floodcause");
		update(progressBar);
		
		sortBarValue = 0;
		category = "torfscale";
		sorted_torFScale = mergeSort(array, "torfscale");
		update(progressBar);
		
		sortBarValue = 0;
		category = "torlength";
		sorted_torLength = mergeSort(array, "torlength");
		update(progressBar);
		
		sortBarValue = 0;
		category = "torwidth";
		sorted_torWidth = mergeSort(array, "torwidth");
		update(progressBar);
		
		sortBarValue = 0;
		category = "torstate";
		sorted_torState = mergeSort(array, "torstate");
		update(progressBar);
		
		sortBarValue = 0;
		category = "torname";
		sorted_torName = mergeSort(array, "torname");
		update(progressBar);
		
		sortBarValue = 0;
		category = "beginlocation";
		sorted_beginLocation = mergeSort(array, "beginlocation");
		update(progressBar);
		
		sortBarValue = 0;
		category = "beginlatitude";
		sorted_beginLatitude = mergeSort(array, "beginlatitude");
		update(progressBar);
		
		sortBarValue = 0;
		category = "beginlongitude";
		sorted_beginLongitude = mergeSort(array, "beginlongitude");
		update(progressBar);
		
		dispose(frame);
		finishedSort = true;
	}


	public static void update(JProgressBar progressBar){
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
	
	public static void updateSortBar(JProgressBar progressBar){
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
	
	public static void dispose(JFrame frame){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				frame.dispose();
			}
		});
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
	

	
	public static ArrayList<Storm> getSortedBeginYearMonth(){
		return sorted_beginYearMonth;
	}

	public static ArrayList<Storm> getSortedBeginDay(){
		return sorted_beginDay;
	}
	
	public static ArrayList<Storm> getSortedBeginTime(){
		return sorted_beginTime;
	}
	
	public static ArrayList<Storm> getSortedEpisodeID(){
		return sorted_episodeID;
	}
	
	public static ArrayList<Storm> getSortedEventID(){
		return sorted_eventID;
	}
	
	public static ArrayList<Storm> getSortedState(){
		return sorted_state;
	}
	
	public static ArrayList<Storm> getSortedStateFIPS(){
		return sorted_stateFIPS;
	}
	
	public static ArrayList<Storm> getSortedYear(){
		return sorted_year;
	}
	
	public static ArrayList<Storm> getSortedMonth(){
		return sorted_month;
	}
	
	public static ArrayList<Storm> getSortedEventType(){
		return sorted_eventType;
	}
	
	public static ArrayList<Storm> getSortedCzType(){
		return sorted_czType;
	}
	
	public static ArrayList<Storm> getSortedCzName(){
		return sorted_czName;
	}
	
	public static ArrayList<Storm> getSortedWfo(){
		return sorted_wfo;
	}
	
	public static ArrayList<Storm> getSortedTimezone(){
		return sorted_timezone;
	}
	
	public static ArrayList<Storm> getSortedDirectInj(){
		return sorted_directInj;
	}
	
	public static ArrayList<Storm> getSortedIndirectInj(){
		return sorted_indirectInj;
	}

	public static ArrayList<Storm> getSortedDirectDeaths(){
		return sorted_directDeaths;
	}
	
	public static ArrayList<Storm> getSortedIndirectDeaths(){
		return sorted_indirectDeaths;
	}
	
	public static ArrayList<Storm> getSortedPropertyDmg(){
		return sorted_propertyDmg;
	}
	
	public static ArrayList<Storm> getSortedCropDmg(){
		return sorted_cropDmg;
	}
	
	public static ArrayList<Storm> getSortedMagnitude(){
		return sorted_magnitude;
	}
	
	public static ArrayList<Storm> getSortedMagnitudeType(){
		return sorted_magnitudeType;
	}
	
	public static ArrayList<Storm> getSortedFloodCause(){
		return sorted_floodCause;
	}

	public static ArrayList<Storm> getsortedTorFSCale(){
		return sorted_torFScale;
	}
	
	public static ArrayList<Storm> getSortedTorLength(){
		return sorted_torLength;
	}
	
	public static ArrayList<Storm> getSortedTorWidth(){
		return sorted_torWidth;
	}
	
	public static ArrayList<Storm> getSortedTorState(){
		return sorted_torState;
	}
	
	public static ArrayList<Storm> getSortedTorName(){
		return sorted_torName;
	}
	
	public static ArrayList<Storm> getSortedBeginLocation(){
		return sorted_beginLocation;
	}
	
	public static ArrayList<Storm> getSortedBeginLatitude(){
		return sorted_beginLatitude;
	}
	
	public static ArrayList<Storm> getSortedBeginLongitude(){
		return sorted_beginLongitude;
	}
	

}
