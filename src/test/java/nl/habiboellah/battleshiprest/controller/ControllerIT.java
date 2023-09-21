package nl.habiboellah.battleshiprest.controller;

import nl.habiboellah.battleshiprest.BattleshipRestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = BattleshipRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ControllerIT {
    @LocalServerPort
    protected int port;


    @Autowired
    protected TestRestTemplate restTemplate;

    protected int getPort() {
        return port;
    }

    abstract protected String getPath();

    protected String getUrlWithId(Long id) {
        return getUrl() + "/" + id.toString();
    }

    protected String getUrl() {
        return getUrl(getPath());
    }

    protected String getUrl(String path) {
        return "http://localhost:" + getPort() + "/" + path;
    }
}
