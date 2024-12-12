package com.kyddaniel.quizApp.service;

import com.kyddaniel.quizApp.dao.QuestionDao;
import com.kyddaniel.quizApp.model.Question;
import com.kyddaniel.quizApp.model.QuestionWrapper;
import com.kyddaniel.quizApp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        if (quiz.isPresent()) {
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for (Question question : questionsFromDB) {
                questionsForUser.add(new QuestionWrapper(question));
            }

            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    }
}
