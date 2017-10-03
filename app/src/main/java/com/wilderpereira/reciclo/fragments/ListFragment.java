package com.wilderpereira.reciclo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mopub.nativeads.MoPubRecyclerAdapter;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.adapters.RecipesAdapter;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.models.StockItem;
import com.wilderpereira.reciclo.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Wilder on 10/07/16.
 */
public abstract class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private RecipesAdapter adapter;
    private MoPubRecyclerAdapter myMoPubAdapter;
    List<StockItem> stock = new ArrayList<>();
    String TAG = getResources().getString(R.string.ListFragment);


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.recycle_fragment, container, false);

        mDatabase = FirebaseUtils.getDatabase().getReference();
        final List<Recipe> recipes = new ArrayList<>();

        loadItens();

        Query recipesQuery = getQuery(mDatabase);
        adapter = new RecipesAdapter(recipes, recipesQuery);

        myMoPubAdapter = new MoPubRecyclerAdapter(getActivity(), adapter);
        // Create an ad renderer and view binder that describe your native ad layout.
        ViewBinder myViewBinder = new ViewBinder.Builder(R.layout.ad_item)
                .titleId(R.id.tv_ad_title)
                .textId(R.id.tv_ad_description)
                .mainImageId(R.id.iv_ad_main_image)
                .iconImageId(R.id.iv_ad_icon)
                .callToActionId(R.id.btn_ad_call_to_action)
                .build();

        MoPubStaticNativeAdRenderer myRenderer = new MoPubStaticNativeAdRenderer(myViewBinder);

        myMoPubAdapter.registerAdRenderer(myRenderer);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setAdapter(myMoPubAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recipesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                recipes.clear();

                Log.d(TAG, getResources().getString(R.string.onDataChange));

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Recipe item = postSnapshot.getValue(Recipe.class);
                    item.setUid(postSnapshot.getKey());
                    Log.d(TAG, item.getName()+ getResources().getString(R.string.canbemade) +item.canBeMade(stock));
                    if(!shouldCheckStock() || item.canBeMade(stock)) {
                        recipes.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, getResources().getString(R.string.onCancelled));
            }

        });

        return view;
    }

    @Override
    public void onResume() {
        // Optional targeting parameters
        RequestParameters parameters = new RequestParameters.Builder()
                .keywords(getResources().getString(R.string.recycling_nature))
                .build();

        myMoPubAdapter.loadAds(getResources().getString(R.string.ad_code), parameters);

        super.onResume();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

    public abstract boolean shouldCheckStock();


    public void loadItens(){
        DatabaseReference databaseReference = FirebaseUtils.getDatabase().getReference().child(getResources().getString(R.string.stocks)+FirebaseUtils.UID+getResources().getString(R.string.itens));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                stock.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    StockItem item = postSnapshot.getValue(StockItem.class);
                    stock.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDestroy() {
        myMoPubAdapter.destroy();
        super.onDestroy();
    }
}
