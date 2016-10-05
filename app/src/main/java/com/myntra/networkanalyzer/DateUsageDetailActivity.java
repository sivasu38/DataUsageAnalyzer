package com.myntra.networkanalyzer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class DateUsageDetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datausagedetails);
        Bundle bundle = getIntent().getExtras();
        boolean compareFlag = bundle.getBoolean("compareFlag");
        int uid1 = (int) bundle.get("UID1");
        String appNameValue1 = bundle.getString("appName1");


        if(savedInstanceState==null) {
            Bundle bundlenew1 = new Bundle();
            bundlenew1.putBoolean("compareFlag",false);
            bundlenew1.putInt("uid1", uid1);
            bundlenew1.putString("appNameValue1", appNameValue1);
            Fragment dataUsageDetailFragment1 = new DataUsageDetailFragment();
            dataUsageDetailFragment1.setArguments(bundlenew1);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content1, dataUsageDetailFragment1);
            fragmentTransaction.commit();
            if (compareFlag) {
                int uid2 = (int) bundle.get("UID2");
                String appNameValue2 = bundle.getString("appName2");
                Bundle bundlenew2 = new Bundle();
                bundlenew2.putBoolean("compareFlag",false);
                bundlenew2.putInt("uid1", uid2);
                bundlenew2.putString("appNameValue1", appNameValue2);
                Fragment dataUsageDetailFragment2 = new DataUsageDetailFragment();
                dataUsageDetailFragment2.setArguments(bundlenew2);
                fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content2, dataUsageDetailFragment2);
                fragmentTransaction.commit();
            }
        }

    }
}
