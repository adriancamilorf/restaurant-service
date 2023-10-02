package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.handler.IOrderDishHandler;
import com.pragma.powerup.infraestructure.security.jwt.JwtProvider;
import com.pragma.powerup.infraestructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/OrderDish")
@RequiredArgsConstructor
public class OrderDishRestController {

    private final IOrderDishHandler orderDishHandler;
    private final JwtProvider jwtProvider;

    @PostMapping("/save/{restaurantName}")
    public ResponseEntity<Map<String,String>> saverOrder (@PathVariable String restaurantName, @RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        orderDishHandler.createOrder(userId,restaurantName,orderRequestDto);
        return ResponseEntity.ok(Collections.singletonMap("Message","Pedido realizado con exito"));
    }

}
