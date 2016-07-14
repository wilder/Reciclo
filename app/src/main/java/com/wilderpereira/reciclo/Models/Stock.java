package com.wilderpereira.reciclo.models;

import java.util.List;

/**
 * Created by Wilder on 11/07/16.
 */
public class Stock {

    List<StockItem> stockItems;

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }
}
