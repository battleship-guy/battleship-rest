package nl.habiboellah.battleshiprest.controller;

import jakarta.servlet.http.HttpServletResponse;
import nl.habiboellah.battleshiprest.exceptionhandling.PlayerNotFoundException;
import nl.habiboellah.battleshiprest.model.dto.PlayerDto;
import nl.habiboellah.battleshiprest.model.dto.assembler.PlayerDtoAssembler;
import nl.habiboellah.battleshiprest.model.entity.Player;
import nl.habiboellah.battleshiprest.model.entity.mapper.PlayerMapper;
import nl.habiboellah.battleshiprest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService service;

    @Autowired
    private PlayerDtoAssembler assembler;

    @Autowired
    private PlayerMapper mapper;

    @GetMapping("/players")
    public CollectionModel<PlayerDto> getAllPlayers() {
        return assembler.toCollectionModel(service.getAllPlayers());
    }

    @GetMapping("/players/{id}")
    public PlayerDto getSinglePlayer(@PathVariable Long id) {
        return assembler.toModel(service.getPlayerById(id));
    }

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDto postPlayer(@RequestBody PlayerDto playerDto) {
        return assembler.toModel(service.addPlayer(mapper.toEntity(playerDto)));
    }

    @PutMapping("/players/{id}")
    public PlayerDto putPlayer(@RequestBody PlayerDto playerDto, @PathVariable Long id, HttpServletResponse response)
    {
        Player player = mapper.toEntity(playerDto);
        try {
            player = service.updatePlayer(player, id);
            response.setStatus(HttpStatus.OK.value());
        } catch (PlayerNotFoundException exception) {
            player = service.addPlayer(player, id);
            response.setStatus(HttpStatus.CREATED.value());
        }
        return assembler.toModel(player);
    }
}
