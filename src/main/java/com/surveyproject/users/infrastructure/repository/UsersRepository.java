package com.surveyproject.users.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.screen.ScreenController;
import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.domain.service.UserService;

public class UsersRepository implements UserService {
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();


    @Override
    public void createUsert(Users user) {
        String createQuery = "INSERT INTO users(username,password) values (?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getPassword());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Users> findUserById(long id) {
        String findByIdQuery = "SELECT id,enabled,username,password FROM users WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    Users entityResult = new Users();
                    // Set the new instance's values with the result of the query
                    entityResult.setId(rs.getLong("id"));
                    entityResult.setEnabled(rs.getBoolean("enabled"));
                    entityResult.setUsername(rs.getString("username"));
                    entityResult.setPassword(rs.getString("password"));
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
    public List<Users> listAllUser() {
        String listAllQuery = "SELECT id,enabled,username,password FROM users";
        // Create the list that will store all the rows of a table
        List<Users> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                Users entityResult = new Users();
                entityResult.setId(rs.getLong("id"));
                entityResult.setEnabled(rs.getBoolean("enabled"));
                entityResult.setUsername(rs.getString("username"));
                entityResult.setPassword(rs.getString("password"));
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
    public void updateUser(Users user) {
        String updateQuery = "UPDATE users SET enabled = ?, username = ?, password = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setBoolean(1, user.getEnabled());
            ps.setString(2, user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setLong(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteUser(long id) {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
