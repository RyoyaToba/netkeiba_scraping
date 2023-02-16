package Entity;

public class Payout {
        private String raceId;
        private Integer horseNumber;
        private Integer payOut;
        private Integer popular;

        public String getRaceId() {
            return raceId;
        }
        public void setRaceId(String raceId) {
            this.raceId = raceId;
        }
        public Integer getHorseNumber() {
            return horseNumber;
        }
        public void setHorseNumber(Integer horseNumber) {
            this.horseNumber = horseNumber;
        }
        public Integer getPayOut() {
            return payOut;
        }
        public void setPayOut(Integer payOut) {
            this.payOut = payOut;
        }
        public Integer getPopular() {
            return popular;
        }
        public void setPopular(Integer popular) {
            this.popular = popular;
        }
        @Override
        public String toString() {
            return "HukushoEntity [raceId=" + raceId + ", horseNumber=" + horseNumber + ", payOut=" + payOut + ", popular="
                    + popular + "]";
        }
    }