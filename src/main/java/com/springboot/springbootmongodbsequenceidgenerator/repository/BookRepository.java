package com.springboot.springbootmongodbsequenceidgenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.springbootmongodbsequenceidgenerator.entity.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {
    
}
