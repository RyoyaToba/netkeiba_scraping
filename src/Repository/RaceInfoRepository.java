package Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import Create.CreateRaceInfoNewStyle;
import Create.CreateRaceInfoOldStyle;
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

    String year = "2000";

    PreparedStatement pstmt = null;
    String sql = "";

    int countNumCounter = 0; // 最大で第何回までレースがあるのか
    int dayNumCounter = 0; // 最大で何日目まであるのか

    CreateRoopCounter createRoopCounter = new CreateRoopCounter();
    ReplacePlaceNumberToPosition replace = new ReplacePlaceNumberToPosition();
    CreateRaceInfoNewStyle raceInfoNewStyle = new CreateRaceInfoNewStyle();
    CreateRaceInfoOldStyle raceInfoNewOldStyle = new CreateRaceInfoOldStyle();
    RaceInfo raceInfo = new RaceInfo();
    CreateRaceId createRaceId = new CreateRaceId();

    /* 場所のループ */// 札幌01 函館02 福島03 新潟04 東京05 中山06 中京07 京都08 阪神09 小倉10
    for (int placeNum = 5; placeNum <= 5; placeNum++) {

      /* カウンタの指定。年によって自分でCreateRoopCounter内の数値を変更 */
      Map<String, Integer> counterMap = createRoopCounter.createRoopCounter(placeNum);
      countNumCounter = counterMap.get("countNumCounter");
      dayNumCounter = counterMap.get("dayNumCounter");

      /* 開催回数のループ */
      for (int countNum = 1; countNum <= countNumCounter; countNum++) {
        /* 開催日のループ */
        for (int dayNum = 1; dayNum <= dayNumCounter; dayNum++) {
          /* レースRのループ */
          for (int raceNum = 1; raceNum <= 12; raceNum++) {

            // 待機処理 １秒
            try {
              Thread.sleep(1 * 1000);
            } catch (Exception e) {
              e.printStackTrace();
            }

            /* レースIdを作成 */
            String raceId = createRaceId.createRaceId(year, placeNum, countNum, dayNum, raceNum);

            String raceName = "";
            String raceDetail = "";
            String raceDay = "";
            String feild = "";

            /* 場所番号と会場名を置き換える 01→札幌 */
            String position = replace.replacePlaceNumberPosition(placeNum);

            /* レースナンバーの作成 札幌01R */
            String raceNumber =
                position + raceId.substring((raceId.length() - 2), raceId.length()) + "R";

            /* INSERT文の作成 raceIdからURLに接続し必要な情報が入ったEntityが戻される */
            if (Integer.parseInt(year) >= 2008) {
              raceInfo = raceInfoNewStyle.createEntityNewStyle(raceId);
            } else {
              raceInfo = raceInfoNewOldStyle.createEntityOldStyle(raceId);
            }

            if (raceInfo == null) {
              continue;
            }

            raceName = raceInfo.getRaceName();
            raceDay = raceInfo.getRaceDay();
            raceDetail = raceInfo.getRaceDetail();
            feild = raceInfo.getFeild();

            Connection con = DBManager.createConnection();

            //try {
//              sql =
//                  "INSERT INTO race_info (race_id, race_day, race_number, race_name, race_detail, feild) VALUES('"
//                      + raceId + "'," + "'" + raceDay + "'," + "'" + raceNumber + "'," + "'"
//                      + raceName + "'," + "'" + raceDetail + "'," + "'" + feild + "');";
//
//              pstmt = con.prepareStatement(sql);
//              pstmt.executeUpdate();
//
//            } catch (Exception e) {
//              e.printStackTrace();
//            } finally {
//              DBManager.closeConnection(con);
//            }

            System.out.println(
                "INSERT INTO race_info (race_id, race_day, race_number, race_name, race_detail, feild) VALUES('"
                    + raceId + "'," + "'" + raceDay + "'," + "'" + raceNumber + "'," + "'"
                    + raceName + "'," + "'" + raceDetail + "'," + "'" + feild + "');");
          }
        }
      }
    }
  }
}
