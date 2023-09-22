package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishHandler dishHandler;

    @Operation(summary = "Add a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "dish already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveDish(@RequestBody DishRequestDto dishRequestDto){
        dishHandler.saveDish(dishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message","Plato creado correctamente"));
    }

    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dish updated",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String,String>> updateDishById(@PathVariable Long id, @RequestBody DishRequestUpdateDto dishRequestUpdateDto){
        dishHandler.updateDish(id, dishRequestUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message","Plato actualizado correctamente"));
    }
}
