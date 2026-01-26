package walter.duncan.dndwebapi.businessmodels.charactermanagement;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

import java.util.ArrayList;
import java.util.List;

public enum CharacterAlignment {
    LAWFUL_GOOD("LG", "Lawful Good"),
    LAWFUL_NEUTRAL("LN", "Lawful Neutral"),
    LAWFUL_EVIL("LE", "Lawful Evil"),
    NEUTRAL_GOOD("NG", "Neutral Good"),
    TRUE_NEUTRAL("TN", "True Neutral"),
    NEUTRAL_EVIL("NE", "Neutral Evil"),
    CHAOTIC_GOOD("CG", "Chaotic Good"),
    CHAOTIC_NEUTRAL("CN", "Chaotic Neutral"),
    CHAOTIC_EVIL("CE", "Chaotic Evil");

    private final String code;
    private final String name;

    CharacterAlignment(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CharacterAlignment fromCode(String code) {
        for (CharacterAlignment alignment : values()) {
            if (alignment.getCode().equals(code)) {
                return alignment;
            }
        }

        List<String> validCodes = new ArrayList<>();
        for (CharacterAlignment alignment : values()) {
            validCodes.add(alignment.getCode());
        }

        throw new BusinessRuleViolationException(
                BusinessRuleViolation.CHARACTER_ALIGNMENT_NOT_VALID,
                String.format("Character alignment must be a valid. Use one of the following codes: '%s'.", String.join(", ", validCodes))
        );
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}