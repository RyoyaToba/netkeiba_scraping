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

    public List<FukushoEntity> createFukusho(String raceId) throws IOException {

	List<FukushoEntity> fukushoList = new ArrayList<>();
	FukushoEntity fukusho1 = new FukushoEntity();
	FukushoEntity fukusho2 = new FukushoEntity();
	FukushoEntity fukusho3 = new FukushoEntity();

	fukusho1.setRaceId(raceId);
	fukusho2.setRaceId(raceId);
	fukusho3.setRaceId(raceId);

	String url = "https://race.netkeiba.com/race/result.html?race_id=" + raceId;

	Document document = Jsoup.connect(url).get();

	Elements horseNumbering = document.select("table.payout_Detail_Table tr.Fukusho > td.Result");
	for (Element element : horseNumbering) {
	    String horseNumberString = element.text();
	    String[] horseNumberList = horseNumberString.split(" ");
	    fukusho1.setHorseNumber(Integer.parseInt(horseNumberList[0]));
	    fukusho2.setHorseNumber(Integer.parseInt(horseNumberList[1]));

	    if (horseNumberList.length == 3) {
		fukusho3.setHorseNumber(Integer.parseInt(horseNumberList[2]));
	    }
	}

	Elements payOuting = document.select("table.payout_Detail_Table tr.Fukusho > td.Payout");
	for (Element element : payOuting) {
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
	fukushoList.add(fukusho1);
	fukushoList.add(fukusho2);
	fukushoList.add(fukusho3);
	return fukushoList;
    }
}
