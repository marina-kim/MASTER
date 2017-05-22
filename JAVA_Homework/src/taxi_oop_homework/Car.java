package taxi_oop_homework;

import taxi_exceptions.NotExistedDoorException;

import java.util.ArrayList;

/**
 * Created by Marina on 15.05.2017.
 */

public class Car {
    String name;
    int price;
    int fuelConsumption;
    int capacity;
    int tripPrice;
    int height;
    ArrayList<Door> doors = new ArrayList<Door>();

    /**
     * class Door
     * It is the inner class of the class Car.
     * It allows to operate with doors details and options.
     */
    class Door
    {
        boolean darkWindow;
        boolean forDisabled;

        Door()
        {
            this.darkWindow = false;
            this.forDisabled = false;
        }
        Door(boolean darkWindow, boolean forDisabled)
        {
            this.darkWindow = darkWindow;
            this.forDisabled = forDisabled;
        }
        public double getDoorHeight()
        {
            return height*0.80;
        }
    }

    /**
     * Car constructor, which fills all the fields of object
     * @param name
     * @param price
     * @param fuelConsumption
     * @param capacity
     * @param tripPrice
     */
    public Car(String name, int price, int fuelConsumption, int capacity, int tripPrice)
    {
        doors.add(new Door());
        doors.add(new Door());
        doors.add(new Door());
        doors.add(new Door());

        this.name = name;
        this.price = price;
        this.fuelConsumption = fuelConsumption;
        this.capacity = capacity;
        this.tripPrice = tripPrice;
    }

    /**
     * void changeDoor - method, which allows to change options of car doors.
     * @param index
     * @param darkWindow
     * @param forDisabled
     * @throws NotExistedDoorException
     */
    public void changeDoor(int index, boolean darkWindow, boolean forDisabled)
            throws NotExistedDoorException
    {
        if (index < 0 || index >= doors.size())
        {
            throw new NotExistedDoorException("Недопустимый номер двери. Ожидается значение от 0 до 3. Введено: "+ index);
        }
        this.doors.add(index,new Door(darkWindow,forDisabled));
    }
    public String getName()
    {
        return name;
    };
    public int getPrice()
    {
        return price;
    };
    public int getFuelConsumption()
    {
        return fuelConsumption;
    };
    int getCapacity()
    {
        return capacity;
    };
    public int getTripPrice()
    {
        return tripPrice;
    };

}
