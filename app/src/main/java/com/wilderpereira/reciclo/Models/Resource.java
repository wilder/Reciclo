package com.wilderpereira.reciclo.models;

import java.io.Serializable;

/**
 * Created by Wilder on 11/07/16.
 */
public class Resource implements Serializable{
    int amount;
    String name;
    String type;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
