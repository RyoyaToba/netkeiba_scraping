package Repository;

import Utility.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HorseMasterRepository {
    public static List<String> select() {

        String sql = "SELECT distinct id FROM horse_master";

        List<String> horseIdList = new ArrayList<>();

        try (Connection con = DBManager.createConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while(rs.next()){
                String horseId = rs.getString("id");
                horseIdList.add(horseId);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return horseIdList;
    }

    public static List<String> selectParentData(String year) {

        String sql = "SELECT distinct " +
                     "m.father_id, m.mother_id " +
                     "FROM horse_master as m " +
                     "JOIN race_result as r " +
                     "ON m.id = r.horse_id " +
                     "WHERE r.race_id like " + "'" + year + "05030211%'";

        System.out.println(sql);

        List<String> horseIdList = new ArrayList<>();

        try (Connection con = DBManager.createConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while(rs.next()){
                String fatherId = rs.getString("father_id");
                String motherId = rs.getString("mother_id");
                horseIdList.add(fatherId);
                horseIdList.add(motherId);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return horseIdList;
    }

}
