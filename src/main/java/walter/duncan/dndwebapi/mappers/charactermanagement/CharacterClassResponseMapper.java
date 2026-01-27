package walter.duncan.dndwebapi.mappers.charactermanagement;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterClassModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.CharacterClassResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class CharacterClassResponseMapper extends BaseResponseMapper<CharacterClassResponseDto, CharacterClassModel> {
    @Override
    public CharacterClassResponseDto toDto(CharacterClassModel model) {
        return new CharacterClassResponseDto(
                model.getId(),
                model.getName(),
                model.getHitDie()
        );
    }
}