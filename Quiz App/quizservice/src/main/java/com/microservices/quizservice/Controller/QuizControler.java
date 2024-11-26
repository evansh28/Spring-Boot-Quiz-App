package com.microservices.quizservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.quizservice.Models.QuestionWrapper;
import com.microservices.quizservice.Models.QuizDto;
import com.microservices.quizservice.Models.Response;
import com.microservices.quizservice.Services.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizControler {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto) {
        return quizService.createQuizz(quizdto.getCategoryName(), quizdto.getNumQ(), quizdto.getTitle());
    }

    @GetMapping("all/{id}")
    public ResponseEntity<List<QuestionWrapper>> getAll(@PathVariable Integer id){
        return quizService.getAllQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response ){
        return quizService.calculate(id, response);
    }

}
