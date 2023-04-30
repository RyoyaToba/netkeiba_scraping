package Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entity.race.RaceInfo;
import Service.RaceResultService;
import Entity.race.RaceResult;
import Utility.NetkeibaURL;
import Utility.RaceTerm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CurrentRaceResultServiceImpl {

	/** 取得したHTMLから、horseIdを取得して戻す*/
	public static String returnHorseId(Element element){

		Integer horseIdStart = element.outerHtml().indexOf("horse/") + 6;
		Integer horseIdEnd = horseIdStart + 10;
		String horseId = element.outerHtml().substring(horseIdStart, horseIdEnd);
		return horseId;
	}

	/** 取得したHTMLから、jockeyIdを取得して戻す*/
	public static String returnJockeyId(Element element){

		Integer jockeyIdStart = element.outerHtml().indexOf("recent/") + 7;
		Integer jockeyIdEnd = jockeyIdStart + 5;
		String jockeyId = element.outerHtml().substring(jockeyIdStart, jockeyIdEnd);
		return jockeyId;
	}


	/** 取得したHTMLから、調教師とトレセンを分離し取得して戻す*/
	public static Map<String, String> separateTrainerAndTrainingCenter(String target){
		Map<String, String> map = new HashMap<>();
		String trainingCenter = target.substring(0,2);
		String trainer = target.substring(2,target.length());
		map.put("trainingCenter", trainingCenter);
		map.put("trainer", trainer);
		return map;
	}

	/** 馬の性別をDB格納用の数値情報に変換する*/
	public static String convertGenderStringToNumber(String gender){

		if (gender.length() > 1){
			gender = gender.substring(0,1);
		}

		String genderNumber = null;

		switch (gender){
			case "牡":
				genderNumber = "1";
				break;
			case "牝":
				genderNumber = "2";
				break;
			case "セ":
				genderNumber = "3";
				break;
			default:
				genderNumber = null;
		}

		return genderNumber;
	}

	/** 取得したHTMLから、trainerIdを取得して戻す*/
	public static String retriveTrainerId(Element element){

		Integer trainerIdStart = element.outerHtml().indexOf("recent/") + 7;
		Integer trainerIdEnd = trainerIdStart + 5;
		String trainerId = element.outerHtml().substring(trainerIdStart, trainerIdEnd);
		return trainerId;
	}

}
