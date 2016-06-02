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

public class StormView extends JFrame implements ActionListener{
	
	private String [] categories = {"beginYearMonth", "beginDay", "beginTime", "episodeID", "eventID", "state", "stateFIPS", "year", "month", "eventType", "czType", "czFips", "czName", "wfo", "beginDateTime", "timezone", "directInj", "indirectInj", "propertyDmg", "cropDmg", "sourceType", "magnitude", "magnitudeType", "floodCause", "category", "torFScale", "torLength", "torWidth", "torwfo", "torState", "torFIPS", "torName", "beginAzimuth", "beingRange", "beginLocation", "beginLatitude", "beginLongitude", "episodeNarrative", "eventNarrative", "lastModDate", "lastModTime", "lastCertDate", "lastCertTime", "lastMod", "lastCert", "addCorrFlg", "addCorrDate"};
	
	private JComboBox sortBy = new JComboBox(categories);
	private JComboBox sortBy2 = new JComboBox();
	
	StormView(ArrayList<Storm> storms){
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
	
	public ArrayList<Storm> returnNonRepeats(ArrayList<Storm> array, String type){
		
		ArrayList<Storm> nonRepeats = new ArrayList<Storm>();
		
		for (int i = 0; i < array.size(); i++){
			if (!nonRepeats.contains(array.get(i).displayData(type))){
				nonRepeats.add(array.get(i));
			}
		}
		
		return nonRepeats;
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == sortBy){
			JComboBox localSortBy = (JComboBox)e.getSource();
			String category = (String)localSortBy.getSelectedItem();
			
			ArrayList<Storm> categoryValue;
			ArrayList<Storm> storms = Main.getStorms();
			
			switch (category.toLowerCase()){
			
			case "beginyearmonth":
				categoryValue = returnNonRepeats(storms, "beginyearmonth");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginyearmonth"));
				}
				break;
				
			case "beginday":
				categoryValue = returnNonRepeats(storms, "beginday");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginyearmonth"));
				}
				break;
			
			case "begintime":
				categoryValue = returnNonRepeats(storms, "begintime");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("begintime"));
				}
				break;
				
			case "endyearmonth":
				categoryValue = returnNonRepeats(storms, "endyearmonth");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endyearmonth"));
				}
				break;
				
			case "endday":
				categoryValue = returnNonRepeats(storms, "endday");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endday"));
				}
				break;
			
			case "endtime":
				categoryValue = returnNonRepeats(storms, "endtime");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endtime"));
				}
				break; 
				
			case "episodeid":
				categoryValue = returnNonRepeats(storms, "episodeid");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("episodeid"));
				}
				break;
				
			case "eventid":
				categoryValue = returnNonRepeats(storms, "eventid");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("eventid"));
				}
				break;
				
			case "state":
				categoryValue = returnNonRepeats(storms, "state");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("state"));
				}
				break;
			
			case "statefips":
				categoryValue = returnNonRepeats(storms, "statefips");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("statefips"));
				}
				break;
				
			case "year":
				categoryValue = returnNonRepeats(storms, "year");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("year"));
				}
				break;
				
			case "month":
				categoryValue = returnNonRepeats(storms, "month");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("month"));
				}
				break;
				
			case "eventtype":
				categoryValue = returnNonRepeats(storms, "eventtype");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("eventtype"));
				}
				break;
				
			case "cztype":
				categoryValue = returnNonRepeats(storms, "cztype");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("cztype"));
				}
				break;
				
			case "czfips":
				categoryValue = returnNonRepeats(storms, "czfips");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("czfips"));
				}
				break;
				
			case "czName":
				categoryValue = returnNonRepeats(storms, "czName");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("czName"));
				}
				break;
				
			case "wfo":
				categoryValue = returnNonRepeats(storms, "wfo");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("wfo"));
				}
				break;
				
			case "begindatetime":
				categoryValue = returnNonRepeats(storms, "begindatetime");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("begindatetime"));
				}
				break;
				
			case "timezone":
				categoryValue = returnNonRepeats(storms, "timezone");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("timezone"));
				}
				break;
				
			case "enddatetime":
				categoryValue = returnNonRepeats(storms, "enddatetime");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("enddatetime"));
				}
				break;
				
			case "directinj":
				categoryValue = returnNonRepeats(storms, "directinj");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("directinj"));
				}
				break;
				
			case "indirectinj":
				categoryValue = returnNonRepeats(storms, "indirectinj");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("indirectinj"));
				}
				break;
				
			case "directdeaths":
				categoryValue = returnNonRepeats(storms, "directdeaths");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("directdeaths"));
				}
				break;
				
			case "indirectDeaths":
				categoryValue = returnNonRepeats(storms, "indirectDeaths");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("indirectDeaths"));
				}
				break;
				
			case "propertydmg":
				categoryValue = returnNonRepeats(storms, "propertydmg");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("propertydmg"));
				}
				break;
				
			case "cropdmg":
				categoryValue = returnNonRepeats(storms, "cropdmg");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("cropdmg"));
				}
				break;
				
			case "sourcetype":
				categoryValue = returnNonRepeats(storms, "sourcetype");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("sourcetype"));
				}
				break;
				
			case "magnitude":
				categoryValue = returnNonRepeats(storms, "magnitude");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("magnitude"));
				}
				break;
				
			case "magnitudetype":
				categoryValue = returnNonRepeats(storms, "magnitudetype");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("magnitudetype"));
				}
				break;
				
			case "floodcause":
				categoryValue = returnNonRepeats(storms, "floodcause");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("floodcause"));
				}
				break;
				
			case "category":
				categoryValue = returnNonRepeats(storms, "category");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("category"));
				}
				break;
				
			case "torfscale":
				categoryValue = returnNonRepeats(storms, "torfscale");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torfscale"));
				}
				break;
				
			case "torlength":
				categoryValue = returnNonRepeats(storms, "torlength");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torlength"));
				}
				break;
			
			case "torwidth":
				categoryValue = returnNonRepeats(storms, "torwidth");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torwidth"));
				}
				break;
				
			case "torwfo":
				categoryValue = returnNonRepeats(storms, "torwfo");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torwfo"));
				}
				break;
				
			case "torstate":
				categoryValue = returnNonRepeats(storms, "torstate");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torstate"));
				}
				break;
				
			case "torfips":
				categoryValue = returnNonRepeats(storms, "torfips");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torfips"));
				}
				break;
				
			case "torname":
				categoryValue = returnNonRepeats(storms, "torname");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("torname"));
				}
				break;
				
			case  "beginrange":
				categoryValue = returnNonRepeats(storms, "beginrange");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginrange"));
				}
				break;
				
			case "beginazimuth":
				categoryValue = returnNonRepeats(storms, "beginazimuth");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginazimuth"));
				}
				break;
				
			case "beginlocation":
				categoryValue = returnNonRepeats(storms, "beginlocation");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginlocation"));
				}
				break;
				
			case "endrange":
				categoryValue = returnNonRepeats(storms, "endrange");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endrange"));
				}
				break;
				
			case "endazimuth":
				categoryValue = returnNonRepeats(storms, "endazimuth");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endazimuth"));
				}
				break;
				
			case "beginlatitude":
				categoryValue = returnNonRepeats(storms, "beginlatitude");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginlatitude"));
				}
				break;
				
			case "beginlongitude":
				categoryValue = returnNonRepeats(storms, "beginlongitude");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("beginlongitude"));
				}
				break;
				
			case "endlatitude":
				categoryValue = returnNonRepeats(storms, "endlatitude");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endlatitude"));
				}
				break;
				
			case "endlongitude":
				categoryValue = returnNonRepeats(storms, "endlongitude");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("endlongitude"));
				}
				break;
				
			case "episodenarrrative":
				categoryValue = returnNonRepeats(storms, "episodenarrrative");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("episodenarrrative"));
				}
				break;
				
			case "eventnarrative":
				categoryValue = returnNonRepeats(storms, "eventnarrative");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("eventnarrative"));
				}
				break;
				
			case "lastmoddate":
				categoryValue = returnNonRepeats(storms, "lastmoddate");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("lastmoddate"));
				}
				break;
				
			case "lastmodtime":
				categoryValue = returnNonRepeats(storms, "lastmodtime");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("lastmodtime"));
				}
				break;
				
			case "lastcertdate":
				categoryValue = returnNonRepeats(storms, "lastcertdate");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("lastcertdate"));
				}
				break;
				
			case "lastcerttime":
				categoryValue = returnNonRepeats(storms, "lastcerttime");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("lastcerttime"));
				}
				break;
				
			case "lastmod":
				categoryValue = returnNonRepeats(storms, "lastmod");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("lastmod"));
				}
				break;
				
			case "lastcert":
				categoryValue = returnNonRepeats(storms, "lastcert");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("lastcert"));
				}
				break;
				
			case "addcorrflg":
				categoryValue = returnNonRepeats(storms, "addcorrflg");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("addcorrflg"));
				}
				break;
				
			case "addcorrdate":
				categoryValue = returnNonRepeats(storms, "addcorrdate");
				for (int i = 0; i < categoryValue.size(); i++){
					sortBy2.addItem(categoryValue.get(i).displayData("addcorrdate"));
				}
				break;
			}
		}
	}

}
