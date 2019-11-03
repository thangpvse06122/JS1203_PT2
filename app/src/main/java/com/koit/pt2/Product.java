package com.koit.pt2;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private float price;
    private int id_cat;

    public Product() {
    }

    public Product(int id, String name, float price, int id_cat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.id_cat = id_cat;
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
}
