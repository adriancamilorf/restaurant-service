package com.pragma.powerup.infraestructure.utils;

import javax.servlet.http.HttpServletRequest;

public class JwtUtils {
    public static String extractJwtToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

}
