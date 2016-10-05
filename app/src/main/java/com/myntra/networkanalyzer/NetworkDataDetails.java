package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.DecimalFormat;

import static java.lang.Math.max;

public class NetworkDataDetails extends ActionBarActivity {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private Bundle bundle;
    private SharedPreferences sharedFile;
    private static final String PREFS_NAME = "DataUsage";
    private String appName;
    private double receivedDataUsage;
    private DecimalFormat df;
    private DataUsage dataUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataUsage = new DataUsage(new TrafficStatsDelegate());
        setContentView(R.layout.activity_network_data_details);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        bundle = getIntent().getExtras();
        sharedFile = getSharedPreferences(PREFS_NAME, 0);
        df = new DecimalFormat("#.##");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network_data_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int uid = (int) bundle.get("UID");
        appName = bundle.getString("apppName");
        receivedDataUsage = dataUsage.computeAppUsage(uid);
        double totalDataUsage = dataUsage.computeTotalUsage();
        editText1.setText(df.format(receivedDataUsage));
        editText2.setText(df.format(totalDataUsage));
        editText3.setText(appName);
        try {
            editText4.setText(df.format(getDataUsageFromSharedPreferences(appName)));
            editText5.setText(df.format(receivedDataUsage - (getDataUsageFromSharedPreferences(appName))));
        } catch (NumberFormatException e) {
            editText4.setText(df.format(0.0));
            editText5.setText(df.format(0.0));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        storeInLocalSharedPreferences(appName, receivedDataUsage);
    }

    private void storeInLocalSharedPreferences(String appName, double receivedDataUsage) {
        SharedPreferences.Editor editor = sharedFile.edit();
        editor.putString(appName + "_LastAppDataUsage", String.valueOf(receivedDataUsage));
        editor.apply();
    }

    private double getDataUsageFromSharedPreferences(String appName) {
        sharedFile = this.getPreferences(Context.MODE_PRIVATE);
        double dataUsage = Double.parseDouble(sharedFile.getString(appName + "_LastAppDataUsage", ""));
        return max(0.0, dataUsage);
    }
}
