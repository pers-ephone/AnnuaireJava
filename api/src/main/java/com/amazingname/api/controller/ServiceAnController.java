package com.amazingname.api.controller;

import com.amazingname.api.model.ServiceAn;
import com.amazingname.api.service.ServiceAnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceAnController {
    private final ServiceAnService serviceAnService;

    public ServiceAnController(ServiceAnService serviceAnService) {
        this.serviceAnService = serviceAnService;
    }

    @GetMapping
    public List<ServiceAn> getAllServiceAns() {
        return serviceAnService.getAllServices();
    }

    @GetMapping("{id}")
    public ServiceAn getServiceAnById(@PathVariable Long id) {
        return serviceAnService.getServiceById(id);
    }

    @GetMapping("/search/{search}")
    public List<ServiceAn> searchServiceAn(@PathVariable String search) {
        return serviceAnService.searchServiceByName(search);
    }

    @PostMapping
    public ServiceAn createServiceAn(@RequestBody ServiceAn serviceAn) {
        return serviceAnService.saveService(serviceAn);
    }

    @PutMapping
    public ServiceAn updateServiceAn(@RequestBody ServiceAn serviceAn) {
        return serviceAnService.saveService(serviceAn);
    }

    @DeleteMapping("{id}")
    public void deleteServiceAnById(@PathVariable Long id) {
        serviceAnService.deleteServiceById(id);
    }
}
