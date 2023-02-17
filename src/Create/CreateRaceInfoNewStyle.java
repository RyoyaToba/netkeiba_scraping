package Create;

import java.io.IOException;

import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.race.RaceInfo;

public class CreateRaceInfoNewStyle {

	public RaceInfo createEntityNewStyle(String raceId) throws IOException {

		RaceInfo raceInfo = new RaceInfo();

		String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId
				   + NetkeibaURL.CURRENT_RACE_RESULT_PAGE_AFTER_URL;

		Document document = Jsoup.connect(url).get();
		Elements pageTitle = document.select("title");

		if (pageTitle.outerHtml().equals("<title>  |    - netkeiba.com</title>")) {
			return null;
		}

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
			raceInfo.setRaceDay(element.text());
		}

		// 芝１８００
		Elements raceData01 = document.select(".RaceData01 > span:nth-of-type(1)");
		for (Element element : raceData01) {
			raceInfo.setRaceDetail(element.text());
		}

		// 馬場状態
		Elements raceData02 = document.select(".RaceData01 > span:nth-of-type(3)");
		for (Element element : raceData02) {
			if (element == null) {
				raceInfo.setFeild(" ");
			} else {
				String feild = (element.text()).substring(2, (element.text().length()));
				raceInfo.setFeild(feild);
			}
		}
		return raceInfo;
	}
}
