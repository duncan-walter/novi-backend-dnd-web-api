package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;

public final class EncounterParticipantModel {
    //region Fields
    private final Long id;
    private int initiative;
    private EncounterModel encounter;
    private final CharacterModel character;
    //endregion

    //region Constructors
    private EncounterParticipantModel(Long id, int initiative, EncounterModel encounter, CharacterModel character) {
        this.id = id;
        this.initiative = initiative;
        this.encounter = encounter;
        this.character = character;
    }
    //endregion

    //region Factory methods
    public static EncounterParticipantModel create(int initiative, EncounterModel encounter, CharacterModel character) {
        return new EncounterParticipantModel(null, initiative, encounter, character);
    }

    public static EncounterParticipantModel restore(Long id, int initiative, EncounterModel encounter, CharacterModel character) {
        return new EncounterParticipantModel(id, initiative, encounter, character);
    }
    //endregion

    //region Getters
    public Long getId() {
        return this.id;
    }

    public int getInitiative() {
        return this.initiative;
    }

    public EncounterModel getEncounter() {
        return this.encounter;
    }

    public CharacterModel getCharacter() {
        return this.character;
    }
    //endregion

    //region Setters
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setEncounter(EncounterModel encounter) {
        this.encounter = encounter;
    }
    //endregion
}