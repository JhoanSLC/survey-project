package com.surveyproject.questions.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.domain.service.QuestionsService;
import com.surveyproject.screen.ScreenController;

public class QuestionsRepository implements QuestionsService {

    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();


    @Override
    public void createQuestion(Questions question) {
        String createQuery = "INSERT INTO questions(chapterId,createdAt,questionNumber,responseType,commentQuestion,questionText) values (NOW(),?,?,?,?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setLong(1, question.getChapterId());
            ps.setString(2, question.getQuestionNumber());
            ps.setString(3, question.getResponseType());
            ps.setString(4, question.getCommentQuestion());
            ps.setString(5, question.getQuestionText());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Questions> findQuestionsById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findQuestionsById'");
    }

    @Override
    public List<Questions> listAllQuestions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAllQuestions'");
    }

    @Override
    public void updateQuestions(Questions question) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateQuestions'");
    }

    @Override
    public void deleteQuestions(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteQuestions'");
    }

}
