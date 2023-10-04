package com.pragma.powerup.infraestructure.input.rest;

import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.infraestructure.security.jwt.JwtProvider;
import com.pragma.powerup.infraestructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;
    private final JwtProvider jwtProvider;

    @GetMapping("/list")
    public ResponseEntity<Page<OrderListResponseDto>> listOrders(
            @RequestParam String state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ){
        String token = JwtUtils.extractJwtToken(request);
        Long userId = jwtProvider.getUserIdFromToken(token);
        return ResponseEntity.ok(orderHandler.getByStateAndRestaurantId(state,userId,page,size));
    }

}
