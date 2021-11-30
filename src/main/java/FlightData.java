

public class FlightData {

    private float delay;
    private float cancelled;


    public float getDelay() {
        return delay;
    }

    public float getCancelled() {
        return cancelled;
    }

    FlightData(float delay, float cancelled) {
        this.delay = delay;
        this.cancelled = cancelled;
    }
}