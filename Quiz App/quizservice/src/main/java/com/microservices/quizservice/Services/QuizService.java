package com.microservices.quizservice.Services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quizservice.Feing.FeingService;
import com.microservices.quizservice.Models.QuestionWrapper;
import com.microservices.quizservice.Models.Quiz;
// import com.microservices.quizservice.Models.Questions;
// import com.microservices.quizservice.Models.Quiz;
import com.microservices.quizservice.Models.Response;
import com.microservices.quizservice.Repostitory.QuizRepository;


@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    FeingService feingservice;

    public ResponseEntity<String> createQuizz(String category, int numQ, String topic){

        List<Integer> questions = feingservice.getQuestions(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(topic);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getAllQuiz(Integer id) {

        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionIds = quiz.getQuestions();

        ResponseEntity<List<QuestionWrapper>> questionsForUser = feingservice.getQuestionsFromId(questionIds);


        return questionsForUser;
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> response) {
        
        ResponseEntity<Integer> right = feingservice.getScore(response);

        return right;
    }

    
}
