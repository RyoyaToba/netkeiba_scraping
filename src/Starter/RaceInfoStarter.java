package Starter;

import Entity.race.RaceInfo;
import Repository.AllRepository;
import SQL.RaceInfoData;
import Scraping.RaceInfoScraping;
import Service.Impl.CurrentRaceInfoServiceImpl;
import Utility.CreateRaceId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceInfoStarter {

    public static void main(String[] args) throws IOException {

        String year = "2022";
        RaceInfo raceInfo = null;
        List<String> sqlList = new ArrayList<>();

        List<String> raceIdList = CreateRaceId.createRaceIdList(year);

        //if (Integer.parseInt(year) >= 2008) {
        List<RaceInfo> raceInfoList = RaceInfoScraping.createRaceInfoList(raceIdList);
      /*} else {
        OldRaceInfoServiceImpl raceInfoNewOldStyle = new OldRaceInfoServiceImpl();
        raceInfo = raceInfoNewOldStyle.createRaceInfoList(raceIdList);
      //}*/

        RaceInfoData raceInfoData = new RaceInfoData();

        System.out.println("=================SQL Create Start==================");
        for(RaceInfo raceInfo2 : raceInfoList){
            String upsertSql = raceInfoData.insert(raceInfo2);
            sqlList.add(upsertSql);
        }
        System.out.println("=================DB Upsert Start==================");
        AllRepository.upsertData(sqlList);
    }
}

