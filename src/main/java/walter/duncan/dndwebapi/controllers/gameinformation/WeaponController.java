package walter.duncan.dndwebapi.controllers.gameinformation;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponRequestDto;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponResponseDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.gameinformation.weapon.WeaponResponseMapper;
import walter.duncan.dndwebapi.services.gameinformation.WeaponService;

@RestController
@RequestMapping("/weapons")
public class WeaponController {
    private final WeaponService weaponService;
    private final WeaponResponseMapper mapper;
    private final UrlHelper urlHelper;

    public WeaponController(WeaponService weaponService, WeaponResponseMapper mapper, UrlHelper urlHelper) {
        this.weaponService = weaponService;
        this.mapper = mapper;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull WeaponResponseDto> getById(@PathVariable Long id) {
        var responseDto = this.mapper.toDto(this.weaponService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<@NonNull List<WeaponResponseDto>> get() {
        var responseDtos = this.mapper.toDtos(this.weaponService.findAll());

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<@NonNull WeaponResponseDto> create(@RequestBody @Valid WeaponRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.weaponService.create(requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull WeaponResponseDto> update(@PathVariable Long id, @RequestBody @Valid WeaponRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.weaponService.update(id, requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Object> delete(@PathVariable Long id) {
        this.weaponService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}