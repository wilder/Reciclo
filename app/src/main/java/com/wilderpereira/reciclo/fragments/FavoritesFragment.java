package com.wilderpereira.reciclo.fragments;


import android.support.v4.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.utils.FirebaseUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends ListFragment {


    public FavoritesFragment() {
        TAG = getResources().getString(R.string.FavoritesFragment);
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child(getResources().getString(R.string.favorites))
                .child(FirebaseUtils.UID);
    }

    @Override
    public boolean shouldCheckStock() {
        return false;
    }

}
