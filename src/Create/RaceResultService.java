package Create;

import Entity.race.RaceResult;

import java.io.IOException;
import java.util.List;

public interface RaceResultService {
    List<RaceResult> createRaceResult(String raceId) throws IOException;
}
