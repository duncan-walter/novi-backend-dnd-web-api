package walter.duncan.dndwebapi.controllers.encountermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterResponseDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class EncounterControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getById_shouldReturnEncounterResponseDto_whenEncounterExists() throws Exception {
        // Arrange
        var encounterId = 1L;

        // Act
        var result = mockMvc.perform(get("/encounters/{id}", encounterId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var encounterResponseDto = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                EncounterResponseDto.class
        );

        // Assert
        assertEquals(encounterId, encounterResponseDto.id());
        assertNotNull(encounterResponseDto.participants());
        assertEquals(3,  encounterResponseDto.participants().size());
        assertEquals("Battle in the Mines of Moria", encounterResponseDto.title());
        assertTrue(encounterResponseDto.description().contains("Moria")); // Using contains to prevent long description string checks.
    }

    @Test
    void getById_shouldReturnNotFound_whenEncounterDoesNotExist() throws Exception {
        // Arrange
        var encounterId = 1337L;

        // Act & Assert
        mockMvc.perform(get("/encounters/{id}", encounterId)).andExpect(status().isNotFound());
    }

    @Test
    void get_shouldReturnAllEncounterResponseDtos_whenEncountersExist() throws Exception {
        // Arrange & Act
        var result = mockMvc.perform(get("/encounters"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var encounterResponseDtos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                EncounterResponseDto[].class
        );

        // Assert
        assertNotNull(encounterResponseDtos);
        assertEquals(2, encounterResponseDtos.length);
    }

    @Test
    void create_shouldCreateAndReturnEncounterResponseDtoWithLocationHeader_whenRequestIsValid() throws Exception {

    }

    @Test
    void create_shouldReturnBadRequestWithProblemDetails_whenRequestHasMissingRequiredProperties() throws Exception {

    }

    @Test
    void create_shouldReturnBadRequestWithProblemDetails_whenRequestViolatesLengthConstraint() throws Exception {

    }

    @Test
    void addParticipant_shouldReturnEncounterResponseDtoWithLocationHeader_whenEncounterAndCharacterExist() throws Exception {

    }

    @Test
    void addParticipant_shouldReturnNotFound_whenEncounterDoesNotExist() throws Exception {

    }

    @Test
    void addParticipant_shouldReturnNotFound_whenCharacterDoesNotExist() throws Exception {

    }

    @Test
    void action_shouldReturnEncounterResponseDtoWithStateInProgress_whenActionIsStart() throws Exception {

    }

    @Test
    void action_shouldReturnEncounterResponseDtoWithNextCurrentActor_whenActionIsAdvanceTurn() throws Exception {

    }

    @Test
    void action_shouldReturnEncounterResponseDtoWithStateCompleted_whenActionIsStop() throws Exception {

    }

    @Test
    void action_shouldReturnUnprocessableContentWithProblemDetails_whenActionIsInvalid() throws Exception {

    }
}