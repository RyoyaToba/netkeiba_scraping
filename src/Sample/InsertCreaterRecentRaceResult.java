package Sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Service.Impl.CurrentRaceResultServiceImpl;
import Service.Impl.OldRaceResultserviceImpl;
import Entity.race.RaceResult;
import Utility.CreateRaceId;
import Utility.CreateRoopCounter;

/**
 * 過去のレース結果を取得するメソッドです。 raceId rank waku horseNumber horseName gender age jockeyWright jockeyName
 * raceTime
 */
public class InsertCreaterRecentRaceResult {
  public static void main(String[] args) throws IOException {

    String year = "2022";


    int countNumCounter = 0; // 最大で第何回までレースがあるのか
    int dayNumCounter = 0; // 最大で何日目まであるのか

    CreateRoopCounter createRoopCounter = new CreateRoopCounter();
    RaceResult raceResult = new RaceResult();
    CurrentRaceResultServiceImpl createRaceResultNewStyle = new CurrentRaceResultServiceImpl();
    OldRaceResultserviceImpl createRaceResultoldRaceResultserviceImpl = new OldRaceResultserviceImpl();
    CreateRaceId createRaceId = new CreateRaceId();

    /* 場所のループ */// 札幌01 函館02 福島03 新潟04 東京05 中山06 中京07 京都08 阪神09 小倉10
    for (int placeNum = 1; placeNum <= 10; placeNum++) {

      /* Counterを作成 */
      Map<String, Integer> counterMap = createRoopCounter.createRoopCounter(placeNum);
      countNumCounter = counterMap.get("countNumCounter");
      dayNumCounter = counterMap.get("dayNumCounter");

      /* 回数のループ */
      for (int countNum = 2; countNum <= countNumCounter; countNum++) {
        /* 日付のループ */
        for (int dayNum = 1; dayNum <= dayNumCounter; dayNum++) {
          /* レースRのループ */
          for (int raceNum = 1; raceNum <= 12; raceNum++) {

            try {
              Thread.sleep(1 * 1000);
            } catch (Exception e) {
              e.printStackTrace();
            }

            /* レースIdを作成 */
            String raceId = createRaceId.createRaceId(year, placeNum, countNum, dayNum, raceNum);

            String rank = "";
            String newRank = "";
            Integer waku = 0;
            Integer horseNumber = 0;
            String horseName = "";
            String gender = "";
            Integer age = 0;
            Double jockeyWeight = 0.1;
            String jockeyName = "";
            String raceTime = "";

            List<RaceResult> raceResultList = new ArrayList<>();

            if (Integer.parseInt(year) >= 2008) {
              raceResultList = createRaceResultNewStyle.createRaceResult(raceId);
            } else {
              raceResultList = createRaceResultoldRaceResultserviceImpl.createRaceResult(raceId);
            }

            // Connection con = DBManager.createConnection();

            for (int i = 0; i < raceResultList.size(); i++) {
              raceResult = raceResultList.get(i);
              rank = raceResult.getRank();
              newRank = String.valueOf(rank);
              waku = raceResult.getWaku();
              horseNumber = raceResult.getHorseNumber();
              horseName = raceResult.getHorseName();
              gender = raceResult.getGender();
              age = raceResult.getAge();
              jockeyWeight = raceResult.getJockeyWeight();
              jockeyName = raceResult.getJockeyName();
              raceTime = raceResult.getRaceTime();

              // try {
              // sql = "INSERT INTO race_result (race_id, rank, waku, horse_number, horse_name,
              // gender, age, jockey_weight, jockey_name, race_time) VALUES ('"
              // + raceId + "'," + newRank + "," + waku + "," + horseNumber + "," + "'"
              // + horseName + "'," + "'" + gender + "'," + age + "," + jockeyWeight + "," + "'"
              // + jockeyName + "'," + "'" + raceTime + "');";
              //
              // pstmt = con.prepareStatement(sql);
              // pstmt.executeUpdate();
              //
              // } catch (Exception e) {
              // e.printStackTrace();
              // }
              System.out.println(
                  "INSERT INTO race_result (race_id, rank, waku, horse_number, horse_name, gender, age, jockey_weight, jockey_name, race_time) VALUES ('"
                      + raceId + "'," + newRank + "," + waku + "," + horseNumber + "," + "'"
                      + horseName + "'," + "'" + gender + "'," + age + "," + jockeyWeight + ","
                      + "'" + jockeyName + "'," + "'" + raceTime + "');");
            }
            // DBManager.closeConnection(con);
          }
        }
      }
    }
  }
}
