package com.wilderpereira.reciclo.fragments;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.activities.RecipeActivity;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.utils.Utils;
import com.wilderpereira.reciclo.viewholder.ItemViewHolder;


/**
 * Created by Wilder on 10/07/16.
 */
public abstract class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Recipe, ItemViewHolder> adapter;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.recycle_fragment, container, false);

        mDatabase = Utils.getDatabase().getReference();

        Query postsQuery = getQuery(mDatabase);
        adapter = new FirebaseRecyclerAdapter<Recipe, ItemViewHolder>(Recipe.class, R.layout.item_item,
                ItemViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final ItemViewHolder viewHolder, final Recipe model, final int position) {
                final DatabaseReference recipeRef = getRef(position);

                if(model.favoritedBy.containsKey(Utils.uid)){
                    viewHolder.imgStar.setImageResource(R.drawable.circle);
                }

                viewHolder.imgStar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.onStarClicked(recipeRef, Utils.uid);
                    }
                });

                // Set click listener for the whole post view
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch RecipeActivity
                        Intent intent = new Intent(v.getContext(),RecipeActivity.class);
                        intent.putExtra(getString(R.string.recipe_extra_key), model);
                        startActivity(intent);
                    }
                });

                // Bind Post to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToPost(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {

                    }
                });
            }
        };




        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}
