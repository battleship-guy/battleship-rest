package nl.habiboellah.battleshiprest.exceptionhandling;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String identifier) {
        super("Player " + identifier + " already exists");
    }
}
