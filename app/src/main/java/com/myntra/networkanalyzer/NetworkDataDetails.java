package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.TrafficStats;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;


public class NetworkDataDetails extends ActionBarActivity {
    public TextView textView1;
    public TextView textView2;
    public EditText editText1;
    public EditText editText2;
    public EditText editText3;
    public EditText editText4;
    public EditText editText5;
    public Bundle bundle;
    private static final double  MEGABYTE = 1024 * 1024;
    public int uid;
    public SharedPreferences sharedfile;
    public static final String PREFS_NAME = "DataUsage";
    public SharedPreferences.Editor editor;
    public String appName;
    public double receivedDataUsage;
    public double totalDataUsage;
    public double lastDataUsage;
    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_data_details);
        textView1=(TextView)findViewById(R.id.textView3);
        textView2=(TextView)findViewById(R.id.textView4);
        editText1=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);
        bundle = getIntent().getExtras();
        sharedfile = getSharedPreferences(PREFS_NAME, 0);
        df= new DecimalFormat("#.##");
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
        uid = (int) bundle.get("UID");
        appName=bundle.getString("apppName");
        try
        {
          lastDataUsage = getDataUsageFromSharedPerferences(appName);
        }
        catch (Exception e){
        }
        receivedDataUsage = ((TrafficStats.getUidRxBytes(uid))/MEGABYTE)+((TrafficStats.getUidTxBytes(uid))/MEGABYTE);
        totalDataUsage = ((TrafficStats.getTotalRxBytes())/MEGABYTE)+((TrafficStats.getTotalTxBytes())/MEGABYTE);
        if(isNegative(receivedDataUsage))
        {
            receivedDataUsage=0.0;
        }
        editText1.setText(df.format(receivedDataUsage));
        editText2.setText(df.format(totalDataUsage));
        editText3.setText(appName);
        try {
            editText4.setText(df.format(getDataUsageFromSharedPerferences(appName)));
            editText5.setText(df.format(receivedDataUsage - (getDataUsageFromSharedPerferences(appName))));
        }
        catch (NumberFormatException e)
        {
            editText4.setText(df.format(0.0));
            editText5.setText(df.format(0.0));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        storeInLocalSharedPreferenes(appName,receivedDataUsage);
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
        sharedfile=this.getPreferences(Context.MODE_PRIVATE);
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
