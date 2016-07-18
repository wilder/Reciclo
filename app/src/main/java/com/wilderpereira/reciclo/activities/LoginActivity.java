package com.wilderpereira.reciclo.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.fragments.LoginFragment;
import com.wilderpereira.reciclo.fragments.SignUpFragment;
import com.wilderpereira.reciclo.utils.FragmentUtils;

public class LoginActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Checks that the activity is using the layout version with the fragment_container FrameLayout
        if(findViewById(R.id.fragment_container) != null)
        {
            // if we are being restored from a previous state, then we dont need to do anything and should
            // return or else we could end up with overlapping fragments.
            if(savedInstanceState != null)
                return;

            Fragment fragment;

            fragment = new SignUpFragment();

            // Adds the fragment to the fragment container layout
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();

        }

    }

    public void goToLogin(View view) {
        FragmentUtils.navigate(getSupportFragmentManager().beginTransaction(),R.id.fragment_container,new LoginFragment());
    }

    public void goToSignUp(View view) {
        FragmentUtils.navigate(getSupportFragmentManager().beginTransaction(),R.id.fragment_container,new SignUpFragment());
    }
}
