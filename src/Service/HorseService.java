package Service;

import Entity.horse.Horse;

import java.io.IOException;
import java.util.List;

public interface HorseService {
    Horse createHorse(int i) throws IOException;

    List<String> getHorseIdList(int i) throws IOException;
}
