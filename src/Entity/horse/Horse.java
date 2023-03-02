package Entity.horse;

public class Horse {

    private String id;
    private String name;
    private String gender;
    private String motherId;
    private String fatherId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMotherId() {
        return motherId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
