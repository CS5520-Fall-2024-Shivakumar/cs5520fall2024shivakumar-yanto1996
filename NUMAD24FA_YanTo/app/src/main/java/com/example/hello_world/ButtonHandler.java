package com.example.hello_world;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ButtonHandler implements OnClickListener {

    private Context context;

    public ButtonHandler(Context context) {
        this.context = context;
    }

    public void onClick(View toast) {
        Toast.makeText(context, "Yan To\n to.y@northeastern.edu", Toast.LENGTH_LONG).show();
    }
}

