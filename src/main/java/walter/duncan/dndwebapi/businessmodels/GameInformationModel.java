package walter.duncan.dndwebapi.businessmodels;

public abstract class GameInformationModel {
    private final String name;
    private final String description;
    private final int valueInCopperPieces;
    private final double weightInLbs;

    public GameInformationModel(String name, String description, int valueInCopperPieces, double weightInLbs) {
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

    public double getWeightInLbs() {
        return this.weightInLbs;
    }
}