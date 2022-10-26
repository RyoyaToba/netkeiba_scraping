package InsertCreater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import Create.CreateFukusho;
import Entity.FukushoEntity;
import Parts.CreateRaceId;
import Parts.CreateRoopCounter;
import Parts.DBManager;

public class InsertCreaterFukusho {

  public static void main(String[] args) throws IOException {

    String sql = "";

    String year = "2022";

    int countNumCounter = 0;
    int dayNumCounter = 0;

    CreateRoopCounter createRoopCounter = new CreateRoopCounter();
    CreateRaceId createRaceId = new CreateRaceId();
    CreateFukusho createFukusho = new CreateFukusho();

    for (int placeNum = 1; placeNum <= 10; placeNum++) {
      Map<String, Integer> counterMap = createRoopCounter.createRoopCounter(placeNum);
      countNumCounter = counterMap.get("countNumCounter");
      dayNumCounter = counterMap.get("dayNumCounter");

      /* 回数のループ */
      for (int countNum = 1; countNum <= countNumCounter; countNum++) {
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

            List<FukushoEntity> fukushoList = createFukusho.createFukusho(raceId);
            FukushoEntity fukusho = null;

            System.err.println(fukushoList);

            // DBへ格納する処理
            for (int i = 0; i < fukushoList.size(); i++) {

              fukusho = fukushoList.get(i);

              if (fukusho.getHorseNumber() == null) {
                continue;
              }

              sql = "INSERT INTO fukusho " + " (race_id, horse_number, payout, popular) "
                  + " VALUES(" + "'" + fukusho.getRaceId() + "'" + "," + fukusho.getHorseNumber()
                  + "," + fukusho.getPayOut() + "," + fukusho.getPopular() + ")";

              System.out.println(
                  "INSERT INTO fukusho " + " (race_id, horse_number, payout, popular) " + " VALUES("
                      + "'" + fukusho.getRaceId() + "'" + "," + fukusho.getHorseNumber() + ","
                      + fukusho.getPayOut() + "," + fukusho.getPopular() + ")");

              try (Connection con = DBManager.createConnection();
                  PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.executeUpdate();

              } catch (SQLException e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    }
  }
}
