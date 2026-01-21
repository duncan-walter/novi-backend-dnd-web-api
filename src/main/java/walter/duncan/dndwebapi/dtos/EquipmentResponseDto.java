package walter.duncan.dndwebapi.dtos;

public final class EquipmentResponseDto extends GameInformationResponseDto {
    public EquipmentResponseDto(String name, String description, int valueInCopperPieces, double weightInLbs) {
        super(name, description, valueInCopperPieces, weightInLbs);
    }
}