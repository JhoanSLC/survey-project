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
        String createQuery = "INSERT INTO surveys(created_at,updated_at,description,name) values (?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            ps.setTimestamp(1, Timestamp.valueOf(survey.getCreated_at()));
            ps.setTimestamp(2, Timestamp.valueOf(survey.getUpdated_at()));
            ps.setString(3, survey.getDescription());
            ps.setString(4, survey.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Surveys> findSurveysById(long id) {
        String findByIdQuery = "SELECT id,created_at,updated_at,description,name FROM surveys WHERE id = ? ";

        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery();){
                if (rs.next()){
                    Surveys resultSurvey = new Surveys();
                    resultSurvey.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                    resultSurvey.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
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
        String listAllQuery = "SELECT id,created_at,updated_at,description,name FROM surveys";
        List<Surveys> resultSurveys = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Surveys survey = new Surveys();
                survey.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                survey.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                survey.setDescription(rs.getString("description"));
                survey.setName(rs.getString("name"));
                resultSurveys.add(survey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSurveys;
    }

    @Override
    public void updateSurveys(Surveys survey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSurveys'");
    }

    @Override
    public void deleteSurveys(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSurveys'");
    }

}
