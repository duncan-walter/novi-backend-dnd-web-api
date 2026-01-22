package walter.duncan.dndwebapi.mappers;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.EquipmentModel;
import walter.duncan.dndwebapi.dtos.EquipmentResponseDto;

@Component
public class EquipmentResponseMapper extends BaseResponseMapper<EquipmentResponseDto, EquipmentModel> {
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