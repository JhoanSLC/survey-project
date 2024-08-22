package com.surveyproject.responseOptions.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;
import com.surveyproject.screen.ScreenController;

public class ResponseOptionsRepository implements ResponseOptionsService{
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();

    @Override
    public void createResponse(ResponseOptions response) {
            String createQuery = "INSERT INTO responseOptions(optionValue,categoryCatalogId,parentResponseId,questionId,typeComponentHtml,commentResponse,optionText) values (?,?,?,?,?,?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setInt(1, response.getOptionValue());
            ps.setLong(2, response.getCategoryCatalogId());
            ps.setLong(3,response.getParentResponseId());
            ps.setLong(4,response.getQuestionId());
            ps.setString(5,response.getTypeComponentHtml());
            ps.setString(6,response.getCommentResponse());
            ps.setString(7,response.getOptionText());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ResponseOptions> findResponseById(long id) {
        String findByIdQuery = "SELECT id,optionValue,categoryCatalogId,createdAt,parentResponseId,questionId,updatedAt,commentResponse,optionText FROM responseOptions WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    ResponseOptions entityResult = new ResponseOptions();
                    // Set the new instance's values with the result of the query
                    entityResult.setId(rs.getLong("id"));
                    entityResult.setOptionValue(rs.getInt("optionValue"));
                    entityResult.setCategoryCatalogId(rs.getLong("categoryCatalogId"));
                    entityResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    entityResult.setParentResponseId(rs.getLong("parentResponseId"));
                    entityResult.setQuestionId(rs.getLong("questionId"));
                    entityResult.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                    entityResult.setCommentResponse(rs.getString("commentResponse"));
                    entityResult.setOptionText(rs.getString("optionText"));
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
    public List<ResponseOptions> listAllResponses() {
        String listAllQuery = "SELECT id,optionValue,categoryCatalogId,createdAt,parentResponseId,questionId,updatedAt,commentResponse,optionText FROM responseOptions";
        // Create the list that will store all the rows of a table
        List<ResponseOptions> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                ResponseOptions entityResult = new ResponseOptions();
                entityResult.setId(rs.getLong("id"));
                entityResult.setOptionValue(rs.getInt("optionValue"));
                entityResult.setCategoryCatalogId(rs.getLong("categoryCatalogId"));
                entityResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                entityResult.setParentResponseId(rs.getLong("parentResponseId"));
                entityResult.setQuestionId(rs.getLong("questionId"));
                entityResult.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                entityResult.setCommentResponse(rs.getString("commentResponse"));
                entityResult.setOptionText(rs.getString("optionText"));
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
    public void updateResponse(ResponseOptions response,long id) {
        String updateQuery = "UPDATE responseOptions SET optionValue = ?,categoryCatalogId = ?, parentResponseId = ?, questionId = ?, commentResponse = ?, optionText = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setInt(1,response.getOptionValue());
            ps.setLong(2,response.getCategoryCatalogId());
            ps.setLong(3,response.getParentResponseId());
            ps.setLong(4,response.getQuestionId());
            ps.setString(5,response.getCommentResponse());
            ps.setString(6,response.getOptionText());
            ps.setLong(7, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteResponse(long id) {
        String deleteQuery = "DELETE FROM responseOptions WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
