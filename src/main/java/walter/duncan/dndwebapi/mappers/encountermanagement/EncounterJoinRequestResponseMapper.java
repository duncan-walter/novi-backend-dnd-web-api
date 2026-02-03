package walter.duncan.dndwebapi.mappers.encountermanagement;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterJoinRequestModel;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class EncounterJoinRequestResponseMapper
        extends BaseResponseMapper<EncounterJoinRequestResponseDto, EncounterJoinRequestModel, List<EncounterJoinRequestResponseDto>> {
    @Override
    public EncounterJoinRequestResponseDto toDto(EncounterJoinRequestModel model) {
        return new EncounterJoinRequestResponseDto(
                model.getId(),
                model.getState().getName(),
                model.getCharacter().getId(),
                model.getInitiative()
        );
    }
}