package Create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utility.NetkeibaURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.Payout.Tansho;

public class CreateTansyo {

    public List<Tansho> createTansyo(String raceId) throws IOException {

	List<Tansho> tanshoList = new ArrayList<>();
	Tansho tansho1 = new Tansho();
	Tansho tansho2 = new Tansho();

	setRaceId(raceId, tansho1, tansho2);

	String url = NetkeibaURL.PAYOUT_PAGE_URL + raceId;

	Document document = Jsoup.connect(url).get();

	setHorseNumber(tansho1, tansho2, document);
	setPayOut(tansho1, tansho2, document);
	setPopular(tansho1, tansho2, document);

	if (tansho2.getHorseNumber() == null) {
	    tanshoList.add(tansho1);
	} else {
	    tanshoList.add(tansho1);
	    tanshoList.add(tansho2);
	}
	return tanshoList;
    }

    /** 人気順をセット */
    public void setPopular(Tansho tansho1, Tansho tansho2, Document document) {
	Integer popular;
	Elements popularing = document.select("table.payout_Detail_Table tr.Tansho > td.Ninki");
	for (Element element : popularing) {
	    String popularString = element.text();

	    if (popularString.length() >= 5) {
		String popularString1 = popularString.substring(0, popularString.indexOf("人"));
		tansho1.setPopular(Integer.parseInt(popularString1));

		String popularString2 = popularString.substring(popularString.indexOf("気") + 1,
			popularString.indexOf("気") + 2);
		tansho2.setPopular(Integer.parseInt(popularString2));

	    } else {
		popular = Integer.parseInt(popularString.substring(0, popularString.indexOf("人気")));
		tansho1.setPopular(popular);
	    }
	}
    }

    /** 支払金をセット */
    public void setPayOut(Tansho tansho1, Tansho tansho2, Document document) {
	String payOut;
	Elements payOuts = document.select("table.payout_Detail_Table tr.Tansho > td.Payout");
	for (Element element : payOuts) {
	    String payOutString = element.text();

	    if (payOutString.contains(" ")) {
		String payOutString1 = payOutString.substring(0, payOutString.indexOf("円"));

		if (payOutString1.contains(",")) {
		    String payOutString11 = payOutString1.replace(",", "");
		    tansho1.setPayOut(Integer.parseInt(payOutString11));
		} else {
		    tansho1.setPayOut(Integer.parseInt(payOutString1));
		}

		String payOutString2 = payOutString.substring(payOutString.indexOf(" ") + 1, payOutString.length() - 1);
		if (payOutString2.contains(",")) {
		    String payOutString22 = payOutString2.replace(",", "");
		    tansho2.setPayOut(Integer.parseInt(payOutString22));
		} else {
		    tansho2.setPayOut(Integer.parseInt(payOutString2));
		}

	    } else {
		payOutString = element.text().substring(0, element.text().indexOf("円"));
		payOut = payOutString.replace(",", "");
		tansho1.setPayOut(Integer.parseInt(payOut));
	    }
	}
    }

    /** 馬番をセット */
    public void setHorseNumber(Tansho tansho1, Tansho tansho2, Document document) {
	Integer horseNumber;
	Elements horseNumbers = document.select("table.payout_Detail_Table tr.Tansho > td.Result");
	for (Element element : horseNumbers) {
	    String horseNumberString = element.text();

	    if (horseNumberString.contains(" ")) {
		String horseNumberString1 = horseNumberString.substring(0, horseNumberString.indexOf(" "));
		tansho1.setHorseNumber(Integer.parseInt(horseNumberString1));
		String horseNumberString2 = horseNumberString.substring(horseNumberString.indexOf(" ") + 1,
			horseNumberString.length());
		tansho2.setHorseNumber(Integer.parseInt(horseNumberString2));
	    } else {
		horseNumber = Integer.parseInt(horseNumberString);
		tansho1.setHorseNumber(horseNumber);
	    }
	}
    }

    /** レースIdをセット */
    public void setRaceId(String raceId, Tansho tansho1, Tansho tansho2) {
	tansho1.setRaceId(raceId);
	tansho2.setRaceId(raceId);
    }
}
