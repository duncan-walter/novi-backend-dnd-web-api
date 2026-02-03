package walter.duncan.dndwebapi.mappers.gameinformation.weapon;

import java.util.List;
import org.springframework.stereotype.Component;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponResponseDto;
import walter.duncan.dndwebapi.mappers.BaseResponseMapper;

@Component
public final class WeaponResponseMapper
        extends BaseResponseMapper<WeaponResponseDto, WeaponModel, List<WeaponResponseDto>> {
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