package walter.duncan.dndwebapi.businessmodels.encountermanagement;

public enum EncounterJoinRequestState {
    PENDING("pending"),
    APPROVED("approved"),
    DECLINED("declinded");

    private final String name;

    EncounterJoinRequestState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}