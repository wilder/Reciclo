package com.wilderpereira.reciclo.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wilderpereira.reciclo.Models.StockItem;
import com.wilderpereira.reciclo.R;

import java.util.ArrayList;

/**
 * Created by Wilder on 12/07/16.
 */
public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private ArrayList<StockItem> mDataset;
    private StockItem stockItem;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        //public ImageView itemImage;
        public TextView itemAmount;
        public ImageButton btnLess;
        public ImageButton btnMore;

        public ViewHolder(View v) {
            super(v);
            itemAmount = (TextView) v.findViewById(R.id.count);
            btnLess = (ImageButton) v.findViewById(R.id.ib_less);
            btnMore = (ImageButton) v.findViewById(R.id.ib_more);
            //itemImage = (ImageView) v.findViewById(R.id.iv_resource);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    //todo: change to item array
    public StockAdapter(ArrayList<StockItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(R.layout.stock_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        stockItem = mDataset.get(position);
        holder.itemAmount.setText(stockItem.getAmount());
        //holder.itemImage.Resource();

        holder.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAmount = stockItem.getAmount()-1;
                stockItem.setAmount(newAmount);
                holder.itemAmount.setText(newAmount);
            }
        });

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAmount = stockItem.getAmount()+1;
                stockItem.setAmount(newAmount);
                holder.itemAmount.setText(newAmount);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
