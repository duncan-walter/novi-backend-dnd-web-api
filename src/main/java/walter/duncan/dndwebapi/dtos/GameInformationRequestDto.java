package walter.duncan.dndwebapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public abstract class GameInformationRequestDto {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2500)
    private String description;

    @Positive
    private int valueInCopperPieces;

    @Positive
    private int weightInLbs;

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