package com.techelevator.dao;

import com.techelevator.model.UserOrder;

import java.util.List;

public interface UserOrderDao {

    UserOrder getOrderById(int orderId);

    //see all orders by user id
    List<UserOrder> getOrdersByUserId(int userId);

    List<UserOrder> getOrderDetailsByCookoutId(int cookoutId);

    //create new order
    UserOrder createNewUserOrder(UserOrder userOrder, int orderId);

    //update user order
    UserOrder updateUserOrder(UserOrder userOrder);
}
