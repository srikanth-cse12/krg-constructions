package com.krg.construction.repository;

import com.krg.construction.entity.CustomerEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerEnquiryRepository extends JpaRepository<CustomerEnquiry, Long> {

    /**
     * Finds recent enquiries with the same customer phone and product to prevent duplicate submissions.
     */
    List<CustomerEnquiry> findByCustomerPhoneAndProductAndSubmissionDateTimeAfter(
        String customerPhone,
        String product,
        LocalDateTime after
    );

    /**
     * Counts submissions by a specific phone number in a given time window (anti-spam protection).
     */
    long countByCustomerPhoneAndSubmissionDateTimeAfter(
        String customerPhone,
        LocalDateTime after
    );
}
