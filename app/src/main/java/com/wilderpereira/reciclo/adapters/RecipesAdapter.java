package com.wilderpereira.reciclo.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.activities.RecipeActivity;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.models.Resource;
import com.wilderpereira.reciclo.utils.FirebaseUtils;
import com.wilderpereira.reciclo.utils.Utils;

import java.util.List;

import static com.wilderpereira.reciclo.R.layout.item_item;

/**
 * Created by Wilder on 08/10/16.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{
    private List<Recipe> mDataset;
    private Context context;
    private Query query;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, andØØ
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        LinearLayout linearItem;
        TextView itemName;
        TextView favoriteCount;
        TextView recycleCount;
        TextView canBeMade;
        ImageView itemImage;
        ImageView imgStar;
        String star;

        ViewHolder(View v) {
            super(v);
            itemName = (TextView) v.findViewById(R.id.tv_item_name);
            recycleCount = (TextView) v.findViewById(R.id.tv_recycled_count);
            itemImage = (ImageView) v.findViewById(R.id.iv_item_image);
            linearItem = (LinearLayout) v.findViewById(R.id.linear_item);
            imgStar = (ImageView) v.findViewById(R.id.iv_favorite);
            favoriteCount = (TextView) v.findViewById(R.id.tv_favorite_count);
            canBeMade = (TextView) v.findViewById(R.id.tv_can_be_made);
            star = v.getResources().getString(R.string.stars);
         }
    }

    public RecipesAdapter(List<Recipe> myDataset, Query query) {
        mDataset = myDataset;
        this.query = query;
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(item_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final Recipe recipe = mDataset.get(position);
        final DatabaseReference recipeRef = query.getRef().child(recipe.getUid());

        holder.itemName.setText(recipe.getName());
        holder.recycleCount.setText(""+recipe.getRecycleCount());
        holder.favoriteCount.setText(""+recipe.getFavoriteCount());

        if(!recipe.canBeMadeWithAvaibleStock()){
            holder.canBeMade.setText("Faltam x itens");
            holder.canBeMade.setBackgroundColor(context.getResources().getColor(R.color.colorNotAvailable));
        }

        // Set click listener for the whole post view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch RecipeActivity
                Intent intent = new Intent(v.getContext(),RecipeActivity.class);
                intent.putExtra(context.getString(R.string.recipe_extra_key), recipe);
                context.startActivity(intent);
            }
        });

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.onStarClicked(recipeRef, FirebaseUtils.UID);
                //Log.d("click", "imgstar: ");
            }
        });

        Log.d("adapter", recipe.getName()+"favoritedBy.containsKey(FirebaseUtils.UID) "+recipe.favoritedBy.containsKey(FirebaseUtils.UID));
        if(recipe.favoritedBy.containsKey(FirebaseUtils.UID)){
            holder.imgStar.setImageResource(R.drawable.circle);
        }else{
            holder.imgStar.setImageResource(R.drawable.star);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
