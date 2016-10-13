package com.wilderpereira.reciclo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.models.Recipe;
import com.wilderpereira.reciclo.models.Resource;
import com.wilderpereira.reciclo.models.Steps;
import com.wilderpereira.reciclo.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  RecipeActivity extends AppCompatActivity {

    private ImageView itemImage;
    private TextView itemName;
    private TextView owner;
    private TextView favoriteCount;
    private TextView recyleCount;
    private LinearLayout linearIngredients;
    private LinearLayout linearPreparation;
    private Button btnReciclo;
    private DatabaseReference mDatabase;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDatabase = FirebaseUtils.getDatabase().getReference();

        Bundle extras = getIntent().getExtras();
        Recipe recipe = (Recipe) extras.getSerializable(getString(R.string.recipe_extra_key));

        itemName = (TextView) findViewById(R.id.tv_item);

        owner = (TextView) findViewById(R.id.tv_by);
        favoriteCount = (TextView) findViewById(R.id.tv_favorite_count);
        recyleCount = (TextView) findViewById(R.id.tv_recycled_count);
        linearIngredients = (LinearLayout) findViewById(R.id.linear_ingredients);
        linearPreparation = (LinearLayout) findViewById(R.id.linear_preparation);
        btnReciclo = (Button) findViewById(R.id.btn_reciclo);
        itemImage = (ImageView) findViewById(R.id.iv_recipe_image);


        itemName.setText(recipe.getName());
        favoriteCount.setText(recipe.getFavoriteCount()+"");
        recyleCount.setText(recipe.getRecycleCount()+"");

        Picasso.with(this).load(recipe.getImgUrl())
                .noFade()
                .into(itemImage);

        getSupportActionBar().setTitle(recipe.getName());

        recipe.getMissingItems();

        String preparationId = recipe.getPreparation();
        getPreparation(preparationId);

        String resourceId = recipe.getResource();
        getResources(resourceId);

    }

    public void getResources(String resourcesId){
        mDatabase.child("resources").child(resourcesId).child("resource").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot resourceSnapshot: dataSnapshot.getChildren()) {
                            Resource res = resourceSnapshot.getValue(Resource.class);
                            addTextViewToLinearLayout(linearIngredients,"● "+res.getAmount(),res.getName());

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("RecipeActivity", "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

    public void getPreparation(String preparationId){
        mDatabase.child("preparation").child(preparationId).child("steps").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot preparationSnapshot: dataSnapshot.getChildren()) {
                            Steps step = preparationSnapshot.getValue(Steps.class);
                            addTextViewToLinearLayout(linearPreparation,step.getDescription(),"");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("RecipeActivity", "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

    //TODO: Move to helper class
    private void addTextViewToLinearLayout(LinearLayout container, String text1, String text2){
        TextView textView = new TextView(this);
        textView.setText(text1+" "+text2);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(textView);
    }

    //TODO: Change Recipes Resources' nodes name to type|name, split and get name
    private void displayMissingItems(Map<String, Integer> items, LinearLayout container){
        for (Map.Entry<String, Integer> r : items.entrySet()) {
            addTextViewToLinearLayout(container,r.getValue()+"x",r.getKey()+" missing");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handling arrow click to go back
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
