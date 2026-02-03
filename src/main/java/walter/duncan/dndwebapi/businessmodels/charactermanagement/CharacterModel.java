package walter.duncan.dndwebapi.businessmodels.charactermanagement;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory.CharacterInventoryItemModel;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

import java.util.ArrayList;
import java.util.List;

public final class CharacterModel {
    //region Fields
    private final List<Integer> levelExperienceThresholds = List.of(0, 300, 900, 2_700, 6_500, 14_000, 23_000,
            34_000, 48_000, 64_000, 85_000, 100_000, 120_000, 140_000, 165_000, 195_000, 225_000, 265_000, 305_000, 355_000);

    private Long id;
    private String name;
    private int charisma;
    private int constitution;
    private int dexterity;
    private int intelligence;
    private int strength;
    private int wisdom;
    private Integer maxHitPoints;
    private Integer currentHitPoints;
    private int experiencePoints;
    private String size;
    private Integer copperPieces;
    private Integer silverPieces;
    private Integer electrumPieces;
    private Integer goldPieces;
    private Integer platinumPieces;
    private String notes;
    private CharacterPortraitModel portrait;
    private CharacterAlignment alignment;
    private CharacterTypeModel type;
    private CharacterRaceModel race;
    private CharacterClassModel characterClass;
    private List<CharacterInventoryItemModel> inventory = new ArrayList<>();
    //endregion

    //region Constructors
    private CharacterModel(Long id) {
        this.id = id;
    }

    private CharacterModel(
            Long id, String name, int charisma, int constitution, int dexterity, int intelligence, int strength, int wisdom,
            int maxHitPoints, int currentHitPoints, int experiencePoints, String size, Integer copperPieces, Integer silverPieces,
            Integer electrumPieces, Integer goldPieces, Integer platinumPieces, String notes, CharacterPortraitModel portrait,
            CharacterAlignment alignment, CharacterTypeModel type, CharacterRaceModel race, CharacterClassModel characterClass
    ) {
        this.id = id;
        this.name = name;
        this.charisma = charisma;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.strength = strength;
        this.wisdom = wisdom;
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = currentHitPoints;
        this.experiencePoints = experiencePoints;
        this.size = size;
        this.copperPieces = copperPieces;
        this.silverPieces = silverPieces;
        this.electrumPieces = electrumPieces;
        this.goldPieces = goldPieces;
        this.platinumPieces = platinumPieces;
        this.notes = notes;
        this.portrait = portrait;
        this.alignment = alignment;
        this.type = type;
        this.race = race;
        this.characterClass = characterClass;
    }
    //endregion

    //region Factory methods
    public static CharacterModel create(
            String name, int charisma, int constitution, int dexterity, int intelligence, int strength, int wisdom,
            int maxHitPoints, int currentHitPoints, int experiencePoints, String size, Integer copperPieces, Integer silverPieces,
            Integer electrumPieces, Integer goldPieces, Integer platinumPieces, String notes, CharacterAlignment alignment,
            CharacterTypeModel type, CharacterRaceModel race, CharacterClassModel characterClass
    ) {
        var model = new CharacterModel(null);
        model.setName(name);
        model.setCharisma(charisma);
        model.setConstitution(constitution);
        model.setDexterity(dexterity);
        model.setIntelligence(intelligence);
        model.setStrength(strength);
        model.setWisdom(wisdom);
        model.setMaxHitPoints(maxHitPoints);
        model.setCurrentHitPoints(currentHitPoints);
        model.setExperiencePoints(experiencePoints);
        model.setSize(size);
        model.setCopperPieces(copperPieces);
        model.setSilverPieces(silverPieces);
        model.setElectrumPieces(electrumPieces);
        model.setGoldPieces(goldPieces);
        model.setPlatinumPieces(platinumPieces);
        model.setNotes(notes);
        model.setAlignment(alignment);
        model.setType(type);
        model.setRace(race);
        model.setCharacterClass(characterClass);

        return model;
    }

    public static CharacterModel restore(
            Long id, String name, int charisma, int constitution, int dexterity, int intelligence, int strength, int wisdom,
            int maxHitPoints, int currentHitPoints, int experiencePoints, String size, Integer copperPieces, Integer silverPieces,
            Integer electrumPieces, Integer goldPieces, Integer platinumPieces, String notes, CharacterPortraitModel portrait,
            CharacterAlignment alignment, CharacterTypeModel type, CharacterRaceModel race, CharacterClassModel characterClass
    ) {
        return new CharacterModel(
                id, name, charisma, constitution, dexterity, intelligence, strength, wisdom, maxHitPoints, currentHitPoints,
                experiencePoints, size, copperPieces, silverPieces, electrumPieces, goldPieces, platinumPieces, notes,
                portrait, alignment, type, race, characterClass
        );
    }
    //endregion

    //region Getters
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCharisma() {
        return this.charisma;
    }

    public int getConstitution() {
        return this.constitution;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getWisdom() {
        return this.wisdom;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public int getCurrentHitPoints() {
        return this.currentHitPoints;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    public String getSize() {
        return this.size;
    }

    public Integer getCopperPieces() {
        return this.copperPieces;
    }

    public Integer getSilverPieces() {
        return this.silverPieces;
    }

    public Integer getElectrumPieces() {
        return this.electrumPieces;
    }

    public Integer getGoldPieces() {
        return this.goldPieces;
    }

    public Integer getPlatinumPieces() {
        return this.platinumPieces;
    }

    public String getNotes() {
        return this.notes;
    }

    public CharacterPortraitModel getPortrait() {
        return this.portrait;
    }

    public CharacterAlignment getAlignment() {
        return this.alignment;
    }

    public CharacterTypeModel getType() {
        return this.type;
    }

    public CharacterRaceModel getRace() {
        return this.race;
    }

    public CharacterClassModel getCharacterClass() {
        return this.characterClass;
    }

    public List<CharacterInventoryItemModel> getInventory() {
        return this.inventory;
    }
    //endregion

    //region Calculated getters
    public int getLevel() {
        int level = 1;

        for (int i = this.levelExperienceThresholds.size() - 1; i >= 0; i--) {
            if (this.experiencePoints >= this.levelExperienceThresholds.get(i)) {
                level = i + 1;
                break;
            }
        }

        return level;
    }

    public int getCharismaModifier() {
        return getAbilityModifier(this.charisma);
    }

    public int getConstitutionModifier() {
        return getAbilityModifier(this.constitution);
    }

    public int getDexterityModifier() {
        return getAbilityModifier(this.dexterity);
    }

    public int getIntelligenceModifier() {
        return getAbilityModifier(this.intelligence);
    }

    public int getStrengthModifier() {
        return getAbilityModifier(this.strength);
    }

    public int getWisdomModifier() {
        return getAbilityModifier(this.wisdom);
    }
    //endregion

    //region Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharisma(int charisma) {
        this.validateAbilityScore(charisma);
        this.charisma = charisma;
    }

    public void setConstitution(int constitution) {
        this.validateAbilityScore(constitution);
        this.constitution = constitution;
    }

    public void setDexterity(int dexterity) {
        this.validateAbilityScore(dexterity);
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.validateAbilityScore(intelligence);
        this.intelligence = intelligence;
    }

    public void setStrength(int strength) {
        this.validateAbilityScore(strength);
        this.strength = strength;
    }

    public void setWisdom(int wisdom) {
        this.validateAbilityScore(wisdom);
        this.wisdom = wisdom;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        if (this.currentHitPoints != null && maxHitPoints < this.currentHitPoints) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_MAX_HIT_POINTS_LESS_THAN_CURRENT_HIT_POINTS,
                    "Max hit points cannot be less than current hit points."
            );
        }

        this.maxHitPoints = maxHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        if (this.maxHitPoints != null && currentHitPoints > this.maxHitPoints) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_CURRENT_HIT_POINTS_EXCEEDS_MAX_HIT_POINTS,
                    "Current hit points cannot exceed max hit points."
            );
        }

        this.currentHitPoints = currentHitPoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCopperPieces(Integer copperPieces) {
        this.copperPieces = copperPieces;
    }

    public void setSilverPieces(Integer silverPieces) {
        this.silverPieces = silverPieces;
    }

    public void setElectrumPieces(Integer electrumPieces) {
        this.electrumPieces = electrumPieces;
    }

    public void setGoldPieces(Integer goldPieces) {
        this.goldPieces = goldPieces;
    }

    public void setPlatinumPieces(Integer platinumPieces) {
        this.platinumPieces = platinumPieces;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPortrait(CharacterPortraitModel portrait) {
        this.portrait = portrait;
    }

    public void setAlignment(CharacterAlignment alignment) {
        this.alignment = alignment;
    }

    public void setType(CharacterTypeModel type) {
        this.type = type;
    }

    public void setRace(CharacterRaceModel race) {
        this.race = race;
    }

    public void setCharacterClass(CharacterClassModel characterClass) {
        this.characterClass = characterClass;
    }

    public void setInventory(List<CharacterInventoryItemModel> inventory) {
        int maxInventorySize = 50;

        if (inventory.size() > maxInventorySize) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_INVENTORY_TOO_BIG,
                    String.format("A character's inventory may not include more than %s items.", maxInventorySize)
            );
        }

        this.inventory = inventory;
    }
    //endregion

    //region Validation methods
    private void validateAbilityScore(int abilityScore) {
        if (abilityScore < 1 || abilityScore > 30) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_ABILITY_SCORE_OUT_OF_BOUNDS,
                    "Ability scores must be between 1 and 30."
            );
        }
    }
    //endregion

    private int getAbilityModifier(int abilityScore) {
        return (int) Math.floor((abilityScore - 10) / 2.0);
    }
}