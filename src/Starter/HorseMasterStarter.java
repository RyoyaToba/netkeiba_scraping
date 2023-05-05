package Starter;

import Entity.horse.Horse;
import Entity.race.RaceInfo;
import Repository.AllRepository;
import Repository.HorseMasterRepository;
import Repository.HorseResultRepository;
import SQL.HorseMasterData;
import SQL.RaceInfoData;
import Scraping.HorseMasterScraping;
import Scraping.RaceInfoScraping;
import Utility.CreateRaceId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorseMasterStarter {

    public static void main(String[] args) throws IOException {

        String year = "2019";

        List<String> horseIdList = HorseResultRepository.select(year);

        // ------既に登録されているものを除外する場合-------
        List<String> savedHorseIdList = HorseMasterRepository.select();
        horseIdList.removeAll(savedHorseIdList);
        // ------ここまで-------

        System.out.println( "【 " + year +"年の出走馬データ" +horseIdList.size() + "件を取得します 】");
        List<Horse> horseMasterList = HorseMasterScraping.createHorseMasterList(horseIdList);

        List<String> sqlList = new ArrayList<>();

        HorseMasterData horseMasterData = new HorseMasterData();

        System.out.println("=================SQL Create Start==================");
        for(Horse horse : horseMasterList){
            String upsertSql = horseMasterData.insert(horse);
            sqlList.add(upsertSql);
        }

        System.out.println("=================DB Upsert Start==================");
        AllRepository.upsertData(sqlList);
    }
}

