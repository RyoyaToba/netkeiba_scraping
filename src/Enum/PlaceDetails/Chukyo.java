package Enum.PlaceDetails;

import java.util.HashMap;
import java.util.Map;

public enum Chukyo implements PlaceDetails {

    Y2022("6", "12"),

    Y2021("",""),

    Y2020("","");

    private String countNum;

    private String dayNum;

    private Chukyo(String countNum, String dayNum){
        this.countNum = countNum;
        this.dayNum = dayNum;
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
