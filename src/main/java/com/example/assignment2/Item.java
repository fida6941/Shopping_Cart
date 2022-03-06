package com.example.assignment2;

import java.io.Serializable;

public class Item implements Serializable {
    private int itemID;
    private String itemName;
    private String itemImage;

    public int getItemID() {
        return  itemID;
    }

    void setItemID(int id) {
        itemID = id;
    }

    public String getItemName() {
        return  itemName;
    }

    void setItemName(String name) {
        itemName = name;
    }

    public String getItemImage() {return itemImage;}

    void setItemImage(String image) {itemImage = image;}
}
