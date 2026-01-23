package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity()
@Table(name = "character_races")
public final class CharacterRaceEntity extends BaseEntity {
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "speed")
    private int speed;
}