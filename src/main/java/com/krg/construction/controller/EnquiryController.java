package com.krg.construction.controller;

import com.krg.construction.model.EnquiryRequest;
import com.krg.construction.service.CustomerEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryController {

    private final CustomerEnquiryService enquiryService;

    public EnquiryController(CustomerEnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    /**
     * Primary endpoint: Validates the enquiry fields, persists to PostgreSQL/H2 database,
     * checks for duplicate submissions, and prepares the pre-filled WhatsApp link.
     */
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitEnquiry(
            @RequestBody EnquiryRequest request,
            HttpServletRequest servletRequest) {

        Map<String, String> errors = enquiryService.validateEnquiry(request);
        if (!errors.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("valid", false);
            errorResponse.put("errors", errors);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        String clientIp = extractClientIp(servletRequest);
        CustomerEnquiryService.ProcessEnquiryResult result = enquiryService.processAndSaveEnquiry(request, clientIp);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("valid", true);
        response.put("saved", result.saved());
        response.put("duplicate", result.duplicate());
        if (result.enquiryId() != null) {
            response.put("enquiryId", result.enquiryId());
        }
        response.put("message", result.message());
        response.put("whatsappUrl", result.whatsappUrl());
        return ResponseEntity.ok(response);
    }

    /**
     * Legacy/Validation endpoint maintained for full compatibility with existing integration tests.
     * Validates input, saves enquiry to database, and returns validation status and WhatsApp URL.
     */
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateEnquiry(
            @RequestBody EnquiryRequest request,
            HttpServletRequest servletRequest) {
        return submitEnquiry(request, servletRequest);
    }

    private String extractClientIp(HttpServletRequest request) {
        if (request == null) return "unknown";
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isBlank()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
