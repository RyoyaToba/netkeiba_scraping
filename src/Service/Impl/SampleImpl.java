package Service.Impl;

import Entity.horse.Horse;
import Utility.NameEscape;

import java.io.IOException;

public class SampleImpl {

    public static void main(String[] args) {

        String name = "center's half";
        NameEscape.escape(name);
        System.out.println(name);

    }
}
