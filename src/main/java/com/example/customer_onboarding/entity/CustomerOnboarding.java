package com.example.customer_onboarding.entity;

import com.example.customer_onboarding.workflow.OnboardingStatus;
import com.example.customer_onboarding.workflow.OnboardingStage;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_onboarding")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOnboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    /**
     * Final outcome of onboarding
     * PENDING | APPROVED | REJECTED
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OnboardingStatus onboardingStatus;

    /**
     * Current workflow step
     * OPS_REVIEW | MANAGER_APPROVAL | COMPLIANCE_CHECK | COMPLETED
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OnboardingStage currentStage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.onboardingStatus = OnboardingStatus.PENDING;
        this.currentStage = OnboardingStage.OPS_REVIEW;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
