package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class EncounterResponseMapper
        extends BaseResponseMapper<EncounterResponseDto, EncounterModel, Set<EncounterResponseDto>> {
    private final EncounterParticipantResponseMapper encounterParticipantResponseMapper;

    public EncounterResponseMapper(EncounterParticipantResponseMapper encounterParticipantResponseMapper) {
        this.encounterParticipantResponseMapper = encounterParticipantResponseMapper;
    }

    @Override
    protected Set<EncounterResponseDto> createCollection() {
        return new HashSet<>();
    }

    @Override
    public EncounterResponseDto toDto(EncounterModel model) {
        return new EncounterResponseDto(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.getRoundNumber(),
                model.getState().getName(),
                model.getCurrentActor().isPresent() ? this.encounterParticipantResponseMapper.toDto(model.getCurrentActor().get()) : null,
                this.encounterParticipantResponseMapper.toDtos(model.getParticipants())
        );
    }
}