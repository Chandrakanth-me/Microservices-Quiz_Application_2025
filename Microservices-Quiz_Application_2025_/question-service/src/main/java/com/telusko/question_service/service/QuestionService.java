package com.telusko.question_service.service;


import com.telusko.question_service.dao.QuestionDao;
import com.telusko.question_service.model.Cacs;
import com.telusko.question_service.model.QuestionWrapper;
import com.telusko.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    public Object addQuestion;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Cacs>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public List<Cacs> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Cacs question) {
        questionDao.save(question);
        return "success";
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {

        List<Integer> cacs = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(cacs, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Cacs> cacs = new ArrayList<>();

        for(Integer id:questionIds){
            cacs.add(questionDao.findById(id).get());
        }

        for(Cacs c:cacs){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(c.getId());
            wrapper.setQues((c.getQues()));
            wrapper.setOp1(c.getOp1());
            wrapper.setOp2(c.getOp2());
            wrapper.setOp3(c.getOp3());
            wrapper.setOp4(c.getOp4());
            wrappers.add(wrapper);

        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int right = 0;

        for(Response response : responses)
        {
            Cacs cacs = questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(cacs.getRightanswer()))
                right++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
