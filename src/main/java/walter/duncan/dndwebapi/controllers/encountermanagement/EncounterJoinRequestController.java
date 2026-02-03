package walter.duncan.dndwebapi.controllers.encountermanagement;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterJoinRequestResponseDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterJoinRequestResponseMapper;
import walter.duncan.dndwebapi.services.encountermanagement.EncounterJoinRequestService;

@RestController
@RequestMapping("/encounters/{encounterId}/join-requests")
public class EncounterJoinRequestController {
    private final EncounterJoinRequestService encounterJoinRequestService;
    private final EncounterJoinRequestResponseMapper mapper;
    private final UrlHelper urlHelper;

    public EncounterJoinRequestController(
            EncounterJoinRequestService encounterJoinRequestService,
            EncounterJoinRequestResponseMapper mapper,
            UrlHelper urlHelper
    ) {
        this.encounterJoinRequestService = encounterJoinRequestService;
        this.mapper = mapper;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<@NonNull List<EncounterJoinRequestResponseDto>> get(@PathVariable Long encounterId) {
        return ResponseEntity.ok(this.mapper.toDtos(this.encounterJoinRequestService.findAllByEncounterId(encounterId)));
    }

    @PostMapping
    public ResponseEntity<@NonNull Object> create(@PathVariable Long encounterId, @RequestBody @Valid Object requestDto) {
        var location = this.urlHelper.getResourceUri(0L); // Placeholder

        return ResponseEntity
                .created(location)
                .body(""); // Placeholder
    }

    @PatchMapping("/{id}")
    public ResponseEntity<@NonNull Object> updateState(@PathVariable Long encounterId, @PathVariable Long id, @RequestBody @Valid Object requestDto) {
        return ResponseEntity.ok(""); // Placeholder
    }
}