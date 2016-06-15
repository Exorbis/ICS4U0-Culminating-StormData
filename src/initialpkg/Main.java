package initialpkg;

import java.util.ArrayList;

import javax.swing.UIManager;



/**
 * This is a program that retrieves data from "Storm Data" files and allows limited user interactivity and visuals with the organized data
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 15, 2016
 * @version 1.2
 *
 */
//Requires openCSV API 
//Used Andrew Seidel's loading bar

public class Main {
	
	 
	
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

		while(StormController.getStatus("finishedSort1") == false && StormController.getStatus("finishedSort2") == false) {
			try {
				Thread.sleep(0);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		

		ArrayList<Double> monthMagnitudes = new ArrayList<Double>();		
		ArrayList<Double> monthDamages = new ArrayList<Double>();
		double damage;
		ArrayList<String> states = StormView.returnNonRepeats(StormController.getSortedState(), "state");
		int numberOfStates = states.size();
		ArrayList<Integer> stormsPerState = new ArrayList<Integer>();
		String [] tempIndex;
		
		for (int i = 0; i < numberOfStates; i++){
			tempIndex = StormController.binarySearch(StormController.getSortedState(), "state", states.get(i)).split(",");
			stormsPerState.add(tempIndex.length);
		}
		
		
		for (int i = 1; i <= 12; i++){
			String [] monthIndexs = StormController.binarySearch(StormController.getSortedMonth(), "month", i).split(",");
			double monthDamage = 0;
			double monthMagnitude = 0;
			for (int j = 0; j < monthIndexs.length; j++){
				monthDamage += StormController.getSortedMonth().get(j).getDataDouble("propertydmg");
				monthDamage += StormController.getSortedMonth().get(j).getDataDouble("cropdmg");
				if ((damage = StormController.getSortedMonth().get(j).getDataDouble("magnitude")) != -1.0) monthMagnitude += damage;
			}
			
			
			monthDamages.add(monthDamage);
			monthMagnitudes.add(monthMagnitude);
		}
		
		StormController.setTotalDmgMonths(monthDamages);
		StormController.setTotalMagnitudeMonths(monthMagnitudes);
		StormController.setTotalStormsPerState(stormsPerState);

		
		StormView frame = new StormView();
		frame.setVisible(true);
		frame.repaint();
		
		

	}
}

	
