package walter.duncan.dndwebapi.businessmodels.charactermanagement;

public final class CharacterRaceModel {
    private final Long id;
    private String name;
    private String description;
    private int speed;

    private CharacterRaceModel(Long id) {
        this.id = id;
    }

    private CharacterRaceModel(Long id, String name, String description, int speed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.speed = speed;
    }

    public static CharacterRaceModel create(String name, String description, int speed) {
        var model = new CharacterRaceModel(null);
        model.setName(name);
        model.setDescription(description);
        model.setSpeed(speed);

        return model;
    }

    public static CharacterRaceModel restore(Long id, String name, String description, int speed) {
        return new CharacterRaceModel(id, name, description, speed);
    }

    //region Getters
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getSpeed() {
        return this.speed;
    }
    //endregion

    //region Setters with validation
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    //endregion
}