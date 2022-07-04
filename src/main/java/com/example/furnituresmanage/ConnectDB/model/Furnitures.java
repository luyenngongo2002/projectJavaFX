package com.example.furnituresmanage.ConnectDB.model;

import javafx.scene.control.cell.PropertyValueFactory;

public class Furnitures {
    public int id_fur;

    public String name;
    public String source;
    public int quantity;
    public float price;
    public String images;
    private int minWidth;

    public int getId_fur() {
        return id_fur;
    }

    public void setId_fur(int id_fur) {
        this.id_fur = id_fur;
    }

    public Furnitures(int id_fur, String name, String source, int quantity, float price, String images) {
            this.id_fur= id_fur;
            this.name = name;
            this.source = source;
            this.quantity = quantity;
            this.price = price;
            this.images=images;

    }

    public Furnitures(String name, String source, int quantity, float price, String images) {
        this.name = name;
        this.source = source;
        this.quantity = quantity;
        this.price = price;
        this.images=images;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuantity() {return quantity;}

    public void setQuantity(int Quantity) {
        this.quantity = quantity;
    }

    public String getImages() {
        return images;
    }
    public void setImages(String images) {this.images = images;
    }

    public void setCellValueFactory(PropertyValueFactory<Object, Object> id_fur) {
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinWidth() {
        return minWidth;
    }
}

