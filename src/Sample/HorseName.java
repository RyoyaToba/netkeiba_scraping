package Sample;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//ここで活動中
//SQL文の形で取得することができます
public class HorseName {
//	<div class="DataBox_01">
//	<h2 class="LimitsWidth">ジュタロウ</h2>
//	<p>牡&nbsp;2019年&nbsp;&nbsp;5戦2勝</p>
//	<p>父:Arrogate</p>
//	<p>母:Bodacious Babe</p>
//	</div>
//https://db.sp.netkeiba.com/?pid=horse_list&word=&sire=&mare=&bms=&trainer=&owner=&breeder=&under_age=2&over_age=none&prize_min=&prize_max=&retired=1&sort=access&submit=
//https://db.sp.netkeiba.com/?pid=horse_list&word=&sire=&mare=&bms=&trainer=&owner=&breeder=&under_age=2&over_age=none&prize_min=&prize_max=&retired=1&sort=access&submit=&page=2

	public static void main(String[] args) throws IOException {

		for (int i = 1901; i <= 2000; i++) {// 2000までとった
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String url = "https://db.sp.netkeiba.com/?pid=horse_list&word=&sire=&mare=&bms=&trainer=&owner=&breeder=&under_age=2&over_age=none&prize_min=&prize_max=&retired=1&sort=access&submit=&page="
					+ i;
			Document document = Jsoup.connect(url).get();

			// 馬名取得（.DataBox_01 h2）
			Elements horseNames = document.select(".DataBox_01 h2");
			Elements genders = document.select(".DataBox_01 p:nth-of-type(1)");
			Elements fathers = document.select(".DataBox_01 p:nth-of-type(2)");
			Elements mothers = document.select(".DataBox_01 p:nth-of-type(3)");
			// System.out.println("horseNames=" + horseNames);// htmlの該当のタグ部分が取得できる
			for (int j = 0; j < horseNames.size(); j++) {
				String horseName = horseNames.get(j).text();

				if (horseName.contains("'")) {
					int dotNumber = horseName.indexOf("'");
					StringBuilder sb = new StringBuilder(horseName);
					sb.insert(dotNumber, "'");
					horseName = sb.toString();
				}

				String gender = genders.get(j).text().substring(0, 1);
				String birth = genders.get(j).text().substring(2, 7);
				String father = fathers.get(j).text().substring(2);

				if (father.contains("'")) {
					int dotNumber2 = father.indexOf("'");
					StringBuilder sb = new StringBuilder(father);
					sb.insert(dotNumber2, "'");
					father = sb.toString();
				}

				String mother = mothers.get(j).text().substring(2);

				if (mother.contains("'")) {
					int dotNumber3 = mother.indexOf("'");
					StringBuilder sb = new StringBuilder(mother);
					sb.insert(dotNumber3, "'");
					mother = sb.toString();
				}

				System.out.println("INSERT INTO horse_profile(name,birth_year,gender,father,mother)" + " VALUES" + "("
						+ "'" + horseName + "'" + "," + "'" + birth + "'" + "," + "'" + gender + "'" + "," + "'"
						+ father + "'" + "," + "'" + mother + "'" + ");");
			}
		}
	}

}
