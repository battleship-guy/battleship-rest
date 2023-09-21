package nl.habiboellah.battleshiprest.exceptionhandling;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String identifier) {
        super("Could not find player " + identifier);
    }
}
