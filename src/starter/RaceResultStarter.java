package starter;

import Entity.race.RaceInfo;
import Entity.race.RaceResult;
import Repository.AllRepository;
import SQL.RaceResultData;
import Service.Impl.CurrentRaceResultServiceImpl;
import Utility.CreateRaceId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceResultStarter {

    public static void main(String[] args) throws IOException {

        String year = "2022";
        //RaceResult raceResult = new RaceResult();
        //List<String> sqlList = new ArrayList<>();
        List<String> raceIdList = CreateRaceId.createRaceIdList(year);

        List<List<RaceResult>> raceResultListOfList = CurrentRaceResultServiceImpl.createraceResultList(raceIdList);

        for (List<RaceResult> target : raceResultListOfList){
            for(RaceResult target2 : target){
                System.out.println(target2.toString());
            }
        }

        RaceResultData raceResultData = new RaceResultData();

        System.out.println("=================SQL Create Start==================");
        //for(RaceResult raceResult2 : raceResultList){
        //    String upsertSql = raceResultData.insert(raceResult2);
        //    sqlList.add(upsertSql);
        //}
        //System.out.println("=================DB Upsert Start==================");
        //AllRepository.upsertData(sqlList);
    }
}
