package com.krg.construction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePageLoadsSuccessfully() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Content-Type-Options", "nosniff"))
                .andExpect(header().string("X-Frame-Options", "SAMEORIGIN"))
                .andExpect(header().string("Referrer-Policy", "strict-origin-when-cross-origin"))
                
                // SEO Metadata & Hierarchy
                .andExpect(content().string(containsString("KRG CONSTRUCTIONS | Solid Cement Bricks &amp; Construction Materials Madagondapalli")))
                .andExpect(content().string(containsString("name=\"description\"")))
                .andExpect(content().string(containsString("name=\"keywords\"")))
                .andExpect(content().string(containsString("rel=\"canonical\"")))
                .andExpect(content().string(containsString("property=\"og:title\"")))
                .andExpect(content().string(containsString("property=\"og:image\"")))
                .andExpect(content().string(containsString("name=\"twitter:card\"")))
                .andExpect(content().string(containsString("\"@type\": \"HomeAndConstructionBusiness\"")))
                .andExpect(content().string(containsString("\"streetAddress\": \"JP4V+335\"")))
                
                // Logo & Branding
                .andExpect(content().string(containsString("/images/krg-logo.svg")))
                .andExpect(content().string(containsString("/images/krg-logo-white.svg")))
                .andExpect(content().string(containsString("brand-logo-img")))
                .andExpect(content().string(containsString("mobile-drawer-logo")))
                .andExpect(content().string(containsString("brand-logo-footer")))
                .andExpect(content().string(containsString("SINCE 2010 • MADAGONDAPALLI")))

                // Hero & H1
                .andExpect(content().string(containsString("KRG CONSTRUCTIONS")))
                .andExpect(content().string(containsString("Building Stronger Foundations Since 2010")))
                .andExpect(content().string(containsString("fetchpriority=\"high\"")))

                // About Section
                .andExpect(content().string(containsString("ABOUT OUR COMPANY")))
                .andExpect(content().string(containsString("Family-Owned Construction Materials Manufacturer & Supplier")))
                .andExpect(content().string(containsString("Established in <strong>2010</strong>")))
                .andExpect(content().string(containsString("Madagondapalli, Tamil Nadu")))

                // Manufacturing Section
                .andExpect(content().string(containsString("SEMI-AUTOMATIC BRICK PLANT")))
                .andExpect(content().string(containsString("How We Manufacture Solid Cement Bricks")))
                .andExpect(content().string(containsString("Completely Solid Concrete Bodies — Never Hollow Blocks")))
                .andExpect(content().string(containsString("100% Solid Cement Bricks Specification")))

                // 4 Required Manufacturing Stages
                .andExpect(content().string(containsString("Semi-Automatic Machine Operation")))
                .andExpect(content().string(containsString("Cement Mixture Pressed Into Solid Bricks")))
                .andExpect(content().string(containsString("Freshly Manufactured Solid Cement Bricks")))
                .andExpect(content().string(containsString("Finished Solid Bricks Arranged in Stacks")))

                // 4 Manufacturing Stage Images with Lazy Loading & Async Decoding
                .andExpect(content().string(containsString("/images/mfg-worker-operating-machine.jpg")))
                .andExpect(content().string(containsString("/images/mfg-mixture-pressed-mould.jpg")))
                .andExpect(content().string(containsString("/images/mfg-fresh-manufactured-bricks.jpg")))
                .andExpect(content().string(containsString("/images/mfg-finished-brick-stacks.jpg")))
                .andExpect(content().string(containsString("loading=\"lazy\"")))
                .andExpect(content().string(containsString("decoding=\"async\"")))

                // Manufactured Products
                .andExpect(content().string(containsString("Products Manufactured by KRG")))
                .andExpect(content().string(containsString("4-inch Solid Cement Bricks")))
                .andExpect(content().string(containsString("6-inch Solid Cement Bricks")))

                // Supplied Materials
                .andExpect(content().string(containsString("Materials Supplied by KRG")))
                .andExpect(content().string(containsString("P-Sand")))
                .andExpect(content().string(containsString("M-Sand")))
                .andExpect(content().string(containsString("Dust")))
                .andExpect(content().string(containsString("Sagar, Priya, Ramco")))
                .andExpect(content().string(containsString("20 mm Jelly")))
                .andExpect(content().string(containsString("40 mm Jelly")))

                // WhatsApp & Contact details
                .andExpect(content().string(containsString("918122895259")))
                .andExpect(content().string(containsString("+91 81228 95259")))
                .andExpect(content().string(containsString("Madagondapalli")))

                // Delivery & Service Areas Section
                .andExpect(content().string(containsString("Delivery & Service Areas")))
                .andExpect(content().string(containsString("Approx. 30–35 km Coverage")))
                .andExpect(content().string(containsString("Small Orders Accepted")))
                .andExpect(content().string(containsString("Delivery Included in Price")))
                .andExpect(content().string(containsString("charges are included in the product price")))

                // Delivery Fleet & Vehicles
                .andExpect(content().string(containsString("Tractor Delivery")))
                .andExpect(content().string(containsString("Lorry / Truck Delivery")))
                .andExpect(content().string(containsString("/images/delivery-tractor.jpg")))
                .andExpect(content().string(containsString("/images/delivery-lorry.jpg")))

                // Factory GPS Coordinates & Google Maps Link
                .andExpect(content().string(
                        containsString("https://www.google.com/maps/dir/?api=1&amp;destination=12.605139,77.742722")))
                .andExpect(content().string(containsString("Get Directions on Google Maps")))

                // Delivery Availability WhatsApp Enquiry
                .andExpect(content().string(containsString("Check Delivery Availability")))
                .andExpect(content().string(containsString(
                        "Hello%20KRG%20CONSTRUCTIONS,%20I%20would%20like%20to%20enquire%20about%20delivery%20availability%20to%20my%20location.%20Please%20let%20me%20know%20the%20details.")))

                // Contact & Enquiry Section
                .andExpect(content().string(containsString("Contact &amp; Customer Enquiry")))
                .andExpect(content().string(containsString("tel:9787267811")))
                .andExpect(content().string(containsString("Call Now: 9787267811")))
                .andExpect(content().string(containsString("+91 97872 67811")))
                .andExpect(content().string(containsString("JP4V+335, Madagondapalli, Tamil Nadu, India.")))
                .andExpect(content().string(containsString("Open 24 Hours")))
                .andExpect(content().string(containsString("Family-Owned Business")))

                // Customer Material Enquiry Form & Back to Top
                .andExpect(content().string(containsString("Customer Material Enquiry")))
                .andExpect(content().string(containsString("id=\"customerName\"")))
                .andExpect(content().string(containsString("id=\"customerPhone\"")))
                .andExpect(content().string(containsString("id=\"materialSelect\"")))
                .andExpect(content().string(containsString("id=\"quantityInput\"")))
                .andExpect(content().string(containsString("id=\"locationInput\"")))
                .andExpect(content().string(containsString("id=\"notesInput\"")))
                .andExpect(content().string(containsString("Send Enquiry on WhatsApp")))
                .andExpect(content().string(containsString("id=\"backToTop\"")));
    }

    @Test
    void enquiryValidationEndpointSuccess() throws Exception {
        String jsonPayload = """
            {
                "customerName": "Ramesh Kumar",
                "customerPhone": "9787267811",
                "materialSelect": "4-inch Solid Cement Bricks",
                "quantityInput": "1500 bricks",
                "locationInput": "Near Madagondapalli",
                "notesInput": "Tractor preferred"
            }
            """;

        mockMvc.perform(post("/api/enquiry/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true))
                .andExpect(jsonPath("$.whatsappUrl").value(containsString("https://wa.me/918122895259")));
    }

    @Test
    void enquiryValidationEndpointFailureOnMissingFields() throws Exception {
        String jsonPayload = """
            {
                "customerName": "",
                "customerPhone": "invalid",
                "materialSelect": "",
                "quantityInput": "",
                "locationInput": ""
            }
            """;

        mockMvc.perform(post("/api/enquiry/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.errors.customerName").exists())
                .andExpect(jsonPath("$.errors.customerPhone").exists());
    }

    @Test
    void healthCheckEndpointsReturnUp() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.application").value("krg-constructions"));

        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void robotsTxtEndpointGeneratesValidOutput() throws Exception {
        mockMvc.perform(get("/robots.txt"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(containsString("User-agent: *")))
                .andExpect(content().string(containsString("Allow: /")))
                .andExpect(content().string(containsString("/sitemap.xml")));
    }

    @Test
    void sitemapXmlEndpointGeneratesValidUrlset() throws Exception {
        mockMvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(content().string(containsString("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">")))
                .andExpect(content().string(containsString("<loc>")))
                .andExpect(content().string(containsString("#about")))
                .andExpect(content().string(containsString("#manufacturing")))
                .andExpect(content().string(containsString("#products")))
                .andExpect(content().string(containsString("#delivery")))
                .andExpect(content().string(containsString("#contact")));
    }

    @Test
    void customErrorPageRendersOnNotFound() throws Exception {
        mockMvc.perform(get("/non-existent-page-test"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Page Not Found")))
                .andExpect(content().string(containsString("404")))
                .andExpect(content().string(containsString("Back to Home")));
    }

    @Test
    void customErrorJsonReturnsOnApiNotFound() throws Exception {
        mockMvc.perform(get("/api/unknown-service"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}
