package Utility;

public class CreateRaceId {

	public String createRaceId(String year, Integer placeNum, Integer countNum, Integer dayNum, Integer raceNum) {

		String raceId = "";
		String placeNumString = "";// 札幌01 函館02 福島03 新潟04 東京05 中山06 中京07 京都08 阪神09 小倉10
		String countNumString = "";// 何回目か ８日＝１回と数えます 2回６日目なら ２×８＋６回開催です
		String dayNumString = "";// 何日目か

		// placeNumを決定している
		if (placeNum != 10) {
			placeNumString = 0 + String.valueOf(placeNum);
		} else {
			placeNumString = String.valueOf(placeNum);
		}

		// dayNumを決定している
		if (dayNum < 10) {
			dayNumString = 0 + String.valueOf(dayNum);
		} else {
			dayNumString = String.valueOf(dayNum);
		}

		countNumString = 0 + String.valueOf(countNum);

		if (raceNum < 10) {
			String raceNumString = "0" + raceNum;
			raceId = year + placeNumString + countNumString + dayNumString + raceNumString;
		} else {
			raceId = year + placeNumString + countNumString + dayNumString + raceNum;
		}

		return raceId;
	}
}
