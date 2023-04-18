package Utility;

public class CreateRaceId {

	public String createRaceId(String year, Integer placeNum, Integer countNum, Integer dayNum, Integer raceNum) {
		// 開催地番号
		String placeNumString = makeTwoDigits(convertInyegerToString(placeNum));
		// 開催回数
		String countNumString = makeTwoDigits(convertInyegerToString(countNum));
		// 開催日数
		String dayNumString = makeTwoDigits(convertInyegerToString(dayNum));
		// レース番号
		String raceNumString = makeTwoDigits(convertInyegerToString(raceNum));

		String raceId = year + placeNumString + countNumString + dayNumString + raceNumString;

		return raceId;
	}

	private String convertInyegerToString(Integer target){
		return String.valueOf(target);
	}

	private String makeTwoDigits(String target){
		if (target.length() == 2) {
			return target;
		}
		return "0" + target;
	}
}
