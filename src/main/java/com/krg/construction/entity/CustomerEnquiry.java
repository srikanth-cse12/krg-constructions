package com.krg.construction.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_enquiries", indexes = {
    @Index(name = "idx_enquiry_phone", columnList = "customer_phone"),
    @Index(name = "idx_enquiry_created_at", columnList = "submission_date_time"),
    @Index(name = "idx_enquiry_status", columnList = "enquiry_status")
})
public class CustomerEnquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "customer_phone", nullable = false, length = 20)
    private String customerPhone;

    @Column(name = "product", nullable = false, length = 150)
    private String product;

    @Column(name = "quantity", nullable = false, length = 100)
    private String quantity;

    @Column(name = "delivery_location", nullable = false, length = 255)
    private String deliveryLocation;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(name = "submission_date_time", nullable = false)
    private LocalDateTime submissionDateTime;

    @Column(name = "enquiry_status", nullable = false, length = 30)
    private String enquiryStatus;

    @Column(name = "client_ip", length = 50)
    private String clientIp;

    public CustomerEnquiry() {
    }

    public CustomerEnquiry(String customerName, String customerPhone, String product,
                           String quantity, String deliveryLocation, String message,
                           String clientIp) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.product = product;
        this.quantity = quantity;
        this.deliveryLocation = deliveryLocation;
        this.message = message;
        this.clientIp = clientIp;
        this.submissionDateTime = LocalDateTime.now();
        this.enquiryStatus = "NEW";
    }

    @PrePersist
    protected void onCreate() {
        if (this.submissionDateTime == null) {
            this.submissionDateTime = LocalDateTime.now();
        }
        if (this.enquiryStatus == null || this.enquiryStatus.isBlank()) {
            this.enquiryStatus = "NEW";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(LocalDateTime submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }

    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
