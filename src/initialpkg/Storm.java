package initialpkg;

public class Storm {

	private String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	private String beginYearMonth, endYearMonth, state, month, eventType, czName, wfo, beginDateTime, timezone, endDateTime, propertyDmg, cropDmg, sourceType, magnitudeType, floodCause, category, torFScale, torwfo, torState, torName, beginAzimuth, beginLocation, endAzimuth, endLocation, episodeNarrative, eventNarrative, lastModDate, lastModTime, lastCertDate, lastCertTime, lastMod, lastCert, addCorrFlg, addCorrDate;
	private int beginDay, beginTime, endDay, endTime, episodeID, eventID, stateFIPS, year, czFips, directInj, indirectInj, directDeaths, indirectDeaths, torWidth, torFIPS;
	private double magnitude, torLength, beginRange, endRange, beginLatitude, beginLongitude, endLatitude, endLongitude;
	private char czType;
	
	public Storm(String beginYearMonth, int beginDay, int beginTime, String endYearMonth, int endDay, int endTime, 
			int episodeID, int eventID, String state, int stateFIPS, int year, String month, String eventType, char czType, 
			int czFips, String czName, String wfo, String beginDateTime, String timezone, String endDateTime, int directInj, 
			int indirectInj, int directDeaths, int indirectDeaths, String propertyDmg, String cropDmg, String sourceType, double magnitude,
			String magnitudeType,  String floodCause, String category, String torFScale, double torLength, int torWidth, String torwfo,
			String torState, int torFIPS, String torName, double beginRange, String beginAzimuth, String beginLocation, double endRange,
			String endAzimuth, String endLocation, double beginLatitude, double beginLongitude, double endLatitude, double endLongitude, 
			String episodeNarrative, String eventNarrative, String lastModDate, String lastModTime, String lastCertDate, 
			String lastCertTime, String lastMod, String lastCert, String addCorrFlg, String addCorrDate ){
		
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
	
}
