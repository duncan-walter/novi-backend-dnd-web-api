package walter.duncan.dndwebapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import walter.duncan.dndwebapi.dtos.gameinformation.weapon.WeaponResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        var responseDto = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                WeaponResponseDto.class
        );

        // Assert
        assertEquals(weaponId, responseDto.getId());
        assertEquals("Longsword", responseDto.getName());
        assertEquals("1d8", responseDto.getDamageDice());
    }

    @Test
    void getById_shouldReturnNotFound_whenWeaponDoesNotExist() throws Exception {
        // Arrange
        var weaponId = 1337L;

        // Act & Assert
        mockMvc.perform(get("/weapons/{id}", weaponId)).andExpect(status().isNotFound());
    }
}