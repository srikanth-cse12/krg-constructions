package com.krg.construction.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class WebSecurityHeadersFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Protect against MIME-type sniffing
        response.setHeader("X-Content-Type-Options", "nosniff");
        // Protect against clickjacking
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        // Legacy cross-site scripting filter
        response.setHeader("X-XSS-Protection", "1; mode=block");
        // Strict referrer policy
        response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        // Restrict sensitive browser permissions
        response.setHeader("Permissions-Policy", "camera=(), microphone=(), geolocation=()");

        // Strict-Transport-Security if secure or forwarded from SSL termination
        String proto = request.getHeader("X-Forwarded-Proto");
        if (request.isSecure() || "https".equalsIgnoreCase(proto)) {
            response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        }

        filterChain.doFilter(request, response);
    }
}
