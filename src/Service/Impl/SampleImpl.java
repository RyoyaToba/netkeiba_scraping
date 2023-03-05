package Service.Impl;

import Entity.horse.Horse;

import java.io.IOException;

public class SampleImpl {

    public static void main(String[] args) {
        HorseServiceImpl impl = new HorseServiceImpl();
            impl.createHorse(10);
    }
}
