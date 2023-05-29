package Entity.horse;

import lombok.Data;

import java.sql.Date;

@Data
public class Horse {
    /** horseID */
    private String id;
    /** 名前 */
    private String name;
    /** 誕生日 */
    private Date birthDay;
    /** 性別 */
    private String gender;
    /** 母馬ID */
    private String motherId;
    /** 父馬ID */
    private String fatherId;
    /** 毛色 */
    private String hair;
    /** 状態 */
    private String situation;
    /** 馬主 */
    private String owner;
    /** 馬主ID */
    private String ownerId;
    /** 調教師 */
    private String trainer;
    /** 調教師ID */
    private String trainerId;
    /** トレーニングセンター */
    private String trainingCenter;
    /** 厩舎 */
    private String breeder;
    /** 厩舎ID */
    private String breederId;

}
