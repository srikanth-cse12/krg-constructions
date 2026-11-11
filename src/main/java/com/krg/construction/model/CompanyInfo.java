package com.krg.construction.model;

public record CompanyInfo(
    String name,
    String legalName,
    int establishedYear,
    String tagline,
    String locationName,
    String fullAddress,
    String landmark,
    String phone,
    String phoneDisplay,
    String whatsappNumber,
    String whatsappDisplay,
    String whatsappMessage,
    String workingHours,
    int coverageRadiusKm,
    String factoryCoordinates,
    String googleMapsUrl,
    String deliveryEnquiryMessage,
    String ownership
) {}
