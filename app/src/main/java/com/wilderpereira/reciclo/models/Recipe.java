package com.wilderpereira.reciclo.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ArrayList<Resource> resources;

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
    private static final int MAX_ITEMS_MISSING = 2;

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

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

    public Map<String, Integer> getMissingItems() {
        return missingItems;
    }

    public void setMissingItems(Map<String, Integer> missingItems) {
        this.missingItems = missingItems;
    }

    public boolean isCanBeMadeWithAvaibleStock() {
        return canBeMadeWithAvaibleStock;
    }

    public void setCanBeMadeWithAvaibleStock(boolean canBeMadeWithAvaibleStock) {
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
        return result;
    }

    /**
     * Item cam be made if there isn't much items missing
     * and the user has at least all of the items needed for the recipe
     */
    @Exclude
    private void canBeMade(List<StockItem> stock){
        for(Resource r : this.resources){
            for (StockItem s : stock) {
                if (r.getName().equals(s.getName())){
                    int amountMissing = r.getAmount()-s.getAmount();

                    if (amountMissing > 0){
                        missingItems.put(s.getName(),amountMissing);
                        if(amountMissing > MAX_ITEMS_MISSING){
                            canBeMadeWithAvaibleStock = false;
                        }
                    }
                    break;
                }
            }
        }

    }
}
