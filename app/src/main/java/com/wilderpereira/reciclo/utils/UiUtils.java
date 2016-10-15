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

        //ll.setLayoutParams(lp);

        ll.addView(createTextView(s,pixels,c, c.getResources().getColor(R.color.common_google_signin_btn_text_light_default)));
        ll.addView(createTextView(text1+" "+text2,pixels-10,c, c.getResources().getColor(R.color.common_google_signin_btn_text_light_default)));

        container.addView(ll);
    }

     static TextView createTextView(String s, float px, Context c, int color){
        TextView textView = new TextView(c);
        textView.setText(s);
        textView.setTextColor(color);
        textView.setTextSize(px);
        textView.setLayoutParams(getDefaultLayoutParams());
        return textView;
    }

     static  LinearLayout.LayoutParams getDefaultLayoutParams(){
         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                 LinearLayout.LayoutParams.WRAP_CONTENT,
                 LinearLayout.LayoutParams.WRAP_CONTENT);

         lp.setMargins(24,0,0,0);

         return lp;
     }

}
