package Repository;

import Utility.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AllRepository {

    public static void upsertData(List<String> sqlList) {

        try (Connection con = DBManager.createConnection();
             Statement statement = con.createStatement();) {

            for (String sql : sqlList) {
                statement.addBatch(sql);
            }

            int[] result = statement.executeBatch();
            System.out.println(result.length + "件データを投入しました");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
