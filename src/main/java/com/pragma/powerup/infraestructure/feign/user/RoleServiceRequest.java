package com.pragma.powerup.infraestructure.feign.user;

import com.pragma.powerup.infraestructure.feign.user.dto.response.role.RoleByUserIdResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/role")
public interface RoleServiceRequest {
    @GetMapping("/id/{id}")
    RoleByUserIdResponseDto getRoleByUserId(@PathVariable Long id);
}