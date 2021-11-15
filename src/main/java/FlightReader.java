

public class FlightReader {

    public static final int FLIGHT_DELAY_POS = 17;
    public static final int FLIGHT_CANCELLED_POS = 19;

    static public FlightData parseFlightData(String inputData) {
        String[] data = inputData.split(Constants.DELIMITER);
        float delay = (!data[FLIGHT_DELAY_POS].equals(Constants.EMPTY_STRING))?
                Float.parseFloat(data[FLIGHT_DELAY_POS]) : Constants.ZERO_FLOAT;
        float cancelled = (!data[FLIGHT_CANCELLED_POS].equals(Constants.EMPTY_STRING))?
                Float.parseFloat(data[FLIGHT_CANCELLED_POS]) : Constants.ZERO_FLOAT;
        return new FlightData(delay, cancelled);
    }
}
