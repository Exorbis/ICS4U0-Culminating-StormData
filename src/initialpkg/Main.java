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

	static double progressValue = 0, sortBarValue1 = 0, sortBarValue2 = 0;
	static double totalValue = 0, sortBarTotal = 0;
	static JLabel counter1 = new JLabel();
	static JLabel counter2 = new JLabel();
	static String category1, category2;

	static boolean finishedSort1 = false, finishedSort2 = false, finishedSort3 = false;

	
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 

		/*
		Thread openFile = new Thread(new Runnable (){

			public void run() {
				FileOpen.createAndShowGUI();

			}
		});
		
		Thread convertData = new Thread(new Runnable () {
			public void run () {

				StormController.fileToStorm(FileOpen.getPath());

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

		while(StormController.getStatus(finishedSort1) == false && StormController.getStatus(finishedSort2) == false) {
			try {
				Thread.sleep(0);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		StormView frame = new StormView();
		frame.setVisible(true);
		*/
		
	
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

		while(finishedSort1 == false && finishedSort2 == false && finishedSort3 == false) {
			try {
				Thread.sleep(0);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		StormView frame = new StormView();
		frame.setVisible(true);
		



	}
	public static String multipleValueRight(ArrayList<Storm> array, String type, int input, int index, String output){
		if (array.get(index).getDataInt(type) != input) return output;
		else return multipleValue(array, type, input, index + 1, output + ", " + index);
	}
	
	public static String multipleValueLeft(ArrayList<Storm> array, String type, int input, int index, String output){
		if (array.get(index).getDataInt(type) != input) return output;
		else return multipleValue(array, type, input, index - 1, output + ", " + index);
	}
	
	public static String multipleValueRight(ArrayList<Storm> array, String type, double input, int index, String output){
		if (array.get(index).getDataDouble(type) != input) return output;
		else return multipleValue(array, type, input, index + 1, output + ", " + index);
	}
	
	public static String multipleValueLeft(ArrayList<Storm> array, String type, double input, int index, String output){
		if (array.get(index).getDataDouble(type) != input) return output;
		else return multipleValue(array, type, input, index - 1, output + ", " + index);
	}
	
	public static String multipleValueRight(ArrayList<Storm> array, String type, String input, int index, String output){
		if (!array.get(index).getDataString(type).toLowerCase().equals(input.toLowerCase())) return output;
		else return multipleValue(array, type, input, index + 1, output + ", " + index);
	}
	
	public static String multipleValueLeft(ArrayList<Storm> array, String type, String input, int index, String output){
		if (!array.get(index).getDataString(type).toLowerCase().equals(input.toLowerCase())) return output;
		else return multipleValue(array, type, input, index - 1, output + ", " + index);
	}
	
	public static String multipleValueRight(ArrayList<Storm> array, String type, char input, int index, String output){
		if (array.get(index).getDataInt(type) != input) return output;
		else return multipleValue(array, type, input, index + 1, output + ", " + index);
	}
	
	public static String multipleValueLeft(ArrayList<Storm> array, String type, char input, int index, String output){
		if (array.get(index).getDataInt(type) != input) return output;
		else return multipleValue(array, type, input, index - 1, output + ", " + index);
	}
	
	
	public static int interpolationSearch(ArrayList<Storm> array, int key){
		int low = 0;
		int high = array.size() - 1;
		int mid;
		
		while (array.get(high).getDataInt != array.get(low).getDataInt && key >= array.get(low).getDataInt && key <= array.get(high).getDataInt){
			mid = low + ((key - array.get(low).getDataInt) * (high - low) / (array.get(high).getDataInt - array.get(low).getDataInt));
			
			if (array.get(mid).getDataInt < key) low = mid + 1;
			else if (key < array.get(mid).getDataInt) high = mid - 1;
			else return mid;
		}
		
		
		if (key == array.get(low).getDataInt) return low;
		else return -1;
	}
	

	public static int binarySearch(ArrayList<Storm> array, String type, int input){
		int maximum = array.size();
		int minimum = 0;
		int middle;
		
		while (minimum < maximum){
			middle = (int) Math.floor((maximum + minimum) / 2);
			if ((int) array.get(middle).getDataInt(type) < input){
				minimum = middle + 1;
			} else {
				maximum = middle;
			}
		}
		if (minimum == maximum && array.get(minimum).getDataInt(type) < input){
			return minimum;
		} else {
			return -1;
		}
	}
	
	public static String binarySearch(ArrayList<Storm> array, String type, String input){
		int maximum = array.size();
		int minimum = 0;
		int middle;
		
		while (minimum < maximum){
			middle = (int) Math.floor((maximum + minimum)/2);
			if (array.get(middle).getDataString(type).toLowerCase().compareTo(input.toLowerCase()) < 0) minimum = middle + 1;
			else maximum = middle;
		}
		if (minimum == maximum && array.get(minimum).getDataString(type).toLowerCase().compareTo(input.toLowerCase()) < 0) return multipleValueLeft(array, type, input, minimum, "") + ", " multipleValueRight(array, type, input, minimum, "");
		else return "-1";
	}
	
	public static String binarySearch(ArrayList<Storm> array, String type, double input){
		int maximum = array.size();
		int minimum = 0;
		int middle;
		
		while (minimum < maximum){
			middle = (int) Math.floor((maximum + minimum)/2);
			if (array.get(middle).getDataDouble(type) < input) minimum = middle + 1;
			else maximum = middle;
		}
		if (minimum == maximum && array.get(minimum).getDataDouble(type) < input) return multipleValueLeft(array, type, input, minimum, "") + ", " multipleValueRight(array, type, input, minimum, "");
		else return "-1";
	}
	
	public static String binarySearch(ArrayList<Storm> array, String type, char input){
		int maximum = array.size();
		int minimum = 0;
		int middle;
		
		while (minimum < maximum){
			middle = (int) Math.floor((maximum + minimum) / 2);
			if (array.get(middle).getDataChar(type) < input) {
				minimum = middle + 1;
			} else {
				maximum = middle;
			}
		}
		if (minimum == maximum && array.get(minimum).getDataChar(type) < input) return multipleValueLeft(array, type, input, minimum, "") + ", " multipleValueRight(array, type, input, minimum, "");
		else return "-1";
	}
	

	/**
	 * This method sorts an array of Storm objects based off the specified data type in a recursive-top down format
	 *
	 * @param array is the input array that needs to be sorted
	 * @param type is what the method is going to sort based off of
	 * @return the sorted array
	 */
	public static ArrayList<Storm> mergeSort(ArrayList<Storm> array, String type, int thread){
		sortBarTotal = fileData.size() - 1;
	 	
		if (array.size() <= 1) return array;

		int midpoint = array.size()/2;

		ArrayList<Storm> firstHalf = new ArrayList<Storm>(array.subList(0, midpoint));
		ArrayList<Storm> secondHalf = new ArrayList<Storm>(array.subList(midpoint, array.size()));

		firstHalf = mergeSort(firstHalf, type, thread);
		secondHalf = mergeSort(secondHalf, type, thread);
		
		if (thread == 1) sortBarValue1++; counter1.setText(category1 + ": " + (int) sortBarValue1 + "/" + (int) sortBarTotal + "  -  ");
		if (thread == 2) sortBarValue2++; counter2.setText(category2 + ": " + (int) sortBarValue2 + "/" + (int) sortBarTotal);
		
		
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
		final ArrayList<Storm> array = new ArrayList<Storm>();
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
		
		
		counter1.setText("Number of lines: " + counterValue);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 110);
		frame.setVisible(true);
		frame.setTitle("Converting file to Storm");
		prefSize.width = 800;
		prefSize.height = 63;
		progressBar.setPreferredSize(prefSize);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		panel.add(progressBar);
		panel.add(counter1);
		frame.add(panel);
		

		
	    try {
	    	BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    	String input;
	    	while ((input = reader.readLine()) != null){
	    		if (input.startsWith("19") || input.startsWith("20")){
	    			lines++;
	    			counter1.setText("Number of lines: " + lines);
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
		totalValue = 30;
		panel.add(counter2);
		
		Thread sort1 = new Thread(new Runnable () {
			public void run () {

				sortBarValue1 = 0;
				update(progressBar);
				frame.setTitle("Sorting by type...");
				category1 = "beginyearmonth";
				sorted_beginYearMonth = mergeSort(array, "beginyearmonth", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				update(progressBar);
				category1 = "directInj";
				sorted_indirectInj = mergeSort(array, "hello", 1);
				
				
				
				sortBarValue1 = 0;
				category1 = "beginday";
				sorted_beginDay = mergeSort(array, "beginday", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "begintime";
				sorted_beginTime = mergeSort(array, "begintime", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "episodeid";
				sorted_episodeID = mergeSort(array, "episodeid", 1);
				update(progressBar);
				
	
				
				sortBarValue1 = 0;
				category1 = "state";
				sorted_state = mergeSort(array, "state", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "statefips";
				sorted_stateFIPS = mergeSort(array, "statefips", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "year";
				sorted_year = mergeSort(array, "year", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "month";
				sorted_month = mergeSort(array, "month", 1);
				update(progressBar);
				

				sortBarValue1 = 0;
				category1 = "magnitudetype";
				sorted_magnitudeType = mergeSort(array, "magnitudetype", 1);
				update(progressBar);
				

				
				sortBarValue1 = 0;
				category1 = "torstate";
				sorted_torState = mergeSort(array, "torstate", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "torname";
				sorted_torName = mergeSort(array, "torname", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "beginlocation";
				sorted_beginLocation = mergeSort(array, "beginlocation", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "beginlatitude";
				sorted_beginLatitude = mergeSort(array, "beginlatitude", 1);
				update(progressBar);
				
				sortBarValue1 = 0;
				category1 = "beginlongitude";
				sorted_beginLongitude = mergeSort(array, "beginlongitude", 1);
				update(progressBar);

				finishedSort1 = true;
				
			}
		});
		
		Thread sort2 = new Thread(new Runnable () {
			public void run () {
				
				
				sortBarValue2 = 0;
				category2 = "floodcause";
				sorted_floodCause = mergeSort(array, "floodcause", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "torfscale";
				sorted_torFScale = mergeSort(array, "torfscale", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "torlength";
				sorted_torLength = mergeSort(array, "torlength", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "torwidth";
				sorted_torWidth = mergeSort(array, "torwidth", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "eventid";
				sorted_eventID = mergeSort(array, "eventid", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "eventtype";
				sorted_eventType = mergeSort(array, "eventtype", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "cztype";
				sorted_czType = mergeSort(array, "cztype", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "czname";
				sorted_czName = mergeSort(array, "czname", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "wfo";
				sorted_wfo = mergeSort(array, "wfo", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "timezone";
				sorted_timezone = mergeSort(array, "timezone", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "directdeaths";
				sorted_directDeaths = mergeSort(array, "directdeaths", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "indirectdeaths";
				sorted_indirectDeaths = mergeSort(array, "indirectdeaths", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "propertydmg";
				sorted_propertyDmg = mergeSort(array, "propertydmg", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "cropdmg";
				sorted_cropDmg = mergeSort(array, "cropdmg", 2);
				update(progressBar);
				
				sortBarValue2 = 0;
				category2 = "magnitude";
				sorted_magnitude = mergeSort(array, "magnitude", 2);
				update(progressBar);

				finishedSort2 = true;
				
			}
		});
	
		frame.setSize(1150, 110);
		sort1.start();
		sort2.start();
		
		while (finishedSort1 == false && finishedSort2 == false){}
		dispose(frame);
		
		
	}


	public static void update(final JProgressBar progressBar){
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
	
	public static void updateSortBar(final JProgressBar progressBar){
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
	
	public static void dispose(final JFrame frame){
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
