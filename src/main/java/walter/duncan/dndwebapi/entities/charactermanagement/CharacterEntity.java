package walter.duncan.dndwebapi.entities.charactermanagement;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "characters")
public final class CharacterEntity extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String name;

    @Min(1)
    @Max(30)
    @Column(name = "charisma", nullable = false)
    private int charisma;

    @Min(1)
    @Max(30)
    @Column(name = "constitution", nullable = false)
    private int constitution;

    @Min(1)
    @Max(30)
    @Column(name = "dexterity", nullable = false)
    private int dexterity;

    @Min(1)
    @Max(30)
    @Column(name = "intelligence", nullable = false)
    private int intelligence;

    @Min(1)
    @Max(30)
    @Column(name = "strength", nullable = false)
    private int strength;

    @Min(1)
    @Max(30)
    @Column(name = "wisdom", nullable = false)
    private int wisdom;

    @Column(name = "max_hit_points", nullable = false)
    private int maxHitPoints;

    @Column(name = "current_hit_points", nullable = false)
    private int currentHitPoints;

    @Column(name = "experience_points", nullable = false)
    private int experiencePoints;

    @Column(name = "size", length = 50)
    private String size;

    @Column(name = "copper_pieces")
    private Integer copperPieces;

    @Column(name = "silver_pieces")
    private Integer silverPieces;

    @Column(name = "electrum_pieces")
    private Integer electrumPieces;

    @Column(name = "gold_pieces")
    private Integer goldPieces;

    @Column(name = "platinum_pieces")
    private Integer platinumPieces;

    @Column(name = "notes", length = 5000)
    private String notes;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "alignment", nullable = false)
    private CharacterAlignment alignment;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CharacterTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private CharacterRaceEntity race;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private CharacterClassEntity characterClass;
}