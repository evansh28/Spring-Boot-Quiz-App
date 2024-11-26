package com.microservices.quizservice.Feing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.quizservice.Models.QuestionWrapper;
import com.microservices.quizservice.Models.Response;

@FeignClient("MICROSERVICEQUESTION")
public interface FeingService {

    @GetMapping("questiones/generate")
    public ResponseEntity<List<Integer>> getQuestions(@RequestParam String categoryName, @RequestParam Integer numQ);

    @PostMapping("questiones/getquetion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId);

    @PostMapping("questiones/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
    
}
