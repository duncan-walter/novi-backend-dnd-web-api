package walter.duncan.dndwebapi.businessmodels.charactermanagement;

import java.util.regex.Pattern;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public final class CharacterTypeModel {
    //region Fields
    private final Long id;
    private String name;
    private String color;
    //endregion

    //region Constructors
    private CharacterTypeModel(Long id) {
        this.id = id;
    }

    private CharacterTypeModel(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
    //endregion

    //region Factory methods
    public static CharacterTypeModel create(String name, String color) {
        var model = new CharacterTypeModel(null);
        model.setName(name);
        model.setColor(color);

        return model;
    }

    public static CharacterTypeModel restore(Long id, String name, String color) {
        return new CharacterTypeModel(id, name, color);
    }
    //endregion

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

    //region Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        if (color == null || !Pattern.compile("^#([A-Fa-f0-9]{6})$").matcher(color).matches()) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_TYPE_INCORRECT_COLOR_FORMAT,
                    "Invalid color code: " + color + ". Must be a hex code like #RRGGBB."
            );
        }

        this.color = color;
    }
    //endregion
}