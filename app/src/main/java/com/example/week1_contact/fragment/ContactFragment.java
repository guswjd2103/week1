package com.example.week1_contact.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;

import com.example.week1_contact.Adapter;
import com.example.week1_contact.ContactData;
import com.example.week1_contact.R;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {
    ArrayList<ContactData> contactList = new ArrayList<ContactData>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);
            }
        }

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        this.getContacts(getActivity(), contactList);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        Adapter myAdapter = new Adapter(getActivity(), contactList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                if(contactList == null) {
                    Intent intent = new Intent(getActivity(), this.getClass());
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    public List<ContactData> getContacts(Context context, List<ContactData> contactsList) {
        ContentResolver resolver = context.getContentResolver();
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = resolver.query(phoneUri, projection, null, null, null);

        if(cursor!=null){
            while(cursor.moveToNext()){
                int idx = cursor.getColumnIndex(projection[0]);
                int nameidx = cursor.getColumnIndex(projection[1]);
                int numberidx = cursor.getColumnIndex(projection[2]);

                String name = cursor.getString(nameidx);
                String number = cursor.getString(numberidx);

                ContactData contactData = new ContactData(R.drawable.android, name, number);
                contactsList.add(contactData);
            }
        }

        cursor.close();
        return contactsList;
    }

}
