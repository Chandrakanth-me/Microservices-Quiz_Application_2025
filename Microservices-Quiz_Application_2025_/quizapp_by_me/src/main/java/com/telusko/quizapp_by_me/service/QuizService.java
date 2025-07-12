package com.telusko.quizapp_by_me.service;

import com.telusko.quizapp_by_me.dao.QuestionDao;
import com.telusko.quizapp_by_me.dao.QuizDao;
import com.telusko.quizapp_by_me.model.Cacs;
import com.telusko.quizapp_by_me.model.QuestionWrapper;
import com.telusko.quizapp_by_me.model.Quiz;
import com.telusko.quizapp_by_me.model.Response;
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

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
        List<Cacs> cacs = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz=  new Quiz();
        quiz.setTitle(title);

        quiz.setCacs(cacs);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Cacs> cacsfromDB = quiz.get().getCacs();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Cacs c : cacsfromDB)
        {
            QuestionWrapper qr = new QuestionWrapper(c.getId(),c.getQues(),c.getOp1(),c.getOp2(),c.getOp3(),c.getOp4());
            questionsForUser.add(qr);
        }
        return new  ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses)
    {
        Quiz quiz = quizDao.findById(id).get();
        List<Cacs> cacs = quiz.getCacs();
        int right = 0;
        int i=0;
        for(Response response : responses)
        {
            if (i < cacs.size() && response.getResponse().equals(cacs.get(i).getRightanswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
