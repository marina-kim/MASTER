package taxi_exceptions;

/**
 * Created by Marina on 16.05.2017.
 */
public class NotExistedDoorException extends RuntimeException {
    public NotExistedDoorException(String message){
        super(message);
    }
}
