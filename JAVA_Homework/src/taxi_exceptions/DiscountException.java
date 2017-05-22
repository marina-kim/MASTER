package taxi_exceptions;

/**
 * Created by Marina on 16.05.2017.
 */
public class DiscountException extends Exception{
    private float percents;
    public float getPercents()
    {
        return percents;
    }
    public DiscountException(){
        super();
    }
    public DiscountException(String message, float perc){
        super(message);
        percents = perc;
    }
    public DiscountException(String message, Throwable cause, float perc)
    {
        super(message,cause);
        percents = perc;
    }
    public DiscountException(Throwable cause, float perc)
    {
        super(cause);
        percents = perc;
    }
}
