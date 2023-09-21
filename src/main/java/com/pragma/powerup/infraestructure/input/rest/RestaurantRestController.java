package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantHandler.saveRestaurant(restaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message","Restaurante creado correctamente"));
    }

}
