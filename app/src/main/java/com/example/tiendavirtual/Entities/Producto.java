package com.example.tiendavirtual.Entities;

public class Producto {
    private int id;
    private byte[] image;
    private String name;
    private String description;
    private int price;
    private String information;

    public Producto(int id, byte[] image, String name, String description, int price, String information) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.information = information;
    }

    public  int getId(){return id;}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
