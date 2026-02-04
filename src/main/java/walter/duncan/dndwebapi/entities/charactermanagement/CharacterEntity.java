package walter.duncan.dndwebapi.entities.charactermanagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import walter.duncan.dndwebapi.entities.usermanagement.UserOwnedEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterParticipantEntity;

@Entity
@Table(name = "characters")
public final class CharacterEntity extends UserOwnedEntity {
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

    @Min(1)
    @Column(name = "max_hit_points", nullable = false)
    private int maxHitPoints;

    @Min(1)
    @Column(name = "current_hit_points", nullable = false)
    private int currentHitPoints;

    @Min(0)
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

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private CharacterPortraitEntity portrait;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "alignment", nullable = false)
    private CharacterAlignment alignment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private CharacterTypeEntity type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "race_id", nullable = false)
    private CharacterRaceEntity race;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = false)
    private CharacterClassEntity characterClass;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterInventoryItemEntity> inventory = new ArrayList<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EncounterParticipantEntity> encounterParticipations = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EncounterJoinRequestEntity> encounterJoinRequests = new HashSet<>();

    //region Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharisma() {
        return this.charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getConstitution() {
        return this.constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getWisdom() {
        return this.wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getCurrentHitPoints() {
        return this.currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getCopperPieces() {
        return this.copperPieces;
    }

    public void setCopperPieces(Integer copperPieces) {
        this.copperPieces = copperPieces;
    }

    public Integer getSilverPieces() {
        return this.silverPieces;
    }

    public void setSilverPieces(Integer silverPieces) {
        this.silverPieces = silverPieces;
    }

    public Integer getElectrumPieces() {
        return this.electrumPieces;
    }

    public void setElectrumPieces(Integer electrumPieces) {
        this.electrumPieces = electrumPieces;
    }

    public Integer getGoldPieces() {
        return this.goldPieces;
    }

    public void setGoldPieces(Integer goldPieces) {
        this.goldPieces = goldPieces;
    }

    public Integer getPlatinumPieces() {
        return this.platinumPieces;
    }

    public void setPlatinumPieces(Integer platinumPieces) {
        this.platinumPieces = platinumPieces;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CharacterPortraitEntity getPortrait() {
        return this.portrait;
    }

    public void setPortrait(CharacterPortraitEntity portrait) {
        this.portrait = portrait;
    }

    public CharacterAlignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(CharacterAlignment alignment) {
        this.alignment = alignment;
    }

    public CharacterTypeEntity getType() {
        return this.type;
    }

    public void setType(CharacterTypeEntity type) {
        this.type = type;
    }

    public CharacterRaceEntity getRace() {
        return this.race;
    }

    public void setRace(CharacterRaceEntity race) {
        this.race = race;
    }

    public CharacterClassEntity getCharacterClass() {
        return this.characterClass;
    }

    public void setCharacterClass(CharacterClassEntity characterClass) {
        this.characterClass = characterClass;
    }

    public List<CharacterInventoryItemEntity> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<CharacterInventoryItemEntity> inventory) {
        this.inventory.clear();

        if (inventory != null) {
            this.inventory.addAll(inventory);
        }
    }

    public Set<EncounterParticipantEntity> getEncounterParticipations() {
        return this.encounterParticipations;
    }

    public Set<EncounterJoinRequestEntity> getEncounterJoinRequests() {
        return this.encounterJoinRequests;
    }
    //endregion
}