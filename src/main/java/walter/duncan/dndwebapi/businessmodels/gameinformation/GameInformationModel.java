package walter.duncan.dndwebapi.businessmodels.gameinformation;

public abstract class GameInformationModel {
    private final Long id;
    private final String name;
    private final String description;
    private final Long valueInCopperPieces;
    private final Double weightInLbs;

    public GameInformationModel(Long id, String name, String description, Long valueInCopperPieces, Double weightInLbs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.valueInCopperPieces = valueInCopperPieces;
        this.weightInLbs = weightInLbs;
    }

    public Long getId() {
        return this.id;
    }

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