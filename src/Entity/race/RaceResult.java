package Entity.race;

import lombok.Data;

@Data
public class RaceResult {
	/** レースID */
	private String raceId;
	/** 順位 */
	private String rank;
	/** 枠順 */
	private String waku;
	/** 馬番 */
	private String horseNumber;
	/** 馬名 */
	private String horseName;
	/** 馬ID */
	private String horseId;
	/** 性別 */
	private String gender;
	/** 当時の年齢 */
	private String atThatAge;
	/** 斤量 */
	private String jockeyWeight;
	/** ジョッキー名 */
	private String jockeyName;
	/** ジョッキーID */
	private String jockeyId;
	/** レースタイム */
	private String raceTime;
	/** 着差 */
	private String arrival;
	/** 人気 */
	private String popularity;
	/** オッズ */
	private String odds;
	/** 上がり３ハロンタイム */
	private String secondHalfThreeFurlongTime;
	/** コーナー順位 */
	private String cornerAccessLocation;
	/** 調教師 */
	private String trainer;
	/** 調教師ID */
	private String trainerId;
	/** トレーニングセンター */
	private String trainingCenter;

}
