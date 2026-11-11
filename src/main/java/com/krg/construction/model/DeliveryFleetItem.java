package com.krg.construction.model;

import java.util.List;

public record DeliveryFleetItem(
    String vehicleType,
    String capacity,
    String idealFor,
    String description,
    List<String> features,
    String imageUrl
) {}

