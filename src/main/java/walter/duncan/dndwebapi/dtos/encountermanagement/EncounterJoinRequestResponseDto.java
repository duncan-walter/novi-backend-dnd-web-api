package walter.duncan.dndwebapi.dtos.encountermanagement;

// Yes the naming on this is unfortunate...
public record EncounterJoinRequestResponseDto(
        Long id,
        String state,
        int initiative,
        Long characterId
) { }