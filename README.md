# scraping

学習用のデータをネット上から取得するため、Javaのサードパーティである[JSOUP](https://jsoup.org/download)を活用したスクレイピングを行なっています。

## 使用しているライブラリ

* JSOUP-jar(1.14.3)
* Postgresql-jar(42.3.4)

## 使用例

構築しているのは、競馬情報を取得するためのスクレイピングコードです。Netkeiba（株式会社ネットドリーマーズ様）のサイトより

* レース情報（レース名、日付、馬場状態など）

* レース結果（着順、競走馬名、騎手名など）

* 払い戻し額（馬番、払戻金、人気など）

* 競走馬情報（馬名、父馬、母馬など）

といった情報を取得することができます。


以下は、2021年に行われたレース結果を取得するコードです。

```Java
package InsertCreater;

/**
 * 過去のレース結果を取得するメソッドです。 
 * raceId rank waku horseNumber horseName gender age
 * jockeyWright jockeyName raceTime
 */
public class InsertCreaterRaceResult {
	public static void main(String[] args) throws IOException {

		String year = "2021";
    
    /* Postgreとの接続 */
		PreparedStatement pstmt = null;
		String sql = "";

		int countNumCounter = 0; // 最大で第何回までレースがあるのか
		int dayNumCounter = 0; // 最大で何日目まであるのか

		CreateRoopCounter createRoopCounter = new CreateRoopCounter();
		RaceResultEntity raceResult = new RaceResultEntity();
		CreateRaceResultNewStyle createRaceResultNewStyle = new CreateRaceResultNewStyle();
		CreateRaceResultOldStyle createRaceResultoldStyle = new CreateRaceResultOldStyle();
		CreateRaceId createRaceId = new CreateRaceId();

		/* 場所のループ */// 札幌01 函館02 福島03 新潟04 東京05 中山06 中京07 京都08 阪神09 小倉10
		for (int placeNum = 1; placeNum <= 10; placeNum++) {

			/* Counterを作成 :各レースでは第何回の何日目かが異なる */
			Map<String, Integer> counterMap = createRoopCounter.createRoopCounter(placeNum);
			countNumCounter = counterMap.get("countNumCounter");
			dayNumCounter = counterMap.get("dayNumCounter");

			/* 回数のループ */
			for (int countNum = 1; countNum <= countNumCounter; countNum++) {
				/* 日付のループ */
				for (int dayNum = 1; dayNum <= dayNumCounter; dayNum++) {
					/* レースRのループ */
					for (int raceNum = 1; raceNum <= 12; raceNum++) {
            
            /* １秒間の停止 */
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

						List<RaceResultEntity> raceResultList = new ArrayList<>();

						if (Integer.parseInt(year) >= 2008) {
							raceResultList = createRaceResultNewStyle.createRaceResultNewStyle(raceId);
						} else {
							raceResultList = createRaceResultoldStyle.createRaceResultNewStyle(raceId);
						}

						Connection con = DBManager.createConnection();

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

							try {
								sql = "INSERT INTO race_result (race_id, rank, waku, horse_number, horse_name, gender, age, jockey_weight, jockey_name, race_time) VALUES ('"
										+ raceId + "'," + newRank + "," + waku + "," + horseNumber + "," + "'"
										+ horseName + "'," + "'" + gender + "'," + age + "," + jockeyWeight + "," + "'"
										+ jockeyName + "'," + "'" + raceTime + "');";

								pstmt = con.prepareStatement(sql);
								pstmt.executeUpdate();

							} catch (Exception e) {
								e.printStackTrace();
							}

							System.out.println(
									"INSERT INTO race_result (race_id, rank, waku, horse_number, horse_name, gender, age, jockey_weight, jockey_name, race_time) VALUES ('"
											+ raceId + "'," + newRank + "," + waku + "," + horseNumber + "," + "'"
											+ horseName + "'," + "'" + gender + "'," + age + "," + jockeyWeight + ","
											+ "'" + jockeyName + "'," + "'" + raceTime + "');");

						}
					}
				}
			}
		}
	}
}
```

