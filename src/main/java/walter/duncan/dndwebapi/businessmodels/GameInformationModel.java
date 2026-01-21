package walter.duncan.dndwebapi.businessmodels;

public abstract class GameInformationModel {
    private final String name;
    private final String description;
    private final int valueInCopperPieces;
    private final int weightInLbs;

    public GameInformationModel(String name, String description, int valueInCopperPieces, int weightInLbs) {
        this.name = name;
        this.description = description;
        this.valueInCopperPieces = valueInCopperPieces;
        this.weightInLbs = weightInLbs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValueInCopperPieces() {
        return valueInCopperPieces;
    }

    public int getWeightInLbs() {
        return weightInLbs;
    }
}