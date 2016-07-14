package com.wilderpereira.reciclo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wilderpereira.reciclo.models.StockItem;
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

        public ImageView itemImage;
        public TextView itemAmount;
        public ImageButton btnLess;
        public ImageButton btnMore;

        public ViewHolder(View v) {
            super(v);
            itemAmount = (TextView) v.findViewById(R.id.count);
            btnLess = (ImageButton) v.findViewById(R.id.ib_less);
            btnMore = (ImageButton) v.findViewById(R.id.ib_more);
            itemImage = (ImageView) v.findViewById(R.id.iv_resource);
        }
    }

    //todo: change to item array
    public StockAdapter(ArrayList<StockItem> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.stock_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        stockItem = mDataset.get(position);
        //TODO: look for firebase drawable storing
        //holder.itemImage.setImageDrawable();
        holder.itemAmount.setText(stockItem.getAmount()+"");
        //holder.itemImage.Resource();

        holder.btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockItem = mDataset.get(position);
                int newAmount = stockItem.getAmount()-1;
                stockItem.setAmount(newAmount);
                holder.itemAmount.setText(newAmount+"");
            }
        });

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockItem = mDataset.get(position);
                int newAmount = stockItem.getAmount()+1;
                stockItem.setAmount(newAmount);
                holder.itemAmount.setText(newAmount+"");
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
