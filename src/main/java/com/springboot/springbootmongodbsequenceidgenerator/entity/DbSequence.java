package com.springboot.springbootmongodbsequenceidgenerator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection= "db_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequence {
    @Id
    private String sequnceName;
    private int seq;

}
