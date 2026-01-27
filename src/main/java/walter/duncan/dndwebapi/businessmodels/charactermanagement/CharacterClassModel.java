package walter.duncan.dndwebapi.businessmodels.charactermanagement;

import java.util.Set;
import java.util.stream.Collectors;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public final class CharacterClassModel {
    //region Fields
    private static final Set<Integer> ALLOWED_HIT_DICE = Set.of(4, 6, 8, 10, 12);

    private final Long id;
    private String name;
    private int hitDie;
    //endregion

    //region Constructors
    private CharacterClassModel(Long id) {
        this.id = id;
    }

    private CharacterClassModel(Long id, String name, int hitDie) {
        this.id = id;
        this.name = name;
        this.hitDie = hitDie;
    }
    //endregion

    //region Factory methods
    public static CharacterClassModel create(String name, int hitDie) {
        var model = new CharacterClassModel(null);
        model.setName(name);
        model.setHitDie(hitDie);

        return model;
    }

    public static CharacterClassModel restore(Long id, String name, int hitDie) {
        return new CharacterClassModel(id, name, hitDie);
    }
    //endregion

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

    //region Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHitDie(int hitDie) {
        if (!ALLOWED_HIT_DICE.contains(hitDie)) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_CLASS_ILLEGAL_HIT_DIE,
                    String.format(
                            "'%s' is not a valid hit die value. Use one of the following codes: '%s'",
                            hitDie,
                            ALLOWED_HIT_DICE.stream().map(String::valueOf).collect(Collectors.joining(", "))
                    )
            );
        }

        this.hitDie = hitDie;
    }
    //endregion
}