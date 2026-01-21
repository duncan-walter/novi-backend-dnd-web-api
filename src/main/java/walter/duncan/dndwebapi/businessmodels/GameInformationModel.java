package walter.duncan.dndwebapi.businessmodels;

public abstract class GameInformationModel {
    private final Long id;
    private final String name;
    private final String description;
    private final int valueInCopperPieces;
    private final double weightInLbs;

    public GameInformationModel(Long id, String name, String description, int valueInCopperPieces, double weightInLbs) {
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

    public int getValueInCopperPieces() {
        return this.valueInCopperPieces;
    }

    public double getWeightInLbs() {
        return this.weightInLbs;
    }
}