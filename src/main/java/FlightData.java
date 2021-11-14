

public class FlightData {

    private float delay;
    private int cancelled;
    private float percentCancelled;
    

    public float getDelay() {
        return delay;
    }

    public int getCancelled() {
        return cancelled;
    }

    FlightData(float delay, int cancelled) {
        this.delay = delay;
        this.cancelled = cancelled;
    }
}
