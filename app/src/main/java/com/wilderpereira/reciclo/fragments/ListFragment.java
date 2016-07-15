package com.wilderpereira.reciclo.fragments;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wilderpereira.reciclo.adapters.ItemAdapter;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.models.Resource;
import com.wilderpereira.reciclo.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by Wilder on 10/07/16.
 */
public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    @ListFragment.ListMode
    private int listMode = LIST_MODE_DEFAULT;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef( {LIST_MODE_FAVORITES, LIST_MODE_DEFAULT})
    public @interface ListMode{}

    public static final int LIST_MODE_DEFAULT = 0;
    public static final int LIST_MODE_FAVORITES = 1;

    public static final ListFragment newInstance(@ListMode int listMode, View view)
    {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt(view.getContext().getString(R.string.LIST_MODE_KEY), listMode);
        fragment.setArguments(bundle);
        return fragment ;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.recycle_fragment, container, false);

        if(getArguments().getInt(getString(R.string.LIST_MODE_KEY)) == LIST_MODE_DEFAULT){
            this.listMode = LIST_MODE_DEFAULT;
        }else{
            this.listMode = LIST_MODE_FAVORITES;
        }

        ItemAdapter adapter = new ItemAdapter(loadRecipesIntoList(), this.listMode);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private ArrayList<Recipe> loadRecipesIntoList(){
        //TODO: Check mode to load favorites or default recipes from firebase
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Recipe r = new Recipe();
            r.setName("Item "+i+1);
            r.setRecycleCount(68*i);
            r.setResouces(getRecipeResources());
            recipes.add(r);
        }
        return recipes;
    }

    private ArrayList<Resource> getRecipeResources(){
        ArrayList<Resource> resources = new ArrayList<>();
        Resource resource;
        for (int i = 0; i < 4; i++){
            resource = new Resource();
            resource.setName("Recurso "+i+1);
            resource.setAmount(3*i);
            resources.add(resource);
        }
        return resources;
    }

}
