package com.myntra.networkanalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */
public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(final Context context, Intent intent) {
        final DBHelper dbHelper = new DBHelper(context);

        if(dbHelper.checkForTableContents())
        {
            ArrayList<DataUsage> list = dbHelper.getAllRecords();
           DataUsageClient dataUsageClient = RetrofitService.createService(DataUsageClient.class);
            dataUsageClient.postHistoricalDataUsage(list, new retrofit.Callback<ArrayList<DataUsage>>() {
                @Override
                public void success(ArrayList<DataUsage> dataUsages, Response response) {
                    Toast.makeText(context, "Data Usage got Stored Successfully", Toast.LENGTH_SHORT);
                    dbHelper.deleteAllContents();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(context, "Data Usage Storage Failed !!!", Toast.LENGTH_SHORT);
                }
            });
        }
    }
}
