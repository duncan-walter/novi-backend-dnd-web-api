package walter.duncan.dndwebapi.mappers.gameinformation.equipment;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.gameinformation.EquipmentModel;
import walter.duncan.dndwebapi.dtos.gameinformation.equipment.EquipmentResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class EquipmentResponseMapper
        extends BaseResponseMapper<EquipmentResponseDto, EquipmentModel, List<EquipmentResponseDto>> {
    @Override
    public EquipmentResponseDto toDto(EquipmentModel model) {
        return new EquipmentResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getValueInCopperPieces(),
                model.getWeightInLbs()
        );
    }
}