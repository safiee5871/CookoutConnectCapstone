package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {

    public MenuController(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Autowired
    private MenuDao menuDao;

    // 200 - ok
    @GetMapping("/all")
    public List<Menu> getAllMenus() {
        return menuDao.getAllMenus();
    }

    // 200 - ok
    @RequestMapping(path= "/{menuItemId}", method = RequestMethod.GET)
    public Menu getMenuByItemId(@PathVariable int menuItemId) {
        Menu menu = menuDao.getMenuByItemId(menuItemId);
        return menu;
    }

    // 200 - ok
    @GetMapping("/cookoutId/{cookoutId}")
    public List <Menu> getMenuByCookoutId (@PathVariable int cookoutId) {
        List <Menu> menu = menuDao.getMenuByCookoutId(cookoutId);
        if(menu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found.");
        }
        System.out.println(menu.get(0).getItemName());
        return menu;
    }

    // 200 - ok
    @PostMapping("/newItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Menu createMenu(@RequestBody Menu menu) {
        return menuDao.createMenu(menu, menu.getCookoutId());
    }

    // 200 - ok
    @PutMapping("/update/{cookoutId}")
    public Menu updateMenu( @RequestBody Menu menu) {
        Menu existingMenu = menuDao.updateMenu(menu);
        return existingMenu;
    }

// TODO
//    @DeleteMapping("/delete/{cookoutId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteMenu(@PathVariable int cookoutId) {
//       Menu existingMenu = (Menu) menuDao.getMenuByCookoutId(cookoutId);
//        if (existingMenu == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found.");
//        }
//        menuDao.deleteMenu(cookoutId);
//    }
}