package com.wilderpereira.reciclo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.activities.RecipeActivity;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.utils.FirebaseUtils;
import com.wilderpereira.reciclo.utils.Utils;

import java.util.List;

import static com.wilderpereira.reciclo.R.layout.item_item;

/**
 * Created by Wilder on 08/10/16.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{
    private List<Recipe> mDataset;
    private Recipe recipe;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, andØØ
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public LinearLayout linearItem;
        public TextView itemName;
        public TextView favoriteCount;
        public TextView recycleCount;
        public ImageView itemImage;
        public ImageView imgStar;
        public ImageButton btnFavorite;
        public ImageButton btnFShare;
        public String star;

        public ViewHolder(View v) {
            super(v);
            itemName = (TextView) v.findViewById(R.id.tv_item_name);
            recycleCount = (TextView) v.findViewById(R.id.tv_recycled_count);
            itemImage = (ImageView) v.findViewById(R.id.iv_item_image);
            btnFavorite = (ImageButton) v.findViewById(R.id.ib_favorite);
            btnFShare = (ImageButton) v.findViewById(R.id.ib_share);
            linearItem = (LinearLayout) v.findViewById(R.id.linear_item);
            imgStar = (ImageView) v.findViewById(R.id.img_favorite);
            favoriteCount = (TextView) v.findViewById(R.id.tv_favorite_count);
            star = v.getResources().getString(R.string.stars);
        }
    }

    public RecipesAdapter(List<Recipe> myDataset) {
        mDataset = myDataset;
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


        recipe = mDataset.get(position);
        final DatabaseReference recipeRef = FirebaseUtils.getDatabase().getReference("recipes").child(recipe.getUid());

        holder.itemName.setText(recipe.getName());
        holder.recycleCount.setText("Reciclado "+recipe.getRecycleCount()+" vezes.");

        if(recipe.favoritedBy.containsKey(FirebaseUtils.UID)){
            holder.imgStar.setImageResource(R.drawable.circle);
        }

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.onStarClicked(recipeRef, FirebaseUtils.UID);
            }
        });

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

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
