package com.example.week1_contact;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.week1_contact.fragment.ContactFragment;
import com.example.week1_contact.fragment.PhotoFragment;
import com.example.week1_contact.fragment.ThirdFragment;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private ContactFragment contact_fragment;
    private PhotoFragment photo_fragment;
    private ThirdFragment third_fragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        contact_fragment = new ContactFragment();
        photo_fragment = new PhotoFragment();
        third_fragment = new ThirdFragment();

        transaction.replace(R.id.frameLayout, contact_fragment).commit();

    }

    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_ContactFragment:
                transaction.replace(R.id.frameLayout, contact_fragment).commit();
                break;
            case R.id.btn_PhotoFragment:
                transaction.replace(R.id.frameLayout, photo_fragment).commit();
                break;
            case R.id.btn_ThirdFragment:
                transaction.replace(R.id.frameLayout, third_fragment).commit();
                break;
        }
    }
}
