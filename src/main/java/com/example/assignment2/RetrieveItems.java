package com.example.assignment2;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrieveItems implements Serializable {
    String databaseLocation = "jdbc:sqlite:E:\\Dropbox\\Semester 6\\CSE 4636 - Web Architecture Lab\\Assignment 2\\database";

    public List<Item> getItemsFromDatabase() {
        String query = "SELECT * FROM items";
        List<Item> itemList = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(databaseLocation);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemID(resultSet.getInt("itemID"));
                item.setItemName(resultSet.getString("itemName"));
                item.setItemImage(resultSet.getString("itemImage"));
                itemList.add(item);
            }

            conn.close();
            statement.close();
            resultSet.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }



}
