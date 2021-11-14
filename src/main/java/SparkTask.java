import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class SparkTask {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> distFile = sc.textFile(args[0]);
        JavaPairRDD<Tuple2<String, String>, FlightData> pairRDD = distFile.mapToPair(
                s -> new Tuple2<Tuple2<String, String>, FlightData>(
                        new Tuple2<String, String>(s.split(",")[11], s.split(",")[14]),
                        FlightReader.parseFlightData(s)
                )
        );
        int count_of_flights;
        int count_of_delays;
        JavaPairRDD<Tuple2<String, String>, AirportPairData> resPairRDD = pairRDD.reduceByKey((value1, value2) -> {
            float max_delay = (value1.getDelay() > value2.getDelay())?value1.getDelay():value2.getDelay();
            float percent =

        })





    }
}
