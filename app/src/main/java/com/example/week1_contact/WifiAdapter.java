package com.example.week1_contact;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


//import com.example.week1_contact.databinding.ItemAccesspointBinding;

import java.util.List;
import java.util.Vector;

public class WifiAdapter extends ArrayAdapter<WifiData> {
    Context context;
    int resource;
    List<WifiData> wifiData;
    LayoutInflater inflater;

    public WifiAdapter(Context context, int resource, List<WifiData> wifiData) {
        super(context, resource, wifiData);
        this.resource = resource;
        this.wifiData = wifiData;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(resource, null);
            holder = new ViewHolder();

            holder.ssidTextView = (TextView) convertView.findViewById(R.id.ssidTextView);
            holder.bssidTextView = (TextView) convertView.findViewById(R.id.bssidTextView);
            holder.rssiLevelTextView = (TextView) convertView.findViewById(R.id.rssiLevelTextView);

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WifiData wifiDatas = wifiData.get(position);

        holder.ssidTextView.setText(wifiDatas.getSSID());
        holder.bssidTextView.setText(wifiDatas.getBSSID());
        holder.rssiLevelTextView.setText(wifiDatas.getRSSI());

        return convertView;
    }

    class ViewHolder {
        TextView ssidTextView;
        TextView bssidTextView;
        TextView rssiLevelTextView;
    }
}
