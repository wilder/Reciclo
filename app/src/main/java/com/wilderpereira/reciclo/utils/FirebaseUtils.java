package com.wilderpereira.reciclo.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wilderpereira.reciclo.models.StockItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilder on 21/08/16.
 */
public class FirebaseUtils {

    public static final String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

}
