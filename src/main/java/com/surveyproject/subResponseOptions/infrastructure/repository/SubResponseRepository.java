package com.surveyproject.subResponseOptions.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.screen.ScreenController;
import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.domain.service.SubResponseService;

public class SubResponseRepository implements SubResponseService {
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();
    

    @Override
    public void createSubResponse(SubResponseOptions subResponse) {
        String createQuery = "INSERT INTO subResponseOptions(subResponseNumber,responseOptionsId,subResponseText) values (?,?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setInt(1, subResponse.getSubResponseNumber());
            ps.setLong(2, subResponse.getResponseOptionsId());
            ps.setString(3,subResponse.getSubResponseText());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SubResponseOptions> findSubResponseById(long id) {
        String findByIdQuery = "SELECT id,subResponseNumber,createdAt,responseOptionsId,updatedAt,subResponseText FROM subResponseOptions WHERE id = ?";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    SubResponseOptions entityResult = new SubResponseOptions();
                    // Set the new instance's values with the result of the query
                    entityResult.setId(rs.getLong("id"));
                    entityResult.setSubResponseNumber(rs.getInt("subResponseNumber"));
                    entityResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    entityResult.setResponseOptionsId(rs.getLong("responseOptionsId"));
                    entityResult.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                    entityResult.setSubResponseText(rs.getString("subResponseText"));
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
    public List<SubResponseOptions> listAllSubResponses() {
        String listAllQuery = "SELECT id,subResponseNumber,createdAt,responseOptionsId,updatedAt,subResponseText FROM subResponseOptions ";
        // Create the list that will store all the rows of a table
        List<SubResponseOptions> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                SubResponseOptions entityResult = new SubResponseOptions();
                entityResult.setId(rs.getLong("id"));
                entityResult.setSubResponseNumber(rs.getInt("subResponseNumber"));
                entityResult.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                entityResult.setResponseOptionsId(rs.getLong("responseOptionsId"));
                entityResult.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                entityResult.setSubResponseText(rs.getString("subResponseText"));
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
    public void updateSubResponse(SubResponseOptions subResponse,long id) {
        String updateQuery = "UPDATE subResponseOptions SET subResponseNumber = ?, responseOptionsId = ?, subResponseText = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setInt(1, subResponse.getSubResponseNumber());
            ps.setLong(2, subResponse.getResponseOptionsId());
            ps.setString(3,subResponse.getSubResponseText());
            ps.setLong(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteSubResponse(long id) {
        String deleteQuery = "DELETE FROM subResponseOptions WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }



}
