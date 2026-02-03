package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import java.util.*;
import java.util.stream.IntStream;

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

    //region Calculated getters
    public List<EncounterParticipantModel> getParticipantsInInitiativeOrder() {
        List<EncounterParticipantModel> ordered = new ArrayList<>(this.participants);
        ordered.sort((a, b) -> Integer.compare(b.getInitiative(), a.getInitiative()));

        return ordered;
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
        this.joinRequests.add(joinRequest);
    }

    public void approveJoinRequest(Long joinRequestId) {
        for (EncounterJoinRequestModel joinRequest : this.joinRequests) {
            if (joinRequest.getId().equals(joinRequestId)) {
                joinRequest.approve();
                this.addParticipant(EncounterParticipantModel.create(joinRequest.getInitiative(), this, joinRequest.getCharacter()));
                return;
            }
        }
    }

    public void declineJoinRequest(Long joinRequestId) {
        for (EncounterJoinRequestModel joinRequest : this.joinRequests) {
            if (joinRequest.getId().equals(joinRequestId)) {
                joinRequest.decline();
                return;
            }
        }
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

    public void advanceTurn() {
        this.validateIsInState(
                EncounterState.IN_PROGRESS,
                BusinessRuleViolation.ENCOUNTER_ADVANCE_TURN_ONLY_ALLOWED_IN_PROGRESS,
                "Cannot advance a turn of an encounter that is not in progress."
        );
        this.validateHasParticipants("Cannot advance turn because there are no participants in the encounter.");

        var participantsInInitiativeOrder = this.getParticipantsInInitiativeOrder();

        if (this.currentActor == null) {
            this.currentActor = participantsInInitiativeOrder.getFirst();
            return;
        }

        // List.indexOf() cannot be used since the object reference of current character is not the same as the one in participants.
        // That's why we use the IntStream (acts like a loop) to loop over the participants that were ordered to find the matching id.
        var currentIndex = IntStream.range(0, participantsInInitiativeOrder.size())
                .filter(i -> participantsInInitiativeOrder.get(i).getId().equals(this.currentActor.getId()))
                .findFirst()
                .orElse(-1);

        // Fallback for when the current actor is not in the participants.
        if (currentIndex == -1) {
            this.currentActor = participantsInInitiativeOrder.getFirst();
            return;
        }

        var nextIndex = currentIndex + 1;
        if (nextIndex >= participantsInInitiativeOrder.size()) {
            nextIndex = 0;
            this.roundNumber++;
        }

        this.currentActor = participantsInInitiativeOrder.get(nextIndex);
    }

    public void startEncounter() {
        this.validateIsInState(
                EncounterState.GATHERING_PARTICIPANTS,
                BusinessRuleViolation.ENCOUNTER_START_ONLY_ALLOWED_WHEN_GATHERING_PARTICIPANTS,
                "Cannot start an encounter that is not gathering participants."
        );
        this.validateHasParticipants("Cannot start encounter because there are no participants in it.");

        this.state = EncounterState.IN_PROGRESS;
        this.roundNumber = 1;
        this.joinRequests.clear();
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

    private void validateHasParticipants(String message) {
        if (this.participants.isEmpty()) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.ENCOUNTER_NO_PARTICIPANTS,
                    message
            );
        }
    }
    //endregion
}