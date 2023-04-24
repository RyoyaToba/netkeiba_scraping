package Service.Impl;

import java.io.IOException;
import java.util.HashMap;
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

		RaceInfo raceInfo = new RaceInfo();

		String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId
				   + NetkeibaURL.CURRENT_RACE_RESULT_PAGE_AFTER_URL;

		Document document = Jsoup.connect(url).get();
		Elements pageTitle = document.select("title");

		if (pageTitle.outerHtml().equals("<title>  |    - netkeiba.com</title>")) {
			return null;
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
		// TODO convert String -> Date or DataTime
		Elements dd = document.select("dd.Active");
		for (Element element : dd) {
			raceInfo.setRaceDay(element.text());
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
				raceInfo.setField(" ");
			} else {
				String field = (element.text()).substring(2, (element.text().length()));
				field = convertFieldStringToNumber(field);
				raceInfo.setField(field);
			}
		}
		return raceInfo;
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


	private String convertFieldStringToNumber(String field){

		field = field.substring(field.indexOf(":") + 1, field.length());

		System.out.println(field);

		switch (field){

			case "良":
				field = "1";
				break;
			default:
				field = null;

		}
		return field;
	}
}
