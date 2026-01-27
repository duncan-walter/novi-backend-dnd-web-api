package walter.duncan.dndwebapi.businessmodels.charactermanagement;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public final class CharacterModel {
    private final Long id;
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
    private CharacterAlignment alignment;
    private CharacterTypeModel type;
    private CharacterRaceModel race;
    private CharacterClassModel characterClass;

    private CharacterModel(Long id) {
        this.id = id;
    }

    private CharacterModel(
            Long id, String name, int charisma, int constitution, int dexterity, int intelligence, int strength, int wisdom,
            int maxHitPoints, int currentHitPoints, int experiencePoints, String size, Integer copperPieces, Integer silverPieces,
            Integer electrumPieces, Integer goldPieces, Integer platinumPieces, String notes, CharacterAlignment alignment,
            CharacterTypeModel type, CharacterRaceModel race, CharacterClassModel characterClass
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
        this.alignment = alignment;
        this.type = type;
        this.race = race;
        this.characterClass = characterClass;
    }

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
            Integer electrumPieces, Integer goldPieces, Integer platinumPieces, String notes, CharacterAlignment alignment,
            CharacterTypeModel type, CharacterRaceModel race, CharacterClassModel characterClass
    ) {
        return new CharacterModel(
                id, name, charisma, constitution, dexterity, intelligence, strength, wisdom, maxHitPoints, currentHitPoints,
                experiencePoints, size, copperPieces, silverPieces, electrumPieces, goldPieces, platinumPieces, notes,
                alignment, type, race, characterClass
        );
    }

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
    //endregion

    //region Setters with validation
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
    //endregion

    private void validateAbilityScore(int abilityScore) {
        if (abilityScore < 1 || abilityScore > 30) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_ABILITY_SCORE_OUT_OF_BOUNDS,
                    "Ability scores must be between 1 and 30."
            );
        }
    }
}