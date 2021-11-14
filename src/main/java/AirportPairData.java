public class AirportPairData {
    private float maxDelay;
    private float percentCancelled;

    public float getMaxDelay() {
        return maxDelay;
    }

    public float getPercentCancelled() {
        return percentCancelled;
    }

    AirportPairData(float maxDelay, float percentCancelled) {
        this.maxDelay = maxDelay;
        this.percentCancelled = percentCancelled;
    }
}
