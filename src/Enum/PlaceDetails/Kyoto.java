package Enum.PlaceDetails;

public enum Kyoto implements PlaceDetails {

    Y2022("0", "0"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Kyoto(String dayNum, String countNum){
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
