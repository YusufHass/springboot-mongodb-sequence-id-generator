package com.springboot.springbootmongodbsequenceidgenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.springbootmongodbsequenceidgenerator.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {
    
}
