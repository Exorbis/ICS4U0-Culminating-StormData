package initialpkg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This StormView will be used as the main client that the user interacts with and where the data is displayed
 * 
 * @author Marc Pelve
 * @since JDK 8
 * @since June 2, 2016
 * @version 1.0
 *
 */
public class StormView extends JFrame implements ActionListener{
	
	private String [] categories = {"beginYearMonth", "beginDay", "beginTime", "episodeID", "eventID", "state", "stateFIPS", "year", "month", "eventType", "czType", "czFips", "czName", "wfo", "beginDateTime", "timezone", "directInj", "indirectInj", "propertyDmg", "cropDmg", "sourceType", "magnitude", "magnitudeType", "floodCause", "category", "torFScale", "torLength", "torWidth", "torwfo", "torState", "torFIPS", "torName", "beginAzimuth", "beingRange", "beginLocation", "beginLatitude", "beginLongitude", "episodeNarrative", "eventNarrative", "lastModDate", "lastModTime", "lastCertDate", "lastCertTime", "lastMod", "lastCert", "addCorrFlg", "addCorrDate"};
	
	private JComboBox sortBy = new JComboBox(categories);
	private JComboBox sortBy2 = new JComboBox();
	
	/**
	 * This is the default constructor that is called to display the client 
	 */
	StormView(){
		JPanel dropDown = new JPanel();
		setLayout(new FlowLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.8));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Storm Data Timeline");
		

		sortBy.setSelectedIndex(0);
		sortBy.addActionListener(this);
		dropDown.add(sortBy);
		dropDown.add(sortBy2);
		
		this.add(dropDown);
		

	}
	
	/**
	 * This method returns a list of strings that includes a unique term from the list of Storm data inputed, which is specified by the inputed type
	 * 
	 * @param array is the input array that contains the Storm data
	 * @param type is the type of data from Storm parameter to sort from
	 * @return the categories non repeated in an arraylist of strings
	 */
	public ArrayList<String> returnNonRepeats(ArrayList<Storm> array, String type){
		ArrayList<Storm> workArray = Main.mergeSort(array, type);
		ArrayList<String> nonRepeats = new ArrayList<String>();
		
		for (int i = 0; i < workArray.size(); i++){
			if (!(nonRepeats.contains(workArray.get(i).displayData(type)))){
				nonRepeats.add(workArray.get(i).displayData(type));
			} 
		}
		
		while (nonRepeats.remove("-1") || nonRepeats.remove("-1.0")){}
		
		return nonRepeats;
		
	}
	
	/**
	 * This method detects for an action listener and carries out a function based off where the action comes from
	 */
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == sortBy){
			JComboBox localSortBy = (JComboBox)e.getSource();
			String category = (String)localSortBy.getSelectedItem();
			
			ArrayList<String> categoryValue;
			ArrayList<Storm> storms = Main.getStorms();
			
			switch (category.toLowerCase()){
			
			case "beginyearmonth":
				categoryValue = returnNonRepeats(storms, "beginyearmonth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginday":
				categoryValue = returnNonRepeats(storms, "beginday");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "begintime":
				categoryValue = returnNonRepeats(storms, "begintime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endyearmonth":
				categoryValue = returnNonRepeats(storms, "endyearmonth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endday":
				categoryValue = returnNonRepeats(storms, "endday");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "endtime":
				categoryValue = returnNonRepeats(storms, "endtime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break; 
				
			case "episodeid":
				categoryValue = returnNonRepeats(storms, "episodeid");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "eventid":
				categoryValue = returnNonRepeats(storms, "eventid");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "state":
				categoryValue = returnNonRepeats(storms, "state");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "statefips":
				categoryValue = returnNonRepeats(storms, "statefips");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "year":
				categoryValue = returnNonRepeats(storms, "year");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "month":
				categoryValue = returnNonRepeats(storms, "month");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "eventtype":
				categoryValue = returnNonRepeats(storms, "eventtype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "cztype":
				categoryValue = returnNonRepeats(storms, "cztype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "czfips":
				categoryValue = returnNonRepeats(storms, "czfips");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "czName":
				categoryValue = returnNonRepeats(storms, "czName");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "wfo":
				categoryValue = returnNonRepeats(storms, "wfo");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "begindatetime":
				categoryValue = returnNonRepeats(storms, "begindatetime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "timezone":
				categoryValue = returnNonRepeats(storms, "timezone");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "enddatetime":
				categoryValue = returnNonRepeats(storms, "enddatetime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "directinj":
				categoryValue = returnNonRepeats(storms, "directinj");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "indirectinj":
				categoryValue = returnNonRepeats(storms, "indirectinj");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "directdeaths":
				categoryValue = returnNonRepeats(storms, "directdeaths");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "indirectDeaths":
				categoryValue = returnNonRepeats(storms, "indirectDeaths");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "propertydmg":
				categoryValue = returnNonRepeats(storms, "propertydmg");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "cropdmg":
				categoryValue = returnNonRepeats(storms, "cropdmg");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "sourcetype":
				categoryValue = returnNonRepeats(storms, "sourcetype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "magnitude":
				categoryValue = returnNonRepeats(storms, "magnitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "magnitudetype":
				categoryValue = returnNonRepeats(storms, "magnitudetype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "floodcause":
				categoryValue = returnNonRepeats(storms, "floodcause");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "category":
				categoryValue = returnNonRepeats(storms, "category");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torfscale":
				categoryValue = returnNonRepeats(storms, "torfscale");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torlength":
				categoryValue = returnNonRepeats(storms, "torlength");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "torwidth":
				categoryValue = returnNonRepeats(storms, "torwidth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torwfo":
				categoryValue = returnNonRepeats(storms, "torwfo");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torstate":
				categoryValue = returnNonRepeats(storms, "torstate");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torfips":
				categoryValue = returnNonRepeats(storms, "torfips");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torname":
				categoryValue = returnNonRepeats(storms, "torname");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case  "beginrange":
				categoryValue = returnNonRepeats(storms, "beginrange");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginazimuth":
				categoryValue = returnNonRepeats(storms, "beginazimuth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginlocation":
				categoryValue = returnNonRepeats(storms, "beginlocation");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endrange":
				categoryValue = returnNonRepeats(storms, "endrange");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endazimuth":
				categoryValue = returnNonRepeats(storms, "endazimuth");
				sortBy2.removeAllItems();
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginlatitude":
				categoryValue = returnNonRepeats(storms, "beginlatitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginlongitude":
				categoryValue = returnNonRepeats(storms, "beginlongitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endlatitude":
				categoryValue = returnNonRepeats(storms, "endlatitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endlongitude":
				categoryValue = returnNonRepeats(storms, "endlongitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "episodenarrrative":
				categoryValue = returnNonRepeats(storms, "episodenarrrative");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "eventnarrative":
				categoryValue = returnNonRepeats(storms, "eventnarrative");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "lastmoddate":
				categoryValue = returnNonRepeats(storms, "lastmoddate");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "lastmodtime":
				categoryValue = returnNonRepeats(storms, "lastmodtime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "lastcertdate":
				categoryValue = returnNonRepeats(storms, "lastcertdate");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "lastcerttime":
				categoryValue = returnNonRepeats(storms, "lastcerttime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "lastmod":
				categoryValue = returnNonRepeats(storms, "lastmod");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "lastcert":
				categoryValue = returnNonRepeats(storms, "lastcert");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "addcorrflg":
				categoryValue = returnNonRepeats(storms, "addcorrflg");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "addcorrdate":
				categoryValue = returnNonRepeats(storms, "addcorrdate");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			}
		}
	}

}
