package Utility;

import Enum.Place;

public class ReplacePlaceNumberToPosition {

	public String replacePlaceNumberPosition(Integer placeNum) {

		String placeNumString = "";

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
		return position;
	}

}
