package com.telusko.quiz_service.service;


import com.telusko.quiz_service.dao.QuizDao;
import com.telusko.quiz_service.feign.Quizinterface;
import com.telusko.quiz_service.model.QuestionWrapper;
import com.telusko.quiz_service.model.Quiz;
import com.telusko.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
    @Autowired
    QuizDao quizDao;

//    @Autowired
//    QuestionDao questionDao;
    @Autowired
    Quizinterface quizinterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
       List<Integer> questions = quizinterface.getQuestionsForQuiz(category,numQ).getBody();
       Quiz quiz = new Quiz();
       quiz.setTitle(title);
       quiz.setQuestionIds(questions);
       quizDao.save(quiz);


        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz = quizDao.findById(id).get();
      List<Integer> questionids = quiz.getQuestionIds();

      ResponseEntity<List<QuestionWrapper>> questions = quizinterface.getQuestionsFromId(questionids);
        return questions;

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses)
    {
        ResponseEntity<Integer> score = quizinterface.getScore(responses);
        return score;
    }
}
