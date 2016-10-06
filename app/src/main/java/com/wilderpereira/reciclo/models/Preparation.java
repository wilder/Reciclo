package com.wilderpereira.reciclo.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wilder on 11/07/16.
 */
public class Preparation implements Serializable{

    List<Steps> steps;

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
}
