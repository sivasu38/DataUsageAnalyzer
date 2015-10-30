package com.myntra.networkanalyzer;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * Created by c.sivasubramanian on 26/07/15.
 */
public class DataUsageDetailFragment extends Fragment {

    public String appNameValue;
    public double receivedDataUsage;
    public double totalDataUsage;
    public double lastDataUsage;

    public TextView appName;
    public TextView dataUsageForPrevSession;
    public TextView totalDataUsageofApp;
//    public TextView totalDataUsageofMobile;

    private static final double  MEGABYTE = 1024 * 1024;
    public int uid;
    public SharedPreferences sharedfile;
    public static final String PREFS_NAME = "DataUsage";
    public SharedPreferences.Editor editor;
    DecimalFormat df;
    public boolean compareFlag;

    public DataUsageDetailFragment(boolean compareFlag,int uid,String appNameValue)
    {
        this.compareFlag=compareFlag;
        this.uid=uid;
        this.appNameValue=appNameValue;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datausagedetails1_fragment,container,false);
        appName = (TextView) rootView.findViewById(R.id.textView12);
        dataUsageForPrevSession = (TextView)rootView.findViewById(R.id.textView13);
        totalDataUsageofApp = (TextView)rootView.findViewById(R.id.textView14);
//        totalDataUsageofMobile = (TextView)rootView.findViewById(R.id.textView15);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedfile = getActivity().getSharedPreferences(PREFS_NAME, 0);
        df= new DecimalFormat("#.##");

    }

    public void setAppName(String appName)
    {
        this.appName.setText(appName);
    }

    public void setDataUsageForPrevSession(String dataUsageForPrevSession)
    {
        this.dataUsageForPrevSession.setText(dataUsageForPrevSession);
    }

    public void setTotalDataUsageofApp(String totalDataUsageofApp)
    {
        this.totalDataUsageofApp.setText(totalDataUsageofApp);
    }

    public void setTotalDataUsageofMobile(String totalDataUsageofMobile)
    {
//        this.totalDataUsageofMobile.setText(totalDataUsageofMobile);
    }

    @Override
    public void onResume() {
        super.onResume();
        try
        {
            lastDataUsage = getDataUsageFromSharedPerferences(appNameValue);
        }
        catch (Exception e){
        }
        receivedDataUsage = ((TrafficStats.getUidRxBytes(uid))/MEGABYTE)+((TrafficStats.getUidTxBytes(uid))/MEGABYTE);
        totalDataUsage = ((TrafficStats.getTotalRxBytes())/MEGABYTE)+((TrafficStats.getTotalTxBytes())/MEGABYTE);
        if(isNegative(receivedDataUsage))
        {
            receivedDataUsage=0.0;
        }
        totalDataUsageofApp.setText(df.format(receivedDataUsage));
//        totalDataUsageofMobile.setText(df.format(totalDataUsage));
        appName.setText(appNameValue);
        try {
//            editText4.setText(df.format(getDataUsageFromSharedPerferences(appName)));
            dataUsageForPrevSession.setText(df.format(receivedDataUsage - (getDataUsageFromSharedPerferences(appNameValue))));
        }
        catch (NumberFormatException e)
        {
//            editText4.setText(df.format(0.0));
            dataUsageForPrevSession.setText(df.format(0.0));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        storeInLocalSharedPreferenes(appNameValue,receivedDataUsage);
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    public void storeInLocalSharedPreferenes(String appName,double receivedDataUsage)
    {
        editor = sharedfile.edit();
        editor.putString(appName + "_LastAppDataUsage", String.valueOf(receivedDataUsage));
        editor.commit();
    }

    public double getDataUsageFromSharedPerferences(String appName)
    {
        double dataUsage;
        sharedfile=getActivity().getPreferences(Context.MODE_PRIVATE);
        dataUsage= Double.parseDouble(sharedfile.getString(appName + "_LastAppDataUsage", ""));
        if(isNegative(dataUsage))
        {
            return 0.0;
        }
        else {
            return dataUsage;
        }
    }

    public boolean isNegative(double d) {
        return Double.compare(d, 0.0) < 0;
    }


}
