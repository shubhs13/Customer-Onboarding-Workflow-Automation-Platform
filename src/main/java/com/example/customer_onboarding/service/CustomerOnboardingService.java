package com.example.customer_onboarding.service;
import com.example.customer_onboarding.dto.CustomerOnboardingRequest;
import com.example.customer_onboarding.entity.CustomerOnboarding;
import com.example.customer_onboarding.repository.CustomerOnboardingRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerOnboardingService {
    private final CustomerOnboardingRepository repository;

    public CustomerOnboardingService(CustomerOnboardingRepository repository){
        this.repository = repository;
    }

    public CustomerOnboarding create(CustomerOnboardingRequest request){
        CustomerOnboarding customer = CustomerOnboarding.builder()
        .fullName(request.getFullName())
        .email(request.getEmail())
        .phone(request.getPhone())
        .build();
        return repository.save(customer);
    }

    public List<CustomerOnboarding> getAll(){
        return repository.findAll();
    }

    public CustomerOnboarding getById(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer onboarding not found"));
    }
    
    
}
