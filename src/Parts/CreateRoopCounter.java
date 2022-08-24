package Parts;

import java.util.HashMap;
import java.util.Map;

public class CreateRoopCounter {

	public Map<String, Integer> createRoopCounter(Integer placeNum) {

		Map<String, Integer> counterMap = new HashMap<>();

		Integer countNumCounter = 0;
		Integer dayNumCounter = 0;

		switch (placeNum) {
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
			dayNumCounter = 10;
			break;
		case 4: // 新潟
			countNumCounter = 3;
			dayNumCounter = 8;
			break;
		case 5: // 東京
			countNumCounter = 5;
			dayNumCounter = 9;
			break;
		case 6: // 中山
			countNumCounter = 5;
			dayNumCounter = 8;
			break;
		case 7: // 中京
			countNumCounter = 3;
			dayNumCounter = 10;
			break;
		case 8: // 京都
			countNumCounter = 5;
			dayNumCounter = 9;
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
