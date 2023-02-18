package Enum.PlaceDetails;

public enum Chukyo implements PlaceDetails {

    Y2022("6", "12"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Chukyo(String dayNum, String countNum){
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
