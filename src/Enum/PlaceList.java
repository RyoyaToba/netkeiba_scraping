package Enum;

import Enum.PlaceDetails.PlaceDetails;

public enum PlaceList {
    SAPPORO("01", "札幌"),
    HAKODATE("02","函館"),
    FUKUSHIMA("03","福島"),
    NIGATA("04","新潟"),
    TOKYO("05","東京"),
    NAKAYAMA("06","中山"),
    CHUKYO("07","中京"),
    KYOTO("08","京都"),
    HANSHIN("09","阪神"),
    KOKURA("10","小倉");

    private String id;
    private  String name;

    private PlaceList(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    /** SAPPORO */
    enum Sapporo implements PlaceDetails {
        Y2022("2", "8"),
        Y2021("",""),
        Y2020("","");

        private String countNum;

        private String dayNum;

        private Sapporo(String dayNum, String countNum){
            this.dayNum = dayNum;
            this.countNum = countNum;
        }

        @Override
        public String getCountNum(){
            return this.countNum;
        }
        @Override
        public String getDayNum(){
            return this.dayNum;
        }
    }
}
