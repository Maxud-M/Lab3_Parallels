public class ResultData {
    private float maxDelay;
    private float percentCancelled;
    private int countOfFlights;
    private int countOfCancelledFlights;



    public int getCountOfFlights() {
        return countOfFlights;
    }

    public int getCountOfCancelledFlights() {
        return countOfCancelledFlights;
    }

    public float getMaxDelay() {
        return maxDelay;
    }

    public float getPercentCancelled() {
        return percentCancelled;
    }

    ResultData(float maxDelay, float percentCancelled) {
        this.maxDelay = maxDelay;
        this.percentCancelled = percentCancelled;
    }

    ResultData() {
        maxDelay = 0F;
        percentCancelled = 0F;
        countOfCancelledFlights = 0;
        countOfFlights = 0;
    }

    public void add(FlightData flightData) {
        if(flightData.getDelay() > maxDelay) {
            maxDelay = flightData.getDelay();
        }
        countOfFlights++;
        if(flightData.getDelay() == 0F || flightData.getCancelled() == 1) {
            countOfCancelledFlights++;
        }
        percentCancelled = countOfCancelledFlights / countOfFlights;
    }

    public void addAll(ResultData resultData) {
        if(resultData.getMaxDelay() > maxDelay) {
            maxDelay = resultData.getMaxDelay();
        }
        countOfFlights += resultData.getCountOfFlights();
        countOfCancelledFlights += resultData.getCountOfCancelledFlights();
        percentCancelled = countOfCancelledFlights / countOfFlights;
    }
}
