package com.example.week1_contact.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.week1_contact.R;

import java.util.ArrayList;

public class PhotoFragment_Zoomed_Activity extends Activity{

    private Context mContext = null;
    private int position;

    SliderAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_slide);
        mContext = this;

        Intent i = getIntent();
        ArrayList<String> DATA = (ArrayList<String>) i.getSerializableExtra("thumbsDataList");

        position = Integer.parseInt(DATA.get(DATA.size()-1));
        DATA.remove(DATA.size()-1);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new SliderAdapter(this, DATA);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    public void onClick(View v) {
    }

    public class SliderAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private Context context;
        private  ArrayList<String> thumbsDataList;

        private final int imgWidth = 320;
        private final int imgHeight = 372;

        public SliderAdapter(Context context, ArrayList<String> thumbsDataList){
            this.context = context;
            this.thumbsDataList = thumbsDataList;
        }

        @Override
        public int getCount(){
            return thumbsDataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object){
            return view == ((ConstraintLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.activity_zoomed, container, false);


            BitmapFactory.Options bo = new BitmapFactory.Options();
            bo.inSampleSize = 8;
            ImageView iv = (ImageView) v.findViewById(R.id.imageZoomedView);
            Bitmap bmp = BitmapFactory.decodeFile(thumbsDataList.get(position), bo);
            Bitmap resized = Bitmap.createScaledBitmap(bmp, 360, 480, true);
            iv.setImageBitmap(resized);
            container.addView(v);
            return v;
        }

        public void destroyItem(ViewGroup container, int position, Object object){
            container.invalidate();
        }
    }
}