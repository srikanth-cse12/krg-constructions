package com.krg.construction.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public Object handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object statusObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = 500;
        if (statusObj != null) {
            try {
                statusCode = Integer.parseInt(statusObj.toString());
            } catch (NumberFormatException ignored) {}
        }

        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (uri == null) {
            uri = request.getRequestURI();
        }

        log.warn("HTTP error [{}] for URI: {}", statusCode, uri);

        // API endpoints return JSON
        if (uri != null && uri.startsWith("/api/")) {
            HttpStatus status = HttpStatus.resolve(statusCode);
            if (status == null) status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(Map.of(
                "status", statusCode,
                "error", status.getReasonPhrase(),
                "path", uri,
                "message", statusCode == 404 ? "Requested resource not found." : "An error occurred processing your request."
            ));
        }

        response.setStatus(statusCode);
        model.addAttribute("statusCode", statusCode);
        if (statusCode == 404) {
            model.addAttribute("errorTitle", "Page Not Found");
            model.addAttribute("errorMessage", "The page or resource you are looking for does not exist or may have moved.");
        } else if (statusCode == 403) {
            model.addAttribute("errorTitle", "Access Denied");
            model.addAttribute("errorMessage", "You do not have permission to access the requested resource.");
        } else {
            model.addAttribute("errorTitle", "Server Error");
            model.addAttribute("errorMessage", "An unexpected server error occurred. Our team has been notified.");
        }

        return "error";
    }
}
