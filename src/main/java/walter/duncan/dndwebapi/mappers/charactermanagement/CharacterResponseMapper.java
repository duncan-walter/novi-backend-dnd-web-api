package walter.duncan.dndwebapi.mappers.charactermanagement;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.*;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class CharacterResponseMapper extends BaseResponseMapper<CharacterResponseDto, CharacterModel> {
    private final CharacterTypeResponseMapper characterTypeResponseMapper;
    private final CharacterRaceResponseMapper characterRaceResponseMapper;
    private final CharacterClassResponseMapper characterClassResponseMapper;

    public CharacterResponseMapper(
            CharacterTypeResponseMapper characterTypeResponseMapper,
            CharacterRaceResponseMapper characterRaceResponseMapper,
            CharacterClassResponseMapper characterClassResponseMapper
    ) {
        this.characterTypeResponseMapper = characterTypeResponseMapper;
        this.characterRaceResponseMapper = characterRaceResponseMapper;
        this.characterClassResponseMapper = characterClassResponseMapper;
    }

    @Override
    public CharacterResponseDto toDto(CharacterModel model) {
        var alignment = model.getAlignment();

        return new CharacterResponseDto(
                model.getId(),
                model.getName(),
                model.getCharisma(),
                model.getCharismaModifier(),
                model.getConstitution(),
                model.getConstitutionModifier(),
                model.getDexterity(),
                model.getDexterityModifier(),
                model.getIntelligence(),
                model.getIntelligenceModifier(),
                model.getStrength(),
                model.getStrengthModifier(),
                model.getWisdom(),
                model.getWisdomModifier(),
                model.getMaxHitPoints(),
                model.getCurrentHitPoints(),
                model.getExperiencePoints(),
                model.getLevel(),
                model.getSize(),
                model.getCopperPieces(),
                model.getSilverPieces(),
                model.getElectrumPieces(),
                model.getGoldPieces(),
                model.getPlatinumPieces(),
                model.getNotes(),
                new CharacterAlignmentResponseDto(
                        alignment.getCode(),
                        alignment.getName()
                ),
                this.characterTypeResponseMapper.toDto(model.getType()),
                this.characterRaceResponseMapper.toDto(model.getRace()),
                this.characterClassResponseMapper.toDto(model.getCharacterClass())
        );
    }
}