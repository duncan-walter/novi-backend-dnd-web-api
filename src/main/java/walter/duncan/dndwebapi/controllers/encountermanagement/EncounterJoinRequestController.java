package walter.duncan.dndwebapi.controllers.encountermanagement;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.helpers.UrlHelper;

@RestController
@RequestMapping("/encounters/{encounterId}/join-requests")
public class EncounterJoinRequestController {
    private final UrlHelper urlHelper;

    public EncounterJoinRequestController(UrlHelper urlHelper) {
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<@NonNull List<Object>> get(@PathVariable Long encounterId) {
        return ResponseEntity.ok(List.of()); // Placeholder
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