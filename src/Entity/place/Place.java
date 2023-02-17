package Entity.place;

public enum Place {
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

    private Place(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

}
