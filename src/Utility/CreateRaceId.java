package Utility;

public class CreateRaceId {

	public String createRaceId(String year, Integer placeNum, Integer countNum, Integer dayNum, Integer raceNum) {

		String raceId = "";
		// 開催地番号
		String placeNumString = String.valueOf(placeNum);
		// 開催回数
		String countNumString = "0" + String.valueOf(countNum);
		// 開催日数
		String dayNumString = String.valueOf(dayNum);
		// レース番号
		String raceNumString = String.valueOf(raceNum);

		if (!(placeNumString.equals("10"))) {
			placeNumString = "0" + placeNumString;
		}

		if (!(dayNumString.equals(10))) {
			dayNumString = "0" + dayNumString;
		}

		if (!(raceNumString.equals(10))){
			raceNumString = "0" + raceNumString;
		}

		raceId = year + placeNumString + countNumString + dayNumString + raceNumString;

		return raceId;
	}
}
