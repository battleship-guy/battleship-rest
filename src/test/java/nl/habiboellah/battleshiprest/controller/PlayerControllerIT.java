package nl.habiboellah.battleshiprest.controller;

import nl.habiboellah.battleshiprest.model.dto.PlayerDto;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerControllerIT extends ControllerIT {
    @Test
    public void testAddGetAndDeletePlayers() {
        String playerOneName = "Sam";
        Long playerOneId = 1L;
        subTestAddPlayer(playerOneName, playerOneId);

        subTestGetAllPlayers(1);

        String playerTwoName = "Bob";
        Long playerTwoId = 2L;
        subTestAddPlayer(playerTwoName, playerTwoId);

        subTestGetAllPlayers(2);

        subTestGetPlayer(playerOneId, playerOneName);
        subTestGetPlayer(playerTwoId, playerTwoName);

        Long nonExistentId = 99L;
        subTestGetNonexistentPlayer(nonExistentId);

        subTestDeletePlayer(playerTwoId);
        subTestGetNonexistentPlayer(playerTwoId);
        subTestGetAllPlayers(1);
    }

    private void subTestDeletePlayer(Long playerId) {
        this.restTemplate.delete(getUrlWithId(playerId));
    }

    private void subTestGetAllPlayers(int expectedNumberOfPlayers) {
        var response = this.restTemplate.getForEntity(getUrl(), CollectionModel.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var collectionModel = Objects.requireNonNull(response.getBody());
        var playersLink = collectionModel.getLink("players");
        assertTrue(playersLink.isPresent());
        assertEquals(getUrl(), ((Link) playersLink.get()).getHref());
        assertEquals(expectedNumberOfPlayers, collectionModel.getContent().size());
    }

    private void subTestGetNonexistentPlayer(Long id) {
        ResponseEntity<PlayerDto> playerDtoResponseEntity = this.restTemplate.getForEntity(getUrlWithId(id), null);
        assertEquals(HttpStatus.NOT_FOUND, playerDtoResponseEntity.getStatusCode());
    }

    private void subTestAddPlayer(String playerName, Long expectedId) {
        PlayerDto request = new PlayerDto(null, playerName);
        ResponseEntity<PlayerDto> playerDtoResponseEntity = this.restTemplate.postForEntity(getUrl(), request, PlayerDto.class);
        assertResponseEntityEquals(playerDtoResponseEntity, HttpStatus.CREATED, expectedId, playerName);
    }

    private void subTestGetPlayer(Long id, String expectedName) {
        ResponseEntity<PlayerDto> playerDtoResponseEntity = this.restTemplate.getForEntity(getUrlWithId(id), PlayerDto.class);
        assertResponseEntityEquals(playerDtoResponseEntity, HttpStatus.OK, id, expectedName);

        Optional<Link> selfLink = Objects.requireNonNull(playerDtoResponseEntity.getBody()).getLink("self");
        assertTrue(selfLink.isPresent());
        assertEquals(getUrlWithId(id), selfLink.get().getHref());

        Optional<Link> playersLink = Objects.requireNonNull(playerDtoResponseEntity.getBody()).getLink("players");
        assertTrue(playersLink.isPresent());
        assertEquals(getUrl(), playersLink.get().getHref());
    }

    private void assertResponseEntityEquals(ResponseEntity<PlayerDto> response, HttpStatus expectedStatus,
                                            Long expectedId, String expectedName) {
        assertEquals(expectedStatus, response.getStatusCode());
        assertEquals(expectedId, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(expectedName, Objects.requireNonNull(response.getBody()).getName());
    }

    @Override
    protected String getPath() {
        return "players";
    }
}
