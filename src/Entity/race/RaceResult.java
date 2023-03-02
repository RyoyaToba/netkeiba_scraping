package Entity.race;

public class RaceResult {

	private String raceId;
	private String rank;
	private Integer waku;
	private Integer horseNumber;
	private String horseName;
	private String horseId;
	private String gender;
	private Integer age;
	private Double jockeyWeight;
	private String jockeyName;
	private String raceTime;

	public String getRaceId() {
		return raceId;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Integer getWaku() {
		return waku;
	}

	public void setWaku(Integer waku) {
		this.waku = waku;
	}

	public Integer getHorseNumber() {
		return horseNumber;
	}

	public void setHorseNumber(Integer horseNumber) {
		this.horseNumber = horseNumber;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getJockeyWeight() {
		return jockeyWeight;
	}

	public void setJockeyWeight(Double jockeyWeight) {
		this.jockeyWeight = jockeyWeight;
	}

	public String getJockeyName() {
		return jockeyName;
	}

	public void setJockeyName(String jockeyName) {
		this.jockeyName = jockeyName;
	}

	public String getRaceTime() {
		return raceTime;
	}

	public void setRaceTime(String raceTime) {
		this.raceTime = raceTime;
	}

	public void setHorseId(String horseId){
		this.horseId = horseId;
	}

	@Override
	public String toString() {
		return "RaceResult [raceId=" + raceId + ", rank=" + rank + ", waku=" + waku + ", horseNumber="
				+ horseNumber + ", horseName=" + horseName + ", gender=" + gender + ", age=" + age + ", jockeyWeight="
				+ jockeyWeight + ", jockeyName=" + jockeyName + ", raceTime=" + raceTime + "]";
	}

}
