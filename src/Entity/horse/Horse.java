package Entity.horse;

public class Horse {

    private Integer id;
    private String name;
    private Integer gender;
    private Integer motherId;
    private Integer fatherId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setFatherId(Integer fatherId) {
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
