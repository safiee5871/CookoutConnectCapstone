package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu {

    private int menuItemId;
    @JsonProperty("cookoutId")
    private int cookoutId;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("itemType")
    private String itemType;
    private String allergens;

    public Menu () {  }

    public Menu(int menuItemId, int cookoutId, String itemName, String itemType, String allergens) {
        this.menuItemId = menuItemId;
        this.cookoutId = cookoutId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.allergens = allergens;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
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
        return "MenuItems{" +
                "menuItemId=" + menuItemId +
                "cookoutId=" + cookoutId +
                ", itemName=" + itemName +
                ", itemType=" + itemType +
                ", allergens=" + allergens +
                '}';
    }

}
