package nl.habiboellah.battleshiprest.repository;

import nl.habiboellah.battleshiprest.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    public List<Player> findByName(String name);
}
