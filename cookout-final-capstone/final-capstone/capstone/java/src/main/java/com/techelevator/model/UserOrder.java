package com.techelevator.model;

public class UserOrder {

    private int orderId;
    private int userId;
    private int menuItemId;
    private String username;
    private int cookoutId;
    private String itemName;
    private String itemType;
    private String allergens;

    public UserOrder() { }

    public UserOrder(int orderId, int userId, int menuItemId) {
        this.orderId = orderId;
        this.userId = userId;
        this.menuItemId = menuItemId;
    }

    public UserOrder(int orderId, int userId, int menuItemId, String username, int cookoutId, String itemName, String itemType, String allergens){
        this.orderId = orderId;
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.username = username;
        this.cookoutId = cookoutId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.allergens = allergens;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCookoutId() {
        return cookoutId;
    }

    public void setCookoutId(int cookoutId) {
        this.cookoutId = cookoutId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", menuItemId=" + menuItemId +
                '}';
    }
}
