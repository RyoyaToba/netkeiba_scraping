package SQL;

import Entity.horse.Horse;

import java.util.Map;

public class HorseData implements Data<Horse> {

    @Override
    public String insert(Horse horse) {

        String SQL =
                "INSERT INTO horse (id, name, gender, birth_day, gender, father_id, mother_id, mother_father_id)"
                        + "VALUES ("
                        + "'" + horse.getId() + "'"
                        + "," + "'" + horse.getName() + "'"
                        + "," + "'" + horse.getBirthDay() + "'"
                        + "," + "'" + horse.getGender() + "'"
                        + "," + "'" + horse.getFatherId() + "'"
                        + "," + "'" + horse.getMotherId() + "'"
                        + "," + "'" + horse.getMotherFatherId() + "'"
                        + ")";
        return SQL;
    }

}
