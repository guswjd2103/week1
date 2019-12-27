package com.example.week1_contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ContactData> sample;

    public Adapter(Context context, ArrayList<ContactData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ContactData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, @Nullable  View view, @Nullable ViewGroup parent) {

        view = mLayoutInflater.inflate(R.layout.fragment_contact_item, parent, false);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView phoneNumber = (TextView)view.findViewById(R.id.phoneNumber);

        name.setText(sample.get(position).getName());
        phoneNumber.setText(sample.get(position).getPhoneNumber());

        return view;
    }

}
