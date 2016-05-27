package initialpkg;

public class Storm {

	private String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	private String beginYearMonth, endYearMonth, state, month, eventType, czName, wfo, beginDateTime, timezone, endDateTime, propertyDmg, cropDmg, sourceType, magnitudeType, floodCause, category, torFScale, torwfo, torState, torName, beginAzimuth, beginLocation, endAzimuth, endLocation, episodeNarrative, eventNarrative, lastModDate, lastModTime, lastCertDate, lastCertTime, lastMod, lastCert, addCorrFlg, addCorrDate;
	private int beginDay, beginTime, endDay, endTime, episodeID, eventID, stateFIPS, year, czFips, directInj, indirectInj, directDeaths, indirectDeaths, torFIPS;
	private double magnitude, torLength, beginRange, endRange, beginLatitude, beginLongitude, endLatitude, endLongitude, torWidth;
	private char czType;
	
	
	public Storm(String beginYearMonth, int beginDay, int beginTime, String endYearMonth, int endDay, int endTime, 
			int episodeID, int eventID, String state, int stateFIPS, int year, String month, String eventType, char czType, 
			int czFips, String czName, String wfo, String beginDateTime, String timezone, String endDateTime, int directInj, 
			int indirectInj, int directDeaths, int indirectDeaths, String propertyDmg, String cropDmg, String sourceType, double magnitude,
			String magnitudeType,  String floodCause, String category, String torFScale, double torLength, double torWidth, String torwfo,
			String torState, int torFIPS, String torName, double beginRange, String beginAzimuth, String beginLocation, double endRange,
			String endAzimuth, String endLocation, double beginLatitude, double beginLongitude, double endLatitude, double endLongitude, 
			String episodeNarrative, String eventNarrative, String lastModDate, String lastModTime, String lastCertDate, 
			String lastCertTime, String lastMod, String lastCert, String addCorrFlg, String addCorrDate){
		
		this.beginTime = beginTime;
		this.endYearMonth = endYearMonth;
		this.endDay = endDay;
		this.endTime = endTime;
		this.episodeID = episodeID;
		this.eventID = eventID;
		this.state = state;
		this.stateFIPS = stateFIPS;
		this.year = year;
		this.month = month;
		this.eventType = eventType;
		this.czType = czType;
		this.czFips = czFips;
		this.czName = czName;
		this.wfo = wfo;
		this.beginDateTime = beginDateTime;
		this.timezone = timezone;
		this.endDateTime = endDateTime;
		this.directInj = directInj;
		this.indirectInj = indirectInj;
		this.directDeaths = directDeaths;
		this.indirectDeaths = indirectDeaths;
		this.propertyDmg = propertyDmg;
		this.cropDmg = cropDmg;
		this.sourceType = sourceType;
		this.magnitude = magnitude;
		this.magnitudeType = magnitudeType;
		this.floodCause = floodCause;
		this.category = category;
		this.torFScale = torFScale;
		this.torLength = torLength;
		this.torWidth = torWidth;
		this.torwfo = torwfo;
		this.torState = torState;
		this.torFIPS = torFIPS;
		this.torName = torName;
		this.beginRange = beginRange;
		this.beginAzimuth = beginAzimuth;
		this.beginLocation = beginLocation;
		this.endRange = endRange;
		this.endAzimuth = endAzimuth;
		this.endLocation = endLocation;
		this.beginLatitude = beginLatitude;
		this.beginLongitude = beginLongitude;
		this.endLatitude = endLatitude;
		this.endLongitude = endLongitude;
		this.episodeNarrative = episodeNarrative;
		this.eventNarrative = eventNarrative;
		this.lastModDate = lastModDate;
		this.lastModTime = lastModTime;
		this.lastCertDate = lastCertDate;
		this.lastCertTime = lastCertTime;
		this.lastMod = lastMod;
		this.lastCert = lastCert;
		this.addCorrFlg = addCorrFlg;
		this.addCorrDate = addCorrDate;
	}
	
	public int getEventID(){
		return this.eventID;
	}
	
	public String getData(String type){
		switch (type.toLowerCase()) {
		
		case "beginyearmonth":
			return this.beginYearMonth;
			
		case "endyearmonth":
			return this.endYearMonth;
			
		case "state":
			return this.state;
			
		case "month":
			return this.month;
			
		case "eventtype":
			return this.eventType;
			
		case "czname":
			return this.czName;
			
		case "wfo":
			return this.wfo;
			
		case "begindatetime":
			return this.beginDateTime;
			
		case "timezone":
			return this.timezone;
			
		case "enddatetime":
			return this.endDateTime;
			
		case "propertydmg":
			return this.propertyDmg;
			
		case "cropdmg":
			return this.cropDmg;
		
		case "sourcetype":
			return this.sourceType;
			
		case "magnitudetype":
			return this.magnitudeType;
			
		case "floodcause":
			return this.floodCause;
			
		case "category":
			return this.category;
			
		case "torfscale":
			return this.torFScale;
			
		case "torwfo":
			return this.torwfo;
			
		case "torstate":
			return this.torState;
			
		case "beginazimuth":
			return this.beginAzimuth;
			
		case "beginlocation":
			return this.beginLocation;
			
		case "endazimuth":
			return this.endAzimuth;
			
		case "endlocation":
			return this.endLocation;
			
		case "episodenarrative":
			return this.episodeNarrative;
			
		case "eventnarrative":
			return this.eventNarrative;
			
		case "lastmoddate":
			return this.lastModDate;
		
		case "lastcertdate":
			return this.lastCertDate;
			
		case "lastcerttime":
			return this.lastCertTime;
			
		case "lastmod":
			return this.lastMod;
			
		case "lastcert":
			return this.lastCert;
			
		case "addcorrflg":
			return this.addCorrFlg;
			
		case "addcorrdate":
			return this.addCorrDate;
			
		}
		return "Incorrect input type";
	}
}

