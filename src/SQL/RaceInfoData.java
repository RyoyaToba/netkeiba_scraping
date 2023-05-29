package SQL;

import Entity.race.RaceInfo;

import java.util.Map;

public class RaceInfoData implements Data<RaceInfo>{

    /**
     * Insert(Upsert) race_information
     * */
    @Override
    public String insert(RaceInfo raceInfo) {
        String SQL = "INSERT INTO race_information (id, day, name, type, distance, field_condition)"
                +"VALUES("
                +"'" + raceInfo.getRaceId() + "'"
                +"," + "'" + raceInfo.getRaceDay() + "'"
                +"," + "'" + raceInfo.getRaceName() + "'"
                +"," + "'" + raceInfo.getRaceType() + "'"
                +"," + "'" + raceInfo.getDistance() + "'"
                +"," + "'" + raceInfo.getFieldCondition() + "'"
                +")"
                + "on conflict(id) "
                + "do update set "
                + "id = " + "'" + raceInfo.getRaceId() + "'"
                + ", day = " + "'" + raceInfo.getRaceDay() + "'"
                + ", name = " + "'" + raceInfo.getRaceName() + "'"
                + ", type = " + "'" + raceInfo.getRaceType() + "'"
                + ", distance = " + "'" + raceInfo.getDistance() + "'"
                + ", field_condition = " + "'" + raceInfo.getFieldCondition() + "'";

        return SQL;
    }

}
