package walter.duncan.dndwebapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponRequestDto;
import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponResponseDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class WeaponControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getById_shouldReturnWeaponResponseDto_whenWeaponExists() throws Exception {
        // Arrange
        var weaponId = 1L;

        // Act
        var result = mockMvc.perform(get("/weapons/{id}", weaponId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var weaponResponseDto = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                WeaponResponseDto.class
        );

        // Assert
        assertEquals(weaponId, weaponResponseDto.getId());
        assertEquals("Longsword", weaponResponseDto.getName());
        assertEquals("1d8", weaponResponseDto.getDamageDice());
    }

    @Test
    void getById_shouldReturnNotFound_whenWeaponDoesNotExist() throws Exception {
        // Arrange
        var weaponId = 1337L;

        // Act & Assert
        mockMvc.perform(get("/weapons/{id}", weaponId)).andExpect(status().isNotFound());
    }

    @Test
    void get_shouldReturnAllWeaponResponseDtos_whenWeaponsExist() throws Exception {
        // Arrange & Act
        var result = mockMvc.perform(get("/weapons"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        var weaponResponseDtos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                WeaponResponseDto[].class
        );

        // Assert
        assertTrue(weaponResponseDtos.length > 0);
    }

    @Test
    void create_shouldCreateAndReturnWeaponResponseDto_whenRequestIsValid() throws Exception {
        // Arrange
        var createWeaponRequestJson = // The request DTOs in this project don't expose setters so we parse a JSON-string through the ObjectMapper instead.
"""
{
    "name": "Light Crossbow",
    "description": "A mechanical ranged weapon that fires bolts with high accuracy and stopping power.",
    "valueInCopperPieces": 2500,
    "weightInLbs": 5.0,
    "damageDice": "1d8",
    "damageType": "piercing",
    "rangeNormal": 80,
    "rangeLong": 320,
    "isTwoHanded": true
}
""";
        var weaponRequestDto = objectMapper.readValue(createWeaponRequestJson, WeaponRequestDto.class);

        // Act
        var result = mockMvc.perform(post("/weapons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(weaponRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("Location"))
                .andReturn();

        var locationHeader = result.getResponse().getHeader("Location");
        var weaponResponseDto = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                WeaponResponseDto.class
        );


        // Assert
        assertNotNull(locationHeader);
        assertTrue(locationHeader.endsWith("/" + weaponResponseDto.getId()));
        assertNotNull(weaponResponseDto.getId());
        assertEquals(weaponRequestDto.getName(), weaponResponseDto.getName());
        assertEquals(weaponRequestDto.getDescription(), weaponResponseDto.getDescription());
        assertEquals(weaponRequestDto.getValueInCopperPieces(), weaponResponseDto.getValueInCopperPieces());
    }

    @Test
    void create_shouldReturnBadRequestWithProblemDetails_whenWhenRequestHasMissingRequiredProperties() throws Exception {
        // Arrange
        var createWeaponRequestWithoutNameJson = // The request DTOs in this project don't expose setters so we parse a JSON-string through the ObjectMapper instead.
"""
{
    "description": "A mechanical ranged weapon that fires bolts with high accuracy and stopping power.",
    "valueInCopperPieces": 2500,
    "weightInLbs": 5.0,
    "damageDice": "1d8",
    "damageType": "piercing",
    "rangeNormal": 80,
    "rangeLong": 320,
    "isTwoHanded": true
}
""";
        var weaponRequestDto = objectMapper.readValue(createWeaponRequestWithoutNameJson, WeaponRequestDto.class);

        // Act & Assert
        mockMvc.perform(post("/weapons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(weaponRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("urn:dnd:api:problem:validation-error"))
                .andExpect(jsonPath("$.detail").value("One or more validation errors occurred."))
                .andExpect(jsonPath("$.errors.validation.name").value("must not be empty"));
    }

    @Test
    void create_shouldReturnUnprocessableContentWithProblemDetails_whenRequestContainsBusinessRuleViolations() throws Exception {
        // Arrange
        var createWeaponRequestWithBusinessRuleViolationJson = // The request DTOs in this project don't expose setters so we parse a JSON-string through the ObjectMapper instead.
"""
{
    "name": "Light Crossbow",
    "description": "A mechanical ranged weapon that fires bolts with high accuracy and stopping power.",
    "valueInCopperPieces": 2500,
    "weightInLbs": 5.0,
    "damageDice": "1d8",
    "damageType": "piercing",
    "rangeNormal": 320,
    "rangeLong": 80,
    "isTwoHanded": true
}
""";
        var weaponRequestDto = objectMapper.readValue(createWeaponRequestWithBusinessRuleViolationJson, WeaponRequestDto.class);

        // Act & Assert
        mockMvc.perform(post("/weapons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(weaponRequestDto)))
                .andExpect(status().isUnprocessableContent())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("urn:dnd:api:problem:business-rule-violation"))
                .andExpect(jsonPath("$.detail").value("A business rule was violated."))
                .andExpect(jsonPath("$.errors.businessRuleViolation.WEAPON_LONG_RANGE_LESS_THAN_NORMAL_RANGE")
                        .value("Weapon long range cannot be less than normal range."));
    }
}