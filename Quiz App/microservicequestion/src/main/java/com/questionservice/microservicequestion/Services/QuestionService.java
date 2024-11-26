package com.questionservice.microservicequestion.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionservice.microservicequestion.Models.QuestionWrapper;
import com.questionservice.microservicequestion.Models.Questions;
import com.questionservice.microservicequestion.Models.Response;
import com.questionservice.microservicequestion.Repostitory.QuestionRepository;


@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Questions> getAllQuestiones() {
        return questionRepository.findAll();
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String topic) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(topic), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();;
        }
        return new ResponseEntity<>(questionRepository.findByCategory(topic), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions questions) {
        questionRepository.save(questions);
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQ) {
        List<Integer> questions = questionRepository.findRandomQuestionIdsByCategory(categoryName, numQ);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsId) {
        
        List<QuestionWrapper> wrapper = new ArrayList<>();

        List<Questions> questions = new ArrayList<>();

        for(Integer id : questionsId){
            questions.add(questionRepository.findById(id).get());
        }

        for(Questions question : questions){
            QuestionWrapper wrap = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOptions1(), question.getOptions2(), question.getOptions3(), question.getOptions4());
            wrapper.add(wrap);
        }

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
        
    }

    public ResponseEntity<Integer> calculate(List<Response> responses) {

        int right = 0;

        for(Response respon: responses){
            Questions question = questionRepository.findById(respon.getId()).get();
            if(respon.getResponse().equals(question.getRightAnswer()))
                right++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
        
    }
    
}
