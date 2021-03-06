import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class SparkTask {

    public static final int ORIGIN_AIRPORT_ID_POS = 11;
    public static final int DEST_AIRPORT_ID_POS = 14;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flights = sc.textFile("/user/evistix28/664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airports = sc.textFile("/user/evistix28/L_AIRPORT_ID.csv");
        JavaPairRDD<Tuple2<Integer, Integer>, FlightData> pairFlightsRDD = flights.mapToPair(
                s -> new Tuple2<>(
                        new Tuple2<>(
                                Integer.parseInt(s.split(Constants.DELIMITER)[ORIGIN_AIRPORT_ID_POS]),
                                Integer.parseInt(s.split(Constants.DELIMITER)[DEST_AIRPORT_ID_POS])),
                        FlightReader.parseFlightData(s)
                )
        );
        JavaPairRDD<Tuple2<Integer, Integer>, ResultData> resultData = pairFlightsRDD.aggregateByKey(
                new ResultData(),
                (a, b) -> {
                    a.add(b);
                    return a;
                    },
                (a, b) -> {
                    a.addAll(b);
                    return a;
                });
        JavaPairRDD<Integer, String> pairAirportsRDD = airports.mapToPair(
                s -> new Tuple2<>(
                        Integer.parseInt(s.split(Constants.DELIMITER, 2)[0].replaceAll(Constants.QUOTE, Constants.EMPTY_STRING)),
                        s.split(Constants.DELIMITER, 2)[1].replaceAll(Constants.QUOTE, Constants.EMPTY_STRING))
        );
        Map<Integer, String> airportsMap = pairAirportsRDD.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportsMap);
        JavaRDD<String> res = resultData.map(T -> {
            Map<Integer, String> airportsData = airportsBroadcasted.value();
            String result = airportsData.get(T._1._1) + " -> " + airportsData.get(T._1._2) + ":" + Constants.NEW_LINE_CHARACTER;
            result += "maxDelay: " + T._2.getMaxDelay() + Constants.NEW_LINE_CHARACTER;
            result += "percentCancelled: " + T._2.calculatePercentCancelled() + Constants.NEW_LINE_CHARACTER;
            return result;
        });
        res.saveAsTextFile("/user/evistix28/lab3_result");
    }
}
