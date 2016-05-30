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
	
	public String checkType(String type){
		switch (type.toLowerCase()) {
		
		case "cztype":
			return ((Object)this.czType).getClass().getSimpleName();
		
		case "beginyearmonth":
			return this.beginYearMonth.getClass().getSimpleName();
			
		case "endyearmonth":
			return this.endYearMonth.getClass().getSimpleName();
			
		case "state":
			return this.state.getClass().getSimpleName();
			
		case "month":
			return this.month.getClass().getSimpleName();
			
		case "eventtype":
			return this.eventType.getClass().getSimpleName();
			
		case "czname":
			return this.czName.getClass().getSimpleName();
			
		case "wfo":
			return this.wfo.getClass().getSimpleName();
			
		case "begindatetime":
			return this.beginDateTime.getClass().getSimpleName();
			
		case "timezone":
			return this.timezone.getClass().getSimpleName();
			
		case "enddatetime":
			return this.endDateTime.getClass().getSimpleName();
			
		case "propertydmg":
			return this.propertyDmg.getClass().getSimpleName();
			
		case "cropdmg":
			return this.cropDmg.getClass().getSimpleName();
		
		case "sourcetype":
			return this.sourceType.getClass().getSimpleName();
			
		case "magnitudetype":
			return this.magnitudeType.getClass().getSimpleName();
			
		case "floodcause":
			return this.floodCause.getClass().getSimpleName();
			
		case "category":
			return this.category.getClass().getSimpleName();
			
		case "torfscale":
			return this.torFScale.getClass().getSimpleName();
			
		case "torwfo":
			return this.torwfo.getClass().getSimpleName();
			
		case "torstate":
			return this.torState.getClass().getSimpleName();
			
		case "beginazimuth":
			return this.beginAzimuth.getClass().getSimpleName();
			
		case "beginlocation":
			return beginLocation.getClass().getSimpleName();
			
		case "endazimuth":
			return this.endAzimuth.getClass().getSimpleName();
			
		case "endlocation":
			return this.endLocation.getClass().getSimpleName();
			
		case "episodenarrative":
			return this.episodeNarrative.getClass().getSimpleName();
			
		case "eventnarrative":
			return this.eventNarrative.getClass().getSimpleName();
			
		case "lastmoddate":
			return this.lastModDate.getClass().getSimpleName();
		
		case "lastcertdate":
			return lastCertDate.getClass().getSimpleName();
			
		case "lastcerttime":
			return this.lastCertTime.getClass().getSimpleName();
			
		case "lastmod":
			return this.lastMod.getClass().getSimpleName();
			
		case "lastcert":
			return this.lastCert.getClass().getSimpleName();
			
		case "addcorrflg":
			return this.addCorrFlg.getClass().getSimpleName();
			
		case "addcorrdate":
			return this.addCorrDate.getClass().getSimpleName();
			
		case "beginday":
			return ((Object)this.beginDay).getClass().getSimpleName();
		
		case "begintime":
			return ((Object)this.beginTime).getClass().getSimpleName();
			
		case "endday":
			return ((Object)this.endDay).getClass().getSimpleName();
			
		case "endtime":
			return ((Object)this.endTime).getClass().getSimpleName();
			
		case "episodeid":
			return ((Object)this.episodeID).getClass().getSimpleName();
			
		case "eventid":
			return ((Object)this.eventID).getClass().getSimpleName();
			
		case "statefips":
			return ((Object)this.stateFIPS).getClass().getSimpleName();
			
		case "year":
			return ((Object)this.year).getClass().getSimpleName();
			
		case "czfips":
			return ((Object)this.czFips).getClass().getSimpleName();
		
		case "directinj":
			return ((Object)this.directInj).getClass().getSimpleName();
			
		case "indirectinj":
			return ((Object)this.indirectInj).getClass().getSimpleName();
			
		case "directdeaths":
			return ((Object)this.directDeaths).getClass().getSimpleName();
			
		case "indirectdeaths":
			return ((Object)this.indirectDeaths).getClass().getSimpleName();
			
		case "torfips":
			return ((Object)this.torFIPS).getClass().getSimpleName();
			
		case "magnitude":
			return ((Object)this.magnitude).getClass().getSimpleName();
			
		case "torlength":
			return ((Object)this.torLength).getClass().getSimpleName();
			
		case "beginrange":
			return ((Object)this.beginRange).getClass().getSimpleName();
			
		case "endrange":
			return ((Object)this.endRange).getClass().getSimpleName();
			
		case "beginlatitude":
			return ((Object)this.beginLatitude).getClass().getSimpleName();
			
		case "beginlongitude":
			return ((Object)this.beginLongitude).getClass().getSimpleName();
			
		case "endlatitude":
			return ((Object)this.endLatitude).getClass().getSimpleName();
			
		case "endlongitude":
			return ((Object)this.endLongitude).getClass().getSimpleName();
			
		case "torwidth":
			return ((Object)this.torWidth).getClass().getSimpleName();
		}
		
		return "Nothing";
	}
	
	public char getDataChar(String type){
		switch (type.toLowerCase()){
		
		case "cztype":
			return this.czType;
		}
		return 'N';
	}
	
	public int getDataInt(String type){
		switch (type.toLowerCase()){
		
		case "beginday":
			return this.beginDay; 
		
		case "begintime":
			return this.beginTime;
			
		case "endday":
			return this.endDay;
			
		case "endtime":
			return this.endTime;
			
		case "episodeid":
			return this.episodeID;
			
		case "eventID":
			return this.eventID;
			
		case "statefips":
			return this.stateFIPS;
			
		case "year":
			return this.year;
			
		case "czfips":
			return this.czFips;
		
		case "directinj":
			return this.directInj;
			
		case "indirectinj":
			return this.indirectInj;
			
		case "directdeaths":
			return this.directDeaths;
			
		case "indirectdeaths":
			return this.indirectDeaths;
			
		case "torfips":
			return this.torFIPS;
		}
		return -1;
	}
	
	public double getDataDouble(String type){
		switch (type.toLowerCase()){
			
		case "magnitude":
			return this.magnitude;
			
		case "torlength":
			return this.torLength;
			
		case "beginrange":
			return this.beginRange;
			
		case "endrange":
			return this.endRange;
			
		case "beginlatitude":
			return this.beginLatitude;
			
		case "beginlongitude":
			return this.beginLongitude;
			
		case "endlatitude":
			return this.endLatitude;
			
		case "endlongitude":
			return this.endLongitude;
			
		case "torwidth":
			return this.torWidth;
		}
		
		return -1;
	}
	
	public String getDataString(String type){
		switch (type.toLowerCase()) {
		
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
			
		}
		return "-1";
	}
	
	public String getData(String type){
		switch (type.toLowerCase()){
		
		case "beginyearmonth":
			return this.beginYearMonth;
			
		case "beginday":
			return String.valueOf(this.beginDay);
		
		case "begintime":
			return String.valueOf(this.beginTime);
			
		case "endyearmonth":
			return String.valueOf(this.endYearMonth);
			
		case "endday":
			return String.valueOf(this.endDay);
		
		case "endtime":
			return String.valueOf(this.endTime); 
			
		case "episodeid":
			return String.valueOf(this.episodeID);
			
		case "eventid":
			return String.valueOf(this.eventID);
			
		case "state":
			return this.state;
		
		case "statefips":
			return String.valueOf(this.stateFIPS);
			
		case "year":
			return String.valueOf(this.year);
			
		case "month":
			return this.month;
			
		case "eventType":
			return this.eventType;
			
		case "cztype":
			return String.valueOf(this.czType);
			
		case "czfips":
			return String.valueOf(this.czFips);
			
		case "czName":
			return this.czName;
			
		case "wfo":
			return this.wfo;
			
		case "begindatetime":
			return this.beginDateTime;
			
		case "timezone":
			return this.timezone;
			
		case "enddatetime":
			return this.endDateTime;
			
		case "directinj":
			return String.valueOf(this.indirectInj);
			
		case "indirectinj":
			return String.valueOf(this.indirectInj);
			
		case "directdeaths":
			return String.valueOf(this.directDeaths);
			
		case "indirectDeaths":
			return String.valueOf(this.indirectDeaths);
			
		case "propertydmg":
			return this.propertyDmg;
			
		case "cropdmg":
			return this.cropDmg;
			
		case "sourcetype":
			return this.sourceType;
			
		case "magnitdue":
			return String.valueOf(this.magnitude);
			
		case "magnitudetype":
			return this.magnitudeType;
			
		case "floodcause":
			return this.floodCause;
			
		case "category":
			return this.category;
			
		case "torfscale":
			return this.torFScale;
			
		case "torLength":
			return String.valueOf(this.torLength);
			
			
			
		}
		

		this.torWidth 
		this.torwfo 
		this.torState 
		this.torFIPS 
		this.torName 
		this.beginRange 
		this.beginAzimuth 
		this.beginLocation
		this.endRange 
		this.endAzimuth 
		this.endLocation 
		this.beginLatitude 
		this.beginLongitude
		this.endLatitude 
		this.endLongitude 
		this.episodeNarrative
		this.eventNarrative
		this.lastModDate
		this.lastModTime
		this.lastCertDate
		this.lastCertTime 
		this.lastMod
		this.lastCert 
		this.addCorrFlg
		this.addCorrDate
	}

} 
