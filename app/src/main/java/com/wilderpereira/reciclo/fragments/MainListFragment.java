package com.wilderpereira.reciclo.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Wilder on 03/08/16.
 */
public class MainListFragment extends AllRecipesFragment {

    public MainListFragment() {
        TAG = "main";
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("recipes");
    }

    @Override
    public boolean shouldCheckStock() {
        return true;
    }
}
