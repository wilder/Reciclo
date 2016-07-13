package com.wilderpereira.reciclo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wilderpereira.reciclo.Adapters.ItemAdapter;
import com.wilderpereira.reciclo.Models.Recipe;
import com.wilderpereira.reciclo.R;

import java.util.ArrayList;

/**
 * Created by Wilder on 10/07/16.
 */
public class MainListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.recycle_fragment, container, false);

        ItemAdapter adapter = new ItemAdapter(loadRecipesIntoList());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private ArrayList<Recipe> loadRecipesIntoList(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Recipe r = new Recipe();
            r.setName("Item "+i+1);
            r.setRecycleCount(68*i);
            recipes.add(r);
        }
        return recipes;
    }
}
