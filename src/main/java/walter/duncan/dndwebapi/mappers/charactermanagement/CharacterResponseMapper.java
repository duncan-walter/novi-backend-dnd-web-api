package walter.duncan.dndwebapi.mappers.charactermanagement;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.CharacterModel;
import walter.duncan.dndwebapi.dtos.charactermanagement.*;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public class CharacterResponseMapper extends BaseResponseMapper<CharacterResponseDto, CharacterModel> {
    @Override
    public CharacterResponseDto toDto(CharacterModel model) {
        var alignment = model.getAlignment();
        var typeModel = model.getType();
        var raceModel = model.getRace();
        var classModel = model.getCharacterClass();

        return new CharacterResponseDto(
                model.getId(),
                model.getName(),
                model.getCharisma(),
                model.getConstitution(),
                model.getDexterity(),
                model.getIntelligence(),
                model.getStrength(),
                model.getWisdom(),
                model.getMaxHitPoints(),
                model.getCurrentHitPoints(),
                model.getExperiencePoints(),
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
                new CharacterTypeResponseDto(
                        typeModel.getId(),
                        typeModel.getName(),
                        typeModel.getColor()
                ),
                new CharacterRaceResponseDto(
                        raceModel.getId(),
                        raceModel.getName(),
                        raceModel.getDescription(),
                        raceModel.getSpeed()
                ),
                new CharacterClassResponseDto(
                        classModel.getId(),
                        classModel.getName(),
                        classModel.getHitDie()
                )
        );
    }
}