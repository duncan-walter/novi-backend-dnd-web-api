package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterParticipantModel;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterParticipantEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;

@Component
public final class EncounterParticipantPersistenceMapper
        extends BasePersistenceMapper<EncounterParticipantModel, EncounterParticipantEntity, Set<EncounterParticipantModel>, Set<EncounterParticipantEntity>> {
    private final CharacterPersistenceMapper characterPersistenceMapper;

    public EncounterParticipantPersistenceMapper(CharacterPersistenceMapper characterPersistenceMapper) {
        this.characterPersistenceMapper = characterPersistenceMapper;
    }

    @Override
    protected Set<EncounterParticipantModel> createModelCollection() {
        return new HashSet<>();
    }

    @Override
    protected Set<EncounterParticipantEntity> createEntityCollection() {
        return new HashSet<>();
    }

    @Override
    public EncounterParticipantModel toModel(EncounterParticipantEntity entity) {
        return EncounterParticipantModel.restore(
                entity.getId(),
                entity.getInitiative(),
                null, // Set later in encounter mapping to avoid circular reference
                this.characterPersistenceMapper.toModel(entity.getCharacter())
        );
    }

    @Override
    public EncounterParticipantEntity toEntity(EncounterParticipantModel model) {
        var entity = new EncounterParticipantEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(EncounterParticipantModel model, EncounterParticipantEntity entity) {
        entity.setId(model.getId());
        entity.setInitiative(model.getInitiative());
        entity.setEncounter(null); // Set later in encounter mapping to avoid circular reference
        entity.setCharacter(this.characterPersistenceMapper.toEntity(model.getCharacter()));
    }
}