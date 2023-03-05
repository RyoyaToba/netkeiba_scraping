package Utility;

public class NameEscape {

    public static void escape(String name){
        int dotNumber = name.indexOf("'");
        StringBuilder sb = new StringBuilder(name);
        sb.insert(dotNumber, "'");
        name = sb.toString();
    }
}
