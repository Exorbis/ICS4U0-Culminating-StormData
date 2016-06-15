package initialpkg;

import java.util.ArrayList;

import javax.swing.UIManager;



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
		

		
		ArrayList<Double> monthDamages = new ArrayList<Double>();
		for (int i = 1; i <= 12; i++){
			String [] monthIndexs = StormController.binarySearch(StormController.getSortedMonth(), "month", i).split(",");
			double monthDamage = 0;
			for (int j = 0; j < monthIndexs.length; j++){
				monthDamage += StormController.getSortedMonth().get(j).getDataDouble("propertydmg");
				monthDamage += StormController.getSortedMonth().get(j).getDataDouble("cropdmg");
			}
			monthDamages.add(monthDamage);
		}
		
		StormController.setTotalDmgMonths(monthDamages);
		
		
		StormView frame = new StormView();
		frame.setVisible(true);
		
		

	}
}

	

