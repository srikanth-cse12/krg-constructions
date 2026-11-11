package com.krg.construction.model;

import java.util.List;

public record Product(
    String id,
    String name,
    String category,
    String group, // "MANUFACTURED" or "SUPPLIED"
    String shortDescription,
    String dimensions,
    List<String> keyFeatures,
    String bestFor,
    String imageUrl,
    String badge,
    String whatsappMessage
) {}
