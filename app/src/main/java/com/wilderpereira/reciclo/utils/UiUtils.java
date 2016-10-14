package com.wilderpereira.reciclo.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilderpereira.reciclo.R;

/**
 * Created by Wilder on 14/10/16.
 */

public class UiUtils {

    public static void addTextViewToLinearLayout(LinearLayout container, String s, String text1, String text2){
        Context c = container.getContext();
        LinearLayout ll = new LinearLayout(c);
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, c.getResources().getDisplayMetrics());

        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER_VERTICAL);

        ll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        ll.addView(createTextView(s,pixels,c));
        ll.addView(createTextView(text1+" "+text2,pixels-10,c));

        container.addView(ll);
    }

    public static TextView createTextView(String s, float px, Context c){
        TextView textView = new TextView(c);
        textView.setText(s);
        textView.setTextColor(c.getResources().getColor(R.color.colorPrimary));
        textView.setTextSize(px);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return textView;
    }

}
