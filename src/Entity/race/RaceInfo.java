package Entity.race;

import lombok.Data;

import java.sql.Date;

@Data
public class RaceInfo {

	private String raceId;
	private Date raceDay;
	private String raceName;
	private String raceType;
	private String distance;
	private String fieldCondition;

}
