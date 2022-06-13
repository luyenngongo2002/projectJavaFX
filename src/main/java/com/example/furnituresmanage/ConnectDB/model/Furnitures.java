package com.example.furnituresmanage.ConnectDB.model;

public class Furnitures {
        public int id_fur;
        public String name;
        public String source;
        public int quantity;
        public float price;
        public String images;


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

}

