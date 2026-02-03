package walter.duncan.dndwebapi.services.encountermanagement;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterJoinRequestModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestStateUpdateRequestDto;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestState;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterState;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterJoinRequestPersistenceMapper;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterJoinRequestRepository;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterRepository;
import walter.duncan.dndwebapi.services.BaseService;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterService;

@Service
public class EncounterJoinRequestService extends BaseService<EncounterJoinRequestEntity, Long, EncounterJoinRequestRepository> {
    private final EncounterRepository encounterRepository;
    private final EncounterService encounterService;
    private final CharacterService characterService;
    private final EncounterJoinRequestPersistenceMapper mapper;
    private final EncounterPersistenceMapper encounterPersistenceMapper;

    protected EncounterJoinRequestService(
            EncounterJoinRequestRepository repository,
            EncounterRepository encounterRepository,
            EncounterService encounterService,
            CharacterService characterService,
            EncounterJoinRequestPersistenceMapper mapper,
            EncounterPersistenceMapper encounterPersistenceMapper
    ) {
        super(repository, EncounterJoinRequestEntity.class);
        this.encounterRepository = encounterRepository;
        this.encounterService = encounterService;
        this.characterService = characterService;
        this.mapper = mapper;
        this.encounterPersistenceMapper = encounterPersistenceMapper;
    }

    public Set<EncounterJoinRequestModel> findAllByEncounterId(Long encounterId) {
        this.encounterService.existsByIdOrThrow(encounterId);

        return this.mapper.toModels(this.repository.findByEncounterId(encounterId));
    }

    @Transactional
    public EncounterJoinRequestModel create(Long encounterId, EncounterJoinRequestRequestDto requestDto) {
        var encounterEntity = this.encounterService.findByIdOrThrow(encounterId);
        var encounterModel = this.encounterPersistenceMapper.toModel(encounterEntity);
        var characterModel = this.characterService.findById(requestDto.characterId());
        var encounterJoinRequestModel = EncounterJoinRequestModel.create(requestDto.initiative(), encounterModel, characterModel);

        // Validates there is only one pending
        if (this.repository.existsByCharacterIdAndState(characterModel.getId(), EncounterJoinRequestState.PENDING)) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.ENCOUNTER_JOIN_REQUEST_ALREADY_PENDING_FOR_CHARACTER,
                    String.format("Character with id: %s already has a pending join request.", characterModel.getId())
            );
        }

        // Validates uniqueness within encounter
        encounterModel.addJoinRequest(encounterJoinRequestModel);

        var characterIsInAnotherActiveEncounter = this.encounterRepository.existsByParticipantAndState(
                characterModel.getId(),
                List.of(EncounterState.GATHERING_PARTICIPANTS, EncounterState.IN_PROGRESS)
        );
        // Validates uniqueness across active encounters
        if (characterIsInAnotherActiveEncounter) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.ENCOUNTER_CHARACTER_ALREADY_PARTICIPANT_IN_ACTIVE_ENCOUNTER,
                    String.format("Character with id: %s is already participating in an encounter active encounter.", characterModel.getId())
            );
        }

        var encounterJoinRequestEntity = this.mapper.toEntity(encounterJoinRequestModel);
        encounterJoinRequestEntity.setEncounter(encounterEntity);

        return this.mapper.toModel(this.repository.save(encounterJoinRequestEntity));
    }

    @Transactional
    public EncounterJoinRequestModel updateState(
            Long encounterId,
            Long joinRequestId,
            EncounterJoinRequestStateUpdateRequestDto requestDto
    ) {
        this.existsByIdOrThrow(joinRequestId);
        var encounterEntity = this.encounterService.findByIdOrThrow(encounterId);
        var encounterModel = this.encounterPersistenceMapper.toModel(encounterEntity);

        switch (walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterJoinRequestState.fromName(requestDto.state())) {
            case APPROVED -> encounterModel.approveJoinRequest(joinRequestId);
            case DECLINED -> encounterModel.declineJoinRequest(joinRequestId);
            default -> throw new BusinessRuleViolationException(
                    BusinessRuleViolation.ENCOUNTER_JOIN_REQUEST_INVALID_STATE_TRANSITION,
                    "Join request can only be approved or declined."
            );
        }

        this.encounterPersistenceMapper.updateEntityFromModel(encounterModel, encounterEntity);
        this.encounterRepository.save(encounterEntity);

        return encounterModel.getJoinRequests().stream()
                .filter(jr -> jr.getId().equals(joinRequestId))
                .findFirst()
                .orElseThrow();
    }
}