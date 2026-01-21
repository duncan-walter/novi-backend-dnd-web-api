package walter.duncan.dndwebapi.mappers;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.EquipmentModel;
import walter.duncan.dndwebapi.dtos.EquipmentRequestDto;
import walter.duncan.dndwebapi.dtos.EquipmentResponseDto;
import walter.duncan.dndwebapi.entities.EquipmentEntity;

@Component
public final class EquipmentMapper extends BaseMapper<EquipmentRequestDto, EquipmentResponseDto, EquipmentModel, EquipmentEntity>{
    @Override
    public EquipmentModel toModel(EquipmentRequestDto model) {
        return new EquipmentModel(
                null,
                model.getName(),
                model.getDescription(),
                model.getValueInCopperPieces(),
                model.getWeightInLbs()
        );
    }

    @Override
    public EquipmentEntity toEntity(EquipmentModel model) {
        var entity = new EquipmentEntity();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setValueInCopperPieces(model.getValueInCopperPieces());
        entity.setWeightInLbs(model.getWeightInLbs());

        return entity;
    }

    @Override
    public EquipmentModel fromEntity(EquipmentEntity entity) {
        return new EquipmentModel(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getValueInCopperPieces(),
                entity.getWeightInLbs()
        );
    }

    @Override
    public EquipmentResponseDto toResponseDto(EquipmentModel model) {
        return new EquipmentResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getValueInCopperPieces(),
                model.getWeightInLbs()
        );
    }
}