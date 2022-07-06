package com.example.furnituresmanage.ConnectDB;

import com.example.furnituresmanage.ConnectDB.model.Admin;
import com.example.furnituresmanage.ConnectDB.model.Furnitures;
import com.example.furnituresmanage.ConnectDB.model.Order;


import java.sql.*;
import java.util.ArrayList;


public class DBConnection {
    public Connection connection;
    public DBConnection(){

        try {
            System.out.println("Start Connect!");
            connection= DriverManager.getConnection("jdbc:mysql://localhost/dbdatajavafx", "root", "");
            System.out.println("Connect!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Furnitures> getFurnitures(){

        ArrayList<Furnitures> list = new ArrayList<>();
        String sql = "SELECT*FROM furniture_manage";
        try {
            ResultSet results= connection.prepareStatement(sql).executeQuery();
            while(results.next()){
                Furnitures std = new Furnitures(
                        results.getInt("id_fur"),
                        results.getString("name"),
                        results.getString("source"),
                        results.getInt("quantity"),
                        results.getFloat("price"),
                        results.getString("images")
                );
                // add Dl vao
                list.add(std);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void insertFurnitures(Furnitures std){
        String sql = "INSERT INTO furniture_manage( name, source, quantity, price, images) VALUE ('"+ std.name +"', '"+ std.source + "', '"+ std.quantity +"', '"+ std.price+ "', '"+ std.images +"')";
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
            System.out.println("Inserted new furtures");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateFurnitures(Furnitures std){
//        String sql = "UPDATE furniture_manage SET name = '" + std.name+ "', source = '" + std.source +  "', quantity = '"+std.quantity+ "', price= " + std.price +  "', images= '" + std.images + " WHERE id_fur ="+ std.id_fur;
        String sql = String.format("UPDATE furniture_manage SET name ='%s', source ='%s', quantity=%d, price = %f, images='%s' WHERE id_fur = %d",std.name, std.source, std.quantity, std.price, std.images, std.id_fur);
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
            System.out.println("Updated a furtures");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void deleteFurnitures(int id_fur){
        String sql = "DELETE FROM furniture_manage WHERE id_fur =" + id_fur;
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
            System.out.println("Deleted a furtures");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Admin> getAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            var result = this.connection.prepareStatement("Select * from admin").executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String password = result.getString("password");
                System.out.println(name);
                System.out.println(password);
                admins.add(new Admin( name, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }

}
