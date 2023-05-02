package Enum.PlaceDetails;

import java.util.HashMap;
import java.util.Map;

public enum Kyoto implements PlaceDetails {

    Y2023("1","6"),

    Y2022("0", "0"),

    Y2021("0","0"),

    Y2020("4","12"),

    Y2019("5","12"),

    Y2018("5","12"),

    Y2017("5","12"),

    Y2016("5","12"),

    Y2015("5","12"),

    Y2014("5","12"),

    Y2013("5","12"),

    Y2012("5","12");

    private String countNum;

    private String dayNum;

    private Kyoto(String countNum, String dayNum){
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
            case "Y2023":
                dataMap.put("countNum", Y2023.getCountNum());
                dataMap.put("dayNum", Y2023.getDayNum());
                break;
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
