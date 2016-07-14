package com.wilderpereira.reciclo.models;

import java.util.List;

/**
 * Created by Wilder on 11/07/16.
 */
public class Favorites {
    List<Recipe> favoriteRecipes;

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
