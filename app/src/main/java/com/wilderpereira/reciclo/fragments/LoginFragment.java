package com.wilderpereira.reciclo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.activities.MainActivity;
import com.wilderpereira.reciclo.utils.Utils;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private String email;
    private String pass;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText etEmail = (EditText) v.findViewById(R.id.et_email);
        final EditText etPassword = (EditText) v.findViewById(R.id.et_pass);

        Button btnLogin = (Button) v.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean areEditTextsEmpty = false;
                email = etEmail.getText().toString();
                pass = etPassword.getText().toString();

                if(email.isEmpty()){
                    Utils.setTextInputLayoutError((TextInputLayout) v.findViewById(R.id.tip_email),getString(R.string.email_empty));
                    areEditTextsEmpty = true;
                }

                if(pass.isEmpty()){
                    Utils.setTextInputLayoutError((TextInputLayout) v.findViewById(R.id.tip_pass),getString(R.string.password_empty));
                    areEditTextsEmpty = true;
                }

                //TODO: Store login shared preferences
                if(!areEditTextsEmpty){
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("Login", "signInWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(getActivity(), R.string.invalid_credentials, Toast.LENGTH_SHORT).show();
                                    }else{
                                        //successful
                                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
                                    }

                                }
                            });
                }
            }
        });

        return v;
    }


}
