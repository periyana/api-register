package com.myjasakube.service;

import com.myjasakube.model.ServiceEntity;
import com.myjasakube.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceEntity createService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public ServiceEntity updateService(Long id, ServiceEntity serviceDetails) {
        ServiceEntity service = serviceRepository.findById(id).orElseThrow();
        // Update fields
        service.setName(serviceDetails.getName());
        service.setDescription(serviceDetails.getDescription());
        service.setPrice(serviceDetails.getPrice());
        service.setCategory(serviceDetails.getCategory());
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
