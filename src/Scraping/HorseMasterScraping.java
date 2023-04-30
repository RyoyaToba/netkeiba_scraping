package Scraping;

import Entity.horse.Horse;
import Entity.race.RaceInfo;
import Service.Impl.CurrentRaceInfoServiceImpl;
import Service.Impl.CurrentRaceResultServiceImpl;
import Utility.NameEscape;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HorseMasterScraping {

    public static List<Horse> createHorseMasterList(List<String> horseIdList) throws IOException{

        List<Horse> horseList = new ArrayList<>();
        int loopCount = 1;

        System.out.println("=================Scraping Start==================");

        for (String horseId : horseIdList){

            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Horse horse = new Horse();

            horse.setId(horseId);

            String URL = NetkeibaURL.HORSE_DATA_URL + horseId;
            Document document = Jsoup.connect(URL).get();

            /** 馬名 */
            Elements elemHorseNames = document.select(".Name > h1");
            for (Element element : elemHorseNames){
                String horseName = element.text();
                horse.setName(horseName);
            }

            /** 性別 */
            Elements elemGenders = document.select(".Data span:nth-of-type(2)");
            for (Element element : elemGenders){
                String retriveGenderData = element.text();
                String gender = CurrentRaceResultServiceImpl.convertGenderStringToNumber(retriveGenderData);
                horse.setGender(gender);
            }

            /** 父馬Id */
            Elements elemFathers = document.select("td.Sire");
            for (Element element : elemFathers){
                String fatherId = CurrentRaceResultServiceImpl.returnHorseId(element);
                horse.setFatherId(fatherId);
            }


            /** 母馬Id */
            Elements elemMothers = document.select("td.Dam");
            for (Element element : elemMothers){
                String fatherId = CurrentRaceResultServiceImpl.returnHorseId(element);
                horse.setMotherId(fatherId);
            }

            /** 毛色 */
            Elements elemHairs = document.select(".Data span:nth-of-type(3)");
            for (Element element : elemHairs){
                String hair = element.text();
                horse.setHair(hair);
            }

            /** 誕生日 */
            Elements elembirthDay = document.select("#DetailTable tr:nth-of-type(9) td");
            for (Element element : elembirthDay){
                String birthDay = element.text();
                horse.setBirthDay(convertBirthDayStringToDate(birthDay));
            }

            /** 現在の状況　現役 or 引退 or 繁殖　など */
            Elements elemSituation = document.select(".Data span:nth-of-type(4)");
            for (Element element : elemSituation){
                String situation = element.text();
                horse.setSituation(situation);
            }

            /** オーナー */
            Elements elemOwners = document.select("td.Owner");
            for (Element element : elemOwners){
                String owner = element.text();
                horse.setOwner(owner);
                horse.setOwnerId(retriveOwnerId(element));
            }

            /** トレセン・調教師・調教師IDを設定 */
            Elements elemTrainers = document.select("#DetailTable tr:nth-of-type(4) td");
            for (Element element : elemTrainers){
                String target = element.text(); // 例：target = 栗東 山田太郎

                if(!target.contains(" ")){
                    continue;
                }

                horse.setTrainingCenter(target.substring(0,target.indexOf(" ")));
                horse.setTrainer(target.substring(target.indexOf(" ") +1, target.length()));
                horse.setTrainerId(retriveTrainerId(element));
            }

            /** 生産者・生産者IDを設定 */
            Elements elemBreeders = document.select("#DetailTable tr:nth-of-type(5) td");
            for (Element element : elemBreeders){
                String target = element.text();
                horse.setBreeder(target.substring(target.indexOf(" ") +1, target.length()));
                horse.setBreederId(retriveBreederId(element));
            }

            System.out.println("【" + horseId + "】" + "total : " + loopCount);
            horseList.add(horse);
            loopCount++;
        }

        return horseList;
    }

    private static Date convertBirthDayStringToDate(String birthDay){

        if (!birthDay.contains("年") || !birthDay.contains("月") || !birthDay.contains("日")){
            return Date.valueOf("9999-01-01");
        }

        String year = birthDay.substring(0,birthDay.indexOf("年"));
        String month = birthDay.substring(birthDay.indexOf("年") + 1, birthDay.indexOf("月"));
        String day = birthDay.substring(birthDay.indexOf("月") + 1, birthDay.indexOf("日"));
        return  Date.valueOf(year + "-" + month + "-" + day);
    }

    private static String retriveOwnerId(Element element){
        Integer ownerIdStart = element.outerHtml().indexOf("owner/") + 6;
        Integer ownerIdEnd = ownerIdStart + 6;
        String ownerId = element.outerHtml().substring(ownerIdStart, ownerIdEnd);
        return ownerId;
    }

    private static String retriveTrainerId(Element element){
        Integer trainerIdStart = element.outerHtml().indexOf("trainer/") + 8;
        Integer trainerIdEnd = trainerIdStart + 5;
        String trainerId = element.outerHtml().substring(trainerIdStart, trainerIdEnd);
        return trainerId;
    }

    private static String retriveBreederId(Element element){
        Integer breederIdStart = element.outerHtml().indexOf("breeder/") + 8;
        Integer breederIdEnd = breederIdStart + 6;
        String breederId = element.outerHtml().substring(breederIdStart, breederIdEnd);

        if(breederId.equals("pan cl")){
            return null;
        }

        return breederId;
    }

}
