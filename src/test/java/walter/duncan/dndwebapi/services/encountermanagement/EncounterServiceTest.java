package walter.duncan.dndwebapi.services.encountermanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterModel;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.mappers.encountermanagement.EncounterPersistenceMapper;
import walter.duncan.dndwebapi.repositories.encountermanagement.EncounterRepository;
import walter.duncan.dndwebapi.services.charactermanagement.CharacterService;
import walter.duncan.dndwebapi.services.usermanagement.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

// DISCLAIMER: Tests are written less DRY on purpose so each scenario is crystal clear and easy to change independently of each-other.
@ExtendWith(MockitoExtension.class)
public class EncounterServiceTest {
    @Mock
    private EncounterRepository encounterRepository;

    @Mock
    private CharacterService characterService;

    @Mock
    private UserService userService;

    @Mock
    private EncounterPersistenceMapper encounterPersistenceMapper;

    @InjectMocks
    private EncounterService encounterService;

    @Test
    void findAll_shouldReturnSetOfAllEncounterModels() {
        // Arrange
        var entities = Set.of(mock(EncounterEntity.class), mock(EncounterEntity.class));
        var models = Set.of(mock(EncounterModel.class), mock(EncounterModel.class));

        when(encounterRepository.findAll()).thenReturn(new ArrayList<>(entities));
        when(encounterPersistenceMapper.toModels(entities)).thenReturn(new HashSet<>(models));

        // Act
        var result = encounterService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.containsAll(models));
        verify(encounterRepository, times(1)).findAll();
        verify(encounterPersistenceMapper, times(1)).toModels(entities);
    }

    @Test
    void findAll_shouldReturnEmptySet_whenNoEncountersExist() {

    }

    @Test
    void findById_shouldReturnEncounterModel_whenEncounterExists() {

    }

    @Test
    void findById_shouldThrowResourceNotFoundException_whenEncounterDoesNotExist() {

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
}