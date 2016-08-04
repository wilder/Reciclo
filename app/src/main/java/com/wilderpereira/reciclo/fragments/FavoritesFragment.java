package com.wilderpereira.reciclo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.utils.Utils;

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
                .child(Utils.uid);
    }
}
