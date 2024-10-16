package com.myjasakube.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // assuming there's a relation to a user
    private String serviceName;
    private String status; // e.g., "completed", "pending", "scheduled"
    private String appointmentDate;
    private String appointmentTime;
}
