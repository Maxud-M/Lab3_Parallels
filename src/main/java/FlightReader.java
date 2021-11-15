

public class FlightReader {

    public static final int FLIGHT_DELAY_POS = 17;
    public static final int FLIGHT_CANCELLED_POS = 19;

    static public FlightData parseFlightData(String inputData) {
        String[] data = inputData.split(",");
        float delay = (!data[18].equals(""))? Float.parseFloat(data[17]) : 0F;
        float cancelled = (!data[19].equals(""))? Float.parseFloat(data[19]) : 0F;
        return new FlightData(delay, cancelled);
    }
}
