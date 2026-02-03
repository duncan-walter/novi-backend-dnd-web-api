package walter.duncan.dndwebapi.dtos.encountermanagement;

import java.util.Set;

public record EncounterResponseDto(
        Long id,
        String title,
        String description,
        int roundNumber,
        String state,
        EncounterParticipantResponseDto currentActor,
        Set<EncounterParticipantResponseDto> participants
) { }