package Utility;

import Enum.PlaceDetails.*;
import Enum.PlaceList;

import java.util.HashMap;
import java.util.Map;

public class CreateRoopCounter {

	public Map<String, Integer> createRoopCounter2(Integer year, Integer placeNum){

		Integer countNumCounter = 0;
		Integer dayNumCounter = 0;

		String targetYear = "Y" + year;

		switch(placeNum){
			case 1:
				countNumCounter = Integer.parseInt(Sapporo.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Sapporo.getData(targetYear).get("dayNum"));
				break;
			case 2:
				countNumCounter = Integer.parseInt(Hakodate.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Hakodate.getData(targetYear).get("dayNum"));
				break;
			case 3:
				countNumCounter = Integer.parseInt(Fukushima.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Fukushima.getData(targetYear).get("dayNum"));
				break;
			case 4:
				countNumCounter = Integer.parseInt(Nigata.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Nigata.getData(targetYear).get("dayNum"));
				break;
			case 5:
				countNumCounter = Integer.parseInt(Tokyo.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Tokyo.getData(targetYear).get("dayNum"));
				break;
			case 6:
				countNumCounter = Integer.parseInt(Nakayama.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Nakayama.getData(targetYear).get("dayNum"));
				break;
			case 7:
				countNumCounter = Integer.parseInt(Chukyo.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Chukyo.getData(targetYear).get("dayNum"));
				break;
			case 8:
				countNumCounter = Integer.parseInt(Kyoto.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Kyoto.getData(targetYear).get("dayNum"));
				break;
			case 9:
				countNumCounter = Integer.parseInt(Hanshin.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Hanshin.getData(targetYear).get("dayNum"));
				break;
			case 10:
				countNumCounter = Integer.parseInt(Kokura.getData(targetYear).get("countNum"));
				dayNumCounter = Integer.parseInt(Kokura.getData(targetYear).get("dayNum"));
				break;
		}

		Map<String, Integer> counterMap = new HashMap<>();
		counterMap.put("countNumCounter", countNumCounter);
		counterMap.put("dayNumCounter", dayNumCounter);
		return counterMap;
	}



	public Map<String, Integer> createRoopCounter(Integer placeNum) {

		Map<String, Integer> counterMap = new HashMap<>();

		Integer countNumCounter = 0;
		Integer dayNumCounter = 0;

		switch (placeNum) {// 2005
		case 1: // 札幌
			countNumCounter = 2; // 回
			dayNumCounter = 8; // 日
			break;
		case 2: // 函館
			countNumCounter = 2;
			dayNumCounter = 8;
			break;
		case 3: // 福島
			countNumCounter = 3;
			dayNumCounter = 8;
			break;
		case 4: // 新潟
			countNumCounter = 3;
			dayNumCounter = 8;
			break;
		case 5: // 東京
			countNumCounter = 5;
			dayNumCounter = 8;
			break;
		case 6: // 中山
			countNumCounter = 5;
			dayNumCounter = 8;
			break;
		case 7: // 中京
			countNumCounter = 3;
			dayNumCounter = 8;
			break;
		case 8: // 京都
			countNumCounter = 5;
			dayNumCounter = 8;
			break;
		case 9: // 阪神
			countNumCounter = 5;
			dayNumCounter = 8;
			break;
		case 10: // 小倉
			countNumCounter = 3;
			dayNumCounter = 8;
		}

		counterMap.put("countNumCounter", countNumCounter);
		counterMap.put("dayNumCounter", dayNumCounter);
		return counterMap;
	}
}
