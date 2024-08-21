package com.surveyproject.categoriesCatalog.infrasctructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;
import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.screen.ScreenController;

public class CategoriesCatalogRepository implements CategoriesCatalogService {
    private final DatabaseConnection database = new DatabaseConnection();
    private Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();
    @Override
    public void createCategories(CategoriesCatalog category) {
        String createQuery = "INSERT INTO categoriesCatalog(createdAt,updatedAt,name) values (NOW(),NOW(),?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setString(1, category.getName());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CategoriesCatalog> finCategoriesById(long id) {
        String findByIdQuery = "SELECT id,createdAt,updatedAt,name FROM categoriesCatalog WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    CategoriesCatalog result = new CategoriesCatalog();
                    // Set the new instance's values with the result of the query
                    result.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    result.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                    result.setName(rs.getString("name"));
                    // At the end, return the optional of the instance of entity
                    return Optional.of(result);
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
    public List<CategoriesCatalog> listAllCategories() {
        String listAllQuery = "SELECT id,createdAt,updatedAt,name FROM categoriesCatalog";
        // Create the list that will store all the rows of a table
        List<CategoriesCatalog> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                CategoriesCatalog entity = new CategoriesCatalog();
                entity.setId(rs.getLong("id"));
                entity.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                entity.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                entity.setName(rs.getString("name"));
                // Add the new instance to the list
                result.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return the list of entitities
        return result;
    }

    @Override
    public void updateCategories(CategoriesCatalog category) {
        String updateQuery = "UPDATE categoriesCatalog SET updatedAt = NOW(), name = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setString(1, category.getName());
            ps.setLong(2, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteCategories(long id) {
        String deleteQuery = "DELETE FROM categoriesCatalog WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
