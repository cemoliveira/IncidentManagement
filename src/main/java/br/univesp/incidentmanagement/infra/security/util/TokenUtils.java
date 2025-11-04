package br.univesp.incidentmanagement.infra.security.util;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtils {
    private TokenUtils() {}

    public static String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}