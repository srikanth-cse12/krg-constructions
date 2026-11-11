package com.krg.construction.model;

import java.util.List;

public record ManufacturingStage(
    int stepNumber,
    String stageName,
    String title,
    String description,
    String imageUrl,
    String imageAlt,
    String focusHighlight,
    List<String> details
) {}

