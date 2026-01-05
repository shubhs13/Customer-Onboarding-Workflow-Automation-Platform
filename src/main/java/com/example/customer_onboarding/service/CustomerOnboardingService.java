package com.example.customer_onboarding.service;
import com.example.customer_onboarding.dto.CustomerOnboardingRequest;
import com.example.customer_onboarding.entity.CustomerOnboarding;
import com.example.customer_onboarding.repository.CustomerOnboardingRepository;
import com.example.customer_onboarding.workflow.OnboardingStage;
import com.example.customer_onboarding.workflow.OnboardingStatus;
import com.example.customer_onboarding.workflow.UserRole;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerOnboardingService {
    private final CustomerOnboardingRepository repository;

    public CustomerOnboardingService(CustomerOnboardingRepository repository){
        this.repository = repository;
    }
    // --------------------------
    // CREATE
    // --------------------------

    public CustomerOnboarding create(CustomerOnboardingRequest request){
        CustomerOnboarding customer = CustomerOnboarding.builder()
        .fullName(request.getFullName())
        .email(request.getEmail())
        .phone(request.getPhone())
        .build();
        return repository.save(customer);
    }
    // --------------------------
    // GET ALL
    // --------------------------
    public List<CustomerOnboarding> getAll(){
        return repository.findAll();
    }
    // --------------------------
    // GET BY ID
    // --------------------------
    public CustomerOnboarding getById(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer onboarding not found"));
    }
    // --------------------------
    // APPROVE
    // --------------------------
    public CustomerOnboarding approve(Long id, UserRole role){
        CustomerOnboarding customer = getById(id);
        validateRole(customer.getCurrentStage(),role);
        // Move to next stage or mark as approved if final stage
        switch (customer.getCurrentStage()) {
            case OPS_REVIEW -> customer.setCurrentStage(OnboardingStage.MANAGER_APPROVAL);
            case MANAGER_APPROVAL -> customer.setCurrentStage(OnboardingStage.COMPLIANCE_CHECK);
            case COMPLIANCE_CHECK -> {
                customer.setCurrentStage(OnboardingStage.COMPLETED);
                customer.setOnboardingStatus(OnboardingStatus.APPROVED);
            }
        case COMPLETED -> throw new RuntimeException("Customer onboarding is already completed");
    }

    return repository.save(customer);
}
    // --------------------------
    // REJECT
    // --------------------------
    public CustomerOnboarding reject(Long id, UserRole role){
        CustomerOnboarding customer = getById(id);
        validateRole(customer.getCurrentStage(), role);
        
        //Reject Immediately
        customer.setCurrentStage(OnboardingStage.COMPLETED);
        customer.setOnboardingStatus(OnboardingStatus.REJECTED);
        return repository.save(customer);
    }
    // --------------------------
    // Validate Role
    // --------------------------
    private void validateRole(OnboardingStage stage, UserRole role){
        switch (stage) {
            case OPS_REVIEW -> {
                if(role != UserRole.OPS_USER) 
                    throw new RuntimeException("Only ops user can approve at this stage");
                }
            case MANAGER_APPROVAL -> {
                if(role != UserRole.OPS_MANAGER) 
                    throw new RuntimeException("Only ops manager can approve at this stage");
            }
            case COMPLIANCE_CHECK -> 
            {
                if(role != UserRole.COMPLIANCE_USER) 
                    throw new RuntimeException("Only compliance user can approve at this stage");
            }
            case COMPLETED -> {
                throw new RuntimeException("Customer onboarding is already completed");
            }
        }
    }
    
}
