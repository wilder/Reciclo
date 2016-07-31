package com.wilderpereira.reciclo.utils;
import android.support.design.widget.TextInputLayout;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.wilderpereira.reciclo.models.Recipe;

/**
 * Created by Wilder on 17/07/16.
 */
public class Utils {

    public static void setTextInputLayoutError(TextInputLayout textInputLayout, String message){
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(message);
    }

    public static void onStarClicked(DatabaseReference postRef, final String uid) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Recipe p = mutableData.getValue(Recipe.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                if (p.favoritedBy.containsKey(uid)) {
                    // Unstar the post and remove self from stars
                    p.setFavoriteCount(p.getFavoriteCount()-1);
                    p.favoritedBy.remove(uid);
                } else {
                    // Star the post and add self to stars
                    p.setFavoriteCount(p.getFavoriteCount()+1);
                    p.favoritedBy.put(uid, true);
                }

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(getClass().getSimpleName(), "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    public static String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
