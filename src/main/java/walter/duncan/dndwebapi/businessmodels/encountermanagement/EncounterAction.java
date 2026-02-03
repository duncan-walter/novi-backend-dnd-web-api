package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import java.util.ArrayList;
import java.util.List;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public enum EncounterAction {
    ADVANCE_TURN("advance-turn"),
    START("start"),
    CLOSE("close");

    private final String name;

    EncounterAction(String name) {
        this.name = name;
    }

    public static EncounterAction fromName(String name) {
        for (EncounterAction action : values()) {
            if (action.getName().equals(name)) {
                return action;
            }
        }

        List<String> validNames = new ArrayList<>();
        for (EncounterAction action : values()) {
            validNames.add(action.getName());
        }

        throw new BusinessRuleViolationException(
                BusinessRuleViolation.ENCOUNTER_ACTION_NOT_VALID,
                String.format("Encounter action must be a valid action. Use one of the following actions: '%s'.", String.join(", ", validNames))
        );
    }

    public String getName() {
        return this.name;
    }
}