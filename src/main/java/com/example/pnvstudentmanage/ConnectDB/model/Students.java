package com.example.pnvstudentmanage.ConnectDB.model;

public class Students {
    public int id;
    public String name;
    public float score;

    public Students(int id, String name, float score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    public Students(String name, float score) {
        this.name = name;
        this.score = score;
    }

}
