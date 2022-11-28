package Create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.FukushoEntity;

public class CreateFukusho {
    /** 複勝リストの作成 */
    public List<FukushoEntity> createFukusho(String raceId) throws IOException {

	List<FukushoEntity> fukushoList = new ArrayList<>();
	FukushoEntity fukusho1 = new FukushoEntity();
	FukushoEntity fukusho2 = new FukushoEntity();
	FukushoEntity fukusho3 = new FukushoEntity();

	setRaceId(raceId, fukusho1, fukusho2, fukusho3);

	String url = "https://race.netkeiba.com/race/result.html?race_id=" + raceId;

	Document document = Jsoup.connect(url).get();

	setHorseNumber(fukusho1, fukusho2, fukusho3, document);
	setPayOut(fukusho1, fukusho2, fukusho3, document);
	setPopular(fukusho1, fukusho2, fukusho3, document);

	fukushoList.add(fukusho1);
	fukushoList.add(fukusho2);
	fukushoList.add(fukusho3);

	return fukushoList;
    }

    /** レースIDのセット */
    public void setRaceId(String raceId, FukushoEntity fukusho1, FukushoEntity fukusho2, FukushoEntity fukusho3) {

	fukusho1.setRaceId(raceId);
	fukusho2.setRaceId(raceId);
	fukusho3.setRaceId(raceId);
    }

    /** 人気順のセット */
    public void setPopular(FukushoEntity fukusho1, FukushoEntity fukusho2, FukushoEntity fukusho3, Document document) {
	Elements popularing = document.select("table.payout_Detail_Table tr.Fukusho > td.Ninki");
	for (Element element : popularing) {
	    String popularString = element.text();
	    String[] popularList = popularString.split("人気");

	    fukusho1.setPopular(Integer.parseInt(popularList[0]));
	    fukusho2.setPopular(Integer.parseInt(popularList[1]));

	    if (popularList.length == 3) {
		fukusho3.setPopular(Integer.parseInt(popularList[2]));
	    }
	}
    }

    /** 支払い金額のセット */
    public void setPayOut(FukushoEntity fukusho1, FukushoEntity fukusho2, FukushoEntity fukusho3, Document document) {

	Elements payOutElements = document.select("table.payout_Detail_Table tr.Fukusho > td.Payout");

	for (Element element : payOutElements) {
	    String payOutString = element.text();
	    String[] payOutList = payOutString.split(" ");
	    List<String> newPayOutList = new ArrayList<>();
	    for (String payOutString2 : payOutList) {
		String payOutString3 = payOutString2.replace("円", "").replace(",", "");
		newPayOutList.add(payOutString3);
	    }
	    fukusho1.setPayOut(Integer.parseInt(newPayOutList.get(0)));
	    fukusho2.setPayOut(Integer.parseInt(newPayOutList.get(1)));

	    if (payOutList.length == 3) {
		fukusho3.setPayOut(Integer.parseInt(newPayOutList.get(2)));
	    }
	}
    }

    /** 馬番のセット */
    public void setHorseNumber(FukushoEntity fukusho1, FukushoEntity fukusho2, FukushoEntity fukusho3,
	    Document document) {

	Elements horseNumbers = document.select("table.payout_Detail_Table tr.Fukusho > td.Result");
	for (Element element : horseNumbers) {
	    String horseNumberString = element.text();
	    String[] horseNumberList = horseNumberString.split(" ");
	    fukusho1.setHorseNumber(Integer.parseInt(horseNumberList[0]));
	    fukusho2.setHorseNumber(Integer.parseInt(horseNumberList[1]));

	    if (horseNumberList.length == 3) {
		fukusho3.setHorseNumber(Integer.parseInt(horseNumberList[2]));
	    }
	}
    }
}
