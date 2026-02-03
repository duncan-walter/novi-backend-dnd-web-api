package walter.duncan.dndwebapi.services.encountermanagement;

import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterAction;
import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterModel;
import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterParticipantModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterActionRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterParticipantRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterState;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterRepository;
import walter.duncan.dndwebapi.services.BaseService;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterService;

@Service
public class EncounterService extends BaseService<EncounterEntity, Long, EncounterRepository> {
    private final CharacterService characterService;
    private final EncounterPersistenceMapper mapper;

    protected EncounterService(
            EncounterRepository repository,
            CharacterService characterService,
            EncounterPersistenceMapper mapper
    ) {
        super(repository, EncounterEntity.class);
        this.characterService = characterService;
        this.mapper = mapper;
    }

    public Set<EncounterModel> findAll() {
        return this.mapper.toModels(this.repository.findAll());
    }

    public EncounterModel findById(Long id) {
        return this.mapper.toModel(this.findByIdOrThrow(id));
    }

    @Transactional
    public EncounterModel create(EncounterRequestDto requestDto) {
        var model = EncounterModel.create(requestDto.title(), requestDto.description());
        var persistedEntity = this.repository.save(this.mapper.toEntity(model));

        return this.mapper.toModel(persistedEntity);
    }

    @Transactional
    public EncounterModel addParticipant(Long id, EncounterParticipantRequestDto encounterParticipantRequestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var characterModel = this.characterService.findById(encounterParticipantRequestDto.characterId());

        var model = this.mapper.toModel(persistedEntity);
        var encounterParticipantModel = EncounterParticipantModel.create(encounterParticipantRequestDto.initiative(), model, characterModel);

        // Validates uniqueness within encounter
        model.addParticipant(encounterParticipantModel);

        // Validates uniqueness across active encounters
        var characterInAnotherActiveEncounter = this.repository.existsByParticipantAndState(
                characterModel.getId(),
                List.of(EncounterState.GATHERING_PARTICIPANTS, EncounterState.IN_PROGRESS)
        );
        if (characterInAnotherActiveEncounter) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.ENCOUNTER_CHARACTER_ALREADY_PARTICIPANT_IN_ACTIVE_ENCOUNTER,
                    String.format("Character with id: %s is already participating in an encounter active encounter.", characterModel.getId())
            );
        }

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }

    @Transactional
    public EncounterModel performAction(Long id, @Valid EncounterActionRequestDto requestDto) {
        var persistedEntity = this.findByIdOrThrow(id);
        var model = this.mapper.toModel(persistedEntity);

        switch (EncounterAction.fromName(requestDto.action())) {
            case ADVANCE_TURN -> model.advanceEncounterTurn();
            case START -> model.startEncounter();
            case CLOSE -> model.closeEncounter();
        }

        this.mapper.updateEntityFromModel(model, persistedEntity);

        return this.mapper.toModel(this.repository.save(persistedEntity));
    }
}