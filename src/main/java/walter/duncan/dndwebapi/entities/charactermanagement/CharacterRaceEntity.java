package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Immutable
@Table(name = "character_races")
public final class CharacterRaceEntity extends BaseEntity {
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "speed")
    private int speed;

    //region Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    //endregion
}