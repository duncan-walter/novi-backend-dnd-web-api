package walter.duncan.dndwebapi.mappers;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.WeaponModel;
import walter.duncan.dndwebapi.dtos.WeaponRequestDto;
import walter.duncan.dndwebapi.dtos.WeaponResponseDto;
import walter.duncan.dndwebapi.entities.WeaponEntity;

@Component
public final class WeaponMapper extends BaseMapper<WeaponRequestDto, WeaponResponseDto, WeaponModel, WeaponEntity> {
    @Override
    public WeaponModel toModel(WeaponRequestDto model) {
        return new WeaponModel(
                null,
                model.getName(),
                model.getDescription(),
                model.getValueInCopperPieces(),
                model.getWeightInLbs(),
                model.getDamageDice(),
                model.getDamageType(),
                model.getRangeNormal(),
                model.getRangeLong(),
                model.getIsTwoHanded()
        );
    }

    @Override
    public WeaponEntity toEntity(WeaponModel model) {
        var entity = new WeaponEntity();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setValueInCopperPieces(model.getValueInCopperPieces());
        entity.setWeightInLbs(model.getWeightInLbs());
        entity.setDamageDice(model.getDamageDice());
        entity.setDamageType(model.getDamageType());
        entity.setRangeNormal(model.getRangeNormal());
        entity.setRangeLong(model.getRangeLong());
        entity.setIsTwoHanded(model.getIsTwoHanded());

        return entity;
    }

    @Override
    public WeaponModel fromEntity(WeaponEntity entity) {
        return new WeaponModel(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getValueInCopperPieces(),
                entity.getWeightInLbs(),
                entity.getDamageDice(),
                entity.getDamageType(),
                entity.getRangeNormal(),
                entity.getRangeLong(),
                entity.getIsTwoHanded()
        );
    }

    @Override
    public WeaponResponseDto toResponseDto(WeaponModel model) {
        return new WeaponResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getValueInCopperPieces(),
                model.getWeightInLbs(),
                model.getDamageDice(),
                model.getDamageType(),
                model.getRangeNormal(),
                model.getRangeLong(),
                model.getIsTwoHanded()
        );
    }
}