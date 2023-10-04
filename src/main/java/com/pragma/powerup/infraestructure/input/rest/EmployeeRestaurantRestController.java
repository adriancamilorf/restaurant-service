package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.EmployeeRestaurantDto;
import com.pragma.powerup.application.handler.IEmployeeRestaurantHandler;
import com.pragma.powerup.infraestructure.security.jwt.JwtProvider;
import com.pragma.powerup.infraestructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestaurantRestController {
    private final IEmployeeRestaurantHandler employeeRestaurantHandler;
    private final JwtProvider jwtProvider;

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveEmployeeRestaurant(@RequestBody EmployeeRestaurantDto employeeRestaurantDto, HttpServletRequest request){
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        employeeRestaurantHandler.saveEmployeeRestaurant(employeeRestaurantDto, userId );
        return ResponseEntity.ok(Collections.singletonMap("message", "Empleado registrado en el restaurante con exito."));
    }

}
