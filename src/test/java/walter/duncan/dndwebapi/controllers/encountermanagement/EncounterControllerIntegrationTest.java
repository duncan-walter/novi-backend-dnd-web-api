package walter.duncan.dndwebapi.controllers.encountermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterRequestDto;
import walter.duncan.dndwebapi.dtos.encountermanagement.EncounterResponseDto;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
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
        var result = mockMvc.perform(get("/encounters/{id}", encounterId)
                        .with(getJwtToken("6c7abe11-6431-4c6b-b530-91f929c25e61", "Henk the Tank")))
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
        mockMvc.perform(get("/encounters/{id}", encounterId)
                        .with(getJwtToken("6c7abe11-6431-4c6b-b530-91f929c25e61", "Henk the Tank")))
                .andExpect(status().isNotFound());
    }

    @Test
    void get_shouldReturnAllEncounterResponseDtos_whenEncountersExist() throws Exception {
        // Arrange & Act
        var result = mockMvc.perform(get("/encounters")
                        .with(getJwtToken("6c7abe11-6431-4c6b-b530-91f929c25e61", "Henk the Tank")))
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
        // Arrange
        var createEncounterRequestJson = // The request DTOs in this project don't expose setters so we parse a JSON-string through the ObjectMapper instead.
"""
{
    "title": "Ambush at Amon Hen",
    "description": "As the Fellowship camps near the ancient ruins of Amon Hen, the air grows tense. Orcs under the command of Saruman emerge from the forest, arrows flying and blades clashing. Boromir fights valiantly to protect the Hobbits, Aragorn and Legolas race through the trees, and chaos erupts as the Fellowship is torn apart."
}
""";
        var encounterRequestDto = objectMapper.readValue(createEncounterRequestJson, EncounterRequestDto.class);

        // Act
        var result = mockMvc.perform(post("/encounters")
                        .with(getJwtToken("6703dd9d-1872-4292-99a9-21bebbdb9b5c", "Bert the Hurt")) // Internal logic requires a sub and name
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(encounterRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("Location"))
                .andReturn();

        var locationHeader = result.getResponse().getHeader("Location");
        var encounterResponseDto = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                EncounterResponseDto.class
        );

        // Assert
        assertNotNull(locationHeader);
        assertTrue(locationHeader.endsWith("/" + encounterResponseDto.id()));
        assertNotNull(encounterResponseDto.id());
        assertEquals(encounterRequestDto.title(), encounterResponseDto.title());
        assertEquals(encounterRequestDto.description(), encounterResponseDto.description());
    }

    @Test
    void create_shouldReturnBadRequestWithProblemDetails_whenRequestHasMissingRequiredProperties() throws Exception {
        // Arrange
        var createEncounterRequestWithoutTitleJson = // The request DTOs in this project don't expose setters so we parse a JSON-string through the ObjectMapper instead.
"""
{
    "description": "As the Fellowship camps near the ancient ruins of Amon Hen, the air grows tense. Orcs under the command of Saruman emerge from the forest, arrows flying and blades clashing. Boromir fights valiantly to protect the Hobbits, Aragorn and Legolas race through the trees, and chaos erupts as the Fellowship is torn apart."
}
""";
        var encounterRequestDto = objectMapper.readValue(createEncounterRequestWithoutTitleJson, EncounterRequestDto.class);

        // Act & Assert
        mockMvc.perform(post("/encounters")
                        .with(getJwtToken("6703dd9d-1872-4292-99a9-21bebbdb9b5c", "Bert the Hurt"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(encounterRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("urn:dnd:api:problem:validation-error"))
                .andExpect(jsonPath("$.detail").value("One or more validation errors occurred."))
                .andExpect(jsonPath("$.errors.validation.title").value("must not be empty"));
    }

    @Test
    void create_shouldReturnBadRequestWithProblemDetails_whenRequestViolatesLengthConstraint() throws Exception {
        // Arrange
        var createEncounterRequestWithTooLongTitleJson = // The request DTOs in this project don't expose setters so we parse a JSON-string through the ObjectMapper instead.
"""
{
    "title": "I am long, way waaaay too long. If the server accepts me, we have a problem. So go ahead and do not accept me!",
    "description": "As the Fellowship camps near the ancient ruins of Amon Hen, the air grows tense. Orcs under the command of Saruman emerge from the forest, arrows flying and blades clashing. Boromir fights valiantly to protect the Hobbits, Aragorn and Legolas race through the trees, and chaos erupts as the Fellowship is torn apart."
}
""";
        var encounterRequestDto = objectMapper.readValue(createEncounterRequestWithTooLongTitleJson, EncounterRequestDto.class);

        // Act & Assert
        mockMvc.perform(post("/encounters")
                        .with(getJwtToken("6703dd9d-1872-4292-99a9-21bebbdb9b5c", "Bert the Hurt"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(encounterRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("urn:dnd:api:problem:validation-error"))
                .andExpect(jsonPath("$.detail").value("One or more validation errors occurred."))
                .andExpect(jsonPath("$.errors.validation.title", containsString("must be between")));
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

    // addFilters = false had to be removed because some endpoints have a @AuthenticationPrincipal in their signature.
    // This was not injected with addFilters = false.
    // @WithMockUser does not inject a @AuthenticationPrincipal either.
    // So now we use this helper to mock a JWT-token for every request that needs it.
    private JwtRequestPostProcessor getJwtToken(String sub, String preferredUserName) {
        return jwt().jwt(builder -> builder
                .subject(sub)
                .claim("preferred_username", preferredUserName)
        );
    }
}