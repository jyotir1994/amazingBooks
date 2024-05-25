package com.example.order.client;

import com.example.order.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "books-ms")
public interface BookClient {

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable("id") Long id);
}