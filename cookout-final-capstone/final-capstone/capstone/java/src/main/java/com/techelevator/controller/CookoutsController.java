package com.techelevator.controller;

import com.techelevator.dao.CookoutsDao;
import com.techelevator.model.Cookouts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cookout")

public class CookoutsController {

    @Autowired
    private CookoutsDao cookoutsDao;

    // 200, retrieved successfully!
    @GetMapping("/{cookoutId}")
    public Cookouts getCookoutDetailsById(@PathVariable int cookoutId){
        Cookouts cookouts = cookoutsDao.getCookoutDetailsById(cookoutId);
        if(cookouts == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cookout not found.");
        } else {
            return cookouts;
        }
    }

    @GetMapping("/mycookouts/{id}")
    public List<Cookouts> getCookoutsByUserId(@PathVariable int id) {
        List<Cookouts> cookoutsList = cookoutsDao.getCookoutsByUserId(id);
        return cookoutsList;
    }

    // 200
    @PostMapping("/newCookout")
    public Cookouts createCookout(@RequestBody Cookouts cookouts){
        System.out.println(cookouts.getDate());
        System.out.println(cookouts.getTime());
        Cookouts newCookout = cookoutsDao.createCookout(cookouts, cookouts.getCookoutId());
        return newCookout;
    }

    // 200
    @PutMapping("/update/{cookoutId}")
    public Cookouts updateCookout(@RequestBody Cookouts cookout, @PathVariable int cookoutId){
        System.out.println(cookout.getDate());
        System.out.println(cookout.getTime());
        Cookouts updatedCookout = cookoutsDao.updateCookout(cookout, cookoutId);
        if(updatedCookout == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cookout not found.");
        }
        return updatedCookout;
    }

// TODO
//    @DeleteMapping("/delete/{cookoutId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteCookout(@PathVariable int cookoutId) {
//        Cookouts existingCookout = (Cookouts) cookoutsDao.getCookoutDetailsById(cookoutId);
//        if (existingCookout == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cookout not found.");
//        }
//        cookoutsDao.deleteCookout(cookoutId);
//    }
}
