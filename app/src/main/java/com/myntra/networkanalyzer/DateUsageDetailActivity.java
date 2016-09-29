package com.myntra.networkanalyzer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by c.sivasubramanian on 26/07/15.
 */
public class DateUsageDetailActivity extends ActionBarActivity {

    private Bundle bundle;
    public Fragment dataUsageDetailFragment1;
    public Fragment dataUsageDetailFragment2;
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    public boolean compareFlag;
    public int uid1;
    public String appNameValue1;
    public int uid2;
    public String appNameValue2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datausagedetails);
        bundle = getIntent().getExtras();
        compareFlag = bundle.getBoolean("compareFlag");
        uid1 = (int) bundle.get("UID1");
        appNameValue1=bundle.getString("appName1");


        if(savedInstanceState==null) {
            Bundle bundlenew1 = new Bundle();
            bundlenew1.putBoolean("compareFlag",false);
            bundlenew1.putInt("uid1",uid1);
            bundlenew1.putString("appNameValue1",appNameValue1);
            dataUsageDetailFragment1 = new DataUsageDetailFragment();
            dataUsageDetailFragment1.setArguments(bundlenew1);
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content1, dataUsageDetailFragment1);
            fragmentTransaction.commit();
            if (compareFlag) {
                uid2 = (int) bundle.get("UID2");
                appNameValue2 = bundle.getString("appName2");
                Bundle bundlenew2 = new Bundle();
                bundlenew2.putBoolean("compareFlag",false);
                bundlenew2.putInt("uid1",uid2);
                bundlenew2.putString("appNameValue1",appNameValue2);
                dataUsageDetailFragment2 = new DataUsageDetailFragment();
                dataUsageDetailFragment2.setArguments(bundlenew2);
                fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content2, dataUsageDetailFragment2);
                fragmentTransaction.commit();
            }
        }

    }
}
