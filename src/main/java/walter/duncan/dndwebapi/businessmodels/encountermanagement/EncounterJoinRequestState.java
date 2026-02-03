package walter.duncan.dndwebapi.businessmodels.encountermanagement;

import java.util.ArrayList;
import java.util.List;

import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

public enum EncounterJoinRequestState {
    PENDING("pending"),
    APPROVED("approved"),
    DECLINED("declined");

    private final String name;

    EncounterJoinRequestState(String name) {
        this.name = name;
    }

    public static EncounterJoinRequestState fromName(String name) {
        for (EncounterJoinRequestState state : values()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }

        List<String> validNames = new ArrayList<>();
        for (EncounterJoinRequestState state : values()) {
            validNames.add(state.getName());
        }

        throw new BusinessRuleViolationException(
                BusinessRuleViolation.ENCOUNTER_JOIN_REQUEST_STATE_NOT_VALID,
                String.format("Encounter join request state must be a valid state. Use one of the following states: '%s'.", String.join(", ", validNames))
        );
    }

    public String getName() {
        return this.name;
    }
}