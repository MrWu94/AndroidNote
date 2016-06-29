package com.hansheng.studynote.xml;

/**
 * Created by hansheng on 2016/6/26.
 */
public class Book {private int id;

    private String name;
    private float price;

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

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name + ", price:" + price;
    }

}
