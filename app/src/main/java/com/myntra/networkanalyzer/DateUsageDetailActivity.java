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
            dataUsageDetailFragment1 = new DataUsageDetailFragment(false, uid1, appNameValue1);
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content1, dataUsageDetailFragment1);
            fragmentTransaction.commit();
            if (compareFlag) {
                uid2 = (int) bundle.get("UID2");
                appNameValue2 = bundle.getString("appName2");
                dataUsageDetailFragment2 = new DataUsageDetailFragment(false, uid2, appNameValue2);
                fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content2, dataUsageDetailFragment2);
                fragmentTransaction.commit();
            }
        }

    }
}
