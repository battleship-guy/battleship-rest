package nl.habiboellah.battleshiprest.service;

import nl.habiboellah.battleshiprest.exceptionhandling.PlayerNotFoundException;
import nl.habiboellah.battleshiprest.model.entity.Player;
import nl.habiboellah.battleshiprest.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    public Player getPlayerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id.toString()));
    }

    public Player addPlayer(Player player) {
        return repository.save(player);
    }
}
