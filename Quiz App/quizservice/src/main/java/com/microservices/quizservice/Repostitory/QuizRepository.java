package com.microservices.quizservice.Repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.quizservice.Models.Quiz;


public interface QuizRepository extends JpaRepository<Quiz, Integer>{
    
}
