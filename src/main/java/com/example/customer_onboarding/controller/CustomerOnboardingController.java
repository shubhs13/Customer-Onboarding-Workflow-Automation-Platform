package com.example.customer_onboarding.controller;

import com.example.customer_onboarding.dto.CustomerOnboardingRequest;
import com.example.customer_onboarding.entity.CustomerOnboarding;
import com.example.customer_onboarding.service.CustomerOnboardingService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-onboarding") 
public class CustomerOnboardingController {
    private final CustomerOnboardingService service;

    public CustomerOnboardingController(CustomerOnboardingService service){
        this.service = service;
    }

    @PostMapping
    public CustomerOnboarding create(
        @Valid @RequestBody CustomerOnboardingRequest request){
            return service.create(request);
        }

    @GetMapping
    public List<CustomerOnboarding> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CustomerOnboarding getById(@PathVariable Long id){
        return service.getById(id);
}
}
