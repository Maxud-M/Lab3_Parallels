

public class FlightReader {

    public static final int FLIGHT_DELAY_POS = 17;
    public static final int FLIGHT_CANCELLED_POS = 19;
    public static final String DELIMETER

    static public FlightData parseFlightData(String inputData) {
        String[] data = inputData.split(",");
        float delay = (!data[FLIGHT_DELAY_POS].equals(""))? Float.parseFloat(data[FLIGHT_DELAY_POS]) : 0F;
        float cancelled = (!data[FLIGHT_CANCELLED_POS].equals(""))? Float.parseFloat(data[FLIGHT_CANCELLED_POS]) : 0F;
        return new FlightData(delay, cancelled);
    }
}
