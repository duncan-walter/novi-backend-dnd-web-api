package walter.duncan.dndwebapi.businessmodels.charactermanagement;

public final class CharacterClassModel {
    private final Long id;
    private String name;
    private int hitDie;

    private CharacterClassModel(Long id) {
        this.id = id;
    }

    private CharacterClassModel(Long id, String name, int hitDie) {
        this.id = id;
        this.name = name;
        this.hitDie = hitDie;
    }

    public static CharacterClassModel create(String name, int hitDie) {
        var model = new CharacterClassModel(null);
        model.setName(name);
        model.setHitDie(hitDie);

        return model;
    }

    public static CharacterClassModel restore(Long id, String name, int hitDie) {
        return new CharacterClassModel(id, name, hitDie);
    }

    //region Getters
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getHitDie() {
        return this.hitDie;
    }
    //endregion

    //region Setters with validation
    public void setName(String name) {
        this.name = name;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }
    //endregion
}