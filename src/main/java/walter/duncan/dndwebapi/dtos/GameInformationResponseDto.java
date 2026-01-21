package walter.duncan.dndwebapi.dtos;

public abstract class GameInformationResponseDto {
    private final String name;
    private final String description;
    private final int valueInCopperPieces;
    private final int weightInLbs;

    public GameInformationResponseDto(String name, String description, int valueInCopperPieces, int weightInLbs) {
        this.name = name;
        this.description = description;
        this.valueInCopperPieces = valueInCopperPieces;
        this.weightInLbs = weightInLbs;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public int getWeightInLbs() {
        return this.weightInLbs;
    }
}