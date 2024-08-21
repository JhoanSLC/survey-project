package com.surveyproject.responseQuestion.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;
import com.surveyproject.screen.ScreenController;

public class ResponseQuestionRepository implements ResponseQuestionService{
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();


    @Override
    public void createResponseQuestions(ResponseQuestion responseQuestion) {
        String createQuery = "INSERT INTO responseQuestion(responseId,subResponsesId,responseText) values (?,?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setLong(1, responseQuestion.getResponseId());
            ps.setLong(2,responseQuestion.getSubResponsesId());
            ps.setString(3, responseQuestion.getResponseText());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ResponseQuestion> findResponseQuestionById(long id) {
        String findByIdQuery = "SELECT id,responseId,subResponsesId,responseText  FROM responseQuestion WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    ResponseQuestion entityResult = new ResponseQuestion();
                    // Set the new instance's values with the result of the query
                    entityResult.setId(rs.getLong("id"));
                    entityResult.setResponseId(rs.getLong("responseId"));
                    entityResult.setSubResponsesId(rs.getLong("subResponsesId"));
                    entityResult.setResponseText(rs.getString("responseText"));
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
    public List<ResponseQuestion> listAllResponseQuestions() {
        String listAllQuery = "SELECT id,responseId,subResponsesId,responseText FROM responseQuestion";
        // Create the list that will store all the rows of a table
        List<ResponseQuestion> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                ResponseQuestion entityResult = new ResponseQuestion();
                entityResult.setId(rs.getLong("id"));
                entityResult.setResponseId(rs.getLong("responseId"));
                entityResult.setSubResponsesId(rs.getLong("subResponsesId"));
                entityResult.setResponseText(rs.getString("responseText"));
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
    public void updateResponseQuestion(ResponseQuestion responseQuestion) {
        String updateQuery = "UPDATE responseQuestion SET responseId = ?,subResponsesId = ?,responseText = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setLong(1, responseQuestion.getResponseId());
            ps.setLong(2, responseQuestion.getSubResponsesId());
            ps.setString(3, responseQuestion.getResponseText());
            ps.setLong(4, responseQuestion.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteResponseQuestion(long id) {
        String deleteQuery = "DELETE FROM responseQuestion WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
