package com.wilderpereira.reciclo.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wilder on 11/07/16.
 */
public class Recipe implements Serializable {
    private String name;
    private String preparation;
    private int recycleCount;
    private int favoriteCount;
    private String resource;
    public Map<String, Boolean> favoritedBy = new HashMap<>();

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getRecycleCount() {
        return recycleCount;
    }

    public void setRecycleCount(int recycleCount) {
        this.recycleCount = recycleCount;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Map<String, Boolean> getFavoritedBy() {
        return favoritedBy;
    }

    public void setFavoritedBy(Map<String, Boolean> favoritedBy) {
        this.favoritedBy = favoritedBy;
    }
}
