package org.maktab.digikala.data.model;

import java.util.List;

public class Product {
private int mId;
private String mName;
private String  mPrice;
private List<String> ImagesUrl;

    public Product(int id, String name, String price, List<String> imagesUrl) {
        mId = id;
        mName = name;
        mPrice = price;
        ImagesUrl = imagesUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public List<String> getImagesUrl() {
        return ImagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        ImagesUrl = imagesUrl;
    }
}
