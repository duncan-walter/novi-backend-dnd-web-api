package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity()
@Table(name = "character_classes")
public final class CharacterClassEntity extends BaseEntity {
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "hit_die")
    private int hitDie;
}