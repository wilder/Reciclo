package com.wilderpereira.reciclo.models;

import java.io.Serializable;

/**
 * Created by Wilder on 11/07/16.
 */
public class Steps implements Serializable{

    private String description;

    public Steps() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
