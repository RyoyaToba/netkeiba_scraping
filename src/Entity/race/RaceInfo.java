package Entity.race;

import lombok.Data;

import java.sql.Date;

@Data
public class RaceInfo {
    /** レースID */
	private String raceId;
	/** 開催日 */
	private Date raceDay;
	/** レース名 */
	private String raceName;
	/** レースタイプ */
	private String raceType;
	/** 距離 */
	private String distance;
	/** 馬場状態 */
	private String fieldCondition;

}
