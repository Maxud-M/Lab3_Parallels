

public class FlightReader {
    static public FlightData parseFlightData(String inputData) {
        float delay = Float.parseFloat(inputData.split(",")[18]);
        float cancelled = Float.parseFloat(inputData.split(",")[19]);
        return new FlightData(delay, cancelled);
    }
}
