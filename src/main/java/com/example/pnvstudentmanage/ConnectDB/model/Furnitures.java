package com.example.pnvstudentmanage.ConnectDB.model;

public class Furnitures {
        public int id_fur;
        public String name;
        public String source;
        public int quantity;
        public float price;


    public Furnitures(int id_fur, String name, String source, int quantity, float price) {
            this.id_fur= id_fur;
            this.name = name;
            this.source = source;
            this.quantity = quantity;
            this.price = price;

    }

    public Furnitures(String name, String source, int quantity, float price) {
        this.name = name;
        this.source = source;
        this.quantity = quantity;
        this.price = price;
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

}

