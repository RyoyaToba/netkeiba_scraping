package Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Service.Impl.CurrentRaceInfoServiceImpl;
import Service.Impl.OldRaceInfoServiceImpl;
import Entity.race.RaceInfo;
import Utility.DBManager;
import Utility.ReplacePlaceNumberToPosition;

/**
 * 過去のレース情報を取得するメソッドです。 raceId raceDay raceNumber raceName raceDetail feild
 * 
 * @author r-toba
 *
 */
public class RaceInfoOnlyOneRaceRepository {
	public static void main(String[] args) throws IOException {

		/* raceIdを自分で設定してください */
		String raceId = ""; // 例：2007|03|01|03|05
							// 年 |場所|回|日|R

		PreparedStatement pstmt = null;
		String sql = "";

		ReplacePlaceNumberToPosition replace = new ReplacePlaceNumberToPosition();
		CurrentRaceInfoServiceImpl raceInfoNewStyle = new CurrentRaceInfoServiceImpl();
		OldRaceInfoServiceImpl raceInfoNewOldStyle = new OldRaceInfoServiceImpl();
		RaceInfo raceInfo = new RaceInfo();

			/* レースRのループ */
			for (int raceNum = 1; raceNum <= 12; raceNum++) {

				// 待機処理 １秒
				try {
					Thread.sleep(1 * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String raceName = "";
				String raceDetail = "";
				String raceDay = "";
				String feild = "";

				Integer placeNum = Integer.parseInt(raceId.substring(4, 5));

				/* 場所番号と会場名を置き換える 01→札幌 */
				String position = replace.replacePlaceNumberPosition(placeNum);

				/* レースナンバーの作成 札幌01R */
				String raceNumber = position + raceId.substring((raceId.length() - 2), raceId.length()) + "R";

				String year = raceId.substring(0, 3);

				/* INSERT文の作成 raceIdからURLに接続し必要な情報が入ったEntityが戻される */
				if (Integer.parseInt(year) >= 2008) {
					raceInfo = raceInfoNewStyle.createRaceInfo(raceId);
				} else {
					raceInfo = raceInfoNewOldStyle.createRaceInfo(raceId);
				}

				if (raceInfo == null) {
					continue;
				}

				raceName = raceInfo.getRaceName();
				raceDay = raceInfo.getRaceDay();
				//raceDetail = raceInfo.getRaceDetail();
				//feild = raceInfo.getFeild();

				Connection con = DBManager.createConnection();

				try {
					sql = "INSERT INTO race_info (race_id, race_day, race_number, race_name, race_detail, feild) VALUES('"
							+ raceId + "'," + "'" + raceDay + "'," + "'" + raceNumber + "'," + "'" + raceName + "',"
							+ "'" + raceDetail + "'," + "'" + feild + "');";

					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.closeConnection(con);
				}

				System.out.println(
						"INSERT INTO race_info (race_id, race_day, race_number, race_name, race_detail, feild) VALUES('"
								+ raceId + "'," + "'" + raceDay + "'," + "'" + raceNumber + "'," + "'" + raceName + "',"
								+ "'" + raceDetail + "'," + "'" + feild + "');");
			}
		}

	}

