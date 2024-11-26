package com.questionservice.microservicequestion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.microservicequestion.Models.QuestionWrapper;
import com.questionservice.microservicequestion.Models.Questions;
import com.questionservice.microservicequestion.Models.Response;
import com.questionservice.microservicequestion.Services.QuestionService;


@RestController
@RequestMapping("questiones")
public class QuestionControler {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestiones")
    public List<Questions> getAllQuestions(){
        return questionService.getAllQuestiones();
    }

    @GetMapping("category/{topic}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String topic){
        return questionService.getQuestionsByCategory(topic);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions){

        return questionService.addQuestion(questions);

    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestions(@RequestParam String categoryName, @RequestParam Integer numQ){
        return questionService.getQuestionsForQuiz(categoryName, numQ);
    }

    @PostMapping("getquetion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId){
        return questionService.getQuestionsFromId(questionsId);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.calculate(responses);
    }
    
}