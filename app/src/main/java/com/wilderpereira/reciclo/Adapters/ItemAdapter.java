package com.wilderpereira.reciclo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilderpereira.reciclo.activities.RecipeActivity;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.R;


import java.io.Serializable;
import java.util.ArrayList;

import static com.wilderpereira.reciclo.R.layout.item_item;

/**
 * Created by Wilder on 12/07/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    
    private ArrayList<Recipe> mDataset;
    private Recipe recipe;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearItem;
        public TextView itemName;
        public TextView recycleCount;
        public ImageView itemImage;
        public ImageButton btnFavorite;
        public ImageButton btnFShare;

        public ViewHolder(View v) {
            super(v);
            itemName = (TextView) v.findViewById(R.id.tv_item_name);
            recycleCount = (TextView) v.findViewById(R.id.tv_recycled_count);
            itemImage = (ImageView) v.findViewById(R.id.iv_item_image);
            btnFavorite = (ImageButton) v.findViewById(R.id.ib_favorite);
            btnFShare = (ImageButton) v.findViewById(R.id.ib_share);
            linearItem = (LinearLayout) v.findViewById(R.id.linear_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    //todo: change to item array
    public ItemAdapter(ArrayList<Recipe> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        recipe = mDataset.get(position);
        holder.itemName.setText(recipe.getName());
        holder.recycleCount.setText("Reciclado "+recipe.getRecycleCount()+" vezes.");

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Handle button's click
                Log.d("Onclick", "Favorited Recipe number #"+position);
            }
        });

        holder.btnFShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Handle button's click
                Log.d("Onclick", "Shared Recipe number #"+position);
            }
        });

        holder.linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RecipeActivity.class);
                intent.putExtra(context.getString(R.string.recipe_extra_key), mDataset.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().getApplicationContext().startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
