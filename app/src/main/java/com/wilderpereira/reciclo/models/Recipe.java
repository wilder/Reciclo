package com.wilderpereira.reciclo.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Wilder on 11/07/16.
 */
public class Recipe implements Serializable {

    /**
     * Name of the recipe
     */
    private String name;

    /**
     * Id for the preparation node
     */
    private String preparation;

    /**
     * Number of times the recipe was made
     */
    private int recycleCount;

    /**
     * Number of times the recipe was marked as favoride
     */
    private int favoriteCount;

    /**
     * Resource's node id
     */
    private String resource;

    /**
     * Favorited by
     */
    public Map<String, Boolean> favoritedBy = new HashMap<>();

    /**
     * Resources needed no make the recipe
     */
    @Exclude
    private  Map<String, Object> resources;

    /**
     * Stores the item name and the amount missing
     */
    @Exclude
    private Map<String, Integer> missingItems = new HashMap<>();

    /**
     * Tells if the recipe can be made using the available stock
     */
    @Exclude
    private boolean canBeMadeWithAvaibleStock = true;

    @Exclude
    private static final int MAX_ITEMS_MISSING = 1;

    public Recipe() {
    }

    public Map<String, Object> getResources() {
        return resources;
    }

    public void setResources(Map<String, Object> resources) {
        this.resources = resources;
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

    public Map<String, Integer> getMissingItems() {
        return missingItems;
    }

    public void setMissingItems(Map<String, Integer> missingItems) {
        this.missingItems = missingItems;
    }

    public boolean canBeMadeWithAvaibleStock() {
        return canBeMadeWithAvaibleStock;
    }

    public void canBeMadeWithAvaibleStock(boolean canBeMadeWithAvaibleStock) {
        this.canBeMadeWithAvaibleStock = canBeMadeWithAvaibleStock;
    }

    public Map toMap(){
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("preparation",preparation);
        result.put("recycleCount",recycleCount);
        result.put("resource",resource);
        result.put("favoriteCount",favoriteCount);
        result.put("favoritedBy",favoritedBy);
        result.put("resources",resources);
        return result;
    }

    /**
     * Item cam be made if the user has all of the items
     * needed for the recipe, not considering the amount
     */
    @Exclude
    public boolean canBeMade(List<StockItem> stock){
        for (Map.Entry<String, Object> r : this.resources.entrySet())
        {
            for (StockItem s : stock) {
                if (r.getKey().equals(s.getName())){
                    int amountMissing = Integer.parseInt(r.getValue().toString())-s.getAmount();

                    if (amountMissing > 0){
                        missingItems.put(s.getName(),amountMissing);
                    }

                    if(canBeMadeWithAvaibleStock && s.getAmount() == 0){
                        canBeMadeWithAvaibleStock = false;
                    }

                    break;
                }
            }
        }

        return this.canBeMadeWithAvaibleStock;
    }
}
