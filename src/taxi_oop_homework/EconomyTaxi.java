package taxi_oop_homework;

import taxi_exceptions.DiscountException;
import taxi_interfaces.Taxi;

/**
 * Created by Marina on 15.05.2017.
 */

public class EconomyTaxi extends Car implements Taxi {
    float discount;

    /**
     * Constructor of EconomyTaxi, which is calling a constructor of superclass Car,
     * then it is filling other fields, actual for EconomyTaxi.
     * @param name
     * @param price
     * @param fuelConsumption
     * @param capacity
     * @param tripPrice
     * @param discount
     * @throws DiscountException
     */
    public EconomyTaxi (String name,
                 int price,
                 int fuelConsumption,
                 int capacity,
                 int tripPrice,
                 float discount) throws DiscountException
    {
        super(name,price,fuelConsumption,capacity,tripPrice);
        if (discount < 0 || discount >= 100)
        {
            throw new DiscountException("Недопустимая величина скидки. Ожидается значение от 0 до 99", discount);
        }
        this.discount = discount;
    }
    /**
     * Implementation of method describeTaxi() of interface Taxi
     */
    public void describeTaxi()
    {
        System.out.println("======= general options =======");
        System.out.println("Название = " + this.getName());
        System.out.println("Стоимость машины = " + this.getPrice());
        System.out.println("Расход топлива = " + this.getFuelConsumption());
        System.out.println("Вместимость(чел.) = " + this.getCapacity());
        System.out.println("Стандартная цена поездки = " + this.getTripPrice());
        System.out.println("======= special options =======");
        System.out.println("Специальная скидка (от 3 человек) = " + this.discount);
        for (Door door:this.doors) {
            if (door.darkWindow)
                System.out.println("Дверь №"+(int)(this.doors.indexOf(door)+1)+": тонированные стекла");
            if (door.forDisabled)
                System.out.println("Дверь №"+(int)(this.doors.indexOf(door)+1)+": для инвалидов");
        }
    }
    /**
     * Implementation of method calculateTrip(int passengers) of interface Taxi
     */
    public int calculateTrip(int passengers)
    {
        int sum = this.getTripPrice()*passengers;
        for (Door door:this.doors) {
            if (door.forDisabled)
                sum+= 10;
        }
        if (passengers>=3)
            sum = (int)(sum*(1-discount*0.01));
        return sum;
    }

}
