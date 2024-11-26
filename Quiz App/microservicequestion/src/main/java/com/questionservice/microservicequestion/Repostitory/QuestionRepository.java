package com.questionservice.microservicequestion.Repostitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.questionservice.microservicequestion.Models.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String topic);
// @Query(value = "SELECT q.id FROM ( " +
//                "    SELECT q.*, ROW_NUMBER() OVER (ORDER BY RANDOM()) AS rn " +
//                "    FROM questions q " +
//                "    WHERE q.category = :category " +
//                ") subquery " +
//                "WHERE subquery.rn <= :numQ", 
//        nativeQuery = true)
// List<Integer> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);

@Query(value = "SELECT id FROM ( " +
               "    SELECT q.id, ROW_NUMBER() OVER (ORDER BY RANDOM()) AS rn " +
               "    FROM questions q " +
               "    WHERE q.category = :category " +
               ") subquery " +
               "WHERE subquery.rn <= :numQ", 
       nativeQuery = true)
List<Integer> findRandomQuestionIdsByCategory(@Param("category") String category, @Param("numQ") int numQ);


    
    
}