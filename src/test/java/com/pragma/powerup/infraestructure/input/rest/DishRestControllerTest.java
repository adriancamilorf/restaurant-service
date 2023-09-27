package com.pragma.powerup.infraestructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.infraestructure.security.jwt.JwtProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DishRestController.class)
class DishRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDishHandler dishHandler;

    @MockBean
    private JwtProvider jwtProvider; // Simular la dependencia JwtProvider

    @Test
    @WithMockUser(authorities = "OWNER")
    void testSaveDish() throws Exception {
        DishRequestDto dishRequestDto = new DishRequestDto();
        dishRequestDto.setName("Plato de Prueba");

        Mockito.when(jwtProvider.getUserIdFromToken(Mockito.anyString())).thenReturn(1L); // Simular el comportamiento de JwtProvider
        Mockito.doNothing().when(dishHandler).saveDish(Mockito.any(DishRequestDto.class), Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.post("/dish/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dishRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(authorities = "OWNER")
    void testUpdateDishById() throws Exception {
        DishRequestUpdateDto dishRequestUpdateDto = new DishRequestUpdateDto();

        Mockito.when(jwtProvider.getUserIdFromToken(Mockito.anyString())).thenReturn(1L); // Simular el comportamiento de JwtProvider
        Mockito.doNothing().when(dishHandler).updateDish(Mockito.anyLong(), Mockito.any(DishRequestUpdateDto.class), Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.put("/dish/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dishRequestUpdateDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
