package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Immutable
@Table(name = "character_types")
public final class CharacterTypeEntity extends BaseEntity {
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "color", length = 7)
    private String color;

    //region Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //endregion
}