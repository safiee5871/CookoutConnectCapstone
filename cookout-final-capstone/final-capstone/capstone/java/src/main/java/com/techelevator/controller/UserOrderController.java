package com.techelevator.controller;

import com.techelevator.dao.UserOrderDao;
import com.techelevator.model.User;
import com.techelevator.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userOrder")
public class UserOrderController {

    @Autowired
    private UserOrderDao userOrderDao;

    @GetMapping("/orders/{cookoutId}")
    public List<UserOrder> getOrderDetailsByCookoutId(@PathVariable int cookoutId){
        List<UserOrder> userOrders = userOrderDao.getOrderDetailsByCookoutId(cookoutId);
        return userOrders;
    }

    // 200 ok
    @GetMapping("/orders/{orderId}")
    public UserOrder getUserOrderById (@PathVariable int orderId){
        UserOrder userOrder = userOrderDao.getOrderById(orderId);
        if(userOrder == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User order not found.");
        } else {
            return userOrder;
        }
    }

    // 200 ok
    @GetMapping("/{userId}")
    public List<UserOrder> getOrdersByUserId(@PathVariable int userId){
        List<UserOrder> userOrdersList = userOrderDao.getOrdersByUserId(userId);
        if(userOrdersList == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User orders not found.");
        } else {
            return userOrdersList;
        }
    }

    // 200 ok
    @PostMapping("/newOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public UserOrder createNewUserOrder(@RequestBody UserOrder userOrder) {
        return userOrderDao.createNewUserOrder(userOrder, userOrder.getOrderId());
    }

    // 200 - ok
    @RequestMapping(path = "/update/{orderId}", method = RequestMethod.PUT)
    public UserOrder update(@RequestBody UserOrder userOrder, @PathVariable int orderId) {
        UserOrder updatedOrder = userOrderDao.updateUserOrder(userOrder);
        if (updatedOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User order not found.");
        } else {
            return updatedOrder;
        }
    }

}
