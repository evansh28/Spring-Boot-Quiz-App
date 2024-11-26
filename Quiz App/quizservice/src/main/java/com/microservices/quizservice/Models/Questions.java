package com.microservices.quizservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String questionTitle;
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    private String rightAnswer;
    private String difficultylevel;
    private String category;
    
}
