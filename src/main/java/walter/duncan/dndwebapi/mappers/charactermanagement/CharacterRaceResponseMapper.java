package walter.duncan.dndwebapi.mappers.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterRaceModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterRaceResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class CharacterRaceResponseMapper
        extends BaseResponseMapper<CharacterRaceResponseDto, CharacterRaceModel, List<CharacterRaceResponseDto>> {
    @Override
    public CharacterRaceResponseDto toDto(CharacterRaceModel model) {
        return new CharacterRaceResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getSpeed()
        );
    }
}