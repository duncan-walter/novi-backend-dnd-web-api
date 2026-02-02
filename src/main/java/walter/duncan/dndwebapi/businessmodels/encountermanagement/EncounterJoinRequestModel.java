package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;

public final class EncounterJoinRequestModel {
    //region Fields
    private final Long id;
    private final int initiative;
    private EncounterJoinRequestState state;
    private final EncounterModel encounter;
    private final CharacterModel character;
    //endregion

    //region Constructors
    private EncounterJoinRequestModel(
            Long id,
            int initiative,
            EncounterJoinRequestState state,
            EncounterModel encounter,
            CharacterModel character
    ) {
        this.id = id;
        this.initiative = initiative;
        this.state = state;
        this.encounter = encounter;
        this.character = character;
    }
    //endregion

    //region Factory methods
    public static EncounterJoinRequestModel create(int initiative, EncounterModel encounter, CharacterModel character) {
        return new EncounterJoinRequestModel(
                null,
                initiative,
                EncounterJoinRequestState.PENDING,
                encounter,
                character
        );
    }

    public static EncounterJoinRequestModel restore(
            Long id,
            int initiative,
            EncounterJoinRequestState state,
            EncounterModel encounter,
            CharacterModel character
    ) {
        return new EncounterJoinRequestModel(id, initiative, state, encounter, character);
    }
    //endregion

    //region Getters
    public Long getId() {
        return this.id;
    }

    public int getInitiative() {
        return this.initiative;
    }

    public EncounterJoinRequestState getState() {
        return this.state;
    }

    public EncounterModel getEncounter() {
        return this.encounter;
    }

    public CharacterModel getCharacter() {
        return this.character;
    }
    //endregion

    //region Actions
    public void approve() {
        // check if state is PENDING > otherwise throw business rule exception
    }

    public void decline() {
        // check if state is PENDING > otherwise throw business rule exception
    }
    //endregion
}