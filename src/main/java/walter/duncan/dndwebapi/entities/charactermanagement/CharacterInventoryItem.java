package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "character_inventory_items")
public class CharacterInventoryItem extends BaseEntity {
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
}