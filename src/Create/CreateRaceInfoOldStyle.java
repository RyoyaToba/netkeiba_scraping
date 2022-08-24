package Create;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.RaceInfoEntity;

public class CreateRaceInfoOldStyle {

	public RaceInfoEntity createEntityOldStyle(String raceId) throws IOException {

		RaceInfoEntity raceInfo = new RaceInfoEntity();

		String url = "https://db.netkeiba.com/race/" + raceId + "/";

		Document document = Jsoup.connect(url).get();

		String raceDay = "";

		// レースの日付取得
		Elements raceDaying = document.select("p.smalltxt");
		for (Element element : raceDaying) {
			raceDay = element.text().substring(5, element.text().indexOf("日") + 1);
			raceInfo.setRaceDay(raceDay.replace(" ", ""));
		}

		if (raceDay.equals("")) {
			return null;
		}

		// レースの名前の取得（例：三歳未勝利戦）
		Elements raceNaming = document.select("h1");
		for (Element element : raceNaming) {
			raceInfo.setRaceName(element.text());
		}

		// ダ1000m
		Elements raceData01 = document.select("div.data_intro span");
		for (Element element : raceData01) {
			String raceDetail = element.text().substring(0, 1)
					+ element.text().substring(2, element.text().indexOf("m") + 1);
			raceInfo.setRaceDetail(raceDetail.replace(" ", ""));
		}

		String feild = "";
		// 馬場:稍
		Elements feilding = document.select("div.data_intro span");
		for (Element element : feilding) {

			if (element.text().contains("芝")) {
				feild = "馬場:" + element.text().substring(24, 25);
			} else {
				feild = "馬場:" + element.text().substring(25, 26);
			}

			if (feild.equals("馬場: ")) {
				feild = "馬場:良";
			}
			raceInfo.setFeild(feild);

		}

		return raceInfo;
	}
}
