package Scraping;

import Entity.race.RaceInfo;
import Service.Impl.CurrentRaceInfoServiceImpl;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceInfoScraping {

    public static List<RaceInfo> createRaceInfoList(List<String> raceIdList) throws IOException {

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

            // レース名の取得
            retriveRaceName(raceInfo, document);

            // 日付の取得
            retriveRaceDay(raceInfo, year, document);

            // レースタイプ（芝 or ダート or 障害） / 距離の取得
            retriveRaceTypeAndDistance(raceInfo, document);

            // 馬場状態の取得
            retriveFieldCondition(raceInfo, document);

            System.out.println("【" + raceId + "】" + "total : " + loopCount);

            raceInfoList.add(raceInfo);
            loopCount ++;
        }
        return raceInfoList;
    }

    /** レース名の取得*/
    private static void retriveRaceName(RaceInfo raceInfo, Document document) {
        Elements raceNaming = document.select("div.raceName");
        for (Element element : raceNaming) {
            if (element == null) {
                raceInfo.setRaceName("");
            } else {
                raceInfo.setRaceName(element.text());
            }
        }
    }

    /** レース日の取得*/
    private static void retriveRaceDay(RaceInfo raceInfo, String year, Document document) {
        Elements dd = document.select("dd.Active");
        for (Element element : dd) {
            Date raceDay = CurrentRaceInfoServiceImpl.convertRaceDayStringToDate(year, element.text());
            raceInfo.setRaceDay(raceDay);
        }
    }

    /** レースタイプと距離の取得*/
    private static void retriveRaceTypeAndDistance(RaceInfo raceInfo, Document document) {
        Elements raceData01 = document.select(".RaceData01 > span:nth-of-type(1)");
        for (Element element : raceData01) {
            Map<String, String> raceTypeAndDistanceMap = CurrentRaceInfoServiceImpl.createRaceTypeAndDistance(element.text());
            raceInfo.setRaceType(raceTypeAndDistanceMap.get("raceType"));
            raceInfo.setDistance(raceTypeAndDistanceMap.get("distance"));
        }
    }

    /** 馬場状態の取得*/
    private static void retriveFieldCondition(RaceInfo raceInfo, Document document) {
        Elements raceData02 = document.select(".RaceData01 > span:nth-of-type(3)");
        for (Element element : raceData02) {
            if (element == null) {
                raceInfo.setFieldCondition(" ");
            } else {
                String field = (element.text()).substring(2, (element.text().length()));
                field = CurrentRaceInfoServiceImpl.convertFieldConditionStringToNumber(field);
                raceInfo.setFieldCondition(field);
            }
        }
    }

}
