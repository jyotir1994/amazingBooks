package com.example.order.controller;

import com.example.order.client.BookClient;
import com.example.order.client.UserClient;
import com.example.order.entity.Book;
import com.example.order.entity.Order;
import com.example.order.entity.User;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private BookClient bookClient;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Verify user exists
        User user = userClient.getUserById(order.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        // Verify book exists
        Book book = bookClient.getBookById(order.getBookId());
        if (book == null) {
            return ResponseEntity.badRequest().build();
        }

        // Create and save order
        order.setOrderDate(LocalDate.now());
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
