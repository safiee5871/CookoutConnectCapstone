package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.UserCookout;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcUserCookoutsDao implements UserCookoutsDao {

    //jdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserCookoutsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserCookout getUserCookoutById(int cookoutId, int userId) {
        UserCookout userCookout = null;
        String sql = "SELECT * FROM user_cookout WHERE user_id = ? AND cookout_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, cookoutId);
            if(results.next()){
                userCookout = mapRowToUserCookouts(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return userCookout;
    }

    //Methods
    @Override
    //Returns cookout details by id
    public List<UserCookout> getGuestListCookoutById(int cookoutId) {
        List<UserCookout> userCookoutList = new ArrayList<>();
        String sql = "SELECT * FROM users JOIN user_cookout ON users.user_id = user_cookout.user_id" +
                " WHERE cookout_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cookoutId);
            while(results.next()){
                UserCookout userCookouts = new UserCookout();
                userCookouts.setUserId(results.getInt("user_id"));
                userCookouts.setUsername(results.getString("username"));
                userCookouts.setCookoutId(results.getInt("cookout_id"));
                userCookouts.setCookoutRole(results.getString("cookout_role"));
                userCookouts.setRsvp(results.getBoolean("rsvp"));
                userCookoutList.add(userCookouts);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return userCookoutList;
    }

//    @Override
//    //Returns a list of Cookouts by user id
//    public List<UserCookout> getUserCookoutsByUserId(int userId) {
//        List<UserCookout> cookoutsByUserId = new ArrayList<>();
//        String sql = "SELECT cookout_id FROM user_cookout WHERE user_id = ?;";
//
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//            while (results.next()) {
//                UserCookout cookouts = mapRowToUserCookouts(results);
//                cookoutsByUserId.add(cookouts);
//            }
//        } catch (CannotGetJdbcConnectionException e){
//            throw new DaoException("Unable to connect to server or database.", e);
//        } catch (BadSqlGrammarException e){
//            throw new DaoException("SQL Syntax error.", e);
//        } catch (DataIntegrityViolationException e){
//            throw new DaoException("Data integrity violation", e);
//        }
//        return cookoutsByUserId;
//    }

//    @Override
//    //Returns a list of users by cookout id
//    public List<UserCookout> getUsersByCookoutId(int cookoutId) {
//        List<UserCookout> usersByCookoutId = new ArrayList<>();
//        String sql = "SELECT user_id FROM user_cookout WHERE cookout_id = ?;";
//
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cookoutId);
//            while(results.next()){
//                UserCookout cookouts = mapRowToUserCookouts(results);
//                usersByCookoutId.add(cookouts);
//            }
//        } catch (CannotGetJdbcConnectionException e){
//            throw new DaoException("Unable to connect to server or database.", e);
//        } catch (BadSqlGrammarException e){
//            throw new DaoException("SQL Syntax error.", e);
//        } catch (DataIntegrityViolationException e){
//            throw new DaoException("Data integrity violation", e);
//        }
//
//        return usersByCookoutId;
//    }

    @Override
    //Create new cookout
//TODO
    public boolean createUserCookout(UserCookout userCookout, int cookoutId) {
        String sql = "INSERT INTO user_cookout (user_id, cookout_id, cookout_role, rsvp) " +
                "VALUES (?,?,?,?);";

        return jdbcTemplate.update(sql, userCookout.getUserId(),
                userCookout.getCookoutId(), userCookout.getCookoutRole(), userCookout.getRsvp()) == 1;
//        try {
////            newCookout = getCookoutById(newCookoutId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (BadSqlGrammarException e) {
//            throw new DaoException("SQL syntax error", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
    }

    @Override
    //Update existing cookout
    public UserCookout updateUserCookoutRSVP(UserCookout userCookout, int id, boolean rsvp, int cookoutId) {
        UserCookout updatedCookout = null;
        String sql = "UPDATE user_cookout SET rsvp = ? WHERE user_id = ? AND cookout_id = ?;";

        try{
            jdbcTemplate.update(sql, rsvp, id, cookoutId);
            updatedCookout = getUserCookoutById(cookoutId, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCookout;
    }

    @Override
    //Update existing cookout
    public UserCookout updateUserCookout(UserCookout cookout, int cookoutId) {
        UserCookout updatedCookout = null;
        String sql = "UPDATE user_cookout SET cookout_role = ? WHERE user_id = ?;";

        try{
            jdbcTemplate.update(sql, cookout.getCookoutRole(), cookout.getUserId());
            updatedCookout = getUserCookoutById(cookoutId, cookout.getUserId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCookout;
    }

    private UserCookout mapRowToUserCookouts(SqlRowSet rs) {
        UserCookout cookouts = new UserCookout();
        cookouts.setUserId(rs.getInt("user_id"));
        cookouts.setCookoutId(rs.getInt("cookout_id"));
        cookouts.setCookoutRole(rs.getString("cookout_role"));
        cookouts.setRsvp(rs.getBoolean("rsvp"));
        return cookouts;
    }
}
