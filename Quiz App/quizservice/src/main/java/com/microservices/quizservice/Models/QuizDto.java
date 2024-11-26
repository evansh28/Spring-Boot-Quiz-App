package com.microservices.quizservice.Models;

import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    String title;
    Integer numQ;
}
