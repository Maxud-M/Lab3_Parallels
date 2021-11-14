

public class FlightData {

    private float maxDelay;
    private int cancelled;
    private float percentCancelled;
    private boolean 


    public float getMaxDelay() {
        return maxDelay;
    }

    public int getCancelled() {
        return cancelled;
    }

    FlightData(float delay, int cancelled) {
        this.maxDelay = delay;
        this.cancelled = cancelled;
    }
}
