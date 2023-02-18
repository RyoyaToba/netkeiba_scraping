package Enum.PlaceDetails;

import Enum.PlaceDetails.PlaceDetails;

public enum Tokyo implements PlaceDetails {

    Y2022("5", "12"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Tokyo(String dayNum, String countNum){
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
