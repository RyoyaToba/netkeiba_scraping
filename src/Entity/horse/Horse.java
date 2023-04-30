package Entity.horse;

import lombok.Data;

import java.sql.Date;

@Data
public class Horse {

    private String id;
    private String name;
    private Date birthDay;
    private String gender;
    private String motherId;
    private String fatherId;
    private String hair;
    private String situation;
    private String owner;
    private String ownerId;
    private String trainer;
    private String trainerId;
    private String trainingCenter;
    private String breeder;
    private String breederId;

}
