package com.wilderpereira.reciclo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilderpereira.reciclo.R;

public class RecipeActivity extends AppCompatActivity {

    public ImageView itemImage;
    public TextView itemName;
    public TextView owner;
    public TextView favoriteCount;
    public TextView recyleCount;
    public LinearLayout linearIngredients;
    public LinearLayout linearPreparation;
    public Button btnReciclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        itemName = (TextView) findViewById(R.id.tv_item);
        owner = (TextView) findViewById(R.id.tv_by);
        favoriteCount = (TextView) findViewById(R.id.tv_favorite_count);
        recyleCount = (TextView) findViewById(R.id.tv_recycled_count);
        linearIngredients = (LinearLayout) findViewById(R.id.linear_ingredients);
        linearPreparation = (LinearLayout) findViewById(R.id.linear_preparation);
        btnReciclo = (Button) findViewById(R.id.btn_reciclo);
        itemImage = (ImageView) findViewById(R.id.iv_item_image);
    }
}
