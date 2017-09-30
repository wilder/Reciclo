package com.wilderpereira.reciclo.fragments;


import android.support.v4.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllRecipesFragment extends ListFragment {

    public AllRecipesFragment() {
        TAG = getResources().getString(R.string.AllRecipesFragment);
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child(getResources().getString(R.string.recipes));
    }

    @Override
    public boolean shouldCheckStock() {
        return false;
    }
}
