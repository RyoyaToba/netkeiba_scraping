package Sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import Utility.DBManager;

public class HorseName {

  public static void main(String[] args) throws IOException {

    PreparedStatement pstmt = null;

    for (int i = 4573; i <= 4574; i++) {// 4574までとった 全部で26800ページ
      try {
        Thread.sleep(1 * 3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String url =
          "https://db.sp.netkeiba.com/?pid=horse_list&word=&sire=&mare=&bms=&trainer=&owner=&breeder=&under_age=2&over_age=none&prize_min=&prize_max=&retired=1&sort=access&submit=&page="
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

        Connection con = DBManager.createConnection();
        try {
          String sql = "INSERT INTO horse_profile(name,birth_year,gender,father,mother)" + " VALUES"
              + "(" + "'" + horseName + "'" + "," + "'" + birth + "'" + "," + "'" + gender + "'"
              + "," + "'" + father + "'" + "," + "'" + mother + "'" + ")";

          System.out.println("INSERT INTO horse_profile(name,birth_year,gender,father,mother)"
              + " VALUES" + "(" + "'" + horseName + "'" + "," + "'" + birth + "'" + "," + "'"
              + gender + "'" + "," + "'" + father + "'" + "," + "'" + mother + "'" + ");");
          pstmt = con.prepareStatement(sql);
          pstmt.executeUpdate();

        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          DBManager.closeConnection(con);
        }
      }
      System.err.println(i + "finish!");
    }
  }
}
