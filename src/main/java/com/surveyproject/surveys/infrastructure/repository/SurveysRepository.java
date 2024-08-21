package com.surveyproject.surveys.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.screen.ScreenController;
import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.domain.service.SurveysService;

public class SurveysRepository implements SurveysService{
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    
    private final ScreenController scr = new ScreenController();

    @Override
    public void createSurveys(Surveys survey) {
        String createQuery = "INSERT INTO surveys(description,name) values (?,?)";

        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            ps.setString(1, survey.getDescription());
            ps.setString(2, survey.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Surveys> findSurveysById(long id) {
        String findByIdQuery = "SELECT id,createdAt,updatedAt,description,name FROM surveys WHERE id = ? ";

        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery();){
                if (rs.next()){
                    Surveys resultSurvey = new Surveys();
                    resultSurvey.setCreated_at(rs.getTimestamp("createdAt").toLocalDateTime());
                    resultSurvey.setUpdated_at(rs.getTimestamp("updatedAt").toLocalDateTime());
                    resultSurvey.setDescription(rs.getString("description"));
                    resultSurvey.setName(rs.getString("name"));
                    return Optional.of(resultSurvey);
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
        return Optional.empty();
        
    }

    @Override
    public List<Surveys> listAllSurveys() {
        String listAllQuery = "SELECT id,createdAt,updatedAt,description,name FROM surveys";
        List<Surveys> resultSurveys = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Surveys survey = new Surveys();
                survey.setId(rs.getLong("id"));
                survey.setCreated_at(rs.getTimestamp("createdAt").toLocalDateTime());
                survey.setUpdated_at(rs.getTimestamp("updatedAt").toLocalDateTime());
                survey.setDescription(rs.getString("description"));
                survey.setName(rs.getString("name"));
                resultSurveys.add(survey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
        return resultSurveys;
    }

    @Override
    public void updateSurveys(Surveys survey, long id) {
        String updateQuery = "UPDATE surveys SET description = ?, name = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, survey.getDescription());
            ps.setString(2, survey.getName());
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
}

    @Override
    public void deleteSurveys(long id) {
        String deleteQuery = "DELETE FROM surveys WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

}
