package com.amazingname.api.service;

import com.amazingname.api.model.ServiceAn;
import com.amazingname.api.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAnService {
    private final ServiceRepository serviceRepository;

    public ServiceAnService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceAn> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceAn getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public ServiceAn saveService(ServiceAn serviceAn) {
        return serviceRepository.save(serviceAn);
    }

    public void deleteServiceById(Long id) {
        serviceRepository.deleteById(id);
    }

    public List<ServiceAn> searchServiceByName(String search) {
        return serviceRepository.findAllByNameContaining(search);
    }
}
