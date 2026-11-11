package com.krg.construction.model;

public record EnquiryRequest(
    String customerName,
    String customerPhone,
    String materialSelect,
    String quantityInput,
    String locationInput,
    String notesInput
) {}

