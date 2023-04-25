package Entity.race;

import java.sql.Date;

public class RaceInfo {

	private String raceId;
	private Date raceDay;
	private String raceName;
	private String raceType;
	private String distance;
	private String fieldCondition;

	public String getRaceId() {
		return raceId;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public Date getRaceDay() {
		return raceDay;
	}

	public void setRaceDay(Date raceDay) {
		this.raceDay = raceDay;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRaceType() {
		return raceType;
	}

	public void setRaceType(String raceType) {this.raceType = raceType;}

	public String getFieldCondition() {
		return fieldCondition;
	}

	public void setFieldCondition(String fieldCondition) {
		this.fieldCondition = fieldCondition;
	}

}
