package com.example.week1_contact.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ImageClickListener implements OnClickListener {
    Context context;
    int imageID;

    public ImageClickListener(Context context, int imageID){
        this.context = context;
        this.imageID = imageID;
    }

    public void onClick(View v) {
        Intent intent = new Intent(context.getApplicationContext(), ImageActivity.class);
        intent.putExtra("image ID", imageID);
        context.startActivity(intent);
    }
}
