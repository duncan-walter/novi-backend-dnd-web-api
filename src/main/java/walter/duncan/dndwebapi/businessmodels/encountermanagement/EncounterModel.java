package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        // check if state is gathering participants > throw business rule exception if true
        // check is participant  already in participants > throw business rule exception if true
        // check if join request model character's participations has an encounter that is in progress > throw business rule exception is true
        // check if join request model character's join requests has a join request that is pending > throw business rule exception if true
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
        // check is participant already in participants > throw business rule exception if true
        // check if participant model character's participations has an encounter that is in progress > throw business rule exception if true
        // add participant to participants
    }

    public void advanceEncounterTurn() {
        // set next current actor
        // increase roundNumber if applicable
    }

    public void startEncounter() {
        // set state
        // set round number
        // set current actor
        // decline all join requests that are pending
    }

    public void closeEncounter() {
        // check if state is "in progress" > throw business rule exception if true
        // set state
    }
    //endregion
}