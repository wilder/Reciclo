package com.wilderpereira.reciclo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wilderpereira.reciclo.R;
import com.wilderpereira.reciclo.activities.MainActivity;
import com.wilderpereira.reciclo.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    String email;
    String pass;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                //TODO: Firebase SignUp
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
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                }

            }
        });

        return v;
    }

}
