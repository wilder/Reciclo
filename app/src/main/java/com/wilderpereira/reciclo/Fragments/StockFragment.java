package com.wilderpereira.reciclo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wilderpereira.reciclo.adapters.StockAdapter;
import com.wilderpereira.reciclo.models.StockItem;
import com.wilderpereira.reciclo.R;

import java.util.ArrayList;

/**
 * Created by Wilder on 10/07/16.
 */
public class StockFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.stock_fragment, container, false);

        StockAdapter adapter =  new StockAdapter(loadItens());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private ArrayList<StockItem> loadItens(){
        ArrayList<StockItem> stockItens = new ArrayList<>(); //TODO: get from Stock.class
        for (int i = 0; i < 8; i++){
            StockItem stockItem = new StockItem();
            stockItem.setName("Item "+i+1);
            stockItem.setAmount(68*i);
            stockItens.add(stockItem);
        }
        return stockItens;
    }

}
