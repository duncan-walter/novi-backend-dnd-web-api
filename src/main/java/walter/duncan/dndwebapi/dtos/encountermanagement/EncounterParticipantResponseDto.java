package walter.duncan.dndwebapi.dtos.encountermanagement;

public record EncounterParticipantResponseDto(
        Long id,
        int initiative,
        String name,
        int currentHitPoints,
        int maxHitPoints
) { }