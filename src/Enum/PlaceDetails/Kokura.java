package Enum.PlaceDetails;

import java.util.HashMap;
import java.util.Map;

public enum Kokura implements PlaceDetails {

    Y2022("4", "8"),

    Y2021("4","8"),

    Y2020("2","12"),

    Y2019("2","12"),

    Y2018("",""),

    Y2017("",""),

    Y2016("",""),

    Y2015("",""),

    Y2014("",""),

    Y2013("",""),

    Y2012("","");

    private String countNum;

    private String dayNum;

    private Kokura(String countNum, String dayNum){
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
            case "Y2021":
                dataMap.put("countNum", Y2021.getCountNum());
                dataMap.put("dayNum", Y2021.getDayNum());
                break;
            case "Y2020":
                dataMap.put("countNum", Y2020.getCountNum());
                dataMap.put("dayNum", Y2020.getDayNum());
                break;
            case "Y2019":
                dataMap.put("countNum", Y2019.getCountNum());
                dataMap.put("dayNum", Y2019.getDayNum());
                break;
            case "Y2018":
                dataMap.put("countNum", Y2018.getCountNum());
                dataMap.put("dayNum", Y2018.getDayNum());
                break;
            case "Y2017":
                dataMap.put("countNum", Y2017.getCountNum());
                dataMap.put("dayNum", Y2017.getDayNum());
                break;
            case "Y2016":
                dataMap.put("countNum", Y2016.getCountNum());
                dataMap.put("dayNum", Y2016.getDayNum());
                break;
            case "Y2015":
                dataMap.put("countNum", Y2015.getCountNum());
                dataMap.put("dayNum", Y2015.getDayNum());
                break;
            case "Y2014":
                dataMap.put("countNum", Y2014.getCountNum());
                dataMap.put("dayNum", Y2014.getDayNum());
                break;
            case "Y2013":
                dataMap.put("countNum", Y2013.getCountNum());
                dataMap.put("dayNum", Y2013.getDayNum());
                break;
            case "Y2012":
                dataMap.put("countNum", Y2012.getCountNum());
                dataMap.put("dayNum", Y2012.getDayNum());
                break;
            default:
                return null;
        }
        return dataMap;
    }
}
