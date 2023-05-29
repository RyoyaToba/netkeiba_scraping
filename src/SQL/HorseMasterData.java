package SQL;

import Entity.horse.Horse;

public class HorseMasterData implements Data<Horse> {

    /**
     * Insert(Upsert) horse_master
     * */
    @Override
    public String insert(Horse horse) {

        String SQL =
                "INSERT INTO horse_master (id, name, birth_day, gender, father_id, mother_id, " +
                        " hair, situation, owner_id, trainer_id, training_center, breeder_id)"
                        + " VALUES ("
                        + "'" + horse.getId() + "'"
                        + "," + "'" + horse.getName() + "'"
                        + "," + "'" + horse.getBirthDay() + "'"
                        + "," + "'" + horse.getGender() + "'"
                        + "," + "'" + horse.getFatherId() + "'"
                        + "," + "'" + horse.getMotherId() + "'"
                        + "," + "'" + horse.getHair() + "'"
                        + "," + "'" + horse.getSituation() + "'"
                        + "," + "'" + horse.getOwnerId() + "'"
                        + "," + "'" + horse.getTrainerId() + "'"
                        + "," + "'" + horse.getTrainingCenter() + "'"
                        + "," + "'" + horse.getBreederId() + "'"
                        + ") on conflict(id) "
                        + "do update set "
                        + "id = " + "'" + horse.getId() + "'"
                        + ", name = " + "'" + horse.getName() + "'"
                        + ", birth_day =" + "'" + horse.getBirthDay() + "'"
                        + ", gender =" + "'" + horse.getGender() + "'"
                        + ", father_id =" + "'" + horse.getFatherId() + "'"
                        + ", mother_id =" + "'" + horse.getMotherId() + "'"
                        + ", hair =" + "'" + horse.getHair() + "'"
                        + ", situation =" + "'" + horse.getSituation() + "'"
                        + ", owner_id =" + "'" + horse.getOwnerId() + "'"
                        + ", trainer_id =" + "'" + horse.getTrainerId() + "'"
                        + ", training_center =" + "'" + horse.getTrainingCenter() + "'"
                        + ", breeder_id =" + "'" + horse.getBreederId() + "'";
        return SQL;
    }

}
