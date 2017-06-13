package taxi_utils;

import taxi_interfaces.Taxi;
import taxi_oop_homework.Car;

import java.util.Comparator;


/**
 * Created by Marina on 15.05.2017.
 */

/**
 * FuelComparator allows to compare Taxi objects on fuelConsumption field.
 * WARNING! Objects for comparing must be objects of Car class,
 * or of classes, which extend Car
 */
public class FuelComparator implements Comparator<Taxi> {
        @Override
        public int compare(Taxi o1, Taxi o2) {
            if (((Car)o1).getFuelConsumption()>((Car)o2).getFuelConsumption()) return 1;
            else if (((Car)o1).getFuelConsumption()<((Car)o2).getFuelConsumption())return -1;
            return 0;
        }
}

