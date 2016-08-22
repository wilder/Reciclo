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

    /** Returns stock key that is used to create the new users' stock */
    public static void createEmptyStock(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = Utils.getDatabase().getReference();

        //Default items TODO: Create default items on firebase
        Map<String, Object> stockItensNode = new HashMap<>();
        //TODO: add type to item
        stockItensNode.put("glass", new StockItem("glass_4x4",0,"https://goo.gl/M3h080").toMap());
        stockItensNode.put("plastic", new StockItem("2l_bottle",0,"https://goo.gl/1mYqPq").toMap());
        stockItensNode.put("paper", new StockItem("4A_paper",0,"https://goo.gl/N39MCC").toMap());
        stockItensNode.put("paper", new StockItem("cardboard",0,"https://goo.gl/7GBsSi").toMap());
        stockItensNode.put("others", new StockItem("wood",0,"https://goo.gl/4yF4El").toMap());

        //Creates the new node
        Map<String, Object> stockNode = new HashMap<>();
        stockNode.put("stocks/"+UID+"/itens",stockItensNode);
        myRef.updateChildren(stockNode);

    }
}
