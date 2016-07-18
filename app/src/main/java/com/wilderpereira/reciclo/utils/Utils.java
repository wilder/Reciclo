package com.wilderpereira.reciclo.utils;
import android.support.design.widget.TextInputLayout;

/**
 * Created by Wilder on 17/07/16.
 */
public class Utils {

    public static void setTextInputLayoutError(TextInputLayout textInputLayout, String message){
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(message);
    }
}
