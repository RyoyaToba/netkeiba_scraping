package Starter;

import Entity.race.RaceInfo;
import Repository.AllRepository;
import Repository.RaceInfoRepository;
import SQL.RaceInfoData;
import Scraping.RaceInfoScraping;
import Utility.CreateRaceId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceInfoStarter {

    public static void main(String[] args) throws IOException {

        String year = "2023";
        List<String> sqlList = new ArrayList<>();

        List<String> raceIdList = CreateRaceId.createRaceIdList(year);

        // 既に登録されているものを除外しない場合、下２行をコメントアウトにする
        List<String> savedRaceIdList = RaceInfoRepository.select(year);
        raceIdList.removeAll(savedRaceIdList);
        // ここまで

        System.out.println( "【 " + year +"年のレースデータ" + raceIdList.size() + "件を取得します 】");

        List<RaceInfo> raceInfoList = RaceInfoScraping.createRaceInfoList(raceIdList);

        RaceInfoData raceInfoData = new RaceInfoData();

        System.out.println("=================SQL Create Start==================");
        for(RaceInfo raceInfo : raceInfoList){
            String upsertSql = raceInfoData.insert(raceInfo);
            sqlList.add(upsertSql);
        }
        System.out.println("=================DB Upsert Start==================");
        AllRepository.upsertData(sqlList);
    }
}

