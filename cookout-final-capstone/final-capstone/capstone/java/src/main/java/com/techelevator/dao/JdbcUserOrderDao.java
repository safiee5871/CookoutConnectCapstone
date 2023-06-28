package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import com.techelevator.model.UserCookout;
import com.techelevator.model.UserOrder;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserOrderDao implements UserOrderDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserOrder createNewUserOrder(UserOrder userOrder, int orderId) {
        String sql = "INSERT INTO user_order (user_id, menu_item_id) " +
                "VALUES (?,?) RETURNING order_id;";
        UserOrder newOrder = null;
        try {
            int newOrderId = jdbcTemplate.queryForObject(sql, int.class, userOrder.getUserId(), userOrder.getMenuItemId());
            newOrder = getOrderById(newOrderId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newOrder;
    }

    @Override
    public UserOrder updateUserOrder(UserOrder userOrder) {
        UserOrder updatedOrder;
        String sql = "UPDATE user_order SET order_id = ?, user_id = ?, " +
                "menu_item_id = ? WHERE order_id = ?;";
        try {
            jdbcTemplate.update(sql, userOrder.getOrderId(), userOrder.getUserId(),
                    userOrder.getMenuItemId(), userOrder.getOrderId());
            updatedOrder = getOrderById(userOrder.getUserId());
        }
        catch (DataAccessException e) {
            throw new DaoException("Error updating the user order", e);
        }
        return updatedOrder;
    }

    @Override
    public List<UserOrder> getOrderDetailsByCookoutId(int cookoutId){
        List<UserOrder> userOrderList = new ArrayList<>();
        String sql = "SELECT uo.order_id, m.cookout_id, uo.user_id, u.username, m.menu_item_id, m.item_name, m.item_type, m.allergens " +
                "FROM user_order AS uo " +
                "JOIN menu AS m ON uo.menu_item_id = m.menu_item_id " +
                "JOIN users AS u ON uo.user_id = u.user_id " +
                "WHERE cookout_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cookoutId);
            if(results.next()){
                UserOrder userOrders = new UserOrder();
                userOrders.setOrderId(results.getInt("order-id"));
                userOrders.setCookoutId(results.getInt("cookout-id"));
                userOrders.setUserId(results.getInt("user-id"));
                userOrders.setUsername(results.getString("username"));
                userOrders.setMenuItemId(results.getInt("menu-item-id"));
                userOrders.setItemName(results.getString("item_name"));
                userOrders.setItemType(results.getString("item_type"));
                userOrders.setAllergens(results.getString("allergens"));
                userOrderList.add(userOrders);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return userOrderList;
    }

    @Override
    //Returns an order by order id
    public UserOrder getOrderById(int orderId) {
        UserOrder userOrder = null;
        String sql = "SELECT * FROM user_order WHERE order_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            if(results.next()){
                userOrder = mapRowToUserOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return userOrder;
    }

    //Returns all orders by user id
    @Override
    public List<UserOrder> getOrdersByUserId(int userId) {
        List<UserOrder> ordersByUserId = new ArrayList<>();
        String sql = " SELECT * FROM user_order WHERE user_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                UserOrder orders = mapRowToUserOrder(results);
                ordersByUserId.add(orders);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return ordersByUserId;
    }

    private UserOrder mapRowToUserOrder(SqlRowSet rs) {
        UserOrder orders = new UserOrder();
        orders.setOrderId(rs.getInt("order_id"));
        orders.setUserId(rs.getInt("user_id"));
        orders.setMenuItemId(rs.getInt("menu_item_id"));
        return orders;
    }
}
