package com.wilderpereira.reciclo.Models;

import java.util.List;

/**
 * Created by Wilder on 11/07/16.
 */
public class Recipe {
    String name;
    Preparation preparation;
    int recycleCount;
    List<Resource> resouces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Preparation getPreparation() {
        return preparation;
    }

    public void Preparation(Preparation preparation) {
        this.preparation = preparation;
    }

    public int getRecycleCount() {
        return recycleCount;
    }

    public void setRecycleCount(int recycleCount) {
        this.recycleCount = recycleCount;
    }

    public List<Resource> getResouces() {
        return resouces;
    }

    public void setResouces(List<Resource> resouces) {
        this.resouces = resouces;
    }
}
