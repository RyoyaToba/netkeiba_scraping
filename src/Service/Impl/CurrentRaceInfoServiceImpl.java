package Service.Impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Service.RaceInfoService;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.race.RaceInfo;

/**
 *
 * */
public class CurrentRaceInfoServiceImpl {

	public static Map<String, String> createRaceTypeAndDistance(String raceData) {

		Map<String, String> map = new HashMap<>();

		String raceType = raceData.substring(0,1);
		String distance = raceData.substring(1,raceData.indexOf("m"));

		map.put("raceType", convertRaceTypeStringToNumber(raceType));
		map.put("distance", distance);
		return map;
	}

	public static String convertRaceTypeStringToNumber(String raceType){

		// TODO fix hard cording
		switch (raceType){

			case "芝":
				raceType = "1";
				break;
			case "ダ":
				raceType = "2";
				break;
			case "障":
				raceType = "3";
				break;
			default:
				raceType = null;
		}
		return raceType;
	}


	public static String convertFieldConditionStringToNumber(String fieldCondition){

		fieldCondition = fieldCondition.substring(fieldCondition.indexOf(":") + 1, fieldCondition.length());

		switch (fieldCondition){

			case "良":
				fieldCondition = "1";
				break;
			case "稍":
				fieldCondition = "2";
				break;
			case "重":
				fieldCondition = "3";
				break;
			case "不":
				fieldCondition = "4";
				break;
			default:
				fieldCondition = null;

		}
		return fieldCondition;
	}

	public static Date convertRaceDayStringToDate(String year,String raceDay){
		String month = "";
		String day = "";

		if (raceDay.contains("/")) {
			month = raceDay.substring(0,raceDay.indexOf("/"));
			day = raceDay.substring(raceDay.indexOf("/") + 1, raceDay.length());

		} else {
			String trimDayOfWeekDay = raceDay.substring(0, raceDay.indexOf("日") + 1);
			month = trimDayOfWeekDay.substring(0, trimDayOfWeekDay.indexOf("月"));
			day = trimDayOfWeekDay.substring(trimDayOfWeekDay.indexOf("月") + 1, trimDayOfWeekDay.indexOf("日"));
		}

		Date raceDayConvertDate = Date.valueOf(year + "-" + month + "-" + day);

		return raceDayConvertDate;
	}

}
