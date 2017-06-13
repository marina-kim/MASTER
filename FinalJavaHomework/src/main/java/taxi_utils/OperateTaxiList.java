package taxi_utils;

import taxi_interfaces.Taxi;
import taxi_oop_homework.Car;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Marina on 16.05.2017.
 */

/**
 * Class OperateTaxiList consists of applied methods for work with ArrayList<Taxi>.
 */
public class OperateTaxiList {
    /**
     * Method calculates and returns the sum of cars prices in the list
     * @param list
     * @return
     */
    public static int sumPrice (ArrayList<Taxi> list)
    {
        int sum = 0;
        for (Taxi taxi:list)
        {
            if (taxi.getClass().getName().equals("taxi_oop_homework.PremiumTaxi")
                    ||taxi.getClass().getName().equals("taxi_oop_homework.EconomyTaxi"))
            {
                Car tmp = (Car) taxi;
                sum = sum + tmp.getPrice();
            }
        }
        return sum;
    }
    /**
     * Method sorts cars in the list on field fuelConsumption
     * @param list
     * @return
     */
    public static ArrayList<Taxi> sortFuel (ArrayList<Taxi> list)
    {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for (Taxi taxi:list)
        {
            if (taxi.getClass().getName().equals("taxi_oop_homework.PremiumTaxi")
                    ||taxi.getClass().getName().equals("taxi_oop_homework.EconomyTaxi"))
            {
                Car tmp = (Car) taxi;
                result.add(taxi);
            }
        }
        Collections.sort(result,new FuelComparator());

        return result;
    }
    /**
     * Method, which filters list of cars on field name
     * @param list
     * @param name
     * @return
     */
    public static ArrayList<Taxi> filterName(ArrayList<Taxi> list,String name)
    {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for (Taxi taxi:list)
        {
            if (taxi.getClass().getName().equals("taxi_oop_homework.PremiumTaxi")||taxi.getClass().getName().equals("taxi_oop_homework.EconomyTaxi"))
            {
                Car tmp = (Car) taxi;
                if (tmp.getName().equals(name))
                    result.add(taxi);
            }
        }
        return result;
    }
    /**
     * Method, which filters list of cars on field tripPrice
     * @param list
     * @param min
     * @param max
     * @return
     */
    public static ArrayList<Taxi> filterTripPrice(ArrayList<Taxi> list,int min, int max)
    {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for (Taxi taxi:list)
        {
            if (taxi.getClass().getName().equals("taxi_oop_homework.PremiumTaxi")||taxi.getClass().getName().equals("taxi_oop_homework.EconomyTaxi"))
            {
                Car tmp = (Car) taxi;
                if (tmp.getTripPrice()>= min && tmp.getTripPrice()<=max)
                    result.add(taxi);
            }
        }
        return result;
    }
}
