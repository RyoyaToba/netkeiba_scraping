package Service.Impl;

import Entity.horse.Horse;
import Service.HorseService;
import Utility.NameEscape;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorseServiceImpl implements HorseService {

    /** 競走馬Listの画面からHorseIdを取得し、IdListを作成する*/
    @Override
    public List<String> getHorseIdList(int i) throws IOException {
        String url = NetkeibaURL.HORSE_LIST_URL + i;
        Document document = Jsoup.connect(url).get();
        List<String> horseIdList = new ArrayList<>();

        Elements elements = document.select("ul.BreederList img");
        for (Element element : elements){
            String target = getId(element);
            horseIdList.add(target);
        }
        return horseIdList;
    }

    //TODO 競走馬詳細から情報を取得し、HorseEntityを作成する
    @Override
    public Horse createHorse(int i){
        List<Horse> horseList = new ArrayList<>();
        try {
            List<String> horseIdList = getHorseIdList(i);

            for (String horseId : horseIdList){
                String URL = NetkeibaURL.HORSE_DATA_URL + horseId;
                Document document = Jsoup.connect(URL).get();
                // TODO Horseにつめるロジックを実装予定

                System.out.println(horseId + "のDOCUMENTを取得しました");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /** 取得したElementからHorseId部分だけを取得する*/
    private String getId(Element element){
        int startIdNum = element.outerHtml().indexOf("id=") + 3;
        int endIdNum = startIdNum + 10;
        return element.outerHtml().substring(startIdNum, endIdNum);
    }

}
