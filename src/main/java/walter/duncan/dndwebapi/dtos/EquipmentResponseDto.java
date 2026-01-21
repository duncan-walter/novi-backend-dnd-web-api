package walter.duncan.dndwebapi.dtos;

public final class EquipmentResponseDto extends GameInformationResponseDto {
    public EquipmentResponseDto(String name, String description, int valueInCopperPieces, int weightInLbs) {
        super(name, description, valueInCopperPieces, weightInLbs);
    }
}