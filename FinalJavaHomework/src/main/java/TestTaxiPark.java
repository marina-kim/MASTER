import taxi_annotations.Checked;
import taxi_annotations.ClassPreamble;
import taxi_enums.LargeAuto;
import taxi_exceptions.DiscountException;
import taxi_exceptions.NotExistedDoorException;
import taxi_interfaces.Taxi;
import taxi_oop_homework.Car;
import taxi_oop_homework.EconomyTaxi;
import taxi_oop_homework.PremiumTaxi;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static taxi_utils.OperateTaxiList.*;

/**
 * Created by Marina on 15.05.2017.
 */
public class TestTaxiPark {

    /** main()
     * Method demonstrates examples of using Car, EconomyTaxi, PremiumTaxi classes.
     * It uses checked and unchecked exceptions in these classes.
     * It creates list of taxis and operates with it.
     * It shows variants of using annotations Checked and ClassPreamble.
     * It demonstrates the difference between methods computeDeliveryPrice() for different values
     * of enum LargeAuto.
     * @param args
     * @throws NoSuchMethodException
     * @throws DiscountException
     */
    public static void main(String[] args) throws NoSuchMethodException, DiscountException {
        ArrayList<Taxi> taxiPark = new ArrayList<Taxi>();
        ArrayList<Taxi> byName = new ArrayList<Taxi>();
        ArrayList<Taxi> byTripPrice = new ArrayList<Taxi>();
        ArrayList<Taxi> sortedByFuel = new ArrayList<Taxi>();

        PremiumTaxi special = new PremiumTaxi("Mersedes",2000000,100,4,300,true,true,true);
        special.changeDoor(0,true,true);
        special.changeDoor(3,false,true);
            taxiPark.add(special);
            taxiPark.add(new PremiumTaxi("Mersedes",2000000,100,4,300,true,true,true));
            taxiPark.add(new EconomyTaxi("Lada",100000,80,4,100,10));
            taxiPark.add(new PremiumTaxi("Audi",1200000,110,4,350,true,true,true));
            taxiPark.add(new PremiumTaxi("Volvo",2100000,130,7,500,true,true,true));
            taxiPark.add(new EconomyTaxi("GAZel",200000,100,6,300,5));
            taxiPark.add(new EconomyTaxi("Renault",250000,90,4,200,10));

        for (Taxi taxicar:
             taxiPark){
            System.out.println("\n№"+(int)(taxiPark.indexOf(taxicar)+1)+")");
            taxicar.describeTaxi();
        }
        System.out.println("\n+++++++++\nПРИМЕР 1: \n+++++++++");
        System.out.println("Стоимость таксопарка = "+ sumPrice(taxiPark));

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++" +
                            "\nПРИМЕР 2: Фильтрация таксопарка по марке Audi"+
                            "\n+++++++++++++++++++++++++++++++++++++++++++++");
        byName = filterName(taxiPark,"Audi");
        for (Taxi taxicar:
                byName){
            System.out.println("\nFILTERED BY NAME №"+(int)(taxiPark.indexOf(taxicar)+1)+")");
            taxicar.describeTaxi();
        }

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                            "\nПРИМЕР 3: Фильтрация таксопарка по стандартной стоимости поездки от 100 до 200"+
                            "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
        byTripPrice = filterTripPrice(taxiPark,100,200);
        for (Taxi taxicar:
                byTripPrice){
            System.out.println("\nFILTERED BY TRIP PRICE №"+(int)(taxiPark.indexOf(taxicar)+1)+")");
            taxicar.describeTaxi();
        }

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++"+
                            "\nПРИМЕР 4: Сортировка таксопарка по расходу топлива"+
                            "\n++++++++++++++++++++++++++++++++++++++++++++++++++");
        sortedByFuel = sortFuel(taxiPark);
        for (Taxi taxicar:
                sortedByFuel){
            System.out.println("\nSORTED BY FUEL №"+(int)(taxiPark.indexOf(taxicar)+1)+")");
            taxicar.describeTaxi();
        }

        System.out.println("\n+++++++++++++++++++++++++++++++++"+
                "\nПРИМЕР 5: Использование аннотаций"+
                "\n+++++++++++++++++++++++++++++++++");
        System.out.println("1) Класс PremiumTaxi помечен аннотацией @ClassPreamble");
        Class<?> c = special.getClass();
        System.out.println(c);
        ClassPreamble anno = c.getAnnotation(ClassPreamble.class);
        System.out.println(anno.author());
        System.out.println(anno.date());
        System.out.println("2) Метод PremiumTaxi.calculateTrip помечен аннотацией @Checked");
        System.out.println("Методы класса PremiumTaxi: ");
        Method[] method = c.getDeclaredMethods();
        for(Method md: method){
            System.out.print("Метод "+md.getName());
            if(md.isAnnotationPresent(Checked.class))
                System.out.println(" проверен "+
                        md.getAnnotation(Checked.class).byWho()+ " " +
                        md.getAnnotation(Checked.class).when());
            else
                System.out.println(" не проверен ");
        }

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+
                "\nПРИМЕР 6: Демонстрация ENUM с анонимным классом для TRUCK"+
                "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        LargeAuto microbus, pickup, truck;
        microbus = LargeAuto.MICROBUS;
        microbus.setParams(10,400);
        microbus.setPrices(100,10);
        System.out.println("computeDeliveryPrice MICROBUS = "+microbus.computeDeliveryPrice());
        pickup = LargeAuto.PICKUP;
        pickup.setParams(10,400);
        pickup.setPrices(100,10);
        System.out.println("computeDeliveryPrice PICKUP = "+pickup.computeDeliveryPrice());
        truck = LargeAuto.TRUCK;
        truck.setParams(10,400);
        truck.setPrices(100,10);
        System.out.println("computeDeliveryPrice TRUCK = "+truck.computeDeliveryPrice());


        System.out.println("\n+++++++++++++++++++++++++++++++++"+
                "\nПРИМЕР 7: Работа с исключениями"+
                "\n+++++++++++++++++++++++++++++++++");
        System.out.println("1) Непроверяемое исключение NoExistedDoorException");
        try{
            Car testTaxi2 = new EconomyTaxi("Ford",200000,90,4,200,10);
            testTaxi2.changeDoor(4,false,false);
        }
        catch (NotExistedDoorException nedex){
            System.out.println(nedex.getMessage());
            nedex.printStackTrace();
        }
        System.out.println("2) Проверяемое исключение DiscountException");
        try{
            Car testTaxi = new EconomyTaxi("Volga",80000,80,4,100,100);
        }
        catch (DiscountException dex){
            System.out.println("Попытка ввести значение = " +dex.getPercents());
            dex.printStackTrace();
        }

    }
}
