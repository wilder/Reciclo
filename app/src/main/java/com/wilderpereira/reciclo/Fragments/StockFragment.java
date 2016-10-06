package com.wilderpereira.reciclo.fragments;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wilderpereira.reciclo.adapters.StockAdapter;
import com.wilderpereira.reciclo.models.StockItem;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.utils.FirebaseUtils;
import com.wilderpereira.reciclo.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Wilder on 10/07/16.
 */
public class StockFragment extends Fragment {

    private RecyclerView recyclerView;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private StockAdapter adapter;
    private ArrayList<StockItem> stockItens;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.stock_fragment, container, false);

        stockItens = new ArrayList<>();
        loadItens();

        adapter =  new StockAdapter(stockItens);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
