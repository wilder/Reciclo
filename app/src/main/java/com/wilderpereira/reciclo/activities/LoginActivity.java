package com.wilderpereira.reciclo.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.fragments.LoginFragment;
import com.wilderpereira.reciclo.fragments.SignUpFragment;
import com.wilderpereira.reciclo.models.StockItem;
import com.wilderpereira.reciclo.utils.FragmentUtils;
import com.wilderpereira.reciclo.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        /**Don't worry about creating a new user every time.
         * This activity is only called when the user is not logged in*/
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    /** Called only when user creates an account*/
                    //TODO: create new users node.
                    addNewUser(user.getUid(),user.getEmail());

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };


        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Checks that the activity is using the layout version with the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // if we are being restored from a previous state, then we dont need to do anything and should
            // return or else we could end up with overlapping fragments.
            if (savedInstanceState != null)
                return;

            Fragment fragment;

            fragment = new SignUpFragment();

            // Adds the fragment to the fragment container layout
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void goToLogin(View view) {
        FragmentUtils.navigate(getSupportFragmentManager().beginTransaction(),R.id.fragment_container,new LoginFragment());
    }

    public void goToSignUp(View view) {
        FragmentUtils.navigate(getSupportFragmentManager().beginTransaction(),R.id.fragment_container,new SignUpFragment());
    }

    //TODO: Move methods below to new class

    /** Creates a new user's node */
    private void addNewUser(String userId, String name){
        String stockKey = createEmptyStock();
        DatabaseReference myRef = Utils.getDatabase().getReference("users").child(userId);
        Map<String, String> user = new HashMap<>();
        user.put("name",name);
        user.put("stock",stockKey);
        myRef.setValue(user);
    }

    /** Returns stock key that is used to create the new users' stock */
    private String createEmptyStock(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = Utils.getDatabase().getReference();
        String key = database.getReference("stocks").push().getKey();

        //Default items TODO: Create default items on firebase
        Map<String, Object> stockItensNode = new HashMap<>();
        //TODO: add type to item
        stockItensNode.put("glass_a", new StockItem("Glass",0).toMap());
        stockItensNode.put("plastic_a", new StockItem("Plastic",0).toMap());
        stockItensNode.put("paper_a", new StockItem("Paper",0).toMap());
        stockItensNode.put("Metal_a", new StockItem("Metal",0).toMap());

        //Creates the new node
        Map<String, Object> stockNode = new HashMap<>();
        stockNode.put("stocks/"+key+"/itens",stockItensNode);
        myRef.updateChildren(stockNode);

        return key;
    }

}
