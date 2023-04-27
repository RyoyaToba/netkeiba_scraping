package Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entity.race.RaceInfo;
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
				//raceResult.setWaku(Integer.parseInt(element.text()));
			}

			/** 馬番 */
			Elements elemHorseNums = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(3)");
			for (Element element : elemHorseNums) {
				//raceResult.setHorseNumber(Integer.parseInt(element.text()));
			}

			/** 馬名・馬ID */
			Elements elemhorseNames = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(4)");
			for (Element element : elemhorseNames) {
				raceResult.setHorseName(element.text());
				raceResult.setHorseId(returnHorseId(element));
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
				//raceResult.setAge(age);
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
				//raceResult.setJockeyWeight(jockeyWeight);
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

	public static List<List<RaceResult>> createraceResultList(List<String> raceIdList) throws IOException{

		List<List<RaceResult>> raceResultListOfList = new ArrayList<>();
		int nextSkipFlg = 0;
		int loopCount = 1;

		System.out.println("=================Scraping Start==================");

		for(String raceId : raceIdList) {

			System.out.println(raceId);

			if (nextSkipFlg == 1 && raceId.substring(10, 12).equals("12")) {
				nextSkipFlg = 0;
				continue;
			}

			if (nextSkipFlg == 1) {
				continue;
			}

			RaceInfo raceInfo = new RaceInfo();
			String year = raceId.substring(0, 4);

			String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId;
			Document document = Jsoup.connect(url).get();

			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			String jockeyWeight = null;

			Integer horseSize = document.select("tr.HorseList").size();

			List<RaceResult> raceResultList = new ArrayList<>();

			for (int i = 1; i <= horseSize; i++) {

				RaceResult raceResult = new RaceResult();

				raceResult.setRaceId(raceId);

				/** 順位*/
				Elements elemRanks = document.select(
						"tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(1)");

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
				Elements elemWakus = document.select(
						"tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(2)");
				for (Element element : elemWakus) {
					raceResult.setWaku(element.text());
				}

				/** 馬番 */
				Elements elemHorseNums = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(3)");
				for (Element element : elemHorseNums) {
					raceResult.setHorseNumber(element.text());
				}

				/** 馬名・馬ID */
				Elements elemhorseNames = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(4)");
				for (Element element : elemhorseNames) {
					raceResult.setHorseName(element.text());
					raceResult.setHorseId(returnHorseId(element));
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
					String age = (element.text()).substring(1, 2);
					raceResult.setAtThatAge(age);
				}

				/** 斤量 */
				Elements elemJockeyWeights = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(6)");
				for (Element element : elemJockeyWeights) {
					if (element.text().equals("未定")) {
						jockeyWeight = null;
					} else {
						jockeyWeight = element.text();
					}
					raceResult.setJockeyWeight(jockeyWeight);
				}

				/** 騎手名 騎手ID*/
				Elements elemJockeyNames = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(7)");
				for (Element element : elemJockeyNames) {
					raceResult.setJockeyName(element.text());
					raceResult.setJockeyId(returnJockeyId(element));
				}

				/** レースタイム */
				Elements elemRaceTimes = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(8)");
				for (Element element : elemRaceTimes) {
					raceResult.setRaceTime(element.text());
				}

				/** 着差 */
				Elements elemArrival = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(9)");
				for(Element element : elemArrival){
					raceResult.setArrival(element.text());
				}

				/** 人気 */
				Elements elemPopularity = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(10)");
				for(Element element : elemPopularity){
					raceResult.setPopularity(element.text());
				}

				/** オッズ */
				Elements elemOdds = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(11)");
				for(Element element : elemOdds){
					raceResult.setOdds(element.text());
				}

				/** 上がり３ハロンタイム*/
				Elements elemSecondHalfThreeFurlongTimes = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(12)");
				for(Element element : elemSecondHalfThreeFurlongTimes){
					raceResult.setSecondHalfThreeFurlongTime(element.text());
				}

				/** コーナー順位*/
				Elements elemCornerAccessLocation = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(13)");
				for(Element element : elemCornerAccessLocation){
					raceResult.setCornerAccessLocation(element.text());
				}

				/** 厩舎 オーナー*/
				// TODO 厩舎と調教師、調教師IDを取得し、インスタンスへ格納する
				Elements elemTrainers = document
						.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(14)");
				for(Element element : elemTrainers){
					raceResult.setTrainer(element.text());
				}
				System.out.println(raceResult);
				raceResultList.add(raceResult);
			}

		raceResultListOfList.stream().forEach(System.out::println);
		}

		return raceResultListOfList;
	}

	public static String returnHorseId(Element element){

		Integer horseIdStart = element.outerHtml().indexOf("horse/") + 6;
		Integer horseIdEnd = horseIdStart + 10;
		String horseId = element.outerHtml().substring(horseIdStart, horseIdEnd);
		return horseId;
	}

	// TODO CREATE JockeyId Logic
	public static String returnJockeyId(Element element){

		Integer jockeyIdStart = element.outerHtml().indexOf("recent/") + 7;
		Integer jockeyIdEnd = jockeyIdStart + 5;
		String jockeyId = element.outerHtml().substring(jockeyIdStart, jockeyIdEnd);
		return jockeyId;
	}

	// TODO CREATE
	public String returnKyushaId(){
		return null;
	}



}
