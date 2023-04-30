package Starter;

import Entity.race.RaceResult;
import Repository.AllRepository;
import SQL.RaceResultData;
import Scraping.RaceResultScraping;
import Service.Impl.CurrentRaceResultServiceImpl;
import Utility.CreateRaceId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceResultStarter {

    public static void main(String[] args) throws IOException {

        String year = "2022";
        List<String> sqlList = new ArrayList<>();
        List<String> raceIdList = CreateRaceId.createRaceIdList(year);

        List<List<RaceResult>> raceResultListOfList = RaceResultScraping.createraceResultList(raceIdList);

        System.out.println(raceResultListOfList);

        RaceResultData raceResultData = new RaceResultData();

        System.out.println("=================SQL Create Start==================");

        for (List<RaceResult> raceResultList : raceResultListOfList){
            for(RaceResult raceResult : raceResultList){
                String upsertSql = raceResultData.insert(raceResult);
                sqlList.add(upsertSql);
            }
        }

        sqlList.stream().forEach(System.out::println);

        System.out.println("=================DB Upsert Start==================");
        AllRepository.upsertData(sqlList);
    }
}
