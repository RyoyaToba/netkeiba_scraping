package Utility;

import Enum.PlaceList;

public class ReplacePlaceNumberToPosition {

	public String replacePlaceNumberPosition(Integer placeNum) {

		String position = "";

		switch(placeNum){
			case 1:
				position = PlaceList.SAPPORO.getName();
				break;
			case 2:
				position = PlaceList.HAKODATE.getName();
				break;
			case 3:
				position = PlaceList.FUKUSHIMA.getName();
				break;
			case 4:
				position = PlaceList.NIGATA.getName();
				break;
			case 5:
				position = PlaceList.TOKYO.getName();
				break;
			case 6:
				position = PlaceList.NAKAYAMA.getName();
				break;
			case 7:
				position = PlaceList.CHUKYO.getName();
				break;
			case 8:
				position = PlaceList.KYOTO.getName();
				break;
			case 9:
				position = PlaceList.HANSHIN.getName();
				break;
			case 10:
				position = PlaceList.KOKURA.getName();
		}
		return position;
	}
}
