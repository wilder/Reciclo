package com.wilderpereira.reciclo.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.utils.Utils;

/**
 * Created by Wilder on 03/08/16.
 */
public class MainListFragment extends ListFragment {

    public MainListFragment() {
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("recipes");
    }
}
