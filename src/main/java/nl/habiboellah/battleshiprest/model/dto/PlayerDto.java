package nl.habiboellah.battleshiprest.model.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class PlayerDto extends RepresentationModel<PlayerDto> {
    private Long id;
    private String name;

    public PlayerDto(Long id, String name) {
        this.id = id;
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
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PlayerDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "PlayerDto[" +
                "id=" + id + ", " +
                "name=" + name + ']';
    }
}
