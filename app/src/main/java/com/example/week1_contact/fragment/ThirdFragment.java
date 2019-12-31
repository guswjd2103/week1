package com.example.week1_contact.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.week1_contact.R;
import com.example.week1_contact.WifiAdapter;
import com.example.week1_contact.WifiData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;


public class ThirdFragment extends Fragment {

    boolean doneWifiScan = true;
    private WifiManager wifiManager;
    private WifiAdapter adapter;
    private List<WifiData> wifiList = new ArrayList<WifiData>();
    private ListView listView;

    IntentFilter filter;
    private Context mContext;
    private List<ScanResult> scanResults = new ArrayList<>();
    ArrayList<String> BSSIDList = new ArrayList<String>();

    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        return fragment;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                getWifiInfo();
                wifiManager.startScan();

            } else if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                mContext.sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));
            }
        }
    };

    private void getWifiInfo() {

        wifiList.clear();
        BSSIDList.clear();

        if(!doneWifiScan) {
            scanResults = wifiManager.getScanResults();
//            Log.d("scan length", String.valueOf(scanResults.size()));

            for(int i=0; i<scanResults.size();i++) {
                ScanResult select = scanResults.get(i);
                String SSID = select.SSID;
                String BSSID = select.BSSID;
                String RSSI = String.valueOf(select.level);

                if(!BSSIDList.contains(SSID)) {
                    BSSIDList.add(SSID);
                    WifiData wifiData = new WifiData(SSID, BSSID, RSSI);
                    wifiList.add(wifiData);
                }
                Log.d("wifi", SSID);
            }
            Collections.sort(wifiList);
            adapter.notifyDataSetChanged();
            doneWifiScan = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        wifiList.clear();
        BSSIDList.clear();

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[] {
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.CHANGE_WIFI_STATE,
                                Manifest.permission.CHANGE_WIFI_MULTICAST_STATE
                        },
                        1);
            }
        }
//        Log.d("third", "third");

        View view = inflater.inflate(R.layout.fragment_third, container, false);
        mContext = view.getContext();

        wifiManager = (WifiManager) mContext.getSystemService(WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        initWifiScan();

        listView = (ListView) view.findViewById(R.id.listView2);

        adapter = new WifiAdapter(mContext, R.layout.item_accesspoint, wifiList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, wifiList.get(position).getSSID(), Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton btn = (FloatingActionButton)view.findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doneWifiScan == true) {
                    Toast.makeText(getActivity(), "WIFI scan start!!", Toast.LENGTH_LONG).show();
                    wifiManager.startScan();
                    doneWifiScan = false;
                } else {
                    stopWifi();
                }

            }
        });
        return  view;
    }

    public void initWifiScan() {
        filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        mContext.registerReceiver(receiver, filter);
        wifiManager.startScan();
    }

    public void stopWifi() {
        mContext.unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        getContext().registerReceiver(receiver, filter);
        wifiManager.startScan();

        listView.setFocusable(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(receiver);
    }

}
