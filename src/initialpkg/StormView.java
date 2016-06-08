package initialpkg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		
		TimelineCircle.initComponents(this);

	}
	
	/**
	 * This method returns a list of strings that includes a unique term from the list of Storm data inputed, which is specified by the inputed type
	 * 
	 * @param array is the input array that contains the Storm data
	 * @param type is the type of data from Storm parameter to sort from
	 * @return the categories non repeated in an arraylist of strings
	 */
	public ArrayList<String> returnNonRepeats(ArrayList<Storm> array, String type){
		ArrayList<String> nonRepeats = new ArrayList<String>();
		
		for (int i = 0; i < array.size(); i++){
			if (!(nonRepeats.contains(array.get(i).displayData(type)))){
				nonRepeats.add(array.get(i).displayData(type));
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
				categoryValue = returnNonRepeats(Main.getSortedBeginYearMonth(), "beginyearmonth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginday":
				categoryValue = returnNonRepeats(Main.getSortedBeginDay(), "beginday");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "begintime":
				categoryValue = returnNonRepeats(Main.getSortedBeginTime(), "begintime");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endyearmonth":
				
				break;
				
			case "endday":
				
				break;
			
			case "endtime":
				
				break; 
				
			case "episodeid":
				sortBy2.removeAllItems();
				for (int i = 0; i < Main.getSortedEpisodeID().size(); i++){
					sortBy2.addItem(Main.getSortedEpisodeID().get(i).displayData("episodeid"));
				}
				break;
				
			case "eventid":
				sortBy2.removeAllItems();
				for (int i = 0; i < Main.getSortedEventID().size(); i++){
					sortBy2.addItem(Main.getSortedEventID().get(i).displayData("eventid"));
				}
				break;
				
			case "state":
				categoryValue = returnNonRepeats(Main.getSortedState(), "state");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "statefips":
				categoryValue = returnNonRepeats(Main.getSortedStateFIPS(), "statefips");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "year":
				categoryValue = returnNonRepeats(Main.getSortedYear(), "year");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "month":
				categoryValue = returnNonRepeats(Main.getSortedMonth(), "month");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "eventtype":
				categoryValue = returnNonRepeats(Main.getSortedEventType(), "eventtype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "cztype":
				categoryValue = returnNonRepeats(Main.getSortedCzType(), "cztype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "czfips":
				
				break;
				
			case "czName":
				categoryValue = returnNonRepeats(Main.getSortedCzName(), "czName");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "wfo":
				categoryValue = returnNonRepeats(Main.getSortedWfo(), "wfo");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "begindatetime":
				
				break;
				
			case "timezone":
				categoryValue = returnNonRepeats(Main.getSortedTimezone(), "timezone");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "enddatetime":
				
				break;
				
			case "directinj":
				categoryValue = returnNonRepeats(Main.getSortedDirectInj(), "directinj");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "indirectinj":
				categoryValue = returnNonRepeats(Main.getSortedIndirectInj(), "indirectinj");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "directdeaths":
				categoryValue = returnNonRepeats(Main.getSortedDirectDeaths(), "directdeaths");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "indirectDeaths":
				categoryValue = returnNonRepeats(Main.getSortedIndirectDeaths(), "indirectDeaths");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "propertydmg":
				categoryValue = returnNonRepeats(Main.getSortedPropertyDmg(), "propertydmg");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "cropdmg":
				categoryValue = returnNonRepeats(Main.getSortedCropDmg(), "cropdmg");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "sourcetype":
				
				break;
				
			case "magnitude":
				categoryValue = returnNonRepeats(Main.getSortedMagnitude(), "magnitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "magnitudetype":
				categoryValue = returnNonRepeats(Main.getSortedMagnitudeType(), "magnitudetype");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "floodcause":
				categoryValue = returnNonRepeats(Main.getSortedFloodCause(), "floodcause");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "category":
				
				break;
				
			case "torfscale":
				categoryValue = returnNonRepeats(Main.getsortedTorFSCale(), "torfscale");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torlength":
				categoryValue = returnNonRepeats(Main.getSortedTorLength(), "torlength");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
			
			case "torwidth":
				categoryValue = returnNonRepeats(Main.getSortedTorWidth(), "torwidth");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torwfo":
				
				break;
				
			case "torstate":
				categoryValue = returnNonRepeats(Main.getSortedTorState(), "torstate");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "torfips":
				
				break;
				
			case "torname":
				categoryValue = returnNonRepeats(Main.getSortedTorName(), "torname");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case  "beginrange":
				
				break;
				
			case "beginazimuth":
			
				break;
				
			case "beginlocation":
				categoryValue = returnNonRepeats(Main.getSortedBeginLocation(), "beginlocation");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endrange":
				
				break;
				
			case "endazimuth":
				
				break;
				
			case "beginlatitude":
				categoryValue = returnNonRepeats(Main.getSortedBeginLatitude(), "beginlatitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "beginlongitude":
				categoryValue = returnNonRepeats(Main.getSortedBeginLongitude(), "beginlongitude");
				sortBy2.removeAllItems();
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i));
				}
				break;
				
			case "endlatitude":
				
				break;
				
			case "endlongitude":
				
				break;
				
			case "episodenarrrative":
				
				break;
				
			case "eventnarrative":
				
				break;
				
			case "lastmoddate":
				
				break;
				
			case "lastmodtime":
				
				break;
				
			case "lastcertdate":
				
				break;
				
			case "lastcerttime":
				
				break;
				
			case "lastmod":
				
				break;
				
			case "lastcert":
				
				break;
				
			case "addcorrflg":
				
				break;
				
			case "addcorrdate":
				
				break;
			}
		}
	}

}
