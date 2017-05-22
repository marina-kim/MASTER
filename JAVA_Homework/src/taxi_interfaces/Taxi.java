package taxi_interfaces;

/**
 * Created by Marina on 14.05.2017.
 */
public interface Taxi {
    void describeTaxi();
    int calculateTrip(int passengers) throws Exception;
}
