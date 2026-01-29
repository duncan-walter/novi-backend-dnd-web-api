package walter.duncan.dndwebapi.entities.charactermanagement.inventory;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import walter.duncan.dndwebapi.entities.BaseEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;

@Entity
@Table(name = "character_inventory_items")
public class CharacterInventoryItemEntity extends BaseEntity {
    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private CharacterInventoryItemType type;

    @Min(1)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;

    // NOTE: Custom item lifecycles are bound to inventory items, meaning they should be deleted when the owning inventory item is deleted.
    // This is achieved via cascade + orphanRemoval since custom info is not a standalone resource (unlike weapon and equipment)
    // For inventory item types other than custom, this field will be null.
    @OneToOne(mappedBy = "inventoryItem", cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    private CharacterInventoryItemCustomInfoEntity customInfo;

    //region Getters & Setters
    public Long getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public CharacterInventoryItemType getType() {
        return this.type;
    }

    public void setType(CharacterInventoryItemType type) {
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

    public CharacterInventoryItemCustomInfoEntity getCustomInfo() {
        if (this.type != CharacterInventoryItemType.CUSTOM) {
            throw new IllegalStateException(
                    "Custom info is only available for custom inventory items."
            );
        }

        return this.customInfo;
    }
    //endregion
}