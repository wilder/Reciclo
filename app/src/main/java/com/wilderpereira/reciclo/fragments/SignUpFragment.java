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
import com.google.firebase.auth.FirebaseUser;
import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.activities.MainActivity;
import com.wilderpereira.reciclo.utils.Utils;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;
    private final String TAG = "signup_fragment";
    private String email;
    private String pass;


    public SignUpFragment() {
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
        final View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        final EditText etEmail = (EditText) v.findViewById(R.id.et_email_s);
        final EditText etPassword = (EditText) v.findViewById(R.id.et_pass_s);

        Button signUpButton = (Button) v.findViewById(R.id.btn_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
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

                if(!areEditTextsEmpty){
                    //TODO: to pass username use getActivity().setUserName(username) and get in the listener
                    createUser();
                }

            }
        });

        return v;
    }

    private void createUser(){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), R.string.user_exists, Toast.LENGTH_SHORT).show();
                        }else{
                            //user created
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent);
                        }
                    }
                });
    }


}
