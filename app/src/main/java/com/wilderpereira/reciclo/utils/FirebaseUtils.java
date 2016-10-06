package com.wilderpereira.reciclo.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Wilder on 21/08/16.
 */
public class FirebaseUtils {

    public static final String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

}
