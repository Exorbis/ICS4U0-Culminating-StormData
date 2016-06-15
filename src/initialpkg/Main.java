package initialpkg;

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
		
		
		
		StormView frame = new StormView();
		frame.setVisible(true);
		
		String janIndexs = StormController.binarySearch(StormController.getSortedMonth(), "month", 1);
		System.out.println(janIndexs);

	}
}

	

