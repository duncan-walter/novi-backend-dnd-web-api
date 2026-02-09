package walter.duncan.dndwebapi.services.encountermanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterState;
import walter.duncan.dndwebapi.exceptions.ResourceNotFoundException;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterClassPersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterPersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterRacePersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.CharacterTypePersistenceMapper;
import walter.duncan.dndwebapi.mappers.charactermanagement.inventory.CharacterInventoryItemPersistenceMapper;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterJoinRequestPersistenceMapper;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterParticipantPersistenceMapper;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterRepository;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterService;
import walter.duncan.dndwebapi.services.usermanagement.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

// DISCLAIMER: Tests are written less DRY on purpose so each scenario is crystal clear and easy to change independently of each-other.
@ExtendWith(MockitoExtension.class)
public class EncounterServiceTest {
    @Mock
    public EncounterRepository encounterRepository;

    @Mock
    public CharacterService characterService;

    @Mock
    public UserService userService;

    // Injected in @BeforeEach
    private EncounterEntityBuilder encounterEntityBuilder;

    // Injected in @BeforeEach
    public EncounterService encounterService;

    @BeforeEach
    public void setUp() {
        this.encounterEntityBuilder = new EncounterEntityBuilder();
        // I was not able to create stubs using a @Mock of EncounterPersistenceMapper.
        // So instead we inject the real implementation, this is fine because the results are predictable as it just contains mapping.
        this.encounterService = new EncounterService(
                this.encounterRepository,
                this.characterService,
                createEncounterPersistenceMapper(),
                this.userService
        );
    }

    @Test
    void findAll_shouldReturnSetOfAllEncounterModels() {
        // Arrange
        var entities = List.of(encounterEntityBuilder.build(), encounterEntityBuilder.build());

        when(encounterRepository.findAll()).thenReturn(entities);

        // Act
        var result = encounterService.findAll();

        // Assert
        assertEquals(entities.size(), result.size());
        verify(encounterRepository, times(1)).findAll();
    }

    @Test
    void findAll_shouldReturnEmptySet_whenNoEncountersExist() {
        // Arrange
        when(encounterRepository.findAll()).thenReturn(List.of());

        // Act
        var result = encounterService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(encounterRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnEncounterModel_whenEncounterExists() {
        // Arrange
        var encounterId = 1L;
        var entity = encounterEntityBuilder.withId(encounterId).build();

        when(encounterRepository.findById(encounterId)).thenReturn(Optional.of(entity));

        // Act
        var result = encounterService.findById(encounterId);

        // Assert
        assertEquals(encounterId, result.getId());
        verify(encounterRepository, times(1)).findById(encounterId);
    }

    @Test
    void findById_shouldThrowResourceNotFoundException_whenEncounterDoesNotExist() {
        // Arrange
        var encounterId = 1337L;

        when(encounterRepository.findById(encounterId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> encounterService.findById(encounterId)
        );
    }

    @Test
    void create_shouldReturnEncounterModel_whenRequestHasNoBusinessRuleViolations() {

    }

    @Test
    void create_shouldThrowBusinessRuleViolationException_when1() {

    }

    @Test
    void create_shouldThrowBusinessRuleViolationException_when2() {

    }

    @Test
    void addParticipant_shouldReturnEncounterModelWithParticipantAdded_whenRequestHasNoBusinessRuleViolations() {

    }

    @Test
    void performAction_shouldReturnEncounterModelWithStateInProgress_whenActionIsStart() {

    }

    @Test
    void performAction_shouldReturnEncounterModelWithNextCurrentActor_whenActionIsAdvanceTurn() {

    }

    @Test
    void performAction_shouldReturnEncounterModelWithStateCompleted_whenActionIsClose() {

    }

    private static EncounterPersistenceMapper createEncounterPersistenceMapper() {
        var characterMapper = new CharacterPersistenceMapper(
                new CharacterTypePersistenceMapper(),
                new CharacterRacePersistenceMapper(),
                new CharacterClassPersistenceMapper(),
                new CharacterInventoryItemPersistenceMapper()
        );

        return new EncounterPersistenceMapper(
                new EncounterJoinRequestPersistenceMapper(characterMapper),
                new EncounterParticipantPersistenceMapper(characterMapper)
        );
    }

    private static class EncounterEntityBuilder {
        private Long id;
        private EncounterState state = EncounterState.IN_PROGRESS;

        public EncounterEntity build() {
            var entity = new EncounterEntity();
            entity.setId(id);
            entity.setState(state);

            reset();
            return entity;
        }

        public EncounterEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EncounterEntityBuilder withState(EncounterState state) {
            this.state = state;
            return this;
        }
    }
}