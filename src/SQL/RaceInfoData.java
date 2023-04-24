package SQL;

import Entity.race.RaceInfo;

public class RaceInfoData implements Data<RaceInfo>{

    @Override
    public String insert(RaceInfo raceInfo) {
        String SQL = "INSERT INTO race_info (race_id, race_day, race_number, race_name, race_type, distance, field)"
                +"VALUES ("
                +"'" + raceInfo.getRaceId() + "'"
                +"," + "'" + raceInfo.getRaceDay() + "'"
                +"," + "'" + raceInfo.getRaceNumber() + "'"
                +"," + "'" + raceInfo.getRaceName() + "'"
                +"," + "'" + raceInfo.getRaceType() + "'"
                +"," + "'" + raceInfo.getDistance() + "'"
                +"," + "'" + raceInfo.getField() + "'"
                +")";
        return SQL;
    }

}
