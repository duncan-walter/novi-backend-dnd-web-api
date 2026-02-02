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
}