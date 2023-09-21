package nl.habiboellah.battleshiprest.repository;

import nl.habiboellah.battleshiprest.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
