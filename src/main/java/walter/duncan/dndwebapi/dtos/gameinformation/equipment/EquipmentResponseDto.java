package walter.duncan.dndwebapi.dtos.gameinformation.equipment;

import walter.duncan.dndwebapi.dtos.gameinformation.GameInformationResponseDto;

public final class EquipmentResponseDto extends GameInformationResponseDto {
    public EquipmentResponseDto(Long id, String name, String description, Long valueInCopperPieces, Double weightInLbs) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }
}