package Create;

import Entity.Payout.Payout;

import java.io.IOException;
import java.util.List;

public interface PayoutService {
    List<? extends Payout> createPayoutResult(String raceId) throws IOException;
}
