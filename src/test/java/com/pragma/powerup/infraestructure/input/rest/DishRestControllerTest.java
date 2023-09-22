package com.pragma.powerup.infraestructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.handler.IDishHandler;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

@WebMvcTest(DishRestController.class)
class DishRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDishHandler dishHandler;

    @Test
    void testSaveDish() throws Exception {
        DishRequestDto dishRequestDto = new DishRequestDto();
        dishRequestDto.setName("Plato de Prueba");
        Mockito.doNothing().when(dishHandler).saveDish(Mockito.any(DishRequestDto.class));

        mockMvc.perform(post("/dish/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dishRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateDishById() throws Exception {
        DishRequestUpdateDto dishRequestUpdateDto = new DishRequestUpdateDto();
        Mockito.doNothing().when(dishHandler).saveDish(Mockito.any(DishRequestDto.class));

        mockMvc.perform(put("/dish/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dishRequestUpdateDto)))
                .andExpect(status().isCreated());
    }
}
