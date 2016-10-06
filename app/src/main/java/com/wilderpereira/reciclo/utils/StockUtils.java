package com.wilderpereira.reciclo.utils;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
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
}
