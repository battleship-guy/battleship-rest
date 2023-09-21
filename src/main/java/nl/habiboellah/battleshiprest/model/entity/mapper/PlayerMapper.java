package nl.habiboellah.battleshiprest.model.entity.mapper;

import nl.habiboellah.battleshiprest.model.dto.PlayerDto;
import nl.habiboellah.battleshiprest.model.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public Player toEntity(PlayerDto playerDto) {
        return new Player(playerDto.getName());
    }
}
