package com.example.furnituresmanage.ConnectDB.model;

public class Order {
    protected int id;

    protected String name;

    protected int quantity;

    protected float totalPrice;

    protected String images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Order(int id_fur, String name, String source, int quantity, float totalPrice, String images) {
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.images = images;
    }


    public Order(int id, String name, int quantity, float totalPrice, String images) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.images = images;
    }

}

