package cake.api.exception;

import cake.api.model.Cake;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidCakeException extends Exception {

    public InvalidCakeException(Cake cake) {
        super("Invalid cake: " + cake.toString());
    }
}
