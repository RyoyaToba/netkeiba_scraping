package SQL;

import Entity.race.RaceResult;

import java.util.HashMap;
import java.util.Map;

public class RaceResultData implements Data<RaceResult>{
    @Override
    public String insert(RaceResult raceResult) {
        String raceResultSql = "INSERT INTO race_result (id, race_id, rank, waku, horse_number, horse_id, horse_gender, horse_age, " +
                "jockey_weight, jockey_id, race_time, arrival, popularity, odds, second_half_three_furlong_time, " +
                "corner_access_location, trainer_id, training_center)"
                +"VALUES("
                +"'" + raceResult.getRaceId() + "-" + raceResult.getRank() + "'"
                +"," + "'" + raceResult.getRaceId() + "'"
                +"," + "'" + raceResult.getRank() + "'"
                +"," + "'" + raceResult.getWaku() + "'"
                +"," + "'" + raceResult.getHorseNumber() + "'"
                +"," + "'" + raceResult.getHorseId() + "'"
                +"," + "'" + raceResult.getGender() + "'"
                +"," + "'" + raceResult.getAtThatAge() + "'"
                +"," + "'" + raceResult.getJockeyWeight() + "'"
                +"," + "'" + raceResult.getJockeyId() + "'"
                +"," + "'" + raceResult.getRaceTime() + "'"
                +"," + "'" + raceResult.getArrival() + "'"
                +"," + "'" + raceResult.getPopularity() + "'"
                +"," + "'" + raceResult.getOdds() + "'"
                +"," + "'" + raceResult.getSecondHalfThreeFurlongTime() + "'"
                +"," + "'" + raceResult.getCornerAccessLocation() + "'"
                +"," + "'" + raceResult.getTrainerId() + "'"
                +"," + "'" + raceResult.getTrainingCenter() + "'"
                +")"
                + "on conflict(id) "
                + "do update set "
                + "id =" + "'" + raceResult.getRaceId() + "-" + raceResult.getRank() + "'"
                + ", race_id = " + "'" + raceResult.getRaceId() + "'"
                + ", rank = " + "'" + raceResult.getRank() + "'"
                + ", waku = " + "'" + raceResult.getWaku() + "'"
                + ", horse_number = " + "'" + raceResult.getHorseNumber() + "'"
                + ", horse_id = " + "'" + raceResult.getHorseId() + "'"
                + ", horse_gender = " + "'" + raceResult.getGender() + "'"
                + ", horse_age = " + "'" + raceResult.getAtThatAge() + "'"
                + ", jockey_weight =" + "'" + raceResult.getJockeyWeight() + "'"
                + ", jockey_id =" + "'" + raceResult.getJockeyId() + "'"
                + ", race_time =" + "'" + raceResult.getRaceTime() + "'"
                + ", arrival =" + "'" + raceResult.getArrival() + "'"
                + ", popularity =" + "'" + raceResult.getPopularity() + "'"
                + ", odds =" + "'" + raceResult.getOdds() + "'"
                + ", second_half_three_furlong_time =" + "'" + raceResult.getSecondHalfThreeFurlongTime() + "'"
                + ", corner_access_location =" + "'" + raceResult.getCornerAccessLocation() + "'"
                + ", trainer_id =" + "'" + raceResult.getTrainerId() + "'"
                + ", training_center =" + "'" + raceResult.getTrainingCenter() + "'"
                ;

        return raceResultSql;
    }

}
