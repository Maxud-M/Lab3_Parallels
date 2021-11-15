import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class SparkTask {

    public static final int ORIGIN
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flights = sc.textFile("/user/evistix28/664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airports = sc.textFile("/user/evistix28/L_AIRPORT_ID.csv");
        JavaPairRDD<Tuple2<Integer, Integer>, FlightData> pairFlightsRDD = flights.mapToPair(
                s -> new Tuple2<>(
                        new Tuple2<>(
                                Integer.parseInt(s.split(",")[11]),
                                Integer.parseInt(s.split(",")[14])),
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
                        Integer.parseInt(s.split(",", 2)[0].replaceAll("\"", "")),
                        s.split(",", 2)[1].replaceAll("\"", ""))
        );
        Map<Integer, String> airportsMap = pairAirportsRDD.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportsMap);
        JavaRDD<String> res = resultData.map(T -> {
            Map<Integer, String> airportsData = airportsBroadcasted.value();
            String result = airportsData.get(T._1._1) + " -> " + airportsData.get(T._1._2) + ":\n";
            result += "maxDelay: " + T._2.getMaxDelay() + "\n";
            result += "percentCancelled: " + T._2.calculatePercentCancelled() + "\n";
            return result;
        });
        res.saveAsTextFile("/user/evistix28/lab3_result");
    }
}
