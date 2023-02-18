package Enum.PlaceDetails;

public enum Hanshin implements PlaceDetails {

    Y2022("5", "8"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Hanshin(String dayNum, String countNum){
        this.dayNum = dayNum;
        this.countNum = countNum;
    }

    @Override
    public String getCountNum(){
        return this.countNum;
    }
    @Override
    public String getDayNum(){
        return this.dayNum;
    }
}
