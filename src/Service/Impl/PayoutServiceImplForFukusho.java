package Service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Service.PayoutService;
import Entity.Payout.Fukusho;
import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PayoutServiceImplForFukusho implements PayoutService {
    /** 複勝リストの作成 */
	@Override
    public List<Fukusho> createPayoutResult(String raceId) throws IOException {

	List<Fukusho> fukushoList = new ArrayList<>();
	Fukusho fukusho1 = new Fukusho();
	Fukusho fukusho2 = new Fukusho();
	Fukusho fukusho3 = new Fukusho();

	setRaceId(raceId, fukusho1, fukusho2, fukusho3);

	String url = NetkeibaURL.PAYOUT_PAGE_URL + raceId;

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
    public void setRaceId(String raceId, Fukusho fukusho1, Fukusho fukusho2, Fukusho fukusho3) {

	fukusho1.setRaceId(raceId);
	fukusho2.setRaceId(raceId);
	fukusho3.setRaceId(raceId);
    }

    /** 人気順のセット */
    public void setPopular(Fukusho fukusho1, Fukusho fukusho2, Fukusho fukusho3, Document document) {
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
    public void setPayOut(Fukusho fukusho1, Fukusho fukusho2, Fukusho fukusho3, Document document) {

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
    public void setHorseNumber(Fukusho fukusho1, Fukusho fukusho2, Fukusho fukusho3,
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
