package taxi_annotations;

import java.lang.annotation.*;

/**
 * Created by Marina on 16.05.2017.
 */
@Documented
@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Checked {
    String byWho();
    String when();
}
