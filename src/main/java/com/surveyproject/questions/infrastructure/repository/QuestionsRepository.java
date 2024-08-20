package com.surveyproject.questions.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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
        String createQuery = "INSERT INTO questions(chapterId,questionNumber,responseType,commentQuestion,questionText) values (?,?,?,?,?)";
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
        String findByIdQuery = "SELECT id,chapterId,createdAd,updatedAd,questionNumber,responseType,commentQuestions,questionText FROM questions WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    Questions entityResult = new Questions();
                    // Set the new instance's values with the result of the query
                    entityResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    entityResult.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                    entityResult.setId(rs.getLong("id"));
                    entityResult.setChapterId(rs.getLong("chapterId"));
                    entityResult.setQuestionNumber(rs.getString("questionNumber"));
                    entityResult.setResponseType(rs.getString("responseType"));
                    entityResult.setCommentQuestion(rs.getString("commentQuestion"));
                    entityResult.setQuestionText(rs.getString("questionText"));
                    // At the end, return the optional of the instance of entity
                    return Optional.of(entityResult);
                }
            } catch (Exception e) {
                scr.clean();
                System.out.println("Error at result set line 41 - SurveysRepository");
                e.printStackTrace();
                scr.pause();
            }
        } catch (Exception e) {
            scr.clean();
            e.printStackTrace();
            scr.pause();
        }
        // If nothing was found, then return an optional empty
        return Optional.empty();
    }

    @Override
    public List<Questions> listAllQuestions() {
        String listAllQuery = "SELECT id,chapterId,createdAd,updatedAd,questionNumber,responseType,commentQuestions,questionText FROM questions";
        // Create the list that will store all the rows of a table
        List<Questions> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                Questions entityResult = new Questions();
                entityResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                entityResult.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                entityResult.setId(rs.getLong("id"));
                entityResult.setChapterId(rs.getLong("chapterId"));
                entityResult.setQuestionNumber(rs.getString("questionNumber"));
                entityResult.setResponseType(rs.getString("responseType"));
                entityResult.setCommentQuestion(rs.getString("commentQuestion"));
                entityResult.setQuestionText(rs.getString("questionText"));
                // Add the new instance to the list
                result.add(entityResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return the list of entitities
        return result;
    }

    @Override
    public void updateQuestions(Questions question) {
        String updateQuery = "UPDATE questions SET chapterId = ?, updatedAt = NOW(), questionNumber = ?,responseType = ?, commentQuestion = ?, questionText = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setLong(1, question.getChapterId());
            ps.setString(2,question.getQuestionNumber());
            ps.setString(3, question.getResponseType());
            ps.setString(4,question.getCommentQuestion());
            ps.setString(5, question.getQuestionText());
            ps.setLong(6, question.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteQuestions(long id) {
        String deleteQuery = "DELETE FROM questions WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
