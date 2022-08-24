package Create;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.RaceInfoEntity;

public class CreateRaceInfoNewStyle {

	public RaceInfoEntity createEntityNewStyle(String raceId) throws IOException {

		RaceInfoEntity raceInfo = new RaceInfoEntity();

		String url = "https://race.netkeiba.com/race/result.html?race_id=" + raceId + "&rf=race_list";
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
