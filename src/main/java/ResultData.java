import java.io.Serializable;

public class ResultData implements Serializable {
    private float maxDelay;
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

    public float calculatePercentCancelled() {
        return countOfCancelledFlights / (float)countOfFlights;
    }

    ResultData(float maxDelay) {
        this.maxDelay = maxDelay;
    }

    ResultData() {
        maxDelay = 0F;
        countOfCancelledFlights = 0;
        countOfFlights = 0;
    }

    public void add(FlightData flightData) {
        if (flightData.getDelay() > maxDelay) {
            maxDelay = flightData.getDelay();
        }
        countOfFlights++;
        if (flightData.getDelay() < 0F || flightData.getCancelled() == 1F) {
            countOfCancelledFlights++;
        }
    }

    public void addAll(ResultData resultData) {
        if(resultData.getMaxDelay() > maxDelay) {
            maxDelay = resultData.getMaxDelay();
        }
        countOfFlights += resultData.getCountOfFlights();
        countOfCancelledFlights += resultData.getCountOfCancelledFlights();
    }
}
