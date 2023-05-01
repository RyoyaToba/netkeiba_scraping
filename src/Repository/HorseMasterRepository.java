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
}
