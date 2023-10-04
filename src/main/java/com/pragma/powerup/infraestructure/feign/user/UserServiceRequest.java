package com.pragma.powerup.infraestructure.feign.user;

import com.pragma.powerup.infraestructure.feign.user.dto.response.user.RoleByUserIdResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceRequest {
    @GetMapping("/role/id/{id}")
    RoleByUserIdResponseDto getRoleByUserId(@PathVariable Long id);
}