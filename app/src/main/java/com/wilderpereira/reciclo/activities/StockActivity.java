package com.wilderpereira.reciclo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.adapters.StockAdapter;
import com.wilderpereira.reciclo.models.StockItem;
import com.wilderpereira.reciclo.utils.FirebaseUtils;
import com.wilderpereira.reciclo.utils.StockUtils;

import java.util.ArrayList;

/**
 * Created by Wilder on 10/07/16.
 */
public class StockActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private StockAdapter adapter;
    private ArrayList<StockItem> stockItens;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_fragment);

        toolbar = (Toolbar) findViewById(R.id.stockbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_remove_black_24dp);
        getSupportActionBar().setTitle(R.string.stock);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StockActivity.this.finish();
            }
        });

        stockItens = new ArrayList<>();
        loadItens();

        adapter =  new StockAdapter(stockItens);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void loadItens(){

        DatabaseReference databaseReference = FirebaseUtils.getDatabase().getReference().child("stocks/"+uid+"/itens");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                stockItens.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    StockItem item = postSnapshot.getValue(StockItem.class);
                    stockItens.add(item);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}
