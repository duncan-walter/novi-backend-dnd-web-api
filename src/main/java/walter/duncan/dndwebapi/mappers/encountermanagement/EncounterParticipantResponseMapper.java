package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterParticipantModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterParticipantResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class EncounterParticipantResponseMapper
        extends BaseResponseMapper<EncounterParticipantResponseDto, EncounterParticipantModel, List<EncounterParticipantResponseDto>> {
    @Override
    public EncounterParticipantResponseDto toDto(EncounterParticipantModel model) {
        return new EncounterParticipantResponseDto(
                model.getId(),
                model.getCharacter().getId(),
                model.getInitiative(),
                model.getCharacter().getName(),
                model.getCharacter().getCurrentHitPoints(),
                model.getCharacter().getMaxHitPoints()
        );
    }
}