package com.myjasakube.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class ServiceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name; // Nama layanan
        private String description; // Deskripsi layanan
        private Double price; // Harga layanan
        private String category; // Kategori layanan (mis. "Konstruksi", "Teknologi", dll.)

        // Tambahkan field lain sesuai kebutuhan
    }


