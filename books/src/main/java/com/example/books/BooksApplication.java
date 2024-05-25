package com.example.books;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(BookRepository bookRepository) {
		return args -> {
			if (bookRepository.count() == 0) {
				bookRepository.save(new Book("Book Title 4", "Author 4", 7));
				bookRepository.save(new Book("Book Title 5", "Author 5", 3));
			}
		};
	}

}
