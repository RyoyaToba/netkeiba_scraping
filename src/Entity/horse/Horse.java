package Entity.horse;

public class Horse {

    private String id;
    private String name;
    private String birthDay;
    private String gender;
    private String motherId;
    private String fatherId;
    private String motherFatherId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDay(){ return birthDay; }

    public String getGender() {
        return gender;
    }

    public String getMotherId() {
        return motherId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public String getMotherFatherId() { return motherFatherId; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(String birthDay){
        this.birthDay = birthDay;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public void setMotherFatherId(String motherFatherId){
        this.motherFatherId = motherFatherId;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", motherId=" + motherId +
                ", fatherId=" + fatherId +
                '}';
    }
}
