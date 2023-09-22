package com.pragma.powerup.infraestructure.input.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RestaurantRestController.class)
class RestaurantRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRestaurantHandler restaurantHandler;

    @Test
    void testSaveRestaurant() throws Exception {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName("Restaurante de Prueba");
        Mockito.doNothing().when(restaurantHandler).saveRestaurant(Mockito.any(RestaurantDto.class));
        mockMvc.perform(post("/restaurant/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(restaurantDto)))
                .andExpect(status().isCreated());
    }
}
