package Utility;

import Enum.PlaceDetails.*;

import java.util.HashMap;
import java.util.Map;

public class CreateLoopCounter {

	/**
	 *
	 *
	 * */
	public Map<String, Integer> createLoopCounter(String year, Integer placeNum){

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

}
