package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "restaurant already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantHandler.saveRestaurant(restaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message","Restaurante creado correctamente"));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<RestaurantListResponseDto>> listRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurantsOrderedByName(page,size));
    }

}
