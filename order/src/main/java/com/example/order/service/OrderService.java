package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order createOrder(Order order) {
        // Save order details
        Order savedOrder = orderRepository.save(order);

        // Call Books-ms to update the number of available books
        restTemplate.put("http://books-ms/books/{bookId}/available-copies?availableCopies={availableCopies}", null, savedOrder.getBookId(), -1);

        return savedOrder;
    }
}
