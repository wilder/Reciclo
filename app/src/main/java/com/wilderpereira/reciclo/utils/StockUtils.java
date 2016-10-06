package com.wilderpereira.reciclo.utils;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wilderpereira.reciclo.adapters.StockAdapter;
import com.wilderpereira.reciclo.models.StockItem;

import java.util.HashMap;
import java.util.Map;

import static com.wilderpereira.reciclo.utils.Utils.uid;

/**
 * Created by Wilder on 05/10/16.
 */

public class StockUtils {

    public static void changeAmount(StockItem stockItem, TextView tvAmount, int newAmount) {
        DatabaseReference databaseReference = Utils.getDatabase().getReference().child("stocks/"+uid+"/itens");
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" +stockItem.getName() + "/" + "amount", newAmount);
        databaseReference.updateChildren(childUpdates);
        stockItem.setAmount(newAmount);
        tvAmount.setText(""+newAmount);
    }

    /** Returns stock key that is used to create the new users' stock */
    public static void createEmptyStock(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = Utils.getDatabase().getReference();

        //Default items TODO: Create default items on firebase
        Map<String, Object> stockItensNode = new HashMap<>();
        //TODO: add type to item
        stockItensNode.put("glass_4x4", new StockItem("glass_4x4",0,"https://goo.gl/M3h080").toMap());
        stockItensNode.put("2l_bottle", new StockItem("2l_bottle",0,"https://goo.gl/1mYqPq").toMap());
        stockItensNode.put("4A_paper", new StockItem("4A_paper",0,"https://goo.gl/N39MCC").toMap());
        stockItensNode.put("cardboard", new StockItem("cardboard",0,"https://goo.gl/7GBsSi").toMap());
        stockItensNode.put("wood", new StockItem("wood",0,"https://goo.gl/4yF4El").toMap());

        //Creates the new node
        Map<String, Object> stockNode = new HashMap<>();
        stockNode.put("stocks/"+uid+"/itens",stockItensNode);
        myRef.updateChildren(stockNode);

    }
}
