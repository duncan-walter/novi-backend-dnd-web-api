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

    //region Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitDie() {
        return this.hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }
    //endregion
}