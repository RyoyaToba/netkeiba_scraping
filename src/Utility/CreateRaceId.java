package Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public static List<String> createRaceIdList(String year) {

		CreateRoopCounter createRoopCounter = new CreateRoopCounter();
		CreateRaceId createRaceId = new CreateRaceId();

		List<String> raceIdList = new ArrayList<>();

		for (int placeNum = 9; placeNum <= 9; placeNum++) {
			Map<String, Integer> counterMap = createRoopCounter.createRoopCounter2(year, placeNum);
			int countNumCounter = counterMap.get("countNumCounter");
			int dayNumCounter = counterMap.get("dayNumCounter");

			for (int countNum = 1; countNum <= countNumCounter; countNum++) {

					/* 開催日のループ */
					for (int dayNum = 1; dayNum <= dayNumCounter; dayNum++) {

							/* レースRのループ */
						for (int raceNum = 1; raceNum <= 12; raceNum++) {
							String raceId = createRaceId.createRaceId(year, placeNum, countNum, dayNum, raceNum);
							raceIdList.add(raceId);
						}
					}
				}
			}
		return raceIdList;
	}
}
