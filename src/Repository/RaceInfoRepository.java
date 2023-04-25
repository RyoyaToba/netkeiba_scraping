package Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import SQL.RaceInfoData;
import Service.Impl.CurrentRaceInfoServiceImpl;
import Service.Impl.OldRaceInfoServiceImpl;
import Entity.race.RaceInfo;
import Utility.CreateRaceId;
import Utility.CreateRoopCounter;
import Utility.DBManager;
import Utility.ReplacePlaceNumberToPosition;

/**
 * 過去のレース情報を取得するメソッドです。
 * 
 * @author r-toba
 *
 */
public class RaceInfoRepository {
  public static void main(String[] args) throws IOException {

    String year = "2022";
    RaceInfo raceInfo = null;


    List<String> raceIdList = createRaceIdList(year);

    for(String raceId : raceIdList) {

      try {
        Thread.sleep(1 * 1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      if (Integer.parseInt(year) >= 2008) {
        CurrentRaceInfoServiceImpl raceInfoNewStyle = new CurrentRaceInfoServiceImpl();
        raceInfo = raceInfoNewStyle.createRaceInfo(raceId);
      } else {
        OldRaceInfoServiceImpl raceInfoNewOldStyle = new OldRaceInfoServiceImpl();
        raceInfo = raceInfoNewOldStyle.createRaceInfo(raceId);
      }

      System.err.println(raceId);

      if(raceInfo == null){
        continue;
      }

      RaceInfoData raceInfoData = new RaceInfoData();
      System.out.println(raceInfoData.insert(raceInfo));
    }
  }

  private static List<String> createRaceIdList(String year) {

    CreateRoopCounter createRoopCounter = new CreateRoopCounter();
    CreateRaceId createRaceId = new CreateRaceId();

    List<String> raceIdList = new ArrayList<>();

    for (int placeNum = 0; placeNum <= 10; placeNum++) {
      Map<String, Integer> counterMap = createRoopCounter.createRoopCounter2(year, placeNum);
      int countNumCounter = counterMap.get("countNumCounter");
      int dayNumCounter = counterMap.get("dayNumCounter");

      for (int countNum = 1; countNum <= countNumCounter; countNum++) {
        /* 開催日のループ */
        for (int dayNum = 1; dayNum <= dayNumCounter; dayNum++) {
          /* レースRのループ */
          for (int raceNum = 1; raceNum <= 12; raceNum++) {
            String raceId = createRaceId.createRaceId(year, placeNum, countNum, dayNum, raceNum);
            raceIdList.add(raceId);
          }
        }
      }
    }
    return raceIdList;
  }
}
