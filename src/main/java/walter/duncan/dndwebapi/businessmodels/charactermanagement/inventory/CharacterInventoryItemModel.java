package walter.duncan.dndwebapi.businessmodels.charactermanagement.inventory;

public abstract class CharacterInventoryItemModel {
    private final Long id;
    private final Long referenceId;
    private final CharacterInventoryItemType type;
    private final String name;
    private final String description;
    private final Long valueInCopperPieces;
    private final Double weightInLbs;
    private final int quantity;

    protected CharacterInventoryItemModel(
            Long id,
            Long referenceId,
            CharacterInventoryItemType type,
            String name,
            String description,
            Long valueInCopperPieces,
            Double weightInLbs,
            int quantity
    ) {
        this.id = id;
        this.referenceId = referenceId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.valueInCopperPieces = valueInCopperPieces;
        this.weightInLbs = weightInLbs;
        this.quantity = quantity;
    }

    //region Getters
    public Long getId() {
        return this.id;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }

    public CharacterInventoryItemType getType() {
        return this.type;
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

    public int getQuantity() {
        return this.quantity;
    }
    //endregion
}