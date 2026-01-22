package walter.duncan.dndwebapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public abstract class GameInformationRequestDto {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2500)
    private String description;

    @PositiveOrZero
    private int valueInCopperPieces;

    @PositiveOrZero
    private double weightInLbs;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public double getWeightInLbs() {
        return this.weightInLbs;
    }
}