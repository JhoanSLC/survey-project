package com.surveyproject.chapter.infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.domain.service.ChapterService;
import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.screen.ScreenController;

public class ChapterRepository implements ChapterService {
    private final DatabaseConnection database = new DatabaseConnection();
    private final Connection con = database.connectDatabase();
    private final ScreenController scr = new ScreenController();

    @Override
    public void createChapters(Chapter chapter) {
        String createQuery = "INSERT INTO chapter(createdAt,surveyId,updatedAt,chapterNumber,chapterTitle) values (NOW(),?,NOW(),?,?)";
        // Using preparedStatement to prepare the query
        try (PreparedStatement ps = con.prepareStatement(createQuery)){
            // Insert every value to the prepared query
            ps.setLong(1, chapter.getSurveyId());
            ps.setString(2, chapter.getChapterNumber());
            ps.setString(3, chapter.getChapterTitle());
            ps.executeUpdate(); // Once all the query is finished, execute it
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Chapter> findChaptersById(long id) {
        String findByIdQuery = "SELECT id,createdAt,surveyId,updatedAt,chapterNumber,chapterTitle FROM chapter WHERE id = ? ";
        // Prepare the query
        try (PreparedStatement ps = con.prepareStatement(findByIdQuery)){
            // Set the id typed by the user into the WHERE statement
            ps.setLong(1, id);
            // Store the result of the query into a ResultSet type variable
            try (ResultSet rs = ps.executeQuery();){
                // If the resultSet returns a true (means that something was found and false if not)
                if (rs.next()){
                    // Create a new instance of the entity of this CRUD
                    Chapter result = new Chapter();
                    // Set the new instance's values with the result of the query
                    result.setId(rs.getLong("id"));
                    result.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    result.setSurveyId(rs.getLong("surveyId"));
                    result.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                    result.setChapterNumber(rs.getString("chapterNumber"));
                    result.setChapterTitle(rs.getString("chapterTitle"));
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
    public List<Chapter> listAllChapter() {
        String listAllQuery = "SELECT id,createdAt,surveyId,updatedAt,chapterNumber,chapterTitle FROM chapter";
        // Create the list that will store all the rows of a table
        List<Chapter> result = new ArrayList<>();
        // Prepare statement and store the result of the execute query in a ResultSet type variable
        try (PreparedStatement ps = con.prepareStatement(listAllQuery); ResultSet rs = ps.executeQuery()){
            // While the resulset is true
            while (rs.next()){
                // Create a new instance of the entity
                Chapter entity = new Chapter();
                entity.setId(rs.getLong("id"));
                entity.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                entity.setSurveyId(rs.getLong("surveyId"));
                entity.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                entity.setChapterNumber(rs.getString("chapterNumber"));
                entity.setChapterTitle(rs.getString("chapterTitle"));
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
    public void updateChapters(Chapter chapter) {
        String updateQuery = "UPDATE chapter SET surveyId = ?, updatedAt = NOW(), chapterNumber = ?, chapterTitle = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            // Replace the "?" in the query with the new values
            ps.setLong(1, chapter.getSurveyId());
            ps.setString(2, chapter.getChapterNumber());
            ps.setString(3, chapter.getChapterTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    @Override
    public void deleteChapters(long id) {
        String deleteQuery = "DELETE FROM chapter WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteQuery)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            scr.pause();
        }
    }

    
}
