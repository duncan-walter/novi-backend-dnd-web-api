package walter.duncan.dndwebapi.dtos;

public final class EquipmentResponseDto extends GameInformationResponseDto {
    public EquipmentResponseDto(Long id, String name, String description, int valueInCopperPieces, double weightInLbs) {
        super(id, name, description, valueInCopperPieces, weightInLbs);
    }
}