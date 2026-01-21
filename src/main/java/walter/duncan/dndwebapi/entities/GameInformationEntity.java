package walter.duncan.dndwebapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;

@MappedSuperclass
public abstract class GameInformationEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "value_in_copper_pieces")
    @Min(0)
    private double valueInCopperPieces;

    @Column(name = "weight_in_lbs")
    @Min(0)
    private double weightInLbs;

    // Getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public void setValueInCopperPieces(double valueInCopperPieces) {
        this.valueInCopperPieces = valueInCopperPieces;
    }

    public double getWeightInLbs() {
        return this.weightInLbs;
    }

    public void setWeightInLbs(double weightInLbs) {
        this.weightInLbs = weightInLbs;
    }
}