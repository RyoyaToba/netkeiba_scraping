package Service;

import Entity.race.RaceInfo;
import java.io.IOException;

public interface RaceInfoService {
    RaceInfo createRaceInfo(String raceId) throws IOException;

}
