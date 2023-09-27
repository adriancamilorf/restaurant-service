package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.infraestructure.security.jwt.JwtProvider;
import com.pragma.powerup.infraestructure.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishHandler dishHandler;
    private final JwtProvider jwtProvider;

    @Operation(summary = "Add a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "dish already exists", content = @Content)
    })
    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveDish(@RequestBody DishRequestDto dishRequestDto, HttpServletRequest request){
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        dishHandler.saveDish(dishRequestDto, userId);
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
    @PreAuthorize("hasAuthority('OWNER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String,String>> updateDishById(@PathVariable Long id, @RequestBody DishRequestUpdateDto dishRequestUpdateDto, HttpServletRequest request){
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        dishHandler.updateDish(id, dishRequestUpdateDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message","Plato actualizado correctamente"));
    }
}
