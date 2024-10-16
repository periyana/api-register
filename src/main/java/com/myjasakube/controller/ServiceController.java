package com.myjasakube.controller;

import com.myjasakube.model.ServiceEntity;
import com.myjasakube.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping
    public ServiceEntity createService(@RequestBody ServiceEntity service) {
        return serviceService.createService(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateService(@PathVariable Long id, @RequestBody ServiceEntity serviceDetails) {
        ServiceEntity updatedService = serviceService.updateService(id, serviceDetails);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
