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

        String year = "2013";

        // 取得したレース結果から、horseIdを検索し、競走馬情報を登録するなら、これをアクティブにする
        //List<String> horseIdList = HorseResultRepository.select(year);

        // 既に登録された競走馬データの両親の情報を調べたい場合は、こちらをアクティブにする
        List<String> horseIdList = HorseMasterRepository.selectParentData(year);

        // ------既に登録されているものを除外する場合-------
        // Updateとかしたい場合は、既に登録されているものも対象にするので、２行をコメントアウトする
        List<String> savedHorseIdList = HorseMasterRepository.select();
        horseIdList.removeAll(savedHorseIdList);
        // ------ここまで-------

        System.out.println( "【 出走馬データ" +horseIdList.size() + "件を取得します 】");
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

