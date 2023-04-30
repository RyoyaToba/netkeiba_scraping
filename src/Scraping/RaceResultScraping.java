package Scraping;

import Entity.race.RaceResult;
import Service.Impl.CurrentRaceResultServiceImpl;
import Utility.NetkeibaURL;
import Utility.RaceTerm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceResultScraping {

    public static List<List<RaceResult>> createraceResultList(List<String> raceIdList) throws IOException {

        List<List<RaceResult>> raceResultListOfList = new ArrayList<>();
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

            String url = NetkeibaURL.CURRENT_RACE_RESULT_PAGE_BEFORE_URL + raceId;
            Document document = Jsoup.connect(url).get();

            Elements pageTitle = document.select("title");

            if (pageTitle.outerHtml().equals("<title>  |    - netkeiba.com</title>")) {
                nextSkipFlg = 1;
                continue;
            }

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
                retriveRank(document, i, raceResult);

                /** 枠番の取得 */
                retriveWaku(document, i, raceResult);

                /** 馬番 */
                retriveHorseNumber(document, i, raceResult);

                /** 馬名・馬ID */
                retriveHorseNameAndHorseId(document, i, raceResult);

                /** 性別・馬年齢*/
                retriveHorseGenderAndHorseAge(document, i, raceResult);

                /** 斤量 */
                retriveJockeyWeight(document, i, raceResult);

                /** 騎手名 騎手ID*/
                retriveJockeyNameAndJockeyId(document, i, raceResult);

                /** レースタイム */
                retriveRaceTime(document, i, raceResult);

                /** 着差 */
                retriveArrival(document, i, raceResult);

                /** 人気 */
                retrivePopularity(document, i, raceResult);

                /** オッズ */
                retriveOdds(document, i, raceResult);

                /** 上がり３ハロンタイム*/
                retriveSecondHalfThreeFurlongTime(document, i, raceResult);

                /** コーナー順位*/
                retriveCornerAccessLocation(document, i, raceResult);

                /** トレセン 調教師*/
                retriveTrainerAndTrainingCenter(document, i, raceResult);

                raceResultList.add(raceResult);
            }

            System.out.println("【" + raceId + "】" + "total : " + loopCount);
            raceResultListOfList.add(raceResultList);
            loopCount ++;
        }
        return raceResultListOfList;
    }

    private static void retriveTrainerAndTrainingCenter(Document document, int i, RaceResult raceResult) {
        Elements elemTrainers = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(14)");
        for(Element element : elemTrainers){
            String trainerId = CurrentRaceResultServiceImpl.retriveTrainerId(element);
            raceResult.setTrainerId(trainerId);
            Map<String, String> map = CurrentRaceResultServiceImpl.separateTrainerAndTrainingCenter(element.text());
            raceResult.setTrainer(map.get("trainer"));
            raceResult.setTrainingCenter(map.get("trainingCenter"));
        }
    }


    private static void retriveRank(Document document, int i, RaceResult raceResult) {
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
    }

    private static void retriveWaku(Document document, int i, RaceResult raceResult) {
        Elements elemWakus = document.select(
                "tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(2)");
        for (Element element : elemWakus) {
            raceResult.setWaku(element.text());
        }
    }

    private static void retriveHorseNumber(Document document, int i, RaceResult raceResult) {
        Elements elemHorseNums = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(3)");
        for (Element element : elemHorseNums) {
            raceResult.setHorseNumber(element.text());
        }
    }

    private static void retriveHorseNameAndHorseId(Document document, int i, RaceResult raceResult) {
        Elements elemhorseNames = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(4)");
        for (Element element : elemhorseNames) {
            raceResult.setHorseName(element.text());
            raceResult.setHorseId(CurrentRaceResultServiceImpl.returnHorseId(element));
        }
    }

    private static void retriveHorseGenderAndHorseAge(Document document, int i, RaceResult raceResult) {
        Elements elemGenderAndAges = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(5)");
        for (Element element : elemGenderAndAges) {
            String gender = (element.text()).substring(0, 1);
            String age = (element.text()).substring(1, 2);
            raceResult.setGender(CurrentRaceResultServiceImpl.convertGenderStringToNumber(gender));
            raceResult.setAtThatAge(age);
        }
    }

    private static void retriveJockeyWeight(Document document, int i, RaceResult raceResult) {
        String jockeyWeight;
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
    }

    private static void retriveJockeyNameAndJockeyId(Document document, int i, RaceResult raceResult) {
        Elements elemJockeyNames = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(7)");
        for (Element element : elemJockeyNames) {
            raceResult.setJockeyName(element.text());
            raceResult.setJockeyId(CurrentRaceResultServiceImpl.returnJockeyId(element));
        }
    }

    private static void retriveRaceTime(Document document, int i, RaceResult raceResult) {
        Elements elemRaceTimes = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(8)");
        for (Element element : elemRaceTimes) {
            raceResult.setRaceTime(element.text());
        }
    }

    private static void retriveArrival(Document document, int i, RaceResult raceResult) {
        Elements elemArrival = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(9)");
        for(Element element : elemArrival){
            raceResult.setArrival(element.text());
        }
    }

    private static void retrivePopularity(Document document, int i, RaceResult raceResult) {
        Elements elemPopularity = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(10)");
        for(Element element : elemPopularity){
            raceResult.setPopularity(element.text());
        }
    }

    private static void retriveOdds(Document document, int i, RaceResult raceResult) {
        Elements elemOdds = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(11)");
        for(Element element : elemOdds){
            raceResult.setOdds(element.text());
        }
    }

    private static void retriveSecondHalfThreeFurlongTime(Document document, int i, RaceResult raceResult) {
        Elements elemSecondHalfThreeFurlongTimes = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(12)");
        for(Element element : elemSecondHalfThreeFurlongTimes){
            raceResult.setSecondHalfThreeFurlongTime(element.text());
        }
    }

    private static void retriveCornerAccessLocation(Document document, int i, RaceResult raceResult) {
        Elements elemCornerAccessLocation = document
                .select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(13)");
        for(Element element : elemCornerAccessLocation){
            raceResult.setCornerAccessLocation(element.text());
        }
    }

}
