package nl.habiboellah.battleshiprest.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Player {
    private @Id @GeneratedValue Long id;
    private String name;

    public Player() {}
    public Player(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Player player)) {
            return false;
        }
        return player.getId().equals(this.getId())
                && player.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName());
    }

    @Override
    public String toString() {
        return "Player { " +
                "id:" + this.getId() + ", " +
                "name:\""  + this.getName() + "\"" +
            " }";
    }
}
