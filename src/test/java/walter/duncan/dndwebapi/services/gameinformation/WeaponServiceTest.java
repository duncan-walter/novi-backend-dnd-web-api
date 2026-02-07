package walter.duncan.dndwebapi.services.gameinformation;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.inventory.CharacterInventoryItemType;
import walter.duncan.dndwebapi.entities.gameinformation.WeaponEntity;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;
import walter.duncan.dndwebapi.exceptions.ResourceNotFoundException;
import walter.duncan.dndwebapi.mappers.gameinformation.weapon.WeaponPersistenceMapper;
import walter.duncan.dndwebapi.repositories.charactermanagement.CharacterRepository;
import walter.duncan.dndwebapi.repositories.gameinformation.WeaponRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeaponServiceTest {
    @Mock
    private WeaponRepository weaponRepository;

    @Mock
    private WeaponPersistenceMapper weaponPersistenceMapper;

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private WeaponService weaponService;

    @Test
    void findAll_shouldReturnListOfAllWeaponModels() {
        // Arrange
        var entities = List.of(mock(WeaponEntity.class), mock(WeaponEntity.class));
        var models = List.of(mock(WeaponModel.class), mock(WeaponModel.class));

        when(weaponRepository.findAll()).thenReturn(entities);
        when(this.weaponPersistenceMapper.toModels(entities)).thenReturn(models);

        // Act
        var result = weaponService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.containsAll(models));
        verify(weaponRepository, times(1)).findAll();
        verify(weaponPersistenceMapper, times(1)).toModels(entities);
    }

    @Test
    void findAll_shouldReturnEmptyList_whenNoWeaponsExist() {
        // Arrange
        List<WeaponEntity> entities = List.of();
        when(weaponRepository.findAll()).thenReturn(entities);

        // Act
        var result = weaponService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(weaponRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnWeaponModel_whenWeaponExists() {
        // Arrange
        var weaponId = 1L;
        var entity =  mock(WeaponEntity.class);
        var model = mock(WeaponModel.class);

        when(weaponRepository.findById(weaponId)).thenReturn(Optional.of(entity));
        when(weaponPersistenceMapper.toModel(entity)).thenReturn(model);

        // Act
        var result = weaponService.findById(weaponId);

        // Assert
        assertSame(model, result);
        verify(weaponRepository, times(1)).findById(weaponId);
        verify(weaponPersistenceMapper, times(1)).toModel(entity);
    }

    @Test
    void findById_shouldThrowResourceNotFoundException_whenWeaponDoesNotExist() {
        // Arrange
        var weaponId = 1337L;

        when(weaponRepository.findById(weaponId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> weaponService.findById(weaponId)
        );
    }

    @Test
    void findByIds_shouldReturnListOfAllWeaponModels() {
        // Arrange
        var weaponIds = List.of(1L, 2L);
        var entities = List.of(mock(WeaponEntity.class), mock(WeaponEntity.class));
        var models = List.of(mock(WeaponModel.class), mock(WeaponModel.class));

        when(entities.getFirst().getId()).thenReturn(weaponIds.getFirst());
        when(entities.get(1).getId()).thenReturn(weaponIds.get(1));
        when(weaponRepository.findAllById(weaponIds)).thenReturn(entities);
        when(weaponPersistenceMapper.toModels(entities)).thenReturn(models);

        // Act
        var result = weaponService.findByIds(weaponIds);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.containsAll(models));
        verify(weaponRepository, times(1)).findAllById(weaponIds);
        verify(weaponPersistenceMapper, times(1)).toModels(entities);
    }

    @Test
    void findByIds_shouldThrowResourceNotFoundException_whenAWeaponDoesNotExist() {
        // Arrange
        var weaponIds = List.of(1L, 2L, 3L);
        var entities = List.of(mock(WeaponEntity.class), mock(WeaponEntity.class));

        when(entities.getFirst().getId()).thenReturn(weaponIds.getFirst());
        when(entities.get(1).getId()).thenReturn(weaponIds.get(1));
        when(weaponRepository.findAllById(weaponIds)).thenReturn(entities);

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> weaponService.findByIds(weaponIds)
        );
    }

    @Test
    void create_shouldReturnWeaponModel_whenRequestHasNoBusinessRuleViolations() {
        // Arrange
        var requestDto = mock(WeaponRequestDto.class);
        when(requestDto.getName()).thenReturn("Light Crossbow");
        when(requestDto.getDescription()).thenReturn("A mechanical ranged weapon that fires bolts with high accuracy and stopping power.");
        when(requestDto.getValueInCopperPieces()).thenReturn(2500L);
        when(requestDto.getWeightInLbs()).thenReturn(5.0);
        when(requestDto.getDamageDice()).thenReturn("1d8");
        when(requestDto.getDamageType()).thenReturn("piercing");
        when(requestDto.getRangeNormal()).thenReturn(80);
        when(requestDto.getRangeLong()).thenReturn(320);
        when(requestDto.getIsTwoHanded()).thenReturn(true);

        var entity = mock(WeaponEntity.class);
        var model = mock(WeaponModel.class);

        when(weaponPersistenceMapper.toEntity(any(WeaponModel.class))).thenReturn(entity);
        when(weaponRepository.save(entity)).thenReturn(entity);
        when(weaponPersistenceMapper.toModel(entity)).thenReturn(model);

        // Act
        var result = weaponService.create(requestDto);

        // Assert
        assertSame(model, result);
        verify(weaponPersistenceMapper, times(1)).toEntity(any(WeaponModel.class));
        verify(weaponRepository, times(1)).save(entity);
        verify(weaponPersistenceMapper, times(1)).toModel(entity);
    }

    @Test
    void create_shouldThrowBusinessRuleViolationException_whenNormalRangeExceedsLongRange() {
        // Arrange
        var requestDto = mock(WeaponRequestDto.class);
        when(requestDto.getName()).thenReturn("Light Crossbow");
        when(requestDto.getDescription()).thenReturn("A mechanical ranged weapon that fires bolts with high accuracy and stopping power.");
        when(requestDto.getValueInCopperPieces()).thenReturn(2500L);
        when(requestDto.getWeightInLbs()).thenReturn(5.0);
        when(requestDto.getDamageDice()).thenReturn("1d8");
        when(requestDto.getDamageType()).thenReturn("piercing");
        when(requestDto.getRangeNormal()).thenReturn(320);
        when(requestDto.getRangeLong()).thenReturn(80);
        when(requestDto.getIsTwoHanded()).thenReturn(true);

        // Act & Assert
        assertThrows(
                BusinessRuleViolationException.class,
                () -> weaponService.create(requestDto)
        );
    }

    @Test
    void update_shouldReturnWeaponModel_whenRequestHasNoBusinessRuleViolations() {
        // Arrange
        var weaponId = 1L;
        var requestDto = mock(WeaponRequestDto.class);
        when(requestDto.getName()).thenReturn("Longsword +1");
        when(requestDto.getDescription()).thenReturn("A finely crafted longsword imbued with minor enchantments that enhance its accuracy and lethality.");
        when(requestDto.getValueInCopperPieces()).thenReturn(30000L);
        when(requestDto.getWeightInLbs()).thenReturn(3.0);
        when(requestDto.getDamageDice()).thenReturn("1d8");
        when(requestDto.getDamageType()).thenReturn("piercing");
        when(requestDto.getRangeNormal()).thenReturn(null);
        when(requestDto.getRangeLong()).thenReturn(null);
        when(requestDto.getIsTwoHanded()).thenReturn(false);

        var persistedEntity = mock(WeaponEntity.class);
        var model = mock(WeaponModel.class);

        when(weaponRepository.findById(weaponId)).thenReturn(Optional.of(persistedEntity));
        when(weaponRepository.save(persistedEntity)).thenReturn(persistedEntity);
        when(weaponPersistenceMapper.toModel(persistedEntity)).thenReturn(model);

        // Act
        var result = weaponService.update(weaponId, requestDto);

        // Assert
        assertSame(model, result);
        verify(weaponPersistenceMapper, times(1)).updateEntityFromModel(any(WeaponModel.class), eq(persistedEntity));
        verify(weaponRepository, times(1)).save(persistedEntity);
        verify(weaponPersistenceMapper, times(1)).toModel(persistedEntity);
    }

    @Test
    void update_shouldReturnWeaponModel_whenLongRangeIsLessThanNormalRange() {
        // Arrange
        var weaponId = 1L;
        var requestDto = mock(WeaponRequestDto.class);
        when(requestDto.getName()).thenReturn("Light Crossbow");
        when(requestDto.getDescription()).thenReturn("A mechanical ranged weapon that fires bolts with high accuracy and stopping power.");
        when(requestDto.getValueInCopperPieces()).thenReturn(2500L);
        when(requestDto.getWeightInLbs()).thenReturn(5.0);
        when(requestDto.getDamageDice()).thenReturn("1d8");
        when(requestDto.getDamageType()).thenReturn("piercing");
        when(requestDto.getRangeNormal()).thenReturn(320);
        when(requestDto.getRangeLong()).thenReturn(80);
        when(requestDto.getIsTwoHanded()).thenReturn(true);

        var weaponEntity = mock(WeaponEntity.class);
        when(weaponRepository.findById(weaponId)).thenReturn(Optional.of(weaponEntity));

        // Act & Assert
        assertThrows(
                BusinessRuleViolationException.class,
                () -> weaponService.update(weaponId, requestDto)
        );
    }

    @Test
    void delete_shouldDeleteWeapon_whenRequestHasNoBusinessRuleViolations() {
        // Arrange
        var weaponId = 1L;

        when(weaponRepository.existsById(weaponId)).thenReturn(true);
        when(characterRepository.existsInventoryItemReference(weaponId, CharacterInventoryItemType.WEAPON)).thenReturn(false);

        // Act
        weaponService.deleteById(weaponId);

        // Assert
        verify(characterRepository, times(1)).existsInventoryItemReference(weaponId, CharacterInventoryItemType.WEAPON);
        verify(weaponRepository, times(1)).existsById(weaponId);
        verify(weaponRepository, times(1)).deleteById(weaponId);
    }
}