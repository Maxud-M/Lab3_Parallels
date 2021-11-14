

public class FlightData {

    private float delay;
    private int cancelled;


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