package com.wilderpereira.reciclo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.fragments.LoginFragment;
import com.wilderpereira.reciclo.fragments.SignUpFragment;
import com.wilderpereira.reciclo.utils.FirebaseUtils;
import com.wilderpereira.reciclo.utils.FragmentUtils;
import com.wilderpereira.reciclo.utils.StockUtils;

import java.util.HashMap;
import java.util.Map;

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

                    Log.d(TAG, getResources().getString(R.string.signed_in) + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, getResources().getString(R.string.signed_out));
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
        StockUtils.createEmptyStock();
        DatabaseReference myRef = FirebaseUtils.getDatabase().getReference(getResources().getString(R.string.users)).child(userId);
        Map<String, String> user = new HashMap<>();
        user.put(getResources().getString(R.string.name),name);
        user.put(getResources().getString(R.string.stock_lowercase),FirebaseUtils.UID);
        myRef.setValue(user);
    }
}
