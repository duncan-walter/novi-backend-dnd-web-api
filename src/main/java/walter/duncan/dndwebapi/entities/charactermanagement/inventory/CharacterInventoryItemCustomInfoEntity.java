package walter.duncan.dndwebapi.entities.charactermanagement.inventory;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "character_inventory_item_custom_infos")
public class CharacterInventoryItemCustomInfoEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "value_in_copper_pieces")
    @Min(0)
    private Long valueInCopperPieces;

    @Column(name = "weight_in_lbs")
    @Min(0)
    private Double weightInLbs;

    @OneToOne
    @JoinColumn(name = "character_inventory_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // Enforced by database for extra data integrity
    private CharacterInventoryItemEntity inventoryItem;

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

    public Long getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public void setValueInCopperPieces(Long valueInCopperPieces) {
        this.valueInCopperPieces = valueInCopperPieces;
    }

    public Double getWeightInLbs() {
        return this.weightInLbs;
    }

    public void setWeightInLbs(Double weightInLbs) {
        this.weightInLbs = weightInLbs;
    }

    public CharacterInventoryItemEntity getInventoryItem() {
        return this.inventoryItem;
    }

    public void setInventoryItem(CharacterInventoryItemEntity inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
    //endregion
}