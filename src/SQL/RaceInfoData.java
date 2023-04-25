package SQL;

import Entity.race.RaceInfo;

public class RaceInfoData implements Data<RaceInfo>{

    @Override
    public String insert(RaceInfo raceInfo) {
        String SQL = "INSERT INTO race_info (race_id, race_day, race_name, race_type, distance, fieldCondition)"
                +"VALUES("
                +"'" + raceInfo.getRaceId() + "'"
                +"," + "'" + raceInfo.getRaceDay() + "'"
                +"," + "'" + raceInfo.getRaceName() + "'"
                +"," + "'" + raceInfo.getRaceType() + "'"
                +"," + "'" + raceInfo.getDistance() + "'"
                +"," + "'" + raceInfo.getFieldCondition() + "'"
                +")"
                + "on conflict(race_id) "
                + "do update set"
                + "race_id = " + "'" + raceInfo.getRaceId() + "'"
                + ", race_day = " + "'" + raceInfo.getRaceDay() + "'"
                + ", race_name = " + "'" + raceInfo.getRaceName() + "'"
                + ", race_type = " + "'" + raceInfo.getRaceType() + "'"
                + ", distance = " + "'" + raceInfo.getDistance() + "'"
                + ", fieldCondition = " + "'" + raceInfo.getFieldCondition() + "'";

        return SQL;
    }

}
