package com.myjasakube.service;

import com.myjasakube.model.Order;
import com.myjasakube.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create a new order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Retrieve all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Update an existing order
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setServiceName(orderDetails.getServiceName());
        order.setStatus(orderDetails.getStatus());
        order.setAppointmentDate(orderDetails.getAppointmentDate());
        order.setAppointmentTime(orderDetails.getAppointmentTime());

        return orderRepository.save(order);
    }

    // Delete an order
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        orderRepository.delete(order);
    }

    // Retrieve orders by user ID
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
