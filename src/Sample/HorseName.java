package Sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entity.horse.Horse;
import SQL.Data;
import SQL.HorseData;
import Utility.NameEscape;
import Utility.NetkeibaURL;
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

      String url = NetkeibaURL.HORSE_LIST_URL + i;
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
          NameEscape.escape(horseName);
        }

        String gender = genders.get(j).text().substring(0, 1);
        String birth = genders.get(j).text().substring(2, 7);
        String father = fathers.get(j).text().substring(2);

        if (father.contains("'")) {
          NameEscape.escape(father);
        }

        String mother = mothers.get(j).text().substring(2);

        if (mother.contains("'")) {
          NameEscape.escape(mother);
        }

        Horse horse = new Horse();

        Connection con = DBManager.createConnection();
        try {
          Data data = new HorseData();
          String SQL = data.insert(horse);

          System.out.println("INSERT INTO horse_profile(name,birth_year,gender,father,mother)"
              + " VALUES" + "(" + "'" + horseName + "'" + "," + "'" + birth + "'" + "," + "'"
              + gender + "'" + "," + "'" + father + "'" + "," + "'" + mother + "'" + ");");
          pstmt = con.prepareStatement(SQL);
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
