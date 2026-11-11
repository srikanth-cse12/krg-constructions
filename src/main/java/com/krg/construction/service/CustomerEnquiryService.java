package com.krg.construction.service;

import com.krg.construction.entity.CustomerEnquiry;
import com.krg.construction.model.EnquiryRequest;
import com.krg.construction.repository.CustomerEnquiryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class CustomerEnquiryService {

    private static final Logger log = LoggerFactory.getLogger(CustomerEnquiryService.class);

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9+\\-\\s]{7,15}$");
    private static final Pattern DANGEROUS_CHARS = Pattern.compile("[<>\"'&]");
    private static final String WHATSAPP_NUMBER = "918122895259";

    private final CustomerEnquiryRepository enquiryRepository;

    public CustomerEnquiryService(CustomerEnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    /**
     * Validates enquiry inputs and returns a map of field errors (empty if valid).
     */
    public Map<String, String> validateEnquiry(EnquiryRequest request) {
        Map<String, String> errors = new HashMap<>();

        String name = sanitize(request.customerName());
        if (name == null || name.isBlank()) {
            errors.put("customerName", "Customer name is required.");
        } else if (name.length() < 2) {
            errors.put("customerName", "Customer name must be at least 2 characters.");
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
        } else if (product.length() > 150) {
            errors.put("materialSelect", "Product name cannot exceed 150 characters.");
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
        } else if (location.length() > 255) {
            errors.put("locationInput", "Location cannot exceed 255 characters.");
        }

        String notes = sanitize(request.notesInput());
        if (notes != null && notes.length() > 1000) {
            errors.put("notesInput", "Notes cannot exceed 1000 characters.");
        }

        return errors;
    }

    /**
     * Checks if a duplicate submission was received within the duplicate window (default 2 minutes).
     */
    public boolean isDuplicateSubmission(String phone, String product, int windowMinutes) {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(windowMinutes);
        List<CustomerEnquiry> duplicates = enquiryRepository
                .findByCustomerPhoneAndProductAndSubmissionDateTimeAfter(phone, product, threshold);
        return !duplicates.isEmpty();
    }

    /**
     * Processes customer enquiry: checks duplicate, persists to database, generates WhatsApp link.
     */
    @Transactional
    public ProcessEnquiryResult processAndSaveEnquiry(EnquiryRequest request, String clientIp) {
        String name = sanitize(request.customerName());
        String phone = sanitize(request.customerPhone());
        String product = sanitize(request.materialSelect());
        String quantity = sanitize(request.quantityInput());
        String location = sanitize(request.locationInput());
        String notes = sanitize(request.notesInput());

        boolean isDuplicate = isDuplicateSubmission(phone, product, 2);
        Long savedId = null;

        if (!isDuplicate) {
            CustomerEnquiry enquiry = new CustomerEnquiry(
                    name,
                    phone,
                    product,
                    quantity,
                    location,
                    notes,
                    clientIp
            );
            CustomerEnquiry saved = enquiryRepository.save(enquiry);
            savedId = saved.getId();
            log.info("Persisted customer enquiry [ID: {}] for contact: {} [Product: {}]",
                    savedId, maskPhone(phone), product);
        } else {
            log.info("Duplicate enquiry submission detected within 2-min window for contact: {} [Product: {}]. Not duplicating row.",
                    maskPhone(phone), product);
        }

        String formattedMessage = generateWhatsAppMessage(name, phone, product, quantity, location, notes);
        String whatsappUrl = "https://wa.me/" + WHATSAPP_NUMBER + "?text=" + URLEncoder.encode(formattedMessage, StandardCharsets.UTF_8);

        String userFeedbackMessage;
        if (isDuplicate) {
            userFeedbackMessage = "We have already received your enquiry for " + product + " recently. Our dispatch desk will contact you soon. You may also proceed to WhatsApp below.";
        } else {
            userFeedbackMessage = "Thank you, " + name + "! Your enquiry (#" + savedId + ") has been received and saved. Our team will review your requirements.";
        }

        return new ProcessEnquiryResult(
                true,
                !isDuplicate,
                isDuplicate,
                savedId,
                userFeedbackMessage,
                whatsappUrl,
                formattedMessage
        );
    }

    /**
     * Generates standard formatted WhatsApp enquiry text.
     */
    public String generateWhatsAppMessage(String name, String phone, String product,
                                          String quantity, String location, String notes) {
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
        return sb.toString();
    }

    public String sanitize(String input) {
        if (input == null) return null;
        return DANGEROUS_CHARS.matcher(input.trim()).replaceAll("");
    }

    private String maskPhone(String phone) {
        if (phone == null || phone.length() < 4) return "****";
        return phone.substring(0, 2) + "******" + phone.substring(phone.length() - 2);
    }

    public record ProcessEnquiryResult(
            boolean valid,
            boolean saved,
            boolean duplicate,
            Long enquiryId,
            String message,
            String whatsappUrl,
            String formattedText
    ) {}
}
