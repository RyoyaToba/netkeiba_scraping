package Enum.PlaceDetails;

public enum Nigata implements PlaceDetails {

    Y2022("3", "8"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Nigata(String dayNum, String countNum){
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
