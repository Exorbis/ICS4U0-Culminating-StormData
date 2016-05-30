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
		
		this.beginYearMonth = beginYearMonth;
		this.beginDay = beginDay;
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
	
	public String getTest(){
		return this.beginYearMonth;
	}
	
	public char getData(String type){
		switch (type.toLowerCase()){
		
		case "cztype":
			return this.czType;
		}
		return 'Z';
	}
	
	public int getData(String type){
		switch (type.toLowerCase()){
		
		case "beginday":
			return this.beginDay; 
		}
		return -1;
	}
	
	public String getData(String type){
		switch (type.toLowerCase()) {
		
		case "cztype":
			return String.valueOf(this.czType).trim();
		
		case "beginyearmonth":
			return this.beginYearMonth.trim();
			
		case "endyearmonth":
			return this.endYearMonth.trim();
			
		case "state":
			return this.state.trim();
			
		case "month":
			return this.month.trim();
			
		case "eventtype":
			return this.eventType.trim();
			
		case "czname":
			return this.czName.trim();
			
		case "wfo":
			return this.wfo.trim();
			
		case "begindatetime":
			return this.beginDateTime.trim();
			
		case "timezone":
			return this.timezone.trim();
			
		case "enddatetime":
			return this.endDateTime.trim();
			
		case "propertydmg":
			return this.propertyDmg.trim();
			
		case "cropdmg":
			return this.cropDmg.trim();
		
		case "sourcetype":
			return this.sourceType.trim();
			
		case "magnitudetype":
			return this.magnitudeType.trim();
			
		case "floodcause":
			return this.floodCause.trim();
			
		case "category":
			return this.category.trim();
			
		case "torfscale":
			return this.torFScale.trim();
			
		case "torwfo":
			return this.torwfo.trim();
			
		case "torstate":
			return this.torState.trim();
			
		case "beginazimuth":
			return this.beginAzimuth.trim();
			
		case "beginlocation":
			return this.beginLocation.trim();
			
		case "endazimuth":
			return this.endAzimuth.trim();
			
		case "endlocation":
			return this.endLocation.trim();
			
		case "episodenarrative":
			return this.episodeNarrative.trim();
			
		case "eventnarrative":
			return this.eventNarrative.trim();
			
		case "lastmoddate":
			return this.lastModDate.trim();
		
		case "lastcertdate":
			return this.lastCertDate.trim();
			
		case "lastcerttime":
			return this.lastCertTime.trim();
			
		case "lastmod":
			return this.lastMod.trim();
			
		case "lastcert":
			return this.lastCert.trim();
			
		case "addcorrflg":
			return this.addCorrFlg.trim();
			
		case "addcorrdate":
			return this.addCorrDate.trim();
			
		
		case "begintime":
			return String.valueOf(this.beginTime).trim();
			
		case "endday":
			return String.valueOf(this.endDay).trim();
			
		case "endtime":
			return String.valueOf(this.endTime).trim();
			
		case "episodeid":
			return String.valueOf(this.episodeID).trim();
			
		case "eventID":
			return String.valueOf(this.eventID).trim();
			
		case "statefips":
			return String.valueOf(this.stateFIPS).trim();
			
		case "year":
			return String.valueOf(this.year).trim();
			
		case "czfips":
			return String.valueOf(this.czFips).trim();
		
		case "directinj":
			return String.valueOf(this.directInj).trim();
			
		case "indirectinj":
			return String.valueOf(this.indirectInj).trim();
			
		case "directdeaths":
			return String.valueOf(this.directDeaths).trim();
			
		case "indirectdeaths":
			return String.valueOf(this.indirectDeaths).trim();
			
		case "torfips":
			return String.valueOf(this.torFIPS).trim();
			
		case "magnitude":
			return String.valueOf(this.magnitude).trim();
			
		case "torlength":
			return String.valueOf(this.torLength).trim();
			
		case "beginrange":
			return String.valueOf(this.beginRange).trim();
			
		case "endrange":
			return String.valueOf(this.endRange).trim();
			
		case "beginlatitude":
			return String.valueOf(this.beginLatitude).trim();
			
		case "beginlongitude":
			return String.valueOf(this.beginLongitude).trim();
			
		case "endlatitude":
			return String.valueOf(this.endLatitude).trim();
			
		case "endlongitude":
			return String.valueOf(this.endLongitude).trim();
			
		case "torwidth":
			return String.valueOf(this.torWidth).trim();
		}
		return "-1";
	}

} 