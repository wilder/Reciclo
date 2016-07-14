package com.wilderpereira.reciclo.models;

/**
 * Created by Wilder on 11/07/16.
 */
public class Resource {
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
