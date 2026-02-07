package walter.duncan.dndwebapi.controllers.encountermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

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

    }

    @Test
    void getById_shouldReturnNotFound_whenEncounterDoesNotExist() throws Exception {

    }

    @Test
    void get_shouldReturnAllEncounterResponseDtos_whenEncountersExist() throws Exception {

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