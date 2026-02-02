package walter.duncan.dndwebapi.controllers.encountermanagement;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.helpers.UrlHelper;

@RestController
@RequestMapping("/encounters")
public class EncounterController {
    private final UrlHelper urlHelper;

    public EncounterController(UrlHelper urlHelper) {
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull Object> getById(@PathVariable Long id) {
        return ResponseEntity.ok(""); // Placeholder
    }

    @GetMapping
    public ResponseEntity<@NonNull List<Object>> get() {
        return ResponseEntity.ok(List.of()); // Placeholder
    }

    @PostMapping
    public ResponseEntity<@NonNull Object> create(@RequestBody @Valid Object requestDto) {
        var location = this.urlHelper.getResourceUri(0L); // Placeholder

        return ResponseEntity
                .created(location)
                .body(""); // Placeholder
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<@NonNull Object> addParticipant(@PathVariable Long id, @RequestBody @Valid Object requestDto) {
        var location = this.urlHelper.getResourceUri(0L); // Placeholder

        return ResponseEntity
                .created(location)
                .body(""); // Placeholder
    }

    @PatchMapping("/{id}")
    public ResponseEntity<@NonNull Object> advanceTurn(@PathVariable Long id, @RequestBody @Valid Object requestDto) {
        return ResponseEntity.ok(""); // Placeholder
    }
}