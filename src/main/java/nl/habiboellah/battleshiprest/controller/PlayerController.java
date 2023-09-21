package nl.habiboellah.battleshiprest.controller;

import nl.habiboellah.battleshiprest.model.dto.PlayerDto;
import nl.habiboellah.battleshiprest.model.dto.assembler.PlayerDtoAssembler;
import nl.habiboellah.battleshiprest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService service;

    @Autowired
    private PlayerDtoAssembler assembler;

    @GetMapping("/players")
    public CollectionModel<PlayerDto> getAllPlayers() {
        return assembler.toCollectionModel(service.getAllPlayers());
    }

    @GetMapping("/players/{id}")
    public PlayerDto getSinglePlayer(@PathVariable Long id) {
        return assembler.toModel(service.getPlayerById(id));
    }
}
