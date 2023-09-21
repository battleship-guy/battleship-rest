package nl.habiboellah.battleshiprest.service;

import nl.habiboellah.battleshiprest.exceptionhandling.PlayerAlreadyExistsException;
import nl.habiboellah.battleshiprest.exceptionhandling.PlayerNotFoundException;
import nl.habiboellah.battleshiprest.model.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PlayerServiceIT {
    @Autowired
    PlayerService playerService;
    @Test
    public void testAddUpdateDeletePlayers() {
        List<Player> allPlayers = playerService.getAllPlayers();
        Player playerOne = new Player("Bob");
        Player playerTwo = new Player("John");
        assertEquals(playerOne, playerService.addPlayer(playerOne));
        assertThrows(PlayerNotFoundException.class,()->playerService.updatePlayer(playerTwo, 2L));
        assertEquals(playerTwo, playerService.addPlayer(playerTwo));
        assertThrows(PlayerAlreadyExistsException.class, () -> playerService.addPlayer(playerTwo));
        playerTwo.setName("Bert");
        assertEquals(playerTwo, playerService.updatePlayer(playerTwo, 2L));

        assertEquals(List.of(playerOne, playerTwo), playerService.getAllPlayers());
        assertEquals(playerOne, playerService.getPlayerById(1L));
        assertEquals(playerTwo, playerService.getPlayerById(2L));
        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById(3L));

        assertThrows(PlayerNotFoundException.class,()->playerService.deletePlayer(3L));
        assertDoesNotThrow(() -> playerService.deletePlayer(2L));
        assertEquals(List.of(playerOne), playerService.getAllPlayers());
        assertThrows(PlayerNotFoundException.class,()->playerService.deletePlayer(2L));
    }

}
