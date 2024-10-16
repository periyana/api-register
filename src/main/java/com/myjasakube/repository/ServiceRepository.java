package com.myjasakube.repository;

import com.myjasakube.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    // Anda bisa menambahkan query custom di sini jika diperlukan
}
