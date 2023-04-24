package Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    //PreparedStatement pstmt = null;
    String sql = "";

    int countNumCounter = 0; // 最大で第何回までレースがあるのか
    int dayNumCounter = 0; // 最大で何日目まであるのか

    CreateRoopCounter createRoopCounter = new CreateRoopCounter();
    ReplacePlaceNumberToPosition replace = new ReplacePlaceNumberToPosition();
    CurrentRaceInfoServiceImpl raceInfoNewStyle = new CurrentRaceInfoServiceImpl();
    OldRaceInfoServiceImpl raceInfoNewOldStyle = new OldRaceInfoServiceImpl();
    RaceInfo raceInfo = new RaceInfo();
    CreateRaceId createRaceId = new CreateRaceId();

    /* 場所のループ */// 札幌01 函館02 福島03 新潟04 東京05 中山06 中京07 京都08 阪神09 小倉10
    for (int placeNum = 5; placeNum <= 5; placeNum++) {

      /* カウンタの指定。年によって自分でCreateRoopCounter内の数値を変更 */
      Map<String, Integer> counterMap = createRoopCounter.createRoopCounter2(year,placeNum);
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

            System.err.println(raceId);

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
              raceInfo = raceInfoNewStyle.createRaceInfo(raceId);
            } else {
              raceInfo = raceInfoNewOldStyle.createRaceInfo(raceId);
            }

            if (raceInfo == null) {
              continue;
            }

            raceInfo.setRaceNumber(raceNumber);

            RaceInfoData raceInfoData = new RaceInfoData();

            System.out.println(raceInfoData.insert(raceInfo));

          }
        }
      }
    }
  }
}
