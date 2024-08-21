package com.surveyproject.usersRoles.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.screen.ScreenController;
import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.domain.service.UsersRoleService;

public class UsersRoleRepository implements UsersRoleService {
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();


    @Override
    public void createUserRole(UsersRoles userRole) {
        String createQuery = "INSERT INTO usersRoles(roledId,userId) values (?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setLong(1, userRole.getRoleId());
            ps.setLong(2, userRole.getUserId());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<UsersRoles> findUserRoleById(long roleId, long userId) {
        String findByIdQuery = "SELECT roleId,userId FROM usersRoles WHERE roleId = ? AND userId = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, roleId);
            ps.setLong(2,userId);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    UsersRoles entityResult = new UsersRoles();
                    // Set the new instance's values with the result of the query
                    entityResult.setRoleId(rs.getLong("roleId"));
                    entityResult.setUserId(rs.getLong("userId"));
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
    public List<UsersRoles> listAllUserRoles() {
        String listAllQuery = "SELECT roleId,userId FROM usersRoles";
        // Create the list that will store all the rows of a table
        List<UsersRoles> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                UsersRoles entityResult = new UsersRoles();
                entityResult.setRoleId(rs.getLong("roleId"));
                entityResult.setUserId(rs.getLong("userId"));
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
    public void updateUserRole(UsersRoles userRole) {
        String updateQuery = "UPDATE usersRoles SET roledId = ?, userId = ? WHERE roleId = ? AND userId = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteUserrole(long roleId, long userId) {
        String deleteQuery = "DELETE FROM usersRoles WHERE roledId = ? AND userId = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, roleId);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
´