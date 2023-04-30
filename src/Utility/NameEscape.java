package Utility;

public class NameEscape {

    public static String escape(String name){
        int dotNumber = name.indexOf("'");
        StringBuilder sb = new StringBuilder(name);
        sb.insert(dotNumber, "'");
        name = sb.toString();
        return name;
    }
}
