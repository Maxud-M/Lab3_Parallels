

public class FlightReader {
    static public FlightData parseFlightData(String inputData) {
        String[] data = inputData.split(",");
        float delay = (!data[18].equals(""))? Float.parseFloat(data[18]) : 0F;
        float cancelled = (!data[19].equals(""))? Float.parseFloat(data[19]) : 0F;
        return new FlightData(delay, cancelled);
    }
}
