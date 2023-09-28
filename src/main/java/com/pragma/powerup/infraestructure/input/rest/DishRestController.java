package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.infraestructure.security.jwt.JwtProvider;
import com.pragma.powerup.infraestructure.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveDish(@RequestBody DishRequestDto dishRequestDto, HttpServletRequest request){
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        dishHandler.saveDish(dishRequestDto, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("message","Plato creado correctamente"));
    }

    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Status updated",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
    })
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<Map<String,String>> updateDishStatusById(@PathVariable Long id, HttpServletRequest request){
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        dishHandler.updateStatus(id, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("message","Estado actualizado correctamente"));
    }

    @GetMapping("/list/{restaurantName}")
    public ResponseEntity<Page<DishResponseDto>> listRestaurants(
            @RequestParam Long categoryId,
            @PathVariable String restaurantName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(dishHandler.findByCategoryAndRestaurantName(categoryId,restaurantName,page,size));
    }
}
