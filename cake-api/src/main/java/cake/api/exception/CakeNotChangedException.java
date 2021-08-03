package cake.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CakeNotChangedException extends Exception {

    public CakeNotChangedException(String cakeSlug) {
        super("Cake not modified: " + cakeSlug);
    }
}
