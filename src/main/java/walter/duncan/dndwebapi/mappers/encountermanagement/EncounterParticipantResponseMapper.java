package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterParticipantModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterParticipantResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class EncounterParticipantResponseMapper
        extends BaseResponseMapper<EncounterParticipantResponseDto, EncounterParticipantModel, Set<EncounterParticipantResponseDto>> {
    @Override
    protected Set<EncounterParticipantResponseDto> createCollection() {
        return new HashSet<>();
    }

    @Override
    public EncounterParticipantResponseDto toDto(EncounterParticipantModel model) {
        return new EncounterParticipantResponseDto(
                model.getId(),
                model.getInitiative(),
                model.getCharacter().getName(),
                model.getCharacter().getCurrentHitPoints(),
                model.getCharacter().getMaxHitPoints()
        );
    }
}