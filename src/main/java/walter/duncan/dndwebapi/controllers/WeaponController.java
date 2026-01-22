package walter.duncan.dndwebapi.controllers;

import java.util.List;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import walter.duncan.dndwebapi.dtos.WeaponRequestDto;
import walter.duncan.dndwebapi.dtos.WeaponResponseDto;
import walter.duncan.dndwebapi.helpers.UrlHelper;
import walter.duncan.dndwebapi.mappers.WeaponResponseMapper;
import walter.duncan.dndwebapi.services.WeaponService;

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
    public ResponseEntity<@NonNull WeaponResponseDto> getAlbumById(@PathVariable Long id) {
        var responseDto = this.mapper.toDto(this.weaponService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<@NonNull List<WeaponResponseDto>> getAlbums() {
        var responseDtos = this.mapper.toDtos(this.weaponService.findAll());

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<@NonNull WeaponResponseDto> createAlbum(@RequestBody @Valid WeaponRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.weaponService.create(requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .created(location)
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull WeaponResponseDto> updateAlbum(@PathVariable Long id, @RequestBody @Valid WeaponRequestDto requestDto) {
        var responseDto = this.mapper.toDto(this.weaponService.update(id, requestDto));
        var location = this.urlHelper.getResourceUri(responseDto.getId());

        return ResponseEntity
                .ok()
                .location(location)
                .body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull Object> deleteAlbum(@PathVariable Long id) {
        this.weaponService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}