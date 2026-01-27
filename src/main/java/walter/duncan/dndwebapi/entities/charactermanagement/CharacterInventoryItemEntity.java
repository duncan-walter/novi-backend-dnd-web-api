package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "character_inventory_items")
public class CharacterInventoryItemEntity extends BaseEntity {
    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    @Column(name = "type", nullable = false)
    private String type;

    @Min(1)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CharacterEntity character;

    //region Getters & Setters
    public Long getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CharacterEntity getCharacter() {
        return this.character;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }
    //endregion
}