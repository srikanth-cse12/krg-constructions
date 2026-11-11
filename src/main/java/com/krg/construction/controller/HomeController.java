package com.krg.construction.controller;

import com.krg.construction.service.BaseUrlResolver;
import com.krg.construction.service.ConstructionDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ConstructionDataService dataService;
    private final BaseUrlResolver baseUrlResolver;

    @Value("${app.hero.image:/images/hero-cement-brick-machine.jpg}")
    private String heroImage;

    @Value("${app.about.image:/images/solid-cement-bricks-cured.jpg}")
    private String aboutImage;

    public HomeController(ConstructionDataService dataService, BaseUrlResolver baseUrlResolver) {
        this.dataService = dataService;
        this.baseUrlResolver = baseUrlResolver;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        var company = dataService.getCompanyInfo();
        String baseUrl = baseUrlResolver.resolveBaseUrl(request);

        model.addAttribute("company", company);
        model.addAttribute("title", company.name() + " | Solid Cement Bricks & Construction Materials Madagondapalli");
        model.addAttribute("headline", company.tagline());
        model.addAttribute("baseUrl", baseUrl);
        
        model.addAttribute("heroImage", heroImage);
        model.addAttribute("aboutImage", aboutImage);
        
        // Manufacturing section stages
        model.addAttribute("manufacturingStages", dataService.getManufacturingStages());
        
        // Products catalog separated into Manufactured & Supplied groups
        model.addAttribute("manufacturedProducts", dataService.getManufacturedProducts());
        model.addAttribute("suppliedProducts", dataService.getSuppliedProducts());
        model.addAttribute("products", dataService.getAllProducts());
        
        model.addAttribute("whyChooseUs", dataService.getWhyChooseUsList());
        model.addAttribute("fleet", dataService.getDeliveryFleet());
        return "index";
    }
}
