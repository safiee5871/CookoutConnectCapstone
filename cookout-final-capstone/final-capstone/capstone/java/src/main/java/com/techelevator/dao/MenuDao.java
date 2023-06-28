package com.techelevator.dao;
import com.techelevator.model.Menu;

import java.util.List;

public interface MenuDao {

    Menu getMenuByItemId(int menuItemId);

    List <Menu> getMenuByCookoutId(int cookoutId);

    Menu createMenu(Menu menu, int cookoutId);

    Menu updateMenu(Menu menu);

    void deleteMenu(int cookoutId);

    List<Menu> getAllMenus();
}


