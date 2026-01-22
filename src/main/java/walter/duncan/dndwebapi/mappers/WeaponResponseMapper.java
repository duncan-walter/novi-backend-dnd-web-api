package walter.duncan.dndwebapi.mappers;

import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.WeaponModel;
import walter.duncan.dndwebapi.dtos.WeaponResponseDto;

@Component
public final class WeaponResponseMapper extends BaseResponseMapper<WeaponResponseDto, WeaponModel>{
    @Override
    public WeaponResponseDto toDto(WeaponModel model) {
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