package com.krg.construction;

import com.krg.construction.entity.CustomerEnquiry;
import com.krg.construction.repository.CustomerEnquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerEnquiryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerEnquiryRepository enquiryRepository;

    @BeforeEach
    void setUp() {
        enquiryRepository.deleteAll();
    }

    @Test
    void submitEnquiryPersistsToDatabaseAndReturnsWhatsAppUrl() throws Exception {
        String jsonPayload = """
            {
                "customerName": "Suresh Babu",
                "customerPhone": "9787267811",
                "materialSelect": "6-inch Solid Cement Bricks",
                "quantityInput": "2000 bricks",
                "locationInput": "Site near Madagondapalli factory",
                "notesInput": "Require delivery by Friday morning via Tractor"
            }
            """;

        mockMvc.perform(post("/api/enquiry/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.saved").value(true))
                .andExpect(jsonPath("$.duplicate").value(false))
                .andExpect(jsonPath("$.enquiryId").isNumber())
                .andExpect(jsonPath("$.message").value(containsString("Suresh Babu")))
                .andExpect(jsonPath("$.whatsappUrl").value(containsString("https://wa.me/918122895259")));

        // Verify entity was saved in the database
        List<CustomerEnquiry> enquiries = enquiryRepository.findAll();
        assertEquals(1, enquiries.size());

        CustomerEnquiry saved = enquiries.get(0);
        assertEquals("Suresh Babu", saved.getCustomerName());
        assertEquals("9787267811", saved.getCustomerPhone());
        assertEquals("6-inch Solid Cement Bricks", saved.getProduct());
        assertEquals("2000 bricks", saved.getQuantity());
        assertEquals("Site near Madagondapalli factory", saved.getDeliveryLocation());
        assertEquals("Require delivery by Friday morning via Tractor", saved.getMessage());
        assertEquals("NEW", saved.getEnquiryStatus());
        assertNotNull(saved.getSubmissionDateTime());
    }

    @Test
    void duplicateSubmissionWithinWindowDoesNotDuplicateDatabaseRow() throws Exception {
        String jsonPayload = """
            {
                "customerName": "Ramesh Kumar",
                "customerPhone": "9876543210",
                "materialSelect": "4-inch Solid Cement Bricks",
                "quantityInput": "1000 bricks",
                "locationInput": "Madagondapalli Town",
                "notesInput": "Test note"
            }
            """;

        // First submission
        mockMvc.perform(post("/api/enquiry/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saved").value(true))
                .andExpect(jsonPath("$.duplicate").value(false));

        // Immediate duplicate submission with same phone and product
        mockMvc.perform(post("/api/enquiry/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.saved").value(false))
                .andExpect(jsonPath("$.duplicate").value(true))
                .andExpect(jsonPath("$.whatsappUrl").value(containsString("https://wa.me/918122895259")));

        // Verify only 1 row in database
        assertEquals(1, enquiryRepository.count());
    }

    @Test
    void submitEnquiryValidationErrorsOnInvalidInputs() throws Exception {
        String jsonPayload = """
            {
                "customerName": "",
                "customerPhone": "bad-phone",
                "materialSelect": "",
                "quantityInput": "",
                "locationInput": ""
            }
            """;

        mockMvc.perform(post("/api/enquiry/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.errors.customerName").exists())
                .andExpect(jsonPath("$.errors.customerPhone").exists())
                .andExpect(jsonPath("$.errors.materialSelect").exists())
                .andExpect(jsonPath("$.errors.quantityInput").exists())
                .andExpect(jsonPath("$.errors.locationInput").exists());

        // Zero rows persisted on validation failure
        assertEquals(0, enquiryRepository.count());
    }

    @Test
    void customerEnquiryListEndpointIsNotPubliclyExposed() throws Exception {
        // Enquiries must NEVER be publicly browsable
        mockMvc.perform(get("/api/enquiry"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/enquiries"))
                .andExpect(status().isNotFound());
    }
}
