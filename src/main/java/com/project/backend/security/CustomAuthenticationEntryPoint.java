package com.project.backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

/**
 * 인증 진입점
 * <p>
 * 인증되지 않은 사용자가 보호된 리소스에 액세스하려고 할 때 (401 Unauthorized)
 */
@Log4j2
public class CustomAuthenticationEntryPoint
        implements org.springframework.security.web.AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Authentication Entry Point {}", request.getRequestURI());
        log.error(authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getLocalizedMessage());
    }
}
