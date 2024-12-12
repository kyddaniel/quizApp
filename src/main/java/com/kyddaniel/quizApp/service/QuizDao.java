package com.kyddaniel.quizApp.service;

import com.kyddaniel.quizApp.model.Question;
import com.kyddaniel.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
