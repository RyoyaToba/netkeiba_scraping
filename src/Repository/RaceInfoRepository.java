package Repository;

import Utility.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RaceInfoRepository {
    public static List<String> select(String year) {

        String sql = "SELECT distinct id FROM race_result WHERE id like " + "'" + year +  "%" + "'";

        List<String> raceIdList = new ArrayList<>();

        try (Connection con = DBManager.createConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while(rs.next()){
                String raceId = rs.getString("id");
                raceIdList.add(raceId);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return raceIdList;
    }
}
