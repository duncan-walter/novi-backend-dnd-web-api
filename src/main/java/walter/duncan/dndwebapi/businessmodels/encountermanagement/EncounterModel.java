package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public final class EncounterModel {
    //region Fields
    private final Long id;
    private String title;
    private String description;
    private int roundNumber;
    private EncounterState state;
    private EncounterParticipantModel currentActor;
    private Set<EncounterParticipantModel> participants;
    private Set<EncounterJoinRequestModel> joinRequests;
    //endregion

    //region Constructors
    private EncounterModel(
            Long id,
            String title,
            String description,
            int roundNumber,
            EncounterState state,
            EncounterParticipantModel currentActor,
            Set<EncounterParticipantModel> participants,
            Set<EncounterJoinRequestModel> joinRequests
    ) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.roundNumber = roundNumber;
        this.state = state;
        this.currentActor = currentActor;
        this.participants = participants;
        this.joinRequests = joinRequests;
    }
    //endregion

    //region Factory methods
    public static EncounterModel create(String title, String description) {
        return new EncounterModel(
                null,
                title,
                description,
                0,
                EncounterState.GATHERING_PARTICIPANTS,
                null,
                new HashSet<>(),
                new HashSet<>()
        );
    }

    public static EncounterModel restore(
            Long id,
            String title,
            String description,
            int roundNumber,
            EncounterState state,
            EncounterParticipantModel currentActor,
            Set<EncounterParticipantModel> participants,
            Set<EncounterJoinRequestModel> joinRequests
    ) {
        return new EncounterModel(id, title, description, roundNumber, state, currentActor, participants, joinRequests);
    }
    //endregion

    //region Getters
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public EncounterState getState() {
        return this.state;
    }

    public Optional<EncounterParticipantModel> getCurrentActor() {
        return Optional.ofNullable(this.currentActor);
    }

    public Set<EncounterParticipantModel> getParticipants() {
        return this.participants;
    }

    public Set<EncounterJoinRequestModel> getJoinRequests() {
        return this.joinRequests;
    }
    //endregion

    //region Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    //region Actions
    public void addJoinRequest(EncounterJoinRequestModel joinRequest) {
        this.validateIsInState(
                EncounterState.GATHERING_PARTICIPANTS,
                BusinessRuleViolation.ENCOUNTER_JOIN_ONLY_ALLOWED_WHEN_GATHERING_PARTICIPANTS,
                "Cannot join encounter unless it is gathering participants."
        );
        this.validateUniqueCharacterParticipant(joinRequest.getCharacter());
        // add join request to join requests
    }

    public void approveJoinRequest(Long joinRequestId) {
        for (EncounterJoinRequestModel joinRequest : this.joinRequests) {
            if (joinRequest.getId().equals(joinRequestId)) {
                joinRequest.approve();
                this.addParticipant(EncounterParticipantModel.create(joinRequest.getInitiative(), this, joinRequest.getCharacter()));
                return;
            }
        }

        // if no join request is found > throw business rule exception
    }

    public void declineJoinRequest(Long joinRequestId) {
        for (EncounterJoinRequestModel joinRequest : this.joinRequests) {
            if (joinRequest.getId().equals(joinRequestId)) {
                joinRequest.decline();
                return;
            }
        }

        // if no join request is found > throw business rule exception
    }

    public void addParticipant(EncounterParticipantModel participant) {
        this.validateIsInState(
                EncounterState.GATHERING_PARTICIPANTS,
                BusinessRuleViolation.ENCOUNTER_JOIN_ONLY_ALLOWED_WHEN_GATHERING_PARTICIPANTS,
                "Cannot join encounter unless it is gathering participants."
        );
        this.validateUniqueCharacterParticipant(participant.getCharacter());
        this.participants.add(participant);
    }

    public void advanceEncounterTurn() {
        this.validateIsInState(
                EncounterState.IN_PROGRESS,
                BusinessRuleViolation.ENCOUNTER_ADVANCE_TURN_ONLY_ALLOWED_IN_PROGRESS,
                "Cannot advance a turn of an encounter that is not in progress."
        );
        // set next current actor
        // increase roundNumber if applicable
    }

    public void startEncounter() {
        this.validateIsInState(
                EncounterState.IN_PROGRESS,
                BusinessRuleViolation.ENCOUNTER_START_ONLY_ALLOWED_WHEN_GATHERING_PARTICIPANTS,
                "Cannot start an encounter that is not gathering participants."
        );
        this.state = EncounterState.IN_PROGRESS;
        // set round number
        // set current actor
        // decline all join requests that are pending
    }

    public void closeEncounter() {
        this.validateIsInState(
                EncounterState.IN_PROGRESS,
                BusinessRuleViolation.ENCOUNTER_CLOSE_ONLY_ALLOWED_IN_PROGRESS,
                "Cannot close an encounter that is not in progress."
        );
        this.state = EncounterState.COMPLETED;
    }
    //endregion

    //region Validation methods
    private void validateIsInState(EncounterState state, BusinessRuleViolation businessRuleViolation, String exceptionMessage) {
        if (this.state != state) {
            throw new BusinessRuleViolationException(businessRuleViolation, exceptionMessage);
        }
    }

    private void validateUniqueCharacterParticipant(CharacterModel character) {
        if (this.participants.stream().anyMatch(p -> p.getCharacter().getId().equals(character.getId()))) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.ENCOUNTER_CHARACTER_ALREADY_PARTICIPANT,
                    String.format("Character with id: %s is already part of this encounter.", character.getId())
            );
        }
    }
    //endregion
}