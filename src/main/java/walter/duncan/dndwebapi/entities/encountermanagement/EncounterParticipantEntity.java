package walter.duncan.dndwebapi.entities.encountermanagement;

import jakarta.persistence.*;

import walter.duncan.dndwebapi.entities.BaseEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;

@Entity
@Table(name = "encounter_participants")
public class EncounterParticipantEntity extends BaseEntity {
    @Column(name = "initiative", nullable = false)
    private int initiative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", nullable = false)
    private EncounterEntity encounter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;
}