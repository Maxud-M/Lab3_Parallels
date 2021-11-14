

public class FlightData {

    private float maxDelay;
    private int cancelled;
    private float percentCancelled;
    private boolean considered;
    public int countOfFlights;
    public int countCancelled;

    public void setConsidered() {
        considered = true;
    }

    public boolean areConsidered() {
        return considered;
    }


    public float getMaxDelay() {
        return maxDelay;
    }

    public int getCancelled() {
        return cancelled;
    }

    FlightData(float delay, int cancelled) {
        this.maxDelay = delay;
        this.cancelled = cancelled;
        if(maxDelay == 0F || cancelled == 1) {
            countCancelled = 1;
        }
        considered = false;
        countOfFlights = 1;
    }
}
