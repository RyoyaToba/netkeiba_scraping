package Create.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Create.RaceResultService;
import Entity.race.RaceResult;
import Utility.Common;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OldRaceResultserviceImpl implements RaceResultService {

	@Override
	public List<RaceResult> createRaceResult(String raceId) throws IOException {

		String url = NetkeibaURL.OLD_RACE_RESULT_PAGE_URL + raceId + Common.SLASH;

		Document document = Jsoup.connect(url).get();

		String rank = "";
		String gender = "";
		Integer age = 0;
		Double jockeyWeight = 0.1;

		Integer horseSize = document.select(".race_table_01 tr").size();

		List<RaceResult> raceResultList = new ArrayList<>();

		for (int i = 2; i <= horseSize; i++) { // サイトの特性上、ループカウンタを2から回します

			RaceResult raceResult = new RaceResult();

			Elements ranking = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(1)");

			for (Element element : ranking) {
				rank = element.text();
			}

			if (rank.equals("除")) {
				rank = "-1";
			} else if (rank.equals("中")) {
				rank = "99";
			} else if (rank.equals("取")) {
				rank = "0";
			} else if (rank.equals("失")) {
				rank = "100";
			} else if (rank.contains("(降)")) {
				rank = rank.replace("(降)", "");
			}

			raceResult.setRaceId(raceId);

			raceResult.setRank(rank);

			Elements wakuing = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(2)");
			for (Element element : wakuing) {
				raceResult.setWaku(Integer.parseInt(element.text()));
			}

			Elements horseNumbering = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(3)");
			for (Element element : horseNumbering) {
				raceResult.setHorseNumber(Integer.parseInt(element.text()));
			}

			Elements horseNameing = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(4)");
			for (Element element : horseNameing) {
				raceResult.setHorseName(element.text());
			}

			Elements gendering = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(5)");
			for (Element element : gendering) {
				gender = element.text().substring(0, 1);
				raceResult.setGender(gender);
			}

			Elements ageing = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(5)");
			for (Element element : ageing) {
				String ageString = element.text().substring(1, 2);
				age = Integer.parseInt(ageString);
				raceResult.setAge(age);
			}

			Elements jockeyWeighting = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(6)");
			for (Element element : jockeyWeighting) {
				if (element.text().equals("未定")) {
					jockeyWeight = 0.0;
				} else {
					jockeyWeight = Double.parseDouble(element.text());
				}
				raceResult.setJockeyWeight(jockeyWeight);
			}

			Elements jockeyNameting = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(7)");
			for (Element element : jockeyNameting) {
				raceResult.setJockeyName(element.text());
			}

			Elements raceTimeing = document.select(".race_table_01 tr:nth-of-type(" + i + ") td:nth-of-type(8)");
			for (Element element : raceTimeing) {
				raceResult.setRaceTime(element.text());
			}
			raceResultList.add(raceResult);
		}
		return raceResultList;
	}
}
