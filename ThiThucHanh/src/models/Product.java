package models;

import java.util.ArrayList;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quatity;
    private String description;
    private static ArrayList<Product> listProduct;

    public Product(int id, String name, int price, int quatity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quatity = quatity;
        this.description = description;
    }
    public Product(){
        listProduct = new ArrayList<>();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public static void setListProduct(ArrayList<Product> listProduct) {
        Product.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", id, name, price, quatity, description);
    }
}
