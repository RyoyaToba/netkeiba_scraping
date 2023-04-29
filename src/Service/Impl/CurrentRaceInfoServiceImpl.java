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
 *  レース情報を作成するクラス
 * */
public class CurrentRaceInfoServiceImpl implements RaceInfoService {
	@Override
	public RaceInfo createRaceInfo(String raceId) throws IOException {

		return null;
	}

	public List<RaceInfo> createRaceInfoList(List<String> raceIdList) throws IOException {

		List<RaceInfo> raceInfoList = new ArrayList<>();
		int nextSkipFlg = 0;
		int loopCount = 1;

		System.out.println("=================Scraping Start==================");

		for(String raceId : raceIdList) {

			if (nextSkipFlg == 1 && raceId.substring(10, 12).equals("12")) {
				nextSkipFlg = 0;
				continue;
			}

			if (nextSkipFlg == 1) {
				continue;
			}

			RaceInfo raceInfo = new RaceInfo();

			String year = raceId.substring(0, 4);

			String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId
					+ NetkeibaURL.CURRENT_RACE_RESULT_PAGE_AFTER_URL;

			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			Document document = Jsoup.connect(url).get();
			Elements pageTitle = document.select("title");

			if (pageTitle.outerHtml().equals("<title>  |    - netkeiba.com</title>")) {
				nextSkipFlg = 1;
				continue;
			}

			raceInfo.setRaceId(raceId);

			// レースの名前の取得（例：三歳未勝利戦）
			Elements raceNaming = document.select("div.raceName");
			for (Element element : raceNaming) {
				if (element == null) {
					raceInfo.setRaceName("");
				} else {
					raceInfo.setRaceName(element.text());
				}
			}

			// 日付取得（例：４月４日（））
			Elements dd = document.select("dd.Active");
			for (Element element : dd) {
				Date raceDay = convertRaceDayStringToDate(year, element.text());
				raceInfo.setRaceDay(raceDay);
			}

			// 芝 or ダート + 距離
			Elements raceData01 = document.select(".RaceData01 > span:nth-of-type(1)");
			for (Element element : raceData01) {

				Map<String, String> raceTypeAndDistanceMap = createRaceTypeAndDistance(element.text());
				raceInfo.setRaceType(raceTypeAndDistanceMap.get("raceType"));
				raceInfo.setDistance(raceTypeAndDistanceMap.get("distance"));
			}

			// 馬場状態
			Elements raceData02 = document.select(".RaceData01 > span:nth-of-type(3)");
			for (Element element : raceData02) {
				if (element == null) {
					raceInfo.setFieldCondition(" ");
				} else {
					String field = (element.text()).substring(2, (element.text().length()));
					field = convertFieldConditionStringToNumber(field);
					raceInfo.setFieldCondition(field);
				}
			}
		System.out.println("【" + raceId + "】" + "total : " + loopCount);

		raceInfoList.add(raceInfo);
		loopCount ++;
		}
		return raceInfoList;
	}

	private Map<String, String> createRaceTypeAndDistance(String raceData) {

		Map<String, String> map = new HashMap<>();
		// TODO think of a better logic
		String raceType = raceData.substring(0,1);
		String distance = raceData.substring(1,raceData.indexOf("m"));

		map.put("raceType", convertRaceTypeStringToNumber(raceType));
		map.put("distance", distance);
		return map;
	}

	private String convertRaceTypeStringToNumber(String raceType){

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


	private String convertFieldConditionStringToNumber(String fieldCondition){

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

	private Date convertRaceDayStringToDate(String year,String raceDay){
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
