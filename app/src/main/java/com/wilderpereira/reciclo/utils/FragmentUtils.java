package com.wilderpereira.reciclo.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Wilder on 17/07/16.
 */
public class FragmentUtils {

    public static void navigate(FragmentTransaction fragmentTransaction, int container, Fragment fragment){
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
