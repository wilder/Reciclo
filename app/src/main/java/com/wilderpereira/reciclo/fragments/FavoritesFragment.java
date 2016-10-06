package com.wilderpereira.reciclo.fragments;


import android.support.v4.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.utils.FirebaseUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends ListFragment {


    public FavoritesFragment() {
        // Required empty public constructor
    }

    //Make onclick abstract and override here for the starClick


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("favorites")
                .child(FirebaseUtils.UID);
    }
}
