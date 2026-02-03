package walter.duncan.dndwebapi.mappers.charactermanagement;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterTypeModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterTypeResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class CharacterTypeResponseMapper
        extends BaseResponseMapper<CharacterTypeResponseDto, CharacterTypeModel, List<CharacterTypeResponseDto>> {
    @Override
    public CharacterTypeResponseDto toDto(CharacterTypeModel model) {
        return new CharacterTypeResponseDto(
                model.getId(),
                model.getName(),
                model.getColor()
        );
    }
}