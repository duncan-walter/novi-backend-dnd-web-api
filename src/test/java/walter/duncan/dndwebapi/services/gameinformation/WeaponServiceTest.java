package walter.duncan.dndwebapi.services.gameinformation;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import walter.duncan.dndwebapi.businessmodels.gameinformation.WeaponModel;
import walter.duncan.dndwebapi.entities.gameinformation.WeaponEntity;
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
    void findById_shouldThrowResourceNotFoundException_whenWeaponDoesNotExists() {
        // Arrange
        var weaponId = 1337L;

        when(weaponRepository.findById(weaponId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> weaponService.findById(weaponId)
        );
    }
}