package com.myntra.networkanalyzer;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;

public class DataUsageDetailFragment extends Fragment {
    private String appNameValue;
    private double receivedDataUsage;
    private TextView appName;
    private TextView dataUsageForPrevSession;
    private TextView totalDataUsageOfApp;
    private int uid;
    private SharedPreferences sharedFile;
    private static final String PREFS_NAME = "DataUsage";
    private DecimalFormat df;
    private DataUsageUtils dataUsageUtils;
    private DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataUsageUtils = new DataUsageUtils(new TrafficStatsDelegate());
        sharedFile = getActivity().getSharedPreferences(PREFS_NAME, 0);
        df = new DecimalFormat("#.##");
        uid = getArguments().getInt("UID1");
        appNameValue = getArguments().getString("appNameValue1");
        dbHelper= new DBHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datausagedetails1_fragment, container, false);
        appName = (TextView) rootView.findViewById(R.id.textView12);
        dataUsageForPrevSession = (TextView) rootView.findViewById(R.id.textView13);
        totalDataUsageOfApp = (TextView) rootView.findViewById(R.id.textView14);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        receivedDataUsage = dataUsageUtils.computeAppUsage(uid);
        totalDataUsageOfApp.setText(df.format(receivedDataUsage));
        appName.setText(appNameValue);
        HashMap<String,Object> inputdata = dataUsageUtils.setdatainMap(appNameValue,receivedDataUsage);
        try {
            dataUsageForPrevSession.setText(df.format(receivedDataUsage - (getDataUsageFromSharedPreferences(appNameValue))));
        } catch (NumberFormatException e) {
            dataUsageForPrevSession.setText(df.format(0.0));
        }
        new AsyncDBHelper("pumpin",dbHelper,inputdata).execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        storeInLocalSharedPreferences(appNameValue, receivedDataUsage);
    }

    private void storeInLocalSharedPreferences(String appName, double receivedDataUsage) {
        SharedPreferences.Editor editor = sharedFile.edit();
        editor.putString(appName + "_LastAppDataUsage", String.valueOf(receivedDataUsage));
        editor.apply();
    }

    private double getDataUsageFromSharedPreferences(String appName) {
        double dataUsage;
        sharedFile = getActivity().getPreferences(Context.MODE_PRIVATE);
        dataUsage = Double.parseDouble(sharedFile.getString(appName + "_LastAppDataUsage", ""));
        return Math.max(0.0, dataUsage);
    }
}
