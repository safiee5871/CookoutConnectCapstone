package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Menu;
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
public class JdbcMenuDao implements MenuDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMenuDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Menu getMenuByItemId(int menuItemId) {
        Menu menu = null;
        String sql = "SELECT * FROM menu WHERE menu_item_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, menuItemId);
            if(results.next()){
                menu = mapRowToMenu(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return menu;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menu;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Menu menu = mapRowToMenu(results);
                menus.add(menu);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving menus from the database.", e);
        }
        return menus;
    }

    @Override
    public Menu createMenu(Menu menu, int cookoutId) {
        String sql = "INSERT INTO menu (cookout_id, item_name, item_type, allergens) " +
                "VALUES (?, ?, ?, 'allergens') RETURNING menu_item_id;";
        Menu newMenu = null;
        try {
            int newMenuItemId = jdbcTemplate.queryForObject(sql, int.class,
                    menu.getCookoutId(), menu.getItemName(), menu.getItemType());
            newMenu = getMenuByItemId(newMenuItemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMenu;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        Menu updatedMenu = null;
        String sql = "UPDATE menu SET cookout_id = ?, " +
                "item_name = ?, item_type = ?, allergens = ? WHERE cookout_id = ?;";
//        try {
//            jdbcTemplate.update(sql, menu.getCookoutId(),
//                    menu.getItemName(), menu.getItemType(), menu.getAllergens(), menu.getCookoutId());
//            updatedMenu = getMenuByCookoutId(menu.getCookoutId());
//        }
//        catch (DataAccessException e) {
//            throw new DaoException("Error updating the menu.", e);
//        }
        return updatedMenu;
    }

    @Override
    public void deleteMenu(int cookoutId) {
        String sql = "DELETE FROM menu WHERE cookout_id = ?;";
        try {
            jdbcTemplate.update(sql, cookoutId);
        } catch (DataAccessException e) {
            throw new DaoException("Error deleting menu.", e);
        }
    }

    // get menu by cookout id
    @Override
    public List <Menu> getMenuByCookoutId(int cookoutId) {
        Menu menuByCookoutId = null;
        String sql = "SELECT * FROM menu WHERE cookout_id = ?;";
        List <Menu> menuList = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cookoutId);
            while(results.next()){
                 menuList.add(mapRowToMenu(results));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (BadSqlGrammarException e){
            throw new DaoException("SQL Syntax error.", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return menuList;
    }


    private Menu mapRowToMenu(SqlRowSet rs) {
        Menu menu = new Menu();
        menu.setMenuItemId(rs.getInt("menu_item_id"));
        menu.setCookoutId(rs.getInt("cookout_id"));
        menu.setItemName(rs.getString("item_name"));
        menu.setItemType(rs.getString("item_type"));
        menu.setAllergens(rs.getString("allergens"));
        return menu;
    }

}
