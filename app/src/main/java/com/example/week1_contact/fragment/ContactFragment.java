package com.example.week1_contact.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;

import com.example.week1_contact.Adapter;
import com.example.week1_contact.ContactData;
import com.example.week1_contact.R;

import java.util.ArrayList;

public class ContactFragment extends Fragment {

    ArrayList<ContactData> contactList;
    Uri cpntactUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false); //동작가능한 view 객체로 생성

        this.InitializeContact();
        ListView listView = (ListView) view.findViewById(R.id.listView);
        Adapter myAdapter = new Adapter(getActivity().getApplicationContext(), contactList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("hi", "fihi");
                parent.getItemAtPosition(position);
                if(contactList == null) {
                    Intent intent = new Intent(getActivity(), this.getClass());
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    public void InitializeContact() {
        contactList = new ArrayList<ContactData>();

        contactList.add(new ContactData("boy", "01010002000"));
        contactList.add(new ContactData("girl", "01030004000"));
    }
}