package cake.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CakeNotFoundException extends Exception {

    public CakeNotFoundException(String cakeSlug) {
        super("Unable to find cake with slug " + cakeSlug);
    }
}
