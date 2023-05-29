package Starter;

import Entity.race.RaceResult;
import Repository.AllRepository;
import Repository.RaceInfoRepository;
import Repository.RaceResultRepository;
import SQL.RaceResultData;
import Scraping.RaceResultScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceResultStarter {

    public static void main(String[] args) throws IOException {

        String year = "2023";
        List<String> sqlList = new ArrayList<>();
        List<String> raceIdList = RaceInfoRepository.select(year);

        // 既に登録されているものは対象から外す場合
        List<String> savedRaceIdList = RaceResultRepository.findRaceId(year);
        raceIdList.removeAll(savedRaceIdList);
        // ここまで

        System.out.println( "【 " + year +"年のレースデータ" +raceIdList.size() + "件を取得します 】");

        List<List<RaceResult>> raceResultListOfList = RaceResultScraping.createraceResultList(raceIdList);

        RaceResultData raceResultData = new RaceResultData();

        System.out.println("=================SQL Create Start==================");

        for (List<RaceResult> raceResultList : raceResultListOfList){
            for(RaceResult raceResult : raceResultList){
                String upsertSql = raceResultData.insert(raceResult);
                sqlList.add(upsertSql);
            }
        }

        System.out.println("=================DB Upsert Start==================");
        AllRepository.upsertData(sqlList);
    }
}
