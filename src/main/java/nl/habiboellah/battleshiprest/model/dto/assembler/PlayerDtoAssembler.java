package nl.habiboellah.battleshiprest.model.dto.assembler;

import nl.habiboellah.battleshiprest.controller.PlayerController;
import nl.habiboellah.battleshiprest.model.dto.PlayerDto;
import nl.habiboellah.battleshiprest.model.entity.Player;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlayerDtoAssembler implements RepresentationModelAssembler<Player, PlayerDto> {
    @Override
    @NonNull
    public PlayerDto toModel(Player player) {
        return new PlayerDto(player.getId(), player.getName())
                .add(linkTo(methodOn(PlayerController.class).getSinglePlayer(player.getId())).withSelfRel())
                .add(linkTo(methodOn(PlayerController.class).getAllPlayers()).withRel("players"))
                ;
    }

    @Override
    @NonNull
    public CollectionModel<PlayerDto> toCollectionModel(@NonNull Iterable<? extends Player> players) {
        return RepresentationModelAssembler.super.toCollectionModel(players)
                .add(linkTo(methodOn(PlayerController.class).getAllPlayers()).withRel("players"));
    }
}
