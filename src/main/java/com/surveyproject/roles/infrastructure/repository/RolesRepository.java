package com.surveyproject.roles.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.domain.service.RoleService;
import com.surveyproject.screen.ScreenController;

public class RolesRepository implements RoleService{
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();


    @Override
    public void createRole(Roles role) {
        String createQuery = "INSERT INTO roles(name) values (?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setString(1, role.getName());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Roles> findRoleById(long id) {
        String findByIdQuery = "SELECT id,name FROM roles WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    Roles entityResult = new Roles();
                    // Set the new instance's values with the result of the query
                    entityResult.setId(rs.getLong("id"));
                    entityResult.setName(rs.getString("name"));
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
    public List<Roles> listAllRoles() {
        String listAllQuery = "SELECT id,name FROM roles";
        // Create the list that will store all the rows of a table
        List<Roles> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                Roles entityResult = new Roles();
                entityResult.setId(rs.getLong("id"));
                entityResult.setName(rs.getString("name"));
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
    public void updateRole(Roles role,long id) {
        String updateQuery = "UPDATE roles SET name = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setString(1, role.getName());
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteRole(long id) {
        String deleteQuery = "DELETE FROM roles WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
