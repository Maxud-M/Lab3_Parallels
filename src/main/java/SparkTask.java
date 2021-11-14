import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Map;

public class SparkTask {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flights = sc.textFile(args[0]);
        JavaRDD<String> airports = sc.textFile(args[1]);
        JavaPairRDD<Tuple2<String, String>, FlightData> pairFlightsRDD = flights.mapToPair(
                s -> new Tuple2<Tuple2<String, String>, FlightData>(
                        new Tuple2<String, String>(s.split(",")[11], s.split(",")[14]),
                        FlightReader.parseFlightData(s)
                )
        );
        JavaPairRDD<String, Integer> pairAirportsRDD = airports.mapToPair(
                s -> new Tuple2<String, Integer>(s.split(",")[0], Integer.parseInt(s.split(",")[1])
                )
        );
        Map<String, Integer> airportsMap = pairAirportsRDD.collectAsMap();
        final 


    }
}
