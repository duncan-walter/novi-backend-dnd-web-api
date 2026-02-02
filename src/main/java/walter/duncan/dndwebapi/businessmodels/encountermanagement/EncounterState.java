package walter.duncan.dndwebapi.businessmodels.encountermanagement;

public enum EncounterState {
    GATHERING_PARTICIPANTS("gathering-participants"),
    IN_PROGRESS("in-progress"),
    COMPLETED("completed");

    private final String name;

    EncounterState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}