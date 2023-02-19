package Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Service.RaceResultService;
import Entity.race.RaceResult;
import Utility.NetkeibaURL;
import Utility.RaceTerm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CurrentRaceResultServiceImpl implements RaceResultService {
	@Override
	public List<RaceResult> createRaceResult(String raceId) throws IOException {

		String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId;

		Document document = Jsoup.connect(url).get();

		Double jockeyWeight = 0.1;

		Integer horseSize = document.select("tr.HorseList").size();

		List<RaceResult> raceResultList = new ArrayList<>();

		for (int i = 1; i <= horseSize; i++) {

			RaceResult raceResult = new RaceResult();

			raceResult.setRaceId(raceId);

			/** 順位*/
			Elements elemRanks = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(1)");

			String rank = "";
			for (Element element : elemRanks) {
				rank = element.text();
			}

			switch (rank) {
				case "除外":
					rank = RaceTerm.JOGAI;
					break;
				case "中止":
					rank = RaceTerm.CHUSHI;
					break;
				case "取消":
					rank = RaceTerm.TORIKESHI;
					break;
				case "失格":
					rank = RaceTerm.SHIKKAKU;
			}

			raceResult.setRank(rank);

			/** 枠番の取得 */
			Elements elemWakus = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(2)");
			for (Element element : elemWakus) {
				raceResult.setWaku(Integer.parseInt(element.text()));
			}

			/** 馬番 */
			Elements elemHorseNums = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(3)");
			for (Element element : elemHorseNums) {
				raceResult.setHorseNumber(Integer.parseInt(element.text()));
			}

			/** 馬名 */
			Elements elemhorseNames = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(4)");
			for (Element element : elemhorseNames) {
				raceResult.setHorseName(element.text());
			}

			/** 性別 */
			Elements elemGenderAndAges = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(5)");
			for (Element element : elemGenderAndAges) {
				String gender = (element.text()).substring(0, 1);
				raceResult.setGender(gender);
			}

			/** 馬年齢 */
			for (Element element : elemGenderAndAges) {
				String ageString = (element.text()).substring(1, 2);
				Integer age = Integer.parseInt(ageString);
				raceResult.setAge(age);
			}

			/** 斤量 */
			Elements elemJockeyWeights = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(6)");
			for (Element element : elemJockeyWeights) {
				if (element.text().equals("未定")) {
					jockeyWeight = 0.0;
				} else {
					jockeyWeight = Double.parseDouble(element.text());
				}
				raceResult.setJockeyWeight(jockeyWeight);
			}

			/** 騎手名 */
			Elements elemJockeyNames = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(7)");
			for (Element element : elemJockeyNames) {
				raceResult.setJockeyName(element.text());
			}

			/** レースタイム */
			Elements elemRaceTimes = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(8)");
			for (Element element : elemRaceTimes) {
				raceResult.setRaceTime(element.text());
			}
			raceResultList.add(raceResult);
		}
		return raceResultList;
	}
}
