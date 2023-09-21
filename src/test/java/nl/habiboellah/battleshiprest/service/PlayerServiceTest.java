package nl.habiboellah.battleshiprest.service;

import nl.habiboellah.battleshiprest.exceptionhandling.PlayerNotFoundException;
import nl.habiboellah.battleshiprest.model.entity.Player;
import nl.habiboellah.battleshiprest.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository repository;

    @Test
    public void testGetAllPlayers() {
        Player playerOne = new Player("Bob");
        Player playerTwo = new Player("John");
        List<Player> players = List.of(playerOne, playerTwo);
        when(repository.findAll()).thenReturn(players);
        assertEquals(players, playerService.getAllPlayers());
    }

    @Test
    public void testGetPlayerById() {
        Long id = 4L;
        Player player = new Player("John");
        player.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(player));
        assertEquals(player, playerService.getPlayerById(id));

        Long nonExistentId = 5L;
        when(repository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(PlayerNotFoundException.class, ()-> playerService.getPlayerById(nonExistentId));
    }

    @Test
    public void testAddPlayer() {
        Long id = 4L;
        Player playerOne = new Player("Jones");
        playerOne.setId(id);
        when(repository.save(playerOne)).thenReturn(playerOne);
        assertEquals(playerService.addPlayer(playerOne), playerOne);

        Long anotherId = 5L;
        Player playerTwo = new Player("Jack");
        playerTwo.setId(anotherId);
        when(repository.save(playerTwo)).thenReturn(playerTwo);
        Player actualPlayer = playerService.addPlayer(playerTwo);
        assertEquals(playerTwo, actualPlayer);
    }
}
