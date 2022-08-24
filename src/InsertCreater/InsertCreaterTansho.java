package InsertCreater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Create.CreateTansyo;
import Entity.TanshoEntity;
import Parts.CreateRaceId;
import Parts.CreateRoopCounter;
import Parts.DBManager;

public class InsertCreaterTansho {

	public static void main(String[] args) throws IOException {

		PreparedStatement pstmt = null;
		String sql = "";

		String year = "2022";

		int countNumCounter = 0;
		int dayNumCounter = 0;

		CreateRoopCounter createRoopCounter = new CreateRoopCounter();
		CreateRaceId createRaceId = new CreateRaceId();
		CreateTansyo createTansyo = new CreateTansyo();

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
							Thread.sleep(1 * 500);
						} catch (Exception e) {
							e.printStackTrace();
						}

						/* レースIdを作成 */
						String raceId = createRaceId.createRaceId(year, placeNum, countNum, dayNum, raceNum);

						List<TanshoEntity> tanshoList = createTansyo.createTansyo(raceId);
						TanshoEntity tansho = null;

						for (int i = 0; i < tanshoList.size(); i++) {

							tansho = tanshoList.get(i);

							if (tansho.getHorseNumber() == null) {
								continue;
							}
							Connection con = DBManager.createConnection();
							try {
								sql = "INSERT INTO tansho " + " (race_id, horse_number, payout, popular) "
										+ " VALUES("
										+ "'" + tansho.getRaceId() + "'" + "," + tansho.getHorseNumber() + ","
										+ tansho.getPayOut() + "," + tansho.getPopular() + ")";

								System.out.println(sql = "INSERT INTO tansho "
										+ " (race_id, horse_number, payout, popular) " + " VALUES(" + "'"
										+ tansho.getRaceId() + "'" + "," + tansho.getHorseNumber() + ","
										+ tansho.getPayOut() + "," + tansho.getPopular() + ")");

								pstmt = con.prepareStatement(sql);
								pstmt.executeUpdate();

							} catch (SQLException e) {
								e.printStackTrace();
							} finally {
								DBManager.closeConnection(con);
							}
						}
					}
				}
			}
		}
	}
}
