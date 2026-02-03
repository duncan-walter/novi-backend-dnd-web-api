package walter.duncan.dndwebapi.services.encountermanagement;

import java.util.Set;
import org.springframework.stereotype.Service;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterJoinRequestModel;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterJoinRequestEntity;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterJoinRequestPersistenceMapper;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterJoinRequestRepository;
import walter.duncan.dndwebapi.services.BaseService;

@Service
public class EncounterJoinRequestService extends BaseService<EncounterJoinRequestEntity, Long, EncounterJoinRequestRepository> {
    private final EncounterService encounterService;
    private final EncounterJoinRequestPersistenceMapper mapper;

    protected EncounterJoinRequestService(
            EncounterJoinRequestRepository repository,
            EncounterService encounterService,
            EncounterJoinRequestPersistenceMapper mapper
    ) {
        super(repository, EncounterJoinRequestEntity.class);
        this.encounterService = encounterService;
        this.mapper = mapper;
    }

    public Set<EncounterJoinRequestModel> findAllByEncounterId(Long encounterId) {
        this.encounterService.existsByIdOrThrow(encounterId);

        return this.mapper.toModels(this.repository.findByEncounterId(encounterId));
    }
}