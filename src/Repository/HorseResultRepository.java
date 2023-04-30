package Repository;

import Entity.race.RaceResult;
import Utility.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HorseResultRepository {
    public static List<String> select(String year) {

        String sql = "SELECT distinct horse_id FROM race_result WHERE race_id like " + "'" + year +  "%" + "'";

        List<String> horseIdList = new ArrayList<>();

        try (Connection con = DBManager.createConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while(rs.next()){
                String horseId = rs.getString("horse_id");
                horseIdList.add(horseId);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return horseIdList;
    }
}
