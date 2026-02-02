package walter.duncan.dndwebapi.entities.encountermanagement;

import jakarta.persistence.*;

import walter.duncan.dndwebapi.entities.BaseEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;

@Entity
@Table(name = "encounter_join_requests")
public class EncounterJoinRequestEntity extends BaseEntity {
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state", nullable = false)
    private EncounterJoinRequestState state;

    @ManyToOne
    @JoinColumn(name = "encounter_id")
    private EncounterEntity encounter;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CharacterEntity character;
}