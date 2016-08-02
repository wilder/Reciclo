package com.wilderpereira.reciclo.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilder on 02/08/16.
 */
public class User {
    private String name;
    private Map<String, Boolean> favorites = new HashMap<>();
    private Map<String, Integer> stock = new HashMap<>();

    public User() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Boolean> getFavorites() {
        return favorites;
    }

    public void setFavorites(Map<String, Boolean> favorites) {
        this.favorites = favorites;
    }

    public Map<String, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<String, Integer> stock) {
        this.stock = stock;
    }
}
