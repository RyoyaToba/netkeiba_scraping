package Enum.PlaceDetails;

import Enum.PlaceDetails.PlaceDetails;

import java.util.HashMap;
import java.util.Map;

public enum Sapporo implements PlaceDetails {

    Y2022("2", "8"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Sapporo(String dayNum, String countNum){
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

    public static Map<String, String> getData(String targetYear){

        Map<String, String> dataMap = new HashMap<>();

        switch (targetYear){
            case "Y2022":
                dataMap.put("countNum", Y2022.getCountNum());
                dataMap.put("dayNum", Y2022.getDayNum());
                break;
            default:
                return null;
        }
        return dataMap;
    }
}
