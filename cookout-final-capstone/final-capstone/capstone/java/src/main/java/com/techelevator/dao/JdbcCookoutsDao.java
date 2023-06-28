package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cookouts;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCookoutsDao implements CookoutsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCookoutsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cookouts getCookoutDetailsById(int cookoutId) {
        Cookouts cookouts = null;
        String sql = "SELECT * FROM cookout WHERE cookout_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cookoutId);
            if(results.next()){
                cookouts = mapRowToCookouts(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return cookouts;
    }

    @Override
    public Cookouts createCookout(Cookouts cookouts, int cookoutId) {
        String sql = "INSERT INTO cookout (cookout_name, date, time, location) " +
                "VALUES (?,?,?,?) RETURNING cookout_id";
        Cookouts newCookout = null;
        try {
            int newCookoutId = jdbcTemplate.queryForObject(sql, int.class, cookouts.getCookoutName(),
                    cookouts.getDate(), cookouts.getTime(), cookouts.getLocation());
            newCookout = getCookoutDetailsById(newCookoutId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCookout;
    }

    @Override
    public Cookouts updateCookout(Cookouts cookouts, int cookoutId) {
        Cookouts updatedCookout = new Cookouts();
        String sql = "UPDATE cookout SET " +
                "date = ?, time = ?, location = ? WHERE cookout_id = ?;";
        try {
            jdbcTemplate.update(sql,
                    cookouts.getDate(), cookouts.getTime(), cookouts.getLocation(), cookoutId);
            updatedCookout = getCookoutDetailsById(cookoutId);
        }
        catch (DataAccessException e) {
            throw new DaoException("Error updating cookout.", e);
        }
        return updatedCookout;
    }

    @Override
    public List<Cookouts> getCookoutsByUserId(int userId) {
        List<Cookouts> cookoutsList = new ArrayList<>();
        String sql = "SELECT * FROM cookout" +
                " JOIN user_cookout ON cookout.cookout_id = user_cookout.cookout_id" +
                " JOIN users ON user_cookout.user_id = users.user_id" +
                " WHERE users.user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()){
                Cookouts newCookout = new Cookouts();
                newCookout.setCookoutId(results.getInt("cookout_id"));
                newCookout.setCookoutName(results.getString("cookout_name"));
                newCookout.setDate(results.getDate("date").toLocalDate());
                newCookout.setTime(results.getTime("time").toLocalTime());
                newCookout.setLocation(results.getString("location"));
                newCookout.setCookoutRole(results.getString("cookout_role"));
                newCookout.setUserNotes(results.getString("user_notes"));

                cookoutsList.add(newCookout);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return cookoutsList;
    }

//    @Override
//    public List<Cookouts> getCookoutsByUserId(int userId) {
//        List<Cookouts> cookoutsList = new ArrayList<>();
//        String sql = "SELECT * FROM cookout" +
//                " JOIN user_cookout ON cookout.cookout_id = user_cookout.cookout_id" +
//                " JOIN users ON user_cookout.user_id = users.user_id" +
//                " WHERE users.user_id = ?";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//            while(results.next()){
//                cookoutsList.add(mapRowToCookouts(results));
//            }
//        } catch (CannotGetJdbcConnectionException e){
//            throw new DaoException("Unable to connect to server or database.", e);
//        } catch (BadSqlGrammarException e){
//            throw new DaoException("SQL Syntax error.", e);
//        } catch (DataIntegrityViolationException e){
//            throw new DaoException("Data integrity violation", e);
//        }
//        return cookoutsList;
//    }

//    @Override
//    public void deleteCookout(int cookoutId) {
//        String sql = "DELETE FROM cookout WHERE cookout_id = ?;";
//        try {
//            jdbcTemplate.update(sql, cookoutId);
//        } catch (DataAccessException e) {
//            throw new DaoException("Error deleting cookout.", e);
//        }
//    }

    private Cookouts mapRowToCookouts(SqlRowSet rs) {
        Cookouts cookouts = new Cookouts();
        cookouts.setCookoutId(rs.getInt("cookout_id"));
        cookouts.setCookoutName(rs.getString("cookout_name"));
        if(rs.getDate("date") != null) {
            cookouts.setDate(rs.getDate("date").toLocalDate());
        }
        if(rs.getTime("time") != null) {
            cookouts.setTime(rs.getTime("time").toLocalTime());
        }
            cookouts.setLocation(rs.getString("location"));
            return cookouts;

    }
}
