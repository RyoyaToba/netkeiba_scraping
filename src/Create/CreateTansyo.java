package Create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Entity.TanshoEntity;

public class CreateTansyo {

	public List<TanshoEntity> createTansyo(String raceId) throws IOException {

		List<TanshoEntity> tanshoList = new ArrayList<>();
		TanshoEntity tansho1 = new TanshoEntity();
		TanshoEntity tansho2 = new TanshoEntity();

		tansho1.setRaceId(raceId);
		tansho2.setRaceId(raceId);

		Integer horseNumber = 0;
		String payOut = "";
		Integer popular = 0;

		String url = "https://race.netkeiba.com/race/result.html?race_id=" + raceId;

		Document document = Jsoup.connect(url).get();

		Elements horseNumbering = document.select("table.payout_Detail_Table tr.Tansho > td.Result");
		for (Element element : horseNumbering) {
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

		Elements payOuting = document.select("table.payout_Detail_Table tr.Tansho > td.Payout");
		for (Element element : payOuting) {
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

		if (tansho2.getHorseNumber() == null) {
			tanshoList.add(tansho1);
		} else {
			tanshoList.add(tansho1);
			tanshoList.add(tansho2);
		}
		return tanshoList;
	}
}
