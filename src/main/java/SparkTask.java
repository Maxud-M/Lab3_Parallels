import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class SparkTask {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flights = sc.textFile(args[0]);
        JavaRDD<String> airports = sc.textFile(args[1]);
        JavaPairRDD<Tuple2<Integer, Integer>, FlightData> pairFlightsRDD = flights.mapToPair(
                s -> new Tuple2<Tuple2<Integer, Integer>, FlightData>(
                        new Tuple2<Integer, Integer>(
                                Integer.parseInt(s.split(",")[11]),
                                Integer.parseInt(s.split(",")[14])),
                        FlightReader.parseFlightData(s)
                )
        );
        pairFlightsRDD
        JavaPairRDD<Integer, String> pairAirportsRDD = airports.mapToPair(
                s -> new Tuple2<Integer, String>(Integer.parseInt(s.split(",")[1]), s.split(",")[0])
        );
        Map<Integer, String> airportsMap = pairAirportsRDD.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportsMap);
        JavaRDD<String> res = pairFlightsRDD.map(T -> {
            Map<Integer, String> airportsData = airportsBroadcasted.value();
            String result = airportsData.get(T._1._1) + " -> " + airportsData.get(T._1._2) + ":\n";
            result += "maxDelay: \n";
            result += "percentCancelled: \n";
            return result;
        });
        res.saveAsTextFile("hdfs://user/evistix28");
    }
}
