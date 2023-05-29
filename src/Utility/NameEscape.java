package Utility;

public class NameEscape {

    /**
     * If horse's name contains apostrophe('), escape this horse_name.
     *
     * */
    public static String escape(String name){

        if (!(name.contains("'"))){
            return name;
        }

        int dotNumber = name.indexOf("'");
        StringBuilder sb = new StringBuilder(name);
        sb.insert(dotNumber, "'");
        name = sb.toString();
        return name;
    }
}
