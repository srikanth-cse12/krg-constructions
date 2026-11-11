package com.krg.construction.controller;

import com.krg.construction.model.EnquiryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryController {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9+\\-\\s]{7,15}$");
    private static final Pattern DANGEROUS_CHARS = Pattern.compile("[<>\"'&]");

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateEnquiry(@RequestBody EnquiryRequest request) {
        Map<String, String> errors = new HashMap<>();

        String name = sanitize(request.customerName());
        if (name == null || name.isBlank()) {
            errors.put("customerName", "Customer name is required.");
        } else if (name.length() > 100) {
            errors.put("customerName", "Customer name cannot exceed 100 characters.");
        }

        String phone = sanitize(request.customerPhone());
        if (phone == null || phone.isBlank()) {
            errors.put("customerPhone", "Phone number is required.");
        } else if (!PHONE_PATTERN.matcher(phone).matches()) {
            errors.put("customerPhone", "Please provide a valid 10-digit phone number.");
        }

        String product = sanitize(request.materialSelect());
        if (product == null || product.isBlank()) {
            errors.put("materialSelect", "Product selection is required.");
        }

        String quantity = sanitize(request.quantityInput());
        if (quantity == null || quantity.isBlank()) {
            errors.put("quantityInput", "Required quantity is required.");
        } else if (quantity.length() > 100) {
            errors.put("quantityInput", "Quantity description cannot exceed 100 characters.");
        }

        String location = sanitize(request.locationInput());
        if (location == null || location.isBlank()) {
            errors.put("locationInput", "Delivery location is required.");
        } else if (location.length() > 200) {
            errors.put("locationInput", "Location cannot exceed 200 characters.");
        }

        String notes = sanitize(request.notesInput());
        if (notes != null && notes.length() > 500) {
            errors.put("notesInput", "Notes cannot exceed 500 characters.");
        }

        Map<String, Object> response = new HashMap<>();
        if (!errors.isEmpty()) {
            response.put("valid", false);
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        // Formatted WhatsApp message generated on server
        StringBuilder sb = new StringBuilder();
        sb.append("Hello KRG CONSTRUCTIONS,\n\n");
        sb.append("I would like to enquire about building materials and delivery availability:\n");
        sb.append("• Customer Name: ").append(name).append("\n");
        sb.append("• Contact Number: ").append(phone).append("\n");
        sb.append("• Product: ").append(product).append("\n");
        sb.append("• Required Quantity: ").append(quantity).append("\n");
        sb.append("• Delivery Location: ").append(location);
        if (notes != null && !notes.isBlank()) {
            sb.append("\n• Additional Message: ").append(notes);
        }
        sb.append("\n\nPlease let me know the price estimate, load availability, and delivery scheduling. Thank you!");

        String whatsappUrl = "https://wa.me/918122895259?text=" + URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);

        response.put("valid", true);
        response.put("whatsappUrl", whatsappUrl);
        response.put("message", sb.toString());
        return ResponseEntity.ok(response);
    }

    private String sanitize(String input) {
        if (input == null) return null;
        return DANGEROUS_CHARS.matcher(input.trim()).replaceAll("");
    }
}

