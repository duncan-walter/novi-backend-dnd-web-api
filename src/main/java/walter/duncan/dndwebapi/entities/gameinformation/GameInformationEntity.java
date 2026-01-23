package walter.duncan.dndwebapi.entities.gameinformation;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;

import walter.duncan.dndwebapi.entities.BaseEntity;

@MappedSuperclass
public abstract class GameInformationEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "value_in_copper_pieces")
    @Min(0)
    private Long valueInCopperPieces;

    @Column(name = "weight_in_lbs")
    @Min(0)
    private Double weightInLbs;

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

    public Long getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public void setValueInCopperPieces(Long valueInCopperPieces) {
        this.valueInCopperPieces = valueInCopperPieces;
    }

    public Double getWeightInLbs() {
        return this.weightInLbs;
    }

    public void setWeightInLbs(Double weightInLbs) {
        this.weightInLbs = weightInLbs;
    }
}