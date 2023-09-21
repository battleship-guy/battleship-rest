package nl.habiboellah.battleshiprest.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlayerAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(PlayerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String playerAlreadyExistsHandler(PlayerAlreadyExistsException exception) {
        return exception.getMessage();
    }
}
