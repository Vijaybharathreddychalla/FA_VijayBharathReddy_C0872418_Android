package com.example.fa_vijaybharathreddy_c0872418_android;

import java.util.ArrayList;
import java.util.Date;

public class Products {
    public static ArrayList<Products> productsArrayList = new ArrayList<>();
    public static String PRODUCT_EDIT_EXTRA = "productEdit";
    private int id;
    private String productName;
    private int productPrice;
    private String description;
    private Double latitude;
    private Double longitude;
    private Date deleted;

    public Products(int id, String productName, String productPrice, int description, Double latitude, Double longitude) {
        this.id = id;
        this.productName = productName;
        this.productPrice = Integer.parseInt(productPrice);
        this.description = String.valueOf(description);
        this.latitude = latitude;
        this.longitude = longitude;
        deleted = null;
    }

    public Products(int id, String productName, int productPrice, Double description, Double latitude, String longitude, Date deleted) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = String.valueOf(description);
        this.latitude = latitude;
        this.longitude = Double.valueOf(longitude);
        this.deleted = deleted;
    }

    public static ArrayList<Products> getProductsArrayList() {
        return productsArrayList;
    }

    public static void setProductsArrayList(ArrayList<Products> productsArrayList) {
        Products.productsArrayList = productsArrayList;
    }

    public static String getProductEditExtra() {
        return PRODUCT_EDIT_EXTRA;
    }

    public static void setProductEditExtra(String productEditExtra) {
        PRODUCT_EDIT_EXTRA = productEditExtra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public static Products getProductsForID(int passedProductsID) {
        for (Products products : productsArrayList) {
            if (products.getId() == passedProductsID)
                return products;
        }

        return null;
    }

    public static ArrayList<Products> nonDeletedNotes() {
        ArrayList<Products> nonDeleted = new ArrayList<>();
        for (Products products : productsArrayList) {
            if (products.getDeleted() == null)
                nonDeleted.add(products);
        }

        return nonDeleted;
    }


}
