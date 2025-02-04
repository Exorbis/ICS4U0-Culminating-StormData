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
 * This is where all data initializations and methods occur for the startup in order to allow the program to run
 * It includes all the sorts, searches, and other functions are held if they do not particularly pertain to any class
 * This class is not meant to be initialized, it is meant to be used solely for its functionality of methods and passing information within the program
 * 
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 15, 2016
 * @version 1.1
 *
 */
public class StormController{
  
  	private static ArrayList<Storm> sorted_beginYearMonth = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_beginDay = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_beginTime = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_episodeID = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_eventID = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_state = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_stateFIPS = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_year = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_month = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_eventType = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_czType = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_czName = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_wfo = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_timezone = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_directInj = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_indirectInj = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_directDeaths = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_indirectDeaths = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_propertyDmg = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_cropDmg = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_magnitude = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_magnitudeType = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_floodCause = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_torFScale = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_torLength = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_torWidth = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_torState = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_torName = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_beginLocation = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_beginLatitude = new ArrayList<Storm>();
	private static ArrayList<Storm> sorted_beginLongitude = new ArrayList<Storm>();

  	private static double progressValue = 0;
	private static double sortBarValue1 = 0;
	private static double sortBarValue2 = 0;
	private static double totalValue = 0, sortBarTotal = 0;
	private static JLabel counter1 = new JLabel();
	private static JLabel counter2 = new JLabel();
	private static String category1, category2;

	private static boolean finishedSort1 = false;
	private static boolean finishedSort2 = false;

  	
  	private static Thread sort1, sort2;
  	private static ArrayList<Storm> fileStorm;
  	
  	private static ArrayList<Integer> totalStormsPerState;
  	private static ArrayList<Double> totalDmgMonths, totalMagnitudeMonths;
  	
  	/**
  	 * This is the constructor that is never called as a new object due to the fact that this is a static class and in no way meant to be initialized, only used for its functionality
  	 */
  	public StormController(){
    
  	}
  
  	/**
  	 * This method returns the total dmg done in each month in the form of an ArrayList of doubles
  	 * 
  	 * @return the variable containing the total dmg done in each month
  	 */
  	public static ArrayList<Double> getTotalDmgMonths(){
  		return totalDmgMonths;
  	}
  	
  	/**
  	 * This method returns the total magnitude incurred in each month in the form of an ArraryList of double
  	 * 
  	 * @return the variable containing the total magnitude in each month
  	 */
  	public static ArrayList<Double> getTotalMagnitudeMonths(){
  		return totalMagnitudeMonths;
  	}
  	
  	/**
  	 * This method changes the total damage months variable to one specified by the input array
  	 * 
  	 * @param array is the input array that is supposed to contain the total damage for each month
  	 */
  	public static void setTotalDmgMonths(ArrayList<Double> array){
  		totalDmgMonths = array;
  	}
  	
  	/**
  	 * This method changes the total magnitude months variable to one specified by the input array
  	 * 
  	 * @param array is the input array that is supposed to contain the total magnitude for each month
  	 */
  	public static void setTotalMagnitudeMonths(ArrayList<Double> array){
  		totalMagnitudeMonths = array;
  	}
  	
  	
  	/**
  	 * This method returns the total storms that have occurred in each state in the form of sorted (by state) ArrayList of integers 
  	 * 
  	 * @return the variable containing the total storms per state
  	 */
  	public static ArrayList<Integer> getTotalStormsPerState(){
  		return totalStormsPerState;
  	}
  	
  	/**
  	 * This method changes the total storms per state to one specified by the input array
  	 * 
  	 * @param array is the input array that is supposed to contain the total storms per state
  	 */
  	public static void setTotalStormsPerState(ArrayList<Integer> array){
  		totalStormsPerState = array;
  	}
  	
  	/**
  	 * This function is used to check to the right of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the right side of the specified index on start
  	 */
  	public static String multipleValueRight(ArrayList<Storm> array, String type, int key, int index, String output){
		index++;
  		while (index < array.size()){
  			if (array.get(index).getDataInt(type) == key) output = output + ", " + index;
 			index++;
		}
		return output;
	}

  	/**
  	 * This function is used to check to the left of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the left side of the specified index on start
  	 */
	public static String multipleValueLeft(ArrayList<Storm> array, String type, int key, int index, String output){
		index--;
		while (index >= 0){
			if (array.get(index).getDataInt(type) == key) output = index + ", " + output;
			index--;
		}
		return output;
	}

	/**
  	 * This function is used to check to the right of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the right side of the specified index on start
  	 */
	public static String multipleValueRight(ArrayList<Storm> array, String type, double key, int index, String output){
		index++;
		while (index < array.size()){
			if (array.get(index).getDataDouble(type) == key) output = output + ", " + index;
			index++;
		}
		return output;
	}

	/**
  	 * This function is used to check to the left of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the left side of the specified index on start
  	 */
	public static String multipleValueLeft(ArrayList<Storm> array, String type, double key, int index, String output){
		index--;
		while (index >= 0){
			if (array.get(index).getDataDouble(type) == key) output = index + ", " + output;		
			index--;
		}
		return output;
	}

	/**
  	 * This function is used to check to the right of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the right side of the specified index on start
  	 */
	public static String multipleValueRight(ArrayList<Storm> array, String type, String key, int index, String output){
		index++;
		while (index < array.size()){
			if (array.get(index).getDataString(type).equals(key)) output = output + ", " + index;
			index++;
		}
		return output;
	}

	/**
  	 * This function is used to check to the left of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the left side of the specified index on start
  	 */
	public static String multipleValueLeft(ArrayList<Storm> array, String type, String key, int index, String output){
		index--;
		while (index >= 0){
			if (array.get(index).getDataString(type).equals(key)) output = index + ", " + output;
			index--;
		}
		return output;
	}

	/**
  	 * This function is used to check to the right of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the right side of the specified index on start
  	 */
	public static String multipleValueRight(ArrayList<Storm> array, String type, char key, int index, String output){
		index++;
		while (index < array.size()){
			if (array.get(index).getDataChar(type) == key) output = output + ", " + index;
			index++;
		}
		return output;
	}

	/**
  	 * This function is used to check to the left of a specific index value in an array in order to ensure all of the key matching index values are included
  	 * 
  	 * @param array is the input array that is being referenced
  	 * @param type is the type of data that is to be retrieved from Storm class
  	 * @param key is the target value that is being searched for
  	 * @param index is the current index position that is being referenced with the array
  	 * @param output is the indexes of the key in one chain of a string
  	 * @return all the indexes on the left side of the specified index on start
  	 */
	public static String multipleValueLeft(ArrayList<Storm> array, String type, char key, int index, String output){
		index--;
		while (index >= 0){
			if (array.get(index).getDataChar(type) == key) output = index + ", " + output;
			index--;
		}
		return output;
	}
  	
  	/**
  	 * This method is used to search a key value based off the binary search algorithm and return its first reach position of that key
  	 * 
  	 * @param array is the input array that is being searched
  	 * @param type is the type of value being compared that will be retrieved from Storm class
  	 * @param key is the target value that is being searched
  	 * @return the first reach index value of the search
  	 */
  	public static String binarySearch(ArrayList<Storm> array, String type, int key){
  		int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
           int mid = (low + high)/2;
           if (array.get(mid).getDataInt(type) == key) return multipleValueLeft(array, type, key, mid, String.valueOf(mid)) + ", " + multipleValueRight(array, type, key, mid, "");
           else if (array.get(mid).getDataInt(type) < key) low = mid + 1;
           else high = mid - 1;
        }
        return "-1";
  	}
  	

  	/**
  	 * This method is used to search a key value based off the binary search algorithm and return its first reach position of that key
  	 * 
  	 * @param array is the input array that is being searched
  	 * @param type is the type of value being compared that will be retrieved from Storm class
  	 * @param key is the target value that is being searched
  	 * @return the first reach index value of the search
  	 */
  	public static String binarySearch(ArrayList<Storm> array, String type, double key){
  		int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
           int mid = (low + high)/2;
           if (array.get(mid).getDataDouble(type) == key) return multipleValueLeft(array, type, key, mid, String.valueOf(mid)) + ", " + multipleValueRight(array, type, key, mid, "");
           else if (array.get(mid).getDataDouble(type) < key) low = mid + 1;
           else high = mid - 1;
        }
        return "-1";
  	}
  	
  	/**
  	 * This method is used to search a key value based off the binary search algorithm and return its first reach position of that key
  	 * 
  	 * @param array is the input array that is being searched
  	 * @param type is the type of value being compared that will be retrieved from Storm class
  	 * @param key is the target value that is being searched
  	 * @return the first reach index value of the search
  	 */
  	public static String binarySearch(ArrayList<Storm> array, String type, char key){
  		int low = 0;
        int high = array.size() - 1;
        while (low <= high) {
           int mid = (low + high)/2;
           if (array.get(mid).getDataChar(type) == key) return multipleValueLeft(array, type, key, mid, String.valueOf(mid)) + ", " + multipleValueRight(array, type, key, mid, "");
           else if (array.get(mid).getDataChar(type) < key) low = mid + 1;
           else high = mid - 1;
        }
        return "-1";
  	}
  	
  	
  	/**
  	 * This method is used to search a key value based off the binary search algorithm and return its first reach position of that key
  	 * 
  	 * @param array is the input array that is being searched
  	 * @param type is the type of value being compared that will be retrieved from Storm class
  	 * @param key is the target value that is being searched
  	 * @return the first reach index value of the search
  	 */
  	public static String binarySearch(ArrayList<Storm> array, String type, String key){
  		int maximum = array.size();
  		int minimum = 0;
  		int middle;
  		while (minimum < maximum) {
  			middle = (int) Math.floor((maximum + minimum) / 2);
  			if (array.get(middle).getDataString(type).compareToIgnoreCase(key) < 0) minimum = middle + 1; 
  			else maximum = middle;
  		}
  		
  		if (minimum == maximum && array.get(minimum).getDataString(type).equalsIgnoreCase(key)) return multipleValueLeft(array, type, key, minimum, String.valueOf(minimum)) + ", " + multipleValueRight(array, type, key, minimum, "");
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
		sortBarTotal = fileStorm.size() - 1;
	 	
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
		ArrayList<Storm> array3 = new ArrayList<Storm>();

		
		String checkType = fileStorm.get(0).checkType(type).toLowerCase();

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

					array.add(new Storm(checkParseInt(thisLine[0]), checkParseInt(thisLine[1]), checkParseInt(thisLine[2]), thisLine[3], checkParseInt(thisLine[4]), checkParseInt(thisLine[5]), 
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

		fileStorm = array;
		
		
		progressValue = 0;
		totalValue = 30;
		panel.add(counter2);
		progressBar.setString("Sorting file");
		
		sort1 = new Thread(new Runnable () {
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
		
		sort2 = new Thread(new Runnable () {
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

	/**
	 * This method retrieves the file data that was parsed into Storm objects, but without sorting
	 * 
	 * @return the file data, in the form of Storm data ArrayList
	 */
	public static ArrayList<Storm> getFile(){
		return fileStorm;
	}
	
	/**
	 * This method retrieves the status of the sorts in order to determine whether the mergeSort operation has been completed
	 * 
	 * @param input is the inputed test value to specify which boolean needs to be checked
	 * @return the proper specified boolean value
	 */
	public static boolean getStatus(String input){
		switch(input.toLowerCase().trim()){
			
		case "finishedsort1":
			return finishedSort1;
			
		case "finishedsort2":
			return finishedSort2;
			
		}
		return false;
	}

	/**
	 * This method updates the specified progress bar to properly display the percentage completion value
	 * 
	 * @param progressBar is the input bar that needs to be updated
	 */
	public static void update(final JProgressBar progressBar){
		try {
			progressValue++;
			final int setValue = (int)((1.0 * progressValue)/(totalValue * 1.0)*100.0);	//updates progress bar and shows
			SwingUtilities.invokeLater(new Runnable() {								//the text.  This runs in another 
				public void run() {													//Thread.
					progressBar.setValue(setValue);
					progressBar.setString( setValue + "%");
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method updates the specific part of the sorting section of the initiation process for the specified progress bar
	 * 
	 * @param progressBar the input progress bar that needs to be updated
	 */
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
	
	/**
	 * This method disposes a specified frame from a remote part of the program
	 * 
	 * @param frame is the frame that needs to be disposed
	 */
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
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedBeginYearMonth(){
		return sorted_beginYearMonth;
	}

	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedBeginDay(){
		return sorted_beginDay;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedBeginTime(){
		return sorted_beginTime;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedEpisodeID(){
		return sorted_episodeID;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedEventID(){
		return sorted_eventID;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedState(){
		return sorted_state;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedStateFIPS(){
		return sorted_stateFIPS;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedYear(){
		return sorted_year;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedMonth(){
		return sorted_month;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedEventType(){
		return sorted_eventType;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedCzType(){
		return sorted_czType;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedCzName(){
		return sorted_czName;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedWfo(){
		return sorted_wfo;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedTimezone(){
		return sorted_timezone;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedDirectInj(){
		return sorted_directInj;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedIndirectInj(){
		return sorted_indirectInj;
	}

	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedDirectDeaths(){
		return sorted_directDeaths;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedIndirectDeaths(){
		return sorted_indirectDeaths;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedPropertyDmg(){
		return sorted_propertyDmg;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedCropDmg(){
		return sorted_cropDmg;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedMagnitude(){
		return sorted_magnitude;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedMagnitudeType(){
		return sorted_magnitudeType;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedFloodCause(){
		return sorted_floodCause;
	}

	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getsortedTorFSCale(){
		return sorted_torFScale;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedTorLength(){
		return sorted_torLength;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedTorWidth(){
		return sorted_torWidth;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedTorState(){
		return sorted_torState;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedTorName(){
		return sorted_torName;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedBeginLocation(){
		return sorted_beginLocation;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedBeginLatitude(){
		return sorted_beginLatitude;
	}
	
	/**
	 * This method returns the specified sorted list by a specific type
	 * 
	 * @return the sorted array of noted type
	 */
	public static ArrayList<Storm> getSortedBeginLongitude(){
		return sorted_beginLongitude;
	}


}
