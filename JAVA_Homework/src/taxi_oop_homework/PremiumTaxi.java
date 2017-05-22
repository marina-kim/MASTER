package taxi_oop_homework;

import taxi_annotations.Checked;
import taxi_annotations.ClassPreamble;
import taxi_interfaces.Taxi;

/**
 * Created by Marina on 15.05.2017.
 */

@ClassPreamble(
        author = "Kim Marina",
        date = "15/05/2017"
)
public class PremiumTaxi extends Car implements Taxi {
    boolean smoking;
    boolean conditioner;
    boolean radio;

    /**Constructor of PremiumTaxi, which is calling a constructor of superclass Car,
     * then it is filling other fields, actual for PremiumTaxi.
     * @param name
     * @param price
     * @param fuelConsumption
     * @param capacity
     * @param tripPrice
     * @param smoking
     * @param conditioner
     * @param radio
     */
    public PremiumTaxi (String name,
                 int price,
                 int fuelConsumption,
                 int capacity,
                 int tripPrice,
                 boolean smoking, boolean conditioner, boolean radio)
    {
        super(name,price,fuelConsumption,capacity,tripPrice);
        this.smoking = smoking;
        this.conditioner = conditioner;
        this.radio = radio;

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
        for (Door door:this.doors) {
            if (door.darkWindow)
                System.out.println("Дверь №"+(int)(this.doors.indexOf(door)+1)+": тонированные стекла");
            if (door.forDisabled)
                System.out.println("Дверь №"+(int)(this.doors.indexOf(door)+1)+": для инвалидов");
        }
        if (this.smoking)
            System.out.println("Курение разрешено");
        if (this.conditioner)
            System.out.println("Есть кондиционер");
        if (this.radio)
            System.out.println("Есть радио");
        if (!this.smoking && !this.conditioner && !this.radio)
            System.out.println("(Дополнительных услуг нет...)");
    }

    /**
     * Implementation of method calculateTrip(int passengers) of interface Taxi
     */
    @Checked(
            byWho = "Marina_K",
            when = "15/05/17")
    public int calculateTrip(int passengers)
    {
        int sum = this.getTripPrice()*passengers;
        if (this.smoking) sum += 50;
        if (this.conditioner) sum += 50;
        if (this.radio) sum+= 10;
        for (Door door:this.doors) {
            if (door.forDisabled)
                sum+= 10;
        }
        return sum;
    }

}
