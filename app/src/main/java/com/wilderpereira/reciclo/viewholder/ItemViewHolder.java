package com.wilderpereira.reciclo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.models.Recipe;

/**
 * Created by Wilder on 24/07/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout linearItem;
    public TextView itemName;
    public TextView recycleCount;
    public ImageView itemImage;
    public ImageView imgStar;
    public ImageButton btnFavorite;
    public ImageButton btnFShare;
    //TODO: Add favorite view

    public ItemViewHolder(View v) {
            super(v);
            itemName = (TextView) v.findViewById(R.id.tv_item_name);
            recycleCount = (TextView) v.findViewById(R.id.tv_recycled_count);
            itemImage = (ImageView) v.findViewById(R.id.iv_item_image);
            btnFavorite = (ImageButton) v.findViewById(R.id.ib_favorite);
            btnFShare = (ImageButton) v.findViewById(R.id.ib_share);
            linearItem = (LinearLayout) v.findViewById(R.id.linear_item);
            imgStar = (ImageView) v.findViewById(R.id.img_favorite);
    }

    public void bindToPost(Recipe recipe, View.OnClickListener starClickListener) {
        itemName.setText(recipe.getName());
        recycleCount.setText("Reciclado "+recipe.getRecycleCount()+" vezes.");
        //TODO: favoriteView.setonclicklistener(starclicklistener)
    }



}
