package walter.duncan.dndwebapi.services.encountermanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;

import walter.duncan.dndwebapi.businessmodels.charactermanagement.*;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterActionRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterParticipantRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterClassEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterRaceEntity;
import walter.duncan.dndwebapi.entities.charactermanagement.CharacterTypeEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterParticipantEntity;
import walter.duncan.dndwebapi.entities.encountermanagement.EncounterState;
import walter.duncan.dndwebapi.entities.usermanagement.UserEntity;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;
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
import static org.mockito.Mockito.mock;
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
        // Arrange
        var requestDto = mock(EncounterRequestDto.class);
        var jwt = mock(Jwt.class);
        var entity = encounterEntityBuilder.build();
        var userEntity = mock(UserEntity.class);

        when(encounterRepository.save(any())).thenReturn(entity);
        when(userService.resolveUser(jwt)).thenReturn(userEntity);

        // Act
        var result = this.encounterService.create(requestDto, jwt);

        // Assert
        assertNotNull(result);
        verify(encounterRepository, times(1)).save(any());
    }

    @Test
    void addParticipant_shouldReturnEncounterModelWithParticipantAdded_whenRequestHasNoBusinessRuleViolations() {
        // Arrange
        var encounterIdOwnedByUser = 1L;
        var characterIdOwnedByUser = 1L;
        var requestDto = mock(EncounterParticipantRequestDto.class);
        var jwt = mock(Jwt.class);
        var entity = encounterEntityBuilder.withState(EncounterState.GATHERING_PARTICIPANTS).build();
        var userEntity = mock(UserEntity.class);
        var characterModel = mock(CharacterModel.class);

        // Here we really see the power of mocks! We don't have to manually instantiate all classes with actual data, only necessary properties are stubbed.
        when(requestDto.characterId()).thenReturn(characterIdOwnedByUser);
        when(characterModel.getId()).thenReturn(characterIdOwnedByUser);
        when(characterModel.getAlignment()).thenReturn(CharacterAlignment.CHAOTIC_EVIL);
        when(characterModel.getType()).thenReturn(mock(CharacterTypeModel.class));
        when(characterModel.getRace()).thenReturn(mock(CharacterRaceModel.class));
        when(characterModel.getCharacterClass()).thenReturn(mock(CharacterClassModel.class));

        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterIdOwnedByUser, userEntity)).thenReturn(Optional.of(entity));
        when(encounterRepository.existsByParticipantAndState(eq(characterIdOwnedByUser), any())).thenReturn(false); // Can't mix matching and actual values, we have to use eq() around actual values
        when(characterService.findByIdForUser(characterIdOwnedByUser, jwt)).thenReturn(characterModel);
        when(encounterRepository.save(any())).thenReturn(entity);

        // Act
        var result = encounterService.addParticipant(encounterIdOwnedByUser, requestDto, jwt);

        // Assert
        assertNotNull(result);
        assertTrue(result.getParticipants().stream().anyMatch(p ->
                p.getCharacter().getId() == characterIdOwnedByUser
        ));
        verify(encounterRepository, times(1)).findByIdAndUser(encounterIdOwnedByUser, userEntity);
        verify(encounterRepository, times(1)).existsByParticipantAndState(eq(characterIdOwnedByUser), any());
        verify(characterService, times(1)).findByIdForUser(characterIdOwnedByUser, jwt);
        verify(encounterRepository, times(1)).save(any());
    }

    @Test
    void addParticipant_shouldThrowBusinessRuleViolationException_whenCharacterIsActiveInAnotherEncounter() {
        // Arrange
        var encounterIdOwnedByUser = 1L;
        var characterIdOwnedByUser = 1L;
        var requestDto = mock(EncounterParticipantRequestDto.class);
        var jwt = mock(Jwt.class);
        var entity = encounterEntityBuilder.withState(EncounterState.GATHERING_PARTICIPANTS).build();
        var userEntity = mock(UserEntity.class);
        var characterModel = mock(CharacterModel.class);

        when(requestDto.characterId()).thenReturn(characterIdOwnedByUser);
        when(characterModel.getId()).thenReturn(characterIdOwnedByUser);

        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterIdOwnedByUser, userEntity)).thenReturn(Optional.of(entity));
        when(encounterRepository.existsByParticipantAndState(eq(characterIdOwnedByUser), any())).thenReturn(true);
        when(characterService.findByIdForUser(characterIdOwnedByUser, jwt)).thenReturn(characterModel);

        // Act
        assertThrows(
                BusinessRuleViolationException.class,
                () -> encounterService.addParticipant(encounterIdOwnedByUser, requestDto, jwt)
        );
    }

    @Test
    void addParticipant_shouldThrowResourceNotFoundException_whenUserDoesNotOwnEncounter() {
        // Arrange
        var encounterIdNotOwnedByUser = 5L;
        var requestDto = mock(EncounterParticipantRequestDto.class);
        var jwt = mock(Jwt.class);
        var userEntity = mock(UserEntity.class);

        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterIdNotOwnedByUser, userEntity)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> encounterService.addParticipant(encounterIdNotOwnedByUser, requestDto, jwt)
        );
    }

    @Test
    void addParticipant_shouldThrowResourceNotFoundException_whenUserDoesNotOwnCharacter() {
        // Arrange
        var encounterIdOwnedByUser = 1L;
        var characterIdNotOwnedByUser = 100L;
        var requestDto = mock(EncounterParticipantRequestDto.class);
        var entity = encounterEntityBuilder.withState(EncounterState.GATHERING_PARTICIPANTS).build();
        var jwt = mock(Jwt.class);
        var userEntity = mock(UserEntity.class);

        when(requestDto.characterId()).thenReturn(characterIdNotOwnedByUser);

        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterIdOwnedByUser, userEntity)).thenReturn(Optional.of(entity));
        when(characterService.findByIdForUser(characterIdNotOwnedByUser, jwt)).thenThrow(new ResourceNotFoundException("Placeholder"));

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> encounterService.addParticipant(encounterIdOwnedByUser, requestDto, jwt)
        );
    }

    @Test
    void performAction_shouldReturnEncounterModelWithStateInProgress_whenActionIsStartAndStateIsGatheringParticipants() {
        // Arrange
        var encounterId = 1L;
        var requestDto = mock(EncounterActionRequestDto.class);
        var jwt = mock(Jwt.class);
        var entity = encounterEntityBuilder.withMockParticipants(3L).withState(EncounterState.GATHERING_PARTICIPANTS).build();
        var userEntity = mock(UserEntity.class);

        when(requestDto.action()).thenReturn("start");
        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterId, userEntity)).thenReturn(Optional.of(entity));
        when(encounterRepository.save(any())).thenReturn(entity);

        // Act
        var result = encounterService.performAction(encounterId, requestDto, jwt);

        // Assert
        assertNotNull(result);
        assertEquals(walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterState.IN_PROGRESS, result.getState());
        verify(encounterRepository).save(any(EncounterEntity.class));
    }

    @Test
    void performAction_shouldReturnEncounterModelWithNextCurrentActor_whenActionIsAdvanceTurnAndStateIsInProgress() {
        // Arrange
        var encounterId = 1L;
        var requestDto = mock(EncounterActionRequestDto.class);
        var jwt = mock(Jwt.class);
        var entity = encounterEntityBuilder.withMockParticipants(3L).withState(EncounterState.IN_PROGRESS).build();
        var userEntity = mock(UserEntity.class);

        when(requestDto.action()).thenReturn("advance-turn");
        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterId, userEntity)).thenReturn(Optional.of(entity));
        when(encounterRepository.save(any())).thenReturn(entity);

        // Act
        var result = encounterService.performAction(encounterId, requestDto, jwt);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCurrentActor());
        assertEquals(walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterState.IN_PROGRESS, result.getState());
        verify(encounterRepository).save(any(EncounterEntity.class));
    }

    @Test
    void performAction_shouldReturnEncounterModelWithStateCompleted_whenActionIsCloseAndStateIsInProgress() {
        // Arrange
        var encounterId = 1L;
        var requestDto = mock(EncounterActionRequestDto.class);
        var jwt = mock(Jwt.class);
        var entity = encounterEntityBuilder.withMockParticipants(3L).withState(EncounterState.IN_PROGRESS).build();
        var userEntity = mock(UserEntity.class);

        when(requestDto.action()).thenReturn("close");
        when(userService.resolveUser(jwt)).thenReturn(userEntity);
        when(encounterRepository.findByIdAndUser(encounterId, userEntity)).thenReturn(Optional.of(entity));
        when(encounterRepository.save(any())).thenReturn(entity);

        // Act
        var result = encounterService.performAction(encounterId, requestDto, jwt);

        // Assert
        assertNotNull(result);
        assertEquals(walter.duncan.dndwebapi.businessmodels.encountermanagement.EncounterState.COMPLETED, result.getState());
        verify(encounterRepository).save(any(EncounterEntity.class));
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
        private final Set<EncounterParticipantEntity> participants = new HashSet<>();

        public EncounterEntity build() {
            var entity = new EncounterEntity();
            entity.setId(id);
            entity.setState(state);
            entity.setParticipants(participants);

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

        public EncounterEntityBuilder withMockParticipants(Long amount) {
            for (Long i = 0L; i < amount; i++) {
                var participant = mock(EncounterParticipantEntity.class);
                var character = mock(CharacterEntity.class);
                when(character.getId()).thenReturn(i);
                when(character.getAlignment()).thenReturn(walter.duncan.dndwebapi.entities.charactermanagement.CharacterAlignment.CHAOTIC_EVIL);
                when(character.getType()).thenReturn(mock(CharacterTypeEntity.class));
                when(character.getRace()).thenReturn(mock(CharacterRaceEntity.class));
                when(character.getCharacterClass()).thenReturn(mock(CharacterClassEntity.class));
                when(participant.getCharacter()).thenReturn(character);

                participants.add(participant);
            }
            return this;
        }
    }
}