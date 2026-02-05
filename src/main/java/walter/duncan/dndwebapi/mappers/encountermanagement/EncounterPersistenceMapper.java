package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.*;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.mappers.BasePersistenceMapper;

@Component
public final class EncounterPersistenceMapper
        extends BasePersistenceMapper<EncounterModel, EncounterEntity, Set<EncounterModel>, Set<EncounterEntity>> {
    private final EncounterParticipantPersistenceMapper encounterParticipantPersistenceMapper;
    private final EncounterJoinRequestPersistenceMapper encounterJoinRequestPersistenceMapper;

    public EncounterPersistenceMapper(
            EncounterJoinRequestPersistenceMapper encounterJoinRequestPersistenceMapper,
            EncounterParticipantPersistenceMapper encounterParticipantPersistenceMapper
    ) {
        this.encounterJoinRequestPersistenceMapper = encounterJoinRequestPersistenceMapper;
        this.encounterParticipantPersistenceMapper = encounterParticipantPersistenceMapper;
    }

    @Override
    protected Set<EncounterModel> createModelCollection() {
        return new HashSet<>();
    }

    @Override
    protected Set<EncounterEntity> createEntityCollection() {
        return new HashSet<>();
    }

    @Override
    public EncounterModel toModel(EncounterEntity entity) {
        var participantModels = this.encounterParticipantPersistenceMapper.toModels(entity.getParticipants());
        var joinRequestModels = this.encounterJoinRequestPersistenceMapper.toModels(entity.getJoinRequests());
        var currentActorModel = entity.getCurrentActor() != null
                ? this.encounterParticipantPersistenceMapper.toModel(entity.getCurrentActor())
                : null;

        var encounterModel = EncounterModel.restore(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getRoundNumber(),
                this.mapState(entity.getState()),
                currentActorModel,
                participantModels,
                joinRequestModels,
                entity.getUser()
        );

        if (currentActorModel != null) {
            currentActorModel.setEncounter(encounterModel);
        }

        participantModels.forEach(pm -> pm.setEncounter(encounterModel));
        joinRequestModels.forEach(jrm -> jrm.setEncounter(encounterModel));

        return encounterModel;
    }

    @Override
    public EncounterEntity toEntity(EncounterModel model) {
        var entity = new EncounterEntity();
        this.updateEntityFromModel(model, entity);

        return entity;
    }

    @Override
    public void updateEntityFromModel(EncounterModel model, EncounterEntity entity) {
        var participantEntities = this.encounterParticipantPersistenceMapper.toEntities(model.getParticipants());
        var joinRequestEntities = this.encounterJoinRequestPersistenceMapper.toEntities(model.getJoinRequests());
        var currentActorEntity = model.getCurrentActor().isPresent()
                ? this.encounterParticipantPersistenceMapper.toEntity(model.getCurrentActor().get())
                : null;

        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setRoundNumber(model.getRoundNumber());
        entity.setState(this.mapState(model.getState()));
        entity.setCurrentActor(currentActorEntity);
        entity.getParticipants().clear();
        entity.getParticipants().addAll(participantEntities);
        entity.getJoinRequests().clear();
        entity.getJoinRequests().addAll(joinRequestEntities);
        entity.setUser(model.getUser());

        if (currentActorEntity != null) {
            currentActorEntity.setEncounter(entity);
        }

        participantEntities.forEach(pe -> pe.setEncounter(entity));
        joinRequestEntities.forEach(jre -> jre.setEncounter(entity));
    }

    private EncounterState mapState(walter.duncan.dndwebapi.entities.encountermanagement.EncounterState persistedState) {
        return switch(persistedState) {
            case GATHERING_PARTICIPANTS -> EncounterState.GATHERING_PARTICIPANTS;
            case IN_PROGRESS -> EncounterState.IN_PROGRESS;
            case COMPLETED -> EncounterState.COMPLETED;
        };
    }

    private walter.duncan.dndwebapi.entities.encountermanagement.EncounterState mapState(EncounterState state) {
        return switch(state) {
            case GATHERING_PARTICIPANTS -> walter.duncan.dndwebapi.entities.encountermanagement.EncounterState.GATHERING_PARTICIPANTS;
            case IN_PROGRESS -> walter.duncan.dndwebapi.entities.encountermanagement.EncounterState.IN_PROGRESS;
            case COMPLETED -> walter.duncan.dndwebapi.entities.encountermanagement.EncounterState.COMPLETED;
        };
    }
}