package walter.duncan.dndwebapi.entities.encountermanagement;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

import walter.duncan.dndwebapi.entities.BaseEntity;

@Entity
@Table(name = "encounters")
public class EncounterEntity extends BaseEntity {
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "round_number")
    private int roundNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private EncounterState state;

    @OneToOne
    @JoinColumn(name = "current_actor_id")
    private EncounterParticipantEntity currentActor;

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EncounterParticipantEntity> participants = new HashSet<>();

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EncounterJoinRequestEntity> joinRequests = new HashSet<>();

    //region Getters & Setters
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public EncounterState getState() {
        return this.state;
    }

    public void setState(EncounterState state) {
        this.state = state;
    }

    public EncounterParticipantEntity getCurrentActor() {
        return this.currentActor;
    }

    public void setCurrentActor(EncounterParticipantEntity currentActor) {
        this.currentActor = currentActor;
    }

    public Set<EncounterParticipantEntity> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<EncounterParticipantEntity> participants) {
        this.participants = participants;
    }

    public Set<EncounterJoinRequestEntity> getJoinRequests() {
        return this.joinRequests;
    }

    public void setJoinRequests(Set<EncounterJoinRequestEntity> joinRequests) {
        this.joinRequests = joinRequests;
    }
    //endregion

    //region Reference helpers
    public void addParticipant(EncounterParticipantEntity participant) {
        this.participants.add(participant);
        participant.setEncounter(this);
    }

    public void addJoinRequest(EncounterJoinRequestEntity joinRequest) {
        this.joinRequests.add(joinRequest);
        joinRequest.setEncounter(this);
    }
    //endregion
}