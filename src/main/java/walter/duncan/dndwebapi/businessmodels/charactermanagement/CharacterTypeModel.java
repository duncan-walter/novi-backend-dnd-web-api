package walter.duncan.dndwebapi.businessmodels.charactermanagement;

public final class CharacterTypeModel {
    private final Long id;
    private String name;
    private String color;

    private CharacterTypeModel(Long id) {
        this.id = id;
    }

    private CharacterTypeModel(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public static CharacterTypeModel create(String name, String color) {
        var model = new CharacterTypeModel(null);
        model.setName(name);
        model.setColor(color);

        return model;
    }

    public static CharacterTypeModel restore(Long id, String name, String color) {
        return new CharacterTypeModel(id, name, color);
    }

    //region Getters
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }
    //endregion

    //region Setters with validation
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //endregion
}