package Create.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Create.RaceResultService;
import Entity.race.RaceResult;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CurrentRaceResultServiceImpl implements RaceResultService {
	@Override
	public List<RaceResult> createRaceResult(String raceId) throws IOException {

		String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId;

		Document document = Jsoup.connect(url).get();

		String rank = "";
		String gender = "";
		Integer age = 0;
		Double jockeyWeight = 0.1;

		Integer horseSize = document.select("tr.HorseList").size();

		List<RaceResult> raceResultList = new ArrayList<>();

		for (int i = 1; i <= horseSize; i++) {

			RaceResult raceResult = new RaceResult();

			Elements ranking = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(1)");

			for (Element element : ranking) {
				rank = element.text();
			}

			if (rank.equals("除外")) {
				rank = "-1";
			} else if (rank.equals("中止")) {
				rank = "99";
			} else if (rank.equals("取消")) {
				rank = "0";
			} else if (rank.equals("失格")) {
				rank = "100";
			}

			raceResult.setRaceId(raceId);

			raceResult.setRank(rank);

			Elements wakuing = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(2)");
			for (Element element : wakuing) {
				raceResult.setWaku(Integer.parseInt(element.text()));
			}

			Elements horseNumbering = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(3)");
			for (Element element : horseNumbering) {
				raceResult.setHorseNumber(Integer.parseInt(element.text()));
			}

			Elements horseNameing = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(4)");
			for (Element element : horseNameing) {
				raceResult.setHorseName(element.text());
			}

			Elements gendering = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(5)");
			for (Element element : gendering) {
				gender = (element.text()).substring(0, 1);
				raceResult.setGender(gender);
			}

			Elements ageing = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(5)");
			for (Element element : ageing) {
				String ageString = (element.text()).substring(1, 2);
				age = Integer.parseInt(ageString);
				raceResult.setAge(age);
			}

			Elements jockWeighting = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(6)");
			for (Element element : jockWeighting) {
				if (element.text().equals("未定")) {
					jockeyWeight = 0.0;
				} else {
					jockeyWeight = Double.parseDouble(element.text());
				}
				raceResult.setJockeyWeight(jockeyWeight);
			}

			Elements jockeyNameing = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(7)");
			for (Element element : jockeyNameing) {
				raceResult.setJockeyName(element.text());
			}

			Elements raceTimeing = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(8)");
			for (Element element : raceTimeing) {
				raceResult.setRaceTime(element.text());
			}

			raceResultList.add(raceResult);
		}
		return raceResultList;
	}
}
