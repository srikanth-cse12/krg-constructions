package com.krg.construction.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoResourceFoundException.class)
    public Object handleNoResourceFound(NoResourceFoundException ex, HttpServletRequest request, HttpServletResponse response, Model model) {
        log.warn("Resource not found [{} {}]: {}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        if (request.getRequestURI() != null && request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", 404,
                "error", "Not Found",
                "message", "Requested API endpoint not found."
            ));
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("statusCode", 404);
        model.addAttribute("errorTitle", "Page Not Found");
        model.addAttribute("errorMessage", "The page or resource you are looking for does not exist or may have moved.");
        return "error";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Object handleResponseStatus(ResponseStatusException ex, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        if (status == null) status = HttpStatus.INTERNAL_SERVER_ERROR;

        log.warn("Response status exception [{} {}]: {}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        if (request.getRequestURI() != null && request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(status).body(Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", ex.getReason() != null ? ex.getReason() : "An error occurred."
            ));
        }
        response.setStatus(status.value());
        model.addAttribute("statusCode", status.value());
        model.addAttribute("errorTitle", status.getReasonPhrase());
        model.addAttribute("errorMessage", ex.getReason() != null ? ex.getReason() : "An error occurred.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex, HttpServletRequest request, HttpServletResponse response, Model model) {
        log.error("Unhandled exception processing request [{} {}]: {}", 
                request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);

        // For REST endpoints, return structured JSON
        if (request.getRequestURI() != null && request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", 500,
                "error", "Internal Server Error",
                "message", "An unexpected error occurred. Please contact KRG CONSTRUCTIONS directly via WhatsApp (918122895259) or phone (+91 97872 67811)."
            ));
        }

        // For web pages, render friendly branded error page with HTTP 500 status
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("statusCode", 500);
        model.addAttribute("errorTitle", "Something Went Wrong");
        model.addAttribute("errorMessage", "An unexpected server error occurred while processing your request. Please try again or reach out to us directly.");
        return "error";
    }
}
