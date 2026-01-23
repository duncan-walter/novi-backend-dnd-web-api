package walter.duncan.dndwebapi.dtos.gameinformation;

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
    private Long valueInCopperPieces;

    @PositiveOrZero
    private Double weightInLbs;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public Double getWeightInLbs() {
        return this.weightInLbs;
    }
}