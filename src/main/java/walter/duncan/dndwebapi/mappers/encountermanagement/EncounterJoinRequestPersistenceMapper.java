package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterJoinRequestModel;
import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterJoinRequestState;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;

@Component
public final class EncounterJoinRequestPersistenceMapper
        extends BasePersistenceMapper<EncounterJoinRequestModel, EncounterJoinRequestEntity, Set<EncounterJoinRequestModel>, Set<EncounterJoinRequestEntity>> {
    private final CharacterPersistenceMapper characterPersistenceMapper;

    public EncounterJoinRequestPersistenceMapper(CharacterPersistenceMapper characterPersistenceMapper) {
        this.characterPersistenceMapper = characterPersistenceMapper;
    }

    @Override
    protected Set<EncounterJoinRequestModel> createModelCollection() {
        return new HashSet<>();
    }

    @Override
    protected Set<EncounterJoinRequestEntity> createEntityCollection() {
        return new HashSet<>();
    }

    @Override
    public EncounterJoinRequestModel toModel(EncounterJoinRequestEntity entity) {
        return EncounterJoinRequestModel.restore(
                entity.getId(),
                entity.getInitiative(),
                this.mapState(entity.getState()),
                null, // Set later in encounter mapping to avoid circular reference
                this.characterPersistenceMapper.toModel(entity.getCharacter())
        );
    }

    @Override
    public EncounterJoinRequestEntity toEntity(EncounterJoinRequestModel model) {
        var entity = new EncounterJoinRequestEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(EncounterJoinRequestModel model, EncounterJoinRequestEntity entity) {
        entity.setId(model.getId());
        entity.setState(this.mapState(model.getState()));
        entity.setEncounter(null); // Set later in encounter mapping to avoid circular reference
        entity.setCharacter(this.characterPersistenceMapper.toEntity(model.getCharacter()));
    }

    private EncounterJoinRequestState mapState(walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestState persistedState) {
        return switch(persistedState) {
            case PENDING -> EncounterJoinRequestState.PENDING;
            case APPROVED -> EncounterJoinRequestState.APPROVED;
            case DECLINED -> EncounterJoinRequestState.DECLINED;
        };
    }

    private walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestState mapState(EncounterJoinRequestState state) {
        return switch(state) {
            case PENDING -> walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestState.PENDING;
            case APPROVED -> walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestState.APPROVED;
            case DECLINED -> walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestState.DECLINED;
        };
    }
}