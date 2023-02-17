package Utility;

import Entity.place.Place;

public class ReplacePlaceNumberToPosition {

	public String replacePlaceNumberPosition(Integer placeNum) {

		String placeNumString = "";

//		if (placeNum == 10) {
//			placeNumString = String.valueOf(placeNum);
//		} else {
//			placeNumString = 0 + String.valueOf(placeNum);
//		}

		String position = "";

		switch(placeNum){
			case 1:
				position = Place.SAPPORO.getName();
				break;
			case 2:
				position = Place.HAKODATE.getName();
				break;
			case 3:
				position = Place.FUKUSHIMA.getName();
				break;
			case 4:
				position = Place.NIGATA.getName();
				break;
			case 5:
				position = Place.TOKYO.getName();
				break;
			case 6:
				position = Place.NAKAYAMA.getName();
				break;
			case 7:
				position = Place.CHUKYO.getName();
				break;
			case 8:
				position = Place.KYOTO.getName();
				break;
			case 9:
				position = Place.HANSHIN.getName();
				break;
			case 10:
				position = Place.KOKURA.getName();
		}


//		if (placeNumString.equals("01")) {
//			position = "札幌";
//		} else if (placeNumString.equals("02")) {
//			position = "函館";
//		} else if (placeNumString.equals("03")) {
//			position = "福島";
//		} else if (placeNumString.equals("04")) {
//			position = "新潟";
//		} else if (placeNumString.equals("05")) {
//			position = "東京";
//		} else if (placeNumString.equals("06")) {
//			position = "中山";
//		} else if (placeNumString.equals("07")) {
//			position = "中京";
//		} else if (placeNumString.equals("08")) {
//			position = "京都";
//		} else if (placeNumString.equals("09")) {
//			position = "阪神";
//		} else if (placeNumString.equals("10")) {
//			position = "小倉";
//		}
		return position;
	}

}
