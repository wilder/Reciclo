package com.wilderpereira.reciclo.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilder on 11/07/16.
 */
@IgnoreExtraProperties
public class StockItem {

    private String name;
    private int amount;
    private String imgUrl;

    public StockItem(){

    }

    public StockItem(String name, int amount, String imgUrl) {
        this.name = name;
        this.amount = amount;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("amount", amount);
        result.put("imgUrl", imgUrl);
        return result;
    }


}
