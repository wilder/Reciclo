package com.wilderpereira.reciclo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilderpereira.reciclo.models.Preparation;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.models.Resource;
import com.wilderpereira.reciclo.R;

import java.util.List;

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

        Bundle extras = getIntent().getExtras();
        Recipe recipe = (Recipe) extras.getSerializable(getString(R.string.recipe_extra_key));

        itemName = (TextView) findViewById(R.id.tv_item);
        owner = (TextView) findViewById(R.id.tv_by);
        favoriteCount = (TextView) findViewById(R.id.tv_favorite_count);
        recyleCount = (TextView) findViewById(R.id.tv_recycled_count);
        linearIngredients = (LinearLayout) findViewById(R.id.linear_ingredients);
        linearPreparation = (LinearLayout) findViewById(R.id.linear_preparation);
        btnReciclo = (Button) findViewById(R.id.btn_reciclo);
        itemImage = (ImageView) findViewById(R.id.iv_item_image);


        itemName.setText(recipe.getName());
        favoriteCount.setText(recipe.getRecycleCount()+""); //TODO: Change to favorite count (Also add on firebase)
        recyleCount.setText(recipe.getRecycleCount()+"");

        Preparation preparation = recipe.getPreparation();
        /*for(){

        }*/

        List<Resource> resources = recipe.getResouces();
        for (Resource resource : resources){
            int i = 1;
            TextView tvResource = new TextView(this);
            tvResource.setText("x"+resource.getAmount()+ " "+resource.getName());
            tvResource.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearIngredients.addView(tvResource);
            i++;
        }
    }

}
